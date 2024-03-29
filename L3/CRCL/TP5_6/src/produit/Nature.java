/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package produit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diallo Alpha oumar Binta (21007631)
 */
public class Nature<P extends Produit> {
    private String nom;
    private Categorie categorie;
    private List<P> produits = new ArrayList<>();


    public String getNom() {
        return nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public List<P> getProduits() {
        return produits;
    }

    @Override
    public String toString() {
        
        return "Nature{" + "nom=" + nom + ", categorie=" + categorie
                + ", liste=" + produits.toString() + '}';
    }

    public Nature(Categorie categorie, String nom) {
        this.nom = nom;
        this.categorie = categorie;
    }
    
}
