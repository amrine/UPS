/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/
#include <stdbool.h>
#include "constantes.h"

#ifndef DEF_VERIFICATIONS
#define DEF_VERIFICATIONS
/******************  V E R I F I C A T I O N S  *****************/

/*fonction qui teste si le caractere passe en parametre est vide*/

    bool case_vide (char c);

/*fonction booleenne qui verifie si les coordonnees appartiennent à la grille */

    bool contenu_ds_grille (int abs_dep,int ord_dep);

/*fonction booleenne qui verifie si le caractere passe en parametre est un bateau */

bool bateau (char c);


/* fonction booleenne qui teste que les bateaux ne se touchent pas */

bool espace_libre_autour (s_bateau bat, t_matrice m);
/*fonction booleenne qui verifie si le placement d'un bateau est correct*/

bool placement_correct (s_bateau bat, t_matrice m);
/* fonction booleenne qui verifie si la case est touché X ou jouer o plouf */

bool deja_touche (char c);

#endif
