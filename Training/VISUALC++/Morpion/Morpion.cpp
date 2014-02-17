// Morpion.cpp : main project file.

#include "stdafx.h"
#include "Joueur.h"
#include "Form1.h"

using namespace Morpion;

[STAThreadAttribute]
int main(array<System::String ^> ^args)
{
	// Enabling Windows XP visual effects before any controls are created
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false); 

	// Create the main window and run it, support de l'application
	Application::Run(gcnew Form1());
	return 0;
}

namespace Morpion {
	Void Form1::cliqueBoutonQuitter(System::Object^  sender, System::EventArgs^  e)
	{
		Close();
	}
	Void Form1::cliqueBoutonNouvellePartie(Object^ sender, EventArgs^ e)
	{
		nouvellePartie();
	}
	Void Form1::cliqueCase(Object^ sender, EventArgs^ e)
	{
		PictureBox^ appelant = (PictureBox^) sender;
		Point coordonnees = (Point) appelant->Tag;
		//MessageBox::Show("Clique sur la case " + coordonnees);
		//quitte si la case est occuppe
		if(grille[coordonnees.X, coordonnees.Y] != nullptr)
			return;

		//case vide
		grille[coordonnees.X, coordonnees.Y] = joueurCourant;
		//affiche la marque du joueur dans la case
		appelant->Image = joueurCourant->propSymbole;
		//incremente le nombre de coup
		coupJoues++;
		//en cas de victoire
		if(alignement())
		{
			//affiche une boite de message
			MessageBox::Show(joueurCourant->propNom + " remporte la manche !", "Morpion");
			//augmente le score du joueur
			joueurCourant->propScore++;
			//entamme une nouvelle manche
			nouvelleManche();
		}
		else if (coupJoues == 9) //toutes les cases sont occuppee
		{
			MessageBox::Show("Match Null !", "Morpion");
			nouvelleManche();
		}

		//passer la main au joueur adverse
		if (joueurCourant == listeJoueurs[0])
			passeLaMain(listeJoueurs[1]);
		else
			passeLaMain(listeJoueurs[0]);
	}
	Void Form1::nouvellePartie(Void)
	{
		//cree un tableau a deux elements
		listeJoueurs = gcnew array<Joueur^>(2);
		//creation des deux objets
		listeJoueurs[0] = gcnew Joueur("Alpha Oumar",
			listeImages->Images[0], labelScoreJoueur1);
		listeJoueurs[1] = gcnew Joueur("Oumou Barry",
			listeImages->Images[1], labelScoreJoueur2);
		//actualisation de la zone de texte des noms des joueur
		labelNomJoueur1->Text = listeJoueurs[0]->propNom;
		labelNomJoueur2->Text = listeJoueurs[1]->propNom;
		//passe la main au premier joueur
		passeLaMain(listeJoueurs[0]);
		//debut de la manche
		nouvelleManche();
	}
	Void Form1::nouvelleManche(Void)
	{
		//instancie une grille a deux dimensions de taille 3*3
		grille = gcnew array<Joueur^, 2>(3, 3);
		//initialisation de la grille, passage par reference
		//sinon la grille reste inchange
		for each (Joueur^ %caseGrille in grille)
			caseGrille = nullptr;
		//on retire les symboles dans les cases
		this->case_0_2->Image = nullptr;
		this->case_2_2->Image = nullptr;
		this->case_1_2->Image = nullptr;
		this->case_1_1->Image = nullptr;
		this->case_2_1->Image = nullptr;
		this->case_0_1->Image = nullptr;
		this->case_0_0->Image = nullptr;
		this->case_1_0->Image = nullptr;
		this->case_2_0->Image = nullptr;
		//nouvelle manche
		coupJoues = 0;
	}
	Void Form1::passeLaMain(Joueur^ joueur)
	{
		joueurCourant = joueur;
		labelJoueurCourant->Text = 
			joueurCourant->propNom->ToUpper() + " JOUE";
	}
	bool Form1::alignement ( void )
	{
		// cherche un alignement sur une ligne
		for ( int ligne=0; ligne<3; ligne++ )
		{
			if (grille[0,ligne] == joueurCourant 
				&& grille[1,ligne] == joueurCourant 
				&& grille[2,ligne] == joueurCourant) 
				return true;
		}

		// cherche un alignement en colonne
		for ( int colonne=0; colonne<3; colonne++ )
		{
			if (grille[colonne,0] == joueurCourant &&
				 grille[colonne,1] == joueurCourant &&
				 grille[colonne,2] == joueurCourant)
				 return true;
		}

		// cherche un alignement en diagonale
		if (grille[0,0] == joueurCourant 
			&& grille[1,1] == joueurCourant 
			&& grille[2,2] == joueurCourant)
			return true;
		if (grille[0,2] == joueurCourant 
			&& grille[1,1] == joueurCourant 
			&& grille[2,0] == joueurCourant)
			return true;

		// aucun alignement
		return false;
	}
	
}