package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.entity.Dogshelter;
import com.example.dogshelter.service.DogService;
import com.example.dogshelter.service.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dogs")
public class DogController {

    private final DogService dogService;
    private final ShelterService shelterService;

    public DogController(DogService dogService, ShelterService shelterService) {
        this.dogService = dogService;
        this.shelterService = shelterService;
    }

    // Get all dogs
    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    // Get dog by ID
    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
        return dogService.getDogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a dog for a specific shelter
    @PostMapping("/shelter/{shelterId}")
    public ResponseEntity<Dog> createDog(@PathVariable Long shelterId, @RequestBody Dog dog) {
        return shelterService.getShelterById(shelterId)
                .map(shelter -> {
                    dog.setShelter(shelter);
                    Dog savedDog = dogService.saveDog(dog);
                    return ResponseEntity.ok(savedDog);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a dog
    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
    }
}

