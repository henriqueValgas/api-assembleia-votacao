package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pautas")
@Tag(
        name = "Pautas",
        description = "Operações relacionadas ao gerenciamento de pautas de votação"
)
public class PautaController implements GenericController {

    private final PautaService service;

    public PautaController(PautaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar pauta",
            description = "Cria uma nova pauta votação"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pauta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Pauta já cadastrada")
    })
    public ResponseEntity<PautaResponseDTO> salvar(
            @Valid @RequestBody PautaRequestDTO dto)
    {

        PautaResponseDTO response = service.salvar(dto);

        var uri = gerarHeaderLocation(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar pautas",
            description = "Lista todas as pautas cadastradas ou filtra pelo nome informado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    })
    public ResponseEntity<List<PautaResponseDTO>> listaPautas(
            @RequestParam(name = "nome", required = false) String nome)
    {

        return ResponseEntity.ok(service.listaPauta(nome));
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Consultar pauta por ID",
            description = "Retorna os dados de uma pauta a partir do Id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pauta encontrada"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada")
    })
    public ResponseEntity<PautaResponseDTO> obterPorId(
            @PathVariable(name = "id") UUID id)
    {

        return ResponseEntity.ok(service.obterPorId(id));
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Excluir pauta",
            description = "Remove uma pauta cadastrada pelo identificador informado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pauta removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada"),
            @ApiResponse(responseCode = "409", description = "Pauta já possui votos")
    })
    public ResponseEntity<Void> deletarPorId(
            @PathVariable(name = "id") UUID id)
    {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
