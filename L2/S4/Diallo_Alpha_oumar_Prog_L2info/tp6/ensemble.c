#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#include "ensemble.h"


/*Definition du type struct set_cell masqu?  */

struct set_cell
 { T_element        valeur ;
   struct set_cell*  suiv ;
 };




/****************************************************************
            Constructeurs
 ****************************************************************/
/* Cr?ation d'un ensemble vide */
T_ensemble ensemble_initialise()
{
return (NULL);
}
/****************************************************************/
/* Ajout d'un element a un ensemble */
T_ensemble ajout (T_element elt, T_ensemble e)
{
T_ensemble eaux=e ;
if (! appartient(elt,e))
    {
    eaux = (T_ensemble) malloc (sizeof(struct set_cell)) ;
    if (eaux==NULL)
        {
         fprintf(stderr,"ajout : ensemble plein !");
         exit(1);
        }
    eaux->valeur = elt ;
    eaux->suiv = e ;
    }
return (eaux);
}
/****************************************************************
           Transformateurs
 ****************************************************************/

T_ensemble reunion (T_ensemble e1, T_ensemble e2)

{
T_ensemble eaux = ensemble_initialise();
//On ajoute tous les ?l?ments de e1
while (! est_vide(e1))
    {
    eaux = ajout(e1->valeur, eaux);
    e1 = e1->suiv;
    }
//puis ceux de e2
while (! est_vide(e2))
    {
    eaux = ajout(e2->valeur, eaux);
    e2 = e2->suiv;
    }
return (eaux);
}
/****************************************************************/
T_ensemble intersection (T_ensemble e1, T_ensemble e2)
{
T_ensemble eaux = ensemble_initialise() ;
while (! est_vide(e1))
{
if (appartient(e1->valeur,e2)) eaux = ajout (e1->valeur, eaux);
e1 = e1->suiv ;
}
return (eaux) ;
}
/****************************************************************/
//Calcul de e1-e2
T_ensemble difference (T_ensemble e1, T_ensemble e2)
{
T_ensemble eaux = ensemble_initialise() ;
while (! est_vide(e1))
    {
    if (! appartient (e1->valeur,e2))
    eaux = ajout (e1->valeur,eaux) ;
    e1 = e1->suiv ;
    }
return(eaux);
}
/****************************************************************/
T_ensemble supprimer (T_element elt, T_ensemble e)
/*Selon que l'element a supprimer soit ou non sur la premiere cellule,
 le traitement est different
*/
{
T_ensemble eaux=e; //ensemble auxilliaire a retourner en fin de fonction
if (appartient (elt,e))
    {
    if (elements_egaux(e->valeur,elt)) eaux=e->suiv;  //elt est sur la premi?re cellule
    else //sinon on se d?place sur la cellule contenant elt
        {
         T_ensemble eaux1;
         while (!elements_egaux(e->valeur,elt))
            {
            eaux1 = e ;
            e = e->suiv ;
            }
        /*eaux1 pointe vers e qui est la cellule contenant elt */
        eaux1->suiv = e->suiv ; //on isole la cellule a supprimer
        }
    free(e); //destruction de la cellule isolee
    }
return eaux ;
}


/*autre version:
{
T_ensemble eaux=e;
if (appartient (elt,e))
    {
    T_ensemble eaux1 = ensemble_initialise() ;
    while (!elements_egaux(e->valeur, elt))
        {
        eaux1 = e ;
        e = e->suiv ;
        }
    if (est_vide (eaux1))
        eaux = e->suiv ;
    else
        eaux1->suiv = e->suiv ;
    free(e);
    }
return eaux ;
}
*/

/****************************************************************
           Accesseurs
 ****************************************************************/
bool est_vide (T_ensemble e)
{
return (e==NULL);
}
/****************************************************************/
bool est_inclus (T_ensemble e1, T_ensemble e2)
{
bool ok=1 ;
while ((! est_vide(e1)) && (ok))
    {
    if (! appartient (e1->valeur, e2)) ok = 0 ;
    else e1 = e1->suiv ;
    }
return ok ;
}
/****************************************************************/
bool appartient (T_element elt, T_ensemble e)
{
bool trouve = false ;
while((!est_vide(e)) && (! trouve))
    {
    if (elements_egaux(e->valeur,elt)) trouve = true ;
    else e = e->suiv ;
    }
return trouve ;
}
/****************************************************************/
void afficher (T_ensemble e)
{
while (!est_vide(e))
    {
    afficher_element(e->valeur) ;
    e = e->suiv ;
    }
printf("\n");
fflush(stdout);
}
/****************************************************************/
int cardinal (T_ensemble e)
{
int cpt=0 ;
while (!est_vide(e))
    {
    cpt ++;
    e = e->suiv;
    }
return cpt ;
}
/*****************************************************************/
T_element min_extrait (T_ensemble e)
// fonction n?cessaire pour la partie application (eratosthene)
// ne fonctionne qu'avec un type ?l?ment ordonn?
{
if (est_vide(e))
    {
    fprintf(stderr,"\n min_extrait: ensemble vide !\n");
    exit(EXIT_FAILURE);
    }
else
    {
    T_element min ;
    min = e->valeur ;
    e= e->suiv ;
    while(! est_vide(e))
        {
        if (min > e->valeur) min = e->valeur ;
        e= e->suiv ;
        }
    return min ;
    }
}
/****************************************************************/
T_element choisir (T_ensemble e)
{
if (est_vide(e))
    {
    fprintf(stderr,"\n min_extrait: ensemble vide !\n");
    exit(EXIT_FAILURE);
    }
return (e->valeur);
}
/****************************************************************/
