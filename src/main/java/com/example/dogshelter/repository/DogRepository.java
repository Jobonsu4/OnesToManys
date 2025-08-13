package com.example.dogshelter.repository;

import com.example.dogshelter.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    // JpaRepository already provides methods like findAll(), findById(), save(), deleteById()
}

