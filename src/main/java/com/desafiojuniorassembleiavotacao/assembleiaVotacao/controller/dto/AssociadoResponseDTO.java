package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Retorna os dados do associado cadastrado")
public record AssociadoResponseDTO(
        @Schema(
                description = "Identificador unico do associado",
                example = "b74e326f-f6b4-4b92-b89f-9f17940257d8"
        )
        UUID id,

        @Schema(
                description = "Nome do associado",
                example = "Henrique"
        )
        String nome,

        @Schema(
                description = "cpf do novo associado",
                example = "699.005.350-06"
        )
        String cpf
){
}
