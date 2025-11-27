/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.mproduit;
import model.mproduitc;
import bgest.Produit;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProduitmodelsController implements Initializable {

    @FXML
    private ImageView pimage;
    @FXML
    private Label pnom;
    @FXML
    private Label pfournisseur;
    @FXML
    private Label pdescription;
    @FXML
    private Label quantité;
    @FXML
    private AnchorPane box;

    private String [] colors = {"B9E5FF","BDB2FE","FB9AA8","FF5056"};    
    
    
    
    
  
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    int idboulangerie;
    int idgerant; 
        
    
    
    @Override
     public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
        
        
    }  
     private Produit produit;
     
     public void setProduit(Produit produit) {
        this.produit = produit;
        updateView();
    }
     
     
     

    private void updateView() {
        
        pnom.setText(produit.getNom());
        pfournisseur.setText(produit.getFournisseur());
        pdescription.setText(produit.getDescription());
        quantité.setText(String.valueOf(produit.getQuantité()));
        pimage.setImage(produit.getImage());
    }

     
   
}
