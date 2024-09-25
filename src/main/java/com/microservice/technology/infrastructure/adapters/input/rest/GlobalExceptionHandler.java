package com.microservice.technology.infrastructure.adapters.input.rest;

import com.microservice.technology.domain.exception.TechnologyAlreadyExists;

import com.microservice.technology.domain.model.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static com.microservice.technology.utils.ErrorCatalog.*;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TechnologyAlreadyExists.class)
    public ErrorResponse handleTechnologyAlreadyExists( ) {
        return ErrorResponse.builder()
                .code(TECHNOLOGY_ALREADY_EXISTS.getCode())
                .message(TECHNOLOGY_ALREADY_EXISTS.getDescription())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
