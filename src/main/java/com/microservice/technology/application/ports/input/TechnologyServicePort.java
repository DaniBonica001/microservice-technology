package com.microservice.technology.application.ports.input;


import com.microservice.technology.domain.model.Tech_Capacity;
import com.microservice.technology.domain.model.Techonology;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyServicePort {

    Mono<Techonology> createTechnology(Techonology techonology);
    Mono<Page<Techonology>> findAllPaged(String order, int page, int size);
    Mono<Boolean> existsById(int id);
    Mono<Techonology> findByName(String name);
    Mono<Void> createTechCapacity(int capacity_id, List<Integer> techs);

}
