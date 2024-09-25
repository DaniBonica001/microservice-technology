package com.microservice.technology.infrastructure.adapters.input.rest.mapper;

import com.microservice.technology.domain.model.Techonology;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.request.CreateTechRequest;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.response.CreateTechResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TechnologyRestMapper {

    Techonology fromCreateTechRequestToTechonology(CreateTechRequest request);

    CreateTechResponse fromTechonologyToCreateTechResponse(Techonology technology);
}
