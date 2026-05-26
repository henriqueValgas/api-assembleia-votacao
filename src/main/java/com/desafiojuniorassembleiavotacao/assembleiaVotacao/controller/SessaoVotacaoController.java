package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.SessaoVotacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessao-votacao")
public class SessaoVotacaoController implements GenericController{

    private final SessaoVotacaoService sessaoVotacaoService;


    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @PostMapping
    public ResponseEntity<SessaoVotacaoResponseDTO> sessaoVotacao(@RequestBody SessaoVotacaoRequestDTO dto) {

        SessaoVotacaoResponseDTO response = sessaoVotacaoService.salvarInicioSessao(dto);
        var uri = gerarHeaderLocation(response.id());

        return ResponseEntity.created(uri).body(response);
    }
}
