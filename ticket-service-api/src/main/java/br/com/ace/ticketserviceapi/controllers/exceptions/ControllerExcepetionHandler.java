package br.com.ace.ticketserviceapi.controllers.exceptions;

import models.excpetions.GenericFeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerExcepetionHandler {

    @ExceptionHandler(GenericFeignException.class)
        ResponseEntity<Map> handleGenericFeignException(final GenericFeignException ex) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getError());
        }
}

