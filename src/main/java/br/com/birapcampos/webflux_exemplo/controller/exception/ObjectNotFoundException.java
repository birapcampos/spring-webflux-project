package br.com.birapcampos.webflux_exemplo.controller.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message){
        super(message);
    }

}
