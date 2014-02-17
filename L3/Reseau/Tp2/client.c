
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<netdb.h>

void error(char *msg) {
	perror(msg);
	exit(0);
}

/*argv1 = numero de port argv2=nom serveur */
int main(int argc, char *argv[]) {
	/* numero de port */
	int portno;
	int sockfd;
	struct sockaddr_in serv_addr;
	/* seveur sur lequel on se connecte */
	struct hostent *server;
	/*message a envoyer */
	char msg[100];
	/*longTotalMsg nbOcet lus*/
	int longTotalMsg, nbOctet;
	char *buffer=malloc(sizeof(char)*(sizeof(msg)+1));
	
	if(argc != 3) error("client : numero_port nom_du_serveur");
	portno = atoi(argv[1]);
	printf("\nEnter le message a envoyer : ");
	/*capturer la chaine sur la sortie standard*/
	gets(msg);
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) error("ERROR opening socket");
	server = gethostbyname(argv[2]);
	if (server == NULL) error("ERROR, no such host");
	serv_addr.sin_family = AF_INET;
	memcpy(&serv_addr.sin_addr, server->h_addr_list[0], server->h_length);
	serv_addr.sin_port = htons(portno);
	if (connect(sockfd,(struct sockaddr*)&serv_addr,sizeof(serv_addr)) < 0) error("ERROR connecting");
	write(sockfd,msg,strlen(msg));
	longTotalMsg=0;
	while(longTotalMsg < strlen(msg)){
		nbOctet = read(sockfd,&buffer[longTotalMsg],strlen(msg));
		longTotalMsg += nbOctet;
	}
	/* we receive an array of characters, so it must be converted
	to a string by adding a \0 at the end */
	buffer[strlen(msg)]='\0';
	printf("%s\n", buffer);
	return 0;
}



/*int main(int argc, char *argv[]) {
	int portno;
	int sockfd;
	struct sockaddr_in serv_addr;
	struct hostent *server;
	char *msg="Message d'exemple";
	char *buffer=malloc(sizeof(char)*(strlen(msg)+1));
	portno = 7;
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) error("ERROR opening socket");
	server = gethostbyname("marine.edu.ups-tlse.fr");
	if (server == NULL) error("ERROR, no such host");
	serv_addr.sin_family = AF_INET;
	memcpy(&serv_addr.sin_addr, server->h_addr_list[0], server->h_length);
	serv_addr.sin_port = htons(portno);
	if (connect(sockfd,(struct sockaddr*)&serv_addr,sizeof(serv_addr)) < 0) error("ERROR connecting");
	write(sockfd,msg,strlen(msg));
	read(sockfd,buffer,strlen(msg));
	we receive an array of characters, so it must be converted
	to a string by adding a \0 at the end 
	buffer[strlen(msg)]='\0';
	printf("%s\n", buffer);
	return 0;
}*/
