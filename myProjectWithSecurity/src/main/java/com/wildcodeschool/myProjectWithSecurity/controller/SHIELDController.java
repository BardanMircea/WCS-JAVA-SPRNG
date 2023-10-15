package com.wildcodeschool.myProjectWithSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SHIELDController {
    @GetMapping("/")
    public String open() {
        return "Welcome to SHIELD";
    }

    @GetMapping("/avengers/assemble")
    public String openToChampions() {
        return "Avengers... Assemble";
    }

    @GetMapping("/secret-bases")
    public String openToDirector() {
        return "Bordeaux, Lyon, Nantes, Lille, Paris, Toulouse";
    }

}
