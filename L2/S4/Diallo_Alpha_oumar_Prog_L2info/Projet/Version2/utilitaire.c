/*Auteur : Diallo Alpha Oumar Binta - Yechan Jang
Annee scolaire : 2010-2011
L2 informatique
Projet Amazones
*/

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <ncurses.h>
#include "amazone.h"
#include <string.h>

char carac( WINDOW* win){
	char c;
	wscanw(win,"%c",&c);
	return c;
}

int entier( WINDOW* win){
	int i;
	wscanw(win,"%d",&i);
	return i;
}

int userInput(char* message){
 WINDOW* win;
 int rep;
 coor_amazones max;
 getmaxyx(stdscr, max.ord, max.abs);
 win = newwin(4, 30, max.ord / 2, max.abs / 2);
 box(win, ACS_VLINE, ACS_HLINE);
 mvwprintw(win, 1, 1, "%s", message);
 wmove(win, 2, 1);
 wrefresh(win);
 echo();
 rep = entier(win);
 noecho();
 delwin(win);
 return rep;
}

/* fonction saisir_coor
  PE : coor_amazones
  PS : enregistre les coordonnees saisies par le joueur */

void saisir_coor(coor_amazones *coor,char* message){
  WINDOW* win;
  WINDOW* win2;
  int y,i;
	char c,a;
 /* max de la console */
  /*creation de la fenetre*/
  win = newwin(4, 30, 20 , 20);
 /*dessin de la boite*/
  box(win, 0, 0);
  mvwprintw(win, 1, 1, "%s", message);
  wmove(win, 2, 1);
  wrefresh(win);
   /*activation d'echo pour montrer la saisie*/
  echo();
  c=carac(win);
  werase(win);
  noecho();
  delwin(win);
  /*creation de la fenetre*/
  win2 =newwin(4, 30, 20 , 20);
  box(win2, 0, 0);
  mvwprintw(win2, 1, 1, "%s", message);
  wmove(win2, 2, 1);
  wrefresh(win2);
  echo();
  y=entier(win2);
  refresh();
  noecho();
  delwin(win2);
  if ((c >= 'a') && (c < 'a' + N)){
	/*si abs est une minuscule et qu'il n'est pas superieur a la lettre j on le
		change en majuscule */
	c = c + 'A' - 'a';
  }
  i=0;
  for (a='A' ; a<c ; a++){
	i++;
  }
  coor->abs = i;
  y--;
  coor->ord= y;
  while(!contenu_ds_grille (*coor)){
	/* tant que les coordonnes n'appartiennent pas a la grille, on recommence
		la saisie des coordonnees */
	mvprintw(18,0,"Coordonnes incorrect ! \nVeuillez recommencer\n");
	refresh();
	saisir_coor(coor,message);
  }
}

/*
In : une matrice
Action : sauvegarde le contenu de la matrice dans un fichier
Out : fichier
*/
void save(t_matrice m){
  int i;
  int j;
  FILE* fich=NULL;
  fich=fopen("matrice.txt", "w");
  if (fich != NULL)
  {
    for (i=N-1; i>=0; i--){
    for (j=N-1; j>=0; j--){
      fprintf(fich, "%c",m[i][j]);
    }
    }
  fclose(fich);
  }
  else {
	 printw("Erreur de Creation du Fichier de Sauvegarde\n");
	 printw("Reprise de la Sauvegarde\n");
	 refresh();
	 save(m);
  }
}


/*
In : une matrice
Action : charge le contenu du fichier sauvegarde dans la matrice passe en
parametre
Out : une matrice
*/
int load(t_matrice m){
    FILE* fich=NULL;
    int i;
    int j;
    char caractereActuel;
    fich=fopen("matrice.txt", "r");
    if (fich != NULL){
	for (i=N-1; i>=0; i--){
	for (j=N-1; j>=0; j--){
          caractereActuel=fgetc(fich);
          m[i][j]=caractereActuel; 
	}
	}
	fclose(fich);
	return 1;
    }else {
	 fprintf(stderr,"Erreur de l'Ouverture du Fichier de Sauvegarde\n");
	 fprintf(stderr,"Aucune Sauvegarde n'a ete Trouve\n");
	 return (-1);
    }
}



