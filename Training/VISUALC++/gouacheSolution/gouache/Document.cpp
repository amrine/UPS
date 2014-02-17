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

using namespace System::IO;
using namespace System::Drawing::Imaging;
using namespace System::Drawing::Drawing2D;

namespace gouache
{
	void Document::NouvelleVue ( void )
	{
		// création d'un nouvel objet Vue
		Vue^ nouvelle = gcnew Vue (this);

		// précise qu'il s'agit d'une fenêtre fille
		nouvelle->MdiParent = app;

		// affiche la vue
		nouvelle->Show ();

		// ajout dans la liste des vues associées au document
		int indice = listeVues->Add ( nouvelle );

		// définit le titre de la vue
		nouvelle->Text = pvTitre + " - vue " + indice;
	}	

	// initialise un document à partir d'un fichier image
	Document::Document ( FenetreApp^ app, String^ fichier )
	{
		// ouvre le fichier bitmap
		pvBmp = gcnew Bitmap ( fichier );

		// crée la surface de dessin associée au bitmap
		dessin = Graphics::FromImage ( pvBmp );
		
		// fenêtre principale
		this->app = app;		



	
		pvNomDeFichier = fichier;
		pvTitre = Path::GetFileName ( fichier );
		pvModification = false;

		// crée une vue pour le document
		listeVues = gcnew ArrayList ();
		NouvelleVue ();
	}

	// initialise un document vide de dimensions données
	Document::Document ( FenetreApp^ app, int tailleX, int tailleY )
	{
		// crée le bitmap
		pvBmp = gcnew Bitmap ( tailleX, tailleY, PixelFormat::Format24bppRgb );

		// crée la surface de dessin associée au bitmap
		dessin = Graphics::FromImage ( pvBmp );

		// remplit l'image de blanc
		dessin->FillRectangle ( gcnew SolidBrush(Color::White), 0,0,pvBmp->Width,pvBmp->Height );

		// fenêtre principale
		this->app = app;

		// initialisation des variables
		pvNomDeFichier = "";
		pvTitre = "Sans titre";
		pvModification = true;

		// crée une vue pour le document
		listeVues = gcnew ArrayList ();
		NouvelleVue();
	}

	bool Document::EnregistrerDocument ( String^ fichier )
	{
		String^ extension = Path::GetExtension(fichier);

		ImageFormat^ format;

		if ( extension == ".jpg" ) 
			format = ImageFormat::Jpeg;
		else if ( extension == ".gif" ) 
			format = ImageFormat::Gif;
		else 
			format = ImageFormat::Bmp;

		pvBmp->Save ( fichier, format );

		// mémorise le fichier associé à l'image
		pvNomDeFichier = fichier;

		// change, s'il y a lieu, le titre des vues
		pvTitre = Path::GetFileName(fichier);

		for each (Vue^ vue in listeVues)
			vue->Text = pvTitre + " - vue " + listeVues->IndexOf(vue);

		return true;
	}

	bool Document::FermerDocument (void)
	{
		if ( !app->AutorisationFermerDocument(this) ) 
			return false;

		while ( listeVues->Count != 0 )
		{
			Vue^ vue = (Vue^)listeVues[0];
			listeVues->Remove(vue);
			vue->Close();
		}
		return true;
	}

	bool Document::AutorisationFermerVue (Vue^ vue)
	{
		// le document a déjà enlevé la vue de la liste
		if ( !listeVues->Contains(vue) )
			return true;

		// il s'agit de la dernière vue du document
		else if ( listeVues->Count == 1 )
		{
			// essaie de fermer le document
			return FermerDocument ();			
		}

		// le document possède au moins deux vues
		else
		{
			listeVues->Remove ( vue );
			return true;
		}
	}

	

	void Document::DessineLigne ( Point pt1, Point pt2 )
	{
		// crée une plume avec la taille 
		// et la couleur courantes
		Pen^ plume = gcnew Pen (app->couleur, (float)app->tailleOutil);
		plume->StartCap = LineCap::Round;
		plume->EndCap = LineCap::Round;

		// trace la ligne		
		dessin->DrawLine ( plume , pt1, pt2 );

		// signale une modification du document
		pvModification = true;

		// redessine toutes les vues associées
		for each ( Vue^ vue in listeVues )
			vue->Invalidate ();
	}


	void Document::DessineRectangle ( Point pt1, Point pt2 )
	{
		int x = Math::Min(pt1.X, pt2.X);
		int y = Math::Min(pt1.Y, pt2.Y);
		int largeur = Math::Abs (pt2.X - pt1.X);
		int hauteur = Math::Abs (pt2.Y - pt1.Y);

		Pen^ plume = gcnew Pen (app->couleur, (float)app->tailleOutil);

		dessin->DrawRectangle ( plume, x, y, largeur, hauteur );

		pvModification = true;

		for each ( Vue^ vue in listeVues )
			vue->Invalidate ();
	}


	void Document::DessineEllipse ( Point pt1, Point pt2 )
	{
		int x = Math::Min(pt1.X, pt2.X);
		int y = Math::Min(pt1.Y, pt2.Y);
		int largeur = Math::Abs (pt2.X - pt1.X);
		int hauteur = Math::Abs (pt2.Y - pt1.Y);

		Pen^ plume = gcnew Pen (app->couleur, (float)app->tailleOutil);

		dessin->DrawEllipse ( plume, x, y, largeur, hauteur );

		pvModification = true;

		for each ( Vue^ vue in listeVues )
			vue->Invalidate ();
	}

	void Document::DessineTexte ( Point p )
	{
		SolidBrush^ pinceau = gcnew SolidBrush( app->couleur );

		dessin->DrawString ( app->texte, app->police, pinceau, p );

		pvModification = true;

		for each ( Vue^ vue in listeVues )
			vue->Invalidate ();
	}

}