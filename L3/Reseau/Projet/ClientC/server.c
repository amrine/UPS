#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <time.h>
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
  Programme serveur : le serveur prend en paramètre un numéro de port. Le 
  serveur est de type multiclients parallèle. Le processus principal à l'écoute 
  des demandes génère un processus par client. Les deux processus père et fils 
  ferme la socket pour qu’elle soit complètement libérée.
*/
int main(int argc, char *argv[]) {
    /*
        sockfd -> socket d'ecoute
        newsockfd -> socket du client connecté
        portno -> numero de port utilisé
    */
    int sockfd, newsockfd, portno, pid;
    socklen_t clilen;
    time_t ticks;
    /*
        Contexte d'adressage
        serv_addr -> adresse IPv4 d'ecoute du serveur
        cli_addr -> adresse IPv4 du client
    */
    struct sockaddr_in serv_addr, cli_addr;
    
    /* numero de port */
    if(argc != 2) {
      fprintf(stderr, "USAGE %s: port\n\tport: the port number. ex: 7000\n", 
          argv[0]);
      exit(EXIT_FAILURE);
    }
    portno = atoi(argv[1]);
    
    /* creation de la socket d'ecoute du serveur*/
    sockfd = create_tcp_socket();
    
    ticks = time(NULL);
    printf("%.24s\r\n", ctime(&ticks));
    printf("the socket %d is open on mode TCP/IP\n", sockfd);

    /* configuration */
    serv_addr.sin_family = AF_INET; /* Protocol familial (Ip)*/
    serv_addr.sin_addr.s_addr = INADDR_ANY; /* Adresse IP automatique*/
    serv_addr.sin_port = htons(portno); /* Listage du port (2 octets)*/
    
    /* Association de la socket à la configuration*/
    if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0)
        error("ERROR on binding", 1);

    /* Mise en ecoute de la socket (établir la connexion), backlog = 5 
        (nombre maximal de connexions pouvant être mise en attente)
        Demarrage du listage (mode server)
    */
    if (listen(sockfd,5) < 0)
        error("ERROR on listening", 1);
    clilen = sizeof(cli_addr);
    
    /* Attente de connexion d'un client*/
    printf("server's running on port %d...\n\n", portno);
    
    /* Acceptation de la connexion d'un client, on peut communiquer avec lui sur
      newsockfd, on lit les requetes du client sur cette socket et on renvoie 
      les reponses dessus, lorsque le serveur se termine, on ferme la connexion
    */
    while(1){
      newsockfd = accept(sockfd, 
                        (struct sockaddr *) &cli_addr, 
                        &clilen);
      
      if (newsockfd < 0)
          error("ERROR on accept", 1);
      
      /* creation du processus fils chargé d'executer la requête du client */
      pid = fork();
      if(pid < 0)
          error("ERROR on fork", 1);
      if(pid == 0) {
        close(sockfd);
        
        printf("a client's connected on socket %d of %s:%d\n", newsockfd, 
               inet_ntoa(cli_addr.sin_addr), htons(cli_addr.sin_port));
        
        /* execution service test */
        test_service(newsockfd);
        exit(0);
      }
      else {
        /* fermeture de la connexion du client */
        close(newsockfd);
      }
    }/* fin du while*/
    /* fermeture du serveur */
    close(sockfd);
    return 0;/* on n'arrive jamais ici*/
}
