package com.wildcodeschool.milkshake.service;

import com.wildcodeschool.milkshake.entity.Recette;

import java.util.List;
import java.util.Optional;

public interface RecetteService {

    public List<Recette> findAll();

    public Optional<Recette> findById(long id);

    public void saveOrUpdate(Recette recette, Long id);

    public void deleteById(Long id);
}
