/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/


#include <stdlib.h>
#include <stdio.h>
#include "constantes.h"
#include "verifications.h"
#include "affichage_init.h"
#include "joueur.h"

/*Fonction de jeu du joueur_humain
PE: 3 matrices la matrice du joueur, de l'ordinateur et la grille d'attaque du joueur
PS: un entier rep=(nbre_case des bateaux l'ordinateur qui restent ) sinon rep=-1 s'il a deja joue a cet endroit
*/

int joueur_humain(t_matrice m_videJ,t_matrice m_ordi,t_matrice m_joueur,int* nbcase_ordi)
    {
        printf("					Tour du Joueur Humain\n");
        int i;
        char a,abs;
        int abs_dep,ord_dep;
        int rep;
        do
        {
            printf ("			Entrer les coordonnees du bateau a abattre (LETTRE chiffre)\n") ;
            scanf ("\n%c", &abs) ;
            scanf ("%d",&ord_dep) ;
            if ((abs >= 'a') && (abs <'a' + N)) {    /*change les minuscules en majuscules*/
				abs = abs + 'A' - 'a' ;
            }
            i=0 ;
            for (a='A' ; a<abs ; a++) {
                i++ ;
            }
            abs_dep = i ;
            ord_dep-- ;
            if (!contenu_ds_grille (abs_dep,ord_dep))
	    {
			printf("				Coordonnées incorrect :\n\n") ;
            }
        } while (!(contenu_ds_grille (abs_dep,ord_dep)));

	if (deja_touche(m_videJ[ord_dep][abs_dep]) )
	{
	  printf("        				Vous avez joue a cet endroit         \n\n") ;
	  rep= (-1);
        }
        else
	{
	  if (bateau(m_ordi[ord_dep][abs_dep]))
	  {
        m_ordi[ord_dep][abs_dep]=TOUCHE;
        m_videJ[ord_dep][abs_dep]=TOUCHE;
        (*nbcase_ordi)--;
	    printf ("\nVoici la grille de placement des bateaux :               ") ;
	    printf ("	Voici la grille d'attaque :\n\n   ") ;
	    afficher_2_matrices(m_joueur,m_videJ);
	  }
	  else
	  {
		m_videJ[ord_dep][abs_dep]=PLOUF;
		printf ("\nVoici la grille de placement des bateaux :               ") ;
		printf ("	Voici la grille d'attaque :\n\n   ") ;
		afficher_2_matrices(m_joueur,m_videJ);
	  }
	  if (*nbcase_ordi==0)
	  {
		printf ("\nVoici la grille de l'ordinateur :               ") ;
	    	printf ("		Voici la grille du joueur :\n\n   ") ;
		afficher_2_matrices(m_ordi,m_joueur);
                printf("\n					Le joueur Humain remporte la bataille \n");
	  }
        rep=(*nbcase_ordi);
      }
      return rep;
    }
