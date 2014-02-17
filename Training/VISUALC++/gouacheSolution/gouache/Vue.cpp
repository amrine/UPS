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

using namespace System::Drawing::Drawing2D;

namespace gouache
{
	Void Vue::FermetureVue(Object^ sender, FormClosingEventArgs^ e)
	{
		if ( doc->AutorisationFermerVue(this) )
			e->Cancel = false;
		else
			e->Cancel = true;
	}
			
	Void Vue::RedessineVue(Object^ sender, PaintEventArgs^ e)
	{
		Graphics^ g = e->Graphics;	

		g->InterpolationMode = Drawing2D::InterpolationMode::NearestNeighbor;
		g->DrawImage ( doc->bmp, 0.0f, 0.0f, (int)doc->bmp->Width * zoom, (int)doc->bmp->Height * zoom); 

		if ( traceEnCours )
		{
			// définit une plume pointillés
			Pen^ pointilles = gcnew Pen (Color::Black);
			pointilles->DashStyle = DashStyle::Dash;

			// outil courant
			Outil outil = ((FenetreApp^)MdiParent)->outil;

			// x,y : coordonnées du coin supérieur gauche
			int x = Math::Min(pointDepart.X, pointArrivee.X);
			int y = Math::Min(pointDepart.Y, pointArrivee.Y);

			// largeur hauteur : dimensions du rectangle
			int largeur = Math::Abs (pointArrivee.X - pointDepart.X);
			int hauteur = Math::Abs (pointArrivee.Y - pointDepart.Y);

			// dessine en pointilles
			if ( outil == Outil::Ligne )
				g->DrawLine(pointilles, pointDepart, pointArrivee);
			else if ( outil == Outil::Rectangle )
				g->DrawRectangle(pointilles, x, y, largeur, hauteur );
			else if ( outil == Outil::Ellipse )
				g->DrawEllipse(pointilles, x, y, largeur, hauteur );
		}
	}

	//// plume noire, deux pixels d'épaisseur
	//	Pen^ plume = gcnew Pen ( Color::Black, 2 );

	//	// coordonnées de début et de fin
	//	Point p1 ( 30, 40 );
	//	Point p2 ( 190, 110 );

	//	Rectangle rec ( 30,30, 150, 80 );

	//	// trace la ligne
	//	//g->DrawLine ( plume, p1,p2 );

	//	// dessine un rectangle
	//	// de coin supérieur gauche (40,40)
	//	// de coin inférieur droit (240,140)
	//	//g->DrawRectangle ( plume, 40, 40, 200, 100 );

	//	//// dessine un rectangle		
	//	//g->DrawRectangle ( plume, 20, 20, 200, 100 );

	//	//// avec les memes paramètres, dessine
	//	//// l'ellipse inscrite dans le rectangle
	//	//g->DrawEllipse   ( plume, 20, 20, 200, 100 );

	//	//// pour tracer un cercle, donner une zone carrée
	//	//g->DrawEllipse ( plume, 240, 30, 50, 50 );

	//	// pinceau bleu uni
	//	SolidBrush^ uni = gcnew SolidBrush ( Color::Blue );

	//	// pinceau blanc à hachures diagonales noires
	//	HatchBrush^ hachure = gcnew HatchBrush (HatchStyle::WideDownwardDiagonal, Color::Black, Color::White );

	//	// dégradé horizontal du rouge au blanc
	//	// entre les abscisses 200 et 250
	//	LinearGradientBrush^ degrade = gcnew LinearGradientBrush ( Point(200,0), Point(250,0), Color::Red, Color::White );

	//	g->FillRectangle (uni, 40, 40, 50, 50);
	//	g->FillEllipse   (hachure, 120, 40, 50, 50);
	//	g->FillRectangle (degrade, 200, 40, 50, 50);



	Void Vue::AppuieSouris(Object^ sender, MouseEventArgs^ e)
	{
		if ( e->Button == Windows::Forms::MouseButtons::Left )
		{
			traceEnCours = true;
			pointDepart = e->Location;
		}
	}

	Void Vue::RelacheSouris(Object^ sender, MouseEventArgs^ e)
	{
		// Outil en cours
		Outil outil = ((FenetreApp^)MdiParent)->outil;

		// bouton gauche
		if ( e->Button == Windows::Forms::MouseButtons::Left )
		{
			if ( traceEnCours == false ) return;
			pointArrivee = e->Location;

			// conversion repère fenetre -> repère image
			pointDepart.X = (int) (pointDepart.X/zoom);
			pointDepart.Y = (int) (pointDepart.Y/zoom);
			pointArrivee.X = (int) (pointArrivee.X/zoom);
			pointArrivee.Y = (int) (pointArrivee.Y/zoom);

			// tracé
			switch ( outil )
			{
			case Outil::Loupe :
				zoom = zoom * 2; 
				break;
			case Outil::Ligne :
				doc->DessineLigne ( pointDepart, pointArrivee ); 
				break;
			case Outil::Rectangle :
				doc->DessineRectangle ( pointDepart, pointArrivee );
				break;
			case Outil::Ellipse :
				doc->DessineEllipse ( pointDepart, pointArrivee );
				break;
			case Outil::Texte :
				doc->DessineTexte ( pointArrivee );
			}
		}

		// bouton droit
		else 
		{
			if ( outil == Outil::Loupe )
				zoom = zoom / 2;
		}

		traceEnCours = false;
		Invalidate();
	}

	Void Vue::DeplaceSouris(Object^ sender, MouseEventArgs^ e)
	{
		if ( traceEnCours )
		{
			pointArrivee = e->Location;

			if ( ((FenetreApp^)MdiParent)->outil == Outil::Crayon )
			{
				Point pt1 = Point ( (int)(pointDepart.X / zoom), (int)(pointDepart.Y / zoom) );
				Point pt2 = Point ( (int)(pointArrivee.X / zoom), (int)(pointArrivee.Y / zoom) );

				doc->DessineLigne ( pt1, pt2 );
				pointDepart = pointArrivee;
			}

			Invalidate ();
		}
	}	



	

}

