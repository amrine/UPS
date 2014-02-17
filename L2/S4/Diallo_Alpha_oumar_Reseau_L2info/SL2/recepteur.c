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
#define TAILLE_MAX 82
/*
 * Parametres du programme :
 *   aucun en version simple (mode local)
 *   port_local, machine_destination, port_destination en version distribuee
 */
int main(int argc, char* argv[])
{
    char donnees[TAILLE_MAX]; /* Taille max des donnees : A DEFINIR */
    int i, lg_donnees;
    int cpt=0;
    int continuer = 1;
    FILE* fich;
    char a_s[6];
    char a_d[6];

            
    /* Pour la version distribuee */ 
    //Initialisation(0.f, 0.f, (short) atoi(argv[1]), argv[2], (short) atoi(argv[3]));
    
    /* Pour la version simple */
    InitialisationSimple(0.f, 0.f, 1);

    /* Ouverture du fichier en ecriture */
    fich = fopen("outsoleil.jpg", "w");
    if (fich == NULL) {
        printf("Fichier out.txt impossible a creer\n");
        return 1;
    }
	
    /* Tant qu'on n'a pas fini de recevoir */
    while (continuer) {

        /* On reinitialise le tableau donnees */
        for (i = 0; i < TAILLE_MAX; i++)
            donnees[i] = '\0';
   
       lg_donnees=L_UNIT_DATA_ind(a_s, a_d, donnees);
       printf("Longueur de donnees %d \n", lg_donnees);
                
        /* On regarde si c'est le dernier message */
        if (lg_donnees==1 && donnees[0]=='\a') {
            
            /* Fermeture fichier et arret de la boucle */
            printf("[couche appli] Fin de reception.\n");
            continuer = 0;
            fclose(fich);
        } else {
            
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

