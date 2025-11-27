/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgest;

/**
 *
 * @author USER
 */
public class GestionnaireID {
    private static int idboulangerie;
    private static int idgerant;

    public static int getIdboulangerie() {
        return idboulangerie;
    }

    public static int getIdgerant() {
        return idgerant;
    }

    public static void setIdboulangerie(int idboulangerie) {
        GestionnaireID.idboulangerie = idboulangerie;
    }

    public static void setIdgerant(int idgerant) {
        GestionnaireID.idgerant = idgerant;
    }

   
}