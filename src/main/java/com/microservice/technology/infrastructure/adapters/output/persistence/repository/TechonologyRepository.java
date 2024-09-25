package com.microservice.technology.infrastructure.adapters.output.persistence.repository;

import com.microservice.technology.infrastructure.adapters.output.persistence.entity.TechonologyEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface TechonologyRepository extends ReactiveCrudRepository<TechonologyEntity, Integer>, ReactiveSortingRepository<TechonologyEntity, Integer> {

    Mono<Boolean> existsByName(String name);


    Flux<TechonologyEntity> findAllBy(Pageable pageable);

    Mono<Boolean> existsById(int id);
}
