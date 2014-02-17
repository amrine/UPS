#define _POSIX_C_SOURCE 1
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "fonction.h"


/*application client */
int main(int argc, char** argv){
	printf("\nLes requetes sont de types commandes linux exemple :\nls,pwd,cat nom_fichier,...\n");
	utilisateur user;
	printf("Enter le nom du tube a cree :");
	gets(user.tubeDeTransmission);
	printf("Entrer votre requete :");
	gets(user.requete);
	ecritureDansTube("SERVER.fifo",user);

	return OK;
}
