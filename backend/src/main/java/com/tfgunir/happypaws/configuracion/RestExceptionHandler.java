package com.tfgunir.happypaws.configuracion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tfgunir.happypaws.exceptions.AppException;
import com.tfgunir.happypaws.modelo.dto.ErrorDto;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = { AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto>handleException(AppException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ErrorDto(ex.getMessage()));
    }
}
