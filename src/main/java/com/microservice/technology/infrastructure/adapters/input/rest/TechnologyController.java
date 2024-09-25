package com.microservice.technology.infrastructure.adapters.input.rest;

import com.microservice.technology.application.ports.input.TechnologyServicePort;
import com.microservice.technology.infrastructure.adapters.input.rest.API.TechnologyAPI;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.request.CreateTechCapacityRequest;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.request.CreateTechRequest;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.response.CreateTechResponse;
import com.microservice.technology.infrastructure.adapters.input.rest.mapper.TechnologyRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TechnologyController implements TechnologyAPI {

    private final TechnologyServicePort servicePort;
    private final TechnologyRestMapper mapper;


    @Override
    public Mono<CreateTechResponse> createTechnology(CreateTechRequest request) {
        return servicePort.createTechnology(mapper.fromCreateTechRequestToTechonology(request))
                .map(mapper::fromTechonologyToCreateTechResponse);
    }

    @Override
    public Mono<Page<CreateTechResponse>> findAll(String order, int page, int size) {
        return servicePort.findAllPaged(order, page, size)
                .map(pageTechnology  -> pageTechnology.map(mapper::fromTechonologyToCreateTechResponse));
    }


    @Override
    public Flux<CreateTechResponse> findByCapacityId(String capacityId) {
        return servicePort.findByCapacityId(capacityId).map(mapper::fromTechonologyToCreateTechResponse);
    }

    @Override
    public ResponseEntity<Mono<Void>> createTechCapacity(CreateTechCapacityRequest request) {
        return new ResponseEntity<>(servicePort.createTechCapacity(request.capacityId(), request.technologies()), HttpStatus.OK);
    }
}
