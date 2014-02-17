#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <ctype.h>
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
  Programme client : le client est capable d'executer trois fonctions
  ->recupérer un fichier (service get)
  ->envoyer un fichier (service put)
  ->faire un test (service test)
*/
int main(int argc, char *argv[]) {
    /*
        sockfd -> socket du client pour se connecter sur le serveur
        portno -> numero de port utilisé
        serviceType -> type de service utilisé
    */
    int portno, sockfd, serviceType;
    char *service; /* the requested service */
    /*
        Contexte d'adressage
        serv_addr -> adresse IPv4 du serveur
        server -> serveur sur lequel on se connecte
    */
    struct sockaddr_in serv_addr;
    struct hostent *server;
    
    usage_client(argc, argv);
    
    /* which service is asked */
    service = string_to_upper(argv[1]);
    
    if(strcmp(service,"TEST") == 0) {
      usage_test(argc, argv);
      serviceType = TEST_SERVICE;
    }
    else if(strcmp(service,"PUT") == 0){
      usage_put(argc, argv);
      serviceType = PUT_SERVICE;
    }
    else if(strcmp(service,"GET") == 0){
      usage_get(argc, argv);
      serviceType = GET_SERVICE;
    }
    else
      usage_client(argc, argv);
      
    /* numero de port */
    portno = atoi(argv[3]);
    
    /* creation de la socket du client */
    sockfd = create_tcp_socket();
    
    /* adresse du serveur */
    server = gethostbyname(argv[2]);
    if (server == NULL) {
        fprintf(stderr,"ERROR no such host\n");
        exit(0);
    }

    /* Configuration du client*/
    bzero((char *) &serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char *)server->h_addr,
         (char *)&serv_addr.sin_addr.s_addr,
         server->h_length);
    serv_addr.sin_port = htons(portno);
    
    /* connexion au serveur, on envoie des requetes et on reçoit des reponses 
    du serveur sur sockfd */
    if (connect(sockfd,(struct sockaddr*)&serv_addr,sizeof(serv_addr)) < 0)
        error("ERROR connecting", 0);
    
    fprintf(stderr, "connecting to %s on port %d\n", 
            inet_ntoa(serv_addr.sin_addr), htons(serv_addr.sin_port));
    
    /* execution du service demandé */
    if(serviceType == TEST_SERVICE) {
      /* service test 
        envoie de la donnée au serveur
      */
      send_message(sockfd, argv[4]);
      
      /* reception message du serveur */
      fprintf(stdout, "the server answer is : %s\n", receive_message(sockfd));
    }
    else if(serviceType == GET_SERVICE) {
      /* service get http*/
      get_service(sockfd, argv[2], argc, argv);
    }
    else if(serviceType == PUT_SERVICE) {
      /* service put http */
      put_service(sockfd, argv[2], argv[4]);
    }
    
    /* fermeture de la connexion à la fin des échanges*/
    close(sockfd);
    
    return 0;
}
