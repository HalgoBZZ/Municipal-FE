package com.halgo.municipalpfe.modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Offre implements Serializable {

    private Long id_offre;

    private String titre_offre;

    private String description_offre;

    private EtatOffre etat;

    private String date_ajout;

    private String date_modification;

    private double prix_offre;

    private Propriete propriete;


    public Offre() {
        super();
    }
    public Offre(String titre, String description_offre, EtatOffre etat) {
        this.titre_offre = titre;
        this.description_offre = description_offre;
        this.etat = etat;
    }

    public Offre(Long id_offre, String titre_offre, String description_offre, EtatOffre etat, String date_ajout,
                 String date_modification, double prix_offre) {
        super();
        this.id_offre = id_offre;
        this.titre_offre = titre_offre;
        this.description_offre = description_offre;
        this.etat = etat;
        this.date_ajout = date_ajout;
        this.date_modification = date_modification;
        this.prix_offre = prix_offre;
    }

    public Long getId_offre() {
        return id_offre;
    }

    public void setId_offre(Long id_offre) {
        this.id_offre = id_offre;
    }

    public String getTitre_offre() {
        return titre_offre;
    }

    public void setTitre_offre(String titre_offre) {
        this.titre_offre = titre_offre;
    }

    public String getDescription_offre() {
        return description_offre;
    }

    public void setDescription_offre(String description_offre) {
        this.description_offre = description_offre;
    }

    public EtatOffre getEtat() {
        return etat;
    }

    public void setEtat(EtatOffre etat) {
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

    public double getPrix_offre() {
        return prix_offre;
    }

    public void setPrix_offre(double prix_offre) {
        this.prix_offre = prix_offre;
    }

    public Propriete getPropriete() {
        return propriete;
    }

    public void setPropriete(Propriete propriete) {
        this.propriete = propriete;
    }


}
