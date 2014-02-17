#ifndef ELEMENTPILE
#define ELEMENTPILE

/* ----------------------------------
        Déclaration des types
   --------------------------------*/
/* Déclaration du type des éléments d'une pile */
typedef int T_element;

/* ----------------------------------
            Constructeurs
   --------------------------------*/
/* Saisie d'un element */
void saisir_element(T_element *el);

/* Copie d'un element */
void affecter_element(T_element *vers, T_element de);

/* ----------------------------------
            Accesseurs
   --------------------------------*/
/* affichage d'un element */
void afficher_element(T_element el);


#endif
