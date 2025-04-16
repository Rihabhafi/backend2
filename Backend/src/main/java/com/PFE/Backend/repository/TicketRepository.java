package com.PFE.Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PFE.Backend.models.EtatTicket;
import com.PFE.Backend.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
     // Trouver tous les tickets d’un utilisateur donné
     List<Ticket> findByUserId(Integer userId);
    
     // (Optionnel) Trouver les tickets par statut
     List<Ticket> findByStatut(String statut);
     long countByStatut(EtatTicket statut);

}
