package dev.bruno.PersonRegistry.controller.exceptions;

public class AdressNotFoundException extends RuntimeException {

    public AdressNotFoundException(){
        super("Adress not found");
    }

    public AdressNotFoundException(String message) {
        super(message);
    }
}
