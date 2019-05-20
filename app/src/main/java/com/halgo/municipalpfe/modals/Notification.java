package com.halgo.municipalpfe.modals;

import java.io.Serializable;
import java.time.LocalDate;

public class Notification implements Serializable {

    private Long id_notification;

    private int nb_jour;

    private String titre_notification;

    private String description_notification;

    private LocalDate date_ajout;

    private LocalDate date_modification;

    private Payement payement;

    public Notification() {
        super();
    }

    public Notification(Long id_notification, int nb_jour, String titre_notification, String description_notification,
                        LocalDate date_ajout, LocalDate date_modification) {
        super();
        this.id_notification = id_notification;
        this.nb_jour = nb_jour;
        this.titre_notification = titre_notification;
        this.description_notification = description_notification;
        this.date_ajout = date_ajout;
        this.date_modification = date_modification;
    }

    public Long getId_notification() {
        return id_notification;
    }

    public void setId_notification(Long id_notification) {
        this.id_notification = id_notification;
    }

    public int getNb_jour() {
        return nb_jour;
    }

    public void setNb_jour(int nb_jour) {
        this.nb_jour = nb_jour;
    }

    public String getTitre_notification() {
        return titre_notification;
    }

    public void setTitre_notification(String titre_notification) {
        this.titre_notification = titre_notification;
    }

    public String getDescription_notification() {
        return description_notification;
    }

    public void setDescription_notification(String description_notification) {
        this.description_notification = description_notification;
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

    public Payement getPayement() {
        return payement;
    }

    public void setPayement(Payement payement) {
        this.payement = payement;
    }


}
