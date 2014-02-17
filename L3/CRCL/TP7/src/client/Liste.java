/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import produit.Produit;
import supermarche.Rayon;

/**
 *
 * @author alphaoumar
 */
public class Liste {
    private Set<Paire<Produit, Double>> listeCourse;

    public Liste() {
        listeCourse = new LinkedHashSet<>();
    }

    public Set<Paire<Produit, Double>> getListeCourse() {
        return listeCourse;
    }
    public void ecrireProduit(Produit produit, double quantite){
        listeCourse.add(new Paire(produit, quantite));
    }
    public boolean barreProduit(Produit produit, double quantite){
        if(listeCourse.contains(new Paire(produit, quantite))){
            return listeCourse.remove(new Paire(produit, quantite));
        }
        return false;
    }
    public String lireListe(){
        String str = "";
        for(Iterator<Paire<Produit, Double>> iter = this.listeCourse.iterator(); iter.hasNext();){
            Paire<Produit, Double> current = iter.next();
            str += "- " + current.getFirst().getNom() + " " + current.getSecond() + "\n";
        }
        return str;
    }
    public void effacerListe(){
        this.listeCourse.clear();
    }
    public Set<Paire<Produit, Double>> selectionnerProduit(Rayon rayon){
        Set<Paire<Produit, Double>> result = new LinkedHashSet<>();
        for(Paire<Produit, Double> paire : listeCourse){
            if(rayon.isProduitRayon(paire.getFirst())){
                result.add(paire);
            }
        }
        return result;
    }
}
