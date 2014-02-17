#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "arbre.h"
/****************************************************************/
void initialiser_arbre(T_arbre * a)
/****************************************************************/
void afficher_arbre(T_arbre a) //affichage GRD
T_element supprimer_min_ite(T_arbre * a)
{

if (est_vide(*a))
    {
    fprintf(stderr,"\nsupprimer_ite : arbre vide !\n");
    exit(EXIT_FAILURE);
    }
T_element min;
T_arbre aux;
//aux est le noeud contenant le min : permet d'extraire min et est d�truit � la fin

if ((*a)->gauche==NULL) //alors le min est sur la racine
    {
    min=(*a)->racine;
    aux=*a;
    *a= (*a)->droit;
    }
else    //le min est au fond a gauche
    {
     T_arbre pere=*a;
     aux=pere->gauche;
     while ((pere->gauche)->gauche!=NULL)
        {
         pere=aux;
         aux=pere->gauche;
        }
     //aux contient le min
     min=aux->racine;
     if (aux->droit==NULL) pere->gauche=NULL; //aux est une feuille
     else  pere->gauche=aux->droit;     //aux a un sous arbre droit
    }
free(aux);
return min;
}