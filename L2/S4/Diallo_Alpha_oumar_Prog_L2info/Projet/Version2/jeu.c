/*Auteur : Diallo Alpha Oumar Binta - Yechan Jang
Annee scolaire : 2010-2011
L2 informatique
Projet Amazones
*/

#include <stdlib.h>
#include <stdio.h>
#include "amazone.h"
#include <ncurses.h>
#include "pile.h"
#include "stack.h"

/* In : un entier
	 Action : retourne le type d'amazone possede par le joueur
	 Out : un caractere
	 */
char couleur_joueur (int joueur) {
	if (joueur == 1) return AMAZONE_BLANCHE; /*joueur 1 */
	else return AMAZONE_NOIR; /*joueur 2*/
}

/* In : un entier
	Action : effectue la succession des tours de jeu
	Out: un entier
	*/
void joueur_suivant (int *joueur) {
	*joueur = *joueur % 2 + 1;
}

/*
In : une matrice
Action : le joueur choisi l’amazone a deplacer tout en verifiant que l’amazone
l’appartient. Ensuite
le joueur entre les coordonnees d’arrive. Enfin a partir de la position
d’arrivee, le joueur entre
les coordonnees d’arrive de la fleche. (On fera en sorte que les coordonnees
entrees verifient la
condition de « deplacement_possible »).
Out : Aucune
*/


void action_homme (t_matrice m, int joueur,int mode,Stack *p,int cpt) {
	coor_amazones deb, fin;
	coor_amazones arriv;
	
	erase();
	afficher_entete(mode);
	afficher_matrice(m);
	printw ("\n\n*****************************\n");
	printw ("* C'est au tour du joueur %d *\n", joueur);
	printw ("*****************************\n\n\n");
	refresh();
	/* Selection de l'Amazone a deplace */
	do {
		saisir_coor(&deb,"coor de depart de l'Amazone");
		saisir_coor(&fin,"coor d'arrivee de l'Amazone");
		if (!deplacement_possible(deb,fin,m)){ 
		  erase();
		  afficher_entete(mode);
		  afficher_matrice(m);
		  mvprintw (17,0,"\nDeplacement incorrect \n Veuillez recommencer!\n");	 refresh();
		}
		if ((m[deb.ord][deb.abs]) != couleur_joueur(joueur)){ 
		  erase();
		  afficher_entete(mode);
		  afficher_matrice(m);
		  mvprintw (17,0,"\nCeci n'est pas votre amazone \n Veuillez recommencer!\n"); refresh();
		}
		if ((deb.abs == fin.abs) && (deb.ord == fin.ord)){ 
		  erase();
		  afficher_entete(mode);
		  afficher_matrice(m);
		  mvprintw (18,0,"\nVous avez saisi les memes coordonnees\n"); refresh();
		}
	} while (!deplacement_possible(deb,fin,m) || (m[deb.ord][deb.abs]) != couleur_joueur(joueur) || ((deb.abs == fin.abs) && (deb.ord == fin.ord)));
	
	/* On deplace le pion selectionne a sa nouvelle case */
	m[fin.ord][fin.abs] = couleur_joueur(joueur);
	m[deb.ord][deb.abs] = VIDE;
	cpt++;
	/* On affiche les cases du deplacement */
	erase();
	afficher_entete(mode);
	afficher_matrice(m);
	mvprintw (18,0,"\n	Il deplace %c%d en %c%d\n", 'A' + deb.abs, deb.ord + 1, 'A' + fin.abs, fin.ord + 1); refresh();
	refresh();
	
	
	/* Tirage de la fleche */
	arriv =fin; /* les coordonnes d'arrivee de l'Amazone sont les coordonnees de depart de la fleche */
	do {
		saisir_coor(&fin,"coor d'arrivee de la fleche");
		if (!deplacement_possible(arriv,fin,m)){
		  erase();
		  afficher_entete(mode);
		  afficher_matrice(m);
		  mvprintw (17,0,"\nDeplacement incorrect \n Veuillez recommencer!\n"); refresh();
		}
	} while (!deplacement_possible(arriv,fin,m));
	
	/* placement du barrage et affichage de la matrice */
	m[fin.ord][fin.abs] = FLECHE;
	StackPush(p,cpt, deb, arriv, fin);
	erase();
	afficher_entete(mode);
	afficher_matrice(m);
	mvprintw (18,0,"\n	Il place un barrage en %c%d\n", 'A' + fin.abs, fin.ord + 1);
	refresh();
	
}


void joueur1_vs_joueur2(t_matrice m){
	int joueur=1,cpt=0;
	Stack p;
	
	while (!fin_du_jeu (m)){
		action_homme(m,joueur,2,&p,cpt);
		joueur_suivant(&joueur);
	} 
}

void ordi_facile(t_matrice m)
{
	coor_amazones dep;
	coor_amazones coor;
	T_pile p;
	int i;
	
	initialiser_pile(&p);
	/* La machine determine tous les coups possibles */
	i = determiner_coups (m, &p, AMAZONE_NOIR); 	
	/* La machine choisie un coup parmis les multiples coups */
	if (pile_vide(p))
	{
		printw("  \n\n FIN DU JEU \n\n");refresh();
	}else {  
	
	get_coor(p,i,&dep,&coor);
	
	
	 /* Deplacement du pion */
	m[coor.abs][coor.ord] = AMAZONE_NOIR;
	m[dep.abs][dep.ord] = VIDE;
	/* L'arrive de l'amazone noir devient le depart de la fleche */
	dep = coor;
	/* On initialiser la pile */
	initialiser_pile(&p);	
	/* la machine determine les coups possibles */
	i = 0;
	i = coups_possibles(m, coor , &p,i);
	/* Le nombre de coups possible */
	/* La machine choisi au hazard les coordonnees */
	 get_coor(p, i, &dep , &coor);
	 m[coor.abs][coor.ord] = FLECHE;
	erase();
	afficher_entete(1);
	afficher_matrice(m);
	refresh();
	}
}
	 
	
	

void joueur_vs_ordi(t_matrice m,Stack p,int cpt){
    int joueur = 1,key,coup,i;
    do{
      action_homme(m,joueur,1,&p,cpt); 
      cpt++;
      ordi_facile(m);
      save(m);
      SaveStack(p);
      SaveHead(StackHead(p));
      key=getch();
      switch (key){
	case 'c':
	case 'C':
	  coup = userInput("Entrer le Numero du coups");
	  back_in_action(coup,p,m);    
	break;
	case 'h':
	case 'H':
	  i = userInput("Numero du coups a afficher");
	  refresh();
	  PrintHisto(i,p,m);
	  getch();
	break;
	default : break;
      }
    }while (!fin_du_jeu(m));
}
	

	
	




