package com.wildcodeschool.milkshake.repository;

import com.wildcodeschool.milkshake.entity.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendeurRepository extends JpaRepository<Vendeur, Long> {
}
