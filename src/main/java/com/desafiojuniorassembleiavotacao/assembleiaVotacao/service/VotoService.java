package com.desafiojuniorassembleiavotacao.assembleiaVotacao.service;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoPorNomeAndCpfRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoPorNomeAndCpfResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper.VotoMapper;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper.VotoPorNomeAndCpfMapper;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.ResultadoPauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Voto;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.VotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class VotoService {

    private final PautaDomainService domainService;
    private final VotoRepository repository;
    private final PautaService pautaService;
    private final AssociadoService associadoService;
    private final SessaoVotacaoService sessaoVotacaoService;

    public VotoService(PautaDomainService domainService,
            VotoRepository votoRepository,
            PautaService pautaService,
            AssociadoService associadoService, SessaoVotacaoService sessaoVotacaoService)
    {

        this.domainService = domainService;
        this.repository = votoRepository;
        this.pautaService = pautaService;
        this.associadoService = associadoService;
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @Transactional
    public VotoPorNomeAndCpfResponseDTO salvarVoto(VotoDTO dto) {

        Pauta pauta = buscaPauta(dto.pautaId());
        Associado associado = buscaAssociado(dto.associadoId());

        sessaoVotacaoService.verificaSessaoAberta(pauta);
        boolean jaVotou = associadoJaVotou(pauta, associado);
        domainService.validarDuplicidade(jaVotou);

        Voto voto = VotoMapper.toEntity(dto, pauta, associado);

        repository.save(voto);
        return VotoPorNomeAndCpfMapper.toDto(voto);
    }

    @Transactional
    public VotoPorNomeAndCpfResponseDTO salvarVotoPorNomeAndCpf(VotoPorNomeAndCpfRequestDTO dto) {

        Pauta pauta = buscaPautaPorNome(dto.nomePauta());
        Associado associado = buscaAssociadoPorCpf(dto.cpf());

        sessaoVotacaoService.verificaSessaoAberta(pauta);
        boolean jaVotou = associadoJaVotou(pauta, associado);
        domainService.validarDuplicidade(jaVotou);

        Voto voto = VotoPorNomeAndCpfMapper.toEntity(dto, pauta, associado);

        repository.save(voto);
        return VotoPorNomeAndCpfMapper.toDto(voto);

    }

    public boolean associadoJaVotou(Pauta pauta, Associado associado) {
        return repository.existsByPautaAndAssociado(pauta, associado);
    }


    private Pauta buscaPauta(UUID id) {
        return pautaService.buscarPautaVotacaoPorId(id);
    }

    private Pauta buscaPautaPorNome(String nomePauta) {
        return pautaService.buscarPautaPorNome(nomePauta);
    }

    private Associado buscaAssociadoPorCpf(String cpf) {
        return associadoService.buscarAssociadoPorCpf(cpf);
    }

    private Associado buscaAssociado(UUID id) {
        return associadoService.buscarAssociado(id);
    }

    public long contaSim(Pauta pauta) {

        return repository.contaVotosSim(pauta.getId());
    }

    public long contaNao(Pauta pauta) {

        return repository.contaVotosNao(pauta.getId());
    }

    public ResultadoPauta vencedor(long votosSim, long votosNao) {

        if (votosSim > votosNao) {

            return (ResultadoPauta.APROVADA);
        } else if (votosNao > votosSim) {

            return (ResultadoPauta.REPROVADA);
        } else {

            return (ResultadoPauta.EMPATE);
        }
    }

}

