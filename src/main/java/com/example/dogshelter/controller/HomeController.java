package com.example.dogshelter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "🐶 Dog Shelter API is running! Use /api/shelters or /api/dogs";
    }
}

