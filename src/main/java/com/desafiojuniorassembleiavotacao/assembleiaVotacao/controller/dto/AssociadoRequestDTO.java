package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Schema(description = "Dados necessários para cadastro do associado")
public record AssociadoRequestDTO(

        @Schema(
                description = "Nome do novo associado",
                example = "Henrique"
        )
        @NotBlank(message = "Nome Obrigatorio")
        @Size(min = 3, max = 150, message = "Nome deve ter entre 3 e 150 caracteres")
        String nome,

        @Schema(
                description = "Cpf válido do novo associado",
                example = "699.005.350-06"
        )
        @NotBlank(message = "CPF obrigatorio")
        @CPF(message = "CPF inválido")
        String cpf
){
}
