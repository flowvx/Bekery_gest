/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author USER
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private LineChart<String, Number> lineChart;

    private NumberAxis yAxis;

    private CategoryAxis xAxis;
    
    private Series<String, Number> series1;
private Series<String, Number> series2;
private Series<String, Number> series3;

int idboulangerie;
    int idgerant; 

    private ObservableList<XYChart.Series<String, Number>> seriesList;
    @FXML
    private Label dmois;
    @FXML
    private JFXButton back;
    @FXML
    private Label lskdsq;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         idboulangerie = GestionnaireID.getIdboulangerie();
    idgerant = GestionnaireID.getIdgerant();
    
    
    GestionnaireID.setIdgerant(idgerant);
    GestionnaireID.setIdboulangerie(idboulangerie);
    
    try {
                // Charger le driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Établir la connexion à la base de données
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baker", "root", "");

                String sql = "SELECT depense_mensuelle,vente_mensuelle from boulangerie where id_boulangerie  = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, idboulangerie);
                
                ResultSet rs = pst.executeQuery();
                Float a = null;
                Float b  = null;
                if (rs.next()){
                    a = rs.getFloat("depense_mensuelle"); 
                    b = rs.getFloat("vente_mensuelle");
                }
                
                lskdsq.setText(String.valueOf(b));
                dmois.setText(String.valueOf(a));

                // Exécuter la requête 
               
           } catch (SQLException e) {
                System.err.println("Erreur lors de la suppression dans la table contenir : " + e);
                
            } catch (ClassNotFoundException ex) {     
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    // Create three series with different colors
    series1 = new Series<>();
    series1.setName("Series 1");
    series1.getData().add(new XYChart.Data<>("Jan", 10));
    series1.getData().add(new XYChart.Data<>("Feb", 20));
    series1.getData().add(new XYChart.Data<>("Mar", 30));
    

    series2 = new Series<>();
    series2.setName("Series 2");
    series2.getData().add(new XYChart.Data<>("Jan", 15));
    series2.getData().add(new XYChart.Data<>("Feb", 25));
    series2.getData().add(new XYChart.Data<>("Mar", 35));
    

    series3 = new Series<>();
    series3.setName("Series 3");
    series3.getData().add(new XYChart.Data<>("Jan", 20));
    series3.getData().add(new XYChart.Data<>("Feb", 30));
    series3.getData().add(new XYChart.Data<>("Mar", 40));
    

    // Add the series to the chart
    lineChart.getData().addAll(series1, series2, series3);

    

    // Set the chart title
    lineChart.setTitle("Line Chart Example");
}

    @FXML
    private void backclique(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("center.fxml"));
        Parent root = loader.load();

        Scene scene = back.getScene();
        Stage stage = (Stage) scene.getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);

        stage.show();
        
        
    }
    
    
    }