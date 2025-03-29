package com.PFE.Backend.controller;



import com.PFE.Backend.models.*;
import com.PFE.Backend.service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipements")
@CrossOrigin(origins = "http://localhost:3002") // Autorise les requêtes du frontend React
public class EquipementController {

    @Autowired
    private EquipementService equipementService;

    @GetMapping
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/{id}")
    public Optional<Equipement> getEquipementById(@PathVariable Long id) {
        return equipementService.getEquipementById(id);
    }

    @PostMapping
    public Equipement addEquipement(@RequestBody Equipement equipement) {
        return equipementService.addEquipement(equipement);
    }

    @PutMapping("/{id}")
    public Equipement updateEquipement(@PathVariable Long id, @RequestBody Equipement equipement) {
        return equipementService.updateEquipement(id, equipement);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipement(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
    }
}