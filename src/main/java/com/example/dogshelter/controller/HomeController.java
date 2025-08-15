package com.example.dogshelter.controller; // Package declaration: places this controller in the same package as other controllers

import org.springframework.web.bind.annotation.GetMapping; // Import annotation for mapping HTTP GET requests
import org.springframework.web.bind.annotation.RestController; // Import annotation for marking the class as a REST controller

@RestController // Marks this class as a REST controller (methods return data directly as JSON or plain text)
public class HomeController { // Begin class definition for the HomeController

    @GetMapping("/") // Maps HTTP GET requests to the root URL "/" to this method
    public String home() { // Method returns a simple string message (Spring will send this as plain text in the HTTP response body)
        return "🐶 Dog Shelter API is running! Use /api/shelter or /api/dog"; // Response message confirming the API is running and listing endpoints
    }
} // End of HomeController

