package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.AssociadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/associados")
public class AssociadoController implements GenericController {

    private final AssociadoService service;

    public AssociadoController(AssociadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AssociadoResponseDTO> salvar(
            @Valid @RequestBody AssociadoRequestDTO dto){

        AssociadoResponseDTO response = service.salvar(dto);

        var uri = gerarHeaderLocation(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AssociadoResponseDTO>> buscarPorNome(
            @RequestParam(value = "nome", required = false) String nome) {

        return ResponseEntity.ok(service.listarTodos(nome));
    }

    @GetMapping("{id}")
    public ResponseEntity<AssociadoResponseDTO> obterPorId(
            @PathVariable("id") UUID id) {

        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<AssociadoResponseDTO> atualizar(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AssociadoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}


