/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author USER
 */
public class classcommande {
    
   private static String nomclient;
   private static String prenomclient;
   private static String adresseclient;
   private static int id_com;
   
   private static String nomp_in_c;
   private static int quanp_in_c;
   private static int IdProduit;

    
   private static LocalDate date_livraison;
   
   public classcommande( String nomclient,String prenomclient, String adresseclient,int id_com, LocalDate date_livraison){
       
       
       this.nomclient = nomclient;
       this.prenomclient = prenomclient;
       this.adresseclient = adresseclient;
       this.id_com = id_com;
       this.date_livraison = date_livraison ;
   }

    
   
   public classcommande(String nomp_in_c,int quanp_in_c){
       this.nomp_in_c = nomp_in_c;
       this.quanp_in_c = quanp_in_c;
   }

    public static String getNomclient() {
        return nomclient;
    }

    public static String getPrenomclient() {
        return prenomclient;
    }

    public static String getAdresseclient() {
        return adresseclient;
    }

    public static int getId_com() {
        return id_com;
    }

    public  static String getNomp_in_c() {
        return nomp_in_c;
    }

    public static int getQuanp_in_c() {
        return quanp_in_c;
    }
    
    public static int getIdProduit() {
        return IdProduit;
    }

    public static LocalDate getDate_livraison() {
        return date_livraison;
    }
    
    
    

    public static void setIdProduit(int IdProduit) {
        classcommande.IdProduit = IdProduit;
    }

    public static void setDate_livraison(LocalDate date_livraison) {
        classcommande.date_livraison = date_livraison;
    }

    
    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public void setPrenomclient(String prenomclient) {
        this.prenomclient = prenomclient;
    }

    public void setAdresseclient(String adresseclient) {
        this.adresseclient = adresseclient;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public void setNomp_in_c(String nomp_in_c) {
        this.nomp_in_c = nomp_in_c;
    }

    public void setQuanp_in_c(int quanp_in_c) {
        this.quanp_in_c = quanp_in_c;
    }
    
    
    
}
