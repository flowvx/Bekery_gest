/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import java.io.IOException;
import java.sql.Connection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


/**
 *
 * @author USER
 */

    


public class Main extends Application {
    
   
@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(Main.class.getResource("ajouterconecteruneboulangerie.fxml"));
        Scene scene;
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("BAKERY GEST 2.1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        
        launch();
    }
}
