/*Auteur : Diallo Alpha Oumar Binta - Yechan Jang
Annee scolaire : 2010-2011
L2 informatique
Projet Amazones
*/

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "amazone.h"



/* fonction saisir_coor
  PE : coor_amazones
  PS : enregistre les coordonnees saisies par le joueur */

void saisir_coor(coor_amazones *coor){
  int i;
  char a,abs;
  printf("(LETTRE, chiffre) :");
  scanf(" %c",&abs);
  scanf("%d",&coor->ord);
  if ((abs >= 'a') && (abs < 'a' + N)){
	/* si abs est une minuscule et qu'il n'est pas superieur a la lettre j on le
		change en majuscule */
	abs = abs + 'A' - 'a';
  }
  i=0;
  for (a='A' ; a<abs ; a++){
	i++;
  }
  coor->abs = i;
  coor->ord --;
  while(!contenu_ds_grille (*coor)){
	/* tant que les coordonnes n'appartiennent pas a la grille, on recommence
		la saisie des coordonnees */
	printf("Coordonnes incorrect ! \nVeuillez recommencer\n");
	saisir_coor(coor);	
  }
}

/*
In : une matrice
Action : sauvegarde le contenu de la matrice dans un fichier
Out : fichier
*/
void save(t_matrice m)
{
    int i;
    int j;
    FILE* fich=NULL;
  fich=fopen("matrice.txt", "w");
  if (fich != NULL)
  {
    for (i=N-1; i>=0; i--){
    for (j=N-1; j>=0; j--){
      fprintf(fich, "%c ",m[i][j]);
    }
    fprintf(fich, "\n");
    }
  fclose(fich);
  }
  else {
	 fprintf(stderr,"Erreur de Creation du Fichier de Sauvegarde\n");
	 fprintf(stderr,"Reprise de la Sauvegarde\n");
	 save(m);
  }
}


/*
In : une matrice
Action : charge le contenu du fichier sauvegarde dans la matrice passe en
parametre
Out : une matrice
*/
void load(t_matrice m)
{
    FILE* fich=NULL;
	 FILE* temp=NULL;
    int i;
    int j;
    char caractereActuel;
    fich=fopen("matrice.txt", "r");
	 temp=fopen("temp.txt", "w"); /* creation d'un fichier temporaire */
    if (fich != NULL && temp != NULL)
    {
		 for (i=(N-1)*2; i>=0; i--){
       for (j=(N-1)*2; j>=0; j--){
			if (fscanf(fich,"%c",&caractereActuel))
				if (caractereActuel != '\n' && caractereActuel != ' ')
					 fprintf(temp,"%c",caractereActuel); /* ecriture dans le fichier
						temporaire des caracteres de la matrice sur une meme ligne */
       }
       }
    fclose(fich);
	 }
	 else {
	 fprintf(stderr,"Erreur de l'Ouverture du Fichier de Sauvegarde\n");
	 fprintf(stderr,"Aucune Sauvegarde n'a ete Trouve\n");
	 exit(EXIT_FAILURE);
  }
  freopen("temp.txt", "r",temp); /* Reouverture du fichier temporaire */
    if (temp != NULL)
    {
      for (i=N-1; i>=0; i--){
      for (j=N-1; j>=0; j--){
          caractereActuel=fgetc(temp);
          m[i][j]=caractereActuel; 
       }
       }
	 fclose(temp);
	 remove("temp.txt"); /* suppression du fichier temporaire */
	 }
	 else {
	 fprintf(stderr,"Erreur de d'ouverture du Fichier Temporaire\n");
	 fprintf(stderr,"Reprise du Chargement\n");
	 load(m);
  }
}




  
