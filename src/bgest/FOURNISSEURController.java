/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FOURNISSEURController implements Initializable {

    @FXML
    private Pane optionpane;
    private JFXButton BTNFOURNISSEUR;
    @FXML
    private Label APPNAME;
    @FXML
    private JFXButton boutonacceuil;
    
    int idboulangerie;
    int idgerant;
    @FXML
    private Pane searchbarpane;
    @FXML
    private TextField searchbar;
    @FXML
    private Label nomboulangerie;
    @FXML
    private ImageView logoboulangerie;
    @FXML
    private JFXButton accesprodut;
    @FXML
    private JFXButton viewcommand;
    @FXML
    private JFXButton dashbord;
    @FXML
    private JFXButton demandeaufournisseur;
    @FXML
    private Pane ajouterfournisseur;
    @FXML
    private JFXButton btnajouterfournisseur;
    @FXML
    private JFXButton enregistrer;
    @FXML
    private JFXButton btnfournisseur;
    @FXML
    private JFXButton MONCOMPTE;
    @FXML
    private ImageView IMAUTILISATEUR;
    @FXML
    private JFXButton COMPTESUTILISATEURS;
    @FXML
    private ImageView IMGCOMTES;
    @FXML
    private JFXButton update;
    @FXML
    private TableView<Fournisseur > fournisseurtable;
    
    TableColumn<Fournisseur, Integer> ID = new TableColumn<>("ID");   
    TableColumn<Fournisseur, String> nom = new TableColumn<>("NOM");
    TableColumn<Fournisseur, String> entreprise = new TableColumn<>("ENTREPRISE");
    TableColumn<Fournisseur, String> email = new TableColumn<>("EMAIL");
    TableColumn<Fournisseur, Integer> tell = new TableColumn<>("TELLEPHONE");
    @FXML
    private JFXButton supprimer;
    @FXML
    private Pane usepane;
    @FXML
    private TextField nomtaxtfield;
    @FXML
    private TextField fournisseurtextfield;
    @FXML
    private TextField emailtextfield;
    @FXML
    private TextField numteltextfield;
    
    int id;
    @FXML
    private JFXComboBox<String> typesearch;
    
    int type1;
    
    String recherche;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    usepane.setVisible(false);
    update.setDisable(true);
    enregistrer.setVisible(false);
    supprimer.setDisable(true);
        
    idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
    
    if(idgerant==0){
        COMPTESUTILISATEURS.setVisible(false);
        IMGCOMTES.setVisible(false);
    }
    
        System.out.println(" l'id de la boulangerie est :" + idboulangerie);
        System.out.println(" l'id du  gerant est :" + idgerant);
        
    TranslateTransition slide = new TranslateTransition();
    slide.setDuration(Duration.seconds(0.4));     
    slide.setNode(optionpane);
    slide.setToX(-190);
    slide.play();
    APPNAME.setVisible(false);
        
        try {
            initialisetable ();
        } catch (SQLException ex) {
            Logger.getLogger(FOURNISSEURController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        fournisseurtable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Fournisseur> obs, Fournisseur oldValue, Fournisseur newValue) -> {
    
                          
                             
          if (newValue!= null) {
                               usepane.setVisible(true);
                               update.setVisible(true);
                               update.setDisable(false);
                               enregistrer.setVisible(false);
                               supprimer.setVisible(true);
                               supprimer.setDisable(false);
                               
                                id = newValue. getId_f();
                                String nom = newValue.getNom_f();
                                String entreprise = newValue.getEntreprise_f();
                                String email = newValue.getEmail_f();
                                int numtell = newValue.getNum_tel_f();
                                
                                
                                nomtaxtfield.setText(entreprise);
                                fournisseurtextfield.setText(nom);
                                emailtextfield.setText(email);
                                numteltextfield.setText(String.valueOf(numtell));        
          }
                                
        });
                                 
        
        ObservableList<String> optionsSearch = FXCollections.observableArrayList("par nom", "par entreprise");
        typesearch.setItems(optionsSearch);
        typesearch.setValue("par nom");

typesearch.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
    String categorie = newVal;
    if (categorie.equals("par nom")) {
        
        type1 = 0;
    } else {
        
            type1 = 1;
        
            
        }
    
});

