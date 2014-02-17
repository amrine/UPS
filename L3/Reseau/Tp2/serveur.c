
#include<stdio.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>

#define TAILLE_MAX 100
/* numero client sur le serveur */
int numClient =0;

void error(char *msg) {
	perror(msg);
	exit(1);
}

/* crée une socket d'écoute sur le port indiqué 
   retourne son descripteur 
*/
int cree_socket_ecoute(int port){
	int listen_fd; /*socket*/
	struct sockaddr_in serv_addr; /*adresse IPv4 d'ecoute*/
	
	/*creation du socket Ip */
	listen_fd = socket(AF_INET, SOCK_STREAM, 0);
	if (listen_fd< 0) error("ERROR opening socket");

	/*remplit l'adresse addr*/
	serv_addr.sin_family = AF_INET; /*adresse internet*/
	/* on accepte les connections sur toutes les adresses IP de la machine */
	serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	serv_addr.sin_port = htons(port); /* port: 2 octets en ordre de réseau */
	
	/* ancre listen_fd à l'adresse addr */
	if (bind(listen_fd, (struct sockaddr *) &serv_addr,sizeof(serv_addr)) < 0) error("ERROR on binding");

	/* transforme liste_fd en une socket d'écoute */
	if(listen(listen_fd,5) == -1) error("echec de listen");
	fprintf(stderr, "Serveur actif sur le port %i\n", port);
	
	return listen_fd;
}

/* boucle de réception des connections */
void boucle(int listen_fd)
{
  while (1) {
    int client;    /* socket connectée */
	int clilenght,n;
	char buffer[TAILLE_MAX];
	struct  sockaddr_in cli_addr;

	/*nombre octet lu et compteur*/
	int lu=0,i,cpt=0;

	clilenght = sizeof(cli_addr);
    /* attend une connection */
    client = accept(listen_fd,(struct sockaddr *) &cli_addr,(socklen_t*) &clilenght);
    if (client < 0) error("ERROR on accept");
	fprintf(stdout,"\nClient numero %d\n",++numClient);
    while( (n = read(client,buffer,TAILLE_MAX))){
	 write(client,buffer,n);
	 lu += n;
	}
	close(client);
	buffer[lu]='\0';
	for(i=0;i<lu;i++)  if(buffer[i] == 'a')  cpt++;
	fprintf(stdout,"Le nombre de a est %d son message est %s de longueur %d\n",cpt,buffer,lu);
  }
}

int main(int argc, char *argv[]) {
	int port;
	if(argc != 2) error("serveur : numero_port ");
	port = atoi(argv[1]);
	boucle(cree_socket_ecoute(port));
	return 0;
}

/*int main(int argc, char *argv[]) {
	int sockfd, newsockfd, portno, clilen;
	char buffer;
	struct sockaddr_in serv_addr, cli_addr;
	int n;
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) error("ERROR opening socket");
	portno = 7000;
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(portno);
	if (bind(sockfd, (struct sockaddr *) &serv_addr,sizeof(serv_addr)) < 0) error("ERROR on binding");
	listen(sockfd,5);
	clilen = sizeof(cli_addr);
	newsockfd = accept(sockfd,(struct sockaddr *) &cli_addr,(socklen_t*) &clilen);
	if (newsockfd < 0) error("ERROR on accept");
	while( (n = read(newsockfd,&buffer,1))) write(newsockfd,&buffer, 1);
	return 0;
}*/

/*
	int sockfd, newsockfd, portno, clilen;
	char buffer;
	struct sockaddr_in serv_addr, cli_addr;
	int n;
	
	if(argc != 2) error("serveur : numero_port ");
	portno = atoi(argv[1]);

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) error("ERROR opening socket");

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(portno);

	if (bind(sockfd, (struct sockaddr *) &serv_addr,sizeof(serv_addr)) < 0) error("ERROR on binding");
	listen(sockfd,5);
	clilen = sizeof(cli_addr);
	newsockfd = accept(sockfd,(struct sockaddr *) &cli_addr,(socklen_t*) &clilen);
	if (newsockfd < 0) error("ERROR on accept");

	while( (n = read(newsockfd,&buffer,1))) write(newsockfd,&buffer, 1);
	return 0;*/
