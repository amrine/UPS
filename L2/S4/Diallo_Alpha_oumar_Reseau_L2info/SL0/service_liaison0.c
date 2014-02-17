#include<stdio.h>
#include "service_liaison.h"
#include "physique.h"

/* Primitive pour transferer une unite de donnee mode non connecte */

void L_UNIT_DATA_req(char *a_s, char *a_d, char *info, int lg_info) {
      Trame_t trame;
      construire_trame(a_s, a_d, info, lg_info, &trame);
      trame.ctrl=DATA;      /* transfert de la PDU de donnees */
      VersCanal(&trame, sizeof(Trame_t)); /* emission de la trame */
}

/* Primitive pour recuperer une unite de donnees 
		renvoie la taille du message */

int L_UNIT_DATA_ind(char *a_s, char *a_d, char *info) {
      Trame_t trame;
      int lg_info=0;
	  	int event=Attendre();
		 	if (event == 0) {			/* Si la trame est disponible */
			    DeCanal(&trame,sizeof(Trame_t));	/* reception de la trame */	
		   		lg_info=extraire_trame(a_s, a_d, info, &trame);	    
	 	 	}
	  	return lg_info;
}
      
      
     
     
     
     
     
     
     
     
