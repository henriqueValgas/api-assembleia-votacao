package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import java.time.LocalDateTime;

public record ErroResponse(
        LocalDateTime timestamp,
        int status,
        String erro,
        Object mensagem,
        String path
){
}
