package com.PFE.Backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.PFE.Backend.DTO.InterventionDTO;
import com.PFE.Backend.mapper.InterventionMapper;
import com.PFE.Backend.models.Intervention;
import com.PFE.Backend.models.Ticket;
import com.PFE.Backend.models.User;
import com.PFE.Backend.repository.EquipementRepository;
import com.PFE.Backend.repository.InterventionRepository;
import com.PFE.Backend.repository.TicketRepository;
import com.PFE.Backend.repository.UserRepository;

@Service
public class InterventionService {
       private final InterventionRepository interventionRepository;
    private final InterventionMapper interventionMapper;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EquipementRepository equipementRepository;

    

    public InterventionService(InterventionRepository interventionRepository, InterventionMapper interventionMapper,
            TicketRepository ticketRepository, UserRepository userRepository,
            EquipementRepository equipementRepository) {
        this.interventionRepository = interventionRepository;
        this.interventionMapper = interventionMapper;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.equipementRepository = equipementRepository;
    }

    // 🔹 1. Créer une intervention
public InterventionDTO createIntervention(InterventionDTO interventionDTO) {
    // Mapper le DTO en entité Intervention (technicien, ticket, équipement seront associés manuellement)
    Intervention intervention = interventionMapper.interventionDTOToIntervention(interventionDTO);

    // 🔐 Récupérer l'utilisateur actuellement connecté (le technicien)
    String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
    User currentUser = userRepository.findByUsername(currentUsername)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    intervention.setTechnicien(currentUser);

    // 🎫 Associer l'intervention au ticket existant
    Ticket ticket = ticketRepository.findById(interventionDTO.getTicketId())
            .orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    intervention.setTicket(ticket);

    // 🖥️ Associer l'équipement (si un ID est fourni dans le DTO)
    if (interventionDTO.getEquipementId() != null) {
        intervention.setEquipement(
            equipementRepository.findById(interventionDTO.getEquipementId())
                .orElseThrow(() -> new RuntimeException("Équipement non trouvé"))
        );
    }

    // 🔧 Changer l'état du ticket à RESOLU
    ticket.setStatut(com.PFE.Backend.models.EtatTicket.RESOLU);
    ticketRepository.save(ticket); // 🔁 Sauvegarder le ticket mis à jour

    // 💾 Sauvegarder l'intervention
    Intervention savedIntervention = interventionRepository.save(intervention);

    // 🔄 Retourner le DTO correspondant
    return interventionMapper.interventionToInterventionDTO(savedIntervention);
}


    // 🔹 2. Obtenir une intervention par ID
    public InterventionDTO getInterventionById(Integer id) {
        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervention non trouvée avec ID : " + id));
        return interventionMapper.interventionToInterventionDTO(intervention);
    }

    // 🔹 3. Obtenir toutes les interventions
    public List<InterventionDTO> getAllInterventions() {
        List<Intervention> interventions = interventionRepository.findAll();
        return interventions.stream()
                .map(interventionMapper::interventionToInterventionDTO)
                .collect(Collectors.toList());
    }

    // 🔹 4. Mettre à jour une intervention
    public InterventionDTO updateIntervention(Integer id, InterventionDTO interventionDTO) {
        // Trouver l'intervention existante par ID
        Intervention existingIntervention = interventionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervention non trouvée avec ID : " + id));

        // Mettre à jour les informations de l'intervention
        existingIntervention.setDateIntervention(interventionDTO.getDateIntervention());
        existingIntervention.setDescription(interventionDTO.getDescription());

        // Récupérer l'utilisateur actuellement connecté
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Mettre à jour le technicien affecté
        existingIntervention.setTechnicien(currentUser);

        // Sauvegarder l'intervention mise à jour
        Intervention updatedIntervention = interventionRepository.save(existingIntervention);

        // Retourner l'intervention mise à jour sous forme de DTO
        return interventionMapper.interventionToInterventionDTO(updatedIntervention);
    }

    // 🔹 5. Supprimer une intervention
    public void deleteIntervention(Integer id) {
        interventionRepository.deleteById(id);
    }

}
