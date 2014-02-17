/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package produit;

/**
 *
 * @author Diallo Alpha oumar Binta (21007631)
 */
public class Aliment extends Produit{

    public Aliment(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
