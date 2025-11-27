/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProductincommandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    int idboulangerie;
    int idgerant; 
    
    @FXML
    private Label nomproduit;
    @FXML
    private TextField quantiteproduit;
    @FXML
    private CheckBox checbox;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
    idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
        
        
    }    
    
    private classcommande produitcommande;
     
     public  void setproduitCommande(classcommande produitcommande) {
        this.produitcommande = produitcommande;
        updateView();
    }
     
     public void setProduitCommande(classcommande produitCommande) {
    this.produitcommande = produitCommande;
    updateView();
    }
     
     private classcommande produitCommande;

    public void init(classcommande produitCommande) {
        this.produitCommande = produitCommande;
        updateView();
    }
     

    private void updateView() {
        nomproduit.setText(classcommande.getNomp_in_c());
        quantiteproduit.setText(String.valueOf(classcommande.getQuanp_in_c()));
         
    }
    
    public Node getView() throws IOException {
        // Retourner le nœud racine du contrôleur
        return (Node) FXMLLoader.load(getClass().getResource("Productincommande.fxml"));
    }

     
    public Label getNomProduit() {
    return nomproduit;
    }

    public TextField getQuantiteProduit() {
            TextField quantiteProduit;
        return quantiteproduit;
    }

    public CheckBox getCheckBox() {
        return checbox;
    }
    
    
}
