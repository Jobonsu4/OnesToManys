package com.example.dogshelter;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.entity.Dogshelter;
import com.example.dogshelter.service.DogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DogControllerTest.class) // Load only DogController for testing
public class DogControllerTest {

    @Autowired
    private MockMvc mockMvc; // Mock MVC to simulate HTTP requests

    @MockBean
    private DogService dogService; // Mock service layer

    private Dogshelter shelter;
    private Dog dog1;
    private Dog dog2;

    @BeforeEach
    public void setup() {
        // Sample shelter for dog association
        shelter = new Dogshelter("Happy Tails Shelter", "123 Main St", "info@happytails.com", "555-1234");
        shelter.setId(1L);

        // Sample dogs
        dog1 = new Dog("Buddy", "Golden Retriever", "Male", 3, "Friendly", shelter);
        dog1.setId(1L);

        dog2 = new Dog("Luna", "Labrador", "Female", 2, "Cuddly", shelter);
        dog2.setId(2L);
    }

    @Test
    public void testGetAllDogs() throws Exception {
        Mockito.when(dogService.getAllDogs()).thenReturn(Arrays.asList(dog1, dog2));

        mockMvc.perform(get("/api/dog"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Buddy"))
                .andExpect(jsonPath("$[1].name").value("Luna"));
    }

    @Test
    public void testGetDogById_Found() throws Exception {
        Mockito.when(dogService.getDogById(1L)).thenReturn(Optional.of(dog1));

        mockMvc.perform(get("/api/dog/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    public void testGetDogById_NotFound() throws Exception {
        Mockito.when(dogService.getDogById(3L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/dog/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateDog() throws Exception {
        Mockito.when(dogService.saveDog(any(Dog.class))).thenReturn(dog1);

        ObjectMapper mapper = new ObjectMapper();
        String dogJson = mapper.writeValueAsString(dog1);

        mockMvc.perform(post("/api/dog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dogJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    public void testUpdateDog_Found() throws Exception {
        Dog updatedDog = new Dog("Buddy Updated", "Golden Retriever", "Male", 4, "Friendly", shelter);
        updatedDog.setId(1L);

        Mockito.when(dogService.getDogById(1L)).thenReturn(Optional.of(dog1));
        Mockito.when(dogService.saveDog(any(Dog.class))).thenReturn(updatedDog);

        ObjectMapper mapper = new ObjectMapper();
        String dogJson = mapper.writeValueAsString(updatedDog);

        mockMvc.perform(put("/api/dog/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dogJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy Updated"))
                .andExpect(jsonPath("$.age").value(4));
    }

    @Test
    public void testUpdateDog_NotFound() throws Exception {
        Mockito.when(dogService.getDogById(anyLong())).thenReturn(Optional.empty());

        ObjectMapper mapper = new ObjectMapper();
        String dogJson = mapper.writeValueAsString(dog1);

        mockMvc.perform(put("/api/dog/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dogJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteDog() throws Exception {
        Mockito.doNothing().when(dogService).deleteDog(1L);

        mockMvc.perform(delete("/api/dog/1"))
                .andExpect(status().isOk());
    }
}
