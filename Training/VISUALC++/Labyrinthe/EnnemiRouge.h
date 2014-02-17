#ifndef ENNEMI_ROUGE_H
#define ENNEMI_ROUGE_H
#include "EnnemiBase.h"

class EnnemiRouge : public EnnemiBase
{
public:
	EnnemiRouge() : EnnemiBase(){}
	//dessin de l'ennemi rouge
	void Dessiner();
	void DeplacementAuto();
};
#endif // !ENNEMI_ROUGE