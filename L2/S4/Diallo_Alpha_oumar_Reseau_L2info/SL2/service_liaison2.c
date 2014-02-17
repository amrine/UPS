#include<stdio.h>
#include "service_liaison.h"
#include "physique.h"

int num_seq = 0;


/* Primitive pour transferer une unite de donnee mode non connecte */
void L_UNIT_DATA_req(char *a_s, char *a_d, char *info, int lg_info){
    Trame_t trame;
    Trame_t acq;
    int event;
    construire_trame(a_s, a_d, info, lg_info, &trame);
    trame.ctrl=DATA; /* transfert de la PDU de donnees */
    trame.num_seq=num_seq;
    int continuer=1;
    DepartCompteur(2,500);
    while(continuer)
    {
        VersCanal(&trame, sizeof(Trame_t)); /* emission de la trame*/
        DepartCompteur(1,100); /* demarrage du compteur */
        event=Attendre();			
        if (event==0) /* Si la trame est disponible */
        {
            ArreterCompteur(2);
            ArreterCompteur(1);
            DeCanal(&acq, sizeof(Trame_t)); /* reception d'un ACK*/
            if ((acq.num_seq == num_seq+1) && (acq.ctrl==ACK)) /* si vrai on sort de la boucle */
            {
                num_seq++;
                continuer=0;
            }
        }
        if (event==2)
        {
            ArreterCompteur(1);
            ArreterCompteur(2);
            printf("[LOST ACK]\n");
            break;
        }
    }
}

/* Primitive pour recuperer une unite de donnees 
		renvoie la taille du message */
int L_UNIT_DATA_ind(char *a_s, char *a_d, char *info)  				
{
    Trame_t trame;
    Trame_t acq;
    int lg_info=0;
    int event;
    int continuer;
    continuer = 1;
    while(continuer)
    {
        DepartCompteur(1,100);
        event=Attendre();
        if (event==0)		/* Si la trame est disponible */				
        {
            ArreterCompteur(1);
            DeCanal(&trame,sizeof(Trame_t)); /* reception de la trame */
            if (trame.ctrl==DATA)
            {
                lg_info=extraire_trame(a_s, a_d, info, &trame);
                if (lg_info!=-1) /* s'il n'y a pas d'erreur de controle */
                {
                    if (num_seq==trame.num_seq)
                    {
                        num_seq++;
			continuer = 0;
                    }
                }
                  construire_trame(a_d, a_s, info, lg_info, &acq);
		  acq.ctrl=ACK;
		  acq.num_seq=num_seq;
		  VersCanal(&acq,sizeof(Trame_t)); /* emission d'un ACK*/
            }
        }
    }
    return(lg_info);
}

