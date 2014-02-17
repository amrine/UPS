/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.Objects;

/**
 *
 * @author 21007631
 */
public class Personne implements Comparable<Personne>{
    private String nom,prenom;
    
    public Personne(String nom, String prenom){
        this.nom= nom;
        this.prenom = prenom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Personne other = (Personne) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.prenom);
        return hash;
    }


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

    @Override
    public int compareTo(Personne o) {
        int res = nom.compareTo(o.nom);
        if(res == 0){
            res = prenom.compareTo(o.prenom);
        }
        return res;
    }
    
}
