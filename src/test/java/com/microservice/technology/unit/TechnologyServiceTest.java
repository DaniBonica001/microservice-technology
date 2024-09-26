package com.microservice.technology.unit;

import com.microservice.technology.application.ports.output.TechnologyPersistencePort;
import com.microservice.technology.application.service.TechnologyService;
import com.microservice.technology.domain.exception.TechnologyAlreadyExists;
import com.microservice.technology.domain.exception.TechnologyNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.microservice.technology.domain.model.Techonology;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



@SpringBootTest
class TechnologyServiceTest {

    @Mock
    private TechnologyPersistencePort persistencePort;

    @InjectMocks
    private TechnologyService technologyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        technologyService = new TechnologyService(persistencePort);
        assertNotNull(persistencePort, "persistencePort should not be null after initialization");
    }

    @Test
    void testCreateTechnologySuccess() {
        // Arrange
        Techonology technology = Techonology.builder()
                .name(".Net")
                .description("Description")
                .build();
        when(persistencePort.existsByName(anyString())).thenReturn(Mono.just(false));
        when(persistencePort.createTechnology(any(Techonology.class))).thenReturn(Mono.just(technology));

        // Act & Assert
        StepVerifier.create(technologyService.createTechnology(technology))
                .expectNext(technology)
                .verifyComplete();
    }

    @Test
    void createTechnologyAlreadyExists() {
        // Arrange
        Techonology technology = Techonology.builder()
                .name(".Net")
                .description("Description")
                .build();
        when(persistencePort.existsByName(anyString())).thenReturn(Mono.just(true));

        // Act & Assert
        StepVerifier.create(technologyService.createTechnology(technology))
                .expectError(TechnologyAlreadyExists.class)
                .verify();
    }

    @Test
    void createTechnologyPersistenceError() {
        // Arrange
        Techonology technology = Techonology.builder()
                .name(".Net")
                .description("Description")
                .build();
        when(persistencePort.existsByName(anyString())).thenReturn(Mono.just(false));
        when(persistencePort.createTechnology(any(Techonology.class)))
                .thenReturn(Mono.error(new RuntimeException("Database error")));

        // Act & Assert
        StepVerifier.create(technologyService.createTechnology(technology))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void findAllPagedSuccess() {
        // Arrange
        List<Techonology> technologies = Arrays.asList(
                Techonology.builder().name("C++").description("Programming language").build(),
                Techonology.builder().name("C#").description("Programming language").build()
        );
        when(persistencePort.findAllPaged(any(PageRequest.class))).thenReturn(Flux.fromIterable(technologies));
        when(persistencePort.count()).thenReturn(Mono.just(2L));

        // Act & Assert
        StepVerifier.create(technologyService.findAllPaged("ASC", 0, 10))
                .expectNextMatches(page ->
                        page.getTotalElements() == 2 &&
                                page.getContent().size() == 2 &&
                                page.getContent().get(0).getName().equals("C++")
                )
                .verifyComplete();
    }

    @Test
    void findAllPagedEmptyList() {
        // Arrange
        when(persistencePort.findAllPaged(any(PageRequest.class))).thenReturn(Flux.empty());
        when(persistencePort.count()).thenReturn(Mono.just(0L));

        // Act & Assert
        StepVerifier.create(technologyService.findAllPaged("DESC", 0, 10))
                .expectError(TechnologyNotFound.class)
                .verify();
    }

    @Test
    void findAllPagedDifferentSortOrder() {
        // Arrange
        List<Techonology> technologies = Arrays.asList(
                Techonology.builder().name("C++").description("Programming language").build(),
                Techonology.builder().name("C#").description("Programming language").build()
        );
        when(persistencePort.findAllPaged(any(PageRequest.class))).thenReturn(Flux.fromIterable(technologies));
        when(persistencePort.count()).thenReturn(Mono.just(2L));

        // Act & Assert
        StepVerifier.create(technologyService.findAllPaged("DESC", 0, 10))
                .expectNextMatches(page ->
                        page.getTotalElements() == 2 &&
                                page.getContent().size() == 2 &&
                                (page.getContent().get(0).getName().equals("C++") || page.getContent().get(0).getName().equals("C#")) &&
                                (page.getContent().get(1).getName().equals("C++") || page.getContent().get(1).getName().equals("C#")) &&
                                !page.getContent().get(0).getName().equals(page.getContent().get(1).getName())
                )
                .verifyComplete();
    }
}
