/* Pierre Piccinini 05/04
 Tours de Hanoi : version imperative
*/
#include <stdio.h>
#include <stdbool.h>
#include "pile.h"


typedef T_pile T_jeu[3];

/*--------------------------------------------------------------------*/
/*creation d'un jeu avec N anneaux */
void initialiser_jeu(int N,T_jeu tours)
{
int i;
for(i=0;i<3;i=i+1) initialiser_pile(&tours[i]);
for(i=0;i<N;i=i+1) empiler(&tours[0],N-i);
}
/*--------------------------------------------------------------------*/
void afficher_jeu(T_jeu tours)
{
int i;
printf("\n");
for(i=0;i<3;i=i+1)  {printf ("\nTour %d:",i+1);
                     afficher_pile(tours[i]);
                    }
printf("\n");
}
/*--------------------------------------------------------------------*/
/*renvoie l'indice de la tour suivante (de la gauche vers la droite)  */
int plus_un (int p)
{
return ((p+1) % 3);
}
/*--------------------------------------------------------------------*/
/*deplacement d'un anneau de la tour d'indice depart vers la tour
d'indice arrivee sans controle de diametre d'anneau */
void deplacer_anneau(int depart, int arrivee, T_jeu tours)
{
printf("\n%d --> %d\n",depart,arrivee);
empiler(&tours[arrivee],sommet_pile(tours[depart]));
depiler(&tours[depart]);
}
/*--------------------------------------------------------------------*/
/*renvoie un booleen vrai s'il est possible de deplacer un anneau de
la tour d'indice depart vers la tour d'indice arrivee */
bool deplacement_possible(int depart, int arrivee, T_jeu tours)
{
bool possible=true;

if ( pile_vide(tours[depart])==true)
  {possible=false;}
else
  {
   if (pile_vide(tours[arrivee])==false)
    {if (sommet_pile(tours[depart]) > sommet_pile(tours[arrivee]) )
     {possible=false;}
    }
  }
return possible;
}
/*--------------------------------------------------------------------*/

int main(void)
{
T_jeu tours;        /*le jeu de Hanoi constitue des 3 tours*/
int N;              /*nombre d'anneaux du jeu */
int pa;             /*indice de la pique contenant le petit anneau*/
int p1,p2;          /*indice des deux autres tours: p2 suit p1 qui suit pa*/
int nb_dep;         /*nombre de deplacements*/

printf("Combien voulez-vous d'anneaux ? (< %d):",10);
scanf("%d",&N);
initialiser_jeu(N,tours);
afficher_jeu(tours);
pa=0;   /*le petit anneau est sur la tour de gauche */
nb_dep=0;     /*il n'y a eu encore aucun deplacement */

/*Tant qu'il y a au moins un anneau sur une des 2 tours de gauche*/
while ( !(pile_vide(tours[0])) || !(pile_vide(tours[1])) )
  {
  /*deplacer le petit anneau*/
  deplacer_anneau(pa,plus_un(pa),tours);
  /*actualiser le nombre de deplacements*/
  nb_dep=nb_dep+1;
  afficher_jeu(tours);
  /*actualiser l'indice de la tour contenant le petit anneau */
  pa=plus_un(pa);
  p1=plus_un(pa);
  p2=plus_un(p1);
  /*si le deplacement d'un  autre anneau est possible:
     de la tour p1 vers la tour p2         */
  if (deplacement_possible(p1,p2,tours) )
         {deplacer_anneau(p1,p2,tours);
          nb_dep=nb_dep+1;
         }
  /* ou sinon de la tour p2 vers la tour p1 */
  else if (deplacement_possible(p2,p1,tours) )
         {deplacer_anneau(p2,p1,tours);
          nb_dep=nb_dep+1;
         }
  afficher_jeu(tours);
  }


printf("\n\nLe nombre de deplacements est :%d\n",nb_dep);
return 0;
}
