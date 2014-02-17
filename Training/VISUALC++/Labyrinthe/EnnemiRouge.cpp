#include "EnnemiRouge.h"

extern enum TYPE_ENNEMI //enumeration pour le type d'ennemi
{
	ENNEMI_VERT, //identificateur ennemi vert
	ENNEMI_ROUGE //identificateur ennemi rouge
};
extern void AjouterEnnemi(TYPE_ENNEMI type, int C, int L);

void EnnemiRouge::Dessiner()
{
	glPushMatrix();
	glTranslated(PosC+0.5, PosL+0.5, 0.0);
	//la tete
	glColor3d(0.9, 0.0, 0.0); //couleur rouge
	glPushMatrix();
	glScaled(0.7, 1.0, 1.0);
	glutSolidSphere(0.35, 12, 12); //premier ovale
	glPopMatrix();
	glPushMatrix();
	glScaled(1.0, 0.7, 1.0);
	glutSolidSphere(0.35, 12, 12); //deuxieme ovale
	glPopMatrix();
	//les yeux
	glColor3d(0.0, 0.0, 0.0); //couleur noire
	glTranslated(0.13, -0.05, 0.0);
	glutSolidSphere(0.07, 12, 12); //premier oeil
	glTranslated(-0.26, 0.0, 0.0);
	glutSolidSphere(0.07, 12, 12); //deuxieme oeil
	glPopMatrix();
}

void EnnemiRouge::DeplacementAuto()
{
	//l'ennemi a une chance sur 50 de se dupliquer
	if (rand()%50 == 0)
	{
		//ajout d'un nouvel ennemi
		AjouterEnnemi(ENNEMI_ROUGE, PosC, PosL);
	}
	//appel de DeplacementAuto de la classe Mere, pour deplacer l'ennemi
	EnnemiBase::DeplacementAuto();
}