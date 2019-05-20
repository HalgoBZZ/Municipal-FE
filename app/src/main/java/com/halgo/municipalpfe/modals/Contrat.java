package com.halgo.municipalpfe.modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contrat implements Serializable {

    private Long id_contrat;

    private String titre_contrat;

    private String date_debut;

    private String date_fin;

    private double prix;

    private String date_ajout;

    private String date_modification;

    private List<Payement> payements = new ArrayList<>();

    private Propriete propriete;


    public Contrat() {
        super();
    }

    public Contrat(String titre, double prix) {
        this.titre_contrat = titre;
        this.prix = prix;

    }
    public Contrat(Long id_contrat, String titre_contrat, String date_debut, String date_fin, double prix,
                   String date_ajout, String date_modification) {
        super();
        this.id_contrat = id_contrat;
        this.titre_contrat = titre_contrat;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.date_ajout = date_ajout;
        this.date_modification = date_modification;
    }

    public Long getId_contrat() {
        return id_contrat;
    }

    public void setId_contrat(Long id_contrat) {
        this.id_contrat = id_contrat;
    }

    public String getTitre_contrat() {
        return titre_contrat;
    }

    public void setTitre_contrat(String titre_contrat) {
        this.titre_contrat = titre_contrat;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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

    public List<Payement> getPayement() {
        return payements;
    }

    public void setPayement(List<Payement> payements) {
        this.payements = payements;
    }

    public Propriete getPropriete() {
        return propriete;
    }

    public void setPropriete(Propriete propriete) {
        this.propriete = propriete;
    }
}
