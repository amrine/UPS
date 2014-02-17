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
#include <signal.h>

/*
int compteur=0;
struct sigaction new,old;

version signal 
void CTRL_C(int sig){
	compteur++;
	if(compteur != 10) signal(SIGINT,CTRL_C);
	printf("tour de boucle numero %d\n",compteur);
	
}

int main (int argc, char *argv[])
{
	signal(SIGINT,CTRL_C);	
	while(1);
	return 0;
}

version sigaction
void CTRL_C(int sig){
	printf("tour de boucle numero %d\n",++compteur);
	if(compteur == 10) sigaction(SIGINT,&old,NULL);
	
}

int main (int argc, char *argv[])
{
	//parametrage de la fonction a appele quand le signal est emis
	new.sa_handler = CTRL_C;
	new.sa_flags=0;
	sigemptyset(&new.sa_mask);
	sigaction(SIGINT,&new,&old);	
	while(1);
	return 0;
}

*/
int RECU=0;
void debut(){RECU=1;}

int main(){
	int F;
	printf("***Debut du Programme (PERE)***\n");
	signal(SIGUSR1,debut);
	switch(F=fork()){
		case -1 : perror("Erreur creation Fils\n");
				  exit(98);
		case  0 : printf("Fils demarre\n");
				  pause();
				  printf("je suis le fils je parle en premier\n");
				  printf("Fils termine\n");
				  exit(50);
		default : break;
	}
	printf("Pere poursuit\n");
	kill(F,SIGUSR1);
	printf("Retour de wait :%d\n",(int)wait(NULL));
	printf("je suis le pere je parle en seconde\n");
	printf("Pere termine\n");
	exit(1);
}
