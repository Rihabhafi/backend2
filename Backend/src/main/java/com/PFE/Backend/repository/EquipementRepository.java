package com.PFE.Backend.repository;

import com.PFE.Backend.models.Equipement;
import com.PFE.Backend.models.EtatEquipement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Integer> {
    List<Equipement> findByEtat(EtatEquipement etat);
    List<Equipement> findByUserId(int userId);

}