package com.desafiojuniorassembleiavotacao.assembleiaVotacao;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroDuplicadoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroNaoEncontradoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.SessaoFechadaException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.ResultadoPauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.VotoEnum;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.VotoRepository;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private PautaService pautaService;

    @Mock
    private AssociadoService associadoService;

    @Mock
    private SessaoVotacaoService sessaoVotacaoService;

    @Mock
    private PautaDomainService pautaDomainService;

    @InjectMocks
    private VotoService votoService;

    @Test
    void deveLancarExceptionQuandoPautaNaoExiste() {
        UUID pautaId = UUID.randomUUID();
        UUID associadoId = UUID.randomUUID();

        VotoDTO dto = new VotoDTO(pautaId, associadoId, VotoEnum.SIM);

        when(pautaService.buscarPautaVotacaoPorId(pautaId))
                .thenThrow(new RegistroNaoEncontradoException("Pauta nao encontrada"));

        assertThrows(RegistroNaoEncontradoException.class,
                () -> votoService.salvarVoto(dto));

        verify(votoRepository, never()).save(any());
    }

    @Test
    void deveSalvarVotoQuandoPautaExiste() {
        UUID pautaId = UUID.randomUUID();
        UUID associadoId = UUID.randomUUID();

        VotoDTO dto = new VotoDTO(pautaId, associadoId, VotoEnum.SIM);

        Pauta pauta = new Pauta();

        Associado associado = new Associado();

        when(pautaService.buscarPautaVotacaoPorId(pautaId))
                .thenReturn(pauta);

        when(associadoService.buscarAssociado(associadoId))
                .thenReturn(associado);

        doNothing().when(sessaoVotacaoService).verificaSessaoAberta(any(Pauta.class));

        doNothing().when(pautaDomainService).validarDuplicidade(anyBoolean());

        votoService.salvarVoto(dto);

        verify(votoRepository).save(any());
    }

    @Test
    void deveLancarExceptionQuandoAssociadoNaoExiste() {
        UUID pautaId = UUID.randomUUID();
        UUID associadoId = UUID.randomUUID();

        VotoDTO dto = new VotoDTO(pautaId, associadoId, VotoEnum.SIM);

        when(associadoService.buscarAssociado(associadoId))
                .thenThrow(new RegistroNaoEncontradoException("Associado nao encontrado"));

        assertThrows(RegistroNaoEncontradoException.class, () -> votoService.salvarVoto(dto));
        verify(votoRepository, never()).save(any());

    }

    @Test
    void deveLancarExceptionQuandoSessaoEstiverFechada() {
        UUID pautaId = UUID.randomUUID();
        UUID associadoId = UUID.randomUUID();

        VotoDTO dto = new VotoDTO(pautaId, associadoId, VotoEnum.SIM);

        Pauta pauta = new Pauta();

        Associado associado = new Associado();

        when(pautaService.buscarPautaVotacaoPorId(pautaId))
                .thenReturn(pauta);

        when(associadoService.buscarAssociado(associadoId))
                .thenReturn(associado);

        doThrow(new SessaoFechadaException("Sessao encerrada"))
                .when(sessaoVotacaoService)
                .verificaSessaoAberta(any(Pauta.class));

        assertThrows(SessaoFechadaException.class,
                () -> votoService.salvarVoto(dto));

        verify(votoRepository, never()).save(any());
    }

    @Test
    void deveLancarExceptionsQuandoAssociadoJaVotou() {
        UUID pautaId = UUID.randomUUID();
        UUID associadoId = UUID.randomUUID();

        VotoDTO dto = new VotoDTO(pautaId, associadoId, VotoEnum.SIM);

        Pauta pauta = new Pauta();

        Associado associado = new Associado();

        when(pautaService.buscarPautaVotacaoPorId(pautaId))
                .thenReturn(pauta);

        when(associadoService.buscarAssociado(associadoId))
                .thenReturn(associado);

        when(votoRepository.existsByPautaAndAssociado(pauta, associado))
                .thenReturn(true);

        doThrow(new RegistroDuplicadoException("Associado ja votou"))
                .when(pautaDomainService).validarDuplicidade(true);

        assertThrows(RegistroDuplicadoException.class, () -> votoService.salvarVoto(dto));

        verify(votoRepository, never()).save(any());

    }

    @Test
    void deveRetornarAprovadoQuandoSimForMaior() {
        ResultadoPauta resultado = votoService.vencedor(10, 5);

        assertEquals(ResultadoPauta.APROVADA, resultado);
    }

    @Test
    void deveRetornarReprovadaQuandoNaoForMaior() {
        ResultadoPauta resultado = votoService.vencedor(5, 10);

        assertEquals(ResultadoPauta.REPROVADA, resultado);
    }

    @Test
    void deveRetornarEmpateQuandoForemIguais() {
        ResultadoPauta resultado = votoService.vencedor(5, 5);

        assertEquals(ResultadoPauta.EMPATE, resultado);
    }

    @Test
    void deveRetornarTotalVotosSim() {
        Pauta pauta = new Pauta();
        UUID pautaId = UUID.randomUUID();
        pauta.setId(pautaId);

        when(votoRepository.contaVotosSim(pautaId)).thenReturn(10L);

        long result = votoService.contaSim(pauta);

        assertEquals(10L, result);

        verify(votoRepository).contaVotosSim(pautaId);
    }

    @Test
    void deveRetornarTotalVotosNao() {
        Pauta pauta = new Pauta();
        UUID pautaId = UUID.randomUUID();
        pauta.setId(pautaId);

        when(votoRepository.contaVotosNao(pautaId)).thenReturn(8L);

        long result = votoService.contaNao(pauta);

        assertEquals(8L, result);

        verify(votoRepository).contaVotosNao(pautaId);

    }
}
