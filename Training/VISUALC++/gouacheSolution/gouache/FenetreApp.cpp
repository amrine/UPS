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
#include "TailleImage.h"


namespace gouache
{
	Void FenetreApp::Fichier_NouveauDocument (Object^ sender, EventArgs^ e)
	{
		TailleImage^ taille = gcnew TailleImage;
		taille->largeur = 450;
		taille->hauteur = 300;

		if ( taille->ShowDialog (this) == Windows::Forms::DialogResult::OK )
		{
			Document^ nouveau = gcnew Document ( this, taille->largeur, taille->hauteur );
			listeDocuments->Add ( nouveau );
		}
	}

	Void FenetreApp::Fichier_Ouvrir (Object^ sender, EventArgs^ e)
	{
		OpenFileDialog^ ouvrir = gcnew OpenFileDialog;
		
		ouvrir->Filter = "Tous les types supportés|*.bmp;*.jpg;*.gif|Images bitmap|*.bmp|Images JPEG|*.jpg|Images GIF|*.gif";
		
		ouvrir->InitialDirectory = Environment::GetFolderPath(Environment::SpecialFolder::MyPictures);
				
		if ( ouvrir->ShowDialog (this) == Windows::Forms::DialogResult::OK )
		{
			Document^ nouveau = gcnew Document (this, ouvrir->FileName);
			listeDocuments->Add ( nouveau );
		}
	}	
	Void FenetreApp::Fichier_FermerDocument (Object^ sender, EventArgs^ e)
	{
		if ( ActiveMdiChild == nullptr ) return;
		
		((Vue^)ActiveMdiChild)->doc->FermerDocument ();	
	}	
	Void FenetreApp::Fichier_NouvelleVue (Object^ sender, EventArgs^ e)
	{
		if ( ActiveMdiChild == nullptr )
			return;
		else
			((Vue^)ActiveMdiChild)->doc->NouvelleVue ();
	}	
	Void FenetreApp::Fichier_FermerVue (Object^ sender, EventArgs^ e)
	{
		if ( ActiveMdiChild == nullptr ) return;

		ActiveMdiChild->Close();
	}	
	Void FenetreApp::Fichier_Enregistrer (Object^ sender, EventArgs^ e)
	{
		Vue^ vue = (Vue^)ActiveMdiChild;

		// vérifie qu'il existe une fenêtre active
		if ( vue != nullptr )
			Enregistrer (vue->doc);
	}	
	Void FenetreApp::Fichier_EnregistrerSous (Object^ sender, EventArgs^ e)
	{
		Vue^ vue = (Vue^)ActiveMdiChild;

		if ( vue != nullptr )
			EnregistrerSous (vue->doc);
	}	
	Void FenetreApp::Fichier_Quitter (Object^ sender, EventArgs^ e)
	{
		Close ();
	}	

	Void FenetreApp::OuvertureMenuFichier (Object^ sender, EventArgs^ e)
	{
		// détermine si les commandes doivent être affichés
		bool affiche = ActiveMdiChild != nullptr;

		// affiche ou masque les commandes
		commandeFermerDocument->Visible = affiche;
		commandeFermerVue->Visible = affiche;
		commandeNouvelleVue->Visible = affiche;
		separateur1->Visible = affiche;
		commandeEnregistrer->Visible = affiche;
		commandeEnregistrerSous->Visible = affiche;
	}

	Void FenetreApp::ChangementOutil (Object^ sender, EventArgs^ e)
	{
		// décoche tous les boutons
		boutonCrayon->Checked = false;
		boutonLigne->Checked = false;
		boutonRectangle->Checked = false;
		boutonEllipse->Checked = false;
		boutonPotPeinture->Checked = false;
		boutonTexte->Checked = false;
		boutonLoupe->Checked = false;

		// seul celui qui vient d'être cliqué sera enfoncé
		((ToolStripButton^)sender)->Checked = true;

		// change l'outil courant
		if ( sender == boutonCrayon ) pvOutil = Outil::Crayon;
		else if ( sender == boutonLigne ) pvOutil = Outil::Ligne;
		else if ( sender == boutonRectangle ) pvOutil = Outil::Rectangle;
		else if ( sender == boutonEllipse ) pvOutil = Outil::Ellipse;
		else if ( sender == boutonPotPeinture ) pvOutil = Outil::PotPeinture;
		else if ( sender == boutonTexte ) pvOutil = Outil::Texte;
		else pvOutil = Outil::Loupe;
		
	}

