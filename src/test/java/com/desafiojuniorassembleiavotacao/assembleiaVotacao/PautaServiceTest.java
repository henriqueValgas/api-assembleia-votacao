package com.desafiojuniorassembleiavotacao.assembleiaVotacao;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroDuplicadoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroNaoEncontradoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.PautaRepository;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.PautaDomainService;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.PautaService;
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
public class PautaServiceTest {
    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaDomainService pautaDomainService;

    @InjectMocks
    private PautaService pautaService;

    private PautaRequestDTO pautaRequestDTO;

    private PautaResponseDTO pautaResponseDTO;

    private Pauta pauta;

    @BeforeEach
    public void setup() {
        UUID uuid = UUID.randomUUID();
        pauta = new Pauta();
        pauta.setId(uuid);
        pauta.setNome("Pauta teste");

        pautaRequestDTO = new PautaRequestDTO(
                "Pauta teste"
        );

        pautaResponseDTO = new PautaResponseDTO(
                uuid,
                "Pauta teste",
                0,
                0,
                0,
                null

        );
    }

    @Test
    void deveCadastrarPautaComSucesso() {

        when(pautaRepository.existsByNome(anyString()))
                .thenReturn(false);

        when(pautaRepository.save(any(Pauta.class)))
                .thenReturn(pauta);

        PautaResponseDTO resultado = pautaService.salvar(pautaRequestDTO);

        assertNotNull(resultado);
        assertEquals("Pauta teste", resultado.nome());

        verify(pautaRepository, times(1)).existsByNome(anyString());

        verify(pautaRepository, times(1)).save(any());

    }

    @Test
    void deveLancarExcecaoRegistroDuplicado() {

        when(pautaRepository.existsByNome(anyString()))
                .thenReturn(true);

        assertThrows(RegistroDuplicadoException.class, () -> {
            pautaService.salvar(pautaRequestDTO);
        });

        verify(pautaRepository, never()).save(any(Pauta.class));

    }

    @Test
    void deveBuscarPautaPorIdComSucesso() {
        UUID uuid = pauta.getId();

        when(pautaRepository.findById(uuid))
                .thenReturn(Optional.of(pauta));

        Pauta resultado = pautaService.buscarPautaVotacaoPorId(uuid);

        assertNotNull(resultado);
        assertEquals(uuid, resultado.getId());
        assertEquals("Pauta teste", resultado.getNome());

        verify(pautaRepository, times(1)).findById(uuid);

    }

    @Test
    void deveLancarExcecaoPautaNaoEncontrada() {
        UUID uuid = UUID.randomUUID();

        when(pautaRepository.findById(uuid))
                .thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class,() ->{
            pautaService.buscarPautaVotacaoPorId(uuid);
        });
        verify(pautaRepository, times(1)).findById(uuid);
    }

}
