#ifndef FONCTION_H
#define FONCTION_H

#define ERR_OUV -1
#define ERR_ECR -2
#define ERR_LEC -3
#define ERR_PARAM -4
#define ERR_CRE -5
#define ERR_JOIN -6
#define ERR_EXEC -7
#define OK 0
#define TAILLE_MESSAGE 256

typedef struct utilisateur utilisateur;
struct utilisateur{
  char tubeDeTransmission[TAILLE_MESSAGE];
  char requete[TAILLE_MESSAGE];
};

char* creerTube(char nomTube[], int Mode);
/*Mode 0600 droit acces precisant lecture et ecriture pour le proprietaire
	   0666 droit acces precisant lecture et ecriture pour tous
	   */
void Erreur(char *Message, int retour);
void ecritureDansTube(char nomTubePrincipal[],utilisateur client);
void boucleLectureDansTube(char nomTube[]);
int creation_thread(void);
void *traitement(void *arg);
#endif
