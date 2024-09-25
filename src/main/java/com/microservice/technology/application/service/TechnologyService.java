package com.microservice.technology.application.service;

import com.microservice.technology.application.ports.input.TechnologyServicePort;
import com.microservice.technology.application.ports.output.TechnologyPersistencePort;
import com.microservice.technology.domain.exception.TechnologyAlreadyExists;
import com.microservice.technology.domain.exception.TechnologyNotFound;
import com.microservice.technology.domain.model.TechCapacity;
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
        Sort sort = Sort.by((order ==null || order.isEmpty())? Sort.DEFAULT_DIRECTION : Sort.Direction.fromString(order), "name");
        return persistencePort.findAllPaged(PageRequest.of(page, size, sort))
                .switchIfEmpty(Mono.defer(() -> Mono.error(TechnologyNotFound::new)))
                .collectList()
                .zipWith(persistencePort.count())
                .map(t -> new PageImpl<>(t.getT1(), PageRequest.of(page, size, sort), t.getT2()));
    }


    private Mono<Boolean> existsById(int id) {
        return persistencePort.existsById(id);
    }

    @Override
    public Flux<Techonology> findByCapacityId(String capacityId) {
        return persistencePort.findByCapacityId(capacityId);
    }

    @Override
    public Mono<Void> createTechCapacity(int capacityId, List<Integer> techs) {
        return Flux.fromIterable(techs)
                .flatMap(techId ->
                        existsById(techId)
                                .flatMap(exists -> {
                                    if (exists) {
                                        // Si existe, crear el objeto Tech_Capacity
                                        return Mono.just(TechCapacity.builder()
                                                .capacityId(capacityId)
                                                .technologyId(techId)
                                                .build());
                                    } else {
                                        // Si no existe, lanzar error pero continuar
                                        return Mono.error(new Exception("Technology with ID " + techId + " does not exist"));
                                    }
                                })
                                .onErrorResume(e -> {
                                    log.error("Error creating tech capacity: {}", e.getMessage());
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
