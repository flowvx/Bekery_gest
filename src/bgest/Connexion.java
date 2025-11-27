/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe de connexion à la base de données MySQL
 *
 * @author USER
 */



public class Connexion {   

   
    /**
     * Méthode de connexion à la base de données
     *
     * @return une connexion à la base de données
     * @throws SQLException si une erreur de connexion se produit
     */
    String urlpilote =  "com.mysql.jdbc.Driver";
    String urlbd =  "jdbc:mysql://localhost:3306/baker";
    Connection con;
    
    public Connexion() {
        try {
            // Chargez le pilote JDBC pour MySQL
            Class.forName(urlpilote);
            System.out.println("chargement du pilote reussi");
        }catch(ClassNotFoundException ex){
            System.out.println(ex);
        }
            
        try {    
          
            con = DriverManager.getConnection(urlbd, "root", "");
            System.out.println("connection etablie");
           } catch (SQLException ex) {
            
            // Et lancez une nouvelle exception SQLException avec un message d'erreur personnalisé
            System.out.println(ex);
        }
    }

   
    
    Connection obtenirConnexion(){
    return con;
    }

}