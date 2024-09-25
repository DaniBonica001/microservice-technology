package com.microservice.technology.infrastructure.adapters.output.persistence.repository;

import com.microservice.technology.infrastructure.adapters.output.persistence.entity.TechonologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TechonologyRepository extends ReactiveCrudRepository<TechonologyEntity, UUID> {

    Mono<Boolean> existsByName(String name);
}
