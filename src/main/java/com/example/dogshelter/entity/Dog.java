package com.example.dogshelter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "dog")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String breed;

    @Column(length = 10)
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false)
    @JsonBackReference // 👈 Prevents infinite recursion
    private Dogshelter shelter;

    public Dog() {}

    public Dog(String name, String breed, String gender, int age, String description, Dogshelter shelter) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.description = description;
        this.shelter = shelter;
    }

    // ----- Getters & Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Dogshelter getShelter() { return shelter; }
    public void setShelter(Dogshelter shelter) { this.shelter = shelter; }
}
