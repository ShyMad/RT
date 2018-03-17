/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ibelarabi
 */
public class Admin{
    
    private String identifiant ;
    private String mdp ;

    public Admin(String identifiant, String mdp) {
        this.identifiant = identifiant ;
        this.mdp = mdp ; 
    }

    public String getIdentifiant() {
        return identifiant  ;
    }

    public String getMdp() {
        return mdp;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    public void remiseAZeroScore(){
        // remise à zero la liste des score global
       
    }
    public void remiseAZeroInscrit(){
        // remise à zero la liste des joueurs
    }
    public void remiseAZero(){
        // supprimer le joeur de la liste
    }
     
}
