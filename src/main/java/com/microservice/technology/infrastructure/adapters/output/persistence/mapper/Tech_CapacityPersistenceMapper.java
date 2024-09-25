package com.microservice.technology.infrastructure.adapters.output.persistence.mapper;

import com.microservice.technology.domain.model.Tech_Capacity;
import com.microservice.technology.infrastructure.adapters.output.persistence.entity.Tech_CapacityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Tech_CapacityPersistenceMapper {

    Tech_CapacityEntity fromTech_CapacityToTech_CapacityEntity(Tech_Capacity tech_capacity);

    List<Tech_CapacityEntity> fromTech_CapacityToTech_CapacityEntity(List<Tech_Capacity> tech_capacities);
}
