package com.microservice.technology.application.service;

import com.microservice.technology.application.ports.input.TechnologyServicePort;
import com.microservice.technology.application.ports.output.TechnologyPersistencePort;
import com.microservice.technology.domain.exception.TechnologyAlreadyExists;
import com.microservice.technology.domain.exception.TechnologyNotFound;
import com.microservice.technology.domain.model.Techonology;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
}
