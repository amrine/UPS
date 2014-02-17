#ifndef ENSEMBLE
#define ENSEMBLE

#include "elt.h"
/* ----------------------------------
        D?claration des types
   --------------------------------*/

/* D?claration du type ensemble (avec masquage)*/

typedef struct set_cell * T_ensemble ; //le type struct set_cell est defini dans ensemble.c


/* ----------------------------------
            Constructeurs
   --------------------------------*/
/* Cr?ation d'un ensemble vide */
T_ensemble ensemble_initialise() ;

/* Ajout d'un element a un ensemble */
T_ensemble ajout (T_element elt, T_ensemble e) ;

/* ----------------------------------
           Transformateurs
   --------------------------------*/
T_ensemble reunion (T_ensemble e1, T_ensemble e2) ;

T_ensemble intersection (T_ensemble e1, T_ensemble e2) ;

T_ensemble difference (T_ensemble e1, T_ensemble e2) ;

T_ensemble supprimer (T_element elt, T_ensemble e) ;


/* ----------------------------------
            Accesseurs
   --------------------------------*/

bool est_vide (T_ensemble e) ;

bool est_inclus (T_ensemble e1, T_ensemble e2) ;

bool appartient (T_element e1t, T_ensemble e) ;

void afficher (T_ensemble e) ;

int cardinal (T_ensemble e) ;


T_element choisir (T_ensemble e) ;

#endif



