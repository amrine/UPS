/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import java.util.*;
import produit.Categorie;

/**
 *
 * @author 21007631
 */
public class Rayon {
    List<Emplacement> listeEmplacement;
    Categorie rayonCategorie;

    public Rayon(Categorie rayonCategorie) {
        this.rayonCategorie = rayonCategorie;
        listeEmplacement = new LinkedList();
    }
    
    public void ajouterEmplacement(Emplacement... emplacement){
        this.listeEmplacement.addAll(Arrays.asList(emplacement));
    }
    
    public String afficherProduitRayon(){
        String str = "";
        for(ListIterator<Emplacement> iter = listeEmplacement.listIterator(); iter.hasNext();){
            Emplacement emplacement = iter.next();
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
        
        for(ListIterator<Emplacement> iter = listeEmplacement.listIterator(); iter.hasNext();){
            Emplacement emplacement = iter.next();
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
    
    
}
