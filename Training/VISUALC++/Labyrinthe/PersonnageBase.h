#ifndef PERSONNAGE_BASE_H
#define PERSONNAGE_BASE_H
//inclusions utiles pour toutes les classes derivees
#include <iostream>
using namespace std;
#include "GL/glut.h"

class PersonnageBase
{
protected:
	//Position Colonne, ligne sur la matrice
	int PosC, PosL;

public:
	//accesseurs
	int GetPosC() const {return PosC;}
	int GetPosL() const {return PosL;}
	//mutateurs
	void SetPosC(int C) {PosC = C;}
	void SetPosL(int L) {PosL = L;}

	//constructeur
	PersonnageBase() {PosC = PosL =0;}
	
	//fonctions de deplacements
	void BougerEnHaut();
	void BougerEnBas();
	void BougerAGauche();
	void BougerADroite();
	//fonction de dessin virtuelle pure
	virtual void Dessiner() = 0;
};
#endif // !PERSONNAGE_BASE_H
