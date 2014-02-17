#pragma once

using namespace System;
using namespace System::ComponentModel;
using namespace System::Collections;
using namespace System::Windows::Forms;
using namespace System::Data;
using namespace System::Drawing;


namespace gouache {

	enum class Outil
	{
		Crayon,
		Ligne,
		Rectangle,
		Ellipse,
		PotPeinture,
		Texte,
		Loupe,
	};

	/// <summary>
	/// Description résumée de FenetreApp
	///
	/// AVERTISSEMENT : si vous modifiez le nom de cette classe, vous devrez modifier la
	///          propriété 'Nom du fichier de ressources' de l'outil de compilation de ressource managée
	///          pour tous les fichiers .resx dont dépend cette classe. Dans le cas contraire,
	///          les concepteurs ne pourront pas interagir correctement avec les ressources
	///          localisées associées à ce formulaire.
	/// </summary>
	public ref class FenetreApp : public System::Windows::Forms::Form
	{
	public:
		FenetreApp(void)
		{
			InitializeComponent();

			// liste vide
			listeDocuments = gcnew ArrayList;

			// outil Crayon
			pvOutil = Outil::Crayon;
			// couleur rouge
			pvCouleur = Color::Red;
			// taille 1 pixel
			pvTailleOutil = 1;
			// police Arial 8
			pvPolice = gcnew Drawing::Font ("Arial", 8 );			
		}

	protected:
		/// <summary>
		/// Nettoyage des ressources utilisées.
		/// </summary>
		~FenetreApp()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::MenuStrip^  menuStrip1;
	private: System::Windows::Forms::ToolStripMenuItem^  fichierToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^  fenêtreToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^  nouveauDocumentToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^  ouvrirToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^  commandeFermerDocument;
	private: System::Windows::Forms::ToolStripMenuItem^  commandeNouvelleVue;
	private: System::Windows::Forms::ToolStripMenuItem^  commandeFermerVue;
	private: System::Windows::Forms::ToolStripMenuItem^  commandeEnregistrer;
	private: System::Windows::Forms::ToolStripMenuItem^  commandeEnregistrerSous;





	private: System::Windows::Forms::ToolStripMenuItem^  quitterToolStripMenuItem;
	private: System::Windows::Forms::ToolStripSeparator^  separateur1;

	private: System::Windows::Forms::ToolStripSeparator^  toolStripSeparator2;
	private: System::Windows::Forms::ToolStrip^  toolStrip1;
	private: System::Windows::Forms::ToolStripButton^  nouveauToolStripButton;
	private: System::Windows::Forms::ToolStripButton^  ouvrirToolStripButton;
	private: System::Windows::Forms::ToolStripButton^  enregistrerToolStripButton;
	private: System::Windows::Forms::ToolStripSeparator^  toolStripSeparator;
	private: System::Windows::Forms::ToolStripButton^  boutonCrayon;
	private: System::Windows::Forms::ToolStripButton^  boutonLigne;
	private: System::Windows::Forms::ToolStripButton^  boutonRectangle;
	private: System::Windows::Forms::ToolStripButton^  boutonEllipse;
	private: System::Windows::Forms::ToolStripButton^  boutonPotPeinture;
	private: System::Windows::Forms::ToolStripButton^  boutonTexte;
	private: System::Windows::Forms::ToolStripButton^  boutonLoupe;
	private: System::Windows::Forms::ToolStripSeparator^  toolStripSeparator1;
	private: System::Windows::Forms::ToolStripDropDownButton^  boutonTailleOutil;
	private: System::Windows::Forms::ToolStripMenuItem^  bouton1pixel;
	private: System::Windows::Forms::ToolStripMenuItem^  bouton2pixels;
	private: System::Windows::Forms::ToolStripMenuItem^  bouton4pixels;
	private: System::Windows::Forms::ToolStripMenuItem^  bouton6pixels;
	private: System::Windows::Forms::ToolStripMenuItem^  bouton8pixels;
	private: System::Windows::Forms::ToolStripMenuItem^  bouton10pixels;
	private: System::Windows::Forms::ToolStripButton^  boutonCouleur;
	private: System::Windows::Forms::ToolStripButton^  boutonPolice;
	private: System::Windows::Forms::ToolStripTextBox^  zoneTexte;






















	protected: 

	private:
		/// <summary>
		/// Variable nécessaire au concepteur.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Méthode requise pour la prise en charge du concepteur - ne modifiez pas
		/// le contenu de cette méthode avec l'éditeur de code.
		/// </summary>
		void InitializeComponent(void)
		{
			System::ComponentModel::ComponentResourceManager^  resources = (gcnew System::ComponentModel::ComponentResourceManager(FenetreApp::typeid));
			this->menuStrip1 = (gcnew System::Windows::Forms::MenuStrip());
			this->fichierToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->nouveauDocumentToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->ouvrirToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->commandeFermerDocument = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->commandeNouvelleVue = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->commandeFermerVue = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->separateur1 = (gcnew System::Windows::Forms::ToolStripSeparator());
			this->commandeEnregistrer = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->commandeEnregistrerSous = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->toolStripSeparator2 = (gcnew System::Windows::Forms::ToolStripSeparator());
			this->quitterToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->fenêtreToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->toolStrip1 = (gcnew System::Windows::Forms::ToolStrip());
			this->nouveauToolStripButton = (gcnew System::Windows::Forms::ToolStripButton());
			this->ouvrirToolStripButton = (gcnew System::Windows::Forms::ToolStripButton());
			this->enregistrerToolStripButton = (gcnew System::Windows::Forms::ToolStripButton());
			this->toolStripSeparator = (gcnew System::Windows::Forms::ToolStripSeparator());
			this->boutonCrayon = (gcnew System::Windows::Forms::ToolStripButton());
			this->boutonLigne = (gcnew System::Windows::Forms::ToolStripButton());
			this->boutonRectangle = (gcnew System::Windows::Forms::ToolStripButton());
			this->boutonEllipse = (gcnew System::Windows::Forms::ToolStripButton());
			this->boutonPotPeinture = (gcnew System::Windows::Forms::ToolStripButton());
			this->boutonTexte = (gcnew System::Windows::Forms::ToolStripButton());
			this->boutonLoupe = (gcnew System::Windows::Forms::ToolStripButton());
			this->toolStripSeparator1 = (gcnew System::Windows::Forms::ToolStripSeparator());
			this->boutonTailleOutil = (gcnew System::Windows::Forms::ToolStripDropDownButton());
			this->bouton1pixel = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->bouton2pixels = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->bouton4pixels = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->bouton6pixels = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->bouton8pixels = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->bouton10pixels = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->boutonCouleur = (gcnew System::Windows::Forms::ToolStripButton());
			this->boutonPolice = (gcnew System::Windows::Forms::ToolStripButton());
			this->zoneTexte = (gcnew System::Windows::Forms::ToolStripTextBox());
			this->menuStrip1->SuspendLayout();
			this->toolStrip1->SuspendLayout();
			this->SuspendLayout();
			// 
			// menuStrip1
			// 
			this->menuStrip1->Items->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(2) {this->fichierToolStripMenuItem, 
				this->fenêtreToolStripMenuItem});
			this->menuStrip1->Location = System::Drawing::Point(0, 0);
			this->menuStrip1->MdiWindowListItem = this->fenêtreToolStripMenuItem;
			this->menuStrip1->Name = L"menuStrip1";
			this->menuStrip1->RenderMode = System::Windows::Forms::ToolStripRenderMode::System;
			this->menuStrip1->Size = System::Drawing::Size(531, 26);
			this->menuStrip1->TabIndex = 1;
			this->menuStrip1->Text = L"menuStrip1";
			// 
			// fichierToolStripMenuItem
			// 
			this->fichierToolStripMenuItem->DropDownItems->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(10) {this->nouveauDocumentToolStripMenuItem, 
				this->ouvrirToolStripMenuItem, this->commandeFermerDocument, this->commandeNouvelleVue, this->commandeFermerVue, this->separateur1, 
				this->commandeEnregistrer, this->commandeEnregistrerSous, this->toolStripSeparator2, this->quitterToolStripMenuItem});
			this->fichierToolStripMenuItem->Name = L"fichierToolStripMenuItem";
			this->fichierToolStripMenuItem->Size = System::Drawing::Size(60, 22);
			this->fichierToolStripMenuItem->Text = L"&Fichier";
			this->fichierToolStripMenuItem->DropDownOpening += gcnew System::EventHandler(this, &FenetreApp::OuvertureMenuFichier);
			// 
			// nouveauDocumentToolStripMenuItem
			// 
			this->nouveauDocumentToolStripMenuItem->Name = L"nouveauDocumentToolStripMenuItem";
			this->nouveauDocumentToolStripMenuItem->ShortcutKeys = static_cast<System::Windows::Forms::Keys>((System::Windows::Forms::Keys::Control | System::Windows::Forms::Keys::N));
			this->nouveauDocumentToolStripMenuItem->Size = System::Drawing::Size(269, 22);
			this->nouveauDocumentToolStripMenuItem->Text = L"&Nouveau document...";
			this->nouveauDocumentToolStripMenuItem->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_NouveauDocument);
			// 
			// ouvrirToolStripMenuItem
			// 
			this->ouvrirToolStripMenuItem->Name = L"ouvrirToolStripMenuItem";
			this->ouvrirToolStripMenuItem->ShortcutKeys = static_cast<System::Windows::Forms::Keys>((System::Windows::Forms::Keys::Control | System::Windows::Forms::Keys::O));
			this->ouvrirToolStripMenuItem->Size = System::Drawing::Size(269, 22);
			this->ouvrirToolStripMenuItem->Text = L"&Ouvrir...";
			this->ouvrirToolStripMenuItem->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_Ouvrir);
			// 
			// commandeFermerDocument
			// 
			this->commandeFermerDocument->Name = L"commandeFermerDocument";
			this->commandeFermerDocument->Size = System::Drawing::Size(269, 22);
			this->commandeFermerDocument->Text = L"Fermer le &document";
			this->commandeFermerDocument->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_FermerDocument);
			// 
			// commandeNouvelleVue
			// 
			this->commandeNouvelleVue->Name = L"commandeNouvelleVue";
			this->commandeNouvelleVue->ShortcutKeys = static_cast<System::Windows::Forms::Keys>((System::Windows::Forms::Keys::Control | System::Windows::Forms::Keys::B));
			this->commandeNouvelleVue->Size = System::Drawing::Size(269, 22);
			this->commandeNouvelleVue->Text = L"Nouvelle &vue";
			this->commandeNouvelleVue->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_NouvelleVue);
			// 
			// commandeFermerVue
			// 
			this->commandeFermerVue->Name = L"commandeFermerVue";
			this->commandeFermerVue->Size = System::Drawing::Size(269, 22);
			this->commandeFermerVue->Text = L"Fer&mer la vue";
			this->commandeFermerVue->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_FermerVue);
			// 
			// separateur1
			// 
			this->separateur1->Name = L"separateur1";
			this->separateur1->Size = System::Drawing::Size(266, 6);
			// 
			// commandeEnregistrer
			// 
			this->commandeEnregistrer->Name = L"commandeEnregistrer";
			this->commandeEnregistrer->ShortcutKeys = static_cast<System::Windows::Forms::Keys>((System::Windows::Forms::Keys::Control | System::Windows::Forms::Keys::S));
			this->commandeEnregistrer->Size = System::Drawing::Size(269, 22);
			this->commandeEnregistrer->Text = L"&Enregistrer";
			this->commandeEnregistrer->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_Enregistrer);
			// 
			// commandeEnregistrerSous
			// 
			this->commandeEnregistrerSous->Name = L"commandeEnregistrerSous";
			this->commandeEnregistrerSous->Size = System::Drawing::Size(269, 22);
			this->commandeEnregistrerSous->Text = L"Enregistrer &sous...";
			this->commandeEnregistrerSous->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_EnregistrerSous);
			// 
			// toolStripSeparator2
			// 
			this->toolStripSeparator2->Name = L"toolStripSeparator2";
			this->toolStripSeparator2->Size = System::Drawing::Size(266, 6);
			// 
			// quitterToolStripMenuItem
			// 
			this->quitterToolStripMenuItem->Name = L"quitterToolStripMenuItem";
			this->quitterToolStripMenuItem->ShortcutKeys = static_cast<System::Windows::Forms::Keys>((System::Windows::Forms::Keys::Alt | System::Windows::Forms::Keys::F4));
			this->quitterToolStripMenuItem->Size = System::Drawing::Size(269, 22);
			this->quitterToolStripMenuItem->Text = L"&Quitter";
			this->quitterToolStripMenuItem->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_Quitter);
			// 
			// fenêtreToolStripMenuItem
			// 
			this->fenêtreToolStripMenuItem->Name = L"fenêtreToolStripMenuItem";
			this->fenêtreToolStripMenuItem->Size = System::Drawing::Size(70, 22);
			this->fenêtreToolStripMenuItem->Text = L"Fe&nêtre";
			// 
			// toolStrip1
			// 
			this->toolStrip1->Items->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(16) {this->nouveauToolStripButton, 
				this->ouvrirToolStripButton, this->enregistrerToolStripButton, this->toolStripSeparator, this->boutonCrayon, this->boutonLigne, 
				this->boutonRectangle, this->boutonEllipse, this->boutonPotPeinture, this->boutonTexte, this->boutonLoupe, this->toolStripSeparator1, 
				this->boutonTailleOutil, this->boutonCouleur, this->boutonPolice, this->zoneTexte});
			this->toolStrip1->Location = System::Drawing::Point(0, 26);
			this->toolStrip1->Name = L"toolStrip1";
			this->toolStrip1->RenderMode = System::Windows::Forms::ToolStripRenderMode::System;
			this->toolStrip1->Size = System::Drawing::Size(531, 25);
			this->toolStrip1->TabIndex = 3;
			this->toolStrip1->Text = L"toolStrip1";
			// 
			// nouveauToolStripButton
			// 
			this->nouveauToolStripButton->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->nouveauToolStripButton->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"nouveauToolStripButton.Image")));
			this->nouveauToolStripButton->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->nouveauToolStripButton->Name = L"nouveauToolStripButton";
			this->nouveauToolStripButton->Size = System::Drawing::Size(23, 22);
			this->nouveauToolStripButton->Text = L"&Nouveau";
			this->nouveauToolStripButton->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_NouveauDocument);
			// 
			// ouvrirToolStripButton
			// 
			this->ouvrirToolStripButton->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->ouvrirToolStripButton->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"ouvrirToolStripButton.Image")));
			this->ouvrirToolStripButton->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->ouvrirToolStripButton->Name = L"ouvrirToolStripButton";
			this->ouvrirToolStripButton->Size = System::Drawing::Size(23, 22);
			this->ouvrirToolStripButton->Text = L"&Ouvrir";
			this->ouvrirToolStripButton->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_Ouvrir);
			// 
			// enregistrerToolStripButton
			// 
			this->enregistrerToolStripButton->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->enregistrerToolStripButton->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"enregistrerToolStripButton.Image")));
			this->enregistrerToolStripButton->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->enregistrerToolStripButton->Name = L"enregistrerToolStripButton";
			this->enregistrerToolStripButton->Size = System::Drawing::Size(23, 22);
			this->enregistrerToolStripButton->Text = L"&Enregistrer";
			this->enregistrerToolStripButton->Click += gcnew System::EventHandler(this, &FenetreApp::Fichier_Enregistrer);
			// 
			// toolStripSeparator
			// 
			this->toolStripSeparator->Name = L"toolStripSeparator";
			this->toolStripSeparator->Size = System::Drawing::Size(6, 25);
			// 
			// boutonCrayon
			// 
			this->boutonCrayon->Checked = true;
			this->boutonCrayon->CheckState = System::Windows::Forms::CheckState::Checked;
			this->boutonCrayon->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->boutonCrayon->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonCrayon.Image")));
			this->boutonCrayon->ImageTransparentColor = System::Drawing::Color::White;
			this->boutonCrayon->Name = L"boutonCrayon";
			this->boutonCrayon->Size = System::Drawing::Size(23, 22);
			this->boutonCrayon->Text = L"toolStripButton1";
			this->boutonCrayon->ToolTipText = L"outil crayon";
			this->boutonCrayon->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementOutil);
			// 
			// boutonLigne
			// 
			this->boutonLigne->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->boutonLigne->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonLigne.Image")));
			this->boutonLigne->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->boutonLigne->Name = L"boutonLigne";
			this->boutonLigne->Size = System::Drawing::Size(23, 22);
			this->boutonLigne->Text = L"toolStripButton1";
			this->boutonLigne->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementOutil);
			// 
			// boutonRectangle
			// 
			this->boutonRectangle->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->boutonRectangle->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonRectangle.Image")));
			this->boutonRectangle->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->boutonRectangle->Name = L"boutonRectangle";
			this->boutonRectangle->Size = System::Drawing::Size(23, 22);
			this->boutonRectangle->Text = L"toolStripButton2";
			this->boutonRectangle->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementOutil);
			// 
			// boutonEllipse
			// 
			this->boutonEllipse->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->boutonEllipse->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonEllipse.Image")));
			this->boutonEllipse->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->boutonEllipse->Name = L"boutonEllipse";
			this->boutonEllipse->Size = System::Drawing::Size(23, 22);
			this->boutonEllipse->Text = L"toolStripButton3";
			this->boutonEllipse->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementOutil);
			// 
			// boutonPotPeinture
			// 
			this->boutonPotPeinture->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->boutonPotPeinture->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonPotPeinture.Image")));
			this->boutonPotPeinture->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->boutonPotPeinture->Name = L"boutonPotPeinture";
			this->boutonPotPeinture->Size = System::Drawing::Size(23, 22);
			this->boutonPotPeinture->Text = L"toolStripButton4";
			this->boutonPotPeinture->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementOutil);
			// 
			// boutonTexte
			// 
			this->boutonTexte->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->boutonTexte->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonTexte.Image")));
			this->boutonTexte->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->boutonTexte->Name = L"boutonTexte";
			this->boutonTexte->Size = System::Drawing::Size(23, 22);
			this->boutonTexte->Text = L"toolStripButton5";
			this->boutonTexte->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementOutil);
			// 
			// boutonLoupe
			// 
			this->boutonLoupe->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->boutonLoupe->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonLoupe.Image")));
			this->boutonLoupe->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->boutonLoupe->Name = L"boutonLoupe";
			this->boutonLoupe->Size = System::Drawing::Size(23, 22);
			this->boutonLoupe->Text = L"toolStripButton6";
			this->boutonLoupe->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementOutil);
			// 
			// toolStripSeparator1
			// 
			this->toolStripSeparator1->Name = L"toolStripSeparator1";
			this->toolStripSeparator1->Size = System::Drawing::Size(6, 25);
			// 
			// boutonTailleOutil
			// 
			this->boutonTailleOutil->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Image;
			this->boutonTailleOutil->DropDownItems->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(6) {this->bouton1pixel, 
				this->bouton2pixels, this->bouton4pixels, this->bouton6pixels, this->bouton8pixels, this->bouton10pixels});
			this->boutonTailleOutil->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonTailleOutil.Image")));
			this->boutonTailleOutil->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->boutonTailleOutil->Name = L"boutonTailleOutil";
			this->boutonTailleOutil->Size = System::Drawing::Size(29, 22);
			this->boutonTailleOutil->Text = L"toolStripDropDownButton1";
			// 
			// bouton1pixel
			// 
			this->bouton1pixel->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"bouton1pixel.Image")));
			this->bouton1pixel->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->bouton1pixel->Name = L"bouton1pixel";
			this->bouton1pixel->Size = System::Drawing::Size(132, 22);
			this->bouton1pixel->Text = L"1 pixel";
			this->bouton1pixel->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementTailleOutil);
			// 
			// bouton2pixels
			// 
			this->bouton2pixels->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"bouton2pixels.Image")));
			this->bouton2pixels->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->bouton2pixels->Name = L"bouton2pixels";
			this->bouton2pixels->Size = System::Drawing::Size(132, 22);
			this->bouton2pixels->Text = L"2 pixels";
			this->bouton2pixels->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementTailleOutil);
			// 
			// bouton4pixels
			// 
			this->bouton4pixels->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"bouton4pixels.Image")));
			this->bouton4pixels->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->bouton4pixels->Name = L"bouton4pixels";
			this->bouton4pixels->Size = System::Drawing::Size(132, 22);
			this->bouton4pixels->Text = L"4 pixels";
			this->bouton4pixels->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementTailleOutil);
			// 
			// bouton6pixels
			// 
			this->bouton6pixels->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"bouton6pixels.Image")));
			this->bouton6pixels->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->bouton6pixels->Name = L"bouton6pixels";
			this->bouton6pixels->Size = System::Drawing::Size(132, 22);
			this->bouton6pixels->Text = L"6 pixels";
			this->bouton6pixels->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementTailleOutil);
			// 
			// bouton8pixels
			// 
			this->bouton8pixels->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"bouton8pixels.Image")));
			this->bouton8pixels->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->bouton8pixels->Name = L"bouton8pixels";
			this->bouton8pixels->Size = System::Drawing::Size(132, 22);
			this->bouton8pixels->Text = L"8 pixels";
			this->bouton8pixels->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementTailleOutil);
			// 
			// bouton10pixels
			// 
			this->bouton10pixels->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"bouton10pixels.Image")));
			this->bouton10pixels->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->bouton10pixels->Name = L"bouton10pixels";
			this->bouton10pixels->Size = System::Drawing::Size(132, 22);
			this->bouton10pixels->Text = L"10 pixels";
			this->bouton10pixels->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementTailleOutil);
			// 
			// boutonCouleur
			// 
			this->boutonCouleur->BackColor = System::Drawing::Color::Red;
			this->boutonCouleur->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::None;
			this->boutonCouleur->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonCouleur.Image")));
			this->boutonCouleur->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->boutonCouleur->Name = L"boutonCouleur";
			this->boutonCouleur->Size = System::Drawing::Size(23, 22);
			this->boutonCouleur->Text = L"toolStripButton1";
			this->boutonCouleur->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementCouleur);
			// 
			// boutonPolice
			// 
			this->boutonPolice->DisplayStyle = System::Windows::Forms::ToolStripItemDisplayStyle::Text;
			this->boutonPolice->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"boutonPolice.Image")));
			this->boutonPolice->ImageTransparentColor = System::Drawing::Color::Magenta;
			this->boutonPolice->Name = L"boutonPolice";
			this->boutonPolice->Size = System::Drawing::Size(51, 22);
			this->boutonPolice->Text = L"Arial 8";
			this->boutonPolice->Click += gcnew System::EventHandler(this, &FenetreApp::ChangementPolice);
			// 
			// zoneTexte
			// 
			this->zoneTexte->Name = L"zoneTexte";
			this->zoneTexte->Size = System::Drawing::Size(100, 25);
			this->zoneTexte->Text = L"Votre texte ici";
			// 
			// FenetreApp
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(8, 16);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(531, 229);
			this->Controls->Add(this->toolStrip1);
			this->Controls->Add(this->menuStrip1);
			this->IsMdiContainer = true;
			this->MainMenuStrip = this->menuStrip1;
			this->Name = L"FenetreApp";
			this->Text = L"Gouache";
			this->WindowState = System::Windows::Forms::FormWindowState::Maximized;
			this->menuStrip1->ResumeLayout(false);
			this->menuStrip1->PerformLayout();
			this->toolStrip1->ResumeLayout(false);
			this->toolStrip1->PerformLayout();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion

	private:
		// liste des documents
		ArrayList^ listeDocuments;

		// configuration des outils
		Outil pvOutil;
		Color pvCouleur;
		int pvTailleOutil;
		Drawing::Font^ pvPolice;		

	public:
		property Outil outil {
			Outil get() { return pvOutil; }
		}
		property Color couleur {
			Color get() { return pvCouleur; }
		}
		property int tailleOutil {
			int get () { return pvTailleOutil; }
		}
		property Drawing::Font^ police {
			Drawing::Font^ get() { return (Drawing::Font^)pvPolice->Clone(); }
		}
		property String^ texte {
			String^ get() { return zoneTexte->Text; }
		}

		bool Enregistrer (Document^ document);
		bool EnregistrerSous (Document^ document);

		bool AutorisationFermerDocument (Document^ document);



	private:
		// commandes
		Void Fichier_NouveauDocument (Object^ sender, EventArgs^ e);
		Void Fichier_Ouvrir (Object^ sender, EventArgs^ e);
		Void Fichier_FermerDocument (Object^ sender, EventArgs^ e);
		Void Fichier_NouvelleVue (Object^ sender, EventArgs^ e);
		Void Fichier_FermerVue (Object^ sender, EventArgs^ e);
		Void Fichier_Enregistrer (Object^ sender, EventArgs^ e);
		Void Fichier_EnregistrerSous (Object^ sender, EventArgs^ e);
		Void Fichier_Quitter (Object^ sender, EventArgs^ e);

		// ouverture du menu
		Void OuvertureMenuFichier (Object^ sender, EventArgs^ e);

		// sélection d'un outil
		Void ChangementOutil (Object^ sender, EventArgs^ e);

		// configuration des outils
		Void ChangementTailleOutil(Object^ sender, EventArgs^ e);
		Void ChangementCouleur(Object^ sender, EventArgs^ e);
		Void ChangementPolice(Object^ sender, EventArgs^ e);
};
}
