package com.microservice.technology.application.ports.input;


import com.microservice.technology.domain.model.Techonology;
import reactor.core.publisher.Mono;

public interface TechnologyServicePort {

    Mono<Techonology> createTechnology(Techonology techonology);

}
