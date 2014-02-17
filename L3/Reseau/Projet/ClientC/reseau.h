#ifndef RESEAU_H
#define RESEAU_H


#define BLOCK_SIZE 1024

#define TEST_SERVICE 0
#define PUT_SERVICE 1
#define GET_SERVICE 2
#define INDEX "/"
#define USERAGENT "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4"

/*
    Auteur : Diallo Alpha Oumar Binta
    Numéro étudiant : 21007631
    Groupe 3.1
    Annee scolaire : 2012-2013
    Projet de Réseau
    Professeur chargé du cours : Rahim KACIMI
*/

/*
  Cette fonction renvoie la taille du fichier fileName
*/
long get_file_size(char *fileName);
/*
  readn lit jusqu'à n octets depuis le descripteur de fichier fd dans le buffer
  pointé par ptr.
  readn renvoie le nombre d'octets lus (0 en fin de fichier) et un nombre
  inférieure à 0 en cas d'erreur
*/
int readn(int fd, char *ptr, int n);
/*
  cette fonction reçoit un message sur fd
  fd -> identificateur de la socket sur laquelle on reçoit le message
*/
char *receive_message(int fd);
/*
  cette fonction envoie un message sur fd
  fd -> identificateur de la socket sur laquelle on envoie le message
  message -> le message à envoyer
*/
void send_message(int fd, char *message);
/*
  affiche le message d'erreur et quitte le programme avec le code de retour 
  indiqué
*/
void error(char *msg, int status);
/*
  cette fonction retourne la chaine de caractères en majuscule
*/
char* string_to_upper(char *chaine);
/*
  cette fonction crée une socket et retourne son identificateur
*/
int create_tcp_socket();
/*
  cette fonction affiche la specification du client
  on quitte le programme si les conditions ne sont pas remplies
*/
void usage_client(int argc, char *argv[]);
/*
  cette fonction affiche la specification du service test
  on quitte le programme si les conditions ne sont pas remplies
*/
void usage_test(int argc, char *argv[]);
/*
  cette fonction affiche la specification du service put
  on quitte le programme si les conditions ne sont pas remplies
*/
void usage_put(int argc, char *argv[]);
/*
  cette fonction affiche la specification du service get
  on quitte le programme si les conditions ne sont pas remplies
*/
void usage_get(int argc, char *argv[]);
/*
  SERVICE test 
  cette fonction est appélé au niveau du serveur quand un client lui envoie 
  un message, le serveur affiche le message envoyé et renvoie une reponse au 
  client sur la socket newsockfd, le client affiche la reponse du serveur, 
  puis se termine
*/
void test_service(int newsockfd);
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
void get_service(int sockfd, char *host, int argc, char *argv[]);
/*
  cette fonction retourne une chaine de caractère (requete de type get http), 
  elle prend en paramètre l'hôte distant host et le nom de la page à telecharger
*/
char *http_get_query(char *host, char *page);
/*
  SERVICE put
  cette fonction envoie le fichier file_to_send sur l'hôte distant host par la
  socket sockfd, puis affiche à l'écran la réponse du serveur (entête et 
  résulats).
*/
void put_service(int sockfd, char *host, char *file_to_send);
/*
  cette fonction retourne une chaine de caractère (requete de type put http), 
  elle prend en paramètre l'hôte distant host et le nom de la page à envoyer
*/
char *http_put_query(char *host, char *page);

#endif
