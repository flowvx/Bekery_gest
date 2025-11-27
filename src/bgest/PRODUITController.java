/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class PRODUITController implements Initializable {

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
    private Label LABELIMAGE;
    @FXML
    private TextField nomduproduit;
    @FXML
    private TextField quantiteproduit;
    @FXML
    private TextField prixuduproduit;
    @FXML
    private TextArea description;
    @FXML
    private JFXButton inserer;
    @FXML
    private TextField nomduproduit1;
    
    
    int idboulangerie;
    int idgerant;
   
    @FXML
    private Label nomboulangerie;
    @FXML
    private ImageView logoboulangerie;
    @FXML
    private AnchorPane addmodifieproductpane;
    @FXML
    private JFXButton enregistrer;
    @FXML
    private TextField searchbar;
    @FXML
    private TableView<Produit> tablelproduct;
    @FXML
    private JFXButton addproduct;
    
    int id;
    
    
    TableColumn<Produit, Integer> ID1 = new TableColumn<>("ID");   
    TableColumn<Produit, String> nom1 = new TableColumn<>("NOM");
    TableColumn<Produit, String> description1 = new TableColumn<>("DESCRIPTION");
    TableColumn<Produit, String> cat1 = new TableColumn<>("CATEGORIE");
    TableColumn<Produit, String> prisu1 = new TableColumn<>("PRISUNITAIRE");
    TableColumn<Produit, String> quantité_reel1 = new TableColumn<>("Q REEL");
    TableColumn<Produit, String> quantité_dispo1 = new TableColumn<>("Q DISPO");
    TableColumn<Produit, String> quantité_attendu1 = new TableColumn<>("Q ATTENDU");
    TableColumn<Produit, String> prime1 = new TableColumn<>("PRIME");
    TableColumn<Produit, String> q_vandu1 = new TableColumn<>("Q VENDU");
    
    
    @FXML
    private JFXToggleButton primestate;
    @FXML
    private JFXButton enregistrer1;
    @FXML
    private JFXButton SUPRIMERBTN;
    @FXML
    private JFXComboBox<String> typecombobox;
    
    Blob dimage;
    @FXML
    private JFXComboBox<String> typesearch;
    
    int type1 = 0;
    String recherche;
    @FXML
    private TextField pointrupture;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
        
        
     ObservableList<String> options = FXCollections.observableArrayList("ingrdient pas a vendre", "ustensiles/materiel", "autres");
typecombobox.setItems(options);
typecombobox.setValue("autre");

typecombobox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
    String catgr = newVal;
    if (catgr.equals("ingrdient pas a vendre")) {
        nomduproduit1.setText("ingrdient pas a vendre");
    } else {
        if (catgr.equals("ustensiles/materiel")) {
            nomduproduit1.setText("ustensiles/materiel");
        } else {
            nomduproduit1.setText(" ");
        }
    }
});

ObservableList<String> optionsSearch = FXCollections.observableArrayList("par nom", "par prix", "par categorie");
typesearch.setItems(optionsSearch);
typesearch.setValue("par nom");

typesearch.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
    String categorie = newVal;
    if (categorie.equals("par nom")) {
        
        type1 = 0;
    } else {
        if (categorie.equals("par prix")) {
            type1 = 1;
        } else {
            type1 = 2;
        }
    }
});



