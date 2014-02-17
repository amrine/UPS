/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include "constantes.h"
#include "utilitaire.h"
#include "procedure.h"

/*Procedure de jeu qui renvoie l'entier correspondant au nombre de case des bateaux qui restent */

int procedure (t_matrice m_videIA,t_matrice m_joueur,int* nbcase_joueur,t_matrice m_videJ,t_matrice m_ordi,int* nbcase_ordi)
{
    int rep;
    char choix;
    rep= joueur_humain(m_videJ,m_ordi,m_joueur,nbcase_ordi );

    while (rep== (-1))
    {
        rep= joueur_humain(m_videJ,m_ordi,m_joueur,nbcase_ordi );
    }

    if (rep != 0)
    {
    rep=joueur_ordi(m_videIA,m_joueur,m_ordi,nbcase_joueur,m_videJ,nbcase_ordi );

    }

    if (rep!=0)
    {
        printf("S = sauvegarder , T = tricher , C = continuer: ");
        scanf("\n%c",&choix);
        if (choix== 'S') {
        sauvegarde(m_joueur,m_videJ,m_ordi,m_videIA);
        sauvegarde_nbcase(nbcase_ordi,nbcase_joueur);
        }
        if (choix== 'T') { cheat(m_ordi);}
    }


    return rep;
}


