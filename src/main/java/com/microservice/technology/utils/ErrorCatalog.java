package com.microservice.technology.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCatalog {

    TECHNOLOGY_ALREADY_EXISTS("ERR-001", "Technology already exists");

    private final String code;
    private final String description;

}
