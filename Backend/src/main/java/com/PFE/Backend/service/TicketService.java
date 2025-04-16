package com.PFE.Backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.PFE.Backend.DTO.TicketDTO;
import com.PFE.Backend.mapper.TicketMapper;
import com.PFE.Backend.models.Equipement;
import com.PFE.Backend.models.EtatTicket;
import com.PFE.Backend.models.Ticket;
import com.PFE.Backend.models.TypeTicket;
import com.PFE.Backend.models.User;
import com.PFE.Backend.repository.EquipementRepository;
import com.PFE.Backend.repository.TicketRepository;
import com.PFE.Backend.repository.UserRepository;

@Service
public class TicketService {
     private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;
    private final EquipementRepository equipementRepository;


    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper, UserRepository userRepository,
            EquipementRepository equipementRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.userRepository = userRepository;
        this.equipementRepository = equipementRepository;
    }

    // 🔹 1. Créer un ticket
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.ticketDTOToTicket(ticketDTO);

        // Récupérer l'utilisateur actuellement connecté
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Associer l'utilisateur
        ticket.setUser(currentUser);
         // Associer l'équipement si fourni
          if (ticketDTO.getEquipementId() != null) {
            Equipement equipement = equipementRepository.findById(ticketDTO.getEquipementId())
                    .orElseThrow(() -> new RuntimeException("Équipement non trouvé avec ID : " + ticketDTO.getEquipementId()));
            ticket.setEquipement(equipement);
        }

        // Définir le type et statut depuis les String du DTO
        ticket.setType(TypeTicket.valueOf(ticketDTO.getType()));
        ticket.setStatut(EtatTicket.valueOf(ticketDTO.getStatut()));

        // Sauvegarde
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.ticketToTicketDTO(savedTicket);
    }

    // 🔹 2. Obtenir un ticket par ID
    public TicketDTO getTicketById(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé avec ID : " + id));
        return ticketMapper.ticketToTicketDTO(ticket);
    }

    // 🔹 3. Obtenir tous les tickets
    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::ticketToTicketDTO)
                .collect(Collectors.toList());
    }

    // 🔹 4. Mettre à jour un ticket
    public TicketDTO updateTicket(Integer id, TicketDTO ticketDTO) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé avec ID : " + id));

        existingTicket.setDescription(ticketDTO.getDescription());

        try {
            existingTicket.setStatut(EtatTicket.valueOf(ticketDTO.getStatut()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Statut invalide : " + ticketDTO.getStatut());
        }

        Ticket updatedTicket = ticketRepository.save(existingTicket);
        return ticketMapper.ticketToTicketDTO(updatedTicket);
    }

    // 🔹 5. Supprimer un ticket
    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }

   // 🔹 6. Prendre en charge un ticket par le technicien connecté
public TicketDTO assignTicketToCurrentTechnicien(Integer ticketId) {
    Ticket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(() -> new RuntimeException("Ticket non trouvé"));

    // Vérifie que le ticket n'est pas déjà assigné
    if (ticket.getAssignedUser() != null) {
        throw new RuntimeException("Le ticket est déjà assigné à un technicien.");
    }

    // Récupérer l'utilisateur connecté
    String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
    User currentUser = userRepository.findByUsername(currentUsername)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    // Vérifier le rôle TECHNICIEN (optionnel mais recommandé)
    if (!currentUser.getRole().name().equals("TECHNICIEN")) {
        throw new RuntimeException("Seuls les techniciens peuvent prendre en charge un ticket.");
    }

    // Assigner le ticket
    ticket.setAssignedUser(currentUser);
    ticket.setStatut(EtatTicket.EN_COURS); // facultatif : changer le statut

    Ticket saved = ticketRepository.save(ticket);
    return ticketMapper.ticketToTicketDTO(saved);
}

// 🔹 7. Compter les tickets avec statut EN_ATTENTE
public long countTicketsEnAttente() {
    return ticketRepository.countByStatut(EtatTicket.EN_ATTENTE);
}


}
