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

    // Constructeurs
    public Equipement() {}

    public Equipement(String nom, String type, String marque, String numeroSerie) {
        this.nom = nom;
        this.type = type;
        this.marque = marque;
        this.numeroSerie = numeroSerie;
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
