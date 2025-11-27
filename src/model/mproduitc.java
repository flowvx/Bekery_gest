/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;
import java.sql.Date;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class mproduitc {
    
    
    private String nom_p;
    private String description;
    private int note;
    private boolean disponibilité;
    private String image_p;
    private String founiseur;
    private int Quantite_p;
    private Blob Image_p;
    
    

    public void setDisponibilité(boolean disponibilité) {
        this.disponibilité = disponibilité;
    }
    

    public boolean isDisponibilité() {
        return disponibilité;
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

    public void setImage_p(String image_p) {
        this.image_p = image_p;
    }

    public void setQuantite_p(int Quantite_p) {
        this.Quantite_p = Quantite_p;
    }

    public void setImage_p(Blob Image_p) {
        this.Image_p = Image_p;
    }

    public Object getQuantite_p() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
    
}
