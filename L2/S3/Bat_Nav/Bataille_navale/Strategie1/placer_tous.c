/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include "constantes.h"
#include "placement.h"
#include "placer_tous.h"



/*fonction de placement de tous les bateaux manuellement*/

void placer_manuel (liste_bateau joueur,t_matrice m){
  int i;
  for (i=0;i<=10;i++){
	if (i<1) placement_manuel (&joueur.tabbateau[i], m,PORTEAVION);
	else if (i>1 && i<4) placement_manuel (&joueur.tabbateau[i],m,CROISEUR) ;
	else if (i>3 && i<7) placement_manuel (&joueur.tabbateau[i], m,DESTROYER) ;
	else if (i>6 && i<=10) placement_manuel (&joueur.tabbateau[i], m,VEDETTE) ;
  }
}

/*fonction de placement de tous les bateaux aleatoirement*/

void placer_aleatoire (liste_bateau joueur,t_matrice m){
  int i;
  for (i=0;i<=10;i++){
	if (i<1) placement_aleatoire (&joueur.tabbateau[i], m,PORTEAVION);
	else if (i>1 && i<4) placement_aleatoire (&joueur.tabbateau[i],m,CROISEUR) ;
	else if (i>3 && i<7) placement_aleatoire (&joueur.tabbateau[i], m,DESTROYER) ;
	else if (i>6 && i<=10) placement_aleatoire (&joueur.tabbateau[i], m,VEDETTE) ;
  }
}
