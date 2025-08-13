package com.example.dogshelter.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dog")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key

    @Column(nullable = false)
    private String name;  // Dog name

    @Column(length = 100)
    private String breed;  // Dog breed

    private Integer age;  // Dog age

    @Column(length = 10)
    private String gender;  // Dog gender

    @Column(columnDefinition = "TEXT")
    private String description;  // Dog description

    // Many dogs belong to one shelter
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Dogshelter shelter;

    public Dog() {}

    public Dog(String name, String breed, Integer age, String gender, String description, DogShelter shelter) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.shelter = shelter;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Dogshelter getShelter() { return shelter; }
    public void setShelter(Dogshelter shelter) { this.shelter = shelter; }
}
