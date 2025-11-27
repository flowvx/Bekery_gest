/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class mproduit {

    private String nom_p;
    private String description;
    private int note;
    private Date validité;
    private String image_p;
    private String founiseur;
    private int quantite_p;

    public void setQuantite_p(int quantite_p) {
        this.quantite_p = quantite_p;
    }

    public int getQuantite_p() {
        return quantite_p;
    }

    public void setFouniseur(String founiseur) {
        this.founiseur = founiseur;
    }

    public String getFouniseur() {
        return founiseur;
    }

    public String getNom_p() {
        return nom_p;
    }

    public String getDescription() {
        return description;
    }

    public int getNote() {
        return note;
    }

    public Date getValidité() {
        return validité;
    }

    public String getImage_p() {
        return image_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setValidité(Date validité) {
        this.validité = validité;
    }

    public void setImage_p(String image_p) {
        this.image_p = image_p;
    }
    
    
    
    
}
