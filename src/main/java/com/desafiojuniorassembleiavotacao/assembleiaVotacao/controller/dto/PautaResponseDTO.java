package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.ResultadoPauta;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Dados retornados de uma pauta")
public record PautaResponseDTO(
        @Schema(
                description = "Identificador único da pauta",
                example = "51fbab1b-f6ea-41b4-a65e-89eec5295e17"
        )
        UUID id,

        @Schema(
                description = "Nome da pauta",
                example = "Reforma do estatuto"
        )
        String nome,

        @Schema(
                description = "Total de votos sim",
                example = "10"
        )
        long totalSim,

        @Schema(
                description = "Total de votos não",
                example = "8"

        )
        long totalNao,

        @Schema(
                description = "Soma do total de votos sim e nao",
                example = "18"
        )
        long totalVotos,

        @Schema(
                description = "Resultado final da pauta",
                example = "APROVADA"
        )
        ResultadoPauta resultado

){
}