	Void FenetreApp::ChangementTailleOutil(Object^ sender, EventArgs^ e)
	{
		// affiche sur le bouton l'image de la taille sélectionnée
		boutonTailleOutil->Image = ((ToolStripMenuItem^)sender)->Image;

		// change la taille de l'outil courant selon le bouton cliqué
		if ( sender == bouton1pixel ) pvTailleOutil = 1;
		else if ( sender == bouton2pixels ) pvTailleOutil = 2;
		else if ( sender == bouton4pixels ) pvTailleOutil = 4;
		else if ( sender == bouton6pixels ) pvTailleOutil = 6;
		else if ( sender == bouton8pixels ) pvTailleOutil = 8;
		else pvTailleOutil = 10;
	}

	Void FenetreApp::ChangementCouleur(Object^ sender, EventArgs^ e)
	{
		// crée une instance de fenêtre de sélection de couleur
		ColorDialog^ choixCouleur = gcnew ColorDialog;

		// initialise la fenêtre
		choixCouleur->Color = pvCouleur;
		choixCouleur->FullOpen = true;

		// affiche la fenêtre, et vérifie que l'utilisateur la ferme avec OK
		if ( choixCouleur->ShowDialog (this) == Windows::Forms::DialogResult::OK )
		{
			// enregistre la nouvelle couleur courante
			pvCouleur = choixCouleur->Color;
			// affiche cette couleur en arrière plan du bouton
			boutonCouleur->BackColor = pvCouleur;
		}
	}

	Void FenetreApp::ChangementPolice(Object^ sender, EventArgs^ e)
	{
		FontDialog^ choixPolice = gcnew FontDialog;
		choixPolice->Font = pvPolice;

		if ( choixPolice->ShowDialog(this) == Windows::Forms::DialogResult::OK )
		{
			pvPolice = choixPolice->Font;
			boutonPolice->Text = pvPolice->Name + " " + pvPolice->Size;
		
		}
	}


	bool FenetreApp::Enregistrer (Document^ document)
	{
		// si le document n'a jamais été sauvegardé,
		// nomDeFichier est la chaîne vide
		if ( document->nomDeFichier == "" )
			return EnregistrerSous (document);

		// sinon, on enregistre le document dans le même fichier
		else
			return document->EnregistrerDocument (document->nomDeFichier);
	}

	bool FenetreApp::EnregistrerSous (Document^ document)
	{
		// instancie un objet SaveFileDialog
		SaveFileDialog^ enregistrer = gcnew SaveFileDialog;
		// règle les filtres
		enregistrer->Filter = "Image bitmap|*.bmp|Image JPEG|*.jpg|Image GIF|*.gif";
		// Mes images sera le dossier ouvert par défaut
		enregistrer->InitialDirectory = Environment::GetFolderPath(Environment::SpecialFolder::MyPictures);
				
		if ( enregistrer->ShowDialog (this) == Windows::Forms::DialogResult::OK )
		{
			// si l'utilisateur clique sur OK, on enregistre le document
			return document->EnregistrerDocument ( enregistrer->FileName );
		}
		else
		{
			// si l'utilisateur clique sur Annuler, on abandonne l'enregistrement
			return false;
		}
	}

	bool FenetreApp::AutorisationFermerDocument (Document^ document)
	{
		// si le document a été modifié, on demande confirmation
		if ( document->modification == true )
		{
			Windows::Forms::DialogResult reponse;

			reponse = MessageBox::Show ( 
				"Enregistrer le document "+document->titre+" ?", 
				"Gouache", 
				MessageBoxButtons::YesNoCancel, 
				MessageBoxIcon::Exclamation );

			if ( reponse == Windows::Forms::DialogResult::Cancel ) 
				return false;
			if ( reponse == Windows::Forms::DialogResult::Yes && Enregistrer(document) == false )
				return false;
		}

		// retire le document de la liste
		listeDocuments->Remove ( document );

		// autorise la fermeture du document
		return true;
	}

}