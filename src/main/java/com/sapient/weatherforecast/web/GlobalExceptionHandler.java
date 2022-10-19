package com.sapient.weatherforecast.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map> handleException(RuntimeException e) {
        Map<String, Object> errorMap = new HashMap();
        errorMap.put("code", 500);
        errorMap.put("errorMessage", e.getMessage());
        return ResponseEntity.ok(errorMap);
    }
}
