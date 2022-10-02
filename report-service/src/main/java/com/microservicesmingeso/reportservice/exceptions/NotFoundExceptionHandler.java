package com.microservicesmingeso.reportservice.exceptions;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorMessage> NotFoundException(NotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}





