#include <iostream>
#include <fstream>
using namespace std;
#include <time.h>
#include "Joueur.h"
#include "EnnemiVert.h"
#include "EnnemiRouge.h"
#include "EnnemiBase.h"

enum TYPE_ENNEMI //enumeration pour le type d'ennemi
{
	ENNEMI_VERT, //identificateur ennemi vert
	ENNEMI_ROUGE //identificateur ennemi rouge
};
int NBColonnes(0), NBLignes(0); //taille du niveau
int SortieC(0), SortieL(0); // Coordonnées de sortie du niveau
char** Matrice = NULL; //matrice contenant le niveau
Joueur monJoueur;//le joueur
EnnemiBase* premierEnnemi = NULL;
const int TIMER_MILLIS(500);
void LabyAffichage();
void LabyRedim(int x, int y);
void LabyClavierSpecial(int key, int x, int y);
void LabyTimer(int value);
void OuvrirNiveau(char* nom_fichier);
void AjouterEnnemi(TYPE_ENNEMI type, int C, int L);
void LibererMemoire();
void DessinerNiveau();
void TesteVictoire();

void main(void)
{
	srand((int)time(NULL));
	/*gestion du graphique*/
	glutInitWindowPosition(10,10);//position de la fenetre
	glutInitWindowSize(500, 500); //taille de la fenetre
	/*mode d'affichage (GLUT_SINGLE -> affichage sans rafraichissement 1 tampon)
	(GLUT_DOUBLE -> affichage avec rafraichissement double tampon)
	*/
	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE); 
	glutCreateWindow("LABYRINTHE"); //creation de la fenetre
	glutDisplayFunc(LabyAffichage); //fontion d'affichage
	glutReshapeFunc(LabyRedim); //fontion de redimensionnement
	glutSpecialFunc(LabyClavierSpecial); //ecoute sur les boutons speciaux du clavier
	glutTimerFunc(TIMER_MILLIS, LabyTimer, 0);//timer
	OuvrirNiveau("LEVEL/niveau.txt");
	glutMainLoop(); //boucle
}

