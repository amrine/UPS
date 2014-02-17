#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#include "amazone.h"
#include "pile.h"
#include <ncurses.h>


    
    
int determiner_coups (t_matrice t, T_pile *p, char couleur)
{
  int i;
  int j;
  int nb_coups=0;
  coor_amazones coor;
  
  for (i=0 ; i< N ; i++)
      {
	  for (j=0 ; j< N ; j++)
	  {
	      if (t[i][j]==couleur)
	      {
		coor.abs=i;
		coor.ord=j;
		nb_coups = coups_possibles(t,coor,p,nb_coups);
	      }
	  }
      }
   return(nb_coups); 
}


int coups_possibles(t_matrice t ,coor_amazones coor, T_pile *p, int nb_coups)
{
      int i=1;
      coor_amazones tmp;
        
       
      while(case_vide(t[coor.abs+i][coor.ord]))		/* En bas */
      {
	tmp.abs=coor.abs+i;
	tmp.ord=coor.ord;
	if (contenu_ds_grille(tmp))
	{
	  nb_coups++;
	  empiler(p,tmp,coor,nb_coups);
	}
	 i++;
      }
      i=1;
      while(case_vide(t[coor.abs-i][coor.ord]))		/* En haut */
      {	
	tmp.abs=coor.abs-i;
	tmp.ord=coor.ord;
	if (contenu_ds_grille(tmp))
	{
	nb_coups++;
	empiler(p,tmp,coor,nb_coups);
	}	
	 i++;
      }
      i=1;
      while(case_vide(t[coor.abs][coor.ord+i]))		/* A droite */
      {
	tmp.abs=coor.abs;
	tmp.ord=coor.ord+i;
	if (contenu_ds_grille(tmp))
	{
	nb_coups++;
	empiler(p,tmp,coor,nb_coups);
	}
	i++;
      }
      i=1;
       while(case_vide(t[coor.abs][coor.ord-i]))		/* A gauche */
      {
	tmp.abs=coor.abs;
	tmp.ord=coor.ord-i;
	if (contenu_ds_grille(tmp))
	{
	nb_coups++;
	empiler(p,tmp,coor,nb_coups);
	}
	i++;
      }
      i=1;
        while(case_vide(t[coor.abs+i][coor.ord+i]))	/* En bas a droite */
      {
	tmp.abs=coor.abs+i;
	tmp.ord=coor.ord+i;
	if (contenu_ds_grille(tmp))
	{
	nb_coups++;
	empiler(p,tmp,coor,nb_coups);
	}
	i++;
      }
      i=1;
        while(case_vide(t[coor.abs+i][coor.ord-i]))	/* En bas a gauche */
      {
	tmp.abs=coor.abs+i;
	tmp.ord=coor.ord-i;
	if (contenu_ds_grille(tmp))
	{
	nb_coups++;
	empiler(p,tmp,coor,nb_coups);
	}
	i++;
      }
      i=1;
        while(case_vide(t[coor.abs-i][coor.ord+i]))	/* En haut a droite */
      {
	tmp.abs=coor.abs-i;
	tmp.ord=coor.ord+i;
	if (contenu_ds_grille(tmp))
	{
	nb_coups++;
	empiler(p,tmp,coor,nb_coups);
	}
	i++;
      }
      i=1;
        while(case_vide(t[coor.abs-i][coor.ord-i]))	/* En haut a gauche */
      {
	tmp.abs=coor.abs-i;
	tmp.ord=coor.ord-i;
	if (contenu_ds_grille(tmp))
	{
	nb_coups++;
	empiler(p,tmp,coor,nb_coups);
	}
	i++;
      }
      i=1; 
      return(nb_coups);
}


void get_coor(T_pile p, int i, coor_amazones *dep, coor_amazones *fin)		/* Choisi au hazard un couple de coordonnees de la pile */
{
  int choix_ordi;
  
  
  choix_ordi=1+ rand()%(i); 
 
	while (choix_ordi != p->nbres_coups)
	{
	p = p->next;
	}
	*dep = p->dep;
	*fin = p->coor;
  
}
  
  
  
  
  
  
  


		  
    
    

