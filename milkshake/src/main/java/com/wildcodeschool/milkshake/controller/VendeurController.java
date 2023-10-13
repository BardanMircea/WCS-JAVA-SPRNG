package com.wildcodeschool.milkshake.controller;

import com.wildcodeschool.milkshake.entity.Vendeur;
import com.wildcodeschool.milkshake.service.VendeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public List<Vendeur> findAll() {
        return this.vendeurService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Vendeur> findById(@PathVariable long id){
        return this.vendeurService.findById(id);
    }

    @PostMapping()
    public void save(@RequestBody Vendeur vendeur){
        this.vendeurService.saveOrUpdate(vendeur);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Vendeur vendeur, @PathVariable long id){
        vendeur.setId(id);
        this.vendeurService.saveOrUpdate(vendeur);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        this.vendeurService.deleteById(id);
    }
}
