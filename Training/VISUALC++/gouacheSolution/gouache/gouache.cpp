// gouache.cpp�: fichier projet principal.

#include "StdAfx.h"
namespace gouache 
{
ref class FenetreApp;
ref class Document;
ref class Vue;
}
#include "FenetreApp.h"
#include "Document.h"
#include "Vue.h"

using namespace gouache;

[STAThreadAttribute]
int main(array<System::String ^> ^args)
{
	// Activation des effets visuels de Windows�XP avant la cr�ation de tout contr�le
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false); 

	// Cr�er la fen�tre principale et l'ex�cuter
	Application::Run(gcnew FenetreApp());
	return 0;
}
