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
#include <string.h>
#include "afficher.h"

char *ChangerExtension(char *NomFichier, char *Extension){
	char* newName;
	newName = malloc(sizeof(char)*(strlen(NomFichier)+strlen(Extension)+1));
	strcpy(newName,NomFichier);
	strtok(newName,"."); 
	strcat(newName,".");
	strcat(newName,Extension);
  	return newName; 
}



int main (int argc, char *argv[])
{
	int i,fich,cmpteRendu;
	char *fichierr;
	bool creationLien=true;
	char **tabNomFichierO;
	if(argc == 1){
		printf("compiler : fichier1.c fichier2.c ... fichiern.c\n");
		exit(EXIT_FAILURE);
	}
	for(i=1;i<argc;i++){
		switch(fork()){
			case -1 :	perror("compiler : "); exit(ERR_FILS);
			case  0 :	fichierr = ChangerExtension(argv[i],"err");
						if ((fich =creat(fichierr,S_IRWXU|S_IRGRP|S_IROTH)) == -1){
							perror("compiler : fichier erreur ");
							exit(ERR_CRE);
						}
						/*copie des erreurs se trouvant sur la sortie des erreurs 2 vers le fichier
						 1= sortie standard*/
						dup2(fich,2);
					 	execlp("gcc","gcc","-c",argv[i],NULL);
					 	perror("erreur execution gcc : "); exit(ERR_FILS);break;
					  
		}
		/*attente de la terminaison du fils */
		wait(&cmpteRendu);
		/*si il ya eu erreur de compilation affichage du nom du fichier contenant le programme */
		
		if ( WEXITSTATUS(cmpteRendu)!=0){
			creationLien = false;
			printf("Des erreurs de compilations ont ete detectees dans le fichier %s, les\nerreurs se trouvent dans le fichier  %s\n",
			argv[i],ChangerExtension(argv[i],"err"));
		}
	}
	if(creationLien) {
		printf("creation lien\n");
		tabNomFichierO=(char**)malloc((argc+1)*sizeof(char*));
		tabNomFichierO[0]=(char*)malloc(3*sizeof(char));
		strcpy(tabNomFichierO[0],"cc");
	
		for(i=1;i<argc;i++){
			tabNomFichierO[i]=(char*)malloc((strlen(argv[i]))*sizeof(char));
			strcpy(tabNomFichierO[i],ChangerExtension(argv[i],"o"));
		}
		tabNomFichierO[argc] =NULL;
		
		for(i=0;i<argc+1;i++){
			printf("tab %d = %s\n",i,tabNomFichierO[i]);
		}
		
		/*creation du lien */
		execvp("gcc",tabNomFichierO);
	}
	
	return 0;
}


