#define _POSIX_C_SOURCE 1
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "fonction.h"
#include <pthread.h>
#include <string.h>
#include <time.h>
#include <signal.h>
#define	OFLAGS	O_CREAT|O_WRONLY|O_APPEND
#define	MODE	S_IRUSR|S_IWUSR|S_IRGRP|S_IROTH

/* compteur d'utlisateur en ligne*/
int users=0;

/*descripteur du tube principal*/
int sortieTube;

/* utlisateur */
utilisateur client;

/*reponse envoyé par le serveur 
0 la requete ne s'est pas execute
1 la requete a ete execute
*/
int reponse=0;

void Erreur(char *Message, int retour){
	perror(Message);
	exit(retour);
}

/*si un signal SIGINT est emis on detruit tous les tubes creees
*/
void CTRL_C(int sig){
	remove("SERVER.fifo");
	remove(client.tubeDeTransmission);
	close(sortieTube);
}


char* creerTube(char nomTube[], int Mode){  
	int fd; /*descripteur du fichier log*/
	char *mesg; /*message d'acceuil du fichier log */
	time_t date; /*date et heure d'acces au serveur */
	date = time(NULL);
	
	mesg= malloc(sizeof(char)*TAILLE_MESSAGE);
	strcat(mesg,"Tube principal du serveur cree : date : ");
	strcat(mesg,ctime(&date)); 
	
	if(mkfifo(nomTube, Mode) != OK) Erreur("Creation du Tube ",ERR_CRE);
	fprintf(stdout,"Le tube de communictaion : %s est ouvert\n",nomTube);
	
	/*mis a jour du fichier log.txt*/
	if((fd=open("log.txt",OFLAGS,MODE))==ERR_OUV) Erreur("Ouverture Fichier log.txt ",ERR_OUV);
	write(fd,mesg,strlen(mesg));
	close(fd);
	
	/*liberation memoire*/
	free(mesg);
	return nomTube;
}


void boucleLectureDansTube(char nomTube[]){
  signal(SIGINT,CTRL_C);	
  while(1){
	if((sortieTube = open (nomTube, O_RDONLY)) == ERR_OUV) 
	Erreur("\nLecture dans le Tube ",ERR_OUV);
	if(read(sortieTube, &client,sizeof(struct utilisateur))){
	  printf("Reception : utilisateur %d \n",++users);
	  creation_thread();
	}
  }
}

void ecritureDansTube(char nomTubePrincipal[],utilisateur user){
	/*descripteur du tube principal*/
	int entreeTube;
	int buffer;
	int desc_rep;
	
	/*ouverture du tube de communication entre le client et le serveur */
	if((entreeTube = open (nomTubePrincipal, O_WRONLY)) == ERR_OUV) 
	Erreur("Ouverture en Ecriture du  Tube Principal : ",ERR_OUV);
	
	/*creation du tube necessaire a la transmission */
	if(mkfifo(user.tubeDeTransmission,0666) != OK) Erreur("Creation du Tube De Transmission ",ERR_CRE);
	
	/*envoi de l'identite du client et de la requete au serveur*/
	write(entreeTube, &user,sizeof(struct utilisateur));
	close(entreeTube);

	if((desc_rep = open (user.tubeDeTransmission, O_RDONLY)) == ERR_OUV) 
	Erreur("Lecture dans le Tube : ",ERR_OUV);
	read(desc_rep, &buffer,sizeof(int));
	close(desc_rep);
	
	/*destruction du tube de transmission cree par le client*/
	if(buffer == 1)
	remove(user.tubeDeTransmission);
}


void *traitement(void *arg)
{
    int desc_trans;
	int desc_log,cmpteRendu;
	
	char *update; /*message de mise ajour de la requete dans  log.txt */
	time_t date; /*date et heure */
	date = time(NULL);
	
	printf("\nResultat de la requete %s\n",client.requete) ;
	system(client.requete);
 

	/*transmission de la reponse a la requete du client dans le tube cree par l'utilisateur
	reponse est a 1 car la requete a ete execute */
	reponse=1;
	if((desc_trans = open (client.tubeDeTransmission, O_WRONLY)) == ERR_OUV) 
	Erreur("Ouverture en ecriture dans le Tube de Transmission : ",ERR_OUV);
	write(desc_trans,&reponse,sizeof(int));
	close(desc_trans);
	
	/*garde une trace de l'execution dans un fichier log */
	if((desc_log=open("log.txt",OFLAGS,MODE))==ERR_OUV) Erreur("Ouverture Fichier log.txt ",ERR_OUV);
	
	update= malloc(sizeof(char)*(TAILLE_MESSAGE*3));
	strcat(update,"Client");
	strcat(update," : tube cree : ");
	strcat(update,client.tubeDeTransmission);
	strcat(update," requete : ");
	strcat(update,client.requete);
	strcat(update," date : ");
	strcat(update,ctime(&date));

	write(desc_log,update,strlen(update));
	
	close(desc_log);
	/*liberation memoire*/
	free(update);
}

int creation_thread(void){
	pthread_t thread1;
    printf("Creation du thread charge du traitement\n");
	
	/*creation du thread de traitement */
    if (pthread_create(&thread1, NULL, traitement, NULL))
	Erreur("creation_thread ",ERR_CRE); 
	
	/*attente de la fin d'execution du thread creee*/
    if (pthread_join(thread1, NULL)) 
	Erreur("pthread_join ",ERR_JOIN);
    printf("\nFin execution du thread de traitement\n");
	
	/*terminaison du thread charge du traitement*/
    pthread_cancel(thread1);
	
    return EXIT_SUCCESS;
}

	

	
