package com.example.dogshelter.repository; // Package declaration: places this interface in the repository (data access) layer

import com.example.dogshelter.entity.Dog; // Import the Dog entity so this repository can manage Dog objects
import org.springframework.data.jpa.repository.JpaRepository; // Import JpaRepository to provide built-in CRUD methods

// This repository interface is responsible for database operations on the Dog entity.
// By extending JpaRepository, it inherits a full set of CRUD and query methods 
// (save, findById, findAll, deleteById, etc.) without needing to implement them.
public interface DogRepository extends JpaRepository<Dog, Long> { 
    // JpaRepository<Dog, Long> means:
    //   - The repository manages Dog entities
    //   - The primary key type (ID) of Dog is Long

    // Additional custom query methods can be added here if needed, e.g.:
    // List<Dog> findByBreed(String breed);
}
