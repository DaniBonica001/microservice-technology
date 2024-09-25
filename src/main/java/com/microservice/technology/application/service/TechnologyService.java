package com.microservice.technology.application.service;

import com.microservice.technology.application.ports.input.TechnologyServicePort;
import com.microservice.technology.application.ports.output.TechnologyPersistencePort;
import com.microservice.technology.domain.exception.TechnologyAlreadyExists;
import com.microservice.technology.domain.model.Techonology;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TechnologyService implements TechnologyServicePort {

    private final TechnologyPersistencePort persistencePort;

    @Override
    public Mono<Techonology> createTechnology(Techonology techonology) {
        return persistencePort.existsByName(techonology.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(TechnologyAlreadyExists::new);
                    }
                    return persistencePort.createTechnology(techonology);
                });
    }
}
