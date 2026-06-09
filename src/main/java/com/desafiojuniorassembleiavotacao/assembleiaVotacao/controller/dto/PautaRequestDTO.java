package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para cadastro de uma pauta")
public record PautaRequestDTO(

        @Schema(
                description = "Nome da pauta que será submetida à votação",
                example = "Reforma do Estatudo"
        )
        @NotBlank(message = "Nome da pauta obrigatorio")
        @Size(min = 3, max = 150, message = "pauta com no minimo 3 caracteres e no maximo 150 caracteres")
        String nome
){
}
