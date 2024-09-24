package com.microservice.technology.infrastructure.adapters.input.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateTechRequest(

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Description is required")
    String description
) {
}
