package com.microservice.technology.application.ports.output;

import com.microservice.technology.domain.model.Techonology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;


public interface TechnologyPersistencePort {

    Mono<Techonology> createTechnology(Techonology techonology);
    Mono<Boolean> existsByName(String name);
    Flux<Techonology> findAllPaged(Pageable pageable);
    Mono<Long> count();
}
