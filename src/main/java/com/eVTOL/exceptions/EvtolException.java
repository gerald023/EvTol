package com.eVTOL.exceptions;

import org.springframework.http.HttpStatus;

public class EvtolException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public EvtolException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public EvtolException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }


    public HttpStatus getStatus(){
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
