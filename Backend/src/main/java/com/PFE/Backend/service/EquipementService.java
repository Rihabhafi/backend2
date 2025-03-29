package com.PFE.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PFE.Backend.models.Equipement;

import com.PFE.Backend.repository.EquipementRepository;


import java.util.List;
import java.util.Optional;

@Service
public class EquipementService {

    @Autowired
    private EquipementRepository equipementRepository;

    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    public Optional<Equipement> getEquipementById(Long id) {
        return equipementRepository.findById(id);
    }

    public Equipement addEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    public Equipement updateEquipement(Long id, Equipement equipementDetails) {
        Equipement equipement = equipementRepository.findById(id).orElseThrow(() -> new RuntimeException("Équipement non trouvé"));
        equipement.setNom(equipementDetails.getNom());
        equipement.setType(equipementDetails.getType());
        equipement.setMarque(equipementDetails.getMarque());
        equipement.setNumeroSerie(equipementDetails.getNumeroSerie());
        return equipementRepository.save(equipement);
    }

    public void deleteEquipement(Long id) {
        equipementRepository.deleteById(id);
    }
}