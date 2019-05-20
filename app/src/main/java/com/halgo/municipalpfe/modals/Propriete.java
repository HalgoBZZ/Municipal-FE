package com.halgo.municipalpfe.modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Propriete implements Serializable {

    private Long id_prop;

    private double surface_prop;

    private String adresse;

    private String type;

    private String date_ajout;

    private String date_modification;

    private List<Offre> offres;

    private Contrat contrat;

    private Client client;

    public Propriete() {
        super();
    }

    public Propriete(double surface, String adresse, String type) {
        this.surface_prop = surface;
        this.adresse= adresse;
        this.type = type;
    }

    public Propriete(Long id_prop, double surface_prop, String adresse, String type, String date_ajout,
                     String date_modification) {
        super();
        this.id_prop = id_prop;
        this.surface_prop = surface_prop;
        this.adresse = adresse;
        this.type = type;
        this.date_ajout = date_ajout;
        this.date_modification = date_modification;
        offres = new ArrayList<>();
    }



    public Long getId_prop() {
        return id_prop;
    }

    public void setId_prop(Long id_prop) {
        this.id_prop = id_prop;
    }

    public double getSurface_prop() {
        return surface_prop;
    }

    public void setSurface_prop(double surface_prop) {
        this.surface_prop = surface_prop;
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



    public List<Offre> getOffres() {
        return offres;
    }



    public void setOffres(List<Offre> offres) {
        this.offres = offres;
    }



    public Contrat getContrat() {
        return contrat;
    }



    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }



    public Client getClient() {
        return client;
    }



    public void setClient(Client client) {
        this.client = client;
    }


}
