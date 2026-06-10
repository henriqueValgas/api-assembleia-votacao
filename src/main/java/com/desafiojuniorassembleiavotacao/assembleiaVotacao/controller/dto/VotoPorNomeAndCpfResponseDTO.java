package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.VotoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Retorna os dados do voto")
public record VotoPorNomeAndCpfResponseDTO(
        @Schema(
                description = "Numero identificador da pauta",
                example = "16c77500-65da-44da-bc7c-ea8960841133"
        )
        UUID pautaId,

        @Schema(
                description = "Nome da pauta",
                example = "Reforma do ginasio"
        )
        String pauta,

        @Schema(
                description = "Identificador do associado",
                example = "580b77be-07a5-4d80-8ee5-736c71b8e20c"
        )
        UUID associadoId,

        @Schema(
                description = "Nome do associado",
                example = "Henrique"
        )
        String nome,

        @Schema(
                description = "Numero do cpf do associado",
                example = "672.250.190-66"
        )
        String cpf,

        @Schema(
                description = "Opção escolhida pelo associado",
                example = "SIM"
        )
        VotoEnum votoEnum
)
{
}
