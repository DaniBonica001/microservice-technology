package com.microservice.technology.application.ports.output;

import com.microservice.technology.domain.model.Techonology;
import reactor.core.publisher.Mono;

public interface TechnologyPersistencePort {

    Mono<Techonology> createTechnology(Techonology techonology);
    Mono<Boolean> existsByName(String name);
}
