package com.microservice.technology.infrastructure.adapters.output.persistence.mapper;

import com.microservice.technology.domain.model.Techonology;
import com.microservice.technology.infrastructure.adapters.output.persistence.entity.TechonologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TechnologyPersistenceMapper {

    TechonologyEntity fromTechnologyToTechnologyEntity(Techonology technology);

    Techonology fromTechnologyEntityToTechnology(TechonologyEntity technologyEntity);
}

