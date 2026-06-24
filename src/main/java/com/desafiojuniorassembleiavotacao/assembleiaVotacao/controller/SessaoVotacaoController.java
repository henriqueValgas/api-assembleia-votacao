package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.SessaoVotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessao-votacao")
@Tag(
        name = "Abrir sessão de votação",
        description = "Operações relacionadas à abertura de sessões de votação e definição do tempo de duração"
)
public class SessaoVotacaoController implements GenericController{

    private final SessaoVotacaoService sessaoVotacaoService;


    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }


    @PostMapping
    @Operation(
            summary = "Iniciar sessão de Votação",
            description = "Inicia uma sessão de votação associada a uma pauta, informando o tempo de duração"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sessão iniciada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada"),
            @ApiResponse(responseCode = "409", description = "Já existe uma sessão aberta ou Pauta ja foi encerrada e apurada")
    })
    public ResponseEntity<SessaoVotacaoResponseDTO> abrirSessao(@Valid @RequestBody SessaoVotacaoRequestDTO dto) {

        SessaoVotacaoResponseDTO response = sessaoVotacaoService.salvarInicioSessao(dto);
        var uri = gerarHeaderLocation(response.id());

        return ResponseEntity.created(uri).body(response);
    }
}
