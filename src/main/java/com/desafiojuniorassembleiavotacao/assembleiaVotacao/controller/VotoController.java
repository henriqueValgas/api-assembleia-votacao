package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoPorNomeAndCpfRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoPorNomeAndCpfResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.VotoService;
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
@RequestMapping("/api/votos")
@Tag(
        name = "Votos",
        description = "Operação para salvar votos dos associados "
)
public class VotoController implements GenericController {

    private final VotoService votoService;


    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    @Operation(
            summary = "Registrar voto por IDs",
            description = "Salva o voto do associado na pauta vigente," +
                    " por Identificador do associado e identificador da pauta"

    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Voto efetuado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Associado ou pauta não encontrado"),
            @ApiResponse(responseCode = "409", description = "Associado já votou, pauta não está ativa ou votação encerrada")
    })
    public ResponseEntity<VotoPorNomeAndCpfResponseDTO> salvarVoto(@Valid @RequestBody VotoDTO dto) {

        VotoPorNomeAndCpfResponseDTO response = votoService.salvarVoto(dto);

        var uri = gerarHeaderLocation(response.pautaId());

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/por-cpf")
    @Operation(
            summary = "Registrar voto por CPF e nome da pauta",
            description = "Salva o voto do associado na pauta vigente," +
                    "por cpf do associado e pelo nome da pauta"

    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Voto efetuado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Associado ou pauta não encontrado"),
            @ApiResponse(responseCode = "409", description = "Associado já votou, pauta não está ativa ou votação encerrada")
    })
    public ResponseEntity<VotoPorNomeAndCpfResponseDTO> salvarVotoPorNomeAndCpf(@Valid @RequestBody VotoPorNomeAndCpfRequestDTO dto) {

        VotoPorNomeAndCpfResponseDTO response = votoService.salvarVotoPorNomeAndCpf(dto);

        var uri = gerarHeaderLocation(response.pautaId());

        return ResponseEntity.created(uri).body(response);
    }
}
