/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#include <stdlib.h>
#include <stdio.h>
#include "constantes.h"
#include "procedure.h"



/*Procedure de jeu qui renvoie l'entier correspondant au nombre de case des bateaux qui restent */

int procedure (t_matrice m_videIA,t_matrice m_joueur,int* nbcase_joueur,t_matrice m_videJ,t_matrice m_ordi,int* nbcase_ordi)
{
    int rep;
    rep= joueur_humain(m_videJ,m_ordi,m_joueur,nbcase_ordi );

    while (rep== (-1))
    {
        rep= joueur_humain(m_videJ,m_ordi,m_joueur,nbcase_ordi );
    }

    if (rep != 0)
    {
    rep=joueur_ordi(m_videIA,m_videJ,m_joueur,m_ordi,nbcase_joueur);

    }

    return rep;
}
