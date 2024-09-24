package com.microservice.technology.infrastructure.adapters.input.rest.API;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(TechnologyAPI.BASE_URL)
public interface TechnologyAPI {

    String BASE_URL = "/technology";
}
