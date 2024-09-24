package com.microservice.technology.application.service;

import com.microservice.technology.application.ports.input.TechnologyServicePort;
import com.microservice.technology.application.ports.output.TechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechnologyService implements TechnologyServicePort {

    private final TechnologyPersistencePort persistencePort;


}
