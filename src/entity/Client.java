/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.swing.JFrame;

/**
 *
 * @author ibelarabi
 */
public class Client extends JFrame{
    
    
    public Joueur createJoueur(String nom, String prenom){
        String code = "";//ServeurM.generateCodeLicencie();
        Joueur j = new Joueur(nom,prenom,code);
       
        return j ;
    }
}
