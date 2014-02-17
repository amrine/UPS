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
  SERVICE get
  cette fonction lorsqu'elle est utilisée avec :
    ->deux noms de fichiers différents sauvegarde le contenu du fichier 
      téléchargédans le second nom et affiche à l'ecran l'entête renvoyée par 
      le serveur
    ->un nom de fichier affiche à l'ecran ce fichier
  le nombre de fichiers est contenu dans la variable argc et les noms de 
  fichiers dans argv.
  La requête est envoyé au serveur distant host par la socket sockfd, les 
  résultats de la requête sont lus sur ce dernier.
*/
void get_service(int sockfd, char *host, int argc, char *argv[])
{
  char *query; /* requete envoyée au serveur*/
  char *page; /* page demandé*/
  char buffer[BLOCK_SIZE]; /* buffer utilisé pour le transport*/
  int bytesRead; /* nombre d'octets lus*/
  int header = 0; /* detection debut du contenu html*/
  int pageCreated = 0, detectionFailed = 1;
  char *content; /* contenu html */
  FILE *file;
  
  switch(argc){
    case 6 : page = argv[4];
             if((file = fopen(argv[5],"wb")) == NULL)
               error("ERROR can't open file", -1);
             pageCreated = 1;
             break;
    case 5 : page = argv[4];
             break;
    default : page = INDEX; /* argc = 4*/
             break;
  }
  
  /* creation de la requete */
  query = http_get_query(host, page);
  fprintf(stderr, "The query is:\n<<START>>\n%s<<END>>\n", query);
  /* on envoie la requete au serveur */
  send_message(sockfd, query);
  
  /* reception de la page*/
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
        if(pageCreated){
          if(detectionFailed != 0)
            fprintf(stderr,"<<START server header>>\n");
          write(1, buffer, strlen(buffer));
          fprintf(stderr,"\n<<END server header>>\n");
          fwrite(content, 1, bytesRead-strlen(buffer)-4, file);
        }
        else
          write(1, content, bytesRead-strlen(buffer)-4);
      }
      else if(pageCreated){
        if(detectionFailed)
          fprintf(stderr,"<<START server header>>\n");
        detectionFailed = 0;
        write(1, buffer, bytesRead);
      }
    }
    else if(pageCreated)
      fwrite(buffer, 1, bytesRead, file);
    else
      write(1, buffer, bytesRead);
      
  }while(bytesRead != 0);
  
  if(pageCreated)
    fclose(file);   
}

/*
  cette fonction retourne une chaine de caractère (requete de type get http), 
  elle prend en paramètre l'hôte distant host et le nom de la page à telecharger
*/
char *http_get_query(char *host, char *page)
{
  char *query;
  char *desiredPage = page;
  char *temp = "GET /%s HTTP/1.0\r\nHost: %s\r\nConnection: Close\r\nAccept: text/html,image/gif, image/x-xbitmap, image/jpeg,image/pjpeg, image/png, */*\r\nUser-Agent: %s\r\n\r\n";
  
  if(desiredPage[0] == '/'){
    desiredPage = desiredPage + 1;
    fprintf(stderr,"Removing leading \"/\", converting %s to %s\n",
            page, desiredPage);
  }
  
  /* -5 = %s %s %s dans temp plus le caractere de fin \0 */
  query = (char *)malloc(
            strlen(host)+strlen(desiredPage)+strlen(USERAGENT)+strlen(temp)-5);
  
  sprintf(query, temp, desiredPage, host, USERAGENT);
  return query;
}
