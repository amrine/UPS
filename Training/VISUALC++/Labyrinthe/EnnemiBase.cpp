#include "EnnemiBase.h"
#include "Joueur.h"

extern Joueur monJoueur;
extern void LibererMemoire();
extern void LabyAffichage(); //rafraichissement de la scene

void EnnemiBase::DeplacementAuto()
{
	int hasard = rand()%4;
	switch (hasard)
	{
	case 0:
		BougerEnHaut(); break;
	case 1:
		BougerEnBas(); break;
	case 2: BougerADroite(); break;
	case 3:
		BougerAGauche(); break;
	}
}
void EnnemiBase::TesteCollision()
{
	//teste si le joueur et l'ennemis sont dans la meme case
	if (PosC == monJoueur.GetPosC()
		&& PosL == monJoueur.GetPosL())
	{
		cout << "Vous avez perdu !\a" << endl;
		LabyAffichage(); //rafraichissement de la scene
		LibererMemoire();
		system("pause");
		exit(EXIT_SUCCESS);
	}
}