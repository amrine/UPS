/*26 11 2007Pile statique avec typage non masqu�*/#include <stdio.h>
#include <stdbool.h>
/* includes "application" */#include "elt_pile.h"#include "pile.h"
int main(){    /*T_element el1,el2;   */    T_pile p1;    /*    saisir_element(&el1);    saisir_element(&el2);    afficher_element(el1);    afficher_element(el2);    affecte(&el1,el2);    printf("\n");    afficher_element(el1);    */    initialiser_pile(&p1);    printf("\npv=%5dpp=%5d\t",pile_vide(p1),pile_pleine(p1));   /* printf("%d\n",sommet_pile(p1));*/    /*saisir_pile(&p1);*/    /*    afficher_pile(p1);    depiler(&p1);    empiler(&p1,54);    empiler(&p1,54);    empiler(&p1,28);    printf("\n%d ",sommet_pile(p1));afficher_pile(p1);    printf("\nok");    */ fprintf(stderr,"\nEssai d'ecriture sur stderr!\n");      empiler(&p1,2);      depiler(&p1);        empiler(&p1,2);        empiler(&p1,3);        printf("\npile:");   afficher_pile(p1);        printf("\nsommet:"); afficher_element(sommet_pile(p1));        depiler(&p1);        depiler(&p1);      /* printf("\nsommet:"); afficher_element(sommet_pile(p1));*/        printf("\n\n");    int i;    for (i=0;i<TAILLEMAX;i++)    {        empiler(&p1,2*i);        printf("\npile:");    afficher_pile(p1);        printf("\nsommet:");  afficher_element(sommet_pile(p1));        printf("\npv=%5dpp=%5d\t",pile_vide(p1),pile_pleine(p1));    }       empiler(&p1,54);       empiler(&p1,54);    printf("\nok");return(0);}