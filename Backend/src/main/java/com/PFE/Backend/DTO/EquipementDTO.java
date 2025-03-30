package com.PFE.Backend.DTO;


public class EquipementDTO {
    private Integer id;
    private String nom;
    private String type;
    private String marque;
    private String numeroSerie;
    private String etat;
    private UserDTO user; 

    public EquipementDTO(Integer id, String nom, String type, String marque, String numeroSerie, String etat,
            UserDTO user) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.marque = marque;
        this.numeroSerie = numeroSerie;
        this.etat = etat;
        this.user = user;
    }

    // Constructeurs
    public EquipementDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    
    
    
}
