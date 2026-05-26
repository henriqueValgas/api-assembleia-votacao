package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.VotoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VotoPorNomeAndCpfRequestDTO(
        @NotBlank String nomePauta,
        @NotBlank String cpf,
        @NotNull VotoEnum votoEnum
){
}
