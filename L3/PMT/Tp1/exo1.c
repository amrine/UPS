#define _POSIX_C_SOURCE 1
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

int main(){
  int NB_MAX =5;
  int i;
  /*creation du processus*/
  switch(fork()){
	case -1 : perror("echec creation fils");
		  exit(-1);
	case 0 	: for(i=1;i<NB_MAX;i++)
			printf("\t\t\t%4d Je suis le fils\n",i);
			exit(1);
	default	: for(i=1;i<NB_MAX;i++)
                        printf("%4d Je suis le pere\n",i);
			break;
	}
  return 0;
}

/*En faisant varier la valeur de la constante NB_MAX on constate que les processus
 * s'execute en parallèle mais l'affichage s'effectue dans le desordre
 */