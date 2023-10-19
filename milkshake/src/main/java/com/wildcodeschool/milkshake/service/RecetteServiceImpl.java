package com.wildcodeschool.milkshake.service;

import com.wildcodeschool.milkshake.entity.Recette;
import com.wildcodeschool.milkshake.repository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetteServiceImpl implements RecetteService{

    private final RecetteRepository recetteRepository;

    public RecetteServiceImpl(@Autowired RecetteRepository recetteRepository){
        this.recetteRepository = recetteRepository;
    }

    @Override
    public List<Recette> findAll(){
        try {
            return this.recetteRepository.findAll();
        } catch(Exception e) {
            throw new RuntimeException("Unexpected database server error");
        }
    }

    @Override
    public Optional<Recette> findById(long id){
        try {
            return this.recetteRepository.findById(id);
        } catch(Exception e) {
            throw new RuntimeException("Unexpected database server error");
        }
    }

    @Override
    public void saveOrUpdate(Recette recette, Long id){
        try {
            Optional.ofNullable(id).ifPresent(recette::setId);
            this.recetteRepository.save(recette);
        } catch(Exception e) {
            throw new RuntimeException("Unexpected database server error");
        }
    }

    @Override
    public void deleteById(Long id){
        try {
            findById(id).ifPresent(this.recetteRepository::delete);
        } catch(Exception e) {
            throw new RuntimeException("Unexpected database server error");
        }
    }
}
