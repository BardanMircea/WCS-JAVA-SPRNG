package com.wildcodeschool.milkshake.service;

import com.wildcodeschool.milkshake.entity.Vendeur;

import java.util.List;
import java.util.Optional;

public interface VendeurService {
    public List<Vendeur> findAll();

    public Optional<Vendeur> findById(long id);

    public void saveOrUpdate(Vendeur vendeur, Long id);

    public void deleteById(Long id);
}
