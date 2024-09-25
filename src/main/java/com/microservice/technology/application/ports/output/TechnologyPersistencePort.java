package com.microservice.technology.application.ports.output;

import com.microservice.technology.domain.model.TechCapacity;
import com.microservice.technology.domain.model.Techonology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TechnologyPersistencePort {

    Mono<Techonology> createTechnology(Techonology techonology);
    Mono<Boolean> existsByName(String name);
    Flux<Techonology> findAllPaged(Pageable pageable);
    Mono<Long> count();
    Mono<Boolean> existsById(int id);
    Flux<Techonology> findByCapacityId(String capacityId);
    Mono<Void> createTechCapacity(List<TechCapacity> techonology);
}
