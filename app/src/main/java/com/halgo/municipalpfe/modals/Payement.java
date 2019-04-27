package com.halgo.municipalpfe.modals;

import java.time.LocalDate;

public class Payement {
    private String id;
    private LocalDate debut;
    private LocalDate fin;
    private String etat;
    private LocalDate ajout;
    private LocalDate modification;

    public Payement() {
    }

    public Payement(String id, LocalDate debut, LocalDate fin, String etat, LocalDate ajout, LocalDate modification) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.etat = etat;
        this.ajout = ajout;
        this.modification = modification;
    }

    public Payement(LocalDate debut, LocalDate fin, String etat, LocalDate ajout, LocalDate modification) {
        this.debut = debut;
        this.fin = fin;
        this.etat = etat;
        this.ajout = ajout;
        this.modification = modification;
    }

    public Payement(LocalDate debut, LocalDate fin, String etat) {
        this.debut = debut;
        this.fin = fin;
        this.etat = etat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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
