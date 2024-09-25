package com.microservice.technology.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCatalog {

    TECHNOLOGY_ALREADY_EXISTS("ERR-001", "Technology already exists"),
    TECHNOLOGY_NOT_FOUND("ERR-002", "Technology not found");

    private final String code;
    private final String description;

}
