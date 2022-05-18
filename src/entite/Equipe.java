/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author nechi
 */
public class Equipe {
    private int id;
    private String nom,email,logo,voiture,pays_origine,extention;

    public Equipe() {
    }
    
    

    public Equipe(int id, String nom, String email, String logo, String voiture, String pays_origine) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.logo = logo;
        this.voiture = voiture;
        this.pays_origine = pays_origine;
    }
    
    public Equipe(String nom, String email, String logo, String voiture, String pays_origine) {
        this.nom = nom;
        this.email = email;
        this.logo = logo;
        this.voiture = voiture;
        this.pays_origine = pays_origine;
    }
    
    
    public Equipe(int id, String nom, String email, String logo, String voiture, String pays_origine,String extension) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.logo = logo;
        this.voiture = voiture;
        this.pays_origine = pays_origine;
        this.extention=extension;
    }
    
    public Equipe(String nom, String email, String logo, String voiture, String pays_origine,String extension) {
        this.nom = nom;
        this.email = email;
        this.logo = logo;
        this.voiture = voiture;
        this.pays_origine = pays_origine;
        this.extention=extension;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getVoiture() {
        return voiture;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public String getPays_origine() {
        return pays_origine;
    }

    public void setPays_origine(String pays_origine) {
        this.pays_origine = pays_origine;
    }

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", logo=" + logo + ", voiture=" + voiture + ", pays_origine=" + pays_origine + ", extension=" + this.extention + '}';
    }
    
    
    
    
}
