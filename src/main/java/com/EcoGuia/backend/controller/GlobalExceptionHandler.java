package com.EcoGuia.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        // Obtiene el primer mensaje de error de validación
        String mensajeError = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // Devuelve el error como texto plano (para que React lo pinte en rojo)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }
}




