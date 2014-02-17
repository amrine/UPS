#include <stdio.h>
#include "service_liaison.h"
#include "string.h"
#include "physique.h"
int num_seq = 0;

/* calcul de la sequence de controle */
int fcs(unsigned char info[TAILLE_MAX], unsigned char lg_info)
{
    int fcs,i;
    fcs = info[0];
    for (i=1; i<lg_info; i++)
    {
        fcs ^= (int)info[i];
    }
    return fcs;
}

/* Primitive de demande d'établissement d'une connexion */
void L_CONNECT_req(unsigned char adr_src[6], unsigned char adr_dst[6])
{
    Trame_t connect,rep;
    int i;
    connect.deb_trame = 0;
    for (i=0; i<6; i++)
    {
        connect.adr_dst[i]=adr_dst[i];
        connect.adr_src[i]=adr_src[i];
    }
    connect.ctrl = 0;
    connect.num_seq = 0;
    connect.lg_info = 0;
    connect.fin_trame = 0;
    while(1)
    {
        printf("envoi connection req\n");
        VersCanal(&connect, sizeof(connect));
        DepartCompteur(1,5);
        if (Attendre() == 0)
        {
            ArreterCompteur(1);
            DeCanal(&rep, sizeof(rep));
            if (rep.ctrl == 1)
            {
                printf("Connection establie\n");

                break;
            }

        }
    }
}

/* Primitive pour notifier d'une demande de connexion */
void L_CONNECT_ind(unsigned char adr_src[6], unsigned char adr_dst[6])
{
    Trame_t demande;
    int i;
    while(1)
    {
        DepartCompteur(1,5);
        if (Attendre()==0)
        {
            ArreterCompteur(1);
            DeCanal(&demande, sizeof(demande));
            if (demande.ctrl == 0)
            {
                printf("recue demande de connection\n");
                break;
            }


        }
    }
    for (i=0; i<6; i++)
    {
        adr_dst[i] = demande.adr_dst[i];
        adr_src[i] = demande.adr_src[i];
    }
}

/* Primitive de réponse à la demande de connexion */
void L_CONNECT_rep(unsigned char adr_src[6], unsigned char adr_dst[6], int reponse)
{
    Trame_t rep;
    int i;
    for (i=0; i<6; i++)
    {
        rep.adr_dst[i]=adr_dst[i];
        rep.adr_src[i]=adr_src[i];
    }
    rep.deb_trame = 0;
    rep.ctrl = reponse;
    rep.num_seq = 0;
    rep.lg_info = 0;
    rep.fin_trame = 0;
    VersCanal(&rep, sizeof(rep));

}

/* Primitive pour notifier la réponse à la demande de connexion */
int L_CONNECT_cnf(unsigned char adr_src[6], unsigned char adr_dst[6])
{
    return 1; /* 2 = refus */
}

/* Primitive pour tranférer une unité de données */
void L_TX_DATA_req(unsigned char info[TAILLE_MAX], int lg_info)
{
    int i;
    Trame_t trame,rep;
    trame.deb_trame = 0;
    trame.ctrl = 4;
    trame.num_seq = num_seq;
    trame.lg_info = lg_info;
    trame.fin_trame = 0;
    trame.fcs = fcs(info, lg_info);
    for(i=0; i<lg_info; i++)
    {
        trame.info[i]=info[i];
    }
    while(1)
    {
        VersCanal(&trame, sizeof(trame));
        DepartCompteur(1,5);
        if (Attendre()==0)
        {
            ArreterCompteur(1);
            DeCanal(&rep, sizeof(rep));
            if (rep.ctrl == 5)
            {
                printf("ACK REÇU\n");
            }
            if (rep.num_seq == num_seq)
            {
                num_seq++;
                break;
            }
        }
    }
}

/* Primitive pour recuperer une unite de donnee au sein d'une connexion*/
int L_TX_DATA_ind(unsigned char info[TAILLE_MAX])
{
    int i;
    Trame_t trame,ack;
    while(1)
    {
        DepartCompteur(1,5);
        if(Attendre()==0)
        {
            ArreterCompteur(1);
            DeCanal(&trame, sizeof(trame));
			if (trame.ctrl == 3)
				break;
            if (fcs(trame.info, trame.lg_info)==trame.fcs)
            {
                ack.ctrl=5;
                ack.num_seq=trame.num_seq;
                ack.lg_info=0;
                ack.fcs=0;
                ack.fin_trame=0;
                printf("ACK envoyer\n");
                VersCanal(&ack,sizeof(ack));
                if(trame.num_seq == num_seq)
                {
                    num_seq++;
                    break;
                }
            }
        }
    }
    for(i=0; i<TAILLE_MAX; i++)
    {
        info[i] = trame.info[i];
    }
    return trame.lg_info;
}

/* Primitive pour mettre fin à une connexion */
void L_DISCONNECT_req(unsigned char adr_src[6], unsigned char adr_dst[6])
{
    int i,attendre;
    Trame_t disc,rep;
    disc.deb_trame = 0;
    disc.info[0]='\a';
    disc.ctrl = 3;
    disc.num_seq = num_seq;
    disc.lg_info = 1;
    disc.fin_trame = 0;
    for (i=0; i<6; i++)
    {
        disc.adr_dst[i]=adr_dst[i];
        disc.adr_src[i]=adr_src[i];
    }
    DepartCompteur(2,10000);
    while(1)
    {
        DepartCompteur(1,5);
        VersCanal(&disc, sizeof(disc));
        attendre = Attendre();
        if (attendre==0)
        {
            DeCanal(&rep, sizeof(rep));
            if (rep.ctrl == 5)
            {
                printf("ACK reçu\n");
            }
            if (rep.num_seq == num_seq)
            {
                ArreterCompteur(1);
		ArreterCompteur(2);
                break;
            }
        }
        else if (attendre==2)
        {
	    ArreterCompteur(1);
            ArreterCompteur(2);
            printf("Timeout quit\n");
            break;
        }
    }

}

/*Primitive de notification de fermeture de connexion */
void L_DISCONNECT_ind(unsigned char adr_src[6], unsigned char adr_dst[6])
{
    Trame_t ack;
    ack.deb_trame = 0;
    ack.ctrl = 5;
    ack.num_seq = num_seq;
    ack.lg_info = 0;
    ack.fin_trame = 0;
    VersCanal(&ack, sizeof(ack));
}


