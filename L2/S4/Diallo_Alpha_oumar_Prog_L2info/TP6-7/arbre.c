#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "arbre.h"#include "elt.h"   //le type element doit être ordonné (int par exemple)/****************************************************************//*type masqué */struct node_cell    {     int racine ;     struct node_cell * droit ;     struct node_cell * gauche ;    };
/****************************************************************/
void initialiser_arbre(T_arbre * a){*a=NULL;}
/****************************************************************/bool est_vide(T_arbre a){return (a==NULL) ;}/****************************************************************/
void afficher_arbre(T_arbre a) //affichage GRD{if (!(est_vide(a)))    {     afficher_arbre(a->gauche);     afficher_element(a->racine);     afficher_arbre(a->droit);    }}/****************************************************************/void creer_noeud(T_arbre * a, T_element el){ *a=(T_arbre) malloc(sizeof(struct node_cell)); if (*a==NULL)        {        fprintf(stderr,"\ncreer_noeud : tas plein !\n");        exit(EXIT_FAILURE);        } affecter_element(&((*a)->racine), el); (*a)->gauche=NULL; (*a)->droit=NULL;}/****************************************************************/void ajouter_rec(T_arbre * a, T_element el){  if (est_vide(*a))        creer_noeud(a,el);  else    {    if ((*a)->racine > el)        ajouter_rec(&((*a)->gauche), el);    else        ajouter_rec(&((*a)->droit), el);    }}/****************************************************************/void ajouter_ite(T_arbre * a , T_element el){T_arbre * b=a;//recherche de la feuille ou placer elwhile (!(est_vide(*b)))    {    if ((*b)->racine > el)         b=&((*b)->gauche);    else b=&((*b)->droit);    }//creation d'un noeud pour elcreer_noeud(b, el);}/****************************************************************/bool appartient_rec(T_arbre a , T_element el){bool trouve=false;if (est_vide(a)) trouve = false;else if (a->racine > el) trouve = appartient_rec(a->gauche, el);     else if (a->racine < el) trouve = appartient_rec(a->droit, el);          else trouve=true;return trouve;}/****************************************************************/bool appartient_ite(T_arbre a , T_element el){bool trouve=false;bool fini=false;while (!fini)    {     if (a->racine==el)        {         trouve=true;         fini=true;        }     else if (a->racine > el)                if (!est_vide(a->gauche)) a=a->gauche; else fini=true;          else  if (!est_vide(a->droit))  a=a->droit; else fini=true;    }return trouve;}/****************************************************************/
T_element supprimer_min_ite(T_arbre * a)
{

if (est_vide(*a))
    {
    fprintf(stderr,"\nsupprimer_ite : arbre vide !\n");
    exit(EXIT_FAILURE);
    }
T_element min;
T_arbre aux;
//aux est le noeud contenant le min : permet d'extraire min et est détruit à la fin

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
}/***************************************************************/
