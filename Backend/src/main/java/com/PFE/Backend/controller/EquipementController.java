package com.PFE.Backend.controller;



import com.PFE.Backend.DTO.EquipementDTO;
import com.PFE.Backend.models.*;
import com.PFE.Backend.service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipements")
@CrossOrigin(origins = "http://localhost:3002") // Autorise les requêtes du frontend React
public class EquipementController {

    
    @Autowired
    private EquipementService equipementService;

    // Créer un équipement
    @PostMapping
    public ResponseEntity<EquipementDTO> createEquipement(@RequestBody EquipementDTO equipementDTO) {
        try {
            EquipementDTO createdEquipement = equipementService.createEquipement(equipementDTO);
            return new ResponseEntity<>(createdEquipement, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Récupérer tous les équipements
    @GetMapping
    public List<EquipementDTO> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    // Récupérer un équipement par ID
    @GetMapping("/{id}")
    public ResponseEntity<EquipementDTO> getEquipementById(@PathVariable int id) {
        EquipementDTO equipementDTO = equipementService.getEquipementById(id);
        return ResponseEntity.ok(equipementDTO);
    }

    // Mettre à jour un équipement
    @PutMapping("/{id}")
    public EquipementDTO updateEquipement(@PathVariable Integer id, @RequestBody EquipementDTO equipementDTO) {
        return equipementService.updateEquipement(id, equipementDTO);
    }

    // Supprimer un équipement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable int id) {
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }
}