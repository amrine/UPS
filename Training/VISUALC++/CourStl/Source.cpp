#include <iostream>
#include <vector>
#include <list>
#include "../Labyrinthe/EnnemiBase.h"

using namespace std;

void main(void)
{
	//allocation d'un vecteur d'entiers
	vector<int> tab;
	cout << tab.size() << endl;
	//tableau de 7 reel initialisee a 1.2
	vector<float> tab1(7, 1.2f);
	cout << tab1.size() << endl;
	//tableau de 4 reel non initialisee
	vector<float> tab2(4);
	for (int i = 0; i < 4; i++)
	{
		tab2[i] = (float)i;
	}
	cout << tab2.size() << endl; //4
	tab2.push_back(4.5f); //ajout d'un element en bout
	cout << tab2.size() << endl; //5

	//declaration d'un iterateur sur un vecteur de float
	vector<float>::iterator it;
	//parcours a l'aide de l'iterateur (un iterateur peut aussi modifier la valeur
	for (it = tab2.begin(); it != tab2.end(); it++)
	{
		*it*=2; //modification de la valeur
		cout << *it; //affiche la valeur stockee
	}
	cout << endl << "Liste chainee" << endl;
	//liste de 4 entiers
	list<int> maListe(4, 150);
	//iterateur
	list<int>::iterator listIt = maListe.begin();
	while (listIt != maListe.end())
	{
		cout << *listIt; //affiche la valeur stockee
		listIt++;
	}

	//liste de 4 ennemi, allouee
	list<EnnemiBase*> listeEnnemi;
	//liberation de la memoire allouee par la liste
	list<EnnemiBase*>::iterator it2 = listeEnnemi.begin();
	while (it2 != listeEnnemi.end())
	{
		//erase() clear() supprime un element, vide la liste
		//sans liberer la memoire
		delete *it2; //suppression de l'element en cours, liberation de la memoire
		it2++;
	}
	listeEnnemi.clear(); //efface le contenu de la liste
	cout << endl << "liste ennemi size : " << listeEnnemi.size() << endl;
}