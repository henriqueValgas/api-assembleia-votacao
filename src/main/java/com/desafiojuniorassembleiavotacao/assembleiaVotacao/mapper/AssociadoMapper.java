package com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;


public class AssociadoMapper {

    public static Associado toEntity(AssociadoRequestDTO dto) {
        Associado associado = new Associado();

        associado.setNome(dto.nome());
        associado.setCpf(dto.cpf());

        return associado;
    }

    public static void updateAssociado(AssociadoRequestDTO dto, Associado entity) {
        entity.setNome(dto.nome());
        entity.setCpf(dto.cpf());
    }

    public static AssociadoResponseDTO toDto(Associado associado) {
        return new AssociadoResponseDTO(
                associado.getId(),
                associado.getNome(),
                associado.getCpf()
        );
    }
}
