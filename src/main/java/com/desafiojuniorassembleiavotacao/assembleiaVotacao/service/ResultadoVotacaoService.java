package com.desafiojuniorassembleiavotacao.assembleiaVotacao.service;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ResultadoSessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ResultadoSessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.SessaoAindaAbertaException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper.ResultadoPautaMapper;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ResultadoVotacaoService {

    PautaService pautaService;
    VotoService votoService;

    public ResultadoVotacaoService(PautaService pautaService, VotoService votoService) {
        this.pautaService = pautaService;
        this.votoService = votoService;
    }

    @Transactional
    public ResultadoSessaoVotacaoResponseDTO resultadoPauta(ResultadoSessaoVotacaoRequestDTO dto) {

        Pauta pauta = pautaService.buscarPautaVotacaoPorId(dto.pautaId());

        long votoSim = votoService.contaSim(pauta);
        long votoNao = votoService.contaNao(pauta);


        if (LocalDateTime.now().isAfter(pauta.getSessaoVotacao().getDataFim())) {
            pauta.setTotalSim(votoSim);
            pauta.setTotalNao(votoNao);
            pauta.setTotalVotos(votoSim + votoNao);
            pauta.setResultado(votoService.vencedor(votoSim, votoNao));
        } else {
            throw new SessaoAindaAbertaException("Sessao de votacao ainda esta aberta");
        }

        pautaService.salvar(pauta);

        return ResultadoPautaMapper.toDTO(pauta);
    }
}
