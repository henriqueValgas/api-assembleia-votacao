package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(
        description = "Dados necessários para retornar o resultado da votação"
)
public record ResultadoSessaoVotacaoRequestDTO(

        @Schema(
                description = "identificador da pauta de votação",
                example = "da2e3820-5227-4448-abf4-8054a814c6f4"
        )
        UUID pautaId
)
{
}
