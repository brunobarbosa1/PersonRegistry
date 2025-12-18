package dev.bruno.PersonRegistry.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorMassage> personNotFoundException(PersonNotFoundException e) {

        ErrorMassage errorMassage = new ErrorMassage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMassage);
    }

    @ExceptionHandler(AdressNotFoundException.class)
    public ResponseEntity<ErrorMassage> adressNotFoundException(AdressNotFoundException e) {

        ErrorMassage errorMassage = new ErrorMassage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMassage);
    }
}
