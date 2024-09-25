package com.microservice.technology.infrastructure.adapters.input.rest.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record CreateTechCapacityRequest(

        int capacityId,
        List<Integer> technologies
) {
}
