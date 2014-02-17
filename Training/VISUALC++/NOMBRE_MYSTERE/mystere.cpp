#include <iostream>
#include <limits>
#include <time.h>
using namespace std;

void jouer();

void main(void)
{
	srand((int) time(NULL)); //intialisation des nombres aleatoires
	char continuer;
	cout << "\tLe juste prix fait maison, LOL prix entre (0 et 1000)" << endl << endl
		<< "\t\tJe ne veux pas trop vous fatiguer :P" << endl << endl;

	jouer();

	while(true)
	{
		cout << "Voulez jouer une nouvelle partie y/n : ";
		while ((!(cin >> continuer) || (cin.peek() != '\n')))
		{
			cout << "Voulez jouer une nouvelle partie y/n : ";
			cin.clear();
			cin.ignore (numeric_limits<streamsize>::max(), '\n');
		} 
		
		if(continuer == 'n' || continuer == 'N')
		{
			cout << "Au revoir ! A bientot !" << endl;
			break;
		}
		else if(continuer == 'y' || continuer == 'Y' || continuer == 'o' || continuer == 'O')
		{
			jouer();
		}
	}

	cout << endl << "\t\tAlpha Oumar Binta Diallo (MINI JEU)" << endl << endl;
	system("pause");
}

void jouer()
{
	int nbMystere, max(1001), nbJoueur, score(0); //declaration des variables de jeu
	nbMystere = rand()%max;

	cout << endl << "\t\t\tDebut de la partie" << endl << endl;

	do
	{
		cout << "Entrez une tentative : ";
		while(!(cin >> nbJoueur) || (cin.peek() != '\n')){
			cerr << "Une tentative valide :-( : ";
			cin.clear();
			cin.ignore (numeric_limits<streamsize>::max(), '\n');
		}
		score++;

		if(nbJoueur == nbMystere)
			cout << "Felicitations ! vous avez trouve le nombre mystere !" << endl
			<< "Vous avez eu besoin de " << score << " tentative(s)" << endl;
		else
		{
			if(nbJoueur < nbMystere)
				cout << "Plus !" << endl;
			else
				cout << "Moins !" << endl;
		}
	}while(nbJoueur != nbMystere);
}