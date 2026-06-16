package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.AssociadoService;
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
@RequestMapping("/associados")
@Tag(
        name = "Associados",
        description = "Operações para cadastras, listar, buscar, atualizar e deletar novos associados"
)
public class AssociadoController implements GenericController {

    private final AssociadoService service;

    public AssociadoController(AssociadoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar associado",
            description = "Cadastra um novo associado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Usuário ja cadastrado")
    })
    public ResponseEntity<AssociadoResponseDTO> salvar(
            @Valid @RequestBody AssociadoRequestDTO dto)
    {

        AssociadoResponseDTO response = service.salvar(dto);

        var uri = gerarHeaderLocation(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar associados ou buscar por nomes",
            description = "Retorna os dados do associado caso encontre o nome," +
                    " caso campo esteja vazio retorna lista de associados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Associado encontrado"),
            @ApiResponse(responseCode = "404", description = "Nenhum associado encontrado")
    })
    public ResponseEntity<List<AssociadoResponseDTO>> buscarPorNome(
            @RequestParam(value = "nome", required = false) String nome)
    {

        return ResponseEntity.ok(service.listarTodos(nome));
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Buscar associado pelo identificador",
            description = "Retorna os dados do associado caso encontre o identificador"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Associado encontrado"),
            @ApiResponse(responseCode = "404", description = "Nenhum associado encontrado")
    })
    public ResponseEntity<AssociadoResponseDTO> obterPorId(
            @PathVariable("id") UUID id)
    {

        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualiza o dados do associado",
            description = "busca o associado pelo identificador" +
                    " e atualiza os dados cadastrais"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Associado atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Associado não encontrado")

    })
    public ResponseEntity<AssociadoResponseDTO> atualizar(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AssociadoRequestDTO dto)
    {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Deleta associado",
            description = "Deleta associado pelo identificador"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Associado excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Associado não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}


