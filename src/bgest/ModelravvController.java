/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import com.jfoenix.controls.JFXButton;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import bgest.classcommande;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModelravvController implements Initializable {
    
     int idboulangerie;
    int idgerant; 
    @FXML
    private Label lblnom;
    @FXML
    private VBox productvbox;
    @FXML
    private JFXButton btnanuler;
    @FXML
    private JFXButton efectuer;
    @FXML
    private Label lblnom1;
    @FXML
    private JFXButton update;
    @FXML
    private DatePicker date_livraison;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
      update.setVisible(false);
      
    idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
    
     
   productvbox.setSpacing(3);
   productvbox.setAlignment(Pos.TOP_LEFT);

        
        
         try {
             initialise(getIdfournir());
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(ModelravvController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
       
    }    

    @FXML
    private void btnanulerclique(MouseEvent event) {
        
        
        
    }

    @FXML
    private void efectuerclique(MouseEvent event) {
    }
    
    
    private classcommande commande;
     
     public void setCommande(classcommande commande) {
        this.commande = commande;
        updateView();
    }
     
    public static int idcommande;
    
    public static void setfournirid(int idcommande){
        ModelravvController.idcommande= idcommande;
    } 

    public static int getIdfournir() {
        return idcommande;
    }
    
    

    private void updateView() {
        
    lblnom.setText(classcommande.getNomclient());;
    date_livraison.setValue(classcommande.getDate_livraison());
       
    }

    
    
  private void initialise(int idfournir) throws ClassNotFoundException {
    try {
        // Charger le driver MySQL pour établir une connexion à la base de données
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Établir une connexion à la base de données avec les informations de connexion
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

        // Préparer une requête SQL pour récupérer les produits associés à la commande
        String sql = "SELECT id_produit_produit, quantite FROM contenir3 WHERE id_fournir =?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        // Définir le paramètre de la requête (idCommande) pour récupérer les produits de la commande spécifique
        preparedStatement.setInt(1, idfournir);
         
        
        // Exécuter la requête et récupérer les résultats
        ResultSet resultSet = preparedStatement.executeQuery();

        // Créez une liste pour stocker les produits
        
        
        // Créez un map pour stocker les produits déjà ajoutés à la liste
        Map<Integer, classcommande> produitsMap = new HashMap<>();

        // Parcourez les résultats et ajoutez les produits à la liste
        while (resultSet.next()) {
            // Récupérer la quantité et l'ID du produit à partir des résultats
            int quantite = resultSet.getInt("quantite");
            int idProduit = resultSet.getInt("id_produit_produit");

            // Vérifiez si le produit est déjà dans le map
            if (produitsMap.containsKey(idProduit)) {
                // Si oui, mettre à jour la quantité du produit
                classcommande produit44 = produitsMap.get(idProduit);
                produit44.setQuanp_in_c(produit44.getQuanp_in_c() + quantite);
            } else {
                // Sinon, créer une nouvelle instance de classcommande
                String sql2 = "SELECT nom_produit FROM produit WHERE id_produit =?";
                PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
                preparedStatement2.setInt(1, idProduit);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                String nomproduit = null;
                
                if (resultSet2.next()) {
                    nomproduit = resultSet2.getString("nom_produit");
                }

                classcommande produit = new classcommande(nomproduit, quantite);
                produitsMap.put(idProduit, produit);
                
                
                
                
                System.out.println("produit trouver " + nomproduit);
                
                
                
                // Create a new FXMLLoader instance for each product
                FXMLLoader loader = new FXMLLoader(getClass().getResource("productincommande.fxml"));

                // Load the FXML file and get the controller instance
                Parent produitModelRoot = loader.load();
                ProductincommandeController produitModelController = loader.getController();

                // Initialize the controller with the produitCommande instance
                produitModelController.init(produit);

                // Add the root node of the FXML file to the HBox
                productvbox.getChildren().add(produitModelRoot);

                
                
            }
        }

        // Fermez les objets ResultSet et PreparedStatement pour libérer les ressources
        resultSet.close();
        preparedStatement.close();
        
        

        // Ajout des produits au HBox
        
        
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
    
    try {
    // Charger le driver MySQL pour établir une connexion à la base de données
    Class.forName("com.mysql.cj.jdbc.Driver");
    // Établir une connexion à la base de données avec les informations de connexion
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

    // Préparer une requête SQL pour récupérer les produits associés à la commande
    String sql5 = "SELECT id_fournisseur_fournisseur, rdate FROM fournir WHERE id_fournir = ?";
    PreparedStatement preparedStatement = conn.prepareStatement(sql5);
    // Définir le paramètre de la requête (idCommande) pour récupérer les produits de la commande spécifique
    preparedStatement.setInt(1, idfournir);

    ResultSet resultSet = preparedStatement.executeQuery();

    int id_fo = 0;
    String adresse_livraison = "";
    String nom_fo = "";
    String entreprise_fo = "";
    String date_livraison1= "";

    if (resultSet.next()) {
        id_fo = resultSet.getInt("id_fournisseur_fournisseur");
        
        date_livraison1 = resultSet.getString("rdate");
    } else {
        System.out.println("Impossible de récupérer l'id et l'adresse du client");
    }

    String sql6 = "SELECT nom_fournisseur, entreprise_fournisseur FROM fournisseur WHERE id_fournisseur = ?";
    PreparedStatement pstn = conn.prepareStatement(sql6);
    pstn.setInt(1, id_fo);

    ResultSet rst = pstn.executeQuery();

    if (rst.next()) {
        nom_fo = rst.getString("nom_fournisseur");
        entreprise_fo = rst.getString("entreprise_fournisseur");
    } else {
        System.out.println("Impossible de récupérer le nom et l' entreprise du fournisseur");
    }

    // Update the UI with the retrieved data
    
    
    lblnom.setText(nom_fo);
    
    date_livraison.setValue(LocalDate.parse(date_livraison1));
} catch (SQLException e) {
    e.printStackTrace();
}
}

    @FXML
    private void updateclique(MouseEvent event) {
        
        
        
        
    }
    

}
