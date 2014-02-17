/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Amazones
*/


#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <stdbool.h>
#include "amazone.h"
#include "stack.h"

int main () {
  int action,action2;
  t_matrice m;
  int cpt;
  Stack p;
  initialiser_matrice_jeu(m);
  InitStack(&p);
  
  
  init_curses();
  Welcome();
  erase();
  action = displayMenuPrincipal();
  switch( action ) {
    case NEW:
	  erase();
	  action2 = displayS_MenuGame();
	   switch( action2 ) {
	      case PLAYER_1_Vs_PLAYER_2:
		  joueur1_vs_joueur2(m);
		  endwin();
	      break;
	      case PLAYER_Vs_IA:
		  cpt=0;
		  joueur_vs_ordi(m,p,cpt);
		  endwin();
	      break;
	      case EXIT:/*Quit the game*/
	      endwin();
	      exit(0);
	      break;
	   }
    break;
    case LOAD:
	  erase();
	  if ((load(m) != 1) || (StackLoad(&p) != 1) ||  (LoadHead(&cpt) != 1)) {
		printw("Aucune Sauvegarde Trouve\n");
		printw("Lancement d'un Nouveau Jeu\n");
		initialiser_matrice_jeu(m);
		InitStack(&p);
		cpt=0;
		refresh();
	  }
	  joueur_vs_ordi(m,p,cpt);
	  endwin();
    break;
    case EXIT:
    endwin();
    exit(0);
    break;
 }
 endwin();
 return 0;
}


