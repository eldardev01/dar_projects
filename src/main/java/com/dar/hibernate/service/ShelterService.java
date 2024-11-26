package com.dar.hibernate.service;

import com.dar.hibernate.model.QShelter;
import com.dar.hibernate.model.Shelter;
import com.dar.hibernate.repository.ShelterRepository;
import com.dar.hibernate.model.ShelterFilterRequest;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShelterService {
    private final ShelterRepository shelterRepository;

    public Page<Shelter> getShelters(ShelterFilterRequest filterRequest, int page, int size, String sortField) {
        QShelter qShelter = QShelter.shelter;
        BooleanBuilder builder = new BooleanBuilder();



        if (filterRequest.getName() != null) {
            builder.and(qShelter.name.containsIgnoreCase(filterRequest.getName()));
        }


        if (filterRequest.getType() != null) {
            builder.and(qShelter.type.eq(filterRequest.getType()));
        }


        if (filterRequest.getLocation() != null) {
            builder.and(qShelter.location.containsIgnoreCase(filterRequest.getLocation()));
        }

        // Фильтр по диапазонам
        if (filterRequest.getMinCapacity() != null && filterRequest.getMaxCapacity() != null) {
            builder.and(qShelter.capacity.between(filterRequest.getMinCapacity(), filterRequest.getMaxCapacity()));
        }
        if (filterRequest.getMinRating() != null && filterRequest.getMaxRating() != null) {
            builder.and(qShelter.rating.between(filterRequest.getMinRating(), filterRequest.getMaxRating()));
        }
        if (filterRequest.getMinAdoptionTime() != null && filterRequest.getMaxAdoptionTime() != null) {
            builder.and(qShelter.averageAdoptionTime.between(filterRequest.getMinAdoptionTime(), filterRequest.getMaxAdoptionTime()));
        }
        if (filterRequest.getMinDailyCost() != null && filterRequest.getMaxDailyCost() != null) {
            builder.and(qShelter.dailyCost.between(filterRequest.getMinDailyCost(), filterRequest.getMaxDailyCost()));
        }

        // Фильтр по флагу
        if (filterRequest.getIsGovernmentFunded() != null) {
            builder.and(qShelter.isGovernmentFunded.eq(filterRequest.getIsGovernmentFunded()));
        }

        // Пагинация и сортировка
        Sort sort = Sort.by(sortField != null ? sortField : "rating");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return shelterRepository.findAll(builder.getValue(), pageRequest);
    }
}
