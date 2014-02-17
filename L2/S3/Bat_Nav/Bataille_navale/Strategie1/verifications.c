/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "verifications.h"
#include "constantes.h"

/******************  V E R I F I C A T I O N S  *****************/

/*fonction qui teste si le caractere passe en parametre est vide*/

bool case_vide (char c) {
	return (c == VIDE) ;
}

/*fonction booleenne qui verifie si les coordonnees appartiennent à la grille */

bool contenu_ds_grille (int abs_dep,int ord_dep)
{
    return ((abs_dep >=0 && abs_dep < N) && (ord_dep >=0 && ord_dep < N));
}

/*fonction booleenne qui verifie si le caractere passe en parametre est un bateau */

bool bateau (char c)
{
  return ( c== PORTEAVION || c== CROISEUR || c== DESTROYER || c== VEDETTE);
}


/* fonction booleenne qui teste que les bateaux ne se touchent pas */

bool espace_libre_autour (s_bateau bat, t_matrice m) {
	bool b = true ;
	int i ;
	switch (bat.orientation) {
		case ('E') :
			for (i=0; i<3; i++) {
				if (contenu_ds_grille (bat.abs_dep - 1, bat.ord_dep + i - 1) && b) {
					b = case_vide (m[bat.ord_dep + i - 1][bat.abs_dep - 1]) ;
				} if (contenu_ds_grille (bat.abs_dep + bat.taille, bat.ord_dep + i - 1) && b) {
					b = case_vide (m[bat.ord_dep + i - 1][bat.abs_dep + bat.taille]) ;
				}
			}
			for (i=0; i<bat.taille; i++) {
				if (contenu_ds_grille (bat.abs_dep + i, bat.ord_dep - 1) && b) {
					b = case_vide (m[bat.ord_dep - 1][bat.abs_dep + i]) ;
				} if (contenu_ds_grille (bat.abs_dep + i, bat.ord_dep + 1) && b) {
					b = case_vide (m[bat.ord_dep + 1][bat.abs_dep + i]) ;
				}
			}
			break ;
		case ('S') :
			for (i=0; i<3; i++) {
				if (contenu_ds_grille (bat.abs_dep + i - 1, bat.ord_dep - 1) && b) {
					b = case_vide (m[bat.ord_dep - 1][bat.abs_dep + i - 1]) ;
				} if (contenu_ds_grille (bat.abs_dep + i - 1, bat.ord_dep + bat.taille) && b) {
					b = case_vide (m[bat.ord_dep + bat.taille][bat.abs_dep + i - 1]) ;
				}
			}
			for (i=0; i<bat.taille; i++) {
				if (contenu_ds_grille (bat.abs_dep - 1, bat.ord_dep + i) && b) {
					b = case_vide (m[bat.ord_dep + i][bat.abs_dep - 1]) ;
				} if (contenu_ds_grille (bat.abs_dep + 1, bat.ord_dep + i) && b) {
					b = case_vide (m[bat.ord_dep + i][bat.abs_dep + 1]) ;
				}
			}
			break ;
		default :
			b = false ;
			printf ("Ce cas n'existe pas !\n\n") ;
			break ;
	}
	return (b) ;
}

/*fonction booleenne qui verifie si le placement d'un bateau est correct*/

bool placement_correct (s_bateau bat, t_matrice m) {
	bool b ;
	int i ;
	b = espace_libre_autour (bat, m) ;
	switch (bat.orientation) {
		case ('E') :
			for (i=0; i<bat.taille; i++) {
				if (b) {
					b = case_vide (m[bat.ord_dep][bat.abs_dep + i]) ;
				}
			}
			if (b) {
				b = ((bat.abs_dep < (N - (bat.taille - 1))) && (bat.abs_dep >= 0 )) ;
			}
			break ;
		case ('S') :
			for (i=0; i<bat.taille; i++) {
				if (b) {
					b = case_vide (m[bat.ord_dep + i][bat.abs_dep]) ;
				}
			}
			if (b) {
				b = ((bat.ord_dep < (N - (bat.taille - 1))) && (bat.ord_dep >= 0)) ;
			}
			break ;
		default :
			b = false ;
			printf ("Ce cas ne doit pas arriver!\n\n") ;
			break ;
	}
	return (b) ;
}
/* fonction booleenne qui verifie si la case est touché X ou jouer o plouf */

bool deja_touche (char c){
    return (c == TOUCHE || c == PLOUF);
}
