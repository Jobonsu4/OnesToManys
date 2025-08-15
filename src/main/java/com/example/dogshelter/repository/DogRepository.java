package com.example.dogshelter.repository;

import com.example.dogshelter.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> { }