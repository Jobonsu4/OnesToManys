package com.example.dogshelter.repository;

import com.example.dogshelter.entity.Dogshelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogShelterRepository extends JpaRepository<Dogshelter, Long> {
    // JpaRepository provides standard CRUD methods
}

