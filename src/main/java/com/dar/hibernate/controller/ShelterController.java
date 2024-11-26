package com.dar.hibernate.controller;

import com.dar.hibernate.model.Shelter;
import com.dar.hibernate.service.ShelterService;
import com.dar.hibernate.model.ShelterFilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shelters")
@RequiredArgsConstructor
public class ShelterController {
    private final ShelterService shelterService;

    @GetMapping("/filter")
    public Page<Shelter> filterShelters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer minCapacity,
            @RequestParam(required = false) Integer maxCapacity,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating,
            @RequestParam(required = false) Double minAdoptionTime,
            @RequestParam(required = false) Double maxAdoptionTime,
            @RequestParam(required = false) Double minDailyCost,
            @RequestParam(required = false) Double maxDailyCost,
            @RequestParam(required = false) Boolean isGovernmentFunded,
            @RequestParam(defaultValue = "rating") String sortField,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ShelterFilterRequest filterRequest = new ShelterFilterRequest(
                name, type, location, minCapacity, maxCapacity,
                minRating, maxRating, minAdoptionTime, maxAdoptionTime,
                minDailyCost, maxDailyCost, isGovernmentFunded
        );
        return shelterService.getShelters(filterRequest, page, size, sortField);
    }
}

