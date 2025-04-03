package com.ziery.ReservasRestaurante.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Tratamento especifico para validações (@NotBlank, NotNull,etc), exibindo mensagem de erro e os campos que foram inseridos incorretamente
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroResposta>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErroResposta> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErroResposta(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }
}
