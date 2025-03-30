package com.PFE.Backend.models;


import jakarta.persistence.*;

@Entity
@Table(name = "equipements")
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="nom")
    private String nom;
    @Column(name ="type")
    private String type;
    @Column(name ="marque")
    private String marque;
    @Column(name ="numeroSerie")
    private String numeroSerie;
    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private EtatEquipement etat; // Nouvel attribut État

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; 

    // Constructeurs
    public Equipement() {}

    public Equipement(String nom, String type, String marque, String numeroSerie, EtatEquipement etat, User user) {
        this.nom = nom;
        this.type = type;
        this.marque = marque;
        this.numeroSerie = numeroSerie;
        this.etat = etat;
        this.user = user;
    }

    public EtatEquipement getEtat() {
        return etat;
    }

    public void setEtat(EtatEquipement etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
}
