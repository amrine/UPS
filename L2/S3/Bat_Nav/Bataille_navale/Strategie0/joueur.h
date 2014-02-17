/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

/*Fonction de jeu du joueur_humain
PE: 3 matrices la matrice du joueur, de l'ordinateur et la grille d'attaque du joueur
PS: un entier rep=(nbre_case des bateaux l'ordinateur qui restent ) sinon rep=-1 s'il a deja joue a cet endroit
*/

#ifndef DEF_JOUEUR
#define DEF_JOUEUR

int joueur_humain(t_matrice m_videJ,t_matrice m_ordi,t_matrice m_joueur,int* nbcase_ordi);

#endif
