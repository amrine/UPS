#ifndef ENNEMI_BASE_H
#define ENNEMI_BASE_H
#include "PersonnageBase.h"

class EnnemiBase : public PersonnageBase
{
private:
	//pointeur sur l'ennemi suivant dans la liste chainee
	EnnemiBase* Suivant;

public:
	//constructeur
	EnnemiBase() : PersonnageBase(), Suivant(NULL) {}

	EnnemiBase* GetSuivant() const {return Suivant;}
	void SetSuivant(EnnemiBase* ptr) {Suivant = ptr;}
	virtual void DeplacementAuto();
	void TesteCollision();
};
#endif // !ENNEMI_BASE_H
