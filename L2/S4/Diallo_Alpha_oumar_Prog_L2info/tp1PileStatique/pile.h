#ifndef PILESTATIQUE

typedef T_element T_tab[TAILLEMAX];

typedef struct
 { T_tab  tab;
   int    tete; /*indice du sommet de la pile dans tab*/
 } T_pile;

/* ----------------------------------