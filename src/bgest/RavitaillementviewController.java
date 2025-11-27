/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;


import com.jfoenix.controls.JFXButton;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class RavitaillementviewController implements Initializable {

    @FXML
    private TextField Searchbar;
    @FXML
    private JFXButton facture;
    @FXML
    private Label nomboulangerie;
    @FXML
    private ImageView logoboulangerie;
    @FXML
    private JFXButton REFREACH;
    @FXML
    private Pane optionpane;
    @FXML
    private JFXButton btnfournisseur;
    @FXML
    private Label APPNAME;
    @FXML
    private JFXButton accesprodut;
    @FXML
    private JFXButton viewcommand;
    @FXML
    private JFXButton dashbord;
    @FXML
    private JFXButton boutonacceuil;
    @FXML
    private JFXButton demandeaufournisseur;
    @FXML
    private JFXButton MONCOMPTE;
    @FXML
    private ImageView IMAUTILISATEUR;
    @FXML
    private JFXButton COMPTESUTILISATEURS;
    @FXML
    private ImageView IMGCOMTES;
    @FXML
    private HBox VBOXCOMMANDEVIEW;
    
    int idboulangerie;
     int      idgerant;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       idboulangerie= GestionnaireID.getIdboulangerie();
                idgerant = GestionnaireID.getIdgerant();

    
                
                GestionnaireID.setIdgerant(idgerant);
                GestionnaireID.setIdboulangerie(idboulangerie);

                
                
                
                try {
                    initialize();
                    // TODO
                } catch (IOException ex) {
                    Logger.getLogger(COMMANDESVIEWController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(COMMANDESVIEWController.class.getName()).log(Level.SEVERE, null, ex);
                }


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


    
    
     TranslateTransition slide = new TranslateTransition();
    slide.setDuration(Duration.seconds(0.4));     
    slide.setNode(optionpane);
    slide.setToX(-190);
    slide.play();
    APPNAME.setVisible(false);
    viewcommand.setStyle("-fx-background-color: white; -fx-text-fill: black;-fx-background-radius: 36 0 0 36");
   
    
    
        
    }    

    @FXML
    private void btnfacture(MouseEvent event) {
        
        String fxurl = "MAKERAVITAILLEMENT.fxml";
        
        ALGORITHME.setFxurl(fxurl);
        ALGORITHME.showOverlayScene();
        
    }

    @FXML
    private void btnfournisseurclique(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("COMMANDESVIEW.fxml"));
        Parent root = loader.load();

        Scene scene = viewcommand.getScene();
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
    private void dashbordclique(MouseEvent event)  throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Parent root = loader.load();

        Scene scene = dashbord.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
    }

    @FXML
    private void btnacceuil(MouseEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("center.fxml"));
        Parent root = loader.load();

        Scene scene = accesprodut.getScene();
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
    private void optionpanemousemove(MouseEvent event) {
        
         TranslateTransition slide2 = new TranslateTransition();
    slide2.setDuration(Duration.seconds(0.4));     
    slide2.setNode(optionpane);
    slide2.setToX(0);
    slide2.play();
    APPNAME.setVisible(true);
        
    }
    
    @FXML
    private void optionpaneeexite(MouseEvent event) {
        
         TranslateTransition slide3 = new TranslateTransition();
    slide3.setDuration(Duration.seconds(0.4));     
    slide3.setNode(optionpane);
    slide3.setToX(-190);
    slide3.play();
    APPNAME.setVisible(false);
        
    }
    
    @FXML
    private void REFREACHCLIQUE(MouseEvent event) throws IOException, ClassNotFoundException {
    
    initialize();
        
    }

    
    
    public void initialize() throws IOException, ClassNotFoundException {
       VBOXCOMMANDEVIEW.getChildren().clear();
       
    try {
        // Charger le driver MySQL pour établir une connexion à la base de données
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Établir une connexion à la base de données avec les informations de connexion
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

        // Préparer une requête SQL pour récupérer les produits associés à la commande
        String sql = "select id_fournir  from fournir where ib_boulangerie_boulangerie = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        // Définir le paramètre de la requête (idCommande) pour récupérer les produits de la commande spécifique
        preparedStatement.setInt(1, idboulangerie);

        ResultSet resultSet252 = preparedStatement.executeQuery();

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        VBOXCOMMANDEVIEW.getChildren().add(vbox);

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        vbox.getChildren().add(hbox);

        int count = 0;

        while (resultSet252.next()) {
            int idfournir = resultSet252.getInt("id_fournir");
            ModelravvController.setfournirid(idfournir);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modelravv.fxml"));

            // Load the FXML file and get the controller instance
            Parent produitModelRootx = loader.load();

            hbox.getChildren().add(produitModelRootx);
            count++;

            if (count == 4) {
                hbox = new HBox();
                hbox.setSpacing(10);
                vbox.getChildren().add(hbox);
                count = 0;
            }
        }

    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
}


    
    
    
}
