package com.microservice.technology.infrastructure.adapters.input.rest.mapper;

import com.microservice.technology.domain.model.Tech_Capacity;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.request.CreateTechCapacityRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Tech_CapacityRestMapper {

    Tech_Capacity fromCreateTechCapacityRequestToTech_Capacity(CreateTechCapacityRequest request);
}
