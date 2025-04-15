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

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Tratamento especifico para validações (@NotBlank, NotNull,etc), exibindo mensagem de erro e os campos que foram inseridos incorretamente
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroResposta>> tratarErrosDeValidacao(MethodArgumentNotValidException ex) {
        List<ErroResposta> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErroResposta(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    //tratamento para recursos não econtrado o buscar por id
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> tratamentoRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    //tratamento alguns  para erros comuns
    @ExceptionHandler(ViolacaoDeIntegridadeException.class)
    public ResponseEntity<Map<String, String>> tratamentoViolacaoDeIntegridade(ViolacaoDeIntegridadeException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }


    //Tratamento para campos que não entram nas validações padrão (ex: enum mal formatado, datas erradas, tipos incompatíveis)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> tratarErroDeLeitura(HttpMessageNotReadableException ex) {
        String mensagemReposta = "Erro ao processar os dados enviados. Verifique se todos os campos estão corretos e bem formatados.";

        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", mensagemReposta);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    //Tratamento para erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> tratrErroGenerico(Exception ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", "Erro interno inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }



}
