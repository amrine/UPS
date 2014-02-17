// Document.h
using namespace System::Drawing;
using namespace System::Collections;

namespace gouache
{
	public ref class Document
	{
	private:
		Graphics^ dessin;
		Bitmap^	pvBmp;

		ArrayList^ listeVues;
		FenetreApp^ app;	
		String^ pvNomDeFichier;
		String^ pvTitre;

		// modif
		bool pvModification;
		
	public:

		// ouvre une image existante
		Document ( FenetreApp^ app, String^ fichier );
		// crée une image vide, de dimensions données
		Document ( FenetreApp^ app, int tailleX, int tailleY );

		void NouvelleVue (void);
		bool EnregistrerDocument ( String^ fichier );

		bool FermerDocument (void);
		bool AutorisationFermerVue (Vue^ vue);

		void DessineLigne ( Point pt1, Point pt2 );
		void DessineRectangle ( Point pt1, Point pt2 );
		void DessineEllipse ( Point pt1, Point pt2 );
		void DessineTexte ( Point p );

		//void PotPeinture ( Point point );
		//void PotPeinturePixel ( int x, int y, Color ancienneCouleur, Queue^ file );

		//// chap2 ...
		//property Bitmap^ bmp {
		//	Bitmap^ get() { return (Bitmap^)pvBmp->Clone(); }
		//}
		//// ... chap2
		property Bitmap^ bmp {
			Bitmap^ get() { return (Bitmap^)pvBmp->Clone(); }
		}

		property bool modification {
			bool get() { return pvModification; }
		}
		property String^ nomDeFichier {
			String^ get() { return (String^)pvNomDeFichier->Clone(); }
		}
		property String^ titre {
			String^ get() { return (String^)pvTitre->Clone(); }
		}

	};
}