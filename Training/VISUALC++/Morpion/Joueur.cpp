#include "stdafx.h"
#include "Joueur.h"

Joueur::Joueur(String^ nomJoueur, Image^ symbole, Label^ labelScore)
{
	this->nomJoueur = nomJoueur;
	this->labelScore = labelScore;
	this->symbole = symbole;
	propScore = 0;
}

void Joueur::propScore::set(int score)
{
	this->score = score;
	//mis a jour du label dans la fenetre
	this->labelScore->Text = score.ToString();
}
int Joueur::propScore::get()
{
	return this->score;
}
String^ Joueur::propNom::get()
{
	return (String^) nomJoueur->Clone();
}
Image^ Joueur::propSymbole::get()
{
	return (Image^) symbole->Clone();
}