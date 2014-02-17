/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package produit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Diallo Alpha oumar Binta (21007631)
 */
public class Categorie <P extends Produit>{
    private String nom;
    private Set<Nature<P>> natures;
    
    public String getNom() {
        return nom;
    }
    public Set<Nature<P>> getNatures(){
        return natures;
    }
    
    public void addNatures(Nature<P> nature){
        natures.add(nature);
    }
    
    public String afficherNatures(){
        String chaine = new String();
        for(Nature<P> nature : natures){
            chaine += nature;
        }
        
        return chaine;
    }
    private static List<String> categories = new ArrayList<>();

    public Categorie(String nom) {
        this.nom = nom;
        categories.add(nom);
        natures = new HashSet<>();
    }

    public static List<String> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "Categorie{" + "nom=" + nom + '}';
    }
}
