package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.ResultadoPauta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Retorna os dados do resultado da votação")
public record ResultadoSessaoVotacaoResponseDTO(
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
                description = "total de votos não",
                example = "5"
        )
        long totalNao,

        @Schema(
                description = "soma total de votos",
                example = "15"
        )
        long totalVotos,
        @Schema(
                description = "Resultado da votação",
                example = "APROVADA"
        )
        ResultadoPauta resultado
)
{
}
