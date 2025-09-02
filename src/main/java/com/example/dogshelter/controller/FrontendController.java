package com.example.dogshelter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    // ----------------------------
    // Vanilla JS Frontend
    // ----------------------------
    @GetMapping("/1vanilla")
    public String vanilla() {
        // Forward to the index.html of 1vanilla
        return "forward:/1vanilla/index.html";
    }

    // ----------------------------
// Vue JS Frontend
// ----------------------------
@GetMapping("/2vuejs")
public String vue() {
    // Forward to the index.html of Vue app
    return "forward:/2vuejs/index.html";
}

    // ----------------------------
    // React JS Frontend
    // ----------------------------
@GetMapping("/react")
public String react() {
    return "forward:/react/index.html";
}
}
