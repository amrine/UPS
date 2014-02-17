#pragma once

using namespace System;
using namespace System::ComponentModel;
using namespace System::Collections;
using namespace System::Windows::Forms;
using namespace System::Data;
using namespace System::Drawing;


namespace gouache {

	/// <summary>
	/// Description résumée de Vue
	///
	/// AVERTISSEMENT : si vous modifiez le nom de cette classe, vous devrez modifier la
	///          propriété 'Nom du fichier de ressources' de l'outil de compilation de ressource managée
	///          pour tous les fichiers .resx dont dépend cette classe. Dans le cas contraire,
	///          les concepteurs ne pourront pas interagir correctement avec les ressources
	///          localisées associées à ce formulaire.
	/// </summary>
	public ref class Vue : public System::Windows::Forms::Form
	{
	public:
		Vue(Document^ document)
		{
			InitializeComponent();
			//
			pvDoc = document;
			zoom = 1.0f;
			traceEnCours = false;
		}

	protected:
		/// <summary>
		/// Nettoyage des ressources utilisées.
		/// </summary>
		~Vue()
		{
			if (components)
			{
				delete components;
			}
		}

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
			this->SuspendLayout();
			// 
			// Vue
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(8, 16);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(292, 252);
			this->Cursor = System::Windows::Forms::Cursors::Cross;
			this->DoubleBuffered = true;
			this->Name = L"Vue";
			this->Text = L"Vue";
			this->Paint += gcnew System::Windows::Forms::PaintEventHandler(this, &Vue::RedessineVue);
			this->MouseUp += gcnew System::Windows::Forms::MouseEventHandler(this, &Vue::RelacheSouris);
			this->FormClosing += gcnew System::Windows::Forms::FormClosingEventHandler(this, &Vue::FermetureVue);
			this->MouseMove += gcnew System::Windows::Forms::MouseEventHandler(this, &Vue::DeplaceSouris);
			this->MouseDown += gcnew System::Windows::Forms::MouseEventHandler(this, &Vue::AppuieSouris);
			this->ResumeLayout(false);

		}
#pragma endregion

	private:
		// valeur de zoom propre à la vue
		float zoom;
		// handle vers le document représenté dans la vue
		Document^ pvDoc;

		// point de départ d'un tracé
		Point pointDepart;
		// point d'arrivée d'un tracé
		Point pointArrivee;
		// un tracé est-il en cours ?
		bool traceEnCours;
		
	public:
		property Document^ doc {
			Document^ get() { return pvDoc; }
		}


	private: 


		Void FermetureVue(Object^ sender, FormClosingEventArgs^ e);
		Void RedessineVue(Object^ sender, PaintEventArgs^ e);

		Void AppuieSouris (Object^  sender, MouseEventArgs^  e);
		Void DeplaceSouris(Object^  sender, MouseEventArgs^  e);
		Void RelacheSouris(Object^  sender, MouseEventArgs^  e);
	};
}
