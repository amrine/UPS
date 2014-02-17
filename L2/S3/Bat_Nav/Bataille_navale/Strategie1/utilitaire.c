/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include "constantes.h"
#include "verifications.h"
#include "utilitaire.h"

/***************  M O D I F I C A T I O N S  ***************/
/*fonction orientation,
PE : un entier
PS : un char correspondant a son orientation
N=1 E=2 O=3 S=4 */
char orientation  (int o) {
	switch (o) {
		case 1 :
			return ('N') ;
			break ;
		case 2 :
			return ('E') ;
			break ;
		case 3 :
			return ('O') ;
			break ;
		case 4 :
			return ('S') ;
			break ;
		default:
			printf ("\n\nOrientation inexistante!\n\n");
			return (' ') ;
			break ;
	}
}

/*** SAUVEGARDE ET CHARGEMENT ***/
/*sauvegarde des matrices des joueurs*/

void sauvegarde(t_matrice m_joueur,t_matrice m_videJ,t_matrice m_ordi,t_matrice m_videIA)
{
    int i;
    int j;
    FILE* save=NULL;
  save=fopen("matrice.txt", "w");
  if (save != NULL)
  {
    for (i=0; i <N; i++){
    for (j=0; j <N; j++){
      fprintf(save, "%c",m_joueur[i][j]);
    }
    }
    for (i=0; i <N; i++){
    for (j=0; j <N; j++){
      fprintf(save, "%c",m_videJ[i][j]);
    }
  }
  for (i=0; i <N; i++){
    for (j=0; j <N; j++){
      fprintf(save, "%c",m_ordi[i][j]);
    }
  }
  for (i=0; i <N; i++){
    for (j=0; j <N; j++){
      fprintf(save, "%c",m_videIA[i][j]);
    }
  }
  fclose(save);
  }

}

/*sauvegarde des nombres de case restantes des joueurs*/

void sauvegarde_nbcase(int *nbcase_ordi,int *nbcase_joueur)
{
    FILE* save=NULL;
  save=fopen("val_case.txt", "w");
  if (save != NULL)
  {
      fputc(*nbcase_ordi,save);
      fputc(*nbcase_joueur,save);
    }
    fclose(save);
  }

/*chargement des nombres de cases restantes*/

void charger_nbcase(int *nbcase_ordi,int *nbcase_joueur)
{
    FILE* load=NULL;
    load=fopen("val_case.txt", "r");
    if (load != NULL)
    {
        *nbcase_ordi=fgetc(load);
        *nbcase_joueur=fgetc(load);
    }
    fclose(load);
}

/*chargement des matrices des joueurs*/

void charger(t_matrice m_joueur,t_matrice m_videJ,t_matrice m_ordi,t_matrice m_videIA)
{
    FILE* load=NULL;
    int i;
    int j;
    char caractereActuel;
    load=fopen("matrice.txt", "r");
    if (load != NULL)
    {

            for (i=0; i <N; i++){
            for (j=0; j <N; j++){
                caractereActuel=fgetc(load);
                m_joueur[i][j]=caractereActuel;
            }
            }
            for (i=0; i <N; i++){
            for (j=0; j <N; j++){
                caractereActuel=fgetc(load);
                m_videJ[i][j]=caractereActuel;
            }
            }
            for (i=0; i <N; i++){
            for (j=0; j <N; j++){
                caractereActuel=fgetc(load);
                m_ordi[i][j]=caractereActuel;
            }
            }
            for (i=0; i <N; i++){
            for (j=0; j <N; j++){
                caractereActuel=fgetc(load);
                m_videIA[i][j]=caractereActuel;
            }
            }

    fclose(load);
}
}


char abcisse(int x)
{
   char res;
    switch (x)
    {
        case 0 :
			res= ('A') ;
			break ;
		case 1 :
			res= ('B') ;
			break ;
		case 2 :
			res= ('C') ;
			break ;
		case 3 :
			res= ('D') ;
			break ;
		case 4 :
			res= ('E') ;
			break ;
		case 5 :
			res= ('F') ;
			break ;
		case 6 :
			res= ('G') ;
			break ;
		case 7 :
			res= ('H') ;
			break ;
		case 8 :
			res= ('I') ;
			break ;
        case 9 :
			res= ('J') ;
			break ;
    }
    return res;
}


void cheat (t_matrice m)
{
    int i,j,cpt=0;
    char type;
    printf("           				Entrer le type de bateau rechercher\n                  		  PORTEAVION=P CROISEUR=C DESTROYER=D VEDETTE=V : ");
    scanf("\n%c",&type);
    if (!bateau(type))
    {
        printf("Type de bateau inconnue\n");
    }
    else
    {
        for (i=0;i<N;i++)
        {
        for (j=0;j<N;j++)
        {
            if (m[i][j]== type)
            {
                cpt++;
                printf("les coordonnes du %c %d = (%c,%d)\n",type,cpt,abcisse(j),i+1);
            }

        }
        }
    }
}

