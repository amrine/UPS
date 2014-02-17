/*Auteur : Diallo Alpha Oumar Binta - Yechan Jang
Annee scolaire : 2010-2011
L2 informatique
Projet Amazones
*/

#include <stdlib.h>
#include <stdio.h>
#include "amazone.h"

/****************  P R E S E N T A T I O N   D U   M E N U  *************/

int menu_presentation () {
	int version ;/*
	printf ("\033[34m\n") ;
	printf ("****************************************************************************************************\n\n") ;
	printf ("				     P R O J E T  A M A Z O N E S\n\n") ;
	printf ("					     (VERSION 1)\n") ;
	printf ("				      par Diallo Alpha Oumar Binta et Jang Yechan\n\n") ;
	printf ("****************************************************************************************************\n\n\n") ;
	printf ("\033[0m");
	printf ("		 				M E N U :\n\n") ;*/
   printf(" \n\n  PROJET D'INFORMATIQUE :                                                    ANNEE 2010-2011\n");
    printf("  ___________________________________________________________________________________________\n");
    printf("  \033[43m                                                                                           \033[0m\n");
    printf("  \033[43m                                                                                           \033[0m\n ");
    printf(" \033[43m                     \033[30m     \033[45m          L E   J E U   D E S         \033[43m                           \033[0m\n");
    printf("  \033[43m                    \033[30m           \033[45m       A M A Z O N E S      \033[43m                                \033[0m\n");
    printf("  \033[43m                                                                                           \033[0m\n");   
    printf("  \033[43m                                                                                           \033[0m\n");   
    printf("  \033[43m___________________________________________________________________________________________\033[0m\n\n");
    printf(" 							    ALPHA DIALLO OUMAR | JANG YE CHAN\n\n"); 
       printf("  \033[47m  \033[30m MENU DU JEU : ________________\033[0m\n");
	printf("  \033[47m|\033[0m 1./Joueur vs IA               \033[47m|\033[0m\n");
        printf("  \033[47m|\033[0m 2./Joueur 1 vs Joueur 2       \033[47m|\033[0m\n");
	printf("  \033[47m|\033[0m 3./Charger une partie         \033[47m|\033[0m\n");
	
	printf("  \033[47m ________________________________\033[0m\n\n");
	printf ("	Veuillez choisir une option : ") ;
	do {
		scanf ("%d", &version) ;
		if ((version != 1) && (version != 2) && (version != 3)) {
			printf ("\nCette option n'existe pas !\nVeuillez faire un autre choix : ") ;
		}
	} while ((version != 1) && (version != 2) && (version != 3)) ;
	return (version) ;
}

/******************  A F F I C H A G E  *********************/

/*fonction d'affichage des matrices */

void afficher_matrice (t_matrice m) {
	int i, j ;
	char a = 'a' ;	
	char b = 'a' ;
	printf ("\nVoici la grille de jeu :\n\n   ") ;
	for (i=0; i<N; i++) {		/*affichage des lettres correspondant aux abscisses de la matrice */
		printf ("  %c ", a) ;
		a ++ ;
	}
	printf (" \n") ;
	for (i=N-1; i>=0; i--) {
		printf ("   +") ;        /*lignes de separation horizontale pour la matrice */
		for (j=0; j<N; j++) {
			printf ("---+") ;
		}
		if (i<9) {								/*lignes de la matrice */
			printf ("\n %d |", i+1) ;
		} else {
			printf ("\n%d |", i+1) ;
		}
		for (j=0; j<N; j++) {
			printf (" %c |", m[i][j]) ;
		}
		printf (" %d\n", i+1) ;
	}
	printf ("   +") ;
	for (j=0; j<N; j++) {		/*affichage de la derniere ligne horizontale de la matrice */
		printf ("---+") ;
	}
	printf("\n   ");
	for (i=0; i<N; i++) {
		printf ("  %c ", b) ;
		b ++ ;
	}
	printf (" \n\n\n") ;
	
}

/******************  I N I T I A L I S A T I O N  *********************/
/*fonction d'initialisation des matrices*/

void initialiser_matrice (t_matrice m) {
	int i, j ;
	/* initialisation a vide */
	for (i=0; i<N; i++) {
		for (j=0; j<N; j++) {
			m[i][j] = VIDE ;
		}
	}
}

void initialiser_matrice_jeu(t_matrice m){
  initialiser_matrice(m);
  /* placement des amazones noirs */
	m[0][3] = AMAZONE_BLANCHE ;
	m[0][6] = AMAZONE_BLANCHE ;
	m[3][0] = AMAZONE_BLANCHE ;
	m[3][9] = AMAZONE_BLANCHE ;
	/* placement des amazones blanches */
	m[9][3] = AMAZONE_NOIR ;
	m[9][6] = AMAZONE_NOIR ;
	m[6][0] = AMAZONE_NOIR ;
	m[6][9] = AMAZONE_NOIR ;
	
	
}

