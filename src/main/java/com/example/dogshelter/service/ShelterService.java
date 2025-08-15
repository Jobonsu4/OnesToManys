package com.example.dogshelter.service; // Package declaration: places this class in the service (business logic) layer

import com.example.dogshelter.entity.Dogshelter; // Import Dogshelter entity so this service can work with shelter objects
import com.example.dogshelter.repository.DogShelterRepository; // Import repository for database operations on shelters
import org.springframework.stereotype.Service; // Import @Service annotation to mark this as a Spring service bean

import java.util.List; // Import List for returning multiple shelters
import java.util.Optional; // Import Optional for handling possibly missing records

@Service // Marks this class as a Spring-managed service component
public class ShelterService { // Begin ShelterService class definition

    private final DogShelterRepository shelterRepository; // Final reference to repository for data access

    // Constructor-based dependency injection of DogShelterRepository
    public ShelterService(DogShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository; // Assign injected repository to field
    }

    // Retrieve all shelters from the database
    public List<Dogshelter> getAllShelters() {
        return shelterRepository.findAll(); // Uses built-in JpaRepository method to fetch all records
    }

    // Retrieve a shelter by its ID, wrapped in Optional to handle possible null
    public Optional<Dogshelter> getShelterById(Long id) {
        return shelterRepository.findById(id); // Uses built-in JpaRepository method to find by primary key
    }

    // Save a new shelter or update an existing one
    public Dogshelter saveShelter(Dogshelter shelter) {
        return shelterRepository.save(shelter); // Inserts or updates shelter record depending on whether ID exists
    }

    // Delete a shelter by its ID
    public void deleteShelter(Long id) {
        shelterRepository.deleteById(id); // Uses built-in JpaRepository method to remove record by primary key
    }
} // End of ShelterService


