/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import com.jfoenix.controls.JFXButton;
import java.io.ByteArrayInputStream;
import static java.lang.Math.random;
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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MakeFactureController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    int idboulangerie;
    int idgerant; 
    @FXML
    private Label nomboulangerie1;
    @FXML
    private JFXButton btnback;
    @FXML
    private TextField NOMC;
    @FXML
    private TextField PRENOMC;
    @FXML
    private Label prixvue;
    @FXML
    private Label unité;
    @FXML
    private ImageView logoboulangerie;
    @FXML
    private TableView<Produit> produittable;
    
     TableColumn<Produit, Integer> quantité = new TableColumn<>("QUANTITE");   
    TableColumn<Produit, String> nom1 = new TableColumn<>("NOM");
    TableColumn<Produit, Integer> prixun = new TableColumn<>("PRIX UNITAIRE"); 
    TableColumn<Produit, Integer> prixtot = new TableColumn<>("PRIX TOTAL"); 
    
    
    
    
    @FXML
    private TextField nompro;
    @FXML
    private TextField prixpro;
    @FXML
    private Label descrproc;
    @FXML
    private Label descriptiontext;
    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXButton btndelate;
    @FXML
    private JFXButton btnsave;
    
     int idf;
    int idp;
    int idcon;
    @FXML
    private Label nomboulangerie;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // ...
    
    idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    idboulangerie =8809424;
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
   
    unité.setText(ALGORITHME.setunite(idboulangerie));
    
    
                try (Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "")) {
                        String sql1 = "SELECT nom_boulangerie,logo_boulangerie FROM boulangerie WHERE id_boulangerie =? ";
                        PreparedStatement statement21 = connection2.prepareStatement(sql1);
                        statement21.setInt(1, idboulangerie);
                        ResultSet resultSet2 = statement21.executeQuery();

                        Blob logoimg ;
                        String nombou= "notre boulangerie";
                        idboulangerie = GestionnaireID.getIdboulangerie();
                        idgerant = GestionnaireID.getIdgerant();

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

    

    Random random = new Random();
    boolean uniqueIdFound = false;

    do {
        try {
            idf = random.nextInt(10000000);
            uniqueIdFound = checkIfExists(idf);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MAKECOMMANDEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } while (!uniqueIdFound);

    System.out.println("id de la facture : " + idf);

     System.out.println("id de la boullangerie : " +idboulangerie );
    System.out.println("id du gerrant : " +idgerant );

    
    btnupdate.setVisible(false);
    btndelate.setVisible(false);
    descriptiontext.setVisible(false);
    descrproc.setVisible(false);
    
    initialisetable ();

    produittable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Produit> obs, Produit oldValue, Produit newValue) -> {
        if (newValue != null) {
            btnupdate.setVisible(true);
            btndelate.setVisible(true);
            descriptiontext.setVisible(true);
            descrproc.setVisible(true);

            String nom = newValue.getNom();
            int quant = newValue.getQuantité();

            String description = "";
            Float prixup = newValue.getPrixu();

            String sqlll = "select description_produit,id_produit from produit where nom_produit = ? AND prixu_produit = ?";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

                PreparedStatement preparedStatement1 = conn1.prepareStatement(sqlll);
                preparedStatement1.setString(1, nom);
                preparedStatement1.setFloat(2, prixup);

                ResultSet rrst = preparedStatement1.executeQuery();

                if (rrst.next()) {
                    description = rrst.getString("description_produit");
                    idp = rrst.getInt("id_produit");
                    System.out.println("id du produit est " + idp);
                }
            } catch (SQLException e) {
                System.out.println("Error checking for product in database: " + e.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MAKECOMMANDEController.class.getName()).log(Level.SEVERE, null, ex);
            }

            String sql55 = "select id_contenir2 from contenir2 where id_produit_produit = ? and id_facture_facture=? and quantite =? ";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

                PreparedStatement preparedStatement1 = conn1.prepareStatement(sql55);
                preparedStatement1.setInt(1, idp);
                preparedStatement1.setInt(2, idf);
                preparedStatement1.setInt(3, quant);

                ResultSet rrst = preparedStatement1.executeQuery();

                if (rrst.next()) {
                    idcon = rrst.getInt("id_contenir2");
                }
            } catch (SQLException e) {
                System.out.println("Error checking for product in database: " + e.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MAKECOMMANDEController.class.getName()).log(Level.SEVERE, null, ex);
            }

            descriptiontext.setText(description);
            nompro.setText(nom);
            prixpro.setText(String.valueOf(quant));
        } else {
            btnupdate.setVisible(false);
            btndelate.setVisible(false);
            descriptiontext.setVisible(false);
            descrproc.setVisible(false);

            descriptiontext.setText("");
            nompro.setText("");
            prixpro.setText(String.valueOf(""));
        }
    });
}    

    @FXML
