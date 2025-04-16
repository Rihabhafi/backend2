package com.PFE.Backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PFE.Backend.DTO.InterventionDTO;
import com.PFE.Backend.service.InterventionService;

@CrossOrigin(origins = "http://localhost:3002/")
@RestController
@RequestMapping("/interventions")
public class InterventionController {
     private final InterventionService interventionService;

    public InterventionController(InterventionService interventionService) {
        this.interventionService = interventionService;
    }

    // 🔹 1. Ajouter une intervention
    @PostMapping("/add")
    public InterventionDTO createIntervention(@RequestBody InterventionDTO interventionDTO) {
        return interventionService.createIntervention(interventionDTO);
    }

    // 🔹 2. Récupérer une intervention par ID
    @GetMapping("/{id}")
    public InterventionDTO getInterventionById(@PathVariable Integer id) {
        return interventionService.getInterventionById(id);
    }

    // 🔹 3. Récupérer toutes les interventions
    @GetMapping
    public List<InterventionDTO> getAllInterventions() {
        return interventionService.getAllInterventions();
    }

    // 🔹 4. Modifier une intervention
    @PutMapping("/{id}")
    public InterventionDTO updateIntervention(@PathVariable Integer id, @RequestBody InterventionDTO interventionDTO) {
        return interventionService.updateIntervention(id, interventionDTO);
    }

    // 🔹 5. Supprimer une intervention
    @DeleteMapping("/{id}")
    public void deleteIntervention(@PathVariable Integer id) {
        interventionService.deleteIntervention(id);
    }

}
