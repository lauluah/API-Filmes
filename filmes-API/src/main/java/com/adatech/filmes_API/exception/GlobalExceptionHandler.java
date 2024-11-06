package com.adatech.filmes_API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        Error Error = new Error();
        Error.setMensagem(e.getMessage());
        Error.setCodigoErro("SRV-500");
        Error.setCodigoErro("ERRO INTERNO SERVIDOR");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error);
    }

    @ExceptionHandler({FilmeNaoEncontradoException.class})
    public ResponseEntity<Error> handlerFilmeNaoEncontrado(FilmeNaoEncontradoException e) {
        Error Error = new Error();
        Error.setMensagem(e.getMessage());
        Error.setCodigoErro("MOV-404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error);
    }
}
