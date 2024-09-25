package com.microservice.technology.infrastructure.adapters.output.persistence;

import com.microservice.technology.application.ports.output.TechnologyPersistencePort;
import com.microservice.technology.domain.model.Techonology;
import com.microservice.technology.infrastructure.adapters.output.persistence.mapper.TechnologyPersistenceMapper;
import com.microservice.technology.infrastructure.adapters.output.persistence.repository.TechonologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TechnologyPersistenceAdaptar implements TechnologyPersistencePort {

    private final TechonologyRepository repository;
    private final TechnologyPersistenceMapper mapper;

    @Override
    public Mono<Techonology> createTechnology(Techonology techonology) {
        return repository.save(mapper.fromTechnologyToTechnologyEntity(techonology)).map(mapper::fromTechnologyEntityToTechnology);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return repository.existsByName(name);
    }
}
