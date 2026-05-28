package com.desafiojuniorassembleiavotacao.assembleiaVotacao;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.SessaoVotacaoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.SessaoVotacao;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.PautaRepository;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.SessaoVotacaoRepository;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.PautaService;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.SessaoVotacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SessaoDeVotacaoServiceTest {

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

        Pauta pauta = new Pauta();
        pauta.setId(pautaId);

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
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
    void deveSalvarSessaoComSucesso() {
        LocalDateTime dataAbertura = LocalDateTime.now();
    }

    @Test
    void deveValidarSessaoAberta() {

    }

    @Test
    void deveLancarExcecaoQuandoSessaoFechada(){
        
    }
}
