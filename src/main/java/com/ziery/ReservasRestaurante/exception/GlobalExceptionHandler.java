package com.ziery.ReservasRestaurante.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Classe responsável por capturar e tratar exceções lançadas em toda a aplicação.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata exceções de validação de dados (@NotBlank, @NotNull, etc.), retornando mensagens detalhadas por campo inválido.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroResposta>> tratarErrosDeValidacao(MethodArgumentNotValidException ex) {
        List<ErroResposta> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErroResposta(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    // Trata exceção lançada quando um recurso não é encontrado ao buscar por ID.
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> tratamentoRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Trata exceções relacionadas à violação de regras de integridade da aplicação (ex: dados duplicados ou inconsistentes).
    @ExceptionHandler(ViolacaoDeIntegridadeException.class)
    public ResponseEntity<Map<String, String>> tratamentoViolacaoDeIntegridade(ViolacaoDeIntegridadeException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }


    // Trata erros de leitura da requisição, como campos mal formatados (ex: enums inválidos, datas erradas, tipos incompatíveis).
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> tratarErroDeLeitura(HttpMessageNotReadableException ex) {
        String mensagemReposta = "Erro ao processar os dados enviados. Verifique se todos os campos estão corretos e bem formatados.";

        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", mensagemReposta);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Trata qualquer exceção não prevista, retornando uma mensagem genérica de erro interno.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> tratrErroGenerico(Exception ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", "Erro interno inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }



}
