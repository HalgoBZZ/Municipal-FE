package com.halgo.municipalpfe.modals;

import java.time.LocalDate;

public class Client {
    private String id;
    private String nom;
    private String prenom;
    private LocalDate naissance;
    private LocalDate ajout;
    private LocalDate modification;
    private int cin;

    public Client(String id, String nom, String prenom, LocalDate naissance, int cin, LocalDate ajout, LocalDate modification) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.cin = cin;
        this.ajout = ajout;
        this.modification = modification;
    }
    public Client(String nom, String prenom, int cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
    }

    public Client(String nom, String prenom, LocalDate naissance, int cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.cin = cin;
    }

    public Client() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
