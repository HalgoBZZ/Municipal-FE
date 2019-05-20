package com.halgo.municipalpfe.modals;

import java.time.LocalDate;

public class Avertissement {

    private Long id_avert;

    private String titre_avert;

    private String contenu;

    private LocalDate date_ajout;

    private LocalDate date_modification;

    private Client client;

    private Payement payement;

    public Avertissement() {
        super();
    }

    public Avertissement(Long id_avert, String titre_avert, String contenu, LocalDate date_ajout,
                         LocalDate date_modification) {
        super();
        this.id_avert = id_avert;
        this.titre_avert = titre_avert;
        this.contenu = contenu;
        this.date_ajout = date_ajout;
        this.date_modification = date_modification;
    }

    public Long getId_avert() {
        return id_avert;
    }

    public void setId_avert(Long id_avert) {
        this.id_avert = id_avert;
    }

    public String getTitre_avert() {
        return titre_avert;
    }

    public void setTitre_avert(String titre_avert) {
        this.titre_avert = titre_avert;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Payement getPayement() {
        return payement;
    }

    public void setPayement(Payement payement) {
        this.payement = payement;
    }


}

