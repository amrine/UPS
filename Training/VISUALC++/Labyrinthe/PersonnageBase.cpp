#include "PersonnageBase.h"

//variables globales declarees dans un autre fichier
extern int NBColonnes;
extern int NBLignes;
extern char** Matrice;

//fonction de deplacement
void PersonnageBase::BougerEnHaut()
{
	if (PosL > 0 && Matrice[PosC][PosL-1] != '0')
	{
		PosL--;
	}
}
void PersonnageBase::BougerEnBas()
{
	if (PosL < NBLignes-1 && Matrice[PosC][PosL+1] != '0')
	{
		PosL++;
	}
}
void PersonnageBase::BougerAGauche()
{
	if (PosC > 0 && Matrice[PosC-1][PosL] != '0')
	{
		PosC--;
	}
}
void PersonnageBase::BougerADroite()
{
	if (PosC < NBColonnes-1 && Matrice[PosC+1][PosL] != '0')
	{
		PosC++;
	}
}