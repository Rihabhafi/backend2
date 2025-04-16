package com.PFE.Backend.DTO;

import java.sql.Date;
import java.time.LocalDateTime;

import com.PFE.Backend.models.NiveauUrgence;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;

public class TicketDTO {
    private Integer id;
    private String type; // DEMANDE, INCIDENT
    private String description;
  
    private String niveauUrgence;

    private String statut; // EN_ATTENTE, EN_COURS, RESOLU
    private Integer userId; // ID de l'utilisateur qui a créé le ticket
    private Integer equipementId; // ID de l'équipement concerné (peut être null)
    private Integer assignedUserId; // ID de l'utilisateur assigné (technicien)
    private String objet;
   
    private String username; 
    private String equipementnom; 
    private String assignedname;
    private LocalDateTime datecreation;
    
     
    public LocalDateTime getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public String getEquipementnom() {
        return equipementnom;
    }

    public void setEquipementnom(String equipementnom) {
        this.equipementnom = equipementnom;
    }

    public String getAssignedname() {
        return assignedname;
    }

    public void setAssignedname(String assignedname) {
        this.assignedname = assignedname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEquipementId() {
        return equipementId;
    }

    public void setEquipementId(Integer equipementId) {
        this.equipementId = equipementId;
    }

    public Integer getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Integer assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public String getNiveauUrgence() {
        return niveauUrgence;
    }

    public void setNiveauUrgence(String niveauUrgence) {
        this.niveauUrgence = niveauUrgence;
    }

}
