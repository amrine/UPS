/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21007631
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //creation des commentaires
        Commentaire comment1 = new Commentaire("Tres bon", 10);
        Commentaire comment2 = new Commentaire("Bien", 8);
        Commentaire comment3 = new Commentaire("Assez bien", 6);
        Commentaire comment4 = new Commentaire("Passable", 5);
        Commentaire comment5 = new Commentaire("Mediocre", 2);
        //creation de jeux
        Jeu j1 = new Jeu("Monopoly");
        Jeu j2 = new Jeu("Belote");
        Jeu j3 = new Jeu("Echecs");
        Jeu j4 = new Jeu("snake");
        //creation de membre
        Membre<Jeu> dupont = new Membre("Dupont", "Alex");
        Membre<Jeu> dupont2 = new Membre("Dupont", "Alew");
        Membre<Jeu> alpha = new Membre("Diallo", "Alpha");
        Membre<Jeu> oumou = new Membre("Barry", "Oumou");
        //ajout de truc pour les membres
        alpha.ajouterTruc(j1);
        alpha.ajouterTruc(j1);
        alpha.ajouterTruc(j2);
        alpha.ajouterTruc(j3);
        dupont.ajouterTruc(j1);
        dupont.ajouterTruc(j1);
        dupont.ajouterTruc(j2);
        dupont.ajouterTruc(j3);
        dupont.ajouterTruc(j4);
        dupont2.ajouterTruc(j1);
        dupont2.ajouterTruc(j3);
        oumou.ajouterTruc(j3);
        //ajout de commentaire
        alpha.getCommentPret().ajouterCommentaire(comment1);
        dupont.getCommentPret().ajouterCommentaire(comment5);
        dupont.getCommentPret().ajouterCommentaire(comment4);
        dupont.getCommentPret().ajouterCommentaire(comment5);
        dupont.getCommentPret().ajouterCommentaire(comment5);
        dupont2.getCommentPret().ajouterCommentaire(comment5);
        dupont2.getCommentPret().ajouterCommentaire(comment4);
        dupont2.getCommentPret().ajouterCommentaire(comment5);
        dupont2.getCommentPret().ajouterCommentaire(comment5);
        oumou.getCommentEmprunt().ajouterCommentaire(comment2);
        oumou.getCommentEmprunt().ajouterCommentaire(comment3);
        dupont2.getCommentEmprunt().ajouterCommentaire(comment1);
        System.out.println("---------------Trucotheque----------------------");
        System.out.println("Jeu recherché: "+dupont.chercherTruc(j3));
        System.out.println("Les Trucs de " + dupont.toString()+ " : "
                + dupont.getObjets());
        System.out.println("Suppression de " + j1.toString() + " et "
                + j3.toString() + " de " + dupont.toString()+ " : "
                + dupont.getObjets());
        dupont.supprimerTruc(j1);
        dupont.supprimerTruc(j3);
        System.out.println("Les Trucs de " + dupont.toString()+ " : "
                + dupont.getObjets());
        System.out.println("-------Ajout de membre dans la Trucotheque------");
        Trucotheque<Jeu> trucoJeu= new Trucotheque("Jeux");
        System.out.println("ajout d'un Membre " + trucoJeu.ajouterMembre(dupont));
        System.out.println("ajout d'un Membre " + trucoJeu.ajouterMembre(alpha));
        System.out.println("ajout d'un Membre " + trucoJeu.ajouterMembre(dupont2));
        System.out.println("ajout d'un Membre " + trucoJeu.ajouterMembre(oumou));
        System.out.println("Statistiques :");
        System.out.println(dupont.printMembreStats());
        System.out.println(alpha.printMembreStats());
        System.out.println(dupont2.printMembreStats());
        System.out.println(oumou.printMembreStats());
        System.out.println("Liste des meilleurs preteurs " + trucoJeu.meilleursPreteurs());
        System.out.println("Liste des meilleurs emprunteurs " + trucoJeu.meilleursEmprunteurs());
        System.out.println("Liste des membres " + trucoJeu.getListeMembres());
        System.out.println("----Black Liste----");
        System.out.println(dupont.toString() + " => Black Liste : " + trucoJeu.getMembre(dupont).getBlackList());
        System.out.println("Ajout de " + alpha.toString() + " dans la black liste de "
                + dupont.toString());
        trucoJeu.getMembre(dupont).ajouterMembreBlackList(alpha);
        System.out.println(dupont.toString() + " => Black Liste : " + trucoJeu.getMembre(dupont).getBlackList());        
        System.out.println(trucoJeu.printMembreTrucs());
        System.out.println("Pret du jeu " + j4.toString() + " à " + alpha.toString()
                + " : " +trucoJeu.emprunterTruc(j4, alpha) + " (emprunteur dans la black liste)");
        System.out.println("Pret du jeu " + j4.toString() + " à " + oumou.toString()
                + " : " +trucoJeu.emprunterTruc(j4, oumou));
        System.out.println(trucoJeu.printMembreTrucs());
        System.out.println("Liste des trucs empruntes par "  + oumou.toString()
                + trucoJeu.getMembre(oumou).getTrucEmprunte());
        System.out.println("Pret du jeu " + j4.toString() + " à " + dupont2.toString()
                + " : " +trucoJeu.emprunterTruc(j4, dupont2) + " (ajouter dans la liste Attente)");
        System.out.println("Pret du jeu " + j4.toString() + " à " + oumou.toString()
                + " : " +trucoJeu.emprunterTruc(j4, oumou) + " (ajouter dans la liste Attente)");
        System.out.println("Pret du jeu " + j4.toString() + " à " + alpha.toString()
                + " : " +trucoJeu.emprunterTruc(j4, alpha) + " (emprunteur dans la black liste)");
        System.out.println(trucoJeu.printMembreTrucs());
        System.out.println("Liste des trucs empruntes par "  + dupont2.toString()
                + trucoJeu.getMembre(dupont2).getTrucEmprunte());
        System.out.println(oumou.toString() + " rends le truc " + j4.toString() + " : "
                + trucoJeu.getMembre(oumou).rendreTruc(j4));
        System.out.println("Le truc " + j4 + " est prete automatiquement au premier"
                + " de la file d'attente donc " + dupont2);
        System.out.println("Liste des trucs empruntes par "  + oumou.toString()
                + trucoJeu.getMembre(oumou).getTrucEmprunte());
        System.out.println("Liste des trucs empruntes par "  + dupont2.toString()
                + trucoJeu.getMembre(dupont2).getTrucEmprunte());
        System.out.println("\n----Test de la file d'attente pleine----");
        for(int i = 0; i < 10; i++){
            System.out.print(trucoJeu.ajouterMembre(new Membre("alpha"+i,"Diallo"+i))
                    + " veut emprunter " + j4 + " => ");
            System.out.println(trucoJeu.emprunterTruc(j4, new Membre("alpha"+i,"Diallo"+i)));
        }
        System.out.println("\nFile d'attente de " + dupont.toString() + "=> " + dupont.getListeAttente(j4));
        System.out.println(dupont2.toString() + " rends le truc " + j4.toString() + " : "
                + trucoJeu.getMembre(dupont2).rendreTruc(j4));
        System.out.println("File d'attente de " + dupont.toString() + "=> " + dupont.getListeAttente(j4));
        System.out.println("Liste des trucs empruntes par "  + oumou.toString()
                + trucoJeu.getMembre(oumou).getTrucEmprunte());
        System.out.println(trucoJeu.printMembreTrucs());
        
        System.out.println("Statistiques :");
        System.out.println(dupont.printMembreStats());
        System.out.println(alpha.printMembreStats());
        System.out.println(dupont2.printMembreStats());
        System.out.println(oumou.printMembreStats());
        System.out.println("\nListe des meilleurs preteurs " + trucoJeu.meilleursPreteurs());
        System.out.println("Liste des meilleurs emprunteurs " + trucoJeu.meilleursEmprunteurs());
   }
}
