package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.entity.Dogshelter;
import com.example.dogshelter.repository.DogRepository;
import com.example.dogshelter.repository.DogShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dog")
@CrossOrigin(origins = "http://localhost:5175") 
public class DogController {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private DogShelterRepository shelterRepository;

    // ✅ Get all dogs
    @GetMapping
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    // ✅ Get single dog by ID
    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
        Optional<Dog> dog = dogRepository.findById(id);
        return dog.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ✅ Create a dog under a shelter
    @PostMapping("/shelter/{shelterId}")
    public ResponseEntity<Dog> createDog(@PathVariable Long shelterId, @RequestBody Dog dog) {
        Optional<Dogshelter> shelter = shelterRepository.findById(shelterId);
        if (shelter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dog.setShelter(shelter.get());
        return ResponseEntity.ok(dogRepository.save(dog));
    }

    // ✅ Update a dog
    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable Long id, @RequestBody Dog dogDetails) {
        return dogRepository.findById(id).map(dog -> {
            dog.setName(dogDetails.getName());
            dog.setBreed(dogDetails.getBreed());
            dog.setGender(dogDetails.getGender());
            dog.setAge(dogDetails.getAge());
            dog.setDescription(dogDetails.getDescription());
            return ResponseEntity.ok(dogRepository.save(dog));
        }).orElse(ResponseEntity.notFound().build());
    }
// ✅ Delete a dog globally
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
    Optional<Dog> dogOpt = dogRepository.findById(id);
    if (dogOpt.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    dogRepository.delete(dogOpt.get());
    return ResponseEntity.noContent().build();
}

// ✅ Delete a dog *within a specific shelter*
// @DeleteMapping("/shelter/{shelterId}/dog/{dogId}")
// public ResponseEntity<Void> deleteDogInShelter(@PathVariable Long shelterId, @PathVariable Long dogId) {
//     Optional<Dogshelter> shelterOpt = shelterRepository.findById(shelterId);
//     if (shelterOpt.isEmpty()) {
//         return ResponseEntity.notFound().build();
//     }

//     Optional<Dog> dogOpt = dogRepository.findById(dogId);
//     if (dogOpt.isEmpty()) {
//         return ResponseEntity.notFound().build();
//     }

//     Dog dog = dogOpt.get();
//     if (!dog.getShelter().getId().equals(shelterId)) {
//         return ResponseEntity.badRequest().build();
//     }
    

//     dogRepository.delete(dog);
//     return ResponseEntity.noContent().build();
// }


@DeleteMapping("/shelter/{shelterId}/dog/{dogId}")
public ResponseEntity<Void> deleteDogInShelter(@PathVariable Long shelterId, @PathVariable Long dogId) {
    Optional<Dogshelter> shelterOpt = shelterRepository.findById(shelterId);
    if (shelterOpt.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Optional<Dog> dogOpt = dogRepository.findById(dogId);
    if (dogOpt.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Dog dog = dogOpt.get();
    Dogshelter shelter = shelterOpt.get();

    if (!dog.getShelter().getId().equals(shelterId)) {
        return ResponseEntity.badRequest().build();
    }

    // ✅ Keep both sides in sync
    shelter.getDogs().remove(dog);
    dog.setShelter(null);

    dogRepository.delete(dog);

    return ResponseEntity.noContent().build();
}

}