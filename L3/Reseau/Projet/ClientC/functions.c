#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
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
  SERVICE test 
  cette fonction est appélé au niveau du serveur quand un client lui envoie
  un message, le serveur affiche le message envoyé et renvoie une reponse au 
  client sur la socket newsockfd, le client affiche la reponse du serveur, 
  puis se termine
*/
void test_service(int newsockfd){
  /* reception du message du client */   
  printf("Le message du client est : %s\n", receive_message(newsockfd));
  /* envoie de la reponse*/
  send_message(newsockfd, "j'ai recu ton message (server respond)");
}
/*
  Cette fonction renvoie la taille du fichier filename
*/
long get_file_size(char *fileName)
{
  struct stat file;
  return (stat(fileName,&file)?0:file.st_size);
}
/*
  readn lit jusqu'à n octets depuis le descripteur de fichier fd dans le buffer
  pointé par ptr.
  readn renvoie le nombre d'octets lus (0 en fin de fichier) et un nombre
  inférieure à 0 en cas d'erreur
*/
int readn(int fd, char *ptr, int n){
  int nl, nr;

  nl = n;
  while ( nl > 0 ) {
    nr = read(fd,ptr,nl);
    if (nr < 0 )
      return nr;     /*erreur de reception*/
    else if ( nr == 0 )
      break;
    nl -= nr;
    ptr += nr;
  }
  
  *ptr = 0x00;
  
  return (n-nl);
}
/*
  cette fonction reçoit un message sur fd
  fd -> identificateur de la socket sur laquelle on reçoit le message
*/
char *receive_message(int fd) {
  static char buffer[BLOCK_SIZE];
  int n, bytesRead = 0;
  
  while((n = read(fd, buffer, BLOCK_SIZE)) > -1){
    bytesRead += n;
    if(n != BLOCK_SIZE) break;
  }
  buffer[bytesRead] = '\0';
  
  return buffer;
}
/*
  cette fonction envoie un message sur fd
  fd -> identificateur de la socket sur laquelle on envoie le message
  message -> le message à envoyer
*/
void send_message(int fd, char *message) {
  if(write(fd, message, strlen(message)) == -1)
    error("Can't send query", -1);
}
/*
  affiche le message d'erreur et quitte le programme avec le code 
  de retour indiqué
*/
void error(char *msg, int status) {
    perror(msg);
    exit(status);
}
/*
  cette fonction retourne la chaine de caractères en majuscule
*/
char* string_to_upper(char *chaine)
{
    int i = 0;
    for (i = 0; chaine[i] != '\0'; i ++)
    {
        if (chaine[i]  >= 'a' &&  chaine[i] <= 'z')
         chaine[i] -=  'a' - 'A';
    }
    return chaine;
}
/*
  cette fonction crée une socket et retourne son identificateur
*/
int create_tcp_socket()
{
  int sock;
  
  if((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    error("ERROR opening socket", 0);
  
  return sock;
}
/*
  cette fonction affiche la specification du client
  on quitte le programme si les conditions ne sont pas remplies
*/
void usage_client(int argc, char *argv[])
{
  if(argc < 4) {
    fprintf(stderr, "USAGE %s: service adress port\n\
  \tservice: the requested service. ex: test, get, put\n\
  \tport: the port number. ex: 7000\n", argv[0]);
    exit(EXIT_FAILURE);
  }
}
/*
  cette fonction affiche la specification du service test
  on quitte le programme si les conditions ne sont pas remplies
*/
void usage_test(int argc, char *argv[])
{
  
  if(argc != 5) {
    fprintf(stderr, "USAGE %s: test adress port \"message\"\n\
  \tadress: the server adress. ex: localhost, 198.168.0.1\n\
  \tport: the port number. ex: 7000\n\
  \tmessage: the message to send. ex: \"coucou\"\n", argv[0]);
    exit(EXIT_FAILURE);
  }
}
/*
  cette fonction affiche la specification du service put
  on quitte le programme si les conditions ne sont pas remplies
*/
void usage_put(int argc, char *argv[])
{
  if(argc != 5) {
    fprintf(stderr, "USAGE %s: put adress port file\n\
  \tadress: the server adress. ex: localhost, 198.168.0.1\n\
  \tport: the port number. ex: 7000\n\
  \tfile: the file to upload. ex: bidule.jpg\n", argv[0]);
    exit(EXIT_FAILURE);
  }
}
/*
  cette fonction affiche la specification du service get
  on quitte le programme si les conditions ne sont pas remplies
*/
void usage_get(int argc, char *argv[])
{
  if(argc < 4 || argc > 6) {
    fprintf(stderr, "USAGE %s: get adress port file1 [file2]\n\
  \tadress: the server adress. ex: localhost, 198.168.0.1\n\
  \tport: the port number. ex: 7000\n\
  \tfile1: the file to retrieve. ex: machin.txt, default: /\n\
  \t[file2]: the new name of file1. ex: download.txt\n", argv[0]);
    exit(EXIT_FAILURE);
  }
}