searchbar.setOnAction((ActionEvent e) -> {
    recherche = searchbar.getText();

    if (!recherche.isEmpty()) {
        if (type1 == 0) {
            
            try {
                tablelproduct.getItems().clear();
                tablelproduct.getColumns().clear();
                
                tablelproduct.getColumns().addAll(ID1, nom1 ,description1 ,cat1, prisu1, quantité_reel1  ,quantité_dispo1 , quantité_attendu1 ,prime1,q_vandu1 );
                
                tablelproduct.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
                tablelproduct.getColumns().get(0).setPrefWidth(90);
                tablelproduct.getColumns().get(1).setPrefWidth(200);
                tablelproduct.getColumns().get(2).setPrefWidth(90);
                tablelproduct.getColumns().get(3).setPrefWidth(90);
                tablelproduct.getColumns().get(4).setPrefWidth(120);
                tablelproduct.getColumns().get(5).setPrefWidth(120);
                tablelproduct.getColumns().get(6).setPrefWidth(120);
                tablelproduct.getColumns().get(7).setPrefWidth(90);
                tablelproduct.getColumns().get(8).setPrefWidth(90);
                
                rechercheParNOM(recherche);
            } catch (SQLException ex) {
                Logger.getLogger(PRODUITController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRODUITController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            if (type1 == 1) {
                try {
                    tablelproduct.getItems().clear();
                    tablelproduct.getColumns().clear();
                    
                    tablelproduct.getColumns().addAll(ID1, nom1 ,description1 ,cat1, prisu1, quantité_reel1  ,quantité_dispo1 , quantité_attendu1 ,prime1,q_vandu1 );
                    
                    tablelproduct.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
                    tablelproduct.getColumns().get(0).setPrefWidth(90);
                    tablelproduct.getColumns().get(1).setPrefWidth(200);
                    tablelproduct.getColumns().get(2).setPrefWidth(90);
                    tablelproduct.getColumns().get(3).setPrefWidth(90);
                    tablelproduct.getColumns().get(4).setPrefWidth(120);
                    tablelproduct.getColumns().get(5).setPrefWidth(120);
                    tablelproduct.getColumns().get(6).setPrefWidth(120);
                    tablelproduct.getColumns().get(7).setPrefWidth(90);
                    tablelproduct.getColumns().get(8).setPrefWidth(90);
                    
                    
                    int prix = Integer.parseInt(recherche);
                    rechercheParPrix(prix);
                } catch (SQLException ex) {
                    Logger.getLogger(PRODUITController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PRODUITController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
                
                try {
                    tablelproduct.getItems().clear();
                    tablelproduct.getColumns().clear();
                    
                    tablelproduct.getColumns().addAll(ID1, nom1 ,description1 ,cat1, prisu1, quantité_reel1  ,quantité_dispo1 , quantité_attendu1 ,prime1,q_vandu1 );
                    
                    tablelproduct.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
                    tablelproduct.getColumns().get(0).setPrefWidth(90);
                    tablelproduct.getColumns().get(1).setPrefWidth(200);
                    tablelproduct.getColumns().get(2).setPrefWidth(90);
                    tablelproduct.getColumns().get(3).setPrefWidth(90);
                    tablelproduct.getColumns().get(4).setPrefWidth(120);
                    tablelproduct.getColumns().get(5).setPrefWidth(120);
                    tablelproduct.getColumns().get(6).setPrefWidth(120);
                    tablelproduct.getColumns().get(7).setPrefWidth(90);
                    tablelproduct.getColumns().get(8).setPrefWidth(90);
                    
                    rechercheParcategorie(recherche);
                } catch (SQLException ex) {
                    Logger.getLogger(PRODUITController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PRODUITController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    } else {
        tablelproduct.getItems().clear();
        tablelproduct.getColumns().clear();
        initialisetable();
    }
});
     
    
    
     SUPRIMERBTN.setDisable(true);
        
        
    TranslateTransition slide = new TranslateTransition();
    slide.setDuration(Duration.seconds(0.4));     
    slide.setNode(optionpane);
    slide.setToX(-190);
    slide.play();
    APPNAME.setVisible(false);
    accesprodut.setStyle("-fx-background-color: white; -fx-text-fill: black;-fx-background-radius: 36 0 0 36");
    
    Blob logoimg;
    addmodifieproductpane.setVisible(false);
    enregistrer.setDisable(true);
    
    String nombou= "notre boulangerie";
    
    idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
    
    dimage = getDefaultImage(idboulangerie);
    
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
    logoimg =  resultSet2.getBlob("logo_boulangerie");
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
    
    enregistrer1.setVisible(false);
    
    
    
             tablelproduct.getItems().clear();
             tablelproduct.getColumns().clear();
             initialisetable (); 
        
          // Événement pour sélectionner un élément du tableau
     tablelproduct.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Produit> obs, Produit oldValue, Produit newValue) -> {
    
                          
                             
          if (newValue!= null) {
                                 SUPRIMERBTN.setDisable(false);
                                 enregistrer1.setVisible(true);
                                 addmodifieproductpane.setVisible(true);
                                
                               id = newValue.getId();
                                String nom = newValue.getNom();
                                String description4 = newValue.getDescription();
                                int prixunitaire = newValue.getPrixUnitaire();
                                int quantiterelle = newValue.getQuantiteReelle();
                                Boolean primes = newValue.isPrime();
                                String categorie4 = "";
                                Blob img4 ;
                                
        
                                 
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
                            String imageQuery = "SELECT image_produit,categorie_produit FROM produit WHERE id_produit=? ";
                            PreparedStatement pstmt = conn2.prepareStatement(imageQuery);
                            pstmt.setInt(1, id);
                           
                            ResultSet resultSet2 = pstmt.executeQuery();

                                if (resultSet2.next()) {
                                    
                                img4 = resultSet2.getBlob("image_produit");
                                categorie4 = resultSet2.getString("categorie_produit");
                                
                                byte[] bytea = img4.getBytes(1, (int) img4.length());
                                Image image = new Image(new ByteArrayInputStream(bytea));
                                ImageView imageView = new ImageView(image); 
                                
                               imageView.setPreserveRatio(false);
                               imageView.setFitWidth(98);
                               imageView.setFitHeight(128);
                                
                                LABELIMAGE.setGraphic(imageView);
                                
                                }
                        } catch (SQLException e) {
                                System.err.println("Erreur lors de la récupération de l'image : " + e.getMessage());
                        }        catch (ClassNotFoundException ex) {     
                                     Logger.getLogger(PRODUITController.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                    // Mise à jour des champs de texte
                                    
                                    description.setText(description4);
                                    nomduproduit.setText(nom);
                                    nomduproduit1.setText(categorie4);
                                    quantiteproduit.setText(String.valueOf(quantiterelle));
                                    prixuduproduit.setText(String.valueOf(prixunitaire));
                                    
                                    if (primes==true){
                                       primestate.setSelected(true);
                                    }
                                    else{
                                        primestate.setSelected(false);
                                    }
                                    
                                  
                                    
                        }
                             else{
                                 enregistrer1.setVisible(false);
                                 addmodifieproductpane.setVisible(false);
                                 
                                 nomduproduit1.setText("");
                                 nomduproduit.setText("");
                                 description.setText("");
                                 quantiteproduit.setText("");
                                 prixuduproduit.setText("");
                                 LABELIMAGE.setText("");
                                 LABELIMAGE.setGraphic(null);
                                 primestate.setSelected(false);
                                 
                             }
                    });
    
        
    }    

    @FXML
    private void btnfournisseurclique(MouseEvent event)  throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FOURNISSEUR.fxml"));
        Parent root = loader.load();

        Scene scene = btnfournisseur.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
    }

    @FXML
    private void accesprodutclique(MouseEvent event) {
    }

    @FXML
    private void viewcommandclique(MouseEvent event)  throws IOException {
        
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
     String path2=null;
    @FXML
    private void incererclique(MouseEvent event) {
        
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
        LABELIMAGE.setGraphic(imageView);
        path2=path;
        
        
    }
       
      int primet= 0;
       int idpx;
    @FXML
    private void enregistrerclique(MouseEvent event) throws ClassNotFoundException {
        int t = 0;
        
        
            
            Random random = new Random();
           
            boolean uniqueIdFound = false;

            do {
                 idpx = random.nextInt(10000000);
                 uniqueIdFound = checkIfExists(idpx);
            } while (!uniqueIdFound);
      
            
          
          primestate.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    primet = 1;
                    System.out.println ( primet);
                } else {
                    primet = 0;
                    System.out.println ( primet);
                }
                });
          
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("veillez remplire tout les champs");
    
    Alert alert1 = new Alert(Alert.AlertType.ERROR);
    alert1.setContentText("conection au serveur momentanement indisponible");
        
        String cathegorieproduit = nomduproduit1.getText();
        String nomproduit = nomduproduit.getText();
        String descriptionproduit = description.getText();
        float quantiteproduit1 = Float.parseFloat(quantiteproduit.getText());
        float prixunitaire1 = Float.parseFloat(prixuduproduit.getText());
        int pointr = Integer.parseInt(pointrupture.getText());
        int quantitéat=0;
        int disponible;
        int r=0;
        
        if(cathegorieproduit.isEmpty()|| nomproduit.isEmpty()|| descriptionproduit.isEmpty()|| quantiteproduit1==0 ){
        alert.showAndWait();
        r=1;
        }
        
        if(r==0){
         
            String sqlquery2 = "insert into produit (nom_produit,description_produit,prixu_produit,quantité_reel,quantité_disponible,quantité_en_attete,image_produit,prime,id_produit,categorie_produit,point_rupture) values (?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            Properties props = new Properties();
            props.setProperty("maxAllowedPacket", "2000M"); 
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker","root",""); 
            PreparedStatement pst = conn1.prepareStatement(sqlquery2);
            pst.setString(1,nomproduit);
            pst.setString(2,descriptionproduit);
            pst.setFloat(3,prixunitaire1);
            pst.setFloat(4,quantiteproduit1);
            pst.setFloat(5,disponible = ALGORITHME.getProduitDisponible( idpx));
            pst.setInt(6,quantitéat);
            
            if(path2==null){
                
                pst.setBlob(7, dimage);
                
            }
            else{
             
            InputStream is = new FileInputStream(new File(path2));
            pst.setBlob(7, is);
            
            }
            pst.setByte(8, (byte) primet);
            
            pst.setInt(9,idpx);
            pst.setString(10,cathegorieproduit);
            pst.setInt(11,pointr);
            
            pst.executeUpdate();
            
        }catch (Exception ex) {
            
            System.out.println("erreur lors  de l'ajout du produit : " +ex);
            alert1.showAndWait();
            t=1;
            
        }
        
       
            String sql9 = "insert into vendre (id_produit_produit,id_boulangerie_boulangerie) values (?,?)";
             
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn1356 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker","root",""); 
            PreparedStatement pst3 = conn1356.prepareStatement(sql9);
            pst3.setInt(1,idpx);
            pst3.setInt(2,idboulangerie);
              
            pst3.executeUpdate(); 
              
        }catch (Exception exc) {
            
            System.out.println("erreur lors du remplissage de TABLE VENDRE  : " + exc);
            alert1.showAndWait();
            alert1.showAndWait();
            t=1;
           
            
       }
        
          
        
        
        if (t==0){
        nomduproduit1.setText("");
        nomduproduit.setText("");
        description.setText("");
        quantiteproduit.setText("");
        prixuduproduit.setText("");
        LABELIMAGE.setText("");
        LABELIMAGE.setGraphic(null);
        primestate.setSelected(false);
        
        
        
             tablelproduct.getItems().clear();
             tablelproduct.getColumns().clear();
             initialisetable ();
             
             enregistrer.setDisable(true);
             enregistrer1.setVisible(false);
             addmodifieproductpane.setVisible(false);
                                
        
        
        }
        
        }  
        
        
    }

    @FXML
    private void addproductclique(MouseEvent event) {
        
        addmodifieproductpane.setVisible(true);
        enregistrer.setDisable(false);
        enregistrer1.setVisible(false);
        
        nomduproduit1.setText("");
        nomduproduit.setText("");
        description.setText("");
        quantiteproduit.setText("");
        prixuduproduit.setText("");
        pointrupture.setText("");
        LABELIMAGE.setText("");
        LABELIMAGE.setGraphic(null);
        primestate.setSelected(false);
                                 
                                 
        
        
    }

    @FXML
    private void enregistrer1clique(MouseEvent event) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
        
        
         int t=0;    
        
         primestate.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    primet = 1;
                } else {
                    primet = 0;
                }
                });
         
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText("Make sure that all fields are filled");
    
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setContentText("conection au serveur momentanement indisponible");
         
         String puproduit = prixuduproduit.getText();
         
         if (puproduit.isEmpty()){
             puproduit = "000";
         }
                 
        
        String cathegorieproduit = nomduproduit1.getText();
        String nomproduit = nomduproduit.getText();
        String descriptionproduit = description.getText();
        float quantiteproduit1 = Float.parseFloat(quantiteproduit.getText());
        float prixunitaire1 = Float.parseFloat(prixuduproduit.getText());
        int pointr = Integer.parseInt(pointrupture.getText());
        //rubicon
        ImageView imageView = (ImageView) LABELIMAGE.getGraphic();
        Image image = imageView.getImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", bos); // ou "png" si vous préférez
        byte[] imageBytes = bos.toByteArray();
       
        Blob imageBlob = new SerialBlob(imageBytes);
         
        int r=0;
        if(cathegorieproduit.isEmpty()|| nomproduit.isEmpty()|| descriptionproduit.isEmpty()|| quantiteproduit1==0|| prixunitaire1==0){
        alert.showAndWait();
        r=1;
        }
        
        int rf=0;
        int rx=0;
        
        int disponible = ALGORITHME.getProduitDisponible( id);
        
        if(r==0){
            
         String updateQuery = "UPDATE produit SET nom_produit =?, description_produit =?, categorie_produit =?, prixu_produit =?, quantité_reel =?,prime =?, image_produit =?,point_rupture = ?  WHERE id_produit =?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

                pstmt.setString(1, nomproduit);  
                pstmt.setString(2, descriptionproduit);  
                pstmt.setString(3, cathegorieproduit);  
                pstmt.setDouble(4,prixunitaire1);  
                pstmt.setFloat(5, quantiteproduit1);  
                pstmt.setByte(6, (byte) primet);  
    
                pstmt.setBlob(7, imageBlob);
                pstmt.setInt(8, pointr); 
                pstmt.setInt(9, id);  

                pstmt.executeUpdate();
    
        } catch (SQLException e) {
                System.err.println("Error updating produit table: " + e );
                rf=1;
                rx=1;
                alert1.showAndWait();
        }
        
        if(rx==0){
            
            String updateQuery1 = "UPDATE produit SET `quantité_disponible` =? WHERE id_produit =?";
            
             try (Connection conn32 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
             PreparedStatement pstmt32 = conn32.prepareStatement(updateQuery1)) {
                pstmt32.setInt(1, disponible); 
                pstmt32.setInt(2, id);
                
                pstmt32.executeUpdate();
                
             }catch (SQLException e) {
                System.err.println("Error updating produit table: " + e );
                rf=1;
                
                alert1.showAndWait();
             }
             
             
        }
        
        
        
        
        if(rf==0){
            addmodifieproductpane.setVisible(false);
            enregistrer1.setVisible(false);
            SUPRIMERBTN.setDisable(true);
            
             tablelproduct.getItems().clear();
             tablelproduct.getColumns().clear();
             initialisetable ();
        }
        
        }
                
        
    }

    @FXML
    private void SUPRIMERBTNCLIQUE(MouseEvent event) {
        
      Alert alert1 = new Alert(Alert.AlertType.ERROR);
      alert1.setContentText("conection au serveur momentanement indisponible");
        int rf = 0;  
        
        
        String deleteQuery1 = "DELETE FROM vendre WHERE id_produit_produit = ?";
        try (Connection conn765 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
             PreparedStatement pstmt543= conn765.prepareStatement(deleteQuery1)) {

                pstmt543.setInt(1, id);

                pstmt543.executeUpdate();

        } catch (SQLException e) {
                System.err.println("Error deleting produit: " + e );
                rf=1;
                alert1.showAndWait();
        }
        
        String deleteQuery = "DELETE FROM produit WHERE id_produit = ?";

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
            
             tablelproduct.getItems().clear();
             tablelproduct.getColumns().clear();
             initialisetable ();
             SUPRIMERBTN.setDisable(true);
             enregistrer1.setVisible(false);
        }
        
        
        
    }
   
    
     
        
       
    private Blob getDefaultImage(int idboulangerie) {
    Blob defaultImage = null;
    try (Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "")) {
        String sql1 = "SELECT logo_boulangerie FROM boulangerie WHERE id_boulangerie =? ";
        PreparedStatement statement21 = connection2.prepareStatement(sql1);
        statement21.setInt(1, idboulangerie);
        ResultSet resultSet2 = statement21.executeQuery();

        if (resultSet2.next()) {
            defaultImage = resultSet2.getBlob("logo_boulangerie");
        }
    } catch (SQLException e) {
        System.err.println("Error getting default image: " + e);
    }
    return defaultImage;
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
    
    
    private boolean checkIfExists(int id) throws ClassNotFoundException {
    boolean exists = false;
     Class.forName("com.mysql.cj.jdbc.Driver");
        
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");
         PreparedStatement pst = conn.prepareStatement("SELECT 1 FROM produit WHERE id_produit =?")) {
        pst.setInt(1, id);
        try (ResultSet rs = pst.executeQuery()) {
            exists = rs.next();
        }
    } catch (SQLException e) {
        System.out.println("Error checking for unique ID 1: " + e.getMessage());
    }

    return !exists;
}
    
    public void initialisetable (){
        tablelproduct.getItems().clear();
        tablelproduct.getColumns().addAll(ID1, nom1 ,description1 ,cat1, prisu1, quantité_reel1  ,quantité_dispo1 , quantité_attendu1 ,prime1,q_vandu1 );
       
        tablelproduct.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tablelproduct.getColumns().get(0).setPrefWidth(90); 
        tablelproduct.getColumns().get(1).setPrefWidth(200); 
        tablelproduct.getColumns().get(2).setPrefWidth(90);
        tablelproduct.getColumns().get(3).setPrefWidth(90);
        tablelproduct.getColumns().get(4).setPrefWidth(120);
        tablelproduct.getColumns().get(5).setPrefWidth(120);
        tablelproduct.getColumns().get(6).setPrefWidth(120);
        tablelproduct.getColumns().get(7).setPrefWidth(90);
        tablelproduct.getColumns().get(8).setPrefWidth(90);
      
             
             
         // Établissez une connexion à la base de données
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            Connection  conn600 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");


                    // Créez une requête SQL pour récupérer les id_produit_produit de la table vendre
                String sql132 = "SELECT id_produit_produit FROM vendre WHERE id_boulangerie_boulangerie = ?";
                PreparedStatement preparedStatement1 = conn600.prepareStatement(sql132);
                preparedStatement1.setInt(1, idboulangerie);

                    // Exécutez la requête et récupérez les résultats
                ResultSet resultSet132 = preparedStatement1.executeQuery();

                    // Créez une liste pour stocker les id_produit_produit
                List<Integer> idProduitList = new ArrayList<>();

                    // Parcourez les résultats et ajoutez les id_produit_produit à la liste
                while (resultSet132.next()) {
                    idProduitList.add(resultSet132.getInt("id_produit_produit"));
                    
                }

                    // Créez une requête SQL pour récupérer les informations de la table produit
                String sql2 = "SELECT  prime,quantité_en_attete,quantité_disponible,quantité_reel,prixu_produit,description_produit,nom_produit,id_produit,categorie_produit,vendu_n1 FROM produit WHERE id_produit = ?";
               
                
                PreparedStatement preparedStatement232 = conn600.prepareStatement(sql2);

                    // Créez une liste pour stocker les produits
                ObservableList<Produit> produits = FXCollections.observableArrayList();

                    // Parcourez la liste des id_produit_produit et exécutez la requête pour chaque id
                for (int idProduit : idProduitList) {
                        preparedStatement232.setInt(1, idProduit);
                        ResultSet resultSet232 = preparedStatement232.executeQuery();
                        System.out.println("l'identifiant du produit est : " + idProduit);
                    // Parcourez les résultats et ajoutez les produits à la liste
                    while (resultSet232.next()) {
                        System.out.println("produit trouver dans la base de données");
                        
                        Produit produit = new Produit(
                           resultSet232.getInt("id_produit"),
                            resultSet232.getString("nom_produit"),
                            resultSet232.getString("description_produit"),
                            resultSet232.getInt("prixu_produit"),
                            resultSet232.getInt("quantité_reel"),
                            resultSet232.getInt("quantité_disponible"),
                            resultSet232.getInt("quantité_en_attete"),
                            resultSet232.getBoolean("prime"),
                            resultSet232.getString("categorie_produit"),
                            resultSet232.getInt("vendu_n1")
                        );
                      produits.add(produit);
                    }
                }
                
                
                ID1.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
                description1.setCellValueFactory(new PropertyValueFactory<>("description"));
                cat1.setCellValueFactory(new PropertyValueFactory<>("cate"));
                prisu1.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
                quantité_reel1.setCellValueFactory(new PropertyValueFactory<>("quantiteReelle"));
                quantité_dispo1.setCellValueFactory(new PropertyValueFactory<>("quantiteDisponible"));
                quantité_attendu1.setCellValueFactory(new PropertyValueFactory<>("quantiteEnAttente"));
                prime1.setCellValueFactory(new PropertyValueFactory<>("prime"));
                q_vandu1.setCellValueFactory(new PropertyValueFactory<>("q_vandu"));
                
                
            // Ajoutez les produits à la table
            tablelproduct.getItems().addAll(produits);

            // Fermez la connexion

                  conn600.close();

     } catch (ClassNotFoundException | SQLException e) {
           System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
           return;
       } 
    }
    
    private void rechercheParNOM(String nom) throws SQLException, ClassNotFoundException {
        
        
    Class.forName("com.mysql.cj.jdbc.Driver");
    
    try (Connection conn8 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "")) {
        String sql1328 = "SELECT id_produit_produit FROM vendre WHERE id_boulangerie_boulangerie =?";
        PreparedStatement preparedStatement18 = conn8.prepareStatement(sql1328);
        preparedStatement18.setInt(1, idboulangerie);

        ResultSet resultSet1328 = preparedStatement18.executeQuery();

        List<Integer> idProduitList = new ArrayList<>();

        while (resultSet1328.next()) {
            idProduitList.add(resultSet1328.getInt("id_produit_produit"));
        }

        String sql28 = "SELECT  prime,quantité_en_attete,quantité_disponible,quantité_reel,prixu_produit,description_produit,nom_produit,id_produit,categorie_produit,vendu_n1 FROM produit WHERE id_produit =? and nom_produit LIKE ?";

        PreparedStatement preparedStatement2328 = conn8.prepareStatement(sql28);

        ObservableList<Produit> produits = FXCollections.observableArrayList();

        for (int idProduit : idProduitList) {
            preparedStatement2328.setInt(1, idProduit);
            preparedStatement2328.setString(2, "%" + nom + "%" );
            ResultSet resultSet2328 = preparedStatement2328.executeQuery();
            System.out.println("l'identifiant du produit est : " + idProduit);

            while (resultSet2328.next()) {
                System.out.println("produit trouver dans la base de données");

                Produit produit = new Produit(
                    resultSet2328.getInt("id_produit"),
                    resultSet2328.getString("nom_produit"),
                    resultSet2328.getString("description_produit"),
                    resultSet2328.getInt("prixu_produit"),
                    resultSet2328.getInt("quantité_reel"),
                    resultSet2328.getInt("quantité_disponible"),
                    resultSet2328.getInt("quantité_en_attete"),
                    resultSet2328.getBoolean("prime"),
                    resultSet2328.getString("categorie_produit"),
                    resultSet2328.getInt("vendu_n1")
                );
                produits.add(produit);
            }
        }
        
         ID1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description1.setCellValueFactory(new PropertyValueFactory<>("description"));
        cat1.setCellValueFactory(new PropertyValueFactory<>("cate"));
        prisu1.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        quantité_reel1.setCellValueFactory(new PropertyValueFactory<>("quantiteReelle"));
        quantité_dispo1.setCellValueFactory(new PropertyValueFactory<>("quantiteDisponible"));
        quantité_attendu1.setCellValueFactory(new PropertyValueFactory<>("quantiteEnAttente"));
        prime1.setCellValueFactory(new PropertyValueFactory<>("prime"));
        q_vandu1.setCellValueFactory(new PropertyValueFactory<>("q_vandu"));

        // Ajoutez les produits à la table
        tablelproduct.getItems().addAll(produits);

        // Fermez la connexion
        conn8.close();
        
    } catch (Exception ex) {
        System.err.println("Erreur de connexion à la base de données : " + ex.getMessage());
    }
}
    
    private void rechercheParcategorie(String categori) throws SQLException, ClassNotFoundException {
        
        
      
        
        
    Class.forName("com.mysql.cj.jdbc.Driver");
    
    try (Connection conn9 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "")) {
        String sql1329 = "SELECT id_produit_produit FROM vendre WHERE id_boulangerie_boulangerie =?";
        PreparedStatement preparedStatement19 = conn9.prepareStatement(sql1329);
        preparedStatement19.setInt(1, idboulangerie);

        ResultSet resultSet1329 = preparedStatement19.executeQuery();

        List<Integer> idProduitList = new ArrayList<>();

        while (resultSet1329.next()) {
            idProduitList.add(resultSet1329.getInt("id_produit_produit"));
        }

        String sql29 = "SELECT  prime,quantité_en_attete,quantité_disponible,quantité_reel,prixu_produit,description_produit,nom_produit,id_produit,categorie_produit,vendu_n1 FROM produit WHERE id_produit =? and categorie_produit LIKE ?";

        PreparedStatement preparedStatement2329 = conn9.prepareStatement(sql29);

        ObservableList<Produit> produits = FXCollections.observableArrayList();

        for (int idProduit : idProduitList) {
            preparedStatement2329.setInt(1, idProduit);
            preparedStatement2329.setString(2,"%" +  categori + "%" );
            ResultSet resultSet2329 = preparedStatement2329.executeQuery();
            System.out.println("l'identifiant du produit est : " + idProduit);

            while (resultSet2329.next()) {
                System.out.println("produit trouver dans la base de données");

                Produit produit = new Produit(
                    resultSet2329.getInt("id_produit"),
                    resultSet2329.getString("nom_produit"),
                    resultSet2329.getString("description_produit"),
                    resultSet2329.getInt("prixu_produit"),
                    resultSet2329.getInt("quantité_reel"),
                    resultSet2329.getInt("quantité_disponible"),
                    resultSet2329.getInt("quantité_en_attete"),
                    resultSet2329.getBoolean("prime"),
                    resultSet2329.getString("categorie_produit"),
                    resultSet2329.getInt("vendu_n1")
                );
                produits.add(produit);
            }
        }
        
         ID1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description1.setCellValueFactory(new PropertyValueFactory<>("description"));
        cat1.setCellValueFactory(new PropertyValueFactory<>("cate"));
        prisu1.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        quantité_reel1.setCellValueFactory(new PropertyValueFactory<>("quantiteReelle"));
        quantité_dispo1.setCellValueFactory(new PropertyValueFactory<>("quantiteDisponible"));
        quantité_attendu1.setCellValueFactory(new PropertyValueFactory<>("quantiteEnAttente"));
        prime1.setCellValueFactory(new PropertyValueFactory<>("prime"));
        q_vandu1.setCellValueFactory(new PropertyValueFactory<>("q_vandu"));

        // Ajoutez les produits à la table
        tablelproduct.getItems().addAll(produits);

        // Fermez la connexion
        conn9.close();
    } catch ( Exception ex) {
        System.err.println("Erreur de connexion à la base de données : " + ex.getMessage());
    }
}
    
    
    
    private void rechercheParPrix(int prix) throws SQLException, ClassNotFoundException {
       
        
        
    Class.forName("com.mysql.cj.jdbc.Driver");
    String sql16 = "SELECT id_produit_produit FROM vendre WHERE id_boulangerie_boulangerie = ?";
    
    try (Connection conn6 = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "")) {
        
        PreparedStatement preparedStatement16 = conn6.prepareStatement(sql16);
        preparedStatement16.setInt(1, idboulangerie);

        ResultSet resultSet1326 = preparedStatement16.executeQuery();

        List<Integer> idProduitList = new ArrayList<>();

        while (resultSet1326.next()) {
            idProduitList.add(resultSet1326.getInt("id_produit_produit"));
        }

        String sql26 = "SELECT  prime,quantité_en_attete,quantité_disponible,quantité_reel,prixu_produit,description_produit,nom_produit,id_produit,categorie_produit,vendu_n1 FROM produit WHERE id_produit = ? and prixu_produit=?";

        PreparedStatement preparedStatement2327 = conn6.prepareStatement(sql26);

        ObservableList<Produit> produits = FXCollections.observableArrayList();

        for (int idProduit : idProduitList) {
            preparedStatement2327.setInt(1, idProduit);
            preparedStatement2327.setInt(2, prix);
            ResultSet resultSet2327 = preparedStatement2327.executeQuery();
            System.out.println("l'identifiant du produit est : " + idProduit);

            while (resultSet2327.next()) {
                System.out.println("produit trouver dans la base de données");

                Produit produit = new Produit(
                    resultSet2327.getInt("id_produit"),
                    resultSet2327.getString("nom_produit"),
                    resultSet2327.getString("description_produit"),
                    resultSet2327.getInt("prixu_produit"),
                    resultSet2327.getInt("quantité_reel"),
                    resultSet2327.getInt("quantité_disponible"),
                    resultSet2327.getInt("quantité_en_attete"),
                    resultSet2327.getBoolean("prime"),
                    resultSet2327.getString("categorie_produit"),
                    resultSet2327.getInt("vendu_n1")
                );
                produits.add(produit);
            }
        }

        ID1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description1.setCellValueFactory(new PropertyValueFactory<>("description"));
        cat1.setCellValueFactory(new PropertyValueFactory<>("cate"));
        prisu1.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        quantité_reel1.setCellValueFactory(new PropertyValueFactory<>("quantiteReelle"));
        quantité_dispo1.setCellValueFactory(new PropertyValueFactory<>("quantiteDisponible"));
        quantité_attendu1.setCellValueFactory(new PropertyValueFactory<>("quantiteEnAttente"));
        prime1.setCellValueFactory(new PropertyValueFactory<>("prime"));
        q_vandu1.setCellValueFactory(new PropertyValueFactory<>("q_vandu"));

        // Ajoutez les produits à la table
        tablelproduct.getItems().addAll(produits);

        // Fermez la connexion
        conn6.close();

    } catch ( Exception ex) {
        System.err.println("Erreur de connexion à la base de données : " + ex.getMessage());
    }
}
}
