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
        return this.recetteRepository.findAll();
    }

    @Override
    public Optional<Recette> findById(long id){
        return this.recetteRepository.findById(id);
    }

    @Override
    public void saveOrUpdate(Recette recette, Long id){
        Optional.ofNullable(id).ifPresent(recette::setId);
        this.recetteRepository.save(recette);
    }

    @Override
    public void deleteById(Long id){
        findById(id).ifPresent(this.recetteRepository::delete);
    }
}
