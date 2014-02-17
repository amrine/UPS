#define _POSIX_C_SOURCE 1
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "erreur.h"

int main(int argc, char *argv[]){
  int i,j;
  char *commande[argc];
  if (argc == 1){
	fprintf(stderr,"my_sh : nom_commande\nmy_sh : nom_commande1;nom_commande2;\n");
	exit(1);
  }
  for(i=1;i<argc;i++){
	commande[i] = argv[i];
	if(*argv[i] == ';'){
	  for(j=0;j<i;j++){
		execlp(commande[0],commande,NULL);
	  }
	}
  }
  return 0;

}

