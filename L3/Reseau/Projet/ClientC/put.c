#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <fcntl.h>
#include <sys/stat.h>
#include "reseau.h"

/*
    Auteur : Diallo Alpha Oumar Binta
    Numéro étudiant : 21007631
    Groupe 3.1
    Annee scolaire : 2012-2013
    Projet de Réseau
    Professeur chargé du cours : Rahim KACIMI
*/

/*
  SERVICE put
  cette fonction envoie le fichier file_to_send sur l'hôte distant host par la
  socket sockfd, puis affiche à l'écran la réponse du serveur (entête et 
  résulats).
*/
void put_service(int sockfd, char *host, char *file_to_send)
{
  char *query; /* requete envoyée au serveur*/
  /* buffer utilisé pour la reception de la réponse du serveur*/
  char buffer[BLOCK_SIZE]; 
  /* buffer utilisé pour l'envoie des données au serveur*/
  unsigned char donnees[BLOCK_SIZE];
  int bytesRead, bytesSent; /* nombre d'octets lus*/
  int header = 0; /* detection debut du contenu html*/
  int detectionFailed = 1;
  char *content; /*contenu html, pour la reception*/
  FILE* file;/*fichier à envoyer */

  if((file = fopen(file_to_send, "rb")) == NULL)
  	error("ERROR opening file", -1);
  /* creation de la requete */
  query = http_put_query(host, file_to_send);
  fprintf(stderr, "The query is:\n<<START>>\n%s<<END>>\n", query);
  /* on envoie la requête au serveur */
  send_message(sockfd, query);
  while (!feof(file)) {
    /* Lecture du fichier */
    /* on lit au maximum BLOCK_SIZE */
    /* fread(void *ptr, size_t size, size_t nbitems, FILE *stream); */
    bytesSent = fread(donnees, 1, BLOCK_SIZE, file);
    /* Si on a lu au moins 1 caractere */
    if ( bytesSent > 0 ){
        write(sockfd, donnees, bytesSent );
    }
  }
  fclose(file);
  /* reception de la page (reponse du serveur)*/
  memset(buffer, 0, sizeof(buffer)); /* intialisation du buffer*/
  do{
    bytesRead = readn(sockfd, buffer, BLOCK_SIZE);
    if(header == 0){
      /* Sous certaines conditions, cela ne fonctionnera pas.
       * Si le \r\n\r\n partie est découpée en deux messages
       * Il ne pourra pas détecter le début du contenu HTML
       * 
       */
      /* recherche de la premiere occurence*/
      content = strstr(buffer, "\r\n\r\n");
      if(content != NULL){
        *content = 0x00;
        content+=4;
        header = 1;
        
        /*
          si le second paramètre est specifié pour sauvegarder dans un fichier
          la page, on affiche l'entête puis on sauvegarde les données renvoyés
          dans le fichier.
          1 -> stdout -> ecran (write)
        */
        if(detectionFailed != 0)
          fprintf(stderr,"<<START server header>>\n");
        write(1, buffer, strlen(buffer));
        fprintf(stderr,"\n<<END server header>>\n");
        write(1, content,bytesRead-strlen(buffer)-4);
      }
      else{
        if(detectionFailed)
          fprintf(stderr,"<<START server header>>\n");
        detectionFailed = 0;
        write(1, buffer, bytesRead);
      }
    }
    else
      write(1, buffer, bytesRead);
  }while(bytesRead != 0);
}

/*
  cette fonction retourne une chaine de caractère (requete de type put http), 
  elle prend en paramètre l'hôte distant host et le nom de la page à envoyer
*/
char *http_put_query(char *host, char *page)
{
  char *query;
  char *desiredPage = page;
  char *temp = "PUT /%s HTTP/1.0\r\nHost: %s\r\nConnection: Close\r\nUser-Agent: %s\r\nContent-Length: %d\r\n\r\n";
  
  if(desiredPage[0] == '/'){
    desiredPage = desiredPage + 1;
    fprintf(stderr,"Removing leading \"/\", converting %s to %s\n",
            page, desiredPage);
  }
  
  /* -6 = %s %s %s dans temp plus le caractere de fin \0 */
  query = (char *)malloc(
            strlen(host)+strlen(desiredPage)+strlen(USERAGENT)+strlen(temp)-6);
  
  sprintf(query, temp, desiredPage, host, USERAGENT, get_file_size(page));
  return query;
}
