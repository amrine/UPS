#ifndef ARBRE
#define ARBRE

#include <stdio.h>
#include "elt.h"

/**********************************************************************/
//Le type struct arbre est masqu� dans le fichier arbre.c
typedef struct node_cell * T_arbre ;

/**********************************************************************/

void initialiser_arbre(T_arbre * a);

bool est_vide(T_arbre a);

void afficher_arbre(T_arbre a);

void creer_noeud(T_arbre * a, T_element el);

void ajouter_rec(T_arbre * a, T_element el);

void ajouter_ite(T_arbre * a, T_element el);

bool appartient_rec(T_arbre a , T_element el);

T_element supprimer_min_ite(T_arbre * a);


#endif