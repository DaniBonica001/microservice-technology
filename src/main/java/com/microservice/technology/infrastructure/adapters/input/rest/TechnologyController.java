package com.microservice.technology.infrastructure.adapters.input.rest;

import com.microservice.technology.application.ports.input.TechnologyServicePort;
import com.microservice.technology.infrastructure.adapters.input.rest.API.TechnologyAPI;
import com.microservice.technology.infrastructure.adapters.input.rest.mapper.TechnologyRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TechnologyController implements TechnologyAPI {

    private final TechnologyServicePort servicePort;
    private final TechnologyRestMapper mapper;

}
