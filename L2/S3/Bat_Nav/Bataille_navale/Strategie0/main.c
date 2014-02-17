/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <stdbool.h>
#include "constantes.h"
#include "presentation.h"
#include "affichage_init.h"
#include "utilitaire.h"
#include "verifications.h"
#include "placement.h"
#include "placer_tous.h"
#include "joueur.h"
#include "IAniveau0.h"
#include "procedure.h"


int main () {
	/*****Declaration des matrices ***/
	t_matrice m_videJ; /***matrice vide pour le joueur*/
	t_matrice m_ordi; /***matrice de l'ordi*/
	t_matrice m_joueur; /***matrice du joueur*/
	t_matrice m_videIA; /***matrice vide pour l'ordinateur*/
	liste_bateau joueur;
	liste_bateau ordi;
	/*Initialisation des matrices*/
	initialiser_matrice(m_videJ);
	initialiser_matrice(m_videIA);
	initialiser_matrice(m_joueur);
	initialiser_matrice(m_ordi);
	ordi.nbre_bateau=NB_P + NB_C + NB_D + NB_V+1;
	joueur.nbre_bateau=NB_P + NB_C + NB_D + NB_V+1;
	int nbcase_joueur= 20;
   	int nbcase_ordi= 20;
 	int rep=20;
	int version ;
	srand(time(NULL)) ;
	version = menu_presentation () ;
	switch (version) {
		case 1 :
            		placer_aleatoire (joueur,m_joueur);
			afficher_matrice(m_joueur);
			break ;
		case 2 :
			afficher_matrice(m_joueur);
			placer_manuel (joueur,m_joueur);
			afficher_matrice(m_joueur);
			break ;
	}
	printf ("Tous vos bateaux sont places.\n\n") ;

      /*** Placement des bateaux de l'ordi ***/
    placer_aleatoire(ordi,m_ordi);
    printf ("Tous les bateaux de l'ordinateur sont places.\n\n") ;
    printf ("Debut du jeu.\n\n") ;
    while ( rep != 0)
    {
        rep=procedure(m_videIA,m_joueur,&nbcase_joueur,m_videJ,m_ordi,&nbcase_ordi);

    }
	return 0 ;
}
