package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ResultadoSessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.ResultadoVotacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/resultado")
public class ResultadoVotacaoController implements GenericController {


    private final ResultadoVotacaoService service;

    public ResultadoVotacaoController(ResultadoVotacaoService resultadoVotacaoService) {
        this.service = resultadoVotacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoSessaoVotacaoResponseDTO> resultadoSessaoVotacao(@PathVariable UUID id) {

        ResultadoSessaoVotacaoResponseDTO response = service.resultadoPauta(id);

        return ResponseEntity.ok(response);
    }
}
