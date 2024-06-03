package br.com.birapcampos.webflux_exemplo.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<FieldError> errors = new ArrayList<>();

    public ValidationError(LocalDateTime timestamp, String path, Integer status, String error, String errormessage) {
        super(timestamp, path, status, error, errormessage);
    }

    public void addError(String fieldError, String message){
        this.errors.add(new FieldError(fieldError,message));
    }

    @Getter
    @AllArgsConstructor
    private static final  class FieldError{
        private String fieldError;
        private String message;

    }
}
