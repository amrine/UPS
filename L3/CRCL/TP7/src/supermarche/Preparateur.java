/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import client.Liste;
import client.Paire;
import java.util.LinkedHashSet;
import java.util.Set;
import produit.Aliment;
import produit.Produit;

/**
 *
 * @author 21007631
 */
public class Preparateur {
    public Commande preparerCommande(Liste liste, Set<Rayon<Aliment>> rayons){
        Commande commande = new Commande();
        
        Set<Paire<Produit, Double>> result = new LinkedHashSet<>();
        Set<Paire<Produit, Double>> temp;
        for(Rayon<Aliment> rayon : rayons){
            //selection des produits
            temp = liste.selectionnerProduit(rayon);
            //si on a des produits, on ajoute l'article dans le caddie et on le
            //retire du rayon
            if(!temp.isEmpty()){
                for(Paire<Produit, Double> paire : temp){
                    commande.ajouterProduitCaddie(
                            rayon.retirerProduitRayon(paire.getFirst()));
                }
                result.addAll(temp);
            }
            
        }
       
        //gestion des produits non trouve
        Set<Paire<Produit, Double>> nonTrouve = new LinkedHashSet<>(
                liste.getListeCourse());
        nonTrouve.removeAll(result);
        for(Paire<Produit, Double> paire : nonTrouve){
            commande.ajouterProduitNonTrouve(paire.getFirst());
        }
        
        return commande;
    }
}
