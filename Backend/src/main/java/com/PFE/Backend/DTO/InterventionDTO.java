package com.PFE.Backend.DTO;

import java.time.LocalDateTime;

public class InterventionDTO {
     private Integer id;
    private String description;
    private LocalDateTime dateIntervention;
    private Integer technicienId;
    private Integer ticketId;
    private Integer equipementId;
    private String technicienusername; 
    

      // Getters et setters

      public Integer getId() {
        return id;
    }

    public Integer getEquipementId() {
        return equipementId;
    }

      public void setEquipementId(Integer equipementId) {
          this.equipementId = equipementId;
      }

      public String gettechnicienusername() {
          return technicienusername;
      }

      public void settechnicienusername(String technicienusername) {
          this.technicienusername = technicienusername;
      }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(LocalDateTime dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public Integer getTechnicienId() {
        return technicienId;
    }

    public void setTechnicienId(Integer technicienId) {
        this.technicienId = technicienId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}
