package com.example.dogshelter;


import com.example.dogshelter.repository.DogRepository;
import com.example.dogshelter.repository.DogShelterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JPA test for {@link Dog} entity.
 * Uses an in-memory H2 database.
 */
@DataJpaTest
class DogEntityTest {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private DogShelterRepository shelterRepository;

    @Test
    void whenSaveDog_thenItShouldBeFoundById() {
        // Arrange: create and persist a shelter first (since Dog requires one)
        Dogshelter shelter = new DogShelter("Happy Paws", "123 Main St", "info@happypaws.org", "555-1234");
        shelter = shelterRepository.save(shelter);

        // Arrange: create a dog linked to that shelter
        Dog dog = new Dog("Buddy", "Labrador", "Male", 3,
                "Friendly and playful", shelter);
        dog.setImageUrl("http://example.com/dog.jpg");

        Dog savedDog = dogRepository.save(dog);

        // Act: retrieve the dog from DB
        Optional<Dog> foundDog = dogRepository.findById(savedDog.getId());

        // Assert
        assertThat(foundDog).isPresent();
        assertThat(foundDog.get().getName()).isEqualTo("Buddy");
        assertThat(foundDog.get().getBreed()).isEqualTo("Labrador");
        assertThat(foundDog.get().getShelter().getName()).isEqualTo("Happy Paws");
        assertThat(foundDog.get().getImageUrl()).isEqualTo("http://example.com/dog.jpg");
    }
}
