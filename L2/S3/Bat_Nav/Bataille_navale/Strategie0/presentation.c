/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include "presentation.h"

/****************  P R E S E N T A T I O N   D U   M E N U  *************/

int menu_presentation () {
	int version ;
	printf ("\033[34m\n") ;
	printf ("****************************************************************************************************\n\n") ;
	printf ("				     B A T A I L L E    N A V A L E\n\n") ;
	printf ("					     (Strategie 0)\n") ;
	printf ("				      par Diallo Alpha Oumar Binta\n\n") ;
	printf ("****************************************************************************************************\n\n\n") ;
	printf ("\033[0m");
	printf ("		 				M E N U :\n\n") ;
	printf ("		1)  	1 joueur contre l'ordinateur : Bateaux places aleatoirement\n") ;
	printf ("		2)  	1 joueur contre l'ordinateur : Bateaux places manuellement\n\n") ;
	printf ("				Veuillez choisir une option : ") ;
	do {
		scanf ("%d", &version) ;
		if ((version != 1) && (version != 2)) {
			printf ("\nCette option n'existe pas !\nVeuillez faire un autre choix : ") ;
		}
	} while ((version != 1) && (version != 2)) ;
	return (version) ;
}
