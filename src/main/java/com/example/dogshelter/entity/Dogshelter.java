package com.example.dogshelter.entity; // Package declaration: places this class in the entity (model) layer of the application

import jakarta.persistence.*; // Import JPA annotations for entity and relationship mapping
import java.util.ArrayList; // Import ArrayList for initializing the list of dogs
import java.util.List; // Import List interface for storing associated Dog entities

@Entity // Marks this class as a JPA entity that maps to a database table
@Table(name = "dog_shelter") // Specifies the exact table name in the database as "dog_shelters"
public class Dogshelter { // Begin class definition for Dogshelter entity

    @Id // Marks this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses database identity column for auto-generating IDs
    private Long id;  // Unique identifier for each shelter

    @Column(nullable = false, length = 100) // Column cannot be null and has a max length of 100 characters
    private String name;  // Shelter's name

    @Column(nullable = false, length = 150) // Column cannot be null and has a max length of 150 characters
    private String location;  // Physical address or location of the shelter

    @Column(length = 255) // Column with a max length of 255 characters (nullable by default)
    private String email;  // Shelter's email address

    @Column(name = "phone_number", length = 20) // Column name explicitly set to "phone_number" with max length 20
    private String phoneNumber;  // Shelter's contact phone number

    // One-to-many relationship: One shelter can have many dogs
    @OneToMany(
        mappedBy = "shelter", // Refers to the 'shelter' field in the Dog entity that owns the relationship
        cascade = CascadeType.ALL, // Propagates all operations (persist, merge, remove, refresh) from shelter to dogs
        orphanRemoval = true // Automatically removes dogs that are no longer associated with this shelter
        // Dogs are fetched only when explicitly accessed, not when the shelter is loaded
    )
    private List<Dog> dogs = new ArrayList<>(); // Collection of dogs belonging to this shelter, initialized to avoid null pointer exceptions

    public Dogshelter() {} // Default constructor (required by JPA)

    // Parameterized constructor to easily create new shelter instances with essential fields
    public Dogshelter(String name, String location, String email, String phoneNumber) {
        this.name = name; // Set shelter name
        this.location = location; // Set shelter location
        this.email = email; // Set shelter email
        this.phoneNumber = phoneNumber; // Set shelter phone number
    }

    // ----- Getters and setters -----
    public Long getId() { return id; } // Return shelter ID
    public void setId(Long id) { this.id = id; } // Set shelter ID

    public String getName() { return name; } // Return shelter name
    public void setName(String name) { this.name = name; } // Set shelter name

    public String getLocation() { return location; } // Return shelter location
    public void setLocation(String location) { this.location = location; } // Set shelter location

    public String getEmail() { return email; } // Return shelter email
    public void setEmail(String email) { this.email = email; } // Set shelter email

    public String getPhoneNumber() { return phoneNumber; } // Return shelter phone number
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; } // Set shelter phone number

    public List<Dog> getDogs() { return dogs; } // Return list of dogs in this shelter
    public void setDogs(List<Dog> dogs) { this.dogs = dogs; } // Replace list of dogs with a new list
} // End of Dogshelter entity
