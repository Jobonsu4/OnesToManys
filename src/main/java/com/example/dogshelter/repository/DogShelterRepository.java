
package com.example.dogshelter.repository;

import com.example.dogshelter.entity.Dogshelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogShelterRepository extends JpaRepository<Dogshelter, Long> { }