/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
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
