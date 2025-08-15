package com.example.dogshelter.controller; // Package declaration: organizes this class under the controller layer of the dog shelter app

import com.example.dogshelter.entity.Dog;      // Import the Dog JPA entity/model representing a dog record
import com.example.dogshelter.service.DogService; // Import the service layer that encapsulates business logic and data access for dogs
import org.springframework.http.ResponseEntity;   // Import ResponseEntity for building HTTP responses with status codes and bodies
import org.springframework.web.bind.annotation.*; // Import Spring MVC annotations like @RestController, @RequestMapping, @GetMapping, etc.

import java.util.List; // Import List to represent collections of Dog objects in responses

@RestController                        // Marks this class as a REST controller (methods return JSON/XML directly, not views)
@RequestMapping("/api/dog")            // Base URI for all endpoints in this controller (all routes start with /api/dog)
public class DogController {           // Begin class definition for the DogController

    private final DogService dogService; // Final field for the DogService to enforce immutability after construction (dependency injection target)

    public DogController(DogService dogService) { // Constructor for injecting DogService (Spring autowires this)
        this.dogService = dogService;             // Assign injected DogService to the field for use in endpoint methods
    }

    @GetMapping                         // HTTP GET to /api/dog — fetch all dogs
    public List<Dog> getAllDogs() {     // Method returns a list of Dog objects (serialized as JSON by Spring)
        return dogService.getAllDogs(); // Delegate to service: retrieves all Dog records from the database/repository
    }

    @GetMapping("/{id}")                              // HTTP GET to /api/dog/{id} — fetch a single dog by ID
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) { // @PathVariable binds {id} from the URL to the method parameter
        return dogService.getDogById(id)              // Call service which returns Optional<Dog>
                .map(ResponseEntity::ok)              // If present, wrap the Dog in 200 OK ResponseEntity
                .orElse(ResponseEntity.notFound().build()); // If absent, return 404 Not Found with an empty body
    }

    @PostMapping                        // HTTP POST to /api/dog — create a new dog
    public Dog createDog(@RequestBody Dog dog) { // @RequestBody binds request JSON to a Dog object (validated if annotations present)
        return dogService.saveDog(dog);          // Delegate to service to persist the new Dog and return the saved entity (including generated ID)
    }

    @PutMapping("/{id}")                                            // HTTP PUT to /api/dog/{id} — full update of an existing dog
    public ResponseEntity<Dog> updateDog(@PathVariable Long id,     // Bind path variable {id} to select which Dog to update
                                         @RequestBody Dog dogDetails) { // Bind request body (incoming fields) to dogDetails object
        return dogService.getDogById(id).map(existingDog -> {       // Look up the existing Dog by id; if found, proceed to update
            existingDog.setName(dogDetails.getName());              // Update name from request payload
            existingDog.setBreed(dogDetails.getBreed());            // Update breed
            existingDog.setAge(dogDetails.getAge());                // Update age
            existingDog.setGender(dogDetails.getGender());          // Update gender
            existingDog.setDescription(dogDetails.getDescription()); // Update description
            existingDog.setShelter(dogDetails.getShelter());        // Update shelter association (watch for ownership/ID immutability rules)
            Dog updatedDog = dogService.saveDog(existingDog);       // Persist changes via service, which saves to the repository
            return ResponseEntity.ok(updatedDog);                   // Return 200 OK with the updated Dog in the response body
        }).orElse(ResponseEntity.notFound().build());               // If the target Dog isn’t found, return 404 Not Found
    }

   @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
    dogService.deleteDog(id);
    return ResponseEntity.ok().build();
}        // Delegate deletion to the service (should handle non-existent IDs appropriately)
    }
 // End of DogController class
