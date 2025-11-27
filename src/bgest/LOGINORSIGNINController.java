 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import static com.sun.javafx.scene.control.skin.FXVK.Type.EMAIL;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.fxml.LoadException;
import java.sql.*;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LOGINORSIGNINController implements Initializable {

    @FXML
    private ImageView i1;
    @FXML
    private Label a1;
    @FXML
    private Label a2;
    @FXML
    private Label a3;
    @FXML
    private Label a4;
    @FXML
    private JFXButton signin;
    @FXML
    private Label c1;
    @FXML
    private Label c2;
    @FXML
    private Label c3;
    @FXML
    private JFXButton SIGNUP;
    private JFXButton btn3;
    @FXML
    private Label b1;
    @FXML
    private TextField b2;
    @FXML
    private PasswordField b3;
    @FXML
    private JFXButton CONTINIOUS;
    @FXML
    private TextField d2;
    @FXML
    private PasswordField d3;
    @FXML
    private Label d1;
    @FXML
    private PasswordField d4;
    @FXML
    private AnchorPane layer1;
    @FXML
    private AnchorPane layer0;
    @FXML
    private AnchorPane layer2;
    @FXML
    private ImageView i2;
    @FXML
    private TextField d5;
    @FXML
    private DatePicker R2;
    @FXML
    private TextField d21;
    
    int idboulangerie;
    int idgerant;
    @FXML
    private Label b11;
    @FXML
    private JFXRadioButton radiocaissier;
    @FXML
    private ToggleGroup employetype;
    @FXML
    private JFXRadioButton radioboulanger;
    @FXML
    private Hyperlink gerantlink;
    
    

    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
    idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
        // TODO b12.setVisible (false);
    signin.setVisible (false);
    c1.setVisible (false);
    c2.setVisible (false);
    c3.setVisible (false);
    i2.setVisible (true);
    
    CONTINIOUS.setVisible (true);
    i1.setVisible (true);
    d1.setVisible (false);
    d2.setVisible (false);
    d21.setVisible (false);
    R2.setVisible (false);
    d3.setVisible (false);
    d4.setVisible (false);
    d5.setVisible (false);

    b2.setVisible (true);
    b3.setVisible (true);
    b1.setVisible (true);
    b11.setVisible (true);
    a3.setVisible (true);
    
    String nombou= "ours bakery";
    
    
    System.out.println(" l'id de la boulangerie est :" + idboulangerie);
    System.out.println(" l'id du  gerant est :" + idgerant);
    
    try (Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakerygest", "root", "")) {
    String sql1 = "SELECT nom_boulangerie FROM boulangerie WHERE id_boulangerie =? ";
    PreparedStatement statement21 = connection2.prepareStatement(sql1);
    statement21.setInt(1, idboulangerie);
    ResultSet resultSet2 = statement21.executeQuery();

    if (resultSet2.next()) {
        nombou = resultSet2.getString("nom_boulangerie");
    } else {
        nombou = "ours bakery";
    }
} catch (SQLException exp) {
    System.out.println(exp.getMessage());
}
    
    b11.setText(nombou);


    }

@FXML
private void btn (MouseEvent event) {

    TranslateTransition slide = new TranslateTransition();
    slide.setDuration(Duration.seconds(1));
    slide.setNode(layer2);
    slide.setToX(330);
    slide.play();
    layer1.setTranslateX(-309);
    
    
    i2.setVisible (true);
    i1.setVisible (true);
    c1.setVisible (true);
    c2.setVisible (true);
    c3.setVisible (true);
    a3.setVisible (true);
    signin.setVisible (true);
    
    SIGNUP.setVisible (false);
    a1.setVisible (false);
    a2.setVisible (false);
    a4.setVisible (false);
    
    CONTINIOUS.setVisible (true);
    d1.setVisible (true);
    d2.setVisible (true);
    d21.setVisible (true);
    R2.setVisible (true);
    d3.setVisible (true);
    d4.setVisible (true);
    d5.setVisible (true);
    
    b1.setVisible (false);
    b11.setVisible (false);
    b2.setVisible (false);
    b3.setVisible (false);
    


    slide.setOnFinished((e->{


    }));
}

    @FXML
    private void btn2(MouseEvent event) {
        
         TranslateTransition slide = new TranslateTransition();
    slide.setDuration(Duration.seconds(1));
    slide.setNode(layer2);
    slide.setToX(0);
    slide.play();
    layer1.setTranslateX(0);
   
    i1.setVisible (true);
    i2.setVisible (true);
    CONTINIOUS.setVisible (true);
    c1.setVisible (false);
    c2.setVisible (false);
    c3.setVisible (false);
    a3.setVisible (true);
    signin.setVisible (true);
    
    SIGNUP.setVisible (false);
    a1.setVisible (true);
    a2.setVisible (true);
    a4.setVisible (true);
    
    
    d1.setVisible (false);
    d2.setVisible (false);
    d21.setVisible (false);
    R2.setVisible (false);
    d3.setVisible (false);
    d4.setVisible (false);
    d5.setVisible (false);
    b1.setVisible(true);
    b11.setVisible(true);
    b2.setVisible (true);
    b3.setVisible (true);
    SIGNUP.setVisible(true);
    signin.setVisible(false);

    slide.setOnFinished((e->{


    }));
        
    }
    
    Statement pstmt;
    Connexion maConnexion = new Connexion();
   

        

    @FXML
