/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import produit.Produit;

/**
 *
 * @author 21007631
 */
public abstract class Article implements Comparable<Article>{
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
    public abstract int compareTo(Article article);

    @Override
    public abstract boolean equals(Object obj);
    
    
}
