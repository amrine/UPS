/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import java.util.Objects;
import produit.Produit;

/**
 *
 * @author 21007631
 */
public abstract class Article{
    private double prix;
    private Produit produit;

    @Override
    public String toString() {
        return produit.toString() + " " + prix;
    }

    public double getPrix() {
        return prix;
    }

    public Produit getProduit() {
        return produit;
    }

    public Article(double prix, Produit produit) {
        this.prix = prix;
        this.produit = produit;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        
        final Article article = (Article) obj;
        
        if(produit.getNom().equals(article.getProduit().getNom())){
            return true;
        }
        return false;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.produit);
        return hash;
    }
}
