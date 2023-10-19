package com.wildcodeschool.milkshake.controller;

import com.wildcodeschool.milkshake.entity.Recette;
import com.wildcodeschool.milkshake.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/recettes")
public class RecetteController {

    private final RecetteService recetteService;

    public RecetteController(@Autowired RecetteService recetteService){
        this.recetteService = recetteService;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(this.recetteService.findAll());
        } catch(RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        try{
            Optional<Recette> recipe = this.recetteService.findById(id);
            if (recipe.isEmpty()) return ResponseEntity.status(404).body("Not found");

            return ResponseEntity.ok(recipe.get());
        } catch(RuntimeException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Recette recette){
        try{
            this.recetteService.saveOrUpdate(recette, null);
            return ResponseEntity.ok("Recette saved successfully");
        } catch(RuntimeException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

   @PutMapping("/{id}")
   public ResponseEntity<?> update(@RequestBody Recette recette, @PathVariable long id){
        try{
            if (this.recetteService.findById(id).isEmpty())
                return ResponseEntity.status(404).body("Not found");

            this.recetteService.saveOrUpdate(recette, id);
            return ResponseEntity.ok("Recette updated successfully");
        } catch(RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

   }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        try{
            if (this.recetteService.findById(id).isEmpty())
                return ResponseEntity.status(404).body("Not found");

            this.recetteService.deleteById(id);
            return ResponseEntity.ok().body("Recipe deleted successfully");
        } catch(RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
