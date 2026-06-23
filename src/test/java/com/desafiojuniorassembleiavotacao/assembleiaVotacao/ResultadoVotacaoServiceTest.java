package com.desafiojuniorassembleiavotacao.assembleiaVotacao;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ResultadoSessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ResultadoSessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.SessaoAindaAbertaException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.ResultadoPauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.SessaoVotacao;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.PautaService;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.ResultadoVotacaoService;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResultadoVotacaoServiceTest {

    @Mock
    private PautaService pautaService;

    @Mock
    private VotoService votoService;

    @InjectMocks
    private ResultadoVotacaoService resultadoVotacaoService;

    private UUID uuid;

    private Pauta pauta;
    private SessaoVotacao sessaoVotacao;

    @BeforeEach
    public void setup() {

        uuid = UUID.randomUUID();

        pauta = new Pauta();
        sessaoVotacao = new SessaoVotacao();

        pauta.setSessaoVotacao(sessaoVotacao);

        resultadoVotacaoService = new ResultadoVotacaoService(pautaService, votoService);

    }

    @Test
    void deveRetornarResultadoVotacaoQuandoSessaoEncerrada() {

        sessaoVotacao.setDataFim(LocalDateTime.now().minusMinutes(1));

        when(pautaService.buscarPautaVotacaoPorId(uuid))
                .thenReturn(pauta);

        when(votoService.contaSim(pauta))
                .thenReturn(10L);

        when(votoService.contaNao(pauta))
                .thenReturn(5L);

        when(votoService.vencedor(10L,5L))
                .thenReturn(ResultadoPauta.APROVADA);

        ResultadoSessaoVotacaoResponseDTO resultado = resultadoVotacaoService.resultadoPauta(uuid);

        assertEquals(10L, pauta.getTotalSim());
        assertEquals(5L, pauta.getTotalNao());
        assertEquals(15L, pauta.getTotalVotos());
        assertEquals(ResultadoPauta.APROVADA, pauta.getResultado());

        verify(pautaService).salvar(pauta);

    }

    @Test
    void deveLancarExcecaoQuandoSessaoAindaAberta() {

        sessaoVotacao.setDataFim(LocalDateTime.now().plusDays(1));

        when(pautaService.buscarPautaVotacaoPorId(uuid))
                .thenReturn(pauta);

        assertThrows(SessaoAindaAbertaException.class, () -> resultadoVotacaoService.resultadoPauta(uuid));
        verify(pautaService, never()).salvar(any(Pauta.class));
    }
}
