
typedef struct {           /* structure de la trame de l'emetteur */
    unsigned char deb_trame;		/* Octet marquant le debut de la trame */
    unsigned char adr_src[6];		/* Identification du destinataire de la trame */
    unsigned char adr_dst[6];		/* Identification de l'emetteur de la trame */
    unsigned char ctrl;		/* Type de PDU */
    unsigned char num_seq;		/* Numero de sequence de la PDU */
    unsigned char lg_info;		/* Longueur du champ info */
    unsigned char info[82];		/* Données */
    unsigned char fcs;		/* Sequence de controle */
    unsigned char fin_trame;		/* Octet marquant la fin de la PDU */
}Trame_t;


		
#define CON_REQ 0		/*demande d'etablissement de connexion */
#define CON_ACCEPT 1		/* acceptation de connexion */
#define CON_REFUSE 2		/* refus d'etablissement de connexion */
#define CON_CLOSE 3		/* Demande de deconnexion */
#define DATA 4			/* Transfert d'une PDU données */
#define ACK 5			/* Accuse de reception positif */
#define NACK 6			/* Accuse de reception negatif */
#define OTHER 7			/* Autres */
    
/* Nombre maximum de retranmission */
#define MAX_RETRANSMISSION 10
/* MTU de la couche liaison */
#define MTU 82
#define TAILLE_MAX 82

void construire_trame(char *a_s, char *a_d, char *info, int lg_info, Trame_t *trame);

int extraire_trame(char *a_s, char *a_d, char *info, Trame_t *trame);

