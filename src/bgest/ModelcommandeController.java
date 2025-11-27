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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModelcommandeController implements Initializable {

    @FXML
    private Label lblnom;
    private Label lblid;
    @FXML
    private Label lblprenom;
    @FXML
    private Label lbladresse;
    @FXML
    private VBox productvbox;
    @FXML
    private JFXButton btnanuler;
    @FXML
    private JFXButton efectuer;
    

    /**
     * Initializes the controller class.
     */
    
    int idboulangerie;
    int idgerant; 
    @FXML
    private Label lbladresse1;
    @FXML
    private Label lblnom1;
    @FXML
    private JFXButton update;
    @FXML
    private DatePicker date_livraison;
    @FXML
    private Text idcc;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     update.setVisible(false);
        
    idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
    
   update.setVisible(false);
   productvbox.setSpacing(3);
   productvbox.setAlignment(Pos.TOP_LEFT);

        
        try {
            initialise(getIdcommande());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelcommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }    

    @FXML
    private void btnanulerclique(MouseEvent event) {
        
        int comid = Integer.parseInt(idcc.getText());
        
        int t = 0;
    
            try {
                // Charger le driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Établir la connexion à la base de données
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

                String sql = "DELETE FROM contenir WHERE id_commande_commande = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, comid);

                // Exécuter la requête 
                int rowsAffected = pst.executeUpdate();
                System.out.println("Nombre de lignes supprimées : " + rowsAffected);
                
                String sql1 = "DELETE FROM commande WHERE id_commande = ?";
                PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst1.setInt(1, comid);

                // Exécuter la requête 
                int rowsAffected1 = pst1.executeUpdate();
                System.out.println("Nombre de lignes supprimées : " + rowsAffected1);
                
                

            } catch (SQLException e) {
                System.err.println("Erreur lors de la suppression dans la table contenir : " + e);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erreur lors de la suppression des produits : " + e.getMessage());
                alert.showAndWait();
                t = 1;
            } catch (ClassNotFoundException ex) {
                t = 1; 
                Logger.getLogger(ALGORITHME.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (t == 0) {
                try {
                    initialise(getIdcommande());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ModelcommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        

            }
        
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
    
    public static void setcommandeid(int idcommande){
        ModelcommandeController.idcommande = idcommande;
    } 

    public static int getIdcommande() {
        return idcommande;
    }
    
    

    private void updateView() {
        
    lblnom.setText(classcommande.getNomclient());;
    lblid.setText(String.valueOf(classcommande.getId_com()));;
    lblprenom.setText(classcommande.getPrenomclient());;
    lbladresse.setText(classcommande.getAdresseclient());;
    date_livraison.setValue(classcommande.getDate_livraison());
       
    }

    
    
  private void initialise(int idCommande) throws ClassNotFoundException {
    try {
        // Charger le driver MySQL pour établir une connexion à la base de données
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Établir une connexion à la base de données avec les informations de connexion
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

        // Préparer une requête SQL pour récupérer les produits associés à la commande
        String sql = "SELECT id_produit_produit, quantite FROM contenir WHERE id_commande_commande =?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        // Définir le paramètre de la requête (idCommande) pour récupérer les produits de la commande spécifique
        preparedStatement.setInt(1, idCommande);
         
        
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
    String sql5 = "SELECT id_client_client, adresse_livraison,date_livraison FROM commande WHERE id_commande = ?";
    PreparedStatement preparedStatement = conn.prepareStatement(sql5);
    // Définir le paramètre de la requête (idCommande) pour récupérer les produits de la commande spécifique
    preparedStatement.setInt(1, idCommande);

    ResultSet resultSet = preparedStatement.executeQuery();

    int id_client = 0;
    String adresse_livraison = "";
    String nom_client = "";
    String prenom_client = "";
    String date_livraison1= "";

    if (resultSet.next()) {
        id_client = resultSet.getInt("id_client_client");
        adresse_livraison = resultSet.getString("adresse_livraison");
        date_livraison1 = resultSet.getString("date_livraison");
    } else {
        System.out.println("Impossible de récupérer l'id et l'adresse du client");
    }

    String sql6 = "SELECT nom_client, prenom_client FROM client WHERE id_client = ?";
    PreparedStatement pstn = conn.prepareStatement(sql6);
    pstn.setInt(1, id_client);

    ResultSet rst = pstn.executeQuery();

    if (rst.next()) {
        nom_client = rst.getString("nom_client");
        prenom_client = rst.getString("prenom_client");
    } else {
        System.out.println("Impossible de récupérer le nom et le prénom du client");
    }

    // Update the UI with the retrieved data
    
    lbladresse.setText(adresse_livraison);
    lblnom.setText(nom_client);
    lblprenom.setText(prenom_client);
    if (date_livraison1 != null && !date_livraison1.isEmpty()) {
    date_livraison.setValue(LocalDate.parse(date_livraison1));
    } else {
        // Handle the case where the date is empty or null
        date_livraison.setValue(null); // or some other default value
    }
    idcc.setText(String.valueOf(idCommande));
} catch (SQLException e) {
    e.printStackTrace();
}
}

    @FXML
    private void updateclique(MouseEvent event) {
    }
    

}
