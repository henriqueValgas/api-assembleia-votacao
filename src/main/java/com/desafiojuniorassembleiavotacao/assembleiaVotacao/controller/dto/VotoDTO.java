package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.VotoEnum;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VotoDTO(

        @NotNull(message = "Obrigatorio informar o id da pauta")
        UUID pautaId,

        @NotNull(message = "obrigatorio informar o id do associado")
        UUID associadoId,

        @NotNull(message = "opcao é obrigatoria")
        VotoEnum opcao
) {
}
