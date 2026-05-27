package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PautaRequestDTO(
        @NotBlank(message = "Nome da pauta obrigatorio")
        @Size(min = 3, max = 150,message = "pauta com no minimo 3 caracteres e no maximo 150 caracteres")
        String nome
){
}
