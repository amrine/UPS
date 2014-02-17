/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.HashSet;
import java.util.Set;
import produit.Produit;

/**
 *
 * @author alphaoumar
 */
public class Client {
    private Liste listeCourse;
    private Set<Integer> ensembleCommande;

    public Client() {
        this.ensembleCommande = new HashSet<>();
        listeCourse = new Liste();
    }
    
    public void ecrireProduit(Produit produit, double quantite){
        listeCourse.ecrireProduit(produit, quantite);
    }
    
    public Liste donnerListe(){
        Liste copie = new Liste();
       
        Set<Paire<Produit, Double>> other = listeCourse.getListeCourse();
        for(Paire<Produit, Double> paire : other){
            copie.ecrireProduit(paire.getFirst(), paire.getSecond());
        }
        
        listeCourse.effacerListe();
        
        return copie;
    }
    
    public void ticketDePreparation(Integer numeroCommande){
        ensembleCommande.add(numeroCommande);
    }
}
