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
public class ArticleAliment extends Article  implements Comparable<Article>{

    private Date expirationDate;

    public ArticleAliment(Produit produit, double prix, Date expirationDate) {
        super(prix, produit);
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return super.toString() + " date exp : " + expirationDate.toString();
    }
    
    
    
    
    @Override
    public int compareTo(Article article) {
        if(getClass() != article.getClass()){
            return -1;
        }else{
            final ArticleAliment v_article = (ArticleAliment) article;
            return expirationDate.compareTo(v_article.expirationDate);
        }
    }
    
}
