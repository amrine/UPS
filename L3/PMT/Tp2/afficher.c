#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include "afficher.h"

int afficher(char *nomFichier){
	int fich,nb;
	char tmp[TAILLE];
	fich = open(nomFichier,O_RDONLY);
	if(fich == -1){
		return ERR_OUV;
	}
	/*affichage du fichier sur l'ecran 1*/
	while((nb=read(fich,tmp,TAILLE)))
		write(1,tmp,nb);
	close(fich);
	return OK;		
}

