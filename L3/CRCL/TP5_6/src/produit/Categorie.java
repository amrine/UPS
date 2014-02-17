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
public class Categorie {
    private String nom;

    public String getNom() {
        return nom;
    }
    private static List<String> categories = new ArrayList<>();

    public Categorie(String nom) {
        this.nom = nom;
        categories.add(nom);
    }

    public static List<String> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "Categorie{" + "nom=" + nom + '}';
    }
}
