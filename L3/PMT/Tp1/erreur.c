#define _POSIX_C_SOURCE 1
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include "erreur.h"
void Erreur(char *Message, int retour){
	perror(Message);
	exit(retour);
}

