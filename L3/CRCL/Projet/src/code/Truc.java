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
public class Truc implements Comparable <Truc>{
    private String nom;
    
    public Truc(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Truc other = (Truc) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return nom.hashCode() * 17;
    }

    @Override
    public int compareTo(Truc o) {
        return nom.compareTo(o.nom);
    }
}
