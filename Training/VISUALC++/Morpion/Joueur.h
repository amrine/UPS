using namespace System;
using namespace System::Drawing;
using namespace System::Windows::Forms;

ref class Joueur
{
private:
	String^ nomJoueur;
	Image^ symbole;
	int score;
	Label^ labelScore;

public:
	Joueur(String^ nomJoueur, Image^ symbole, Label^ labelScore);
	//propScore accesseur et mutateur
	property int propScore
	{
		int get();
		void set(int score);
	}
	//propNom accesseur seul
	property String^ propNom
	{
		String^ get();
	}
	//propSymbole accesseur seul
	property Image^ propSymbole
	{
		Image^ get();
	}

};