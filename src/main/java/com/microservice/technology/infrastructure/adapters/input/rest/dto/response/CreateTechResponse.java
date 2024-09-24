package com.microservice.technology.infrastructure.adapters.input.rest.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateTechResponse(
        UUID id,
        String name,
        String description
) {
}
