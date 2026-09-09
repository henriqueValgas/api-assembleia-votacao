package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ResultadoSessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ResultadoSessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.ResultadoVotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/resultados")
@Tag(
        name = "Resultado da votação",
        description = "Permiti consultar o resultado final de uma votação encerrada"
)
public class ResultadoVotacaoController implements GenericController {


    private final ResultadoVotacaoService service;

    public ResultadoVotacaoController(ResultadoVotacaoService resultadoVotacaoService) {
        this.service = resultadoVotacaoService;
    }

    @Operation(
            summary = "Consultar o resultado da votação",
            description = "Retorna o resultado consolidado da votação buscando a pauta por id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado da votação retornado com sucesso"),
            @ApiResponse(responseCode = "400", description = "uuid informado é inválido"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada"),
            @ApiResponse(responseCode = "409", description = "Pauta não iniciada ou ainda em aberto")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResultadoSessaoVotacaoResponseDTO> resultadoSessaoVotacao(@PathVariable UUID id) {

        ResultadoSessaoVotacaoResponseDTO response = service.resultadoPauta(id);

        return ResponseEntity.ok(response);
    }
}
