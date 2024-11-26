package com.dar.hibernate.repository;

import com.dar.hibernate.model.Shelter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ShelterRepository extends MongoRepository<Shelter, Long>, QuerydslPredicateExecutor<Shelter> {
}
