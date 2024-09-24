package com.microservice.technology.infrastructure.adapters.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("technology")
public class TechonologyEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
}
