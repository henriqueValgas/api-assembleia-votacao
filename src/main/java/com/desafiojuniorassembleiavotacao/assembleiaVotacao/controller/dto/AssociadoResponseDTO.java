package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import java.util.UUID;

public record AssociadoResponseDTO(
        UUID id,
        String nome,
        String cpf
){
}
