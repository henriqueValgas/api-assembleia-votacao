package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.VotoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados nescessários para salvar o voto," +
        " por cpf do usuário e pelo nome da pauta")
public record VotoPorNomeAndCpfRequestDTO(

        @Schema(
                description = "Nome da pauta",
                example = "Alteração do estatuto dos associados"
        )
        @NotBlank
        String nomePauta,

        @Schema(
                description = "Cpf do associado",
                example = "996.734.050-93"
        )
        @NotBlank
        String cpf,

        @Schema(
                description = "Opção do voto do associado",
                example = "SIM"
        )
        @NotNull
        VotoEnum votoEnum
)
{
}
