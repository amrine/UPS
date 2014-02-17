#ifndef JOUEUR_H
#define JOUEUR_H
#include "PersonnageBase.h"

class Joueur : public PersonnageBase
{
public:
	//constructeur
	Joueur() : PersonnageBase() {}

	//dessin de l'avatr du joueur
	void Dessiner();
};
#endif // !JOUEUR_H