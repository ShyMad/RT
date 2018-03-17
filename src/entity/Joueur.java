/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;
/**
 *
 * @author ibelarabi
 */
public class Joueur {
    
    private String nom;
    private String prenom;
    private String codeLicencie ;
    private int scoreActuel;
    private int scoreGlobal;
    private String duree;
    
    public Joueur() {
    }

    public Joueur(String nom, String prenom, String codeLicencie) {
        this.nom = nom;
        this.prenom = prenom;
        this.codeLicencie = codeLicencie;
    }

    public Joueur(String nom, String prenom, String codeLicencie, int scoreActuel, int scoreGlobal, String duree) {
        this.nom = nom;
        this.prenom = prenom;
        this.codeLicencie = codeLicencie;
        this.scoreActuel = scoreActuel;
        this.scoreGlobal = scoreGlobal;
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCodeLicencie() {
        return codeLicencie;
    }

    public int getScoreActuel() {
        return scoreActuel;
    }

    public int getScoreGlobal() {
        return scoreGlobal;
    }

    public String getDuree() {
        return duree;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom ;
    }

    public void setCodeLicencie(String codeLicencie) {
        this.codeLicencie = codeLicencie;
    }

    public void setScoreActuel(int scoreActuel) {
        this.scoreActuel = scoreActuel;
    }

    public void setScoreGlobal(int scoreGlobal) {
        this.scoreGlobal = scoreGlobal;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Joueur{" + "nom=" + nom + ", prenom=" + prenom + ", codeLicencie=" + codeLicencie + ", scoreActuel=" + scoreActuel + ", scoreGlobal=" + scoreGlobal + ", duree=" + duree + '}';
    }
    
    
    
}
