package com.dar.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShelterFilterRequest {
    private String name;
    private String type;
    private String location;
    private Integer minCapacity;
    private Integer maxCapacity;
    private Double minRating;
    private Double maxRating;
    private Double minAdoptionTime;
    private Double maxAdoptionTime;
    private Double minDailyCost;
    private Double maxDailyCost;
    private Boolean isGovernmentFunded;
}

