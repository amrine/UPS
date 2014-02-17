/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Amazones
*/


#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <stdbool.h>
#include "amazone.h"



int main () {
	t_matrice m;
	int version;
	
	  srand(time(NULL));
	
version = menu_presentation () ;
	switch (version) {
		case 1 :
			initialiser_matrice_jeu(m);
			joueur_vs_ordi(m);
			break ;
		case 2 :
			initialiser_matrice_jeu(m);
			afficher_matrice(m);
			joueur1_vs_joueur2(m);
			break ;
		case 3 :
			printf ("  Charger une partie \n") ;
			initialiser_matrice(m);
			load(m);
			afficher_matrice(m);
			printf ("Le joueur Humain joue avec les Amazones Blanches\n") ;
			joueur_vs_ordi(m);
			break ;
	}
	return 0;
}
