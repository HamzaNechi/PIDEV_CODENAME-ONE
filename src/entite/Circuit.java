/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author manaa
 */
public class Circuit {
    private int circuit_id;
    private String nom;
    private String pays;
    private int longeur;
    private int coursedistance;
    private String description;
    private int capacite;
    private String image;
     public Circuit(int circuit_id, String nom, String pays, int longeur, int coursedistance, String description, int capacite, String image) {
        this.circuit_id = circuit_id;
        this.nom = nom;
        this.pays = pays;
        this.longeur = longeur;
        this.coursedistance = coursedistance;
        this.description = description;
        this.capacite = capacite;
        this.image = image;
    }
      public Circuit( String nom, String pays, int longeur, int coursedistance, String description, int capacite, String image) {
        
        this.nom = nom;
        this.pays = pays;
        this.longeur = longeur;
        this.coursedistance = coursedistance;
        this.description = description;
        this.capacite = capacite;
        this.image = image;
    }

    public Circuit() {
        
    }
       public int getCapacite() {
        return capacite;
    }

    public int getCircuit_id() {
        return circuit_id;
    }

    public int getCourse_distance() {
        return coursedistance;
    }

    public String getDescription() {
        return description;
    }

    public int getlongeur() {
        return longeur;
    }

    public String getNom() {
        return nom;
    }
    
    public String getImage() {
        return image;
    }

    public String getPays() {
        return pays;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setCircuit_id(int circuit_id) {
        this.circuit_id = circuit_id;
    }

    public void setCourse_distance(int coursedistance) {
        this.coursedistance = coursedistance;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setlongeur(int longeur) {
        this.longeur = longeur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    
public String toString() {
        return  nom   ;  }
}
