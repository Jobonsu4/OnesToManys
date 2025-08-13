package com.example.dogshelter.entity;



import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dog_shelters")
public class Dogshelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key

    @Column(nullable = false)
    private String name;  // Shelter name

    @Column(nullable = false)
    private String location;  // Shelter location

    @Column(length = 255)
    private String email;  // Shelter email

    @Column(length = 20)
    private String phoneNumber;  // Shelter phone number

    // One shelter has many dogs
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> dogs = new ArrayList<>();

    public Dogshelter() {}

    public Dogshelter(String name, String location, String email, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
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
