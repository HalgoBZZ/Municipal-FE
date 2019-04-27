package com.halgo.municipalpfe.modals;

import java.time.LocalDate;

public class Contrat {

    private String id;
    private String titre;
    private LocalDate debut;
    private LocalDate fin;
    private double prix;
    private LocalDate ajout;
    private LocalDate modification;

    public Contrat() {
    }

    public Contrat(String titre, LocalDate debut, LocalDate fin, double prix, LocalDate ajout, LocalDate modification) {
        this.titre = titre;
        this.debut = debut;
        this.fin = fin;
        this.prix = prix;
        this.ajout = ajout;
        this.modification = modification;
    }

    public Contrat(String id, String titre, LocalDate debut, LocalDate fin, double prix, LocalDate ajout, LocalDate modification) {
        this.id = id;
        this.titre = titre;
        this.debut = debut;
        this.fin = fin;
        this.prix = prix;
        this.ajout = ajout;
        this.modification = modification;
    }

    public Contrat(String titre, double prix) {
        this.titre = titre;
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LocalDate getAjout() {
        return ajout;
    }

    public void setAjout(LocalDate ajout) {
        this.ajout = ajout;
    }

    public LocalDate getModification() {
        return modification;
    }

    public void setModification(LocalDate modification) {
        this.modification = modification;
    }
}
