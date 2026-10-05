package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank
        String cpf,

        @NotBlank
        String senha
) {
}
