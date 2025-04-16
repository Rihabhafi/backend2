package com.PFE.Backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interventions")
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technicien_id")
    private User technicien;  // Technicien qui effectue l'intervention

    @Column(name = "description")
    private String description;

    @Column(name = "date_intervention")
    private LocalDateTime dateIntervention;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipement_id")
    private Equipement equipement;

    // Constructeur, Getters et Setters
    public Intervention() {}

   
    public Intervention(int id, Ticket ticket, User technicien, String description, LocalDateTime dateIntervention,
            Equipement equipement) {
        this.id = id;
        this.ticket = ticket;
        this.technicien = technicien;
        this.description = description;
        this.dateIntervention = dateIntervention;
        this.equipement = equipement;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public User getTechnicien() { return technicien; }
    public void setTechnicien(User technicien) { this.technicien = technicien; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDateIntervention() { return dateIntervention; }
    public void setDateIntervention(LocalDateTime dateIntervention) { this.dateIntervention = dateIntervention; }


    public Equipement getEquipement() {
        return equipement;
    }


    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }
}