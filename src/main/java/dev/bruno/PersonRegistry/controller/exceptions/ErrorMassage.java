package dev.bruno.PersonRegistry.controller.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorMassage {

    private HttpStatus status;
    private String message;

    public ErrorMassage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorMassage() {}

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
