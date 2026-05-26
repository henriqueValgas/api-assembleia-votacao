package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record AssociadoRequestDTO(
        @NotBlank(message = "Nome Obrigatorio")
        @Size(min = 3, max = 150, message = "Nome deve ter entre 3 e 150 caracteres")
        String nome,
        @NotBlank(message = "CPF obrigatorio")
        @CPF(message = "CPF inválido")
        String cpf
){
}
