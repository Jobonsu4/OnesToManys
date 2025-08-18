package com.example.dogshelter;

import com.example.dogshelter.entity.Dogshelter;
import com.example.dogshelter.service.ShelterService;
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
 * Web layer test for {@link DogShelterController}.
 * Uses {@link WebMvcTest} to test only the web layer, mocking {@link ShelterService}.
 */
@WebMvcTest(DogShelterControllerTest.class)
class DogShelterControllerTest {

    @Autowired
    private MockMvc mockMvc; // Mock HTTP client

    @MockBean
    private ShelterService shelterService; // Mocked service dependency

    @Test
    void getAllShelters_ShouldReturnListOfShelters() throws Exception {
        Dogshelter s1 = new Dogshelter(1L, "Happy Tails", "New York", "happy@example.com", "1234567890");
        Dogshelter s2 = new Dogshelter(2L, "Paws Home", "Los Angeles", "paws@example.com", "9876543210");

        when(shelterService.getAllShelters()).thenReturn(Arrays.asList(s1, s2));

        mockMvc.perform(get("/api/shelter"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Happy Tails")))
                .andExpect(jsonPath("$[1].name", is("Paws Home")));
    }

    @Test
    void getShelterById_WhenExists_ShouldReturnShelter() throws Exception {
        Dogshelter s1 = new Dogshelter(1L, "Happy Tails", "New York", "happy@example.com", "1234567890");

        when(shelterService.getShelterById(1L)).thenReturn(Optional.of(s1));

        mockMvc.perform(get("/api/shelter/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Happy Tails")))
                .andExpect(jsonPath("$.location", is("New York")));
    }

    @Test
    void getShelterById_WhenNotExists_ShouldReturn404() throws Exception {
        when(shelterService.getShelterById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/shelter/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createShelter_ShouldReturnSavedShelter() throws Exception {
        Dogshelter saved = new Dogshelter(1L, "Happy Tails", "New York", "happy@example.com", "1234567890");

        when(shelterService.saveShelter(any(Dogshelter.class))).thenReturn(saved);

        mockMvc.perform(post("/api/shelter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Happy Tails",
                                  "location": "New York",
                                  "email": "happy@example.com",
                                  "phoneNumber": "1234567890"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Happy Tails")));
    }

    @Test
    void updateShelter_WhenExists_ShouldReturnUpdatedShelter() throws Exception {
        Dogshelter existing = new Dogshelter(1L, "Happy Tails", "NY", "old@example.com", "1111111111");
        Dogshelter updated = new Dogshelter(1L, "Happy Tails Updated", "New York", "happy@example.com", "1234567890");

        when(shelterService.getShelterById(1L)).thenReturn(Optional.of(existing));
        when(shelterService.saveShelter(any(Dogshelter.class))).thenReturn(updated);

        mockMvc.perform(put("/api/shelter/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Happy Tails Updated",
                                  "location": "New York",
                                  "email": "happy@example.com",
                                  "phoneNumber": "1234567890"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Happy Tails Updated")))
                .andExpect(jsonPath("$.email", is("happy@example.com")));
    }

    @Test
    void updateShelter_WhenNotExists_ShouldReturn404() throws Exception {
        when(shelterService.getShelterById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/shelter/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Ghost Shelter",
                                  "location": "Nowhere",
                                  "email": "ghost@example.com",
                                  "phoneNumber": "0000000000"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteShelter_ShouldReturn200() throws Exception {
        doNothing().when(shelterService).deleteShelter(1L);

        mockMvc.perform(delete("/api/shelter/1"))
                .andExpect(status().isOk());
    }
}
