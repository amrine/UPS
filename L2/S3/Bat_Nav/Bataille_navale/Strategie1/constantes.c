/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>




/**************  D E F I N I T I O N   D E S   V A R I A B L E S  *****************/

/*Taille de la carte*/
#define N 10

/*Pour la carte des placements*/
#define VIDE ' '
#define PORTEAVION 'P'
#define CROISEUR 'C'
#define DESTROYER 'D'
#define VEDETTE 'V'

/*Nombre de bateaux de chaque catégorie*/
#define NB_P 1           /*nb de porte-avions*/
#define NB_C 2           /*nb de croiseurs*/
#define NB_D 3           /*nb de destroyers*/
#define NB_V 4           /*nb de vedettes*/

/*Pour la carte d'attaques*/
#define TOUCHE 'X'
#define PLOUF 'O'

typedef char t_matrice[N][N] ;

typedef struct {
	int ord_dep ;
	int abs_dep ;
	char orientation ;
	int taille ;
	char nom ;
} s_bateau ;

typedef struct {
	int nbre_bateau;
	s_bateau tabbateau[NB_P + NB_C + NB_D + NB_V+1];
}liste_bateau;
