/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterconecteruneboulangerieController implements Initializable {

    @FXML
    private Pane creationpane2;
    @FXML
    private JFXButton accederboulangerie;
    @FXML
    private Pane connectionpane2;
    @FXML
    private JFXButton creecomdeboulangerie;
    @FXML
    private Pane creationpane;
    @FXML
    private TextField bnom1;
    @FXML
    private TextField badresse;
    @FXML
    private TextField nomgerant;
    @FXML
    private TextField prenomgerant;
    @FXML
    private DatePicker dateembauche;
    @FXML
    private JFXButton ajouter;
    @FXML
    private Label labellogo;
    @FXML
    private JFXButton insettlogo;
    @FXML
    private PasswordField bpassword1;
    @FXML
    private PasswordField gerantpassword;
    @FXML
    private Pane cgerantpane;
    @FXML
    private PasswordField cgerantpassword;
    @FXML
    private TextField cnomgerant;
    @FXML
    private ToggleButton geranttogle;
    @FXML
    private PasswordField cboulangeriepassword;
    @FXML
    private TextField cboulangerienom;
    @FXML
    private JFXButton continuer;
    @FXML
    private Pane connectionpane;
    
     int x=0;
     
     String path2=null;
    @FXML
    private TextField cnomgerant1;
    @FXML
    private TextField emailgerant;
    @FXML
    private TextField devise;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        creationpane2.setVisible(false);
        creationpane.setVisible(false);
        cgerantpane.setVisible(false);
        
         geranttogle.selectedProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue) {
        cgerantpane.setVisible(true);
        x = 1;
    } else {
        cgerantpane.setVisible(false);
        x = 0;
    }
    });
        
        
        
    }    

    private void accederboulangerieclique(MouseEvent event) {
        
         creationpane2.setVisible(false);
        creationpane.setVisible(false);
        
       
        
    }

    @FXML
    private void creecomdeboulangerieclique(MouseEvent event) {
        
         creationpane2.setVisible(true);
        creationpane.setVisible(true);
        
        connectionpane2.setVisible(false);
        connectionpane.setVisible(false);
    }

    
    int t=0;
    
    
    @FXML
    private void ajouterclique(MouseEvent event) throws ClassNotFoundException, IOException {
        
     Random random = new Random();
       int y;
    boolean uniqueIdFound = false;

    do {
         y = random.nextInt(10000000);
         uniqueIdFound = checkIfExists(y);
    } while (!uniqueIdFound);
   
    GestionnaireID.setIdgerant(y);
    
    
    Random random1 = new Random();
    int r;
    boolean uniqueIdFound1 = false;

    do {
       r = random1.nextInt(10000000);
       uniqueIdFound1 = checkIfExists2(r);
    } while (!uniqueIdFound1);
    GestionnaireID.setIdboulangerie(r);

    
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Make sure that all fields are filled");
    
     Alert alert1 = new Alert(Alert.AlertType.ERROR);
    alert1.setContentText("conection au serveur momentanement indisponible");
    
     Alert alert5 = new Alert(Alert.AlertType.ERROR);
                     alert5.setTitle("Erreur");
                     alert5.setHeaderText("Adresse e-mail invalide");
                     alert5.setContentText("Veuillez entrer une adresse e-mail valide.");
   
    
        
        String nomboulangerie = bnom1.getText();
        String adressboulangerie = badresse.getText();
        String passwordboulangerie = bpassword1.getText();
        String devises = devise.getText();
        
        String nomgeran = nomgerant.getText();
        String prenomgeran = prenomgerant.getText();
        LocalDate dateembauch = dateembauche.getValue();
        String passwordgerant = gerantpassword.getText();
        String email_gerant = emailgerant.getText();
        
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
      
        
        if(nomboulangerie.isEmpty() || devises.isEmpty() || adressboulangerie.isEmpty() || passwordboulangerie.isEmpty() ){
        alert.showAndWait();
        t=1;
       }else{
            if(x==1 && (nomgeran.isEmpty() || dateembauch==null || prenomgeran.isEmpty() || passwordgerant.isEmpty() || email_gerant.isEmpty())){
              alert.showAndWait();
               t=1;  
            }
            else{
                if (!email_gerant.matches(emailRegex)) {
                  // Afficher un message d'erreur si la valeur entrée n'est pas une adresse e-mail valide
                     
                     alert5.showAndWait();
                     x = 1;
                     return;
            }
                t=0;
            }
            t=0;
        }
        
        String sqlquery1 = "insert into gerant (nom_gerant,prenom_gerant,id_gerant,password_gerant,dateembauche_gerant,email_gerant) values (?,?,?,?,?,?)";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker","root",""); 
            PreparedStatement pst = conn.prepareStatement(sqlquery1);
            pst.setString(1,nomgeran);
            pst.setString(2,prenomgeran);
            pst.setInt(3,y);
            pst.setString(4,passwordgerant);
            pst.setDate(5, java.sql.Date.valueOf(dateembauch));
            pst.setString(6, email_gerant);
           
            // Execute the first query
            pst.executeUpdate();
            
            
        }catch (Exception e) {
            
            System.out.println("erreur lors du remplissage de la table gérant :" +e);
            alert1.showAndWait();
            t=1;
        }
        
        String sqlquery2 = "insert into boulangerie (nom_boulangerie,adresse_boulangerie,id_gerant_gerant,password_boulangerie,id_boulangerie,logo_boulangerie, `unité`) values (?,?,?,?,?,?,?)";
        
        try{
            Properties props = new Properties();
            props.setProperty("maxAllowedPacket", "2000M"); 
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker","root",""); 
            PreparedStatement pst = conn1.prepareStatement(sqlquery2);
            pst.setString(1,nomboulangerie);
            pst.setString(2,adressboulangerie);
            pst.setInt(3,y);
            pst.setString(4,passwordboulangerie);
            pst.setInt(5,r);
            
            InputStream is = new FileInputStream(new File(path2));
            pst.setBlob(6, is);
            pst.setString(7, devises);
            pst.executeUpdate();
            
        }catch (Exception ex) {
            
            System.out.println("erreur lors du remplissage de la table boulangerie : " +ex);
            alert1.showAndWait();
            t=1;
            
        }
         if(t==0){
            
                  
             FXMLLoader loader = new FXMLLoader(getClass().getResource("center.fxml"));
        Parent root = loader.load();

        Scene scene = continuer.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
        
        }
        
        
        
    }

    @FXML
    private void insertlogocliuque(MouseEvent event) {
        
        
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG file (*.jpg)","*.JPG");
        filechooser.getExtensionFilters().add(extFilterJPG);
        Stage primaryStage = null;  
        
        File selectedFile = filechooser.showOpenDialog(primaryStage);
        String path = selectedFile.getAbsolutePath();
        Image img = new Image("file:" + path);
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(95);
        imageView.setFitHeight(79);
        labellogo.setGraphic(imageView);
        path2=path;
    }

    @FXML
    private void continuerclique(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Make sure that all fields are filled");
    
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setContentText("conection au serveur momentanement indisponible");
     
        
        
        String cnomboulangerie = cboulangerienom.getText();
        String cpasswordboulangerie =cboulangeriepassword.getText();
        
        String  Cnomgerant = cnomgerant.getText();
        String cpasswordgerant = cgerantpassword.getText();
        String email_gerant1 = cnomgerant1.getText();
     
        int xy=0;
        int tz=0;
        
        if (cnomboulangerie.isEmpty() || cpasswordboulangerie.isEmpty() ){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
           alert2.setContentText("veillez vous assurer que le nom de la boulagerie et sont monde passe soit rempli");
           alert2.showAndWait();
           xy=1;
        }
        else{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker","root",""); 

                 // Create a PreparedStatement to query the database
            PreparedStatement pstmt3 = conn3.prepareStatement("SELECT id_boulangerie FROM boulangerie WHERE nom_boulangerie =?");

                  // Set the parameter value
            pstmt3.setString(1, cnomboulangerie);

                // Execute the query
            ResultSet rs3 = pstmt3.executeQuery();

                // Check if the query returns any results
            if (rs3.next()) {
                // Get the id_boulangerie values
            List<Integer> idBoulangeries = new ArrayList<>();
            do {
                    idBoulangeries.add(rs3.getInt("id_boulangerie"));
            } while (rs3.next());

                    // Check if the password matches one of the id_boulangerie values
            pstmt3 = conn3.prepareStatement("SELECT password_boulangerie FROM boulangerie WHERE id_boulangerie =? AND password_boulangerie =?");
            for (int idBoulangerie : idBoulangeries) {
                    pstmt3.setInt(1, idBoulangerie);
                    pstmt3.setString(2, cpasswordboulangerie);
                    ResultSet rs34 = pstmt3.executeQuery();
                    if (rs34.next()) {
                        // Password matches, set t to 0 and break the loop
                        tz = 0;
                        GestionnaireID.setIdboulangerie(idBoulangerie); // Set the id_boulangerie value
                        break;
                   }
                    else{
                        tz=1;
                    }
            }
               if (tz != 0) {
                   // Password doesn't match, set t to 1
                   Alert alert2 = new Alert(Alert.AlertType.ERROR);
                   alert2.setContentText("le mot de passe de la boulangerie est invalid");
                   alert2.showAndWait();
                   t = 1;
                }
            } else {
                    // cnomboulangerie doesn't exist, set t to 1
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                   alert2.setContentText("boulangerie introuvable dans la base de donné");
                   alert2.showAndWait();
                    t = 1;
                    tz=1;
            }
        
        
       if(tz==0){ 
        if(x==1){
               Class.forName("com.mysql.cj.jdbc.Driver");
               Connection conn23 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker","root",""); 
            
            
              if (cnomboulangerie.isEmpty() || Cnomgerant.isEmpty() || cpasswordgerant.isEmpty() || cpasswordboulangerie.isEmpty() || email_gerant1.isEmpty()){
                 alert.showAndWait();
                 t = 1;
              }
              else{
                  int tx=0;
                  // Create a PreparedStatement to query the database
                PreparedStatement pstmt33 = conn23.prepareStatement("SELECT id_gerant FROM gerant WHERE nom_gerant =? AND password_gerant =? AND  email_gerant =? ");

                    // Set the parameter values
                pstmt33.setString(1, Cnomgerant);
                pstmt33.setString(2, cpasswordgerant);
                pstmt33.setString(3, email_gerant1);
                    // Execute the query
                ResultSet rs33 = pstmt33.executeQuery();

                    // Check if the query returns any results
                if (rs33.next()) {
                    // Get the id_gérant values
                List<Integer> idGérants = new ArrayList<>();
                do {
                        idGérants.add(rs33.getInt("id_gerant"));
                } while (rs33.next());
                 PreparedStatement pstmt22;
                     // Check if the password matches one of the id_gérant values
                pstmt22 = conn23.prepareStatement("SELECT 	password_gerant FROM gerant WHERE id_gerant =? AND password_gerant =?");
                for (int idGérant : idGérants) {
                        pstmt22.setInt(1, idGérant);
                        pstmt22.setString(2, cpasswordgerant);
                        ResultSet rs22 = pstmt22.executeQuery();
                if (rs22.next()) {
                        // Password matches, set t to 0 and break the loop
                        GestionnaireID.setIdgerant(idGérant);
                        tx = 0;
                        t = 0;
                    break;
                }
                else{
                    tx=1;
                }
                }
                    if (tx!= 0) {
                        // Password doesn't match, set t to 1
                        
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setContentText("le mot de passe du gérant est invalid");
                        alert2.showAndWait();
                        t = 1;
                    }
                } else {
                        // Cnomgerant doesn't exist, set t to 1
                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setContentText("ce gérant n'existe pas dans notre base de donné");
                        alert3.showAndWait();
                        t = 1;
                  }
                  
            }
        }
              
              
              
        if(t==0){
            if(x==1){
                  
             FXMLLoader loader = new FXMLLoader(getClass().getResource("center.fxml"));
        Parent root = loader.load();

        Scene scene = continuer.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
        
        }
        else{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LOGIN OR SIGN IN.fxml"));
        Parent root = loader.load();

        Scene scene = continuer.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
            
        }
        
      }
     }   
    }
    }

    @FXML
    private void accederboulangerieclique1(MouseEvent event) {
    
        
        creationpane2.setVisible(false);
        creationpane.setVisible(false);
        
        connectionpane2.setVisible(true);
        connectionpane.setVisible(true);
        
        
    }
    
    
    
    private boolean checkIfExists(int id) throws ClassNotFoundException {
    boolean exists = false;
     Class.forName("com.mysql.cj.jdbc.Driver");
        
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
         PreparedStatement pst = conn.prepareStatement("SELECT 1 FROM gerant WHERE id_gerant =?")) {
        pst.setInt(1, id);
        try (ResultSet rs = pst.executeQuery()) {
            exists = rs.next();
        }
    } catch (SQLException e) {
        System.out.println("Error checking for unique ID 1: " + e.getMessage());
    }

    return !exists;
}
    
    private boolean checkIfExists2(int id) throws ClassNotFoundException {
    boolean exists2 = false;
    Class.forName("com.mysql.cj.jdbc.Driver");
        
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
         PreparedStatement pst = conn.prepareStatement("SELECT 1 FROM boulangerie WHERE id_boulangerie =?")) {
        pst.setInt(1, id);
        try (ResultSet rs = pst.executeQuery()) {
            exists2 = rs.next();
        }
    } catch (SQLException e) {
        System.out.println("Error checking for unique ID 2: " + e.getMessage());
    }

    return !exists2;
}
    
    
    
    
}
