#include "EnnemiVert.h"

void EnnemiVert::Dessiner()
{
	glPushMatrix();
	glTranslated(PosC+0.5, PosL+0.5, 0.0);
	//la tete
	glColor3d(0.0, 0.9, 0.0); //couleur verte
	glutSolidSphere(0.3, 12, 12); //sphere de la tete
	//les cornes
	glPushMatrix();
	glTranslated(0.0, -0.13, 0.0);
	glScaled(1.0, 0.5, 1.0);
	glutSolidCube(0.5);
	glPopMatrix();
	//les yeux
	glColor3d(1.0, 0.0, 0.0);
	//se place a la position du premier oeil
	glTranslated(-0.3, -0.1, 0.0);
	//dessin des trois yeux
	for (int i = 0; i < 3; i++)
	{
		glTranslated(0.15, 0.0, 0.0);
		glutSolidSphere(0.05, 12, 12);
	}
	glPopMatrix();
}