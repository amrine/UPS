/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author 21007631
 */
public class Emplacement {
    Queue<Article> theQueue;

    public Emplacement() {
        this.theQueue = new PriorityQueue(7);
    }
    
    public String ajouterArticle(Article... listOfArticles){
        int cpt = 0;
        for(Article article : listOfArticles){
            if(theQueue.offer(article)){
                cpt++;
            }else{
                return cpt + " " + article.getProduit().getNom() + " sur " 
                        + listOfArticles.length + " ont été ajoutés";
            }
        }
        return "Les " + listOfArticles.length + " "
                + listOfArticles[0].getProduit().getNom() + " ont été ajoutés";
    }
    
    public Article prendreArticle(){
        return this.theQueue.poll();
    }
    
    public String voirArticle(){
        Article article = theQueue.peek();
        
        if(article != null){
            return article.getProduit().getNom();
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emplacement other = (Emplacement) obj;
        return (other.voirArticle().equals(voirArticle()));
        
    }

    @Override
    public int hashCode() {
       return voirArticle().hashCode();
    }
}
