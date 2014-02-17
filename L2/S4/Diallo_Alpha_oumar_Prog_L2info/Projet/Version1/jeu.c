/*Auteur : Diallo Alpha Oumar Binta - Yechan Jang
Annee scolaire : 2010-2011
L2 informatique
Projet Amazones
*/

#include <stdlib.h>
#include <stdio.h>
#include "amazone.h"
#include "pile.h"


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
void action_homme (t_matrice m, int joueur) {
	coor_amazones deb, fin;

	printf ("\n\n*****************************\n");
	printf ("* C'est au tour du joueur %d *\n", joueur);
	printf ("*****************************\n\n\n");
	
	/* Selection de l'Amazone a deplace */
	do {
		printf("Entrez les coordonnees de l'Amazone a deplacer :\n ");
		saisir_coor(&deb);
		if ((m[deb.ord][deb.abs]) != couleur_joueur(joueur)) printf("Ceci n'est pas votre amazone \n Veuillez recommencer!\n");
		printf("Entrez les coordonnees d'arrivee de l'Amazone : \n");
		saisir_coor(&fin);
		if (!deplacement_possible(deb,fin,m)) printf("Deplacement incorrect \n Veuillez recommencer!\n");
		if ((deb.abs == fin.abs) && (deb.ord == fin.ord)) printf("\nVous avez entrer les memes coordonnees\n");
	} while (!deplacement_possible(deb,fin,m) || (m[deb.ord][deb.abs]) != couleur_joueur(joueur) || ((deb.abs == fin.abs) && (deb.ord == fin.ord)));
	
	/* On deplace le pion selectionne a sa nouvelle case */
	m[fin.ord][fin.abs] = couleur_joueur(joueur);
	m[deb.ord][deb.abs] = VIDE;

	/* On affiche les cases du deplacement */
	printf ("Il deplace %c%d en %c%d\n", 'A' + deb.abs, deb.ord + 1, 'A' + fin.abs, fin.ord + 1);
	afficher_matrice (m);

	/* Tirage de la fleche */
	deb = fin; /* les coordonnes d'arrivee de l'Amazone sont les coordonnees de depart de la fleche */
	do {
		printf("Entrez les coordonnees d'arrivee de la fleche pour le barrage : ");
		saisir_coor(&fin);
		if (!deplacement_possible(deb,fin,m)) printf("Deplacement incorrect \n Veuillez recommencer!\n");
	} while (!deplacement_possible(deb,fin,m));
	
	/* placement du barrage et affichage de la matrice */
	m[fin.ord][fin.abs] = FLECHE;
	printf ("Il place un barrage en %c%d\n", 'A' + fin.abs, fin.ord + 1);
	afficher_matrice (m);
}


void joueur1_vs_joueur2(t_matrice m){
	int joueur=1;
	
	printf ("  Joueur 1 vs Joueur 2 \n") ;
	printf ("Le joueur 1 joue avec les Amazones Blanches\n") ;
	printf ("Le joueur 2 joue avec les Amazones Noirs\n") ;
	while (!fin_du_jeu (m)){
		action_homme(m,joueur);
		joueur_suivant(&joueur);
	} 
}

void ordi_facile(t_matrice m)
{
	coor_amazones dep;
	coor_amazones coor;
	T_pile p;
	int i;
	
	printf ("\n\n*****************************\n");
	printf ("* C'est au tour de l'ordinateur\n");
	printf ("*****************************\n\n\n");
	
	initialiser_pile(&p);
	
	/* La machine determine tous les coups possibles */
	i = determiner_coups (m, &p, AMAZONE_NOIR); 	
	/* La machine choisie un coup parmis les multiples coups */
	if (pile_vide(p))
	{
		printf("  \n\n FIN DU JEU \n\n");
	}else {  
	
	get_coor(p,i,&dep,&coor);
	
	
	 /* Deplacement du pion */
	m[coor.abs][coor.ord] = AMAZONE_NOIR;
	m[dep.abs][dep.ord] = VIDE;
	printf ("L'ordinateur deplace %c%d en %c%d\n", 'A'+ dep.ord, dep.abs+1, 'A'+ coor.ord, coor.abs+1);
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
	 printf ("Il place un barrage en %c%d\n", 'A'+ coor.ord,coor.abs+1 );
	 /* Affichage du tirage */
	 afficher_matrice (m);
	}
}
	 
	
	

void joueur_vs_ordi(t_matrice m)
{
    int joueur = 1;
    int i=3;
  
    printf ("\n\n  \033[46m             \033[30mLe joueur joue avec les Amazones Blanches           \n\n\033[0m") ;
    printf("\033[46m         \033[30m   LE JEU CONTRE LA MACHINE COMMENCE DANS 3 SECONDES        \033[0m \n ");
    system("sleep 3");
    system("clear");
    while (i>0)
    {
      printf("\n\n\n\n\n\n\n\n\n\n\n\n\n							\033[37m %d\033[0m\n\n\n\n\n",i);
      system("sleep 1");
      system("clear");
      i--;
    }
    afficher_matrice(m);
    do
    {
      action_homme (m, joueur); 
      ordi_facile(m);
      save(m);
    }while (!fin_du_jeu(m));
}
	

	
	




