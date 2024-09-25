package com.microservice.technology.infrastructure.adapters.output.persistence.mapper;

import com.microservice.technology.domain.model.TechCapacity;
import com.microservice.technology.infrastructure.adapters.output.persistence.entity.TechCapacityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TechCapacityPersistenceMapper {

    TechCapacityEntity fromTechCapacityToTechCapacityEntity(TechCapacity tech_capacity);

    List<TechCapacityEntity> fromTechCapacityToTechCapacityEntity(List<TechCapacity> tech_capacities);
}
