package com.example.dogshelter.service; // Package declaration: places this class in the service (business logic) layer

import com.example.dogshelter.entity.Dog; // Import the Dog entity class
import com.example.dogshelter.repository.DogRepository; // Import DogRepository for database access
import org.springframework.stereotype.Service; // Import @Service annotation to mark this as a service bean

import java.util.List; // Import List for returning multiple Dog objects
import java.util.Optional; // Import Optional for handling possible null values when fetching by ID

@Service // Marks this class as a Spring service component (detected during component scanning)
public class DogService { // Begin DogService class definition

    private final DogRepository dogRepository; // Final reference to DogRepository (injected by constructor)

    // Constructor-based dependency injection of DogRepository
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository; // Assign injected repository to the class field
    }

    // Retrieves all Dog entities from the database
    public List<Dog> getAllDogs() {
        return dogRepository.findAll(); // Uses built-in JpaRepository method to get all records
    }

    // Retrieves a Dog by its ID, wrapped in Optional (empty if not found)
    public Optional<Dog> getDogById(Long id) {
        return dogRepository.findById(id); // Uses built-in JpaRepository method to find by primary key
    }

    // Saves a new Dog or updates an existing Dog in the database
    public Dog saveDog(Dog dog) {
        return dogRepository.save(dog); // save() handles both insert (if ID is null) and update (if ID exists)
    }

    // Deletes a Dog from the database by its ID
    public void deleteDog(Long id) {
        dogRepository.deleteById(id); // Uses built-in JpaRepository method to delete by primary key
    }
} // End of DogServicer
