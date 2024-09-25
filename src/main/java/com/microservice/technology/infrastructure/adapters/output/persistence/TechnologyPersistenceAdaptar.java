package com.microservice.technology.infrastructure.adapters.output.persistence;

import com.microservice.technology.application.ports.output.TechnologyPersistencePort;
import com.microservice.technology.domain.model.Tech_Capacity;
import com.microservice.technology.domain.model.Techonology;
import com.microservice.technology.infrastructure.adapters.output.persistence.mapper.Tech_CapacityPersistenceMapper;
import com.microservice.technology.infrastructure.adapters.output.persistence.mapper.TechnologyPersistenceMapper;
import com.microservice.technology.infrastructure.adapters.output.persistence.repository.Tech_CapacityRepository;
import com.microservice.technology.infrastructure.adapters.output.persistence.repository.TechonologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@RequiredArgsConstructor
public class TechnologyPersistenceAdaptar implements TechnologyPersistencePort {

    private final TechonologyRepository repository;
    private final Tech_CapacityRepository tech_capacityRepository;
    private final TechnologyPersistenceMapper mapper;
    private final Tech_CapacityPersistenceMapper mapperTechCapacity;

    @Override
    public Mono<Techonology> createTechnology(Techonology techonology) {

        return repository.save(mapper.fromTechnologyToTechnologyEntity(techonology)).map(mapper::fromTechnologyEntityToTechnology);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public Flux<Techonology> findAllPaged(Pageable pageable) {
        return repository.findAllBy(pageable).map(mapper::fromTechnologyEntityToTechnology);
    }

    @Override
    public Mono<Long> count() {
        return repository.count();
    }

    @Override
    public Mono<Boolean> existsById(int id) {
        return repository.existsById(id);
    }

    @Override
    public Mono<Techonology> findByName(String name) {
        return repository.findByName(name).map(mapper::fromTechnologyEntityToTechnology);
    }

    @Override
    public Mono<Void> createTechCapacity(List<Tech_Capacity> technology) {
        return tech_capacityRepository.saveAll(mapperTechCapacity.fromTech_CapacityToTech_CapacityEntity(technology)).then();
    }

}