private void btn3(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {
    String email = d5.getText();
    String nom = d2.getText();
    String prenom = d21.getText();
    LocalDate dateembauche = R2.getValue();
    String password = d3.getText();
    String confirmPassword = d4.getText();
    String emailorusername = b2.getText();
    String verifiepassword = b3.getText();

    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Make sure that all fields are filled");

    Alert defferentpassword = new Alert(Alert.AlertType.ERROR);
    defferentpassword.setTitle("alert");
    defferentpassword.setContentText(" The passwords do not match");

    Alert incorectpassword = new Alert(Alert.AlertType.ERROR);
    incorectpassword.setTitle("alert");
    incorectpassword.setContentText("invalid password");
    
    Alert alertt = new Alert(Alert.AlertType.ERROR);
    alertt.setContentText("Une erreur a été détectée. Veuillez réessayer.");
    
    Alert alert5 = new Alert(Alert.AlertType.ERROR);
                     alert5.setTitle("Erreur");
                     alert5.setHeaderText("Adresse e-mail invalide");
                     alert5.setContentText("Veuillez entrer une adresse e-mail valide.");
                     
    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                   aler.setContentText("CONECTION SUCCES");  
                   
     Alert alertO = new Alert(Alert.AlertType.ERROR);
                   alertO.setContentText("AN ERROR HAS DETECT PLEASE TRY AGAIN LATER");
      
     Alert alertKL = new Alert(Alert.AlertType.ERROR);
                   alertKL.setContentText("USER NOT FOUND");
                     
      String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
             

    
    int x = 0;

    if (d1.isVisible()) {
        if (email.isEmpty() || prenom.isEmpty() || nom.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()|| dateembauche == null) {
            alert.showAndWait();
            x = 1;
        } else {
            
            // Définir l'expression régulière pour valider le format de l'adresse e-mail
            
            // Vérifier si la valeur entrée correspond au format de l'adresse e-mail
            if (!email.matches(emailRegex)) {
                  // Afficher un message d'erreur si la valeur entrée n'est pas une adresse e-mail valide
                     
                     alert5.showAndWait();
                     x = 1;
                     return;
            }
            
            if (password.equals(confirmPassword)) {
                
            } else {
                defferentpassword.showAndWait();
                x = 1;
            }
        }
        
        String sql = "INSERT INTO caissiere (nom_caissiere,prenom_caissiere,date_embauche_caissiere,password_caissiere,email_caissiere,	id_boulangerie_boulangerie) VALUES ('" + nom + "','" + prenom + "','" + dateembauche + "','" + password + "', '" + email + "','" + idboulangerie + "')";
        String sql2 = "INSERT INTO boulange_patissier (nom_p,premon_p,date_embaouche_p,password_p,email_p,id_boulangerie_boulangerie) VALUES ('" + nom + "','" + prenom + "','" + dateembauche + "','" + password + "', '" + email + "','" + idboulangerie + "')";
        
        
        try {
            // Assurez-vous que pstmt n'est pas null
                 pstmt = maConnexion.obtenirConnexion().createStatement();
                 
                 if(radiocaissier.isSelected()){
                 pstmt.executeUpdate(sql);
                 }
                 else{ 
                     if(radioboulanger.isSelected()){
                        pstmt.executeUpdate(sql2); 
                     }
                     else{
                        Alert alerx = new Alert(Alert.AlertType.ERROR);
                   alerx.setContentText("VEUILLEZ CHOISIR UN DES BOUTTONS RADIO ");
                   alerx.showAndWait();
     
                     }
                     }
        } catch (SQLException e) {
            System.out.println( "imposible de remplire le table caissier/BOULLANGER : " +e );
            alertt.showAndWait();
            x = 1;
        } 
    

    }
    else {
        if (emailorusername.isEmpty() || verifiepassword.isEmpty()) {
            alert.showAndWait();
            x = 1;
        } 
        if(radiocaissier.isSelected()){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "")) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                
                if (emailorusername.matches(emailRegex)) {
                    System.out.println("recherche par mail");
                    String sql13 = "SELECT id_caissiere, password_caissiere FROM caissiere WHERE email_caissiere = ?";
                    
                     
                    PreparedStatement statement34 = connection.prepareStatement(sql13);
                    statement34.setString(1, emailorusername);
                    ResultSet resultSet34 = statement34.executeQuery(sql13);

                    if (resultSet34.next()) {
                            String userId = resultSet34.getString("id_caissiere");
                            String storedPassword = resultSet34.getString("password_caissiere");

                            if (verifiepassword.equals(storedPassword)) {
                                aler.showAndWait();
                            } else {
                                        incorectpassword.showAndWait();
                                        x = 1;
                            }
                        } else {
                            alertKL.showAndWait();
                            x = 1;
                        }
                    } else {
                        System.out.println("recherche par nom");
                        String sql = "SELECT id_caissiere, password_caissiere FROM caissiere WHERE nom_caissiere = ?";

                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, emailorusername);
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                        String userId = resultSet.getString("id_caissiere");
                        String storedPassword = resultSet.getString("password_caissiere");

                            if (verifiepassword.equals(storedPassword)) {
                                aler.showAndWait();
                            } else {
                                    incorectpassword.showAndWait();
                                    x = 1;
                            }
                        } else {
                                alertKL.showAndWait();
                                x = 1;
                        }
                 }
                } catch (SQLException e) {
                    System.out.println("connection a la base de donné impossible" + e);
                    alertO.showAndWait();
                    x = 1;
                }
        }
        else{
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "")) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                
                if (emailorusername.matches(emailRegex)) {
                    System.out.println("recherche par mail");
                    
                     String sql13 = "SELECT id_p , password_p FROM boulange_patissier WHERE email_p = ?";
                  
                     
                    PreparedStatement statement34 = connection.prepareStatement(sql13);
                    statement34.setString(1, emailorusername);
                    ResultSet resultSet34 = statement34.executeQuery(sql13);

                    if (resultSet34.next()) {
                            String userId = resultSet34.getString("id_p");
                            String storedPassword = resultSet34.getString("password_p");

                            if (verifiepassword.equals(storedPassword)) {
                                aler.showAndWait();
                            } else {
                                        incorectpassword.showAndWait();
                                        x = 1;
                            }
                        } else {
                            alertKL.showAndWait();
                            x = 1;
                        }
                    } else {
                        System.out.println("recherche par nom");
                        String sql = "SELECT id_p , password_p FROM boulange_patissier WHERE nom_p = ?";

                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, emailorusername);
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                        String userId = resultSet.getString("id_p");
                        String storedPassword = resultSet.getString("password_p");

                            if (verifiepassword.equals(storedPassword)) {
                                aler.showAndWait();
                            } else {
                                    incorectpassword.showAndWait();
                                    x = 1;
                            }
                        } else {
                                alertKL.showAndWait();
                                x = 1;
                        }
                 }
                } catch (SQLException e) {
                    System.out.println("connection a la base de donné impossible" + e);
                    alertO.showAndWait();
                    x = 1;
                }
        }

    }

    if (x == 0) {
        
       if(radiocaissier.isSelected()){ 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("center.fxml"));
        Parent root = loader.load();

        Scene scene = CONTINIOUS.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
    }
       else{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("center.fxml"));
        Parent root = loader.load();

        Scene scene = CONTINIOUS.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
       }
       
    }
}

    @FXML
    private void gerantlinkclique(MouseEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterconecteruneboulangerie.fxml"));
        Parent root = loader.load();

        Scene scene = gerantlink.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
        
    }

    
}