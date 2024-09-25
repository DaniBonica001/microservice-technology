package com.microservice.technology.infrastructure.adapters.output.persistence.repository;

import com.microservice.technology.infrastructure.adapters.output.persistence.entity.TechCapacityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TechCapacityRepository extends ReactiveCrudRepository<TechCapacityEntity, Integer> {

    Flux<TechCapacityEntity> findByCapacityId(int techonologyId);
}
