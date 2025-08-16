package com.example.dogshelter.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dog_shelter")
public class Dogshelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for each shelter

    @Column(nullable = false, length = 100)
    private String name;  // Shelter's name

    @Column(nullable = false, length = 150)
    private String location;  // Physical address or location of the shelter

    @Column(length = 255)
    private String email;  // Shelter's email address

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;  // Shelter's contact phone number

    // One-to-many relationship: One shelter can have many dogs
    @OneToMany(
        mappedBy = "shelter",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER // 👈 Always load dogs when shelter is loaded
    )
    private List<Dog> dogs = new ArrayList<>();

    public Dogshelter() {}

    public Dogshelter(String name, String location, String email, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // ----- Getters and setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public List<Dog> getDogs() { return dogs; }
    public void setDogs(List<Dog> dogs) { this.dogs = dogs; }
}