private void btnbackclique(MouseEvent event) {
        
   idboulangerie = GestionnaireID.getIdboulangerie();     
         int t = 0;
    
try {
    // Charger le driver MySQL
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Établir la connexion à la base de données
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

    String sql = "DELETE FROM contenir2 WHERE id_facture_facture =?";
    PreparedStatement pst = conn.prepareStatement(sql);
    pst.setInt(1, idf);

    // Exécuter la requête 
    int rowsAffected = pst.executeUpdate();
    System.out.println("Nombre de lignes supprimées : " + rowsAffected);

} catch (SQLException e) {
    System.err.println("Erreur lors de la suppression dans la table contenir2 : " + e);
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Erreur lors de la suppression des produits : " + e.getMessage());
    alert.showAndWait();
    t = 1;
} catch (ClassNotFoundException ex) {
    t = 1; 
    Logger.getLogger(ALGORITHME.class.getName()).log(Level.SEVERE, null, ex);
}

if (t == 0) {
    // Récupérer la scène active
    Scene currentScene = ((Stage) btnback.getScene().getWindow()).getScene();

    // Fermer la scène active
    ((Stage) currentScene.getWindow()).close();
    
    
}
        
        
        
    }

    @FXML
    private void btnaddclique(MouseEvent event) {
        
        idboulangerie = GestionnaireID.getIdboulangerie();
        
        if(btnupdate.isVisible()){
            
                initialisetable();
                System.out.println("Table initialisée");

                prixpro.setText("");
                nompro.setText("");
                descriptiontext.setText("");

        }
        else{
                    String nomp = nompro.getText();
                    String qt = prixpro.getText();
                    if (qt.isEmpty()) {
                        qt = "0";
                    }
                    int quantite = Integer.parseInt(qt);

                    System.out.println("Quantité : " + quantite);

                    if (nomp.isEmpty() || quantite == 0) {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setContentText("Remplissez toutes les informations svp");
                        alert1.showAndWait();
                    } else {
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

                            System.out.println("Connexion à la base de données établie");

                            String sql152 = "SELECT id_produit_produit  FROM vendre WHERE id_boulangerie_boulangerie  = ?";
                            PreparedStatement prept1 = conn.prepareStatement(sql152);
                            prept1.setInt(1, idboulangerie);

                            ResultSet resultSet132 = prept1.executeQuery();

                            System.out.println("Requête SQL 1 exécutée");

                            List<Integer> idProduitList = new ArrayList<>();

                            while (resultSet132.next()) {
                                idProduitList.add(resultSet132.getInt("id_produit_produit"));
                            }
                            
                            

                            System.out.println("Liste des ID produits : " + idProduitList);

                            String sql1 = "SELECT categorie_produit, quantité_reel, description_produit, prixu_produit, quantité_disponible, quantite_sur_commande FROM produit WHERE nom_produit like ? AND id_produit=?";
                            PreparedStatement pst1 = conn.prepareStatement(sql1);

                            ObservableList<Produit> produits = FXCollections.observableArrayList();
                            
                             boolean produitTrouve = false;
                             
                            for (int idProduit : idProduitList) {
                                pst1.setString(1, "%" + nomp + "%");
                                pst1.setInt(2, idProduit);

                                ResultSet rst = pst1.executeQuery();

                                System.out.println("Requête SQL 2 exécutée");

                                if (rst.next()) {
                                    
                                    produitTrouve = true;
                                    
                                    int quantitéDisponible = rst.getInt("quantité_disponible");
                                    int quantitéreel = rst.getInt("quantité_reel");
                                    int quantitésurcommande = rst.getInt("quantite_sur_commande");
                                    Float prixunitaire = rst.getFloat("prixu_produit");
                                    String descriptionProduit = rst.getString("description_produit");
                                    String categorie = rst.getString("categorie_produit");

                                    System.out.println("Produit trouvé : " + nomp + " - " + quantitéDisponible);

                                    
                                        Float prix_total = prixunitaire * quantite;
                                        quantitéreel = quantitéreel - quantite;
                                        quantitésurcommande = quantitésurcommande + quantite;
                                        quantitéDisponible = quantitéreel - quantitésurcommande;

                                        System.out.println("Prix total : " + prix_total);
                                        System.out.println("Quantité sur commande : " + quantitésurcommande);
                                        System.out.println("Quantité disponible : " + quantitéDisponible);

                                        Produit produit = new Produit(nomp, quantite, prixunitaire, prix_total);
                                        produits.add(produit);

                                        String ssql = "INSERT INTO contenir2 (id_produit_produit, id_facture_facture, quantite, prix_total) VALUES (?,?,?,?)";
                                        PreparedStatement ptr = conn.prepareStatement(ssql);

                                        ptr.setInt(1, idProduit);
                                        ptr.setInt(2, idf);
                                        ptr.setInt(3, quantite);
                                        ptr.setFloat(4, prix_total);

                                        ptr.executeUpdate();

                                        System.out.println("Produit ajouté à la commande");
                                    
                                }
                                
                            }


                                if (produitTrouve == false) {
                                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                                    alert2.setContentText("SE PRODUIT NE SE TROUVE PAS DANS VOTE STOCK");
                                    alert2.showAndWait();
                                }


                        } catch (SQLException e) {
                            System.out.println("Error checking for product in database: " + e.getMessage());
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MAKECOMMANDEController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    initialisetable();
                    System.out.println("Table initialisée");

                    prixpro.setText("");
                    nompro.setText("");
                    descriptiontext.setText("");
        } 
        
        
    }

    @FXML
private void btnmiseajourclique(MouseEvent event) {
    
    idboulangerie = GestionnaireID.getIdboulangerie();
        
         System.out.println("suppresion en cour");
    
    String nomp = nompro.getText();
    int quantite = Integer.parseInt(prixpro.getText());

   System.out.println("Quantité : " + quantite);

            if (nomp.isEmpty() || quantite == 0) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("Remplissez toutes les informations svp");
                alert1.showAndWait();
            } else {
                int t = 0;

                try {
                    // Charger le driver MySQL
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Établir la connexion à la base de données
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

                    String sql1 = "SELECT categorie_produit, quantité_reel, description_produit, prixu_produit, quantité_disponible, quantite_sur_commande FROM produit WHERE nom_produit LIKE ? AND id_produit = ?";
                    PreparedStatement pst1 = conn.prepareStatement(sql1);

                    pst1.setString(1, "%" + nomp + "%");
                    pst1.setInt(2, idp);

                    ResultSet rst = pst1.executeQuery();

                    System.out.println("Requête SQL 1 exécutée");

                    int quantitéDisponible = 0;
                    int quantitéreel = 0;
                    int quantitésurcommande = 0;
                    float prixunitaire = 0f;
                    String descriptionProduit = "";
                    String categorie = "";

                    if (rst.next()) {
                        quantitéDisponible = rst.getInt("quantité_disponible");
                        quantitéreel = rst.getInt("quantité_reel");
                        quantitésurcommande = rst.getInt("quantite_sur_commande");
                        prixunitaire = rst.getFloat("prixu_produit");
                        descriptionProduit = rst.getString("description_produit");
                        categorie = rst.getString("categorie_produit");
                    }
                    
                    if (quantitéreel > quantite ){
                        
                    float prix_total = prixunitaire * quantite;
                    quantitésurcommande = quantite;
                    quantitéDisponible = quantitéreel - quantitésurcommande;

                    System.out.println("Prix total : " + prix_total);
                    System.out.println("Quantité sur commande : " + quantitésurcommande);
                    System.out.println("Quantité disponible : " + quantitéDisponible);

                    String sql2 = "UPDATE contenir2 SET quantite = ?, prix_total = ? WHERE id_facture_facture = ? AND id_produit_produit = ?";
                    PreparedStatement pst2 = conn.prepareStatement(sql2);

                    pst2.setInt(1, quantite);
                    pst2.setFloat(2, prix_total);
                    pst2.setInt(3, idf);
                    pst2.setInt(4, idp);

                    int rowsUpdated = pst2.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Produit modifié dans la facture");
                    } else {
                        System.out.println("Aucune mise à jour effectuée dans la table 'contenir2'");
                    }
                    }
                    else{
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setContentText("stock insuffisiant");
                         alert.showAndWait();
                    }
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la mise à jour dans la table contenir2 : " + e);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erreur lors de la mise à jour du produit : " + e.getMessage());
                    alert.showAndWait();
                    t = 1;
                } catch (ClassNotFoundException ex) {
                    t = 1;
                    Logger.getLogger(ALGORITHME.class.getName()).log(Level.SEVERE, null, ex);
                }


        if (t == 0) {
            btnupdate.setVisible(false);
            btndelate.setVisible(false);
            descriptiontext.setVisible(false);
            descrproc.setVisible(false);

            descriptiontext.setText("");
            nompro.setText("");
            prixpro.setText(String.valueOf(""));

            initialisetable();
        }
    }
}

   @FXML
