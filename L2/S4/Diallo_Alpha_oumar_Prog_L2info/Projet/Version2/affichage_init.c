/*Auteur : Diallo Alpha Oumar Binta - Yechan Jang
Annee scolaire : 2010-2011
L2 informatique
Projet Amazones
*/

#include <stdlib.h>
#include <stdio.h>
#include <ncurses.h>
#include "amazone.h"



/******************  A F F I C H A G E  *********************/

void init_curses(){
	initscr();	
	start_color();
	init_pair(1, COLOR_WHITE, COLOR_RED);  /*Amazone Blanche */
	init_pair(2, COLOR_WHITE, COLOR_YELLOW); /*Amazone Blanche */
	init_pair(3, COLOR_BLACK, COLOR_RED); /*Amazone Noire*/
	init_pair(4, COLOR_BLACK, COLOR_YELLOW); /*Amazone Noire */
	init_pair(5, COLOR_GREEN, COLOR_RED); /*Fleche */
	init_pair(6, COLOR_GREEN, COLOR_YELLOW); /*Fleche */
	init_pair(7, COLOR_BLUE, COLOR_RED); /*vide */
	init_pair(8, COLOR_BLUE, COLOR_YELLOW);/*vide*/
	init_pair(9, COLOR_MAGENTA,COLOR_CYAN);/*vide*/
	keypad(stdscr,TRUE);
}

void afficher_entete(int i){
  coor_amazones coord_help;
 /*If we can print the help or if forced is set to true then print help*/
  coord_help.ord=0;
  coord_help.abs=0;
  if (i==2){
    mvprintw(coord_help.ord ,coord_help.abs, "PLAYER 1 Vs PLAYER 2\n");
    mvprintw((coord_help.ord)+1, coord_help.abs, "Le joueur 1 joue avec les Amazones Blanches\n");
    mvprintw((coord_help.ord)+2, coord_help.abs, "Le joueur 2 joue avec les Amazones Noirs\n");
  }else {
    mvprintw(coord_help.ord ,coord_help.abs, "PLAYER Vs IA \n");
    mvprintw((coord_help.ord)+1, coord_help.abs, "Vous avez les Amazones Blanches\n");
    mvprintw((coord_help.ord)+2, coord_help.abs, "C	pour Annuler un coup\n");
    mvprintw((coord_help.ord)+3, coord_help.abs, "H	pour Afficher l'Historique\n");
  }
}

void initialiser_matrice (t_matrice m) {
	int i, j ;
	/* initialisation a vide */
	for (i=0; i<N; i++) {
		for (j=0; j<N; j++) {
			m[i][j] = VIDE ;
		}
	}
}

void initialiser_matrice_jeu(t_matrice m){
  initialiser_matrice(m);
  /* placement des amazones noirs */
	m[0][3] = AMAZONE_BLANCHE ;
	m[0][6] = AMAZONE_BLANCHE ;
	m[3][0] = AMAZONE_BLANCHE ;
	m[3][9] = AMAZONE_BLANCHE ;
	/* placement des amazones blanches */
	m[9][3] = AMAZONE_NOIR ;
	m[9][6] = AMAZONE_NOIR ;
	m[6][0] = AMAZONE_NOIR ;
	m[6][9] = AMAZONE_NOIR ;
}

void afficher_matrice (t_matrice m) {
int i, j ;
char a = 'a' ;
printw("\n   ");

for (i=0; i<N; i++) {	/*affichage des lettres correspondant aux abscisses de
la matrice */
	
	printw ("  %c ", a) ;
	a ++ ;
}
for (i=N-1; i>=0; i--) {
	if (i<9) {								/*lignes de la matrice */
		printw ("\n %d  ", i+1) ;
		} else {
			printw ("\n%d  ", i+1) ;
		}
if (i % 2 == 0){
	for (j=0; j<N; j++) {
		if (j % 2 == 0){
			switch(m[i][j]){
				case AMAZONE_BLANCHE: attron(COLOR_PAIR(1));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(1));
															break;
				case AMAZONE_NOIR: 	attron(COLOR_PAIR(3));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(3));
															break;
				case FLECHE: 					attron(COLOR_PAIR(5));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(5));
															break;
				case VIDE: 						attron(COLOR_PAIR(7));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(7));
															break;
				default: 							printw("Type Inconnu\n");break;
				}
		}else {
			switch(m[i][j]){
				case AMAZONE_BLANCHE: attron(COLOR_PAIR(2));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(2));
															break;
				case AMAZONE_NOIR: 	attron(COLOR_PAIR(4));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(4));
															break;
				case FLECHE: 					attron(COLOR_PAIR(6));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(6));
															break;
				case VIDE: 						attron(COLOR_PAIR(8));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(8));
															break;
				default: 							printw("Type Inconnu\n");break;
				}
	 	 }
	}
} else {
	for (j=0; j<N; j++) {
		if (j % 2 != 0){
			switch(m[i][j]){
				case AMAZONE_BLANCHE: attron(COLOR_PAIR(1));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(1));
															break;
				case AMAZONE_NOIR: 	attron(COLOR_PAIR(3));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(3));
															break;
				case FLECHE: 					attron(COLOR_PAIR(5));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(5));
															break;
				case VIDE: 						attron(COLOR_PAIR(7));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(7));
															break;
				default: 							printw("Type Inconnu\n");break;
				}

		}else {
			switch(m[i][j]){
				case AMAZONE_BLANCHE: attron(COLOR_PAIR(2));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(2));
															break;
				case AMAZONE_NOIR: 	attron(COLOR_PAIR(4));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(4));
															break;
				case FLECHE: 					attron(COLOR_PAIR(6));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(6));
															break;
				case VIDE: 						attron(COLOR_PAIR(8));
															printw (" %c  ", m[i][j]) ;
															attroff(COLOR_PAIR(8));
															break;
				default: 							printw("Type Inconnu\n");break;
				}
		}
	}
 }
}
 refresh();
}
