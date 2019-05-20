package com.halgo.municipalpfe.modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Payement implements Serializable {

    private Long id;

    private LocalDate date_debut;

    private LocalDate date_fin;

    private String etat;

    private LocalDate date_ajout;

    private LocalDate date_modification;

    private List<Avertissement> avertissements = new ArrayList<>();

    private Contrat contrat;

    private List<Notification> notifications = new ArrayList<>();


    public Payement() {
        super();
    }

    public Payement(LocalDate debut, LocalDate fin, String etat) {

    }

    public Payement(Long id, LocalDate date_debut, LocalDate date_fin, String etat, LocalDate date_ajout, LocalDate date_modification) {
        super();
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.date_ajout = date_ajout;
        this.date_modification = date_modification;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public LocalDate getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(LocalDate date_ajout) {
        this.date_ajout = date_ajout;
    }

    public LocalDate getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(LocalDate date_modification) {
        this.date_modification = date_modification;
    }

    public List<Avertissement> getAvertissements() {
        return avertissements;
    }

    public void setAvertissements(List<Avertissement> avertissements) {
        this.avertissements = avertissements;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

}
