package com.dar.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("shelter-entity")
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {

    @Id
    private Long id;
    private String name;
    private String location;
    private int capacity;
    private String type;
    private double rating;
    private boolean isGovernmentFunded;
    private double averageAdoptionTime;
    private double dailyCost;
}

