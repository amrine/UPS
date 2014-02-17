/*************************************
 * UPS - L2 Informatique - Réseaux   *
 *                                   *
 * recepteur.c : programme principal *
 ************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "physique.h" /* Initialisation "physique" */
#include "service_liaison.h" /* L_UNIT_DATA_ind */

/*
 * Parametres du programme :
 *   aucun en version simple (mode local)
 *   port_local, machine_destination, port_destination en version distribuee
 */
int main(int argc, char* argv[])
{
    unsigned char donnees[TAILLE_MAX]; /* Taille max des donnees : A DEFINIR */
    int i, lg_donnees,reponse;
    int cpt=0;
    int continuer = 1;
    FILE* fich;
    unsigned char adr_src[6], adr_dst[6];
    /* Pour la version distribuee */
    // Initialisation(0.f, 0.f, (short) atoi(argv[1]), argv[2], (short) atoi(argv[3]));

    /* Pour la version simple */
    InitialisationSimple(0.3, 0.3, 1);

    /* Ouverture du fichier en ecriture */
    fich = fopen("soleil_out.jpg", "w");
    if (fich == NULL)
    {
        printf("Fichier out.txt impossible a creer\n");
        return 1;
    }

    /* Tant qu'on n'a pas fini de recevoir */
    printf("Attente de connection\n");
    L_CONNECT_ind(adr_src, adr_dst);
    printf("generation de la reponse\n");
    reponse = L_CONNECT_cnf(adr_src, adr_dst);
    printf("envoie de la reponse\n");
    L_CONNECT_rep(adr_src, adr_dst, reponse);
    printf("out\n");
    while (continuer)
    {

        /* On reinitialise le tableau donnees */
        for (i = 0; i < TAILLE_MAX; i++)
            donnees[i] = '\0';

        /*  invoquer primitive liaison pour reception */

        lg_donnees =  L_TX_DATA_ind(donnees);

        /* On regarde si c'est le dernier message */
        if (lg_donnees==1 && donnees[0]=='\a')
        {
            printf("debug \n");
            L_DISCONNECT_ind(adr_src, adr_dst);
            /* Fermeture fichier et arret de la boucle */
            printf("[couche appli] Fin de reception.\n");
            continuer = 0;
            fclose(fich);
        }
        else
        {

            printf("[couche appli] Reception numero %d\n\n",cpt);

            /* Ecriture des donnees dans le fichier */
            /* size_t fwrite(const void *ptr, size_t size, size_t nbitems, FILE *stream); */
            fwrite(donnees, 1, lg_donnees, fich);
            fflush(fich);
            cpt++;
        }
    }
    return 0;
}

