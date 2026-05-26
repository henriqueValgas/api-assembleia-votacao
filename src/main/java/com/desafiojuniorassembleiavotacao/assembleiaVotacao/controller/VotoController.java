package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoPorNomeAndCpfRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoPorNomeAndCpfResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votos")
public class VotoController implements GenericController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<VotoPorNomeAndCpfResponseDTO> salvarVoto(@Valid @RequestBody VotoDTO dto) {

        VotoPorNomeAndCpfResponseDTO response = votoService.salvarVoto(dto);

        var uri = gerarHeaderLocation(response.pautaId());

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/por-cpf")
    public ResponseEntity<VotoPorNomeAndCpfResponseDTO> salvarVotoPorNomeAndCpf(@Valid @RequestBody VotoPorNomeAndCpfRequestDTO dto) {

        VotoPorNomeAndCpfResponseDTO response = votoService.salvarVotoPorNomeAndCpf(dto);

        var uri = gerarHeaderLocation(response.pautaId());

        return ResponseEntity.created(uri).body(response);
    }
}
