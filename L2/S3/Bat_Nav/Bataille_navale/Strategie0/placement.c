/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "constantes.h"
#include "verifications.h"
#include "utilitaire.h"
#include "affichage_init.h"
#include "placement.h"

/*************  P L A C E M E N T   M A N U E L  **************/

void placement_manuel (s_bateau* bat, t_matrice m, char type) {
	int i, o;
	char a,abs;
	bat->nom=type;
	switch (bat->nom) {
		case PORTEAVION :
			bat->taille = 4 ;
			break ;
		case CROISEUR :
			bat->taille = 3 ;
			break ;
		case DESTROYER :
			bat->taille = 2 ;
			break ;
		case VEDETTE :
			bat->taille = 1 ;
			break ;
		default:
			printf ("Ce type de bateau n'existe pas !\n\n") ;
			break ;
	}
	do {
		printf ("Entrer les coordonnees du bateau (LETTRE chiffre)\n") ;
		printf ("et son orientation (chiffre 1=haut 2=droite 3=gauche 4=bas) :\n\n") ;
		scanf ("\n%c", &abs) ;
		scanf ("%d", &bat->ord_dep) ;
		scanf ("%d", &o) ;
		bat->orientation = orientation (o) ;
		if ((abs >= 'a') && (abs <'a' + N)) {    /*change les minuscules en majuscules*/
				abs = abs + 'A' - 'a' ;
		}
		i=0 ;
		for (a='A' ; a<abs ; a++) {
			i++ ;
		}
		bat->abs_dep = i ;
		bat->ord_dep-- ;
		switch (bat->orientation) {
			case ('O') :
				bat->abs_dep = bat->abs_dep - (bat->taille-1) ;
				bat->orientation = 'E' ;
				break ;
			case ('N') :
				bat->ord_dep = bat->ord_dep - (bat->taille-1) ;
				bat->orientation = 'S' ;
				break ;
		}
		if (! placement_correct(*bat, m)) {
			printf("Placement Incorrect !\nVeuillez recommencer :\n\n") ;
		}
	} while (! placement_correct(*bat, m)) ;
	switch (bat->orientation) {
		case 'E' :
			for (i=0; i<bat->taille; i++) {
				m[bat->ord_dep][bat->abs_dep+i] = bat->nom ;
			}
			break ;
		case 'S' :
			for (i=0; i<bat->taille; i++) {
				m[bat->ord_dep+i][bat->abs_dep] = bat->nom ;
			}
			break ;
		default :
			printf ("Ce cas ne doit pas arriver!\n\n") ;
			break ;
	}
	afficher_matrice (m) ;
}

/*************  P L A C E M E N T   A L E A T O I R E  **************/

void placement_aleatoire (s_bateau* bat, t_matrice m,char type) {
    int i, o ;
    bat->nom=type;
    switch (bat->nom){
        case PORTEAVION :
                bat->taille=4;
                break;
        case CROISEUR :
                bat->taille=3;
                break;
        case DESTROYER :
                bat->taille=2;
                break;
        case VEDETTE :
                bat->taille=1;
                break;
        default: printf("Ce type de bateau n'existe pas !\n");
                break;
    }
    do {
	bat->abs_dep = rand() % N ;
	bat->ord_dep = rand() % N ;
	o = (rand() % 4) + 1 ;
	bat->orientation = orientation (o) ;
	switch (bat->orientation) {
	    case ('O') :
		bat->abs_dep = bat->abs_dep - (bat->taille-1) ;
		bat->orientation = 'E' ;
		break ;
	    case ('N') :
		bat->ord_dep = bat->ord_dep - (bat->taille-1) ;
		bat->orientation = 'S' ;
		break ;
	}
    } while (! placement_correct(*bat, m)) ;
    switch (bat->orientation) {
	case 'E' :
	    for (i=0; i<bat->taille; i++) {
		m[bat->ord_dep][bat->abs_dep+i] = bat->nom ;
	    }
	    break ;
	case 'S' :
	    for (i=0; i<bat->taille; i++) {
		m[bat->ord_dep+i][bat->abs_dep] = bat->nom ;
	    }
	    break ;
	default :
	    printf ("Ce cas ne doit pas arriver!\n") ;
	    break ;
    }
}
