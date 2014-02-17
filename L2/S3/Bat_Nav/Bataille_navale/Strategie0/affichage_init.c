/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include "constantes.h"
#include "affichage_init.h"

/******************  A F F I C H A G E  *********************/

/*fonction d'affichage des matrices */

void afficher_matrice (t_matrice m) {
	int i, j ;
	char a = 'A' ;

	printf ("\nVoici la grille de placement des bateaux :\n\n   ") ;
	for (i=0; i<N; i++) {
		printf ("| %c ", a) ;
		a ++ ;
	}
	printf ("|\n") ;
	for (i=0; i<N; i++) {
		for (j=0; j<=N; j++) {
			printf ("---+") ;
		}
		if (i<9) {
			printf ("\n %d |", i+1) ;
		} else {
			printf ("\n%d |", i+1) ;
		}
		for (j=0; j<N; j++) {
			printf (" %c |", m[i][j]) ;
		}
		printf ("\n") ;
	}
	for (j=0; j<=N; j++) {
		printf ("---+") ;
	}
	printf ("\n\n") ;
}

/*fonction d'affiche de deux matrices */

void afficher_2_matrices (t_matrice m_def, t_matrice m_att) {
	int i, j, a ;
	a = 'A' ;         /*affichage des lettres correspondant aux abscisses de la matrice de placement*/
	for (i=0; i<N; i++) {
		printf ("| %c ", a) ;
		a ++ ;
	}
	printf ("|               ") ;
	a = 'A' ;         /*affichage des lettres correspondant aux abscisses de la matrice des attaques*/
	for (i=0; i<N; i++) {
		printf ("| %c ", a) ;
		a ++ ;
	}
	printf ("|\n") ;
	for (i=0; i<N; i++) {
		for (j=0; j<=N; j++) {        /*lignes de separation horizontale pour la matrice de placement*/
			printf ("---+") ;
		}
		printf ("            ") ;
		for (j=0; j<=N; j++) {        /*lignes de separation horizontale pour la matrice des attaques*/
			printf ("---+") ;
		}
		if (i<9) {        /*lignes de la matrice de placement*/
			printf ("\n %d |", i+1) ;
		} else {
			printf ("\n%d |", i+1) ;
		}
		for (j=0; j<N; j++) {
			printf (" %c |", m_def[i][j]) ;
		}
		printf ("            ") ;
		if (i<9) {        /*lignes de la matrice des attaques*/
			printf (" %d |", i+1) ;
		} else {
			printf ("%d |", i+1) ;
		}
		for (j=0; j<N; j++) {
			printf (" %c |", m_att[i][j]) ;
		}
		printf ("\n") ;
	}
	for (j=0; j<=N; j++) {   /*affichage de la derniere ligne horizontale de la matrice de placement*/
		printf ("---+") ;
	}
	printf ("            ") ;
	for (j=0; j<=N; j++) {   /*affichage de la derniere ligne horizontale de la matrice des attaques*/
		printf ("---+") ;
	}
	printf ("\n\n") ;
}

/*fonction d'initialisation des matrices*/

void initialiser_matrice (t_matrice m) {
	int i, j ;

	for (i=0; i<N; i++) {
		for (j=0; j<N; j++) {
			m[i][j] = VIDE ;
		}
	}
}
