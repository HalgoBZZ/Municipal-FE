package com.halgo.municipalpfe.modals;

import java.time.LocalDate;

public class Propertie {


    private String id;
    private double surface;
    private String adresse;
    private String type;
    private LocalDate ajout;
    private LocalDate modification;

    public Propertie() {
    }

    public Propertie(String id, double surface, String adresse, String type, LocalDate ajout, LocalDate modification) {
        this.id = id;
        this.surface = surface;
        this.adresse = adresse;
        this.type = type;
        this.ajout = ajout;
        this.modification = modification;
    }

    public Propertie(double surface, String adresse, String type, LocalDate ajout, LocalDate modification) {
        this.surface = surface;
        this.adresse = adresse;
        this.type = type;
        this.ajout = ajout;
        this.modification = modification;
    }

    public Propertie(double surface, String adresse, String type) {
        this.surface = surface;
        this.adresse = adresse;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
