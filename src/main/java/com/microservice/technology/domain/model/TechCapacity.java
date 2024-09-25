package com.microservice.technology.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechCapacity {
    private int id;
    private int technologyId;
    private int capacityId;


}
