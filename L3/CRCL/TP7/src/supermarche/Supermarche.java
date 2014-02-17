/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import client.Liste;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import produit.Aliment;
import produit.Categorie;

/**
 *
 * @author 21007631
 */
public class Supermarche {
    Set<Rayon<Aliment>> rayons;
    Map<Integer, Commande> commandes;
    int numeroCommande = 1;
    Deque<Preparateur> preparateurs;
    
    public Supermarche(){
        rayons = new LinkedHashSet<>();
        commandes = new HashMap<>();
        preparateurs = new ArrayDeque<>();
    }
    
    public Categorie<Aliment> trouverCategorie(String categorieName){
        for(Rayon<Aliment> rayon : rayons){
            if(rayon.isCategorieName(categorieName))
                return rayon.getCategorie();
        }
        return null;
    }
    public List<String> listerNomCategorie(){
        List<String> liste = new ArrayList<>();
        for(Rayon<Aliment> rayon : rayons){
            liste.add(rayon.getCategorieRayon());
        }
        
        return liste;
    }
    public void ajouterPreparateur(Preparateur preparateur){
         preparateurs.offer(preparateur);
    }
    public void ajouterRayon(Rayon rayon){
        rayons.add(rayon);
    }
    public int preparerCommande(Liste listeCourse){
        //preparation de la commande
        Commande commande = preparateurs.peekFirst().preparerCommande(
                listeCourse, rayons);
        //ajout dans la liste des commandes
        commandes.put(numeroCommande, commande);
        int numero = numeroCommande;
        //numero de la commande suivante
        numeroCommande++;
        
        return numero;
    }
    public String afficherCommande(int numero){
        return commandes.get(numero).afficherPrix();
    }
}
