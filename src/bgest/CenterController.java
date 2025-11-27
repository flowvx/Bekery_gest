/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import model.mproduit;
import model.mproduitc;
import bgest.ProduitmodelsController;
import javafx.scene.Group;
import bgest.Produit;
import javafx.scene.Node;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class CenterController implements Initializable {

    private ImageView add;
    @FXML
    private JFXButton facture;
    @FXML
    private Label nomboulangerie;
    @FXML
    private ImageView logoboulangerie;
    @FXML
    private Pane optionpane;
    @FXML
    private HBox producbox;
    @FXML
    private ScrollPane mosxellbox;
    @FXML
    private Label APPNAME;
    @FXML
    private JFXButton boutonacceuil;
    
    

    
    int idboulangerie;
    int idgerant;
    
    @FXML
    private JFXButton btnfournisseur;
    @FXML
    private JFXButton accesprodut;
    @FXML
    private JFXButton viewcommand;
    @FXML
    private JFXButton dashbord;
    @FXML
    private JFXButton demandeaufournisseur;
    @FXML
    private JFXButton MONCOMPTE;
    @FXML
    private JFXButton COMPTESUTILISATEURS;
    @FXML
    private ImageView IMAUTILISATEUR;
    @FXML
    private ImageView IMGCOMTES;
    @FXML
    private VBox productplanebox;
    @FXML
    private JFXButton btnclient;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
    
    productplanebox.setSpacing(20);
    productplanebox.setAlignment(Pos.TOP_LEFT);
    
    
    
    Blob logoimg ;
    String nombou= "notre boulangerie";
    idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
    
    System.out.println(" l'id de la boulangerie est :" + idboulangerie);
    System.out.println(" l'id du  gerant est :" + idgerant);
    
    if (idgerant==0){
        COMPTESUTILISATEURS.setVisible(false);
        MONCOMPTE.setVisible(true);
        IMAUTILISATEUR.setVisible(true);
        IMGCOMTES.setVisible(false);
    }
    else{
        COMPTESUTILISATEURS.setVisible(true);
        MONCOMPTE.setVisible(false);
        IMAUTILISATEUR.setVisible(false);
        IMGCOMTES.setVisible(true);
    
    }
    
    try (Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "")) {
    String sql1 = "SELECT nom_boulangerie,logo_boulangerie FROM boulangerie WHERE id_boulangerie =? ";
    PreparedStatement statement21 = connection2.prepareStatement(sql1);
    statement21.setInt(1, idboulangerie);
    ResultSet resultSet2 = statement21.executeQuery();

   if (resultSet2.next()) {
    nombou = resultSet2.getString("nom_boulangerie");
    logoimg = resultSet2.getBlob("logo_boulangerie");
    byte[] bytea = logoimg.getBytes(1, (int) logoimg.length());
    Image image = new Image(new ByteArrayInputStream(bytea));
    logoboulangerie.setImage(image);
    nomboulangerie.setText(nombou);
} else {
    nombou = "notre boulangerie";
    nomboulangerie.setText(nombou);
}
   
} catch (SQLException exp) {
    System.out.println(exp.getMessage());
}      
    
    
    
    
           
            
           
      

    
    
     TranslateTransition slide = new TranslateTransition();
    slide.setDuration(Duration.seconds(0.4));     
    slide.setNode(optionpane);
    slide.setToX(-190);
    slide.play();
    APPNAME.setVisible(false);
    boutonacceuil.setStyle("-fx-background-color: white; -fx-text-fill: black;-fx-background-radius: 36 0 0 36");
   
    
    producbox.setSpacing(20);
    producbox.setAlignment(Pos.TOP_LEFT);
    try {
    // Charger le driver MySQL
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Établir la connexion à la base de données
    Connection conn56 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

    // Créez une requête SQL pour récupérer les id_produit_produit de la table vendre
    String sql152 = "SELECT id_produit_produit FROM vendre WHERE id_boulangerie_boulangerie =?";
    PreparedStatement preparedStatement1 = conn56.prepareStatement(sql152);
    preparedStatement1.setInt(1, idboulangerie);

    // Exécutez la requête et récupérez les résultats
    ResultSet resultSet132 = preparedStatement1.executeQuery();

    // Créez une liste pour stocker les id_produit_produit
    List<Integer> idProduitList = new ArrayList<>();

    // Parcourez les résultats et ajoutez les id_produit_produit à la liste
    while (resultSet132.next()) {
        idProduitList.add(resultSet132.getInt("id_produit_produit"));
    }
     String cat1 ="ingrdient pas a vendre ";
     String cat2 = "ustensiles/materiel";
    // Créez une requête SQL pour récupérer les informations de la table produit
    String sql25 = "SELECT quantité_disponible, description_produit, nom_produit, image_produit FROM produit WHERE id_produit =? AND prime != 0 and categorie_produit != ? and categorie_produit!= ?";
    PreparedStatement preparedStatement252 = conn56.prepareStatement(sql25);

    // Créez une liste pour stocker les produits
    ObservableList<Produit> produits = FXCollections.observableArrayList();

    // Parcourez la liste des id_produit_produit et exécutez la requête pour chaque id
    for (int idProduit : idProduitList) {
        preparedStatement252.setInt(1, idProduit);
        preparedStatement252.setString(2, cat1);
        preparedStatement252.setString(3, cat2);
        ResultSet resultSet252 = preparedStatement252.executeQuery();

        // Parcourez les résultats et ajoutez les produits à la liste
        while (resultSet252.next()) {
            int quantitéDisponible = resultSet252.getInt("quantité_disponible");
            String descriptionProduit = resultSet252.getString("description_produit");
            String nomProduit = resultSet252.getString("nom_produit");
            Blob imageProduitBlob = resultSet252.getBlob("image_produit");
            byte[] imageBytes = imageProduitBlob.getBytes(1, (int) imageProduitBlob.length());
            Image image = new Image(new ByteArrayInputStream(imageBytes));

            Produit produit = new Produit(nomProduit, descriptionProduit, quantitéDisponible, image);
            produits.add(produit);
        }
    }

    
    
    
    for (Produit produit : produits) {
        // Créez une instance de FXMLLoader pour charger le fichier FXML
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProduitmodelVIEW.fxml"));

        // Chargez le fichier FXML et récupérez l'instance du contrôleur
        Parent produitModelRoot = loader.load();

        ProduitmodelsController produitModelController = loader.getController();

        // Configurez le contrôleur avec les informations du produit
        produitModelController.setProduit(produit);

        // Ajoutez le noeud racine du fichier FXML au HBox
        for (Node node : producbox.getChildren()) {
            if (node.isVisible() == false) {
                System.out.println("Invisible node found!");
        }
        }
        
        producbox.getChildren().add(produitModelRoot);

        
    }

   
    

    // Fermez la connexion
    conn56.close();

} catch (SQLException exp) {
    System.out.println(exp.getMessage());
} catch (IOException ex) {
    Logger.getLogger(CenterController.class.getName()).log(Level.SEVERE, null, ex);
} catch (ClassNotFoundException ex) {
    Logger.getLogger(CenterController.class.getName()).log(Level.SEVERE, null, ex);
}

    
    
    productplanebox.setSpacing(10);
    productplanebox.setPadding(new Insets(0, 0, 0, 0));
   try {
    // Charger le driver MySQL
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Établir la connexion à la base de données
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

    // Créez une requête SQL pour récupérer les id_produit_produit de la table vendre
    String sql132 = "SELECT id_produit_produit FROM vendre WHERE id_boulangerie_boulangerie =?";
    PreparedStatement preparedStatement1 = conn.prepareStatement(sql132);
    preparedStatement1.setInt(1, idboulangerie);

    // Exécutez la requête et récupérez les résultats
    ResultSet resultSet132 = preparedStatement1.executeQuery();

    // Créez une liste pour stocker les id_produit_produit
    List<Integer> idProduitList = new ArrayList<>();

    // Parcourez les résultats et ajoutez les id_produit_produit à la liste
    while (resultSet132.next()) {
        idProduitList.add(resultSet132.getInt("id_produit_produit"));
    }
     String cat1 ="ingrdient pas a vendre ";
     String cat2 = "ustensiles/materiel";
    // Créez une requête SQL pour récupérer les informations de la table produit
    String sql2 = "SELECT quantité_disponible, description_produit, nom_produit, image_produit FROM produit WHERE id_produit =? and categorie_produit != ? and categorie_produit!= ?";
    PreparedStatement preparedStatement232 = conn.prepareStatement(sql2);

    // Créez une liste pour stocker les produits
    ObservableList<Produit> produits = FXCollections.observableArrayList();

    // Parcourez la liste des id_produit_produit et exécutez la requête pour chaque id
    for (int idProduit : idProduitList) {
        preparedStatement232.setInt(1, idProduit);
        preparedStatement232.setString(2, cat1);
        preparedStatement232.setString(3, cat2);
        ResultSet resultSet232 = preparedStatement232.executeQuery();

        // Parcourez les résultats et ajoutez les produits à la liste
        while (resultSet232.next()) {
            int quantitéDisponible = resultSet232.getInt("quantité_disponible");
            String descriptionProduit = resultSet232.getString("description_produit");
            String nomProduit = resultSet232.getString("nom_produit");
            Blob imageProduitBlob = resultSet232.getBlob("image_produit");
            byte[] imageBytes = imageProduitBlob.getBytes(1, (int) imageProduitBlob.length());
            Image image = new Image(new ByteArrayInputStream(imageBytes));

            Produit produit = new Produit(nomProduit, descriptionProduit, quantitéDisponible, image);
            produits.add(produit);
        }
    }

    // Créez un HBox pour stocker les produits
    HBox hboxproduct = new HBox(10); // spacing de 10 pixels entre les éléments

    // Créez une liste pour stocker les lignes de produits
    List<HBox> rows = new ArrayList<>();

    // Parcourez la liste des produits et ajoutez-les au HBox
    int rowIndex = 0;
    HBox row = new HBox(10); // spacing de 10 pixels entre les éléments
    for (Produit produit : produits) {
        // Créez une instance de FXMLLoader pour charger le fichier FXML
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProduitmodelVIEW.fxml"));

        // Chargez le fichier FXML et récupérez l'instance du contrôleur
        Parent produitModelRoot = loader.load();

        ProduitmodelsController produitModelController = loader.getController();

        // Configurez le contrôleur avec les informations du produit
        produitModelController.setProduit(produit);

        // Ajoutez le noeud racine du fichier FXML au HBox
        row.getChildren().add(produitModelRoot);

        rowIndex++;

        // Si nous avons atteint 3 éléments dans la ligne, ajoutez la ligne à la liste des lignes
        if (rowIndex % 3 == 0) {
            rows.add(row);
            row = new HBox(10); // créer une nouvelle ligne
        }
    }

    // Ajoutez la dernière ligne à la liste des lignes si elle n'est pas vide
    if (!row.getChildren().isEmpty()) {
        rows.add(row);
    }
    
    for (Node node : productplanebox.getChildren()) {
            if (node.isVisible() == false) {
                System.out.println("Invisible node found!");
        }
    }
    
     productplanebox.getChildren().clear();
    // Ajoutez les lignes à la scène
    for (HBox rowBox : rows) {
        
        
        productplanebox.getChildren().add(rowBox);
    }

    // Fermez la connexion
    conn.close();

} catch (SQLException exp) {
    System.out.println(exp.getMessage());
} catch (IOException ex) {
    Logger.getLogger(CenterController.class.getName()).log(Level.SEVERE, null, ex);
} catch (ClassNotFoundException ex) {
    Logger.getLogger(CenterController.class.getName()).log(Level.SEVERE, null, ex);
}
}
    
    
    
    

    
    


   

    @FXML
    private void btnfacture(MouseEvent event) {
         String fxurl = "make facture.fxml";
        
        ALGORITHME.setFxurl(fxurl);
        ALGORITHME.showOverlayScene();
    }

    @FXML
    private void btnacceuil(MouseEvent event) {
        
        
        
    }
    
    
    

    @FXML
    private void optionpaneexit(MouseEvent event) {
        
        TranslateTransition slide3 = new TranslateTransition();
    slide3.setDuration(Duration.seconds(0.4));     
    slide3.setNode(optionpane);
    slide3.setToX(-190);
    slide3.play();
    APPNAME.setVisible(false);
        
    }

    @FXML
    private void optionpanemousemove(MouseEvent event) {
        
        
        TranslateTransition slide2 = new TranslateTransition();
    slide2.setDuration(Duration.seconds(0.4));     
    slide2.setNode(optionpane);
    slide2.setToX(0);
    slide2.play();
    APPNAME.setVisible(true);
        
        
    }
    
  

    @FXML
    private void btnfournisseurclique(MouseEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FOURNISSEUR.fxml"));
        Parent root = loader.load();

        Scene scene = btnfournisseur.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
        
        
    }

    @FXML
    private void accesprodutclique(MouseEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("PRODUIT.fxml"));
        Parent root = loader.load();

        Scene scene = accesprodut.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
        
        
    }

    @FXML
    private void viewcommandclique(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("COMMANDESVIEW.fxml"));
        Parent root = loader.load();

        Scene scene = viewcommand.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
        
    }

    @FXML
    private void dashbordclique(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Parent root = loader.load();

        Scene scene = dashbord.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
        
    }

    @FXML
    private void demandeaufournisseurclique(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ravitaillementview.fxml"));
        Parent root = loader.load();

        Scene scene = demandeaufournisseur.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
        
        
    }

    @FXML
    private void MONCOMPTECLIQUE(MouseEvent event) {
    }

    @FXML
    private void COMPTESUTILISATEURSCLIQUE(MouseEvent event) {
    }

    @FXML
    private void btnclient(MouseEvent event) {
    }

     
   
    
}
