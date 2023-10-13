package com.wildcodeschool.milkshake.repository;

import com.wildcodeschool.milkshake.entity.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetteRepository extends JpaRepository<Recette, Long> {
}
