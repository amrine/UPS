/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import java.util.*;
import produit.Categorie;
import produit.Produit;

/**
 *
 * @author 21007631
 */
public class Rayon {
    Set<Emplacement> listeEmplacement;
    Categorie rayonCategorie;

    public Rayon(Categorie rayonCategorie) {
        this.rayonCategorie = rayonCategorie;
        listeEmplacement = new HashSet<>();
    }
    
    public void ajouterEmplacement(Emplacement... emplacement){
        this.listeEmplacement.addAll(Arrays.asList(emplacement));
    }
    
    public String afficherProduitRayon(){
        String str = "";
        for(Emplacement emplacement : listeEmplacement){
            String result = emplacement.voirArticle();
            if(result != null){
                str += result + "\n";
            }
        }
        return str;
    }
    public String afficherProduitPrix(){
        int size = listeEmplacement.size();
        PriorityQueue<Article> trieArticle = new PriorityQueue(size, new Comparator<Article>() {

            @Override
            public int compare(Article a1, Article a2) {
                //ordre decroissant Double.compare(a2.getPrix(), a1.getPrix());
                //ordre croissant Double.compare(a1.getPrix(), a2.getPrix();
                return Double.compare(a2.getPrix(), a1.getPrix());
            }
        });
        
        for(Emplacement emplacement : listeEmplacement){
            Article result = emplacement.prendreArticle();
            if(result != null){
                trieArticle.offer(result);
            }
        }
        
        Article article = trieArticle.poll();
        String str = "";
        while(article != null){
            str += article.getProduit().getNom() + "\n";
            article = trieArticle.poll();
        }
        
        return str;
    }
    public boolean isProduitRayon(Produit produit){
        Emplacement emplacement = new Emplacement();
        emplacement.ajouterArticle(new ArticleDegenere(0, produit));
        return listeEmplacement.contains(emplacement);
    }
    
    public Article retirerProduitRayon(Produit produit){
        Article result = null;
        if(isProduitRayon(produit)){
            Emplacement emplacement = new Emplacement();
            emplacement.ajouterArticle(new ArticleDegenere(0, produit));
            for(Emplacement current : listeEmplacement){
                if(current.equals(emplacement)){
                    result = current.prendreArticle();
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Rayon" + " : " + rayonCategorie.getNom();
    }
    
}
