package com.wildcodeschool.milkshake.service;

import com.wildcodeschool.milkshake.entity.Vendeur;
import com.wildcodeschool.milkshake.repository.VendeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendeurServiceImpl implements VendeurService{
    private VendeurRepository vendeurRepository;

    public VendeurServiceImpl(@Autowired VendeurRepository vendeurRepository){
        this.vendeurRepository = vendeurRepository;
    }

    @Override
    public List<Vendeur> findAll(){
        return this.vendeurRepository.findAll();
    }

    @Override
    public Optional<Vendeur> findById(long id){
        return this.vendeurRepository.findById(id);
    }

    @Override
    public void save(Vendeur vendeur){
        this.vendeurRepository.save(vendeur);
    }

    @Override
    public void deleteById(Long id){
        Optional<Vendeur> toDelete = findById(id);
        toDelete.ifPresent(vendeur -> this.vendeurRepository.delete(vendeur));
    }
}
