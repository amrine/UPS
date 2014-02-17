#include <stdio.h>

#include <stdlib.h>  /*contient la constante EXIT_FAILURE*/

#include "pile.h"



/*masquage du type struct cell  */
struct cell
 { T_element   elt;
   struct cell * suivant; /*pointeur sur cellule suivante*/
 };



void initialiser_pile(T_pile *p)
{
   *p=NULL;
}

/*version imp�rative de l'affichage*/
void afficher_pile(T_pile p)
{
if (pile_vide(p))
    {
    printf("pile vide !\n");
    }
else
    {
    while(!pile_vide(p))
          {
          afficher_element(p->elt);
          p = p->suivant;
          }
    }
}

/*version r�cursive de l'affichage: permet ais�ment un affichage dans un sens ou
 dans l'autre */
void afficher_pile_rec(T_pile p)
{
if (!pile_vide(p))
      {
      afficher_pile(p->suivant);
      afficher_element(p->elt);
      /*permuter ces 2 lignes provoque un affichage dans l'autre sens */
      }
}




void saisir_pile(T_pile* p)
{
    int nb_elt;
    T_element element;
    int i;

    printf("Taper le nombre d'elements de la pile:");
    scanf("%d",&nb_elt);
    printf("%d",nb_elt);
    for (i=0; i<nb_elt; i=i+1)
    {
        printf("\n%d",i);
        saisir_element(&element);
        empiler(p,element);
    }
}



bool pile_vide(T_pile p)
{
    return (p==NULL);
}


bool pile_pleine(T_pile p)
{
    T_pile paux=(T_pile) malloc (sizeof(struct cell));
    bool res=(paux==NULL);
    free(paux);
    return (res);
}



void empiler(T_pile *p, T_element el)
{
if (pile_pleine(*p))
    {
        fprintf(stderr,"\nempiler : pile pleine !\n");
        exit(EXIT_FAILURE);
    }
else
    {
    /* Allocation du nouveau sommet de  pile */
    T_pile nouvelle;
    nouvelle = (T_pile)malloc(sizeof(struct cell));
    /* Initialisation du sommet de pile */
    affecter_element(&(nouvelle->elt),el);
    nouvelle->suivant = *p;
    /* Modification de la pile */
    *p = nouvelle;
    }
}




void depiler(T_pile *p)
{
if (pile_vide(*p))
    {
        fprintf(stderr,"\ndepiler: pile vide !\n");
        exit(EXIT_FAILURE);
    }
else
    {/* la pile n'est pas vide */
     T_pile s = *p;
     *p = (*p)->suivant;
     /* Ne pas oublier de lib�rer la m�moire. Regle absolue : � tout malloc son free!!! */
     free(s);
    }
}



T_element sommet_pile(T_pile p)
{
 if (pile_vide(p))
    {
        fprintf(stderr,"\nsommet_pile: pile vide !\n");
        exit(EXIT_FAILURE);
    }
else
    {
        return(p->elt);
    }
}
