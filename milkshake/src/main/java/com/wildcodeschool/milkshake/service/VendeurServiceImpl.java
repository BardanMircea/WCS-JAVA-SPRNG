package com.wildcodeschool.milkshake.service;
import com.wildcodeschool.milkshake.entity.Vendeur;
import com.wildcodeschool.milkshake.repository.VendeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendeurServiceImpl implements VendeurService{
    private final VendeurRepository vendeurRepository;

    public VendeurServiceImpl(@Autowired VendeurRepository vendeurRepository){
        this.vendeurRepository = vendeurRepository;
    }

    @Override
    public List<Vendeur> findAll(){
        try {
            return this.vendeurRepository.findAll();
        } catch(Exception e) {
            throw new RuntimeException("Unexpected database server error");
        }
    }

    @Override
    public Optional<Vendeur> findById(long id){
        try {
            return this.vendeurRepository.findById(id);
        } catch(Exception e) {
            throw new RuntimeException("Unexpected database server error");
        }
    }

    @Override
    public void saveOrUpdate(Vendeur vendeur, Long id){
        try {
            Optional.ofNullable(id).ifPresent(vendeur::setId);
            this.vendeurRepository.save(vendeur);
        } catch(Exception e) {
            throw new RuntimeException("Unexpected database server error");
        }
    }

    @Override
    public void deleteById(Long id){
        try {
            findById(id).ifPresent(this.vendeurRepository::delete);
        } catch(Exception e) {
            throw new RuntimeException("Unexpected database server error");
        }
    }
}
