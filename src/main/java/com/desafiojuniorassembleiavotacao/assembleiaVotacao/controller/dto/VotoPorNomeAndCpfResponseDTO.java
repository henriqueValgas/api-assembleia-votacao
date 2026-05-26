package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.VotoEnum;

import java.util.UUID;

public record VotoPorNomeAndCpfResponseDTO(
        UUID pautaId,
        String pauta,
        UUID associadoId,
        String nome,
        String cpf,
        VotoEnum votoEnum
) {
}
