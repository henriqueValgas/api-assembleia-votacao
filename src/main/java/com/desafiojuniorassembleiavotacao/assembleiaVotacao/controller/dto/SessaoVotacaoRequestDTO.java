package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record SessaoVotacaoRequestDTO(

        @NotNull
        UUID pautaId,

        @NotNull(message = "Informe a duração")
        @Positive(message = "A duraçao deve ser maior do que zero")
        Long duracao
) {
}
