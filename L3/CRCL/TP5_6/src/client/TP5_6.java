/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import produit.Aliment;
import produit.Categorie;
import produit.GestionProduit;
import produit.Nature;
import supermarche.ArticleAliment;
import supermarche.Date;
import supermarche.Emplacement;
import supermarche.Preparateur;
import supermarche.Rayon;
import supermarche.Supermarche;

/**
 *
 * @author alphaoumar
 */
public class TP5_6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Création des aliments
        Categorie cremerie = new Categorie("Crèmerie");
        Nature<Aliment> fromage = new Nature<>(cremerie, "les fromages");
	Aliment fromageChevre = new Aliment("fromage de chèvre");
        Aliment fromageBleu = new Aliment("fromage bleu");
        
        Nature<Aliment> yaourt = new Nature<>(cremerie, "les yaourts");
	Aliment yaourtNature = new Aliment("yaourt nature");
	Aliment yaourtFruit = new Aliment("yaourt fruit");
		
        Nature<Aliment> beurre = new Nature<>(cremerie, "les beurres");
	Aliment beurreDoux = new Aliment("beurre doux");
	Aliment beurreSale = new Aliment("beurre salé");
		
	Nature<Aliment> lait = new Nature<>(cremerie, "les lait");
	Aliment laitDemiEcreme = new Aliment("lait demi-écrémé");
	Aliment laitEcreme = new Aliment("lait écrémé");
	Aliment laitEntier = new Aliment("lait entier");
		

	Categorie boucherie = new Categorie("Boucherie");
	Nature<Aliment> boeuf = new Nature<>(boucherie, "la viande de boeuf");
        Aliment steackHache = new Aliment("steak haché");
	Aliment bavette = new Aliment("bavette");
	Aliment rumsteck = new Aliment("rumsteck");
		
	Categorie epicerie = new Categorie("Epicerie");
	Nature<Aliment> petitDej = new Nature<>(epicerie, "le petit dejeuner");
	Aliment cafeDeca = new Aliment("café décaféiné");
        Aliment cafe = new Aliment("café");
        
    
        //Gestion des aliments
        GestionProduit<Aliment> gestionAliment = new GestionProduit<>();
	gestionAliment.ajouterNatureProduit(fromage, yaourt, beurre, lait, boeuf,petitDej);
	gestionAliment.ajouterProduit(fromage, fromageChevre, fromageBleu);
        gestionAliment.ajouterProduit(yaourt, yaourtNature, yaourtFruit);
        gestionAliment.ajouterProduit(beurre, beurreSale, beurreDoux);
        gestionAliment.ajouterProduit(lait, laitEcreme, laitDemiEcreme, laitEntier);
	gestionAliment.ajouterProduit(boeuf, steackHache, bavette, rumsteck);
        gestionAliment.ajouterProduit(petitDej, cafeDeca, cafe);
        
        // Création des emplacements dans les rayons
        Emplacement eYaourtNature = new Emplacement();
        eYaourtNature.ajouterArticle(new ArticleAliment(yaourtNature, 2.80,new Date(12,2,2012)),
				new ArticleAliment(yaourtNature, 2.80,new Date(21,2,2012)),
				new ArticleAliment(yaourtNature, 2.80,new Date(19,2,2012)),
				new ArticleAliment(yaourtNature, 2.80,new Date(12,2,2012)),
				new ArticleAliment(yaourtNature, 2.80,new Date(21,2,2012)),
				new ArticleAliment(yaourtNature, 2.80,new Date(12,2,2012)));
	Emplacement eYaourtFruit = new Emplacement();
	eYaourtFruit.ajouterArticle(new ArticleAliment(yaourtFruit, 3.10,new Date(12,2,2012)),
				new ArticleAliment(yaourtFruit, 3.10,new Date(21,2,2012)),
				new ArticleAliment(yaourtFruit, 3.10,new Date(19,2,2012)),
				new ArticleAliment(yaourtFruit, 3.10,new Date(12,2,2012)),
				new ArticleAliment(yaourtFruit, 3.10,new Date(21,2,2012)),
				new ArticleAliment(yaourtFruit, 3.10,new Date(12,2,2012)));
	Emplacement eBeurreDoux = new Emplacement();
	eBeurreDoux.ajouterArticle(new ArticleAliment(beurreDoux, 1.39,new Date(12,2,2012)),
				new ArticleAliment(beurreDoux, 1.39,new Date(21,2,2012)),
				new ArticleAliment(beurreDoux, 1.39,new Date(19,2,2012)),
				new ArticleAliment(beurreDoux, 1.39,new Date(12,2,2012)),
				new ArticleAliment(beurreDoux, 1.39,new Date(21,2,2012)),
				new ArticleAliment(beurreDoux, 1.39,new Date(12,2,2012)));
	Emplacement eBeurreSale = new Emplacement();
	eBeurreSale.ajouterArticle(new ArticleAliment(beurreSale, 1.42,new Date(12,2,2012)),
				new ArticleAliment(beurreSale, 1.42,new Date(21,2,2012)),
				new ArticleAliment(beurreSale, 1.42,new Date(19,2,2012)),
				new ArticleAliment(beurreSale, 1.42,new Date(12,2,2012)),
				new ArticleAliment(beurreSale, 1.42,new Date(21,2,2012)),
				new ArticleAliment(beurreSale, 1.42,new Date(12,2,2012)));
	Emplacement eLaitDemiEcreme = new Emplacement();
	eLaitDemiEcreme.ajouterArticle(new ArticleAliment(laitDemiEcreme, 0.72,new Date(12,2,2012)),
				new ArticleAliment(laitDemiEcreme, 0.72, new Date(21,2,2012)),
				new ArticleAliment(laitDemiEcreme, 0.72, new Date(19,2,2012)),
				new ArticleAliment(laitDemiEcreme, 0.72, new Date(12,2,2012)),
				new ArticleAliment(laitDemiEcreme, 0.72, new Date(21,2,2012)),
				new ArticleAliment(laitDemiEcreme, 0.72, new Date(12,2,2012)));
	Emplacement eLaitEcreme = new Emplacement();
	eLaitEcreme.ajouterArticle(new ArticleAliment(laitEcreme, 0.73, new Date(12,2,2012)),
				new ArticleAliment(laitEcreme, 0.73, new Date(21,2,2012)),
				new ArticleAliment(laitEcreme, 0.73, new Date(19,2,2012)),
				new ArticleAliment(laitEcreme, 0.73, new Date(12,2,2012)),
				new ArticleAliment(laitEcreme, 0.73, new Date(21,2,2012)),
				new ArticleAliment(laitEcreme, 0.73, new Date(12,2,2012)));
	Emplacement eLaitEntier = new Emplacement();
	eLaitEntier.ajouterArticle(new ArticleAliment(laitEntier, 1.03, new Date(12,2,2012)),
				new ArticleAliment(laitEntier, 1.03, new Date(21,2,2012)),
				new ArticleAliment(laitEntier, 1.03, new Date(19,2,2012)),
				new ArticleAliment(laitEntier, 1.03, new Date(12,2,2012)),
				new ArticleAliment(laitEntier, 1.03, new Date(21,2,2012)),
				new ArticleAliment(laitEntier, 1.03, new Date(12,2,2012)));
	Emplacement eFromageChevre = new Emplacement();
	eFromageChevre.ajouterArticle(new ArticleAliment(fromageChevre, 13.80, new Date(12,2,2012)),
				new ArticleAliment(fromageChevre, 13.80, new Date(21,2,2012)),
				new ArticleAliment(fromageChevre, 13.80, new Date(19,2,2012)),
				new ArticleAliment(fromageChevre, 13.80, new Date(12,2,2012)),
				new ArticleAliment(fromageChevre, 13.80, new Date(21,2,2012)),
				new ArticleAliment(fromageChevre, 13.80, new Date(12,2,2012)));
	Emplacement eFromageBleu = new Emplacement();
	eFromageBleu.ajouterArticle(new ArticleAliment(fromageBleu, 10.90, new Date(12,2,2012)),
				new ArticleAliment(fromageBleu, 10.90, new Date(21,2,2012)),
				new ArticleAliment(fromageBleu, 10.90, new Date(19,2,2012)),
				new ArticleAliment(fromageBleu, 10.90, new Date(12,2,2012)),
				new ArticleAliment(fromageBleu, 10.90, new Date(21,2,2012)),
				new ArticleAliment(fromageBleu, 10.90, new Date(12,2,2012)));
		
	Rayon rayonCremerie = new Rayon(cremerie);
	rayonCremerie.ajouterEmplacement(eYaourtNature,eYaourtFruit,eLaitDemiEcreme,eLaitEcreme, eLaitEntier, eBeurreDoux,eBeurreSale,eFromageChevre,eFromageBleu);
		
	Emplacement eSteackHache = new Emplacement();
	eSteackHache.ajouterArticle(new ArticleAliment(steackHache, 9.9,new Date(12,2,2012)),
				new ArticleAliment(steackHache, 9.9,new Date(21,2,2012)),
				new ArticleAliment(steackHache, 9.9,new Date(19,2,2012)),
				new ArticleAliment(steackHache, 9.9,new Date(12,2,2012)),
				new ArticleAliment(steackHache, 9.9,new Date(21,2,2012)),
				new ArticleAliment(steackHache, 9.9,new Date(12,2,2012)));
	Emplacement eBavette = new Emplacement();
	eBavette.ajouterArticle(new ArticleAliment(bavette, 16.9,new Date(12,2,2012)),
				new ArticleAliment(bavette, 16.9,new Date(21,2,2012)),
				new ArticleAliment(bavette, 16.9,new Date(19,2,2012)),
				new ArticleAliment(bavette, 16.9,new Date(12,2,2012)),
				new ArticleAliment(bavette, 16.9,new Date(21,2,2012)),
				new ArticleAliment(bavette, 16.9,new Date(12,2,2012)));

	Emplacement eRumsteck = new Emplacement();
	eRumsteck.ajouterArticle(new ArticleAliment(rumsteck, 0.9, new Date(12,2,2012)),
				new ArticleAliment(rumsteck, 16.9,new Date(21,2,2012)),
				new ArticleAliment(rumsteck, 16.9,new Date(19,2,2012)),
				new ArticleAliment(rumsteck, 16.9,new Date(12,2,2012)),
				new ArticleAliment(rumsteck, 16.9,new Date(21,2,2012)),
				new ArticleAliment(rumsteck, 16.9,new Date(12,2,2012)));
		
	Rayon rayonBoucherie = new Rayon(boucherie);
	rayonBoucherie.ajouterEmplacement(eSteackHache,eBavette,eRumsteck);		

	Emplacement eCafeDeca = new Emplacement();
        eCafeDeca.ajouterArticle(new ArticleAliment(cafeDeca, 2.39, new Date(12,2,2012)),
				new ArticleAliment(cafeDeca, 2.39, new Date(21,2,2012)),
				new ArticleAliment(cafeDeca, 2.39, new Date(19,2,2012)),
				new ArticleAliment(cafeDeca, 2.39, new Date(12,2,2012)),
				new ArticleAliment(cafeDeca, 2.39, new Date(21,2,2012)),
				new ArticleAliment(cafeDeca, 2.39, new Date(12,2,2012)));
	Emplacement eCafe = new Emplacement();
	eCafe.ajouterArticle(new ArticleAliment(cafe, 2.66, new Date(12,2,2012)),
				new ArticleAliment(cafe, 2.66, new Date(21,2,2012)),
				new ArticleAliment(cafe, 2.66, new Date(19,2,2012)),
				new ArticleAliment(cafe, 2.66, new Date(12,2,2012)),
				new ArticleAliment(cafe, 2.66, new Date(21,2,2012)),
				new ArticleAliment(cafe, 2.66, new Date(12,2,2012)));
		
	Rayon rayonEpicerie = new Rayon(epicerie);
	rayonEpicerie.ajouterEmplacement(eCafe,eCafeDeca);
        System.out.println(rayonEpicerie.toString());
        System.out.println("Le cafe est un produit du rayon epicerie : "
                + rayonEpicerie.isProduitRayon(cafe));
        System.out.println("retirer produit cafe : "
                + rayonEpicerie.retirerProduitRayon(cafe));
        System.out.println("Le cafe est un produit du rayon epicerie : "
                + rayonEpicerie.isProduitRayon(cafe));
        
        
        Liste maListe = new Liste();
        
        maListe.ecrireProduit(laitDemiEcreme, 6.0);
        maListe.ecrireProduit(beurreDoux, 1.0);
        maListe.ecrireProduit(cafe, 6.0);
        maListe.ecrireProduit(rumsteck, 1.0);
        maListe.ecrireProduit(cafeDeca, 3.0);
        System.out.print(maListe.selectionnerProduit(rayonEpicerie));
        
        //TEST TP5&6
        //Création du supermaché composé de rayons
	Supermarche supermarche = new Supermarche();
        supermarche.ajouterRayon(rayonEpicerie);
        supermarche.ajouterRayon(rayonBoucherie);
	supermarche.ajouterRayon(rayonCremerie);
        //Création d'un préparateur de commandes
	Preparateur preparateur1 = new Preparateur();
	supermarche.ajouterPreparateur(preparateur1);
	//Création du client
	Client client = new Client();
	//TEST TP7
	//GESTION de la selection d'un produit par affichage
	//CreationListeCourse.creationListeCourse(supermarche, gestionAliment, client);

    }
}
