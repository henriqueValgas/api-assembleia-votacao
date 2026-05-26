package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.ResultadoPauta;

import java.util.UUID;

public record PautaResponseDTO(
        UUID id,
        String nome,
        long totalSim,
        long totalNao,
        long totalVotos,
        ResultadoPauta resultado

){
}
