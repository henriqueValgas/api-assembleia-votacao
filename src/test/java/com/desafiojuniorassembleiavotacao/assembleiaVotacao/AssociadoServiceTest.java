package com.desafiojuniorassembleiavotacao.assembleiaVotacao;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroDuplicadoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroNaoEncontradoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.AssociadoRepository;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.AssociadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTest {

    @Mock
    private AssociadoRepository associadoRepository;

    @Mock
    private AssociadoResponseDTO associadoResponseDTO;

    @InjectMocks
    private AssociadoService associadoService;

    private Associado associado;
    private AssociadoRequestDTO associadoRequestDTO;


    @BeforeEach
    void setUp() {
        UUID uuid = UUID.randomUUID();
        associado = new Associado();
        associado.setId(uuid);
        associado.setNome("Henrique");
        associado.setCpf("12345678900");

        associadoRequestDTO = new AssociadoRequestDTO(
                "Henrique",
                "12345978900"
        );

        associadoResponseDTO = new AssociadoResponseDTO(
                uuid,
                "Henrique",
                "12345678900"
        );
    }

    @Test
    void deveCadastrarAssociadoComSucesso() {

        when(associadoRepository.existsByCpf(anyString()))
                .thenReturn(false);

        when(associadoRepository.save(any(Associado.class)))
                .thenReturn(associado);

        AssociadoResponseDTO resultado = associadoService.salvar(associadoRequestDTO);

        assertNotNull(resultado);
        assertEquals("Henrique", resultado.nome());

        verify(associadoRepository, times(1)).existsByCpf(anyString());

        verify(associadoRepository, times(1)).save(any(Associado.class));

    }

    @Test
    void deveLancarExcecaoQuandoCpfExistir() {

        when(associadoRepository.existsByCpf(anyString()))
                .thenReturn(true);

        assertThrows(RegistroDuplicadoException.class,
                () -> associadoService.salvar(associadoRequestDTO));

        verify(associadoRepository, never()).save(any(Associado.class));
    }

    @Test
    void deveBuscarAssociadoComIdComSucesso() {
        UUID uuid = associado.getId();

        when(associadoRepository.findById(uuid))
                .thenReturn(Optional.of(associado));

        Associado resultado = associadoService.buscarAssociado(uuid);

        assertNotNull(resultado);
        assertEquals(uuid, resultado.getId());
        assertEquals("Henrique", resultado.getNome());
        assertEquals("12345678900", resultado.getCpf());

        verify(associadoRepository, times(1)).findById(uuid);


    }

    @Test
    void deveLancarExcecaoQuandoAssociadoNaoEncontrado() {
        UUID uuid = UUID.randomUUID();

        when(associadoRepository.findById(uuid))
                .thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            associadoService.buscarAssociado(uuid);
        });

        verify(associadoRepository, times(1)).findById(uuid);
    }
}