searchbar.setOnAction((ActionEvent e) -> {
    recherche = searchbar.getText();

    if (!recherche.isEmpty()) {
        if (type1 == 0) {
            try {
                rechercheparnom (recherche);
                System.out.println("recherche par nom");
            } catch (SQLException ex) {
                Logger.getLogger(FOURNISSEURController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
             else {
               
            try {
                rechercheparentreprise(recherche);
                System.out.println("recherche par entreprise");
                
            } catch (SQLException ex) {
                Logger.getLogger(FOURNISSEURController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
        }
         else {
        try {
            initialisetable();
        } catch (SQLException ex) {
            Logger.getLogger(FOURNISSEURController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});
        
        
        
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
    private void optionpanemousemove(MouseEvent event) {
        
        
        TranslateTransition slide2 = new TranslateTransition();
    slide2.setDuration(Duration.seconds(0.4));     
    slide2.setNode(optionpane);
    slide2.setToX(0);
    slide2.play();
    APPNAME.setVisible(true);
        
        
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
    private void btnajouterfournisseurclique(MouseEvent event) {
        
        nomtaxtfield.setText("");
        fournisseurtextfield.setText("");
        emailtextfield.setText("");
        numteltextfield.setText("");
        
        usepane.setVisible(true);
        update.setVisible(false);
        enregistrer.setVisible(true);
        enregistrer.setDisable(false);
        supprimer.setVisible(false);
        
    }
   int idp;
   int r=0;
    @FXML
    private void enregistrerclique(MouseEvent event) throws SQLException, ClassNotFoundException {
        
         int t = 0;
        
        
            
            Random random = new Random();
           
            boolean uniqueIdFound = false;

            do {
                 idp = random.nextInt(10000000);
                 uniqueIdFound = checkIfExists(idp);
            } while (!uniqueIdFound);
      
            
          
          
          
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("Make sure that all fields are filled");
    
    Alert alert1 = new Alert(Alert.AlertType.ERROR);
    alert1.setContentText("conection au serveur momentanement indisponible");
        
        String nomentreprise = nomtaxtfield.getText();
        String nomf = fournisseurtextfield.getText();
        String emailf = emailtextfield.getText();
        int numtel = Integer.parseInt( numteltextfield.getText());
        
        
        
        if(nomentreprise.isEmpty()|| nomf.isEmpty()|| emailf.isEmpty()|| numtel==0 ){
        alert.showAndWait();
        r=1;
        }
        
        if(r==0){
         
        String sqlquery2 = "insert into fournisseur (id_fournisseur,entreprise_fournisseur,nom_fournisseur,tel_fournisseur,email_fournisseur) values (?,?,?,?,?)";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker","root",""); 
            PreparedStatement pst = conn1.prepareStatement(sqlquery2);
            pst.setInt(1,idp);
            pst.setString(2,nomentreprise);
            pst.setString(3,nomf);
            pst.setInt(4,numtel);
            pst.setString(5,emailf);
             
            pst.executeUpdate();
            
        }catch (Exception ex) {
            
            System.out.println("erreur lors  de l'ajout du produit : " +ex);
            alert1.showAndWait();
            t=1;
            
        }
        
       
            String sql9 = "insert into livre (id_fournisseur_fournisseur,id_boulangerie_boulangerie) values (?,?)";
             
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn1356 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker","root",""); 
            PreparedStatement pst3 = conn1356.prepareStatement(sql9);
            pst3.setInt(1,idp);
            pst3.setInt(2,idboulangerie);
              
            pst3.executeUpdate(); 
              
        }catch (Exception exc) {
            
            System.out.println("erreur lors du remplissage de TABLE VENDRE  : " + exc);
            alert1.showAndWait();
            alert1.showAndWait();
            t=1;
           
            
       }
        
            
        
        
        if (t==0){
        nomtaxtfield.setText("");
        fournisseurtextfield.setText("");
        emailtextfield.setText("");
        numteltextfield.setText("");
        
            
             initialisetable ();
             
             enregistrer.setDisable(true);
             usepane.setVisible(false);
                                
        
        
        }
        
        }  
        
        
                
        
    }

    @FXML
    private void btnfournisseurclique(MouseEvent event){
        
        
    }

    @FXML
    private void MONCOMPTECLIQUE(MouseEvent event) {
    }

    @FXML
    private void COMPTESUTILISATEURSCLIQUE(MouseEvent event) {
    }


    @FXML
    private void updateclique(MouseEvent event) throws SQLException {
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText("Make sure that all fields are filled");
    
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setContentText("conection au serveur momentanement indisponible");
         
        String nom =  fournisseurtextfield.getText();
        String entreprise =nomtaxtfield.getText();
        String email = emailtextfield.getText();
        int numtel  = Integer.parseInt(numteltextfield.getText());      
        
        int r=0;
        
        if(nom.isEmpty()|| entreprise.isEmpty()|| email.isEmpty()|| numtel==0){
        alert.showAndWait();
        r=1;
        }
        
        int rf=0;
        int rx=0;
        
       
        
        if(r==0){
            
         String updateQuery = "UPDATE fournisseur SET nom_fournisseur =?, entreprise_fournisseur =?,email_fournisseur=?, tel_fournisseur =?  WHERE id_fournisseur =?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

                pstmt.setString(1, nom);  
                pstmt.setString(2, entreprise);  
                pstmt.setString(3, email);  
                pstmt.setInt(4, numtel);
                pstmt.setInt(5, id);  

                pstmt.executeUpdate();
    
        } catch (SQLException e) {
                System.err.println("Error updating produit table: " + e );
                rf=1;
              
                alert1.showAndWait();
        }
        
        if(rf==0){
            usepane.setVisible(false);
            update.setVisible(true);
            update.setDisable(true);
            enregistrer.setVisible(false);
            supprimer.setVisible(true);
            supprimer.setDisable(true);
             
             initialisetable ();
        }
        
        }
        
        
    }

    @FXML
    private void optiopaneEXITE(MouseEvent event) {
        
    TranslateTransition slide3 = new TranslateTransition();
    slide3.setDuration(Duration.seconds(0.4));     
    slide3.setNode(optionpane);
    slide3.setToX(-190);
    slide3.play();
    APPNAME.setVisible(false);
        
        
    }

    @FXML
    private void supprmerclique(MouseEvent event) throws SQLException {
        
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
      alert1.setContentText("conection au serveur momentanement indisponible");
        int rf = 0;  
        
        
        String deleteQuery1 = "DELETE FROM livre WHERE id_fournisseur_fournisseur = ?";
        try (Connection conn765 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
             PreparedStatement pstmt543= conn765.prepareStatement(deleteQuery1)) {

                pstmt543.setInt(1, id);

                pstmt543.executeUpdate();

        } catch (SQLException e) {
                System.err.println("Error deleting produit: " + e );
                rf=1;
                alert1.showAndWait();
        }
        
        String deleteQuery = "DELETE FROM fournisseur WHERE id_fournisseur = ?";

        try (Connection conn76 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
             PreparedStatement pstmt = conn76.prepareStatement(deleteQuery)) {

                pstmt.setInt(1, id);

                pstmt.executeUpdate();

        } catch (SQLException e) {
                System.err.println("Error deleting produit: " + e );
                rf=1;
                alert1.showAndWait();
        }
        if(rf==0){
            usepane.setVisible(false);
            update.setVisible(true);
            update.setDisable(true);
            enregistrer.setVisible(false);
            supprimer.setVisible(true);
            supprimer.setDisable(true);
             
             initialisetable ();
        }
        
        
        
    }
     
    
    public class Fournisseur {
        private final int id_f;
        private final String nom_f;
        private final String entreprise_f;
        private final String email_f;
        private final int num_tel_f;
        
        public Fournisseur(int id_f, String nom_f, String entreprise_f,String email,int num_tel_f){
            this.id_f = id_f;
            this.nom_f = nom_f;
            this.entreprise_f = entreprise_f;
            this.email_f = email;
            this.num_tel_f = num_tel_f;
     }

        public int getId_f() {
            return id_f;
        }

        public String getNom_f() {
            return nom_f;
        }

        public String getEntreprise_f() {
            return entreprise_f;
        }

        public String getEmail_f() {
            return email_f;
        }

        public int getNum_tel_f() {
            return num_tel_f;
        }
        
        
        
        
        
    }
    
     
    
    public void initialisetable () throws SQLException{
        fournisseurtable.getItems().clear();
        fournisseurtable.getColumns().clear();  
        
        fournisseurtable.getColumns().addAll(ID, nom,entreprise ,email , tell);
       
        fournisseurtable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        fournisseurtable.getColumns().get(0).setPrefWidth(90); 
        fournisseurtable.getColumns().get(1).setPrefWidth(195); 
        fournisseurtable.getColumns().get(2).setPrefWidth(200);
        fournisseurtable.getColumns().get(3).setPrefWidth(195);
            
         // Établissez une connexion à la base de données
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            Connection  conn600 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");


                    // Créez une requête SQL pour récupérer les id_produit_produit de la table vendre
                String sql132 = "SELECT id_fournisseur_fournisseur FROM livre WHERE id_boulangerie_boulangerie = ?";
                PreparedStatement preparedStatement1 = conn600.prepareStatement(sql132);
                preparedStatement1.setInt(1, idboulangerie);

                    // Exécutez la requête et récupérez les résultats
                ResultSet resultSet132 = preparedStatement1.executeQuery();

                    // Créez une liste pour stocker les id_produit_produit
                List<Integer> idfournisseurList = new ArrayList<>();

                    // Parcourez les résultats et ajoutez les id_produit_produit à la liste
                while (resultSet132.next()) {
                    idfournisseurList.add(resultSet132.getInt("id_fournisseur_fournisseur"));
                    
                }

                    // Créez une requête SQL pour récupérer les informations de la table produit
                String sql2 = "SELECT  id_fournisseur,nom_fournisseur,entreprise_fournisseur,tel_fournisseur,email_fournisseur FROM fournisseur WHERE  id_fournisseur = ?";
               
                
                PreparedStatement preparedStatement232 = conn600.prepareStatement(sql2);

                    // Créez une liste pour stocker les produits
                ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList();

                    // Parcourez la liste des id_produit_produit et exécutez la requête pour chaque id
                for (int idfournisseur : idfournisseurList) {
                        preparedStatement232.setInt(1, idfournisseur);
                        ResultSet resultSet232 = preparedStatement232.executeQuery();
                        System.out.println("l'identifiant du produit est : " + idfournisseur);
                    // Parcourez les résultats et ajoutez les produits à la liste
                    while (resultSet232.next()) {
                        System.out.println("produit trouver dans la base de données");
                        
                        Fournisseur fournisseur = new Fournisseur(
                            resultSet232.getInt("id_fournisseur"),
                            resultSet232.getString("nom_fournisseur"),
                            resultSet232.getString("entreprise_fournisseur"),
                            resultSet232.getString("email_fournisseur"),
                            resultSet232.getInt("tel_fournisseur")
                            
                        );
                      fournisseurs.add(fournisseur);
                    }
                }
                
                
                ID.setCellValueFactory(new PropertyValueFactory<>("id_f"));
                nom.setCellValueFactory(new PropertyValueFactory<>("nom_f"));
                entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise_f"));
                email.setCellValueFactory(new PropertyValueFactory<>("email_f"));
                tell.setCellValueFactory(new PropertyValueFactory<>("num_tel_f"));
                 
                
            // Ajoutez les produits à la table
            fournisseurtable.getItems().addAll(fournisseurs);

            // Fermez la connexion

                  conn600.close();

     } catch (ClassNotFoundException | SQLException e) {
           System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
           return;
       } 
    }

   private boolean checkIfExists(int id) throws ClassNotFoundException {
    boolean exists = false;
     Class.forName("com.mysql.cj.jdbc.Driver");
        
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
         PreparedStatement pst = conn.prepareStatement("SELECT 1 FROM fournisseur WHERE id_fournisseur =?")) {
        pst.setInt(1, id);
        try (ResultSet rs = pst.executeQuery()) {
            exists = rs.next();
        }
    } catch (SQLException e) {
        System.out.println("Error checking for unique ID 1: " + e.getMessage());
    }
    
   return !exists;
}
   
    public void rechercheparnom (String nomf) throws SQLException{
        fournisseurtable.getItems().clear();
        fournisseurtable.getColumns().clear();  
        
        fournisseurtable.getColumns().addAll(ID, nom,entreprise ,email , tell);
       
        fournisseurtable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        fournisseurtable.getColumns().get(0).setPrefWidth(90); 
        fournisseurtable.getColumns().get(1).setPrefWidth(195); 
        fournisseurtable.getColumns().get(2).setPrefWidth(200);
        fournisseurtable.getColumns().get(3).setPrefWidth(195);
            
         // Établissez une connexion à la base de données
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            Connection  conn600 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");


                    // Créez une requête SQL pour récupérer les id_produit_produit de la table vendre
                String sql132 = "SELECT id_fournisseur_fournisseur FROM livre WHERE id_boulangerie_boulangerie = ?";
                PreparedStatement preparedStatement1 = conn600.prepareStatement(sql132);
                preparedStatement1.setInt(1, idboulangerie);

                    // Exécutez la requête et récupérez les résultats
                ResultSet resultSet132 = preparedStatement1.executeQuery();

                    // Créez une liste pour stocker les id_produit_produit
                List<Integer> idfournisseurList = new ArrayList<>();

                    // Parcourez les résultats et ajoutez les id_produit_produit à la liste
                while (resultSet132.next()) {
                    idfournisseurList.add(resultSet132.getInt("id_fournisseur_fournisseur"));
                    
                }

                    // Créez une requête SQL pour récupérer les informations de la table produit
                String sql2 = "SELECT  id_fournisseur,nom_fournisseur,entreprise_fournisseur,tel_fournisseur,email_fournisseur FROM fournisseur WHERE  id_fournisseur = ? and 	nom_fournisseur like ?";
               
                
                PreparedStatement preparedStatement232 = conn600.prepareStatement(sql2);

                    // Créez une liste pour stocker les produits
                ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList();

                    // Parcourez la liste des id_produit_produit et exécutez la requête pour chaque id
                for (int idfournisseur : idfournisseurList) {
                        preparedStatement232.setInt(1, idfournisseur);
                        preparedStatement232.setString(2, "%" + nomf + "%");
                        ResultSet resultSet232 = preparedStatement232.executeQuery();
                        System.out.println("l'identifiant du produit est : " + idfournisseur);
                    // Parcourez les résultats et ajoutez les produits à la liste
                    while (resultSet232.next()) {
                        System.out.println("produit trouver dans la base de données");
                        
                        Fournisseur fournisseur = new Fournisseur(
                            resultSet232.getInt("id_fournisseur"),
                            resultSet232.getString("nom_fournisseur"),
                            resultSet232.getString("entreprise_fournisseur"),
                            resultSet232.getString("email_fournisseur"),
                            resultSet232.getInt("tel_fournisseur")
                            
                        );
                      fournisseurs.add(fournisseur);
                    }
                }
                
                
                ID.setCellValueFactory(new PropertyValueFactory<>("id_f"));
                nom.setCellValueFactory(new PropertyValueFactory<>("nom_f"));
                entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise_f"));
                email.setCellValueFactory(new PropertyValueFactory<>("email_f"));
                tell.setCellValueFactory(new PropertyValueFactory<>("num_tel_f"));
                 
                
            // Ajoutez les produits à la table
            fournisseurtable.getItems().addAll(fournisseurs);

            // Fermez la connexion

                  conn600.close();

     } catch (ClassNotFoundException | SQLException e) {
           System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
           return;
       } 
    }
    
    
    public void rechercheparentreprise(String nom_ent_f) throws SQLException {
    fournisseurtable.getItems().clear();
    fournisseurtable.getColumns().clear();

    fournisseurtable.getColumns().addAll(ID, nom, entreprise, email, tell);

    fournisseurtable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    fournisseurtable.getColumns().get(0).setPrefWidth(90);
    fournisseurtable.getColumns().get(1).setPrefWidth(195);
    fournisseurtable.getColumns().get(2).setPrefWidth(200);
    fournisseurtable.getColumns().get(3).setPrefWidth(195);

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn600 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

        String sql132 = "SELECT id_fournisseur_fournisseur FROM livre WHERE id_boulangerie_boulangerie = ?";
        PreparedStatement preparedStatement1 = conn600.prepareStatement(sql132);
        preparedStatement1.setInt(1, idboulangerie);

        ResultSet resultSet132 = preparedStatement1.executeQuery();

        List<Integer> idfournisseurList = new ArrayList<>();

        while (resultSet132.next()) {
            idfournisseurList.add(resultSet132.getInt("id_fournisseur_fournisseur"));
        }

        String sql2 = "SELECT  id_fournisseur,nom_fournisseur,entreprise_fournisseur,tel_fournisseur,email_fournisseur FROM fournisseur WHERE  id_fournisseur = ? and entreprise_fournisseur like ?";

        PreparedStatement preparedStatement232 = conn600.prepareStatement(sql2);

        ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList();

        for (int idfournisseur : idfournisseurList) {
            preparedStatement232.setInt(1, idfournisseur);
            preparedStatement232.setString(2, "%" + nom_ent_f + "%"); // ajout du wildcard pour la recherche
            ResultSet resultSet232 = preparedStatement232.executeQuery();
            System.out.println("l'identifiant du produit est : " + idfournisseur);

            while (resultSet232.next()) {
                System.out.println("produit trouver dans la base de données");

                Fournisseur fournisseur = new Fournisseur(
                    resultSet232.getInt("id_fournisseur"),
                    resultSet232.getString("nom_fournisseur"),
                    resultSet232.getString("entreprise_fournisseur"),
                    resultSet232.getString("email_fournisseur"),
                    resultSet232.getInt("tel_fournisseur")
                );
                fournisseurs.add(fournisseur);
            }
        }

        ID.setCellValueFactory(new PropertyValueFactory<>("id_f"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_f"));
        entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise_f"));
        email.setCellValueFactory(new PropertyValueFactory<>("email_f"));
        tell.setCellValueFactory(new PropertyValueFactory<>("num_tel_f"));

        fournisseurtable.getItems().addAll(fournisseurs);

        conn600.close();

    } catch (ClassNotFoundException | SQLException e) {
        System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        return;
    }
}
   
   
    
}
 