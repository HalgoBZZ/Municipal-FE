package com.halgo.municipalpfe.modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {
    //@SerializedName("id")
    private Long id_compte;

    private String nom_client;

    private String prenom_client;

    private String date_naissance;

    private String date_ajout;

    private String date_modification;

    private int cin;
    private String pwd;
    private String email;

    private String role;

    private List<Avertissement> avertissements = new ArrayList<>();


    private Contrat contrat;

    private List<Propriete> proprietes = new ArrayList<>();

    public Client(Long id, String nom_client, String prenom_client, String naissance, int cin, String ajout, String date_modification) {
        this.id_compte = id;
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.date_naissance = naissance;
        this.cin = cin;
        this.date_ajout = ajout;
        this.date_modification = date_modification;
    }
    public Client(String nom_client, String prenom_client, int cin) {
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.cin = cin;
    }

    public Client(String nom_client, String prenom_client, String naissance, int cin) {
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.date_naissance = naissance;
        this.cin = cin;
    }

    public Client() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public List<Propriete> getProprietes() {
        return proprietes;
    }

    public void setProprietes(List<Propriete> proprietes) {
        this.proprietes = proprietes;
    }

    public Long getId_compte() {
        return id_compte;
    }

    public void setId_compte(Long id_compte) {
        this.id_compte = id_compte;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String naissance) {
        this.date_naissance = naissance;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }
}
