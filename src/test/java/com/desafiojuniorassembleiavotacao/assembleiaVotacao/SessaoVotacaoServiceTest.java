package com.desafiojuniorassembleiavotacao.assembleiaVotacao;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroNaoEncontradoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.SessaoFechadaException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.SessaoVotacao;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.SessaoVotacaoRepository;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.PautaService;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.SessaoVotacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SessaoVotacaoServiceTest {

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private PautaService pautaService;

    @InjectMocks
    private SessaoVotacaoService sessaoVotacaoService;

    private SessaoVotacaoRequestDTO sessaoVotacaoRequestDTO;

    private SessaoVotacaoResponseDTO sessaoVotacaoResponseDTO;

    private SessaoVotacao sessaoVotacao;

    private Pauta pauta;

    @BeforeEach
    public void setup() {
        UUID pautaId = UUID.randomUUID();
        int id = Math.abs(new Random().nextInt());

        pautaService.obterPorId(pautaId);

        pauta = new Pauta();
        pauta.setId(pautaId);

        sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setId(id);
        sessaoVotacao.setDuracao(10L);

        sessaoVotacaoRequestDTO = new SessaoVotacaoRequestDTO(
                pautaId,
                10L
        );

        sessaoVotacaoResponseDTO = new SessaoVotacaoResponseDTO(
                sessaoVotacao.getId(),
                pauta.getId(),
                sessaoVotacao.getDataInicio(),
                sessaoVotacao.getDuracao(),
                sessaoVotacao.getDataFim()
        );
    }

    @Test
    void deveSalvarInicioSessaoComSucesso() {

        when(pautaService.buscarPautaVotacaoPorId(pauta.getId()))
                .thenReturn(pauta);

        when(sessaoVotacaoRepository.save(any(SessaoVotacao.class)))
                .thenReturn(sessaoVotacao);

        SessaoVotacaoResponseDTO resultado = sessaoVotacaoService.salvarInicioSessao(sessaoVotacaoRequestDTO);

        assertEquals(10L, resultado.duracao());

        verify(pautaService, times(1)).buscarPautaVotacaoPorId(pauta.getId());
        verify(sessaoVotacaoRepository, times(1)).save(any(SessaoVotacao.class));
    }

    @Test
    void deveValidarcaSessaoAberta() {

      Pauta pauta = new Pauta();
      SessaoVotacao sessaoVotacao = new SessaoVotacao();

      sessaoVotacao.setDuracao(10L);
      sessaoVotacao.setDataInicio(LocalDateTime.now().minusMinutes(1));
      sessaoVotacao.setDataFim(LocalDateTime.now().plusMinutes(5));

      pauta.setSessaoVotacao(sessaoVotacao);


      assertDoesNotThrow(() -> sessaoVotacaoService.verificaSessaoAberta(pauta));

    }

    @Test
    void deveLancarExcecaoQuandoPautaNaoPossuiSessaoVotacao() {

        Pauta pauta = new Pauta();

        assertThrows(SessaoFechadaException.class, () -> {
            sessaoVotacaoService.verificaSessaoAberta(pauta);
        });
    }

    @Test
    void deveLancarExcecaoQuandoSessaoVotacaoAindaNaoIniciou() {

        Pauta pauta = new Pauta();
        SessaoVotacao sessaoVotacao = new SessaoVotacao();

        sessaoVotacao.setDuracao(10L);
        sessaoVotacao.setDataInicio(LocalDateTime.now().minusMinutes(-5L));
        sessaoVotacao.setDataFim(sessaoVotacao.getDataInicio().plusMinutes(20L));

        pauta.setSessaoVotacao(sessaoVotacao);

        assertThrows(SessaoFechadaException.class, () -> {
            sessaoVotacaoService.verificaSessaoAberta(pauta);
        });
    }

    @Test
    void deveLancarExcecaoQuandoSessaoVotacaoJaEncerrada() {

        Pauta pauta = new Pauta();
        SessaoVotacao sessaoVotacao = new SessaoVotacao();

        sessaoVotacao.setDuracao(3L);
        sessaoVotacao.setDataInicio(LocalDateTime.now().minusMinutes(10L));
        sessaoVotacao.setDataFim(LocalDateTime.now().minusMinutes(5L));

        pauta.setSessaoVotacao(sessaoVotacao);

        assertThrows(SessaoFechadaException.class, () -> {
            sessaoVotacaoService.verificaSessaoAberta(pauta);
        });

    }

    @Test
    void deveDefinirDuracaoPadraoDeUmMinutoQuandoDuracaoNaoInformada(){

    }
}
