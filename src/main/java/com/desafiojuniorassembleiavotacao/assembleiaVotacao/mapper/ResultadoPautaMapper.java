package com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ResultadoSessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ResultadoSessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;

public class ResultadoPautaMapper {
    public static Pauta toEntity(ResultadoSessaoVotacaoRequestDTO dto) {
        Pauta pauta = new Pauta();
        pauta.setId(dto.id());

        return pauta;
    }

    public static ResultadoSessaoVotacaoResponseDTO toDTO(Pauta entity){
        return new ResultadoSessaoVotacaoResponseDTO(
                entity.getNome(),
                entity.getTotalSim(),
                entity.getTotalNao(),
                entity.getTotalVotos(),
                entity.getResultado()
        );
    }

}
