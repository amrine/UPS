#ifndef STACK
#define STACK


#include "amazone.h"
#include <stdbool.h>

/* ----------------------------------
        Déclaration des types
   --------------------------------*/

/* Déclaration du type d'une pile (avec masquage)*/

typedef struct cell* Stack;


/* ----------------------------------
            Constructeurs
   --------------------------------*/

/* Initialisation d'une pile  */
void InitStack(Stack *p);


/* Empiler */
void StackPush(Stack *p, int num, coor_amazones coor_dep, coor_amazones coor_arriv, coor_amazones coor_flech);

void action_homme (t_matrice m, int joueur,int mode,Stack *p,int cpt);
void joueur_vs_ordi(t_matrice m,Stack p,int cpt);
/* Depiler */


/* la pile est-elle vide ? */
bool EmptyStack(Stack p);

void back_in_action(int coup,Stack p,t_matrice m);
void SaveStack(Stack p);
int  StackLoad(Stack *p);
void PrintHisto(int coup,Stack p,t_matrice m);
int LoadHead(int *tete);
void SaveHead(int tete);
int StackHead(Stack p);
#endif




