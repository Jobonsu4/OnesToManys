package com.example.dogshelter.controller; // Package declaration: groups this class with other controllers in the dog shelter app

import com.example.dogshelter.entity.Dogshelter; // Import the Dogshelter entity representing shelter records in the database
import com.example.dogshelter.service.ShelterService; // Import service layer class that handles shelter-related business logic
import org.springframework.http.ResponseEntity; // Import Spring's ResponseEntity for building HTTP responses with status codes and optional body
import org.springframework.web.bind.annotation.*; // Import Spring MVC annotations like @RestController, @RequestMapping, etc.

import java.util.List; // Import List to store multiple Dogshelter objects in a response

@RestController // Marks this class as a REST controller (return values are serialized to JSON/XML)
@RequestMapping("/api/shelter") // Base URI path for all endpoints in this controller; every route will start with /api/shelters
public class DogShelterController { // Begin class definition for DogShelterController

    private final ShelterService shelterService; // Declare final reference to ShelterService (for dependency injection)

    public DogShelterController(ShelterService shelterService) { // Constructor for injecting ShelterService
        this.shelterService = shelterService; // Assign injected ShelterService instance to the class field
    }

    @GetMapping // Handles HTTP GET requests to /api/shelters
    public List<Dogshelter> getAllShelters() { // Method returns a list of all shelter entities
        return shelterService.getAllShelters(); // Delegate to service layer to fetch all shelters from the database
    }

    @GetMapping("/{id}") // Handles HTTP GET requests to /api/shelters/{id}
    public ResponseEntity<Dogshelter> getShelterById(@PathVariable Long id) { // @PathVariable binds the {id} part of the URL to the method parameter
        return shelterService.getShelterById(id) // Call service layer to retrieve shelter by ID (returns Optional<Dogshelter>)
                .map(ResponseEntity::ok) // If shelter is found, wrap it in a 200 OK ResponseEntity
                .orElse(ResponseEntity.notFound().build()); // If not found, return a 404 Not Found response
    }

    @PostMapping // Handles HTTP POST requests to /api/shelters
    public Dogshelter createShelter(@RequestBody Dogshelter shelter) { // @RequestBody binds JSON request body to a Dogshelter object
        return shelterService.saveShelter(shelter); // Save the new shelter to the database and return the persisted entity
    }

    @PutMapping("/{id}") // Handles HTTP PUT requests to /api/shelters/{id} (full updates)
    public ResponseEntity<Dogshelter> updateShelter(@PathVariable Long id, // Bind the shelter ID from the path
                                                    @RequestBody Dogshelter shelterDetails) { // Bind incoming JSON to shelterDetails object
        return shelterService.getShelterById(id).map(existingShelter -> { // Find existing shelter by ID; if found, proceed
            existingShelter.setName(shelterDetails.getName()); // Update name
            existingShelter.setLocation(shelterDetails.getLocation()); // Update location
            existingShelter.setEmail(shelterDetails.getEmail()); // Update email
            existingShelter.setPhoneNumber(shelterDetails.getPhoneNumber()); // Update phone number
            Dogshelter updatedShelter = shelterService.saveShelter(existingShelter); // Save the updated shelter entity
            return ResponseEntity.ok(updatedShelter); // Return 200 OK with the updated shelter in the response
        }).orElse(ResponseEntity.notFound().build()); // If shelter not found, return 404 Not Found
    }

   @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteShelter(@PathVariable Long id) {
    shelterService.deleteShelter(id);          // call service to delete
    return ResponseEntity.ok().build();        // return 200 OK
}

} // End of DogShelterController class