private void btndelateclique(MouseEvent event) {
    
    idboulangerie = GestionnaireID.getIdboulangerie();
    
    int t=0;
    
    try {
        // Charger le driver MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Établir la connexion à la base de données
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

        String sql = "DELETE FROM contenir2 WHERE id_facture_facture = ? AND id_produit_produit = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, idf); // idf est l'ID de la facture
        pst.setInt(2, idp); // idp est l'ID du produit

        // Exécuter la requête 
        int rowsAffected = pst.executeUpdate();
        System.out.println("Nombre de lignes supprimées : " + rowsAffected);

    } catch (SQLException e) {
        System.err.println("Erreur lors de la suppression dans la table contenir2 : " + e);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Erreur lors de la suppression du produit : " + e.getMessage());
        alert.showAndWait();
        t=1;
    } catch (ClassNotFoundException ex) {
       t=1;
        Logger.getLogger(ALGORITHME.class.getName()).log(Level.SEVERE, null, ex);
    }

    if (t==0){
        btnupdate.setVisible(false);
                        btndelate.setVisible(false);
                        descriptiontext.setVisible(false);
                        descrproc.setVisible(false);
     
                        descriptiontext.setText("");
                        nompro.setText("");
                        prixpro.setText(String.valueOf(""));
         initialisetable();               
    }
}
    
    
    @FXML
    private void btnsaveclique(MouseEvent event) {
        
         int t = 0;
    int tx1 = 0;
    int tx2 = 0;

    double pt; 
    try {
        pt = Double.parseDouble(prixvue.getText());
    } catch (NumberFormatException e) {
        
        System.err.println("Invalid input: " + e.getMessage());
        
        t = 1;
        tx1 = 1;
        return; // exit the method
    }
            String nomc = NOMC.getText();
            String prenom = PRENOMC.getText();
            
            if (pt == 0 ||  nomc.isEmpty() || prenom.isEmpty()  ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
                t = 1;
                tx1 = 1;
            } else {
                try {
                    // Charger le driver MySQL
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Établir la connexion à la base de données
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

                    String sqlo = "SELECT id_client FROM client WHERE nom_client like ? AND prenom_client like ?";
                    PreparedStatement dps = conn.prepareStatement(sqlo);
                    dps.setString(1, "%" + nomc + "%");
                    dps.setString(2, "%" + prenom + "%");

                    ResultSet rfs = dps.executeQuery();
                    int id_clint = 0;

                    if (rfs.next()) {
                        id_clint = rfs.getInt("id_client");
                    } else {
                        boolean uniqueIdFound1 = false;
                        Random random1 = new Random();

                        do {
                            id_clint = random1.nextInt(10000000);
                            uniqueIdFound1 = checkIfExists1(id_clint);
                        } while (!uniqueIdFound1);

                        String sqlp = "INSERT INTO client (id_client, nom_client, prenom_client) VALUES (?, ?, ?)";
                        PreparedStatement dpsc = conn.prepareStatement(sqlp);
                        dpsc.setInt(1, id_clint);
                        dpsc.setString(2, nomc);
                        dpsc.setString(3, prenom);
                        
                         
                        dpsc.executeUpdate();
                    }

                    String sql = "INSERT INTO facture (id_facture, id_boulangerie_boulangerie, prix_total) VALUES (?, ?, ?)";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setInt(1, idf);
                    pst.setInt(2, idboulangerie);
                    pst.setDouble(3, pt);
                    pst.executeUpdate();

                    try {
                        // Charger le driver MySQL
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Établir la connexion à la base de données
                        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

                        String sql4 = "SELECT id_produit_produit, quantite FROM contenir2 WHERE id_facture_facture = ?";
                        PreparedStatement pss = conn2.prepareStatement(sql4);
                        pss.setInt(1, idf);

                        ResultSet rts = pss.executeQuery();

                        while (rts.next()) {
                            int id_pro = rts.getInt("id_produit_produit");
                            int quantite = rts.getInt("quantite");

                            String sql1 = "SELECT quantité_reel, quantité_disponible, quantite_sur_commande FROM produit WHERE id_produit = ?";
                            PreparedStatement pst1 = conn2.prepareStatement(sql1);
                            pst1.setInt(1, id_pro);

                            ResultSet rsst = pst1.executeQuery();

                            if (rsst.next()) {
                                int quantitéDisponible = rsst.getInt("quantité_disponible");
                                int quantitéreel = rsst.getInt("quantité_reel");
                                int quantitésurcommande = rsst.getInt("quantite_sur_commande");
                                quantitéreel = quantitéreel - quantite;
                                quantitésurcommande = quantitésurcommande + quantite;
                                quantitéDisponible = quantitéreel - quantitésurcommande;

                                String sql12 = "UPDATE produit SET quantité_reel = ?, quantité_disponible = ?, quantite_sur_commande = ? WHERE id_produit = ?";
                                PreparedStatement pst12 = conn2.prepareStatement(sql12);
                                pst12.setInt(1, quantitéreel);
                                pst12.setInt(2, quantitéDisponible);
                                pst12.setInt(3, quantitésurcommande);
                                pst12.setInt(4, id_pro);
                                pst12.executeUpdate();
                            }
                        }
                    } catch (SQLException e) {
                        System.err.println("Erreur lors de la mise à jour de la table produit :" + e);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("désolé une erreur s'est produite " );
                        alert.showAndWait();
                        t = 1;
                    } catch (ClassNotFoundException ex) {
                        t = 1;
                        Logger.getLogger(ALGORITHME.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    
                    
                    String sqlp = "select vente_mensuelle from boulangerie where id_boulangerie= ?";
                
                    PreparedStatement ptd = conn.prepareStatement(sqlp);
                    ptd.setInt(1, idboulangerie);
                    
                    ResultSet rds = ptd.executeQuery();
                    
                    Double ventem = 0.0;
                    if (rds.next()){
                        ventem= rds.getDouble("vente_mensuelle");
                    }
                    else{
                        t=1;
                    }
                    
                    Double prixt = getTotalPriceForOrderId(idf);
                    ventem = ventem + prixt;
                    
                   String sqlt = "update boulangerie set vente_mensuelle = ? where id_boulangerie = ? ";

                    try (PreparedStatement ptr = conn.prepareStatement(sqlt)) {
                        ptr.setDouble(1, ventem);
                        ptr.setInt(2, idboulangerie);
                        ptr.executeUpdate();
                        System.out.println("Vente mensuelle mise à jour avec succès.");
                    } catch (SQLException e) {
                        System.err.println("Erreur de mise à jour de la vente mensuelle : " + e.getMessage());
                        t = 1;
                    }

                    String sqlg = "insert into reçois (id_client_client, id_facture_facture) values (?, ?) ";

                    try (PreparedStatement ptf = conn.prepareStatement(sqlg)) {
                        ptf.setInt(1, id_clint);
                        ptf.setInt(2, idf);
                        ptf.executeUpdate();
                        System.out.println("Insertion dans la table reçois réussie.");
                    } catch (SQLException e) {
                        System.err.println("Erreur d'insertion dans la table reçois : " + e.getMessage());
                        t = 1;
                    }
                    
                    
                    
                } catch (SQLException e) {
                    System.err.println("Erreur lors de l'ajout de la facture : " + e);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("désolé une erreur s'est produite " );
                    alert.showAndWait();
                    t = 1;
                    tx2 = 1;
                } catch (ClassNotFoundException ex) {
                    t = 1;
                    tx2 = 1;
                    Logger.getLogger(ALGORITHME.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (t == 0) {
                        // Récupérer la scène active
                        Scene currentScene = ((Stage) btnsave.getScene().getWindow()).getScene();

                        // Fermer la scène active
                        ((Stage) currentScene.getWindow()).close();
                    }
                
                
            }
    }


     private boolean checkIfExists(int id) throws ClassNotFoundException {
    boolean exists = false;
     Class.forName("com.mysql.cj.jdbc.Driver");
        
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
         PreparedStatement pst = conn.prepareStatement("SELECT 1 FROM facture WHERE id_facture =?")) {
        pst.setInt(1, id);
        try (ResultSet rs = pst.executeQuery()) {
            exists = rs.next();
        }
    } catch (SQLException e) {
        System.out.println("Error checking for unique ID 1: " + e.getMessage());
    }

    return !exists;
}
    private boolean checkIfExists1(int id) throws ClassNotFoundException {
    boolean exists = false;
     Class.forName("com.mysql.cj.jdbc.Driver");
        
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
         PreparedStatement pst = conn.prepareStatement("SELECT 1 FROM client WHERE id_client =?")) {
        pst.setInt(1, id);
        try (ResultSet rs = pst.executeQuery()) {
            exists = rs.next();
        }
    } catch (SQLException e) {
        System.out.println("Error checking for unique ID 1: " + e.getMessage());
    }

    return !exists;
}
    
    
   public void initialisetable() {
    System.out.println("Initialisation de la table...");
    produittable.getItems().clear();
    produittable.getColumns().clear();
    produittable.getColumns().addAll(nom1, quantité, prixun, prixtot);

    produittable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    produittable.getColumns().get(0).setPrefWidth(200);
    produittable.getColumns().get(1).setPrefWidth(100);
    produittable.getColumns().get(2).setPrefWidth(100);
    produittable.getColumns().get(3).setPrefWidth(100);
    
    
    try {
        System.out.println("Chargement du driver MySQL...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn12 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

        System.out.println("Connexion établie avec succès.");

        try {
            System.out.println("Exécution de la requête SQL pour récupérer les id_produit_produit...");
            String sql = "SELECT id_produit_produit FROM contenir2 WHERE id_facture_facture =?";
            PreparedStatement pstmt = conn12.prepareStatement(sql);
            pstmt.setInt(1, idf); // idf est l'ID de la facture

            System.out.println("Requête préparée avec succès.");

            // Exécutez la requête et récupérez les résultats
            ResultSet resultSet = pstmt.executeQuery();

            System.out.println("Requête exécutée avec succès.");

            // Créez une liste pour stocker les id_produit_produit
            List<Integer> idProduitList = new ArrayList<>();

            // Parcourez les résultats et ajoutez les id_produit_produit à la liste
            while (resultSet.next()) {
                idProduitList.add(resultSet.getInt("id_produit_produit"));
            }

            System.out.println("Nombre d'id_produit_produit trouvés : " + idProduitList.size());

            // Créez une requête SQL pour récupérer les informations de la table produit
            String sql2 = "SELECT p.nom_produit, c.quantite, p.prixu_produit, c.prix_total " +
                          "FROM contenir2 c " +
                          "JOIN produit p ON c.id_produit_produit = p.id_produit " +
                          "WHERE c.id_facture_facture =?";

            pstmt = conn12.prepareStatement(sql2);
            pstmt.setInt(1, idf); // idf est l'ID de la facture

            System.out.println("Requête préparée avec succès.");

            // Exécutez la requête et récupérez les résultats
            resultSet = pstmt.executeQuery();

            System.out.println("Requête exécutée avec succès.");

            // Créez une liste pour stocker les produits
            ObservableList<Produit> produits = FXCollections.observableArrayList();

            // Parcourez les résultats et ajoutez les produits à la liste
            while (resultSet.next()) {
                Produit produit = new Produit(
                    resultSet.getString("nom_produit"),
                    resultSet.getInt("quantite"),
                    resultSet.getFloat("prixu_produit"),
                    resultSet.getFloat("prix_total")
                );
                produits.add(produit);
            }

            System.out.println("Nombre de produits trouvés : " + produits.size());

            nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            quantité.setCellValueFactory(new PropertyValueFactory<>("quantité"));
            prixun.setCellValueFactory(new PropertyValueFactory<>("prixu"));
            prixtot.setCellValueFactory(new PropertyValueFactory<>("prixt"));

            // Ajoutez les produits à la table
            produittable.getItems().addAll(produits);

            System.out.println("Produits ajoutés à la table avec succès.");

        } catch (SQLException e) {
            System.err.println("Erreur de requête SQL : " + e.getMessage());
            System.err.println("Paramètres : " + idf);
        } finally {
            try {
                conn12.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture de la connexion : " + e.getMessage());
            }
        }
    } catch (ClassNotFoundException e) {
        System.err.println("Erreur de classe non trouvée : " + e.getMessage());
    } catch (SQLException ex) {
        Logger.getLogger(MAKECOMMANDEController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
   String prix = String.valueOf(getTotalPriceForOrderId(idf));
   prixvue.setText(prix);

   System.out.println("Prix total : " + prix);
}

    
    
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

    Produit(String nom, int quantité, Float prixu, Float prixt) {
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
    
    public double getTotalPriceForOrderId(int idcon) {
    double totalPrice = 0.0;

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
         PreparedStatement statement = connection.prepareStatement("SELECT prix_total FROM contenir2 WHERE id_facture_facture = ?")) {
        statement.setInt(1, idcon);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Double prixein = resultSet.getDouble("prix_total");
            System.out.println("le prix de ce produit est "+prixein);
            totalPrice = totalPrice + prixein;
        }
        System.out.println("le prix total est : " + totalPrice);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalPrice;
}
    
    
}
