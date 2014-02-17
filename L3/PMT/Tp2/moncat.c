#define _POSIX_C_SOURCE 1
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <string.h>
#include <stdbool.h>
#include "afficher.h"
 

int main(int argc, char *argv[]){
	int i;
	int fich;
	bool redirection=false;
	if(argc == 1) {
		printf("moncat : fich1 fich 2\nmoncat : fich1 fichi2 \">\" fichier_dest\n");
		exit(ERR_PARAM);
	}
	if(strcmp(argv[argc-2],">") == 0){
			redirection=true;
		}
	
	if (redirection){
		if ((fich =creat(argv[argc-1],S_IRWXU|S_IRGRP|S_IROTH)) == -1){
			perror("moncat : ");
			exit(ERR_CRE);
			}
		/*close(1);
		dup(fich); equivaut a dup2(fich,1) copie la sortie standard ecran dans le fichier
		fich*/
		dup2(fich,1);
		for(i=1;i<argc-2;i++)
		afficher(argv[i]);
			
	}else{
		for(i=1;i<argc;i++)
		afficher(argv[i]);
	}
	return OK;
}

