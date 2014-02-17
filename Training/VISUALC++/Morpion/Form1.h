#pragma once

namespace Morpion {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for Form1
	/// </summary>
	public ref class Form1 : public System::Windows::Forms::Form
	{
	public:
		Form1(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
			this->case_0_2->Tag = Point(0, 2);
			this->case_2_2->Tag = Point(2, 2);
			this->case_1_2->Tag = Point(1, 2);
			this->case_1_1->Tag = Point(1, 1);
			this->case_2_1->Tag = Point(2, 1);
			this->case_0_1->Tag = Point(0, 1);
			this->case_0_0->Tag = Point(0, 0);
			this->case_1_0->Tag = Point(1, 0);
			this->case_2_0->Tag = Point(2, 0);

			nouvellePartie();
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~Form1()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::PictureBox^  case_0_2;
	private: System::Windows::Forms::PictureBox^  case_2_2;
	protected: 

	protected: 

	private: System::Windows::Forms::PictureBox^  case_1_2;

	private: System::Windows::Forms::PictureBox^  case_1_1;
	private: System::Windows::Forms::PictureBox^  case_2_1;


	private: System::Windows::Forms::PictureBox^  case_0_1;
	private: System::Windows::Forms::PictureBox^  case_0_0;
	private: System::Windows::Forms::PictureBox^  case_1_0;
	private: System::Windows::Forms::PictureBox^  case_2_0;
	private: System::Windows::Forms::Button^  boutonNouvellePartie;

	private: System::Windows::Forms::Button^  boutonQuitter;

	private: System::Windows::Forms::Label^  labelNomJoueur1;
	private: System::Windows::Forms::Label^  labelNomJoueur2;
	private: System::Windows::Forms::Label^  labelScoreJoueur1;



	private: System::Windows::Forms::Label^  labelScoreJoueur2;
	private: System::Windows::Forms::Label^  labelJoueurCourant;
	private: System::Windows::Forms::ImageList^  listeImages;
	private: System::ComponentModel::IContainer^  components;








	protected: 

	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>


#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->components = (gcnew System::ComponentModel::Container());
			System::ComponentModel::ComponentResourceManager^  resources = (gcnew System::ComponentModel::ComponentResourceManager(Form1::typeid));
			this->case_0_2 = (gcnew System::Windows::Forms::PictureBox());
			this->case_2_2 = (gcnew System::Windows::Forms::PictureBox());
			this->case_1_2 = (gcnew System::Windows::Forms::PictureBox());
			this->case_1_1 = (gcnew System::Windows::Forms::PictureBox());
			this->case_2_1 = (gcnew System::Windows::Forms::PictureBox());
			this->case_0_1 = (gcnew System::Windows::Forms::PictureBox());
			this->case_0_0 = (gcnew System::Windows::Forms::PictureBox());
			this->case_1_0 = (gcnew System::Windows::Forms::PictureBox());
			this->case_2_0 = (gcnew System::Windows::Forms::PictureBox());
			this->boutonNouvellePartie = (gcnew System::Windows::Forms::Button());
			this->boutonQuitter = (gcnew System::Windows::Forms::Button());
			this->labelNomJoueur1 = (gcnew System::Windows::Forms::Label());
			this->labelNomJoueur2 = (gcnew System::Windows::Forms::Label());
			this->labelScoreJoueur1 = (gcnew System::Windows::Forms::Label());
			this->labelScoreJoueur2 = (gcnew System::Windows::Forms::Label());
			this->labelJoueurCourant = (gcnew System::Windows::Forms::Label());
			this->listeImages = (gcnew System::Windows::Forms::ImageList(this->components));
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_0_2))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_2_2))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_1_2))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_1_1))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_2_1))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_0_1))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_0_0))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_1_0))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_2_0))->BeginInit();
			this->SuspendLayout();
			// 
			// case_0_2
			// 
			this->case_0_2->BackColor = System::Drawing::SystemColors::Window;
			this->case_0_2->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->case_0_2->Location = System::Drawing::Point(161, 73);
			this->case_0_2->Name = L"case_0_2";
			this->case_0_2->Size = System::Drawing::Size(60, 60);
			this->case_0_2->TabIndex = 0;
			this->case_0_2->TabStop = false;
			this->case_0_2->Click += gcnew System::EventHandler(this, &Form1::cliqueCase);
			// 
			// case_2_2
			// 
			this->case_2_2->BackColor = System::Drawing::SystemColors::Window;
			this->case_2_2->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->case_2_2->Location = System::Drawing::Point(311, 73);
			this->case_2_2->Name = L"case_2_2";
			this->case_2_2->Size = System::Drawing::Size(60, 60);
			this->case_2_2->TabIndex = 1;
			this->case_2_2->TabStop = false;
			this->case_2_2->Click += gcnew System::EventHandler(this, &Form1::cliqueCase);
			// 
			// case_1_2
			// 
			this->case_1_2->BackColor = System::Drawing::SystemColors::Window;
			this->case_1_2->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->case_1_2->Location = System::Drawing::Point(236, 73);
			this->case_1_2->Name = L"case_1_2";
			this->case_1_2->Size = System::Drawing::Size(60, 60);
			this->case_1_2->TabIndex = 2;
			this->case_1_2->TabStop = false;
			this->case_1_2->Click += gcnew System::EventHandler(this, &Form1::cliqueCase);
			// 
			// case_1_1
			// 
			this->case_1_1->BackColor = System::Drawing::SystemColors::Window;
			this->case_1_1->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->case_1_1->Location = System::Drawing::Point(236, 139);
			this->case_1_1->Name = L"case_1_1";
			this->case_1_1->Size = System::Drawing::Size(60, 60);
			this->case_1_1->TabIndex = 3;
			this->case_1_1->TabStop = false;
			this->case_1_1->Click += gcnew System::EventHandler(this, &Form1::cliqueCase);
			// 
			// case_2_1
			// 
			this->case_2_1->BackColor = System::Drawing::SystemColors::Window;
			this->case_2_1->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->case_2_1->Location = System::Drawing::Point(311, 139);
			this->case_2_1->Name = L"case_2_1";
			this->case_2_1->Size = System::Drawing::Size(60, 60);
			this->case_2_1->TabIndex = 4;
			this->case_2_1->TabStop = false;
			this->case_2_1->Click += gcnew System::EventHandler(this, &Form1::cliqueCase);
			// 
			// case_0_1
			// 
			this->case_0_1->BackColor = System::Drawing::SystemColors::Window;
			this->case_0_1->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->case_0_1->Location = System::Drawing::Point(161, 139);
			this->case_0_1->Name = L"case_0_1";
			this->case_0_1->Size = System::Drawing::Size(60, 60);
			this->case_0_1->TabIndex = 5;
			this->case_0_1->TabStop = false;
			this->case_0_1->Click += gcnew System::EventHandler(this, &Form1::cliqueCase);
			// 
			// case_0_0
			// 
			this->case_0_0->BackColor = System::Drawing::SystemColors::Window;
			this->case_0_0->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->case_0_0->Location = System::Drawing::Point(161, 205);
			this->case_0_0->Name = L"case_0_0";
			this->case_0_0->Size = System::Drawing::Size(60, 60);
			this->case_0_0->TabIndex = 6;
			this->case_0_0->TabStop = false;
			this->case_0_0->Click += gcnew System::EventHandler(this, &Form1::cliqueCase);
			// 
			// case_1_0
			// 
			this->case_1_0->BackColor = System::Drawing::SystemColors::Window;
			this->case_1_0->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->case_1_0->Location = System::Drawing::Point(236, 205);
			this->case_1_0->Name = L"case_1_0";
			this->case_1_0->Size = System::Drawing::Size(60, 60);
			this->case_1_0->TabIndex = 7;
			this->case_1_0->TabStop = false;
			this->case_1_0->Click += gcnew System::EventHandler(this, &Form1::cliqueCase);
			// 
			// case_2_0
			// 
			this->case_2_0->BackColor = System::Drawing::SystemColors::Window;
			this->case_2_0->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->case_2_0->Location = System::Drawing::Point(311, 205);
			this->case_2_0->Name = L"case_2_0";
			this->case_2_0->Size = System::Drawing::Size(60, 60);
			this->case_2_0->TabIndex = 8;
			this->case_2_0->TabStop = false;
			this->case_2_0->Click += gcnew System::EventHandler(this, &Form1::cliqueCase);
			// 
			// boutonNouvellePartie
			// 
			this->boutonNouvellePartie->Location = System::Drawing::Point(66, 242);
			this->boutonNouvellePartie->Name = L"boutonNouvellePartie";
			this->boutonNouvellePartie->Size = System::Drawing::Size(89, 23);
			this->boutonNouvellePartie->TabIndex = 9;
			this->boutonNouvellePartie->Text = L"Nouvelle Partie";
			this->boutonNouvellePartie->UseVisualStyleBackColor = true;
			this->boutonNouvellePartie->Click += gcnew System::EventHandler(this, &Form1::cliqueBoutonNouvellePartie);
			// 
			// boutonQuitter
			// 
			this->boutonQuitter->Location = System::Drawing::Point(388, 242);
			this->boutonQuitter->Name = L"boutonQuitter";
			this->boutonQuitter->Size = System::Drawing::Size(89, 23);
			this->boutonQuitter->TabIndex = 10;
			this->boutonQuitter->Text = L"Quitter";
			this->boutonQuitter->UseVisualStyleBackColor = true;
			this->boutonQuitter->Click += gcnew System::EventHandler(this, &Form1::cliqueBoutonQuitter);
			// 
			// labelNomJoueur1
			// 
			this->labelNomJoueur1->AutoSize = true;
			this->labelNomJoueur1->Location = System::Drawing::Point(12, 161);
			this->labelNomJoueur1->Name = L"labelNomJoueur1";
			this->labelNomJoueur1->Size = System::Drawing::Size(45, 13);
			this->labelNomJoueur1->TabIndex = 11;
			this->labelNomJoueur1->Text = L"Joueur1";
			// 
			// labelNomJoueur2
			// 
			this->labelNomJoueur2->AutoSize = true;
			this->labelNomJoueur2->Location = System::Drawing::Point(385, 161);
			this->labelNomJoueur2->Name = L"labelNomJoueur2";
			this->labelNomJoueur2->Size = System::Drawing::Size(45, 13);
			this->labelNomJoueur2->TabIndex = 12;
			this->labelNomJoueur2->Text = L"Joueur2";
			// 
			// labelScoreJoueur1
			// 
			this->labelScoreJoueur1->AutoSize = true;
			this->labelScoreJoueur1->Location = System::Drawing::Point(106, 161);
			this->labelScoreJoueur1->Name = L"labelScoreJoueur1";
			this->labelScoreJoueur1->Size = System::Drawing::Size(49, 13);
			this->labelScoreJoueur1->TabIndex = 13;
			this->labelScoreJoueur1->Text = L"1545999";
			// 
			// labelScoreJoueur2
			// 
			this->labelScoreJoueur2->AutoSize = true;
			this->labelScoreJoueur2->Location = System::Drawing::Point(484, 161);
			this->labelScoreJoueur2->Name = L"labelScoreJoueur2";
			this->labelScoreJoueur2->Size = System::Drawing::Size(49, 13);
			this->labelScoreJoueur2->TabIndex = 14;
			this->labelScoreJoueur2->Text = L"1545999";
			// 
			// labelJoueurCourant
			// 
			this->labelJoueurCourant->AutoSize = true;
			this->labelJoueurCourant->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(0)));
			this->labelJoueurCourant->Location = System::Drawing::Point(214, 46);
			this->labelJoueurCourant->Name = L"labelJoueurCourant";
			this->labelJoueurCourant->Size = System::Drawing::Size(112, 15);
			this->labelJoueurCourant->TabIndex = 15;
			this->labelJoueurCourant->Text = L"Le joueur 1 joue";
			// 
			// listeImages
			// 
			this->listeImages->ImageStream = (cli::safe_cast<System::Windows::Forms::ImageListStreamer^  >(resources->GetObject(L"listeImages.ImageStream")));
			this->listeImages->TransparentColor = System::Drawing::Color::Transparent;
			this->listeImages->Images->SetKeyName(0, L"rond.bmp");
			this->listeImages->Images->SetKeyName(1, L"croix.bmp");
			// 
			// Form1
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(545, 311);
			this->Controls->Add(this->labelJoueurCourant);
			this->Controls->Add(this->labelScoreJoueur2);
			this->Controls->Add(this->labelScoreJoueur1);
			this->Controls->Add(this->labelNomJoueur2);
			this->Controls->Add(this->labelNomJoueur1);
			this->Controls->Add(this->boutonQuitter);
			this->Controls->Add(this->boutonNouvellePartie);
			this->Controls->Add(this->case_2_0);
			this->Controls->Add(this->case_1_0);
			this->Controls->Add(this->case_0_0);
			this->Controls->Add(this->case_0_1);
			this->Controls->Add(this->case_2_1);
			this->Controls->Add(this->case_1_1);
			this->Controls->Add(this->case_1_2);
			this->Controls->Add(this->case_2_2);
			this->Controls->Add(this->case_0_2);
			this->FormBorderStyle = System::Windows::Forms::FormBorderStyle::FixedSingle;
			this->MaximizeBox = false;
			this->MinimizeBox = false;
			this->Name = L"Form1";
			this->Text = L"Jeu du Morpion";
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_0_2))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_2_2))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_1_2))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_1_1))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_2_1))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_0_1))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_0_0))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_1_0))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->case_2_0))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
private: 
	Void cliqueBoutonQuitter(System::Object^  sender, System::EventArgs^  e);
	Void cliqueBoutonNouvellePartie(Object^ sender, EventArgs^ e);
	Void cliqueCase(Object^ sender, EventArgs^ e);

	array<Joueur^>^ listeJoueurs; //tableau contenant les 2 objets joueurs
	//handle vers le joueur qui a la main
	Joueur^ joueurCourant;
	//avancement du remplissage de la grille
	array<Joueur^, 2>^ grille; //tableau a 2 dimensions
	int coupJoues;//nombre de coup jouee

	void nouvellePartie(void);
	void nouvelleManche(void);
	void passeLaMain(Joueur^ joueur);
	bool alignement(void);

};
}

