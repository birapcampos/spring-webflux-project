package br.com.birapcampos.webflux_exemplo.controller.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<Mono<StandardError>> duplicateKeyException(
            DuplicateKeyException ex,
            ServerWebExchange exchange
    )
    {
        return ResponseEntity.badRequest()
                .body(Mono.just(StandardError.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .errormessage(verifyDuplicateKey(ex.getMessage()))
                        .path(exchange.getRequest().getPath().toString())
                        .build()));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    ResponseEntity<Mono<StandardError>> objectNotFoundException(
            ObjectNotFoundException ex,
            ServerWebExchange exchange
    )
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Mono.just(StandardError.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .errormessage(ex.getMessage())
                        .path(exchange.getRequest().getPath().toString())
                        .build()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Mono<ValidationError>> validationError(
            WebExchangeBindException ex,
            ServerWebExchange exchange){

        ValidationError error = new ValidationError(
                LocalDateTime.now(),
                exchange.getRequest().getPath().toString(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation error","Error on validation attributes"
                );
        for(FieldError x: ex.getBindingResult().getFieldErrors()){
            error.addError(x.getField(),x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(error));
    }




    private String verifyDuplicateKey(String message){
        if(message.contains("email dup key")){
            return "E-mail already registered!";
        }

        return "Duplicated key exception";
    }
}
