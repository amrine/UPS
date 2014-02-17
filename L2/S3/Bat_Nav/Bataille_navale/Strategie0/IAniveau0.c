/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "constantes.h"
#include "verifications.h"
#include "affichage_init.h"
#include "IAniveau0.h"



/*Fonction de jeu du l'ordinateur
PE: 3 matrices, la matrice du joueur, de l'ordinateur et la grille d'attaque de l'ordinateur
PS: un entier le nbre_case des bateaux du joueur qui restent
*/



int joueur_ordi(t_matrice m_videIA,t_matrice m_videJ,t_matrice m_joueur,t_matrice m_ordi,int* nbcase_joueur)
{
    printf("					Tour de l'ordinateur\n");
    int abs_dep,ord_dep;
    do
    {
        abs_dep = rand() % N ;
        ord_dep = rand() % N ;
    }while (deja_touche(m_videIA[ord_dep][abs_dep]));
    if (bateau(m_joueur[ord_dep][abs_dep]))
        {
            m_joueur[ord_dep][abs_dep]=TOUCHE;
            m_videIA[ord_dep][abs_dep]=TOUCHE;
            (*nbcase_joueur)--;
            printf ("\nVoici la grille de placement des bateaux :               ") ;
            printf ("	Voici la grille d'attaque :\n\n   ") ;
            afficher_2_matrices(m_joueur,m_videJ);
        }
        else
        {
            m_videIA[ord_dep][abs_dep]=PLOUF;
        }
        if (*nbcase_joueur==0)
        {
            printf ("\nVoici la grille du joueur :               ") ;
	    printf ("		Voici la grille de l'ordinateur :\n\n   ") ;
	    afficher_2_matrices(m_joueur,m_ordi);
            printf("\n					L'ordinateur remporte la bataille \n");
        }
        return (*nbcase_joueur);
}
