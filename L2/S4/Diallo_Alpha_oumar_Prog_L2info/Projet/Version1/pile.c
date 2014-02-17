#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>  /*contient la constante EXIT_FAILURE*/


#include "amazone.h"
#include "pile.h"

void initialiser_pile(T_pile *p) /*initialisation de la pile a NULL*/
{
 *p = NULL;   
}

bool pile_vide(T_pile p)
{
    return (p == NULL);  
}

void afficher_pile(T_pile p)
{
	/*si la pile est vide on sort du programme*/
	if (pile_vide(p)){
		printf("pile vide !\n");
	}
	else{
		T_pile tmp = p;
		while (tmp != NULL){ /*tant qu'on est pas au bout de la liste*/
			printf("%d  :",tmp->nbres_coups);
			printf("Coordonnees Depart ");
			printf("%d-" ,tmp->dep.abs);
			printf("%d" ,tmp->dep.ord);
			printf("Coordoonnes Arrive ");
			printf("%d-",tmp->coor.abs);
			printf("%d\n",tmp->coor.ord);
			tmp = tmp->next; /*on avance d'une case*/
		}
	}
}

void empiler(T_pile *p, coor_amazones position , coor_amazones depart, int nb_coups) 
{
	T_pile tmp;
	tmp = *p;
	*p = (T_pile)malloc(sizeof(struct cellule));
	if (pile_vide(*p)){
		fprintf(stderr,"\nempiler : pile vide !\n");
	}
	else{
	  (*(*p)).coor.abs = position.abs;
	  (*(*p)).coor.ord = position.ord;
	  (*(*p)).dep.abs = depart.abs;
	  (*(*p)).dep.ord = depart.ord;
	  (*(*p)).nbres_coups = nb_coups;
	   (*(*p)).next=tmp;
	}
}

