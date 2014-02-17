package vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import client.Client;

import produit.Aliment;
import produit.Categorie;
import produit.GestionProduit;
import produit.Nature;
import supermarche.Supermarche;

public class CreationListeCourse {
	
	private static void inscrireProduit (Categorie<Aliment> categorieChoisie,
                GestionProduit<Aliment> gestionAliment, Client client){
		boolean reponseNature = true;
		Nature<Aliment> nature = CreationListeCourse.choisirNature(categorieChoisie);
		while (reponseNature) {
			Aliment produit = CreationListeCourse.choisirProduit(nature);
			boolean reponse=Clavier.getBool("Voulez-vous ajouter le produit : "
                                +produit+" sur votre liste de course ?");
			if (reponse){
				Double quantite = Clavier.getInteger("En quelle quantite?").doubleValue();
				client.ecrireProduit(produit, quantite);
				System.out.println(quantite);
			}
			
			reponseNature=Clavier.getBool("Voulez-vous ajouter un autre produit de "+nature+" ?");
		}
	}
	
	private static Categorie<Aliment> choisirCategorie(Supermarche supermarche){
		System.out.println("Veuillez selectionner la catégorie");
		List<String> categories = supermarche.listerNomCategorie();
		int num = 1;
		for (String categorie : categories) {
			System.out.println(num+" : "+categorie);
			num++;
		}
                
		int choix;
                do{
                    choix=Clavier.getInteger("Entrer le chiffre correspondant à votre choix : ");
                }while(choix > num-1 || choix <= 0);
                
		String categorieName = categories.get(choix-1);
		Categorie<Aliment> categorieChoisie = supermarche.trouverCategorie(categorieName);
		return categorieChoisie;
	}
	
	private static Nature<Aliment> choisirNature(Categorie<Aliment> categorie){
		System.out.println("Veuillez selectionner la nature du produit");
		Set<Nature<Aliment>> natures = categorie.getNatures();
		List<Nature<Aliment>> listeNature = new ArrayList<>();
		int num = 1;
		for (Nature<Aliment> nature : natures) {
			System.out.println(num+" : "+nature.getNom());
			listeNature.add(nature);
			num++;
		}
		
		int choix;
                do{
                    choix=Clavier.getInteger("Entrer le chiffre correspondant à votre choix : ");
                }while(choix > num-1 || choix <= 0);
		choix--;
		return (Nature<Aliment>)listeNature.get(choix);
	}
	
	private static Aliment choisirProduit(Nature<Aliment> nature){
		System.out.println("Veuillez selectionner le produit");
		List<Aliment> produits = nature.getProduits();
		int num = 1;
		for (Aliment produit : produits) {
			System.out.println(num+" : "+produit.getNom());
			num++;
		}
		
		int choix;
                do{
                    choix=Clavier.getInteger("Entrer le chiffre correspondant à votre choix : ");
                }while(choix > num-1 || choix <= 0);
		choix--;
		return (Aliment) nature.getNumProduit(choix);
	}

	public static void creationListeCourse (Supermarche supermarche, GestionProduit<Aliment> gestionAliment, Client client){
		boolean reponseContinuerListe = true;
		while (reponseContinuerListe) {
			boolean reponseCategorie = true;
			Categorie<Aliment> categorieChoisie = CreationListeCourse.choisirCategorie(supermarche);
			while (reponseCategorie) {
				CreationListeCourse.inscrireProduit(categorieChoisie, gestionAliment, client);
				
				reponseCategorie = Clavier.getBool("Voulez-vous ajouter d'autres produits de "
                                        +categorieChoisie+" ?");
			}
			
			reponseContinuerListe = Clavier.getBool("Voulez-vous poursuivre votre liste de course ? ");
		}
		Integer numeroCommande = supermarche.preparerCommande(client.donnerListe());
		client.ticketDePreparation(numeroCommande);
		System.out.println(supermarche.afficherCommande(numeroCommande));
	}

}
