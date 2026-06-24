package com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.common;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.ErroResponse;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> tratarErroGenerico(
            Exception ex, HttpServletRequest request)
    {
        return criarRespostaErro(HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro Interno",
                "Ocorreu um erro inesperado"
                , request);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<ErroResponse> tratarRegistroDuplicado(
            RegistroDuplicadoException ex, HttpServletRequest request)
    {

        return criarRespostaErro(HttpStatus.CONFLICT,
                "Registro Duplicado",
                ex.getMessage(),
                request);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarErroRegistroNaoEncontrado(
            RegistroNaoEncontradoException ex, HttpServletRequest request)
    {

        return criarRespostaErro(HttpStatus.NOT_FOUND,
                "Registro não encontrado",
                ex.getMessage(),
                request);
    }

    @ExceptionHandler(SessaoFechadaException.class)
    public ResponseEntity<ErroResponse> tratarErroVotacaoEncerrada(
            SessaoFechadaException ex, HttpServletRequest request)
    {

        return criarRespostaErro(HttpStatus.CONFLICT,
                "Votação encerrada",
                ex.getMessage(),
                request);
    }

    @ExceptionHandler(SessaoAindaAbertaException.class)
    public ResponseEntity<ErroResponse> tratarErroVotacaoAberta(
            SessaoAindaAbertaException ex, HttpServletRequest request
    )
    {
        return criarRespostaErro(HttpStatus.CONFLICT,
                "Votação ainda aberta,",
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(SessaoEncerradaEApuradaException.class)
    public ResponseEntity<ErroResponse> tratarErroVotacaoEncerradaEApurada(
            SessaoEncerradaEApuradaException ex, HttpServletRequest request
    ){
        return criarRespostaErro(HttpStatus.CONFLICT,
                "Pauta ja encerrada e apurada",
                ex.getMessage(),
                request);
    }

    @ExceptionHandler(AssociadoJaVotouException.class)
    public ResponseEntity<ErroResponse> tratarVotoDuplicado(
            AssociadoJaVotouException ex, HttpServletRequest request)
    {

        return criarRespostaErro(HttpStatus.CONFLICT,
                "Usuario ja votou",
                ex.getMessage(),
                request);
    }

    @ExceptionHandler(PautaJaTemVotosException.class)
    public ResponseEntity<ErroResponse> tratarDeletePautaComVotos(
            PautaJaTemVotosException ex, HttpServletRequest request)
    {

        return criarRespostaErro(HttpStatus.CONFLICT,
                "Pauta ja possui votos",
                ex.getMessage(),
                request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErrosValidacao(
            MethodArgumentNotValidException ex,
            HttpServletRequest request)
    {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                erros.put(
                        erro.getField(),
                        erro.getDefaultMessage()));

        return criarRespostaErro(
                HttpStatus.BAD_REQUEST,
                "Erro de validação",
                erros.toString(),
                request
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResponse> tratarErrosUuidInvalido(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request)
    {
        return criarRespostaErro(
                HttpStatus.BAD_REQUEST,
                "id Invalido",
                ex.getMessage(),
                request
        );
    }

    private ResponseEntity<ErroResponse> criarRespostaErro(
            HttpStatus status,
            String erro,
            String mensagem,
            HttpServletRequest request)
    {
        ErroResponse response = new ErroResponse(
                LocalDateTime.now(),
                status.value(),
                erro,
                mensagem,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(response);
    }
}
