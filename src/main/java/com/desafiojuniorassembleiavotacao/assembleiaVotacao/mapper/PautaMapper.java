package com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;

public class PautaMapper {

    public static Pauta toEntity(PautaRequestDTO dto) {
        Pauta pauta = new Pauta();
        pauta.setNome(dto.nome());
        return pauta;
    }

    public static PautaResponseDTO toDTO(Pauta entity) {
        return new PautaResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTotalSim(),
                entity.getTotalNao(),
                entity.getTotalVotos(),
                entity.getResultado()
        );
    }
}
