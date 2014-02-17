#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>  /*contient la constante EXIT_FAILURE*/

#include "stack.h"
#include "stdbool.h"

typedef struct cell {
	int num;
	coor_amazones dep;
	coor_amazones arriv;
	coor_amazones flech;
	struct cell *suiv;
	}cell;  
	/*cell= structure a deux champs (T_element,ptr sur cell)*/



void InitStack(Stack *p) /*initialisation de la pile a NULL*/
{
 *p = NULL;   
}

bool EmptyStack(Stack p)
{
    return (p == NULL);  
}



void StackPush(Stack *p, int num, coor_amazones coor_dep, coor_amazones coor_arriv, coor_amazones coor_flech) /*ajouter un element a la pile*/
{
	/*on cree une pile temporaire*/
	Stack tmp;
	tmp = *p;
	*p = (Stack)malloc(sizeof(struct cell));
	/*verifie si malloc n'a pas retourne NULL*/
	if (EmptyStack(*p)){
		fprintf(stderr,"\nempiler : pile vide !\n");
		exit(EXIT_FAILURE);
	}
	else{
		(*(*p)).num = num; /*On assigne la numero au nouvel élément*/
		(*(*p)).dep.ord = coor_dep.ord; 
		(*(*p)).dep.abs = coor_dep.abs; 
		(*(*p)).arriv.ord = coor_arriv.ord; 
		(*(*p)).arriv.abs = coor_arriv.abs;
		(*(*p)).flech.ord = coor_flech.ord; 
		(*(*p)).flech.abs = coor_flech.abs;
		(*(*p)).suiv = tmp;/*On assigne tmp a l'element suiv donc on met jour la pile*/
	}
}



void back_in_action(int coup,Stack p,t_matrice m){
  
  	Stack tmp = p;
	Stack tmp2;
	/*si la pile est vide on sort du programme*/
	if (EmptyStack(p)){
		printw("\nAucune Annulation Possible\n");
		refresh();
	}
	else{
	    while (!EmptyStack(tmp)) {
	      if (tmp->num == coup) tmp2= tmp;
	      tmp=tmp->suiv;
	  } if (tmp2->num == coup){
	      m[tmp2->dep.ord][tmp2->dep.abs] = m[tmp2->arriv.ord][tmp2->arriv.abs];
	      m[tmp2->arriv.ord][tmp2->arriv.abs] = VIDE;
	      m[tmp2->flech.ord][tmp2->flech.abs] = VIDE;
	  }
	else {
	      printw("Ce Coup n'a pas ete joue\n"); refresh();
	    }
	}
}

void PrintHisto(int coup,Stack p,t_matrice m){
  
  	Stack tmp = p;
	Stack tmp2;
	/*si la pile est vide on sort du programme*/
	if (EmptyStack(p)){
		printw("\nAucun Historique Disponible");
		refresh();
	}
	else{
	    while (!EmptyStack(tmp)) {
	      if (tmp->num == coup) tmp2= tmp;
	      tmp=tmp->suiv;
	  } if (tmp2->num == coup){
	      erase();
	      afficher_entete(1);
	      afficher_matrice(m);
	      mvprintw(18,0,"\n Coup %d :\n",tmp2->num);
	      printw("depart.ord =%-3d depart.abs =%-3d\n",tmp2->dep.ord,tmp2->dep.abs);
	      printw("arrivee.ord=%-3d arrivee.abs=%-3d\n",tmp2->arriv.ord,tmp2->arriv.abs);
	      printw("fleche.ord =%-3d fleche.abs =%-3d\n\n",tmp2->flech.ord,tmp2->flech.abs);
	      refresh();
	  }
	else {
	      printw("Ce Coup n'a pas ete joue\n"); refresh();
	    }
	}
}


void SaveStack(Stack p){
  
  FILE* fich=NULL;
  Stack tmp=p;
  fich=fopen("stack.txt", "w");
  if (fich != NULL){
    while (!EmptyStack(tmp)) {
	      fputc(tmp->num,fich);
	      fputc(tmp->dep.ord,fich);
	      fputc(tmp->dep.abs,fich);
	      fputc(tmp->arriv.ord,fich);
	      fputc(tmp->arriv.abs,fich);
	      fputc(tmp->flech.ord,fich);
	      fputc(tmp->flech.abs,fich);
	      tmp=tmp->suiv;
	  }
  free(tmp); 
  fclose(fich);
  }
  else {
	 printw("Erreur de Creation du Fichier Historique\n");
	 printw("Reprise de la Sauvegarde\n");
	 refresh();
	 SaveStack(p);
  }
}

int StackLoad(Stack *p){
    FILE* fich=NULL;
    coor_amazones dep,arriv,flech;
    int num;
    int caractere;
    fich=fopen("stack.txt", "r");
    if (fich != NULL){
	caractere= fgetc(fich);
	while (caractere != EOF ){
		num = caractere;
		caractere= fgetc(fich);
		dep.ord = caractere;
		caractere= fgetc(fich);
		dep.abs = caractere; 
		caractere= fgetc(fich);
		arriv.ord = caractere; 
		caractere= fgetc(fich);
		arriv.abs = caractere;
		caractere= fgetc(fich);
		flech.ord = caractere; 
		caractere= fgetc(fich);
		flech.abs = caractere;
		StackPush(p,num,dep,arriv,flech);
		caractere= fgetc(fich);	
	  }
	fclose(fich);
	return 1;
    }else {
	 fprintf(stderr,"Erreur de l'Ouverture du Fichier Historique\n");
	 fprintf(stderr,"Aucune Historique n'a ete Trouve\n");
	 return (-1);
    }
}

/*sauvegarde de la tete*/

void SaveHead(int tete)
{
    FILE* save=NULL;
  save=fopen("tete.txt", "w");
  if (save != NULL)
  {
      fputc(tete,save);
    }
    fclose(save);
  }

/*chargement de la valeur de la tete*/

int LoadHead(int *tete)
{
    FILE* load=NULL;
    load=fopen("tete.txt", "r");
    if (load != NULL)
    {
        *tete=fgetc(load);
    
    fclose(load);
    return 1;
    }else return (-1);
}

int StackHead(Stack p) /*recuperer la numeur au sommet de la pile*/
{
	/*si la pile est vide on sort du programme*/
	if (EmptyStack(p)){
		fprintf(stderr,"\nsommeStack: pile vide !\n");
		exit(EXIT_FAILURE);
	}
	else return (p->num);
}
