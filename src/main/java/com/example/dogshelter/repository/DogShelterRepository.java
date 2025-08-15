package com.example.dogshelter.repository; // Package declaration: places this interface in the repository (data access) layer

import com.example.dogshelter.entity.Dogshelter; // Import the Dogshelter entity for database operations
import org.springframework.data.jpa.repository.JpaRepository; // Import JpaRepository to provide CRUD and query method support

// Repository interface for managing Dogshelter entities in the database.
// Extending JpaRepository gives this interface a full set of database operations
// without writing any implementation code (Spring Data JPA provides it automatically).
public interface DogShelterRepository extends JpaRepository<Dogshelter, Long> { 
    // JpaRepository<Dogshelter, Long> means:
    //   - This repository works with Dogshelter entities
    //   - The primary key type of Dogshelter is Long

    // You can add custom query methods here if needed, for example:
    // List<Dogshelter> findByLocation(String location);
}
