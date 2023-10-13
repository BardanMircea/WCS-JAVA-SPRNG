package com.wildcodeschool.milkshake.controller;

import com.wildcodeschool.milkshake.entity.Recette;
import com.wildcodeschool.milkshake.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recettes")
public class RecetteController {

    private final RecetteService recetteService;

    public RecetteController(@Autowired RecetteService recetteService){
        this.recetteService = recetteService;
    }

    @GetMapping()
    public List<Recette> findAll() {
        return this.recetteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Recette> findById(@PathVariable long id){
        return this.recetteService.findById(id);
    }

    @PostMapping()
    public void save(@RequestBody Recette recette){
        this.recetteService.saveOrUpdate(recette);
    }

   @PutMapping("/{id}")
   public void update(@RequestBody Recette recette, @PathVariable long id){
        recette.setId(id);
        this.recetteService.saveOrUpdate(recette);
   }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        this.recetteService.deleteById(id);
    }
}
