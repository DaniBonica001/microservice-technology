package com.microservice.technology.infrastructure.adapters.output.persistence.repository;

import com.microservice.technology.infrastructure.adapters.output.persistence.entity.Tech_CapacityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tech_CapacityRepository extends ReactiveCrudRepository<Tech_CapacityEntity, Integer> {
}
