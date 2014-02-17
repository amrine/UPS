/*Auteur : Diallo Alpha Oumar Binta - Yechan Jang
Annee scolaire : 2010-2011
L2 informatique
Projet Amazones
*/

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "amazone.h"
#include "pile.h"


/*	fonction contenu_ds_grille
	PE : coor_amazones
	PS : vrai si les coordonnees sont contenu dans la grille faux sinon
*/

bool contenu_ds_grille (coor_amazones coor){
  return ((coor.abs >=0 && coor.abs< N) && (coor.ord >=0 && coor.ord < N));
}

/* fonction case_touche
	PE : coor_amazones,char
	PS : vrai si la caractere est egal a touche, faux sinon
	*/

/* fonction cas_vide
	PE : char
	PS : vrai si le caractere egal a vide
	*/

bool case_vide (char c) {
	return (c == VIDE) ;
}


/* fonction deplacement_possible
  PE : coor_amamzones,coor_amazones,t_matrice
  PS : vrai si le deplacement est possible faux sinon
  */

bool deplacement_possible(coor_amazones dep, coor_amazones arriv, t_matrice m){
  int i=1,dist;
  bool b;
  /* pour que le deplacement soit possible, il faut que les coordonnees
	de depart et d'arrivee soit differente et qu'elle soit contenu
	dans la grille */
  if (contenu_ds_grille(dep) && contenu_ds_grille (arriv)){
	/* deplacement horizontale */
	if (dep.ord == arriv.ord){
	  if (dep.abs < arriv.abs){
		/* deplacement vers la droite */
		dist= arriv.abs - dep.abs;
		while(i<= dist){
		  b=case_vide (m[dep.ord][dep.abs+i]);
		  if (b) i++;
		  else i=dist+1;
		}
	  }
	  else if (dep.abs > arriv.abs){
		/* deplacement vers la gauche */
		dist= dep.abs - arriv.abs;
		while(i<= dist){
		  b=case_vide (m[dep.ord][dep.abs-i]);
		  if (b) i++;
		  else i=dist+1;
		}
	  }
	}
	/* deplacement verticale */
	else if (dep.abs == arriv.abs){
	  if (dep.ord < arriv.ord){
		/* deplacement vers le bas */
		dist= arriv.ord - dep.ord;
		while(i<= dist){
		  b=case_vide (m[dep.ord+i][dep.abs]);
		  if (b) i++;
		  else i=dist+1;
		}
	  }
	  else if (dep.ord > arriv.ord){
		/* deplacement vers le haut */
		dist= dep.ord - arriv.ord;
		while(i<= dist){
		 b=case_vide (m[dep.ord-i][dep.abs]);
		 if (b) i++;
		  else i=dist+1;
		}
	  }
	}
	/* cas de la diagonale */
	else  if (abs(dep.abs - arriv.abs) == abs(dep.ord - arriv.ord)){
  	/* diagonale vers le bas a droite */
   	if ((dep.ord < arriv.ord) && (dep.abs < arriv.abs)){
		dist= arriv.ord - dep.ord;
		while(i<= dist){
		  b=case_vide (m[dep.ord+i][dep.abs+i]);
		  if (b)i++;
		  else i=dist+1;
		
	  	}
  	}
 	 /* diagonale vers le haut a droite */
 	else if ((dep.ord > arriv.ord) && (dep.abs < arriv.abs)){
		dist= dep.ord - arriv.ord;
		while(i<= dist){
		  b=case_vide (m[dep.ord-i][dep.abs+i]);
		  if (b) i++;
		  else i=dist+1;
		
	  	}
  	} 
  	/* diagonale vers le haut a gauche */
 	else if ((dep.ord > arriv.ord) && (dep.abs > arriv.abs)){
		dist= dep.ord - arriv.ord;
		while(i<= dist){
		  b=case_vide (m[dep.ord-i][dep.abs-i]);
		  if (b) i++;
		  else i=dist+1;
		
	  	}
  	}
 	 /* diagonale vers le bas a gauche */
 	else if ((dep.ord < arriv.ord) && (dep.abs > arriv.abs)){
		dist= arriv.ord - dep.ord;
		while(i<= dist){
		  b=case_vide (m[dep.ord+i][dep.abs-i]);
		  if (b) i++;
		  else i=dist+1;
		
	  	}
  	}
  	else b=false;
  }
  else b=false;
  }
  else b=false;
  return (b);
}



bool fin_du_jeu (t_matrice m) {			/* *********************A voir par ALPHA ********************/
  bool b=false;
  int i,j;
  int cpt_noir=0;
  int cpt_blanc=0;
  T_pile p;

  for (i=0 ; i<10 ; i++)
  {
	 for (j=0 ; j<10 ; j++)
	 {
	      if (m[i][j] == AMAZONE_BLANCHE)
	      {
		  cpt_blanc = determiner_coups(m, &p, AMAZONE_BLANCHE);
	      }
	      if (m[i][j] == AMAZONE_NOIR) 
	      {
		     cpt_noir = determiner_coups (m, &p, AMAZONE_NOIR);
	      }
	 }
  }
  
  if (cpt_blanc == 0)
  {
    printf(" LES AMAZONES NOIRS GAGNENT LA PARTIE \n\n\n");
    b = true;
  }
  if (cpt_noir == 0)	
  {
    printf(" LES AMAZONES BLANCS GAGNENT LA PARTIE \n\n\n");
    b = true;
  }
  return(b);
}