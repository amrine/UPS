// essaiCLR.cpp : main project file.

#include "stdafx.h"

using namespace System;

value class Vecteur3D
{
public:
	int x,y,z;
	//constructeur
	Vecteur3D(int a, int b, int c) : x(a), y(b), z(c){}

	virtual String^ ToString() override
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
};

int main(array<System::String ^> ^args)
{
	Vecteur3D vecteur(1, 2, 0);
	String^ chaine = "VIVE le C++/CLI" + " haha";
	Console::Write("Votre Nom : ");
	String^ nom = Console::ReadLine();
	Console::Write("\nVotre INE : ");
	int INE = int::Parse(Console::ReadLine());
	Console::WriteLine(L"Hello World " + chaine + " Coordonnées : "
		+ vecteur + " salut : " + nom + " INE : " + INE);
    return 0;
}