int displayMenuPrincipal(){
 int x, y, x_save, y_save;
 int key;
 int ret = -1;
 int exit = 0;

 /*Get the screen coord max and write the menu at the screen center*/
 getmaxyx(stdscr, y_save, x_save);

 x_save = x_save / 2;
 y_save = y_save / 2;
 x = x_save;
 y = y_save - 2; 

 /*Print menu*/
 mvwaddstr(stdscr, y, x, "AMAZONE MENU");
 y = y + 2;
 mvwaddstr(stdscr, y, x, "New game");
 y++;
 mvwaddstr(stdscr, y, x, "Load Game");
 y++;
 mvwaddstr(stdscr, y, x, "EXIT");

 move(y_save, x_save);
 chgat(11, A_NORMAL, 2, NULL);
 refresh();

 do{ 
 /*Menu navigation*/
 key = getch();

 switch( key ){
 case KEY_UP:
  getyx(stdscr, y , x);
  if( y > y_save){
   chgat(11, A_NORMAL, 0, NULL);
   y--;
   move(y, x);
   chgat(11, A_NORMAL, 2, NULL);
  }
 break;
 case KEY_DOWN:
  getyx(stdscr, y , x);
  if( y < y_save + MENU_HEIGHT - 1){
   chgat(11, A_NORMAL, 0, NULL);
   y++;
   move(y, x);
   chgat(11, A_NORMAL, 2, NULL);
  }
 break;
 case ENTER:
  /*set the return value and break the boucle in setting exit value*/
  getyx(stdscr, y, x);
  if(y == y_save){
   ret = NEW; /*New game is choosen*/
  } else if(y == (y_save + 1)){
   ret = LOAD; /*Load game is choosen*/
  }
  exit = 1;
 break;
 case ESCAPE:
  ret = EXIT;
  exit = 1;
 break;
 }
  refresh();
 }while(exit != 1);

 return ret;

}

int displayS_MenuGame(){
 int x, y, x_save, y_save;
 int key;
 int ret = -1;
 int exit = 0;

 /*Get the screen coord max and write the menu at the screen center*/
 getmaxyx(stdscr, y_save, x_save);

 x_save = x_save / 2;
 y_save = y_save / 2;
 x = x_save;
 y = y_save - 2; 

 /*Print menu*/
 mvwaddstr(stdscr, y, x, "GAME MENU");
 y = y + 2;
 mvwaddstr(stdscr, y, x, "PLAYER 1 Vs PLAYER 2");
 y++;
 mvwaddstr(stdscr, y, x, "PLAYER Vs IA");
 y++;
  mvwaddstr(stdscr, y, x, "Quit Application");

 move(y_save, x_save);
 chgat(20, A_NORMAL, 2, NULL);
 refresh();

 do{ 
 /*Menu navigation*/
 key = getch();

 switch( key ){
 case KEY_UP:
  getyx(stdscr, y , x);
  if( y > y_save){
   chgat(20, A_NORMAL, 0, NULL);
   y--;
   move(y, x);
   chgat(20, A_NORMAL, 2, NULL);
  }
 break;
 case KEY_DOWN:
  getyx(stdscr, y , x);
  if( y < y_save + MENU_HEIGHT - 1){
   chgat(20, A_NORMAL, 0, NULL);
   y++;
   move(y, x);
   chgat(20, A_NORMAL, 2, NULL);
  }
 break;
 case ENTER:
  /*set the return value and break the boucle in setting exit value*/
  getyx(stdscr, y, x);
  if(y == y_save){
   ret = PLAYER_1_Vs_PLAYER_2; 
  } else if(y == (y_save + 1)){
   ret = PLAYER_Vs_IA;
  }
  exit = 1;
 break;
 case ESCAPE:
  ret = EXIT;
  exit = 1;
 break;
 }
  refresh();
 }while(exit != 1);

 return ret;

}
  
void print_in_middle(WINDOW *win, int starty, int startx, int width, char *string)
{	int length, x, y;
	float temp;

	if(win == NULL)
		win = stdscr;
	getyx(win, y, x);
	if(startx != 0)
		x = startx;
	if(starty != 0)
		y = starty;
	if(width == 0)
		width = 80;

	length = strlen(string);
	temp = (width - length)/ 2;
	x = startx + (int)temp;
	mvwprintw(win, y, x, "%s", string);
	refresh();
}

void Welcome(){
	print_in_middle(stdscr,2, 0, 0, "Projet de Programmation Licence 2 Informatique");
    	print_in_middle(stdscr,4, 0, 0, "Universite Paul Sabatier Annee Scolaire 2010-2011");
	print_in_middle(stdscr,6, 0, 0, "Par Jang Yechan & Diallo Alpha Oumar Binta");
	print_in_middle(stdscr,7, 0, 0, "Encadrant : Pierre Piccinini");
	print_in_middle(stdscr,9, 0, 0, "Vous serez amener a saisir des coordonnees de tir (lettre,chiffre)");
	print_in_middle(stdscr,10, 0, 0, "Representant respectivement l'abscisse et l'ordonnee ");
	print_in_middle(stdscr,11, 0, 0,"que vous validerez en tapotant la touche ENTRER");
	print_in_middle(stdscr,12, 0, 0, "Diffrents types d'actions sont proposes durant le jeu");
	print_in_middle(stdscr,13, 0, 0, "vous taperez la touche correspondante pour effectuer l'action");
	print_in_middle(stdscr,14, 0, 0, "AMUSEZ VOUS BIEN");
	print_in_middle(stdscr,16, 0, 0, "Taper une touche pour Continuer");
	getch();
	endwin();
}