void LabyAffichage()
{
	glClearColor(1.0, 1.0, 1.0, 1.0); //definition de la couleur de fond
	glClear(GL_COLOR_BUFFER_BIT); //efface l'ecran
	//matrice de modelisation active pour disposer des elements de la scene
	glMatrixMode(GL_MODELVIEW);
	//instruction d'affichage
	DessinerNiveau();//affiche le niveau
	monJoueur.Dessiner(); //afficher l'avatar du joueur
	//afficahe des ennemis
	EnnemiBase* Pointeur = premierEnnemi;
	for (EnnemiBase* Pointeur = premierEnnemi;
		Pointeur != NULL; Pointeur = Pointeur->GetSuivant())
	{
		//affichage de l'ennemi courant
		Pointeur->Dessiner();
	}

	//glFlush();acheve l'affichage
	//permet de passer d'un tampon à l'autre, il fait appel a glFlush()
	glutSwapBuffers(); 
}
void LabyRedim(int x, int y)
{
	glViewport(0, 0, x, y);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0.0, (double)NBColonnes, (double)NBLignes, 0.0);
}
void LibererMemoire()
{
	if (Matrice != NULL)
	{
		for (int i = 0; i < NBColonnes; i++)
			delete[] Matrice[i];//liberation des colonnes
		//liberation de la ligne des pointeurs
		delete[] Matrice;
	}
	//suppression de la liste chainee
	EnnemiBase* Pointeur = NULL;
	while (premierEnnemi != NULL)
	{
		Pointeur = premierEnnemi; //l'element courant
		premierEnnemi = premierEnnemi->GetSuivant(); //sauvegarde du suivant
		delete Pointeur;//suppression de l'element courant
	}
}
void OuvrirNiveau(char* nom_fichier)
{
	ifstream fichier; //objet ifstream
	fichier.open(nom_fichier); //ouverture en lecture

	//test existence du fichier
	if (!fichier)
	{
		cerr << "Erruer lors de l'ouverture du fichier ! " << endl;
		system("pause");
		exit(EXIT_FAILURE);
	}

	//lecture de la taille du niveau
	fichier >> NBColonnes;
	fichier >> NBLignes;

	//allocation du tableau de niveau
	Matrice = new char*[NBColonnes];
	for (int i = 0; i < NBColonnes; i++)
	{
		Matrice[i] = new char[NBLignes];
	}
	//intialisation de la matrice
	for (int i = 0; i < NBColonnes; i++)
		for (int j = 0; j < NBLignes; j++)
			Matrice[i][j] = '0';
	//lecture du tableau du niveau, caractere par caractere
	for (int i = 0; i < NBLignes; i++)
		for (int j = 0; j < NBColonnes; j++)
		{
			fichier >> Matrice[j][i]; //lecture du caractere
			switch (Matrice[j][i])
			{
			case 'j':
			case 'J':
				//position de depart du joueur
				monJoueur.SetPosC(j);
				monJoueur.SetPosL(i);
				break;
			case 's':
			case 'S':
				SortieC = j;
				SortieL = i;
			case 'v':
			case 'V':
				//ennemi vert
				AjouterEnnemi(ENNEMI_VERT, j, i);
				break;
			case 'r':
			case 'R':
				//ennemi rouge
				AjouterEnnemi(ENNEMI_ROUGE, j, i);
				break;
			}
		}
	//fermeture du fichier
	fichier.close();
}
void DessinerNiveau()
{
	glColor3d(0.5, 0.5, 0.5); //couleur gris
	glBegin(GL_QUADS); //dessin de quadrilatere
	for(int i(0); i < NBColonnes; i++)
		for (int j = 0; j < NBLignes; j++)
		{
			//si c'est un mur, on dessine
			if(Matrice[i][j] == '0')
			{
				//placements des points
				glVertex2d(i, j);
				glVertex2d(i, j+1);
				glVertex2d(i+1, j+1);
				glVertex2d(i+1, j);
			}
		}
	glEnd(); //fin de l'affichage
	//Affichage du point de sortie en vert
	glPushMatrix();
	//se positionner sur la sortie
	glTranslated(SortieC+0.5, SortieL+0.5, 0.0);
	glColor3d(0.3, 1.0, 0.3);//couleur verte
	//dessinde plusieurs carree de taille croissante
	for (double taille = 0.1; taille  < 1.0; taille += 0.2)
		//dessin d'un carre de taille "taille"
		glutWireCube(taille);
	glPopMatrix();
}
void LabyClavierSpecial(int key, int x, int y)
{
	switch (key)
	{
	case GLUT_KEY_UP:
		//haut
		monJoueur.BougerEnHaut();
		break;
	case GLUT_KEY_DOWN:
		//bas
		monJoueur.BougerEnBas();
		break;
	case GLUT_KEY_LEFT:
		//gauche
		monJoueur.BougerAGauche();
		break;
	case GLUT_KEY_RIGHT:
		monJoueur.BougerADroite();
		//droite
		break;
	}
	glutPostRedisplay(); //rafraichir la fenetre
	TesteVictoire(); //le joueur a peut etre gagne
	//le joueur a peut etre ete mange
	EnnemiBase* Pointeur = premierEnnemi;
	while (Pointeur != NULL)
	{
		//teste de collision
		Pointeur->TesteCollision();
		//passage a l'ennemi suivant
		Pointeur = Pointeur->GetSuivant();
	}
}
void TesteVictoire()
{
	if (monJoueur.GetPosC() == SortieC
		&& monJoueur.GetPosL() == SortieL)
	{
		cout << "Vous avez gagne !" << endl;
		LabyAffichage(); //rafraichissement de la scene
		LibererMemoire(); //libere la memeore allouee
		system("pause");
		exit(1);
	}
}
void LabyTimer(int value)
{
	//le joueur a peut etre ete mange
	EnnemiBase* Pointeur = premierEnnemi;
	while (Pointeur != NULL)
	{
		//deplacement auto de l'ennemi
		Pointeur->DeplacementAuto();
		//teste de collision avec le joueur
		Pointeur->TesteCollision();
		//passage a l'ennemi suivant
		Pointeur = Pointeur->GetSuivant();
	}
	//rafraichir la page pour voir le mouvement de l'enneimi dans la scene
	glutPostRedisplay();
	//pour que la fonction timer soit repete
	glutTimerFunc(TIMER_MILLIS, LabyTimer, 0);
}
void AjouterEnnemi(TYPE_ENNEMI type, int C, int L)
{
	EnnemiBase* Pointeur = NULL;
	switch (type)
	{
	case ENNEMI_VERT:
		Pointeur = new EnnemiVert();
		break;
	case ENNEMI_ROUGE:
		Pointeur = new EnnemiRouge();
		break;
	default:
		exit(0);//type inconnu, on quite le programme
	}
	Pointeur->SetPosC(C);
	Pointeur->SetPosL(L);
	//place le nouvel ennemi en tete de liste
	Pointeur->SetSuivant(premierEnnemi);
	//la nouvelle tete est le nouvelle ennemi
	premierEnnemi = Pointeur; //
}