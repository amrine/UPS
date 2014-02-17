/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package produit;

/**
 *
 * @author Diallo Alpha oumar Binta (21007631)
 */
public abstract class Produit {
    String nom;
    
    public Produit(String nom){
        this.nom = nom;
    }
    
    public String getNom(){
        return nom;
    }

    @Override
    public String toString() {
        return "Produit{" + "nom=" + nom + '}';
    }
}
