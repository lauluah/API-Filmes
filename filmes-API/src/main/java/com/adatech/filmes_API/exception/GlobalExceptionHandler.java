package com.adatech.filmes_API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UsuarioNaoEncontradoException.class})
    public ResponseEntity<Error> handlerExceptionGeral(UsuarioNaoEncontradoException e) {
        Error Error = new Error();
        Error.setMensagem(e.getMessage());
        Error.setCodigoErro("USR-404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> handlerExceptionGeral(Exception e) {
        Error error = new Error();
        error.setMensagem(e.getMessage());
        error.setCodigoErro("SRV-500");
        error.setCodigoErro("ERRO INTERNO SERVIDOR");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Error> handleValidationError(MethodArgumentNotValidException e) {
        Error error = new Error();
        error.setMensagem("Erro de validação nos campos");
        error.setCodigoErro("VALIDATION-400");

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(validationError -> {
            String fieldName = ((FieldError) validationError).getField();
            String errorMessage = validationError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        error.setErrors(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); //400
    }


    @ExceptionHandler({FilmeNaoEncontradoException.class})
    public ResponseEntity<Error> handlerFilmeNaoEncontrado(FilmeNaoEncontradoException e) {
        Error error = new Error();
        error.setMensagem(e.getMessage());
        error.setCodigoErro("MOV-404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
