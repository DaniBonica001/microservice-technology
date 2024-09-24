package com.microservice.technology.infrastructure.adapters.input.rest.mapper;

import com.microservice.technology.domain.model.Techonology;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.request.CreateTechRequest;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.response.CreateTechResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnologyRestMapper {

    Techonology fromCreateTechRequestToTechonology(CreateTechRequest request);

    CreateTechResponse fromTechonologyToCreateTechResponse(Techonology technology);
}
