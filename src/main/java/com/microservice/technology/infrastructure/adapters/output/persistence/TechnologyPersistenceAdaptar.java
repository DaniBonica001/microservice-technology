package com.microservice.technology.infrastructure.adapters.output.persistence;

import com.microservice.technology.application.ports.output.TechnologyPersistencePort;
import com.microservice.technology.infrastructure.adapters.output.persistence.mapper.TechnologyPersistenceMapper;
import com.microservice.technology.infrastructure.adapters.output.persistence.repository.TechonologyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TechnologyPersistenceAdaptar implements TechnologyPersistencePort {

    private final TechonologyRepository repository;
    private final TechnologyPersistenceMapper mapper;
}
