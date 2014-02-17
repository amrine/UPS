#include <iostream>
using namespace std;
#include <limits>     // inclusion pour numeric_limits
#include <iomanip>    // inclusion pour setfill et setw
#include "GL/glut.h"  // inclusion pour OpenGL et GLUT


const int TAILLE_MAX_CHAINE = 16; //taille maximale de la chaine
int NB_ELEVE; //nombre d'eleve dans la classe

//Structure eleve
struct Eleve
{
	char nom[TAILLE_MAX_CHAINE]; //nom de l'eleve
	char preNom[TAILLE_MAX_CHAINE]; //prenom de l'eleve
	float moyenne; //moyenne de l'eleve
};

Eleve *tab_eleve = NULL;// tableau d'élèves déclaré global

// Déclarations des fonctions
void SaisieEleve(Eleve & param);
void Graphique();
void GraphiqueAffichage();
void GraphiqueRedim(int x, int y);
float CalculMoyenneGenerale();  

void vider_tampon_cin()
{
	cin.clear();
	cin.ignore(numeric_limits<streamsize>::max(), '\n');
}
void forcer_int(int& var, char msg[] = NULL)
{
	while (!(cin >> var) || (cin.peek() != '\n'))
	{
		if(msg != NULL)
		{
			cout << msg;
		}
		vider_tampon_cin();
	}
}
void SaisieEleve(Eleve& var_eleve)
{
	//saisie du nom et du prenom
	cout << "Entrez le nom : ";
	cin >> ws;
	cin.get(var_eleve.nom, TAILLE_MAX_CHAINE);
	vider_tampon_cin();
	cout << "Entrez le prenom : ";
	cin >> ws;
	cin.get(var_eleve.preNom, TAILLE_MAX_CHAINE);
	vider_tampon_cin();
	//saisie de la moyenne
	cout << "Entrez la moyenne : ";
	//on force la moyenne entre 0 et 20
	while (!(cin >> var_eleve.moyenne) || (cin.peek() != '\n') 
		|| var_eleve.moyenne < 0 || var_eleve.moyenne > 20)
	{
		cout << "Valeur incorrecte ! veuillez la saisir a nouveau : ";
		vider_tampon_cin();
	}
	//recapitulatif
	cout << "L'eleve " << var_eleve.nom << " " << var_eleve.preNom
		<< " a " << var_eleve.moyenne 
		<< " moyenne." << endl;
}
void menu()
{
	bool boucler(true);
	while (boucler)
	{
		system("cls"); //on efface l'ecran
		cout << "********* Menu *********" << endl;
		cout << "1 - Afficher la classe" <<endl;
		cout << "2 - Modifier un eleve" <<endl;
		cout << "3 - Afficher le graphique" <<endl;
		cout << "4 - Quitter le programme" <<endl;

		//gestion du choix
		int choix;
		cout << "Entrez votre choix : ";
		do
		{
			forcer_int(choix, "Choix incorrecte ! veuillez le saisir a nouveau : ");
			vider_tampon_cin();
			if (choix < 1 || choix > 4)
			{
				cout << "Choix incorrecte ! veuillez le saisir a nouveau : ";
			}else
			{
				break;
			}
		} while (true);

		switch (choix)
		{
		case 1 :{
			//afficher la classe
			system("cls"); //on efface l'ecran
			for (int i = 0; i < NB_ELEVE; i++)
			{
				cout << setfill(' ') << setw(2) << i+1 << " : " 
					<< setw(20) << tab_eleve[i].nom << setw(20)
					<< tab_eleve[i].preNom << "\t"
					<< tab_eleve[i].moyenne << endl;
			}
			system("pause"); //attente
			break;
				}
		case 2 :{
			//modifier un 
			system("cls"); //on efface l'ecran
			int numero;
			cout << "Entrez le numero de l'eleve : ";
			do
			{
				forcer_int(numero, "Incorrecte ! veuillez entrez un numero juste : ");
				if (numero < 1 || numero > NB_ELEVE)
				{
					cout << "Incorrecte ! veuillez entrez un numero juste : ";
				}else
				{
					break;
				}
			} while (true);
			SaisieEleve(tab_eleve[numero-1]);
			system("pause");
			break;
				}
		case 3 :{
			//afficher le graphique
			Graphique();
			break;
				}
		case 4 :{
			//quitter le programme
			boucler = false;
			break;
				}
		}
	}
}

