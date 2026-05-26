package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SessaoVotacaoRequestDTO(
        @NotNull
        UUID pautaId,
        @NotBlank(message = "Coloque a quantidade de tempo para duracao da pauta")
        long duracao
) {
}
