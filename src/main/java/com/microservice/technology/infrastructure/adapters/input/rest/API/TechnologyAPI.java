package com.microservice.technology.infrastructure.adapters.input.rest.API;


import com.microservice.technology.infrastructure.adapters.input.rest.dto.request.CreateTechRequest;
import com.microservice.technology.infrastructure.adapters.input.rest.dto.response.CreateTechResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping(TechnologyAPI.BASE_URL)
public interface TechnologyAPI {

    String BASE_URL = "/technology";

    @PostMapping("/v1/api")
    Mono<CreateTechResponse> createTechnology(@Valid @RequestBody CreateTechRequest request);

    @GetMapping("/v1/api")
    Mono<Page<CreateTechResponse>> findAll(@RequestParam(required = false) String order, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size);


}
