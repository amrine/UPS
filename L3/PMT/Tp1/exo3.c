#define _POSIX_C_SOURCE 1
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "erreur.h"

int main(){
	int CR;
	switch(fork()){
		case -1: Erreur("Creation fils",-1);
		case  0: execlp("date","date",NULL);
			 Erreur("Execution commande fils date",1);
		default: /*attente de la fin du fils*/
			 wait(&CR);
			 execlp("ps","ps","-al",NULL);
			 Erreur("Execution commande pere ps",2);
	}
	return 0;
}

