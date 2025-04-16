package com.PFE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PFE.Backend.models.Intervention;
import java.util.List;

public interface InterventionRepository extends JpaRepository<Intervention, Integer> {
     // Trouver toutes les interventions d’un technicien
     List<Intervention> findByTechnicienId(Integer technicienId);

     // Trouver les interventions d’un ticket
     List<Intervention> findByTicketId(Integer ticketId);

}
