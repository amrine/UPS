#define _POSIX_C_SOURCE 1
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "fonction.h"

/*application serveur */
int main(int argc, char** argv){

	/* le nom du tube correspond a argv[1] */
	char* nomTube= "SERVER.fifo";
	/* suppression du fichier correspondant au tube s'il existe */
 	remove(nomTube);
	/*	le serveur doit boucler sur la lecture des requêtes dans le tube d’entree
		mise en place du tube nommé avec lequel les utilisateurs communiquent(connection,soumission requete)
		*/
	boucleLectureDansTube(creerTube(nomTube,0666));
	return OK;
}
