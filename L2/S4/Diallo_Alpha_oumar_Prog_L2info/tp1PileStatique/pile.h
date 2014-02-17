#ifndef PILESTATIQUE#define PILESTATIQUE#include "elt_pile.h"#define TAILLEMAX 8/* ----------------------------------        Déclaration des types   --------------------------------*//* Déclaration du type d'une pile (sans masquage)*/

typedef T_element T_tab[TAILLEMAX];

typedef struct
 { T_tab  tab;
   int    tete; /*indice du sommet de la pile dans tab*/
 } T_pile;

/* ----------------------------------            Constructeurs   --------------------------------*//* Initialisation d'une pile  */void initialiser_pile(T_pile *p);/* Empiler */void empiler(T_pile *p, T_element el);/* Depiler */void depiler(T_pile *p);/* ----------------------------------            Accesseurs   --------------------------------*//*sommet de la pile   */T_element sommet_pile(T_pile p);/* affichage d'une pile */void afficher_pile(T_pile p);/* la pile est-elle vide ? */bool pile_vide(T_pile p);/*La pile est-elle pleine ? */bool pile_pleine(T_pile p);#endif