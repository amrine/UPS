/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import java.util.*;
import java.util.Map.Entry;
import produit.Produit;

/**
 *
 * @author 21007631
 */
public class Commande {
    Map<Produit, Double> produitNonTrouve;
    Map<Article, Double> caddie;

    public Commande() {
        produitNonTrouve = new HashMap<>();
        caddie = new HashMap<>();
    }
    
    public void ajouterProduitNonTrouve(Produit produit){
        if(produitNonTrouve.containsKey(produit)){
            produitNonTrouve.put(produit, produitNonTrouve.get(produit)+1);
        }else
            produitNonTrouve.put(produit, 1.0);
    }
    
    public void ajouterProduitCaddie(Article article){
        if(caddie.containsKey(article)){
            caddie.put(article, caddie.get(article)+1);
        }else
            caddie.put(article, 1.0);
    }
    
    public String afficherCommande(){
        String str = (!produitNonTrouve.isEmpty()) ? "PRODUIT(S) NON TROUVE(S)"
                + "\n" : "";
        if(!produitNonTrouve.isEmpty()){
           for(Entry<Produit, Double> current : produitNonTrouve.entrySet()){
               str += "- " + current.getValue() + " " + current.getKey().getNom()
                    + "\n";
            } 
        }
        
        str += (!caddie.isEmpty()) ? "PRODUIT(S) ACHETE(S)"
                + "\n" : "";
        if(!caddie.isEmpty()){
           for(Entry<Article, Double> current : caddie.entrySet()){
               str += "- " + current.getValue() + " "
                       + current.getKey().toString() + "\n";
            } 
        }
        
        return str;
    }
    
    public String afficherPrix(){
        double prix = 0;
        if(!caddie.isEmpty()){
           for(Article current : caddie.keySet()){
               prix += current.getPrix();
            } 
        }
        return "" + prix;
    }
}
