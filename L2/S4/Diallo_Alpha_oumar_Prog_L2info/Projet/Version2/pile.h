#ifndef PILEDYNAMIQUE
#define PILEDYNAMIQUE

#include <stdbool.h>
#include <time.h>
#include "amazone.h"



/* ----------------------------------
        D�claration des types
   --------------------------------*/

/* D�claration du type d'une pile (avec masquage)*/




typedef struct cellule* T_pile;

/* ----------------------------------
            Constructeurs
   --------------------------------*/

/* Initialisation d'une pile  */
void initialiser_pile(T_pile *p);


/* Empiler */
void empiler(T_pile *p, coor_amazones position, coor_amazones depart, int nb_coups);

bool pile_vide(T_pile p);

void afficher_pile(T_pile p);


int coups_possibles(t_matrice t ,coor_amazones coor, T_pile *p,int nb_coups);
/*
void afficher_pile(T_pile p);
*//*
In : une matrice
Action : On parcours la matrice pour reperer les pions de l’ordinateur et à chaque rencontre de ces
pions, on va cherche tous les coups possibles que peut effectuer ce pion et on les stockera dans une
structure cellule.
Out : une liste contenant les coordonnees des coups possibles
*/

int determiner_coups (t_matrice t, T_pile *p, char couleur);


void get_coor(T_pile p, int i, coor_amazones *dep, coor_amazones *fin);
/*
In : une liste de coordonnees, un entier representant le numero de la coordonnees a recuperer
Action : on parcours la liste contenant les coordonnees et on recupere la coordonnee du numero
entrer en paramètre
Out : Aucune
*/




/* ----------------------------------
            Accesseurs
   --------------------------------*/



/* affichage d'une pile */
void afficher_pile(T_pile p);


#endif




