package com.microservice.technology.infrastructure.adapters.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("technology_capacity")
public class Tech_CapacityEntity {
    @Id
    private int id;
    private int technology_id;
    private int capacity_id;
}
