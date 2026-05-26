package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pautas")
public class PautaController implements GenericController {

    private final PautaService service;

    public PautaController(PautaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PautaResponseDTO> salvar(
            @Valid @RequestBody PautaRequestDTO dto) {

        PautaResponseDTO response = service.salvar(dto);

        var uri = gerarHeaderLocation(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PautaResponseDTO>> listaPautas(
            @RequestParam(name = "nome", required = false) String nome) {

        return ResponseEntity.ok(service.listaPauta(nome));
    }

    @GetMapping("{id}")
    public ResponseEntity<PautaResponseDTO> obterPorId(
            @PathVariable(name = "id") UUID id) {

        return ResponseEntity.ok(service.obterPorId(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(
            @PathVariable(name = "id") UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
