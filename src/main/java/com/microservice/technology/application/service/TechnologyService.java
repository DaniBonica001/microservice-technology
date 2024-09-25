package com.microservice.technology.application.service;

import com.microservice.technology.application.ports.input.TechnologyServicePort;
import com.microservice.technology.application.ports.output.TechnologyPersistencePort;
import com.microservice.technology.domain.exception.TechnologyAlreadyExists;
import com.microservice.technology.domain.exception.TechnologyNotFound;
import com.microservice.technology.domain.model.Tech_Capacity;
import com.microservice.technology.domain.model.Techonology;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @Override
    public Mono<Page<Techonology>> findAllPaged(String order, int page, int size) {
        if (order == null || order.isBlank()) {
            return persistencePort.findAllPaged(PageRequest.of(page, size))
                    .switchIfEmpty(Mono.defer(() -> Mono.error(TechnologyNotFound::new)))
                    .collectList()
                    .zipWith(persistencePort.count())
                    .map(t -> new PageImpl<>(t.getT1(), PageRequest.of(page, size), t.getT2()));
        }
        Sort sort = Sort.by(Sort.Direction.fromString(order), "name");
        return persistencePort.findAllPaged(PageRequest.of(page, size, sort))
                .switchIfEmpty(Mono.defer(() -> Mono.error(TechnologyNotFound::new)))
                .collectList()
                .zipWith(persistencePort.count())
                .map(t -> new PageImpl<>(t.getT1(), PageRequest.of(page, size, sort), t.getT2()));
    }

    @Override
    public Mono<Boolean> existsById(int id) {
        return persistencePort.existsById(id);
    }

    @Override
    public Mono<Techonology> findByName(String name) {
        return persistencePort.findByName(name);
    }

    @Override
    public Mono<Void> createTechCapacity(int capacity_id, List<Integer> techs) {
        return Flux.fromIterable(techs)
                .flatMap(techId ->
                        existsById(techId)
                                .flatMap(exists -> {
                                    if (exists) {
                                        // Si existe, crear el objeto Tech_Capacity
                                        return Mono.just(Tech_Capacity.builder()
                                                .capacity_id(capacity_id)
                                                .technology_id(techId)
                                                .build());
                                    } else {
                                        // Si no existe, lanzar error pero continuar
                                        return Mono.error(new Exception("Technology with ID " + techId + " does not exist"));
                                    }
                                })
                                .onErrorResume(e -> {
                                    log.error(e.getMessage());
                                    return Mono.empty();
                                })
                )
                .collectList()  // Convertir a lista después de filtrar los válidos
                .flatMap(validTechCapacities -> {
                    if (validTechCapacities.isEmpty()) {
                        return Mono.error(new Exception("No valid technologies found"));
                    }
                    // Guardar las asociaciones válidas
                    return persistencePort.createTechCapacity(validTechCapacities);
                });
    }
}
