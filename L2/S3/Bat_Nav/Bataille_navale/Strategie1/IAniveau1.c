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
#include "joueur.h"
#include "IAniveau1.h"

/*Fonction de jeu de l'ordinateur
PE: 4 matrices, la matrice du joueur, de l'ordinateur et les grille d'attaques du joueur et de l'ordinateur
PS: un entier le nbre_case des bateaux du joueur qui restent
*/


int joueur_ordi(t_matrice m_videIA,t_matrice m_joueur,t_matrice m_ordi,int* nbcase_joueur,t_matrice m_videJ,int* nbcase_ordi)
{
    int cpt,i=1;
    printf("					Tour de l'ordinateur\n");
    int abs_dep,ord_dep;
    int longueur;
    char case_j;
    do
    {
        abs_dep = rand() % N ;
        ord_dep = rand() % N ;
    }while (deja_touche(m_videIA[ord_dep][abs_dep]));
      if (bateau(m_joueur[ord_dep][abs_dep]))
        {
	    case_j=m_joueur[ord_dep][abs_dep];
	    m_joueur[ord_dep][abs_dep]=TOUCHE;
            m_videIA[ord_dep][abs_dep]=TOUCHE;
            (*nbcase_joueur)--;
	    printf ("\nVoici la grille de placement des bateaux :               ") ;
            printf ("	Voici la grille d'attaque :\n\n   ") ;
            afficher_2_matrices(m_joueur,m_videJ);
            cpt=1;
	    switch(case_j){
	     case PORTEAVION : longueur=4;
			   break;
	     case CROISEUR :   longueur=3;
			   break;
	     case DESTROYER :  longueur=2;
			   break;
	     case VEDETTE :    longueur=1;
			   break;
	    }
        }
        else
        {
            m_videIA[ord_dep][abs_dep]=PLOUF;
            cpt=0;
        }
	if (cpt==1)
	  {
            for (i=1;i<longueur;i++)
            {

                if (!(case_vide(m_joueur[ord_dep][abs_dep-i+1])) && bateau(m_joueur[ord_dep][abs_dep-i]) && (m_joueur[ord_dep][abs_dep-i] == case_j))
                {
                  joueur_humain(m_videJ,m_ordi,m_joueur,nbcase_ordi );
		  m_joueur[ord_dep][abs_dep-i]= TOUCHE;
		  (*nbcase_joueur)--;
		  printf ("\nVoici la grille de placement des bateaux :               ") ;
                  printf ("	Voici la grille d'attaque :\n\n   ") ;
                  afficher_2_matrices(m_joueur,m_videJ);

                }
                if (!(case_vide(m_joueur[ord_dep][abs_dep+i-1])) && bateau(m_joueur[ord_dep][abs_dep+i]) && (m_joueur[ord_dep][abs_dep+i] == case_j))
                {
                  joueur_humain(m_videJ,m_ordi,m_joueur,nbcase_ordi );
		  m_joueur[ord_dep][abs_dep+i]= TOUCHE;
		  (*nbcase_joueur)--;
		  printf ("\nVoici la grille de placement des bateaux :               ") ;
                  printf ("	Voici la grille d'attaque :\n\n   ") ;
                  afficher_2_matrices(m_joueur,m_videJ);

                }
                if (!(case_vide(m_joueur[ord_dep+i-1][abs_dep])) && bateau(m_joueur[ord_dep+i][abs_dep]) && (m_joueur[ord_dep+i][abs_dep] == case_j))
                {
                  joueur_humain(m_videJ,m_ordi,m_joueur,nbcase_ordi );
		  m_joueur[ord_dep+i][abs_dep]= TOUCHE;
		  (*nbcase_joueur)--;
		  printf ("\nVoici la grille de placement des bateaux :               ") ;
                  printf ("	Voici la grille d'attaque :\n\n   ") ;
                  afficher_2_matrices(m_joueur,m_videJ);

                }
                if (!(case_vide(m_joueur[ord_dep-i+1][abs_dep])) && bateau(m_joueur[ord_dep-i][abs_dep]) && (m_joueur[ord_dep-i][abs_dep] == case_j))
                {
                  joueur_humain(m_videJ,m_ordi,m_joueur,nbcase_ordi );
		  m_joueur[ord_dep-i][abs_dep]= TOUCHE;
		  (*nbcase_joueur)--;
		  printf ("\nVoici la grille de placement des bateaux :               ") ;
                  printf ("	Voici la grille d'attaque :\n\n   ") ;
                  afficher_2_matrices(m_joueur,m_videJ);

                }
            }
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
