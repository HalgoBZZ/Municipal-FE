package com.halgo.municipalpfe.modals;

import java.time.LocalDateTime;

public class Offre {

    private String id;
    private String titre;
    private String description;
    private String etat;
    private LocalDateTime ajout;
    private LocalDateTime modification;
    private double prix;

    public Offre(){
    }

    public Offre(String titre, String description, String etat) {
        this.titre = titre;
        this.description = description;
        this.etat = etat;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre){
        this.titre = titre;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public LocalDateTime getAjout() {
        return ajout;
    }

    public void setAjout(LocalDateTime ajout) {
        this.ajout = ajout;
    }

    public LocalDateTime getModification() {
        return modification;
    }

    public void setModification(LocalDateTime modification) {
        this.modification = modification;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
