package com.halgo.municipalpfe.modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Payement implements Serializable {

    private Long id;

    private String date_debut;

    private String date_fin;

    private String etat;

    private String date_ajout;

    private String date_modification;

    private List<Avertissement> avertissements = new ArrayList<>();

    private Contrat contrat;

    private List<Notification> notifications = new ArrayList<>();


    public Payement() {
        super();
    }

    public Payement(LocalDate debut, LocalDate fin, String etat) {

    }

    public Payement(Long id, String date_debut, String date_fin, String etat, String date_ajout, String date_modification) {
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

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(String date_modification) {
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
