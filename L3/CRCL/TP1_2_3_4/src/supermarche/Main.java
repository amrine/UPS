/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import produit.Aliment;
import produit.Categorie;

/**
 *
 * @author 21007631
 */
public class Main {
    public static void main(String[] args) {
        
        Emplacement eYaourtNature = new Emplacement();
        Aliment yaourtNature = new Aliment("yaourt nature");
        System.out.println(eYaourtNature.ajouterArticle(
                new ArticleAliment(yaourtNature, 2.80, new Date(12, 02, 2012)),
                new ArticleAliment(yaourtNature, 2.80, new Date(21, 02, 2012)),
                new ArticleAliment(yaourtNature, 2.80, new Date(19, 02, 2012)),
                new ArticleAliment(yaourtNature, 2.80, new Date(12, 02, 2012)),
                new ArticleAliment(yaourtNature, 2.80, new Date(21, 02, 2012)),
                new ArticleAliment(yaourtNature, 2.80, new Date(12, 02, 2012))
                ));
        /*
        //prendre un article et afficher l'ensemble des produits d'un emplacement
        ArticleAliment unArticle = (ArticleAliment) eYaourtNature.prendreArticle();
        while(unArticle != null){
            System.out.println(unArticle.toString());
            unArticle = (ArticleAliment) eYaourtNature.prendreArticle();
        }*/
        Emplacement eYaourtFruit = new Emplacement();
        Aliment yaourtFruit = new Aliment("yaourt fruit");
        eYaourtFruit.ajouterArticle(
                new ArticleAliment(yaourtFruit, 3.10, new Date(12, 02, 2012)),
                new ArticleAliment(yaourtFruit, 3.10, new Date(21, 02, 2012)),
                new ArticleAliment(yaourtFruit, 3.10, new Date(19, 02, 2012)),
                new ArticleAliment(yaourtFruit, 3.10, new Date(12, 02, 2012)),
                new ArticleAliment(yaourtFruit, 3.10, new Date(21, 02, 2012)),
                new ArticleAliment(yaourtFruit, 3.10, new Date(12, 02, 2012))
                );
        
        Emplacement eBeurre = new Emplacement();
        Aliment beurre = new Aliment("beurre");
        eBeurre.ajouterArticle(
                new ArticleAliment(beurre, 1.5, new Date(12, 02, 2012)),
                new ArticleAliment(beurre, 1.5, new Date(21, 02, 2012)),
                new ArticleAliment(beurre, 1.5, new Date(19, 02, 2012)),
                new ArticleAliment(beurre, 1.5, new Date(12, 02, 2012)),
                new ArticleAliment(beurre, 1.5, new Date(21, 02, 2012)),
                new ArticleAliment(beurre, 1.5, new Date(12, 02, 2012))
                );
        Emplacement eLait = new Emplacement();
        Aliment lait = new Aliment("lait");
        eLait.ajouterArticle(
                new ArticleAliment(lait, 0.9, new Date(12, 02, 2012)),
                new ArticleAliment(lait, 0.9, new Date(21, 02, 2012)),
                new ArticleAliment(lait, 0.9, new Date(19, 02, 2012)),
                new ArticleAliment(lait, 0.9, new Date(12, 02, 2012)),
                new ArticleAliment(lait, 0.9, new Date(21, 02, 2012)),
                new ArticleAliment(lait, 0.9, new Date(12, 02, 2012))
                );
        Categorie cremerie = new Categorie("Cremerie");
        Rayon rayonCremerie = new Rayon(cremerie);
        rayonCremerie.ajouterEmplacement(eYaourtNature, eLait, eYaourtFruit, eBeurre);
        System.out.println(rayonCremerie.afficherProduitRayon());
        System.out.println(rayonCremerie.afficherProduitPrix());
    }
}
