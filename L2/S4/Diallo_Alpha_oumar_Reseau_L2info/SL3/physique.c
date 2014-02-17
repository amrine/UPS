#include <stdio.h>

#if defined(UNIX)

	#include <stdio.h>
	#include <stdlib.h>
	#include <unistd.h>
	#include <ctype.h>
	#include <netdb.h>
	#include <string.h>
	#include <sys/types.h>
	#include <sys/socket.h>
	#include <sys/select.h>
	#include <netinet/in.h>
	#include <arpa/inet.h>
	#include <errno.h>
	
#else
	
#include <winsock2.h>

#endif

#include <time.h>

/* Variables cachees */

/* le socket */
#ifndef UNIX
SOCKET	physique_socket;
#else
int physique_socket;
#endif
/* les timers */
/* timers[0] contient le nombre de timers */
int	physique_timers[100];

int	physique_initialise = 0;

/* proba de perte */
float	physique_proba_perte = 0;
/* proba d'erreurs */
float physique_proba_erreur = 0; 
/* nom de la machine destination */
char*	physique_destination = NULL;
short	physique_port_local = 2000;
short	physique_port_destination = 2001;



/******************************************************************************
 * Initialisation avec un taux de perte
 *                        taux d'erreurs et
 *                        parametres reseau
 */
void Initialisation(float proba_perte, float proba_erreur,
	unsigned short port_local, char* destination, unsigned short port_dest) {

	struct sockaddr_in adresse_locale;
	
	#ifndef UNIX
	WSADATA	wsaData;
	#endif

	physique_initialise = 1;

	physique_proba_perte = proba_perte;
	physique_proba_erreur = proba_erreur;
	physique_destination = strdup(destination);
	physique_port_local = port_local;
	physique_port_destination = port_dest;

	srand( (unsigned)time( NULL ) );
	
	#ifndef UNIX
	if (WSAStartup(0x202,&wsaData) == SOCKET_ERROR)
	{
		printf("WSAStartup() n'a pas fonctionne, erreur : %d\n", WSAGetLastError()) ;
		WSACleanup();
		exit(1);
	}
	#endif

	physique_socket = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP);
	if (physique_socket < 0)
	{
		#ifndef UNIX
		printf("socket() n'a pas fonctionne, erreur : %d\n", WSAGetLastError()) ;
		WSACleanup();
		#else
		perror("socket() n'a pas fonctionnÈe : ");
		#endif
		exit(1);
	}

	adresse_locale.sin_port        = htons(physique_port_local);
	adresse_locale.sin_family      = AF_INET;
	adresse_locale.sin_addr.s_addr = INADDR_ANY;

	if (bind(physique_socket, (struct sockaddr *)&adresse_locale, sizeof(adresse_locale)) < 0)
	{
		#ifndef UNIX
		printf("bind() n'a pas fonctionne, erreur : %d\n", WSAGetLastError()) ;
		WSACleanup();
		#else
		perror("Echec du bind : ");
		close(physique_socket);
		#endif
		exit(1);
	}

	physique_timers[0] = 0;
}

/******************************************************************************
 * Initialisation simple avec un taux de perte,
 *                               taux d'erreur,
 *                               sens de communication (si reception=1 cote
 *                               reception sinon cote emission)
 */
void InitialisationSimple(float proba_perte, float proba_erreur, int reception)
{
	unsigned short port_local;
	unsigned short port_distant;
#ifdef UNIX
	uid_t uid = getuid();
#else
	int uid = 1;
#endif
	if (reception) {
		port_local = (short) (uid % 60000 + 2000);
		port_distant = (short) (uid % 60000 + 3000);
	} else {
		port_local = (short) (uid % 60000 + 3000);
		port_distant = (short) (uid % 60000 + 2000);
	}
	
	printf("On utilise le port local %d et le port distant %d\n",
			port_local, port_distant);
	
	Initialisation(proba_perte, proba_erreur, port_local, "localhost", port_distant);
}


/* Attends un ÈvËnement et retourne soit 0 si un
paquet reÁu est disponible ou un numÈro de timer
si un timeout a ÈtÈ gÈnÈrÈ */
int Attendre() {
	fd_set read_fs;
	struct timeval timeout;
	struct timeval *ptimeout;
	int rep;
	int fini = 0;
	int i;
	int resultat = -1;
	int taille_fd_set;

	if (!physique_initialise)
	{
		printf("Attention, le canal n'Ètait pas initialisÈ\n");
		Initialisation(0.f, 0.f, 2000, "localhost", 2001);
	}

	while(!fini)
	{
		rep = 0;
		for(i=1; i < physique_timers[0]; i++)
		{
		    if(physique_timers[i]>0)
			    rep = i;
		}

		if (rep>0)
		{
		    timeout.tv_sec = 0;
		    timeout.tv_usec = 100000; /* 100000 us = 100 ms */
		    ptimeout = &timeout;
		}
		else 
		    ptimeout = NULL;

		FD_ZERO(&read_fs);
		FD_SET(physique_socket, &read_fs);
		
#ifndef UNIX
		taille_fd_set = 1;
#else
		taille_fd_set = physique_socket + 1;
#endif
		rep = select(taille_fd_set, &read_fs, NULL, NULL, ptimeout);
		switch (rep)
		{
			case -1:
				#ifndef UNIX
				printf("select() n'a pas fonctionne, erreur : %d\n", WSAGetLastError()) ;
				WSACleanup();
				#else
				perror("select() n'a pas fonctionnÈe : ");
				close(physique_socket);
				#endif
				exit(1);
			case 0:
			/* timeout ? */
				/* printf ("timeout\n", rep); */
				for(i=1; i < physique_timers[0]; i++)
				{
					if (physique_timers[i] > 0 && physique_timers[i] <= 100)
					{
						fini = 1;
						resultat = i;
						physique_timers[i] = -1;
					}
					else if (physique_timers[i] > 0) {
						/*printf ("decremente timer %d de 100 ms\n", i);*/
						physique_timers[i] -= 100;
					}
				}
				break;
			default: 
				/* reception possible */
				/* printf ("action de %d descripteur\n", rep); */
				if ( FD_ISSET( physique_socket, &read_fs ) )
				{
					fini = 1;
					resultat = 0;
				}
		}
	}

	return resultat;
}

