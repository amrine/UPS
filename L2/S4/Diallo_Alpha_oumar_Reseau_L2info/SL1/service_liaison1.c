#include<stdio.h>
#include "service_liaison.h"
#include "physique.h"

/* Primitive pour transferer une unite de donnee mode non connecte */
void L_UNIT_DATA_req(char *a_s, char *a_d, char *info, int lg_info) {
      Trame_t trame;
      Trame_t acq;
      int event;
			int continuer=1;
      construire_trame(a_s, a_d, info, lg_info, &trame);
      trame.ctrl=DATA;		 /* transfert de la PDU de donnees */
      while(continuer)
      {
          VersCanal(&trame, sizeof(Trame_t)); /* emission de la trame */
          event = Attendre();
          if(event==0)		/* Si la trame est disponible */
          {
              DeCanal(&acq, sizeof(Trame_t));	/* reception d'un ACK*/	
              if (acq.ctrl == ACK) /* si vrai on sort de la boucle */
                continuer=0;
          }
      }
}

/* Primitive pour recuperer une unite de donnees 
		renvoie la taille du message */
int L_UNIT_DATA_ind(char *a_s, char *a_d, char *info) {
      Trame_t trame;
      Trame_t acq;
      int lg_info=0;
      int event=Attendre();
	if(event==0) {	/* Si la trame est disponible */
	    DeCanal(&trame,sizeof(Trame_t));	/* reception de la trame */	
	    lg_info=extraire_trame(a_s, a_d, info, &trame);
	    construire_trame(a_s, a_d, info, lg_info, &trame);
	    acq.ctrl = ACK;
	    VersCanal(&acq, sizeof(Trame_t)); /* emission d'un ACK*/
	}
      return(lg_info);
}

