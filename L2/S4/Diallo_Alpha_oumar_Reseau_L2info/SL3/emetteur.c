/************************************
 * UPS - L2 Informatique - Réseaux  *
 *                                  *
 * emetteur.c : programme principal *
 ***********************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "service_liaison.h" /* L_UNIT_DATA_req, etc. */
#include "physique.h" /* Initialisation "physique" */

/*
 * Parametres du programme :
 *   aucun en version simple (mode local)
 *   port_local, machine_destination, port_destination en version distribuee
 */
int main(int argc, char* argv[])
{
    unsigned char donnees[TAILLE_MAX]; /* Taille max des donnees : A DEFINIR */
    unsigned char adr_src[6] = {0,0,0,0,0,0};
    unsigned char adr_dst[6] = {1,1,1,1,1,1};
    FILE*	fich;
    int	lg, cpt = 0;

    /* Pour la version distribuee */
    // Initialisation(0.f, 0.f, (short) atoi(argv[1]), argv[2], (short) atoi(argv[3]));

    /* Pour la version simple */
    InitialisationSimple(0.1, 0.1, 0);

    /* Ouverture du fichier en lecture */
    fich = fopen("soleil.jpg", "r");
    if (fich == NULL)
    {
        printf("Fichier in.txt non present\n");
        return 1;
    }

    /* Tant qu'on n'a pas fini de lire le fichier */
    L_CONNECT_req(adr_src, adr_dst);
    while (!feof(fich))
    {

        /* Lecture du fichier */
        /* on lit au maximum TAILLE_MAX */
        /* fread(void *ptr, size_t size, size_t nbitems, FILE *stream); */
        lg = fread(donnees, 1, TAILLE_MAX, fich);
        /* Si on a lu au moins 1 caractere */
        if ( lg > 0 )
        {

            printf("\n[couche appli] Envoi numero %d\n",cpt);


            /* invoquer primitive liaison pour emission */

            L_TX_DATA_req(donnees, lg);
            cpt++;
        }
    }

    /* On envoie un message de fin */
    donnees[0] = '\a';

    /* emission du message de fin */
    printf("\n[couche appli] Envoi fin de transmission\n");

    L_DISCONNECT_req(adr_src, adr_dst);

    printf("[couche appli] Fin de transmission.\n");
    fclose(fich);
    return 0;
}

