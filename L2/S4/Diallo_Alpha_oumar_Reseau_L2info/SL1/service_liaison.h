#include "couche_liaison.h"

/* Mode non connecte ____________________________________________________________*/

/* Primitive pour tranférer une unité de données */
void L_UNIT_DATA_req(char *a_s, char *a_d, char *info, int lg_info);

/* Primitive pour récupérer une unité de données */
int L_UNIT_DATA_ind(char *a_s, char *a_d, char *info);

/* ______________________________________________________________________________*/  



/* Mode connecte ________________________________________________________________*/

/* Primitive de demande d'établissement d'une connexion */
void L_CONNECT_req(unsigned char adr_src[6], unsigned char adr_dst[6]);

/* Primitive pour notifier d'une demande de connexion */
void L_CONNECT_ind(unsigned char adr_src[6], unsigned char adr_dst[6]);

/* Primitive de réponse à la demande de connexion */
void L_CONNECT_rep(unsigned char adr_src[6], unsigned char adr_dst[6], int reponse);

/* Primitive pour notifier la réponse à la demande de connexion */
int L_CONNECT_cnf(unsigned char adr_src[6], unsigned char adr_dst[6]);

/* Primitive pour tranférer une unité de données */
void L_TX_DATA_req(unsigned char info[TAILLE_MAX], int lg_info);

/* Primitive pour mettre fin à une connexion */
void L_DISCONNECT_req(unsigned char adr_src[6], unsigned char adr_dst[6]);

/* Primitive pour recuperer une unite de donnee au sein d'une connexion*/
int L_TX_DATA_ind(unsigned char info[TAILLE_MAX]);

/*Primitive de notification de fermeture de connexion */
void L_DISCONNECT_ind(unsigned char adr_src[6], unsigned char adr_dst[6]);

/* ______________________________________________________________________________*/

