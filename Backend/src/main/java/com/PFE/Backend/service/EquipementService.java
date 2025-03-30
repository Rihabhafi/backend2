package com.PFE.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PFE.Backend.DTO.EquipementDTO;
import com.PFE.Backend.mapper.EquipementMapper;
import com.PFE.Backend.models.Equipement;
import com.PFE.Backend.models.EtatEquipement;
import com.PFE.Backend.models.User;
import com.PFE.Backend.repository.EquipementRepository;
import com.PFE.Backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipementService {

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EquipementMapper equipementMapper;  // Injection du Mapper

    public EquipementDTO createEquipement(EquipementDTO equipementDTO) {
        // Mapper le DTO en entité Equipement
        Equipement equipement = equipementMapper.equipementDTOToEquipement(equipementDTO);
    
        // Vérifier l'état et définir l'utilisateur en conséquence
        if ("EN_STOCK".equalsIgnoreCase(equipementDTO.getEtat())) {
            // Si l'équipement est "EN_STOCK", il ne doit pas avoir d'utilisateur
            equipement.setUser(null);
        } else if ("ACTIF".equalsIgnoreCase(equipementDTO.getEtat())) {
            // Si l'équipement est "ACTIF", un utilisateur doit être affecté
            if (equipementDTO.getUser() == null || equipementDTO.getUser().getId() == null) {
                throw new RuntimeException("Un utilisateur doit être affecté pour un équipement ACTIF.");
            }
            
            // Récupérer l'utilisateur par son ID
            User user = userRepository.findById(equipementDTO.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    
            // Associer l'utilisateur à l'équipement
            equipement.setUser(user);
        } else {
            throw new RuntimeException("État non valide. Les valeurs autorisées sont 'EN_STOCK' ou 'ACTIF'.");
        }
    
        // Sauvegarder l'équipement
        Equipement savedEquipement = equipementRepository.save(equipement);
    
        // Mapper l'équipement sauvegardé en DTO et le retourner
        return equipementMapper.equipementToEquipementDTO(savedEquipement);
    }

    // Récupérer tous les équipements
    public List<EquipementDTO> getAllEquipements() {
        List<Equipement> equipements = equipementRepository.findAll();
        return equipements.stream()
                .map(equipementMapper::equipementToEquipementDTO)  // Mapper chaque équipement
                .collect(Collectors.toList());
    }

    // Récupérer un équipement par ID
    public EquipementDTO getEquipementById(int id) {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Équipement non trouvé"));
        return equipementMapper.equipementToEquipementDTO(equipement);
    }

    // Mettre à jour un équipement
    public EquipementDTO updateEquipement(int id, EquipementDTO equipementDTO) {
        // Trouver l'équipement existant par son ID
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Équipement non trouvé"));
    
        // Mettre à jour les informations de l'équipement
        equipement.setNom(equipementDTO.getNom());
        equipement.setType(equipementDTO.getType());
        equipement.setMarque(equipementDTO.getMarque());
        equipement.setNumeroSerie(equipementDTO.getNumeroSerie());
    
        try {
            // Mise à jour de l'état de l'équipement
            equipement.setEtat(EtatEquipement.valueOf(equipementDTO.getEtat().toUpperCase())); // Uppercase pour éviter les erreurs de casse
        } catch (IllegalArgumentException e) {
            // Gérer les erreurs de valeur invalide pour l'état
            throw new RuntimeException("Valeur invalide pour l'état de l'équipement : " + equipementDTO.getEtat());
        }
    
        // Gestion de l'affectation de l'utilisateur
        if (equipementDTO.getEtat().equalsIgnoreCase("ACTIF")) {
            if (equipementDTO.getUser() == null || equipementDTO.getUser().getId() == null) {
                throw new RuntimeException("Vous devez sélectionner un utilisateur pour un équipement ACTIF.");
            }
    
            // Si l'équipement est actif, nous affectons un utilisateur
            User user = userRepository.findById(equipementDTO.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID : " + equipementDTO.getUser().getId()));
            equipement.setUser(user);
        } else if (equipementDTO.getEtat().equalsIgnoreCase("EN_STOCK")) {
            // Si l'équipement est en stock, on retire l'utilisateur s'il y en a un
            equipement.setUser(null); // Pas d'utilisateur affecté pour un équipement en stock
        }
    
        // Sauvegarde des modifications
        Equipement updatedEquipement = equipementRepository.save(equipement);
    
        // Retourner l'équipement mis à jour sous forme de DTO
        return equipementMapper.equipementToEquipementDTO(updatedEquipement);
    }
    

    // Supprimer un équipement
    public void deleteEquipement(int id) {
        equipementRepository.deleteById(id);
    }
}