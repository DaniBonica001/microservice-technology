package com.microservice.technology.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tech_Capacity {
    private int id;
    private int capacity_id;
    private int technology_id;

}
