package com.wildcodeschool.milkshake.controller;
import com.wildcodeschool.milkshake.entity.Vendeur;
import com.wildcodeschool.milkshake.service.VendeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendeurs")
public class VendeurController {

    private final VendeurService vendeurService;

    public VendeurController(@Autowired VendeurService vendeurService){
        this.vendeurService = vendeurService;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(this.vendeurService.findAll());
        } catch(RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        try{
            Optional<Vendeur> vendeur = this.vendeurService.findById(id);
            if (vendeur.isEmpty()) return ResponseEntity.status(404).body("Not found");

            return ResponseEntity.ok(vendeur.get());
        } catch(RuntimeException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Vendeur vendeur){
        try{
            this.vendeurService.saveOrUpdate(vendeur, null);
            return ResponseEntity.ok("Vendeur saved successfully");
        } catch(RuntimeException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Vendeur vendeur, @PathVariable long id){
        try{
            if (this.vendeurService.findById(id).isEmpty())
                return ResponseEntity.status(404).body("Not found");

            this.vendeurService.saveOrUpdate(vendeur, id);
            return ResponseEntity.ok("Vendeur updated successfully");
        } catch(RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        try{
            if (this.vendeurService.findById(id).isEmpty())
                return ResponseEntity.status(404).body("Not found");

            this.vendeurService.deleteById(id);
            return ResponseEntity.ok().body("Vendeur deleted successfully");
        } catch(RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
