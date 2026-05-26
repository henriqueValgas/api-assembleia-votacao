package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessaoVotacaoResponseDTO(
        int id,
        UUID pautaId,
        LocalDateTime dataAbertura,
        long duracao,
        LocalDateTime dataFechamento
){
}
