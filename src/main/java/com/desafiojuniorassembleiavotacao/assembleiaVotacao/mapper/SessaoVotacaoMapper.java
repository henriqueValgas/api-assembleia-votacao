package com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.SessaoVotacao;

public class SessaoVotacaoMapper {

    public static SessaoVotacao toEntity(SessaoVotacaoRequestDTO dto, Pauta pauta) {
        SessaoVotacao entity = new SessaoVotacao();

        entity.setPauta(pauta);
        entity.setDuracao(dto.duracao());

        return entity;
    }

    public static SessaoVotacaoResponseDTO toDto(SessaoVotacao sessaoVotacao) {

        return new SessaoVotacaoResponseDTO(
                sessaoVotacao.getId(),
                sessaoVotacao.getPauta().getId(),
                sessaoVotacao.getDataInicio(),
                sessaoVotacao.getDuracao(),
                sessaoVotacao.getDataFim()
        );
    }
}
