package com.example.dogshelter;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.service.DogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Web layer test for {@link DogController}.
 * Uses {@link WebMvcTest} to spin up only the web layer and mocks {@link DogService}.
 */
@WebMvcTest(DogControllerTest.class)
class DogControllerTest {

    @Autowired
    private MockMvc mockMvc; // Mock HTTP client for simulating requests to controller endpoints

    @MockBean
    private DogService dogService; // Mocked service dependency

    @Test
    void getAllDogs_ShouldReturnListOfDogs() throws Exception {
        Dog dog1 = new Dog(1L, "Buddy", "Labrador", 3, "Male", "Friendly", null);
        Dog dog2 = new Dog(2L, "Lucy", "Beagle", 2, "Female", "Playful", null);

        when(dogService.getAllDogs()).thenReturn(Arrays.asList(dog1, dog2));

        mockMvc.perform(get("/api/dog"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Buddy")))
                .andExpect(jsonPath("$[1].name", is("Lucy")));
    }

    @Test
    void getDogById_WhenDogExists_ShouldReturnDog() throws Exception {
        Dog dog = new Dog(1L, "Buddy", "Labrador", 3, "Male", "Friendly", null);

        when(dogService.getDogById(1L)).thenReturn(Optional.of(dog));

        mockMvc.perform(get("/api/dog/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Buddy")))
                .andExpect(jsonPath("$.breed", is("Labrador")));
    }

    @Test
    void getDogById_WhenDogDoesNotExist_ShouldReturn404() throws Exception {
        when(dogService.getDogById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/dog/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createDog_ShouldReturnSavedDog() throws Exception {
        Dog saved = new Dog(1L, "Buddy", "Labrador", 3, "Male", "Friendly", null);

        when(dogService.saveDog(any(Dog.class))).thenReturn(saved);

        mockMvc.perform(post("/api/dog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Buddy",
                                  "breed": "Labrador",
                                  "age": 3,
                                  "gender": "Male",
                                  "description": "Friendly"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Buddy")));
    }

    @Test
    void updateDog_WhenDogExists_ShouldReturnUpdatedDog() throws Exception {
        Dog existing = new Dog(1L, "Buddy", "Labrador", 3, "Male", "Friendly", null);
        Dog updated = new Dog(1L, "Max", "Golden Retriever", 4, "Male", "Calm", null);

        when(dogService.getDogById(1L)).thenReturn(Optional.of(existing));
        when(dogService.saveDog(any(Dog.class))).thenReturn(updated);

        mockMvc.perform(put("/api/dog/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Max",
                                  "breed": "Golden Retriever",
                                  "age": 4,
                                  "gender": "Male",
                                  "description": "Calm"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Max")))
                .andExpect(jsonPath("$.breed", is("Golden Retriever")));
    }

    @Test
    void updateDog_WhenDogDoesNotExist_ShouldReturn404() throws Exception {
        when(dogService.getDogById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/dog/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Ghost",
                                  "breed": "Unknown",
                                  "age": 1,
                                  "gender": "Male",
                                  "description": "Invisible"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteDog_ShouldReturn200() throws Exception {
        doNothing().when(dogService).deleteDog(1L);

        mockMvc.perform(delete("/api/dog/1"))
                .andExpect(status().isOk());
    }
}
