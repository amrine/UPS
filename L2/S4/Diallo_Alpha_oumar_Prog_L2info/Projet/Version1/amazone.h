/*Auteur : Diallo Alpha Oumar Binta - Yechan Jang
Annee scolaire : 2010-2011
Projet Amazones
*/
#include <stdbool.h>

#ifndef DEF_AMAZONE
#define DEF_AMAZONE


/**************  D E F I N I T I O N   D E S   V A R I A B L E S 
*****************/

/* Constante pour le nombre de case sur les lignes et colonnes */

#define N 10

/* definition des cases */

#define VIDE 'O'
#define AMAZONE_NOIR 'N'
#define AMAZONE_BLANCHE 'B'


/* Pour les attaques */

#define FLECHE 'X'

/* Matrice contenant les Amazones */

typedef char t_matrice[N][N] ;

/* Les Structures Utilisees */

/* coor_amazones contient les coordonnes des amazones */

typedef struct {
	int ord ;
	int abs ;
} coor_amazones;

/* structure contenant une liste de coordonnees et le numero du coup
*/

typedef struct cellule{
	int nbres_coups;
	coor_amazones coor;			/* Coordonnees arrive */
	coor_amazones dep;			/* Coordonnees depart */
	struct cellule *next;
}cellule;

void saisir_coor(coor_amazones *coor); 
/* fonction saisir_coor
  PE : coor_amazones
  PS : coor_amazones */
  
bool contenu_ds_grille (coor_amazones coor); 
/*	fonction contenu_ds_grille
	PE : coor_amazones
	PS : vrai si les coordonnees sont contenu dans la grille faux sinon
*/

bool case_touche (coor_amazones coor, char a); 
/* fonction case_touche
	PE : coor_amazones,char
	PS : vrai si la caractere est egal a touche, faux sinon
	*/

bool case_vide (char c); 
/*
In : un caractere
Action : teste si le caractère est égale au caractère vide(‘O’) 
Out : Booleen
*/

bool deplacement_possible(coor_amazones dep, coor_amazones arriv, t_matrice m);
/*
In : coordonnees de depart, coordonnees d’arrive, une matrice
Action : A ce niveau on verifie si le deplacement est possible a partir des
coordonnees de depart
aux coordonnees d’arrivees (voir que toutes les cases presentes entre les 2
coordoonnees sont vides)
Out : Bool ; - Vrai st_matricei le deplacement est possible
	       -Faux sinon
	       */

int menu_presentation () ; 
/*
In : Aucune
Action : affiche les differents options disponibles pour le jeu
Out : Un entier representant le choix du joueur
*/


void afficher_matrice (t_matrice m); 
/*fonction d'affichage de la matrice 

In : une matrice
Action : On parcours la matrice tout en affichant le contenu des caes sur
l’ecran
Out : Aucune
*/

void initialiser_matrice (t_matrice m); 
/*fonction d'initialisation des matrices

In: une matrice
Action: On initialise tous les cases a vide (‘O’) 
Out : une matrice
*/

void initialiser_matrice_jeu(t_matrice m); 
/*fonction d'initialisation de la matrice de jeu 

In: une matrice
Action: On initialise la matrice puis on place les amazones de depart a leur
place.
Out : une matrice
*/

void save(t_matrice m);	
/*
In : une matrice
Action : sauvegarde le contenu de la matrice dans un fichier
Out : fichier
*/

void load(t_matrice m);	
/*
In : une matrice
Action : charge le contenu du fichier sauvegarde dans la matrice passe en
parametre
Out : une matrice
*/


void action_homme (t_matrice m, int joueur); 
/*
In : une matrice, un entier
Action : le joueur choisi l’amazone a deplacer tout en verifiant que l’amazone
l’appartient. Ensuite
le joueur entre les coordonnees d’arrive. Enfin a partir de la position
d’arrivee, le joueur entre
les coordonnees d’arrive de la fleche. (On fera en sorte que les coordonnees
entrees verifient la
condition de « deplacement_possible »).
Out : Aucune
*/

void ordi_facile(t_matrice m); 
/*
In : une matrice
Action : On selectionnera aleatoirement un coups parmis les differents coups
possible que peut
effectuer chacun des pions de l’ordinateur.
Out : Aucune
*/
/*
bool coller_au_mur(t_matrice t, int abs, int ord);

bool peut_se_deplacer(t_matrice t, int abs, int ord);*/
/* Fonction qui verifie si un pion peut se deplacer , 
     PE : t_matrice , abs , ord 
     PS : Vrai si au moins un des cotes soit vide faux sinon */
/* Fonction utiliser pour la verification du fin de jeu */


bool fin_du_jeu (t_matrice m);
/*
In : une matrice
Action : on parcours la matrice pour chercher des eventuels coups possibles
Out : un entier
		false s'il reste des coups possibles
		true sinon
*/


void joueur_vs_ordi(t_matrice m);
/*
In : une matrice
Action : procedure de jeu d'une personne contre la machine
Out : Aucune
*/


void joueur_suivant (int *joueur); 
/* In : un entier
	Action : effectue la succession des tours de jeu
	Out: un entier
	*/

char couleur_joueur (int joueur); 
/* In : un entier
	 Action : retourne le type d'amazone possede par le joueur
	 Out : un caractere
	 */

void joueur1_vs_joueur2(t_matrice m); 
/*
In : une matrice
Action : procedure de jeu de deux personnes
Out : Acune
*/

#endif
