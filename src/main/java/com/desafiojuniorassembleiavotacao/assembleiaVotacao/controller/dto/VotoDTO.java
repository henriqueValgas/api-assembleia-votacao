package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.VotoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Dados nescessários para salvar o voto," +
        " por identificador do usuário e identificador da pauta")
public record VotoDTO(

        @Schema(
                description = "Identificador da pauta",
                example = "ce1d8700-e642-48f5-8a98-41ba329c37c3"
        )
        @NotNull(message = "Obrigatorio informar o id da pauta")
        UUID pautaId,


        @Schema(
                description = "Identificador do associado",
                example =  "fb21d470-dbf6-42a3-b9b9-19d8aae28c83"
        )
        @NotNull(message = "obrigatorio informar o id do associado")
        UUID associadoId,

        @Schema(
                description = "Opção do voto do associado",
                example = "SIM"
        )
        @NotNull(message = "opcao é obrigatoria")
        VotoEnum opcao
) {
}
