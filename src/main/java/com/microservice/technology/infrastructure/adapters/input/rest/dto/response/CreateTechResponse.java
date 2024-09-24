package com.microservice.technology.infrastructure.adapters.input.rest.dto.response;

import lombok.Builder;


@Builder
public record CreateTechResponse(
        int id,
        String name,
        String description
) {
}
