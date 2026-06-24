package com.desafiojuniorassembleiavotacao.assembleiaVotacao.service;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.SessaoAindaAbertaException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.SessaoEncerradaEApuradaException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.SessaoFechadaException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper.SessaoVotacaoMapper;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.SessaoStatus;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.SessaoVotacao;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.SessaoVotacaoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

        boolean existeSessaoAberta = repository.findByPautaIdAndStatus(dto.pautaId(), SessaoStatus.ABERTA).isPresent();

        if (existeSessaoAberta) {
            throw new SessaoAindaAbertaException("Já existe uma sessão aberta");
        }

        if (pauta.getSessaoVotacao() != null && pauta.getSessaoVotacao().getStatus() == SessaoStatus.ENCERRADA) {
            throw new SessaoEncerradaEApuradaException("Sessao ja foi Apurada");

        }

        SessaoVotacao sessaoVotacao = SessaoVotacaoMapper.toEntity(dto, pauta);
        iniciarVotacao(sessaoVotacao);
        repository.save(sessaoVotacao);

        return SessaoVotacaoMapper.toDto(sessaoVotacao);
    }

    public void iniciarVotacao(SessaoVotacao sessaoVotacao) {

        sessaoVotacao.setDataInicio(LocalDateTime.now());

        if (sessaoVotacao.getDuracao() == null || sessaoVotacao.getDuracao() == 0) {
            sessaoVotacao.setDataFim(sessaoVotacao.getDataInicio().plusMinutes(1));
            sessaoVotacao.setStatus(SessaoStatus.ABERTA);
            return;
        }

        sessaoVotacao.setDataFim(
                sessaoVotacao.getDataInicio()
                        .plusMinutes(sessaoVotacao.getDuracao()));
        sessaoVotacao.setStatus(SessaoStatus.ABERTA);

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

    @Scheduled(fixedRate = 60000)
    public void encerrarSessaoAberta() {
        List<SessaoVotacao> sessoesAbertas = repository.findByStatus(SessaoStatus.ABERTA);

        for (SessaoVotacao sessao : sessoesAbertas) {
            if (LocalDateTime.now().isAfter(sessao.getDataFim())) {
                sessao.setStatus(SessaoStatus.ENCERRADA);
                repository.save(sessao);
            }
        }

    }
}
