/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import javafx.scene.Cursor;

/**
 *
 * @author USER
 */
public class ALGORITHME {
    
    
    public static int getProduitDisponible(int idProduit) throws ClassNotFoundException, SQLException {
    int disponible = 0;
    try {
        // Charger le driver MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Établir la connexion à la base de données
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

        String sql = "SELECT quantité_reel, quantite_sur_commande FROM produit WHERE id_produit = ?";
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, idProduit);

        // Exécutez la requête et récupérez les résultats
        ResultSet resultSet = pst.executeQuery();

        if (resultSet.next()) {
            int qReel = resultSet.getInt("quantité_reel");
            int qCom = resultSet.getInt("quantite_sur_commande");

            disponible = qReel - qCom;

            System.out.println("La quantité réelle de ce produit est " + qReel);
            System.out.println("La quantité sur commande de ce produit est " + qCom);
            System.out.println("La quantité disponible de ce produit est " + disponible);
        } else {
            System.out.println("Produit non trouvé");
        }

    } catch (SQLException e) {
        System.err.println("Error updating produit table: " + e);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Erreur lors de la récupération de la quantité disponible : " + e.getMessage());
        alert.showAndWait();
    }

    return disponible;
}
    
    
    
    
    
     public class Produit {
    private final int id;
    private final String nom;
    private final String description;
    private final String cate ;
    private final int q_vandu;
    private final int prixUnitaire;
    private final int quantiteReelle;
    private final int quantiteDisponible;
    private final int quantiteEnAttente;
    private final boolean prime;
    

    public Produit(int id, String nom, String description, int prixUnitaire, int quantiteReelle, int quantiteDisponible, int quantiteEnAttente, boolean prime, String cate, int q_vandu) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.quantiteReelle = quantiteReelle;
        this.quantiteDisponible = quantiteDisponible;
        this.quantiteEnAttente = quantiteEnAttente;
        this.prime = prime;
        this.cate = cate;
        this.q_vandu = q_vandu;
    }

    public String getCate() {
        return cate;
    }

        // Getters et setters
        public int getQ_vandu() {
            return q_vandu;
        }

        public int getId() {
            return id;
        }

    public String getNom() {
        return nom;   
    }

    public String getDescription() {
        return description;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getQuantiteReelle() {
        return quantiteReelle;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public int getQuantiteEnAttente() {
        return quantiteEnAttente;
    }

    public boolean isPrime() {
        return prime;
    }

    // Autres méthodes de la classe Produit
    }
    
   private static String fxurl;

public static void setFxurl(String fxurl) {
    ALGORITHME.fxurl = fxurl;
}

public static String getFxurl() {
    return fxurl;
}

public static void showOverlayScene() {
    try {
        // Chargement de la scène superposée à partir de FXML
        FXMLLoader loader = new FXMLLoader(ALGORITHME.class.getResource(ALGORITHME.getFxurl()));
        Parent overlayRoot;
        try {
            overlayRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return; // ou gérer l'exception d'une autre manière
        }

        // Création de la scène avec le layout chargé depuis le FXML
        Scene overlayScene = new Scene(overlayRoot);

        // Rendre la scène superposée mobile
        

        // Configuration de la fenêtre superposée
        Stage overlayStage = new Stage();
        overlayStage.initModality(Modality.APPLICATION_MODAL);
        overlayStage.initStyle(StageStyle.UNDECORATED);
        overlayStage.setScene(overlayScene);
        overlayStage.showAndWait();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static String setunite(int idb) {
   String qReel = "FCFA"; 
    try {
        // Charger le driver MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Établir la connexion à la base de données
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

    
            String unite = "select unité from boulangerie where id_boulangerie= ?";
            
            
            
            PreparedStatement pst = conn.prepareStatement(unite);

            pst.setInt(1, idb);

        // Exécutez la requête et récupérez les résultats
        ResultSet resultSet = pst.executeQuery();

        if (resultSet.next()) {
             qReel = resultSet.getString("unité");
            

            
        } else {
            System.out.println("Produit non trouvé");
        }

     } catch (SQLException e) {
        System.err.println("Error updating produit table: " + e);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Erreur lors de la récupération de l'unité de vante : " + e.getMessage());
        alert.showAndWait();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(ALGORITHME.class.getName()).log(Level.SEVERE, null, ex);
        }
return qReel;
    
}



    
}


    
    
    
 