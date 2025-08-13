package com.example.dogshelter.service;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    // Get all dogs
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    // Get dog by id
    public Optional<Dog> getDogById(Long id) {
        return dogRepository.findById(id);
    }

    // Save a dog
    public Dog saveDog(Dog dog) {
        return dogRepository.save(dog);
    }

    // Delete a dog by id
    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }
}
