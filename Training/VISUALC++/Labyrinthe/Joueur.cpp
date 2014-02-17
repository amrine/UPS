#include "Joueur.h"

//dessin du joueur
void Joueur::Dessiner()
{
	glPushMatrix();
	glTranslated(PosC+0.5, PosL+0.5, 0.0);

	glColor3d(0.0, 0.0, 0.0); //couleur noire
	glutSolidSphere(0.3, 12, 12); //sphere de la tete

	glColor3d(1.0, 1.0, 0.0); //couleur jaune
	glTranslated(0.1, -0.1, 0.0);
	glutSolidSphere(0.05, 12, 12); //premier oeil

	glTranslated(-0.2, 0.0, 0.0);
	glutSolidSphere(0.05, 12, 12); //deuxieme oeil

	glPopMatrix();
}