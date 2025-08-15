package com.example.dogshelter;

import com.example.dogshelter.entity.Dogshelter;
import com.example.dogshelter.service.ShelterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DogShelterControllerTest.class)
public class DogShelterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterService shelterService;

    private Dogshelter shelter1;
    private Dogshelter shelter2;

    @BeforeEach
    public void setup() {
        shelter1 = new Dogshelter("Happy Tails Shelter", "123 Main St", "info@happytails.com", "555-1234");
        shelter1.setId(1L);

        shelter2 = new Dogshelter("Paws and Friends", "456 Elm St", "contact@pawsfriends.com", "555-5678");
        shelter2.setId(2L);
    }

    @Test
    public void testGetAllShelters() throws Exception {
        Mockito.when(shelterService.getAllShelters()).thenReturn(Arrays.asList(shelter1, shelter2));

        mockMvc.perform(get("/api/shelters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Happy Tails Shelter"))
                .andExpect(jsonPath("$[1].name").value("Paws and Friends"));
    }

    @Test
    public void testGetShelterById_Found() throws Exception {
        Mockito.when(shelterService.getShelterById(1L)).thenReturn(Optional.of(shelter1));

        mockMvc.perform(get("/api/shelters/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Happy Tails Shelter"));
    }

    @Test
    public void testGetShelterById_NotFound() throws Exception {
        Mockito.when(shelterService.getShelterById(10L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/shelters/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateShelter() throws Exception {
        Mockito.when(shelterService.saveShelter(any(Dogshelter.class))).thenReturn(shelter1);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(shelter1);

        mockMvc.perform(post("/api/shelters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Happy Tails Shelter"));
    }

    @Test
    public void testUpdateShelter_Found() throws Exception {
        Dogshelter updated = new Dogshelter("Updated Shelter", "New Location", "newemail@test.com", "555-9999");
        updated.setId(1L);

        Mockito.when(shelterService.getShelterById(1L)).thenReturn(Optional.of(shelter1));
        Mockito.when(shelterService.saveShelter(any(Dogshelter.class))).thenReturn(updated);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(updated);

        mockMvc.perform(put("/api/shelters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Shelter"))
                .andExpect(jsonPath("$.location").value("New Location"));
    }

    @Test
    public void testUpdateShelter_NotFound() throws Exception {
        Mockito.when(shelterService.getShelterById(anyLong())).thenReturn(Optional.empty());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(shelter1);

        mockMvc.perform(put("/api/shelters/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteShelter() throws Exception {
        Mockito.doNothing().when(shelterService).deleteShelter(1L);

        mockMvc.perform(delete("/api/shelters/1"))
                .andExpect(status().isOk());
    }
}
