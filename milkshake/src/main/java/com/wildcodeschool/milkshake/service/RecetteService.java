package com.wildcodeschool.milkshake.service;

import com.wildcodeschool.milkshake.entity.Recette;
import com.wildcodeschool.milkshake.repository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface RecetteService {

    public List<Recette> findAll();

    public Optional<Recette> findById(long id);

    public void save(Recette recette);

    public void deleteById(Long id);
}
