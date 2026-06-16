package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Retorna o dados da pauta e o tempo de duração da sessão de votação ")
public record SessaoVotacaoResponseDTO(

        @Schema(
                description = "Identificador único do inicio da sessão de votação vinculado ao identificador da pauta",
                example = "1"
        )
        int id,

        @Schema(
                description = "identificador único da pauta vinculada ao inicio de sessão de votação",
                example = "da2e3820-5227-4448-abf4-8054a814c6f4"
        )
        UUID pautaId,

        @Schema(
                description = "data e hora da abertura do sessão de votação",
                example = "15/06/2026 21:52:00"
        )
        LocalDateTime dataAbertura,

        @Schema(
                description = "o tempo de duração da sessão de votação em minutos",
                example = "10"
        )
        Long duracao,

        @Schema(
                description = "horário de ecerramento da sessão de votação",
                example = "15/06/2026 22:02:00"
        )
        LocalDateTime dataFechamento
)
{
}
