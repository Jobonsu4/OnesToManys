package com.example.dogshelter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Web layer test for {@link HomeController}.
 * Uses {@link WebMvcTest} to load only this controller.
 */
@WebMvcTest(HomeControllerTest.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc; // Mock HTTP client for simulating requests

    @Test
    void home_ShouldReturnWelcomeMessage() throws Exception {
        mockMvc.perform(get("/")) // Perform GET request to "/"
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(content().string("🐶 Dog Shelter API is running! Use /api/shelter or /api/dog")); // Expect exact response string
    }
}
