#include "couche_liaison.h"
#include "physique.h"
#include<stdio.h>


/* Fonction pour construire une trame */
void construire_trame(char *a_s, char *a_d, char *info, int lg_info, Trame_t *trame){
 	 int i;
   for(i=0 ; i<6 ; i++){
      trame->adr_src[i]=a_s[i];
   }
   for(i=0 ; i<6 ; i++){
     trame->adr_dst[i]=a_d[i];
   }
   for(i=0 ; i<lg_info ; i++){
   trame->info[i]=info[i];
   }
   trame->lg_info=lg_info;
   trame->fcs=0;
   for (i=0 ; i<lg_info ; i++){
     trame->fcs=trame->fcs^trame->info[i];
   }
}


/* Fonction pour extraire une trame */
int extraire_trame(char *a_s, char *a_d, char *info, Trame_t *trame){
    int i;
    unsigned char fcs=0;
    for(i=0 ; i<6 ; i++){
		a_s[i]=trame->adr_src[i];
    }
    for(i=0 ; i<6 ; i++){
		a_d[i]=trame->adr_dst[i];
    }
    for(i=0 ; i<trame->lg_info; i++){
		info[i]=trame->info[i];
    }
    
    /* Calcul de FCS */
    for (i=0 ; i<trame->lg_info ; i++){
    fcs=fcs^trame->info[i];
    }
    if (fcs!=trame->fcs){
      printf(" [couche liaison ] erreur de somme de controle\n");
      return(-1);
    }
    return trame->lg_info;
}

