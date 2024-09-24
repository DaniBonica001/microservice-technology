package com.microservice.technology.infrastructure.adapters.input.rest.API;

import com.microservice.technology.infrastructure.adapters.input.rest.dto.request.CreateTechRequest;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.response.CreateTechResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@RequestMapping(TechnologyAPI.BASE_URL)
public interface TechnologyAPI {

    String BASE_URL = "/technology";

    @PostMapping
    Mono<CreateTechResponse> createTechnology(@Valid CreateTechRequest request);


}
