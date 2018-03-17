/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author ibelarabi
 */
public class Partie {
    private int id ;
    private String motSecret;
    private Date duree;

    public Partie(int id, String motSecret, Date duree) {
        this.id = id;
        this.motSecret = motSecret;
        this.duree = duree ;
    }

    public Partie() {
    }

    public Partie(String motSecret) {
        this.motSecret = motSecret;
    }

    public int getId() {
        return id;
    }

    public String getMotSecret() {
        return motSecret;
    }

    public Date getDuree() {
        return duree;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMotSecret(String motSecret) {
        this.motSecret = motSecret;
    }

    public void setDuree(Date duree) {
        this.duree = duree;
    }
    
}
