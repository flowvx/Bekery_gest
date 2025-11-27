/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javafx.scene.image.Image;

/**
 *
 * @author USER
 */
public class Produit {
    
    
    private String nom;
    private Image image;
    private String fournisseur;
    private String description;
    private int quantité;
    
    private Float prixu;
    private Float prixt;
    private int id;
    

    public Produit(String nom, String description, int quantité, Image image) {
        this.nom = nom;
        this.description = description;
        this.quantité = quantité;
        this.image = image;
    }

    Produit(String nom, int quantite, Float prixu, Float prixt) {
         //To change body of generated methods, choose Tools | Templates.
         this.nom = nom;
         this.quantité = quantité;
         this.prixu = prixu;
         this.prixt = prixt;
         
    }

    public Float getPrixu() {
        return prixu;
    }

    public Float getPrixt() {
        return prixt;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPrixu(Float prixu) {
        this.prixu = prixu;
    }

    public void setPrixt(Float prixt) {
        this.prixt = prixt;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public int getId() {
            return id;
        }



// Méthode pour convertir un blob en objet Image
private Image blobToImage(Blob blob) {
    try {
        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
        return new Image(new ByteArrayInputStream(imageBytes));
    } catch (SQLException e) {
        System.err.println("Erreur lors de la récupération de l'image : " + e.getMessage());
        return null;
    }
    
}
    
    
    
}