float CalculMoyenneGenerale()
{
	float moyenG = 0.0f; //initialisation de la moyenne
	for (int i = 0; i < NB_ELEVE; i++)
	{
		moyenG += tab_eleve[i].moyenne;
	}
	moyenG /= NB_ELEVE;
	return moyenG;
}
/* Gestion de graphique */
void Graphique()
{
	/* Création de la fenêtre */
	glutInitWindowPosition(10, 10); // position de la fenêtre
	glutInitWindowSize(640, 480);   // taille de la fenêtre
	// type d'affichage
	glutInitDisplayMode(GLUT_RGBA | GLUT_SINGLE);
	// affichage de la fenêtre
	glutCreateWindow("Graphique");

    // fonction d'affichage
    glutDisplayFunc(GraphiqueAffichage);
    // fonction de redimensionnement
    glutReshapeFunc(GraphiqueRedim);
    
    // lance la boucle de traitement GLUT
    glutMainLoop();
}

void GraphiqueAffichage()
{
	//couleur de fond
	glClearColor(1.0, 1.0, 1.0, 1.0);
	//efface l'ecran
	glClear(GL_COLOR_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
    //instructions d'affichage
    glColor3d(0.0, 0.0, 0.0); //passe en couleur noire
    glBegin(GL_LINE_STRIP); //commence l'affichage d'une ligne brisee
    //placement des points
    for(int i=0; i< NB_ELEVE; i++)
        glVertex2d(i, tab_eleve[i].moyenne);
    glEnd(); //termine la ligne brisee

	//lignes de repere
	glColor3d(0.3, 0.3, 1.0); //couleur bleu clair
	glBegin(GL_LINES); //dessin de ligne
	//positionnement des reperes
	for (int i = 0; i <= 20; i+=5)
	{
		glVertex2d(0, i);
		glVertex2d(NB_ELEVE-1, i);
	}
	glEnd(); //termine l'edition des lignes de repere

	//affichage moyenne generale
	float moyenG = CalculMoyenneGenerale();
	glBegin(GL_LINES);
	glColor3d(1.0, 0.0, 0.0); //couleur rouge
	glVertex2d(0, moyenG);
	glVertex2d(NB_ELEVE-1, moyenG);
	glEnd();
	//fin affichage
	glFlush();

}
void GraphiqueRedim(int x, int y)
{
	glViewport(10, 10, x-20, y-20);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	// redimensionnement proportionnel au nombre d'élèves
    gluOrtho2D(0.0, NB_ELEVE-1, 0.0, 20.1);
}

void main(void)
{
	cout << "Entrez le nombre d'eleve de la classe : ";
	while (!(cin >> NB_ELEVE) || (cin.peek() != '\n') || NB_ELEVE <= 1)
	{
		cout << "Erreur ! le nombre de l'eleve doit etre superieur a 1 : ";
		vider_tampon_cin();
	}
	//allocation dynamique du tableau
	tab_eleve = new Eleve[NB_ELEVE];
	if(tab_eleve == NULL)
	{
		cout << "L'allocation du tableau a echoue !!" << endl;
		exit(EXIT_FAILURE);
	}
	cout << endl << "Saisie du nom, prenom, moyenne des " << NB_ELEVE
		<< " eleves de la classe :" << endl;
	for (int i(0); i < NB_ELEVE; i++)
	{
		SaisieEleve(tab_eleve[i]);
	}
	cout << "Fin de la saisie des " << NB_ELEVE << " eleves" << endl;
	system("pause");
	menu();
	//libere la memoire du tableau
	delete[] tab_eleve;
}