/* recoit une trame - taille doit etre sizeof(trame_t) ou trame_t est votre type trame */
void DeCanal(void *trame, int taille) {
	int l_data;
	if (!physique_initialise)
	{
		printf("Attention, le canal n'Ètait pas initialisÈ\n");
		Initialisation(0.f, 0.f, 2000, "localhost", 2001);
	}

	l_data = recvfrom(physique_socket, (char *)trame, taille, 0, NULL, NULL);
	if (l_data < 0)
	{
		#ifndef UNIX
		printf("recvfrom() n'a pas fonctionne, erreur : %d\n", WSAGetLastError()) ;
		WSACleanup();
		#else
		perror("recvfrom() n'a pas fonctionnÈe : ");
		close(physique_socket);
		#endif
		exit(1);
	}
	printf("[couche physique] trame recue\n");
}

/* Envoie une trame
 * taille doit etre sizeof(trame_t) ou trame_t est votre type trame
 */
void VersCanal(void *trame, int taille) {
	
	struct hostent *host;
	struct sockaddr_in adresse_dest;
	int l_adr = sizeof(adresse_dest);
	int l_data;
        
	if (!physique_initialise)
	{
		printf("Attention, le canal n'Ètait pas initialisÈ\n");
		Initialisation(0.f, 0.f, 2001, "localhost", 2000);
	}
	
	/* perte ? */
	if(rand()/(float)RAND_MAX < physique_proba_perte) {
		printf("[couche physique] perte de trame\n");
		return;
	}
	
	/* copie de la trame (utile si generation d'une erreur) */
	void * new_trame = malloc(taille);
	memcpy(new_trame, trame, taille);
	
	
	/* erreur ? */
	if(rand()/(float)RAND_MAX < physique_proba_erreur) {
		printf("[couche physique] generation d'une erreur dans la trame\n");
		/* Si la structure de la trame suit l'ÈnoncÈ, l'octet 16 devrait
		   etre le premier octet du champs info... */
		*((char *)new_trame+16)='E';
	}
	
	
	#ifndef UNIX
	/* calcul de l'adresse de destination */
	/* testons voir si ce n'est pas une @ IP en notation dÈcimale (eg 141.115.20.90) */
	adresse_dest.sin_addr.s_addr = inet_addr(physique_destination);
	if (adresse_dest.sin_addr.s_addr == INADDR_NONE) {
	#endif
		/* ce doit Ítre un nom */
		host = gethostbyname(physique_destination);
		
		if (host == NULL) {
			#ifndef UNIX
			printf("gethostbyname() n'a pas fonctionne, erreur : %d\n", WSAGetLastError()) ;
			WSACleanup();
			#else
			perror("gethostbyname() n'a pas fonctionnÈe : ");
			close(physique_socket);
			#endif
			exit(1);
		}
		
		memcpy((void *)&(adresse_dest.sin_addr), (void *)host->h_addr, host->h_length);
	#ifndef UNIX
	}
	#endif

	adresse_dest.sin_port        = htons(physique_port_destination);
	adresse_dest.sin_family      = AF_INET;
	
	l_data = sendto(
		physique_socket,
		(char *) new_trame, taille, 0,
		(struct sockaddr *) &adresse_dest, l_adr);
	
	if (l_data < 0) {
		#ifndef UNIX
		printf("sendto() n'a pas fonctionne, erreur : %d\n", WSAGetLastError());
		WSACleanup();
		#else
		perror("sendto() n'a pas fonctionnÈe : ");
		close(physique_socket);
		#endif
		exit(1);
	}
	printf("[couche physique] trame envoyee\n");
}

/* dÈmarre le timer numÈro n (0 < n < 100) qui s'arrËte
aprËs ms millisecondes (ms doit Ítre un multiple de 100) */
void DepartCompteur(int n, int ms) {
	physique_timers[n] = ms;
	if (n+1 > physique_timers[0])
	{
		physique_timers[0] = n+1;
	}
}

/* arrÍte le timer numÈro n */
void ArreterCompteur(int n) {
	physique_timers[n] = -1;

	if (n+1 == physique_timers[0])
	{
		physique_timers[0] = n;
	}
}
