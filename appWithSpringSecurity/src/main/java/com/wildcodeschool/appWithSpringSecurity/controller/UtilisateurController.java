package com.wildcodeschool.appWithSpringSecurity.controller;

import com.wildcodeschool.appWithSpringSecurity.entity.Utilisateur;
import com.wildcodeschool.appWithSpringSecurity.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(@Autowired UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/signup")
    public String accessSignup(){
        return "Welcome all. Please sign up using the POST http method on this endpoint";
    }

    @GetMapping("/login")
    public String accessLogin(){
        return "Welcome all. Please log in using the POST http method on this endpoint";
    }

    @GetMapping("/content")
    public String accessContent(){
        return "Welcome, USER/ADMIN";
    }

    @GetMapping("/admin-page")
    public String accessAdminPage(){
        return "Welcome, ADMIN";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Utilisateur utilisateur){
        if (this.utilisateurService.canSignupUser(utilisateur))
            return ResponseEntity.ok().body("User signed up successfully: " + this.utilisateurService.findByUsername(utilisateur.getUsername()).get().getUsername());

        return ResponseEntity.status(403).body("Please choose another username ('" + utilisateur.getUsername() + "' already exists)");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilisateur utilisateur){
        Optional<String> token = this.utilisateurService.loginUser(utilisateur);
        if (token.isPresent())
            return ResponseEntity.ok(token);

        return ResponseEntity.status(401).body("Invalid username and/or password");
    }
}
