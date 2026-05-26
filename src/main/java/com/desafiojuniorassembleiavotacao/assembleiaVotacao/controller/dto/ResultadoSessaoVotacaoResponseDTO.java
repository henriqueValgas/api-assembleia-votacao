package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.ResultadoPauta;

public record ResultadoSessaoVotacaoResponseDTO(
        String nome,
        long totalSim,
        long totalNao,
        long totalVotos,
        ResultadoPauta resultado
){
}
