package com.desafiojuniorassembleiavotacao.assembleiaVotacao.service;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.SessaoFechadaException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper.SessaoVotacaoMapper;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.SessaoVotacao;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.SessaoVotacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SessaoVotacaoService {
    private final SessaoVotacaoRepository repository;
    private final PautaService pautaService;


    public SessaoVotacaoService(SessaoVotacaoRepository repository, PautaService pautaService) {
        this.repository = repository;
        this.pautaService = pautaService;

    }

    @Transactional
    public SessaoVotacaoResponseDTO salvarInicioSessao(SessaoVotacaoRequestDTO dto) {
        Pauta pauta = pautaService.buscarPautaVotacaoPorId(dto.pautaId());

        SessaoVotacao sessaoVotacao = SessaoVotacaoMapper.toEntity(dto, pauta);
        iniciarVotacao(sessaoVotacao);
        repository.save(sessaoVotacao);

        return SessaoVotacaoMapper.toDto(sessaoVotacao);
    }

    public void iniciarVotacao(SessaoVotacao sessaoVotacao) {

        sessaoVotacao.setDataInicio(LocalDateTime.now());

        if (sessaoVotacao.getDuracao() == null || sessaoVotacao.getDuracao() == 0) {
            sessaoVotacao.setDataFim(sessaoVotacao.getDataInicio().plusMinutes(1));
        }

        sessaoVotacao.setDataFim(
                sessaoVotacao.getDataInicio()
                        .plusMinutes(sessaoVotacao.getDuracao()));

    }

    public void verificaSessaoAberta(Pauta pauta) {
        if (pauta.getSessaoVotacao() == null) {
            throw new SessaoFechadaException("Sessao nao aberta");
        }
        if (LocalDateTime.now().isBefore(pauta.getSessaoVotacao().getDataInicio()) ||
                LocalDateTime.now().isAfter(pauta.getSessaoVotacao().getDataFim()))
        {
            throw new SessaoFechadaException("Votacao nao esta ativa");
        }
    }
}
