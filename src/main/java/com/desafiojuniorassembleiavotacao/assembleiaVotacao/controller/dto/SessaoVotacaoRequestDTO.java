package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@Schema(
        description = "Dados necessários para iniciar a sessão de votação"
)
public record SessaoVotacaoRequestDTO(

        @Schema(
                description = "seleciona a pauta através do identificador único",
                example = "da2e3820-5227-4448-abf4-8054a814c6f4"

        )
        @NotNull
        UUID pautaId,

        @Schema(
                description = "informa o tempo de duração em minutos da sessão de votação",
                example = "10"
        )
        @NotNull(message = "Informe a duração")
        @Positive(message = "A duraçao deve ser maior do que zero")
        Long duracao
)
{
}
