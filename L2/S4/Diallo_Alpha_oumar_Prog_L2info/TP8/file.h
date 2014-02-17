#include <stdio.h>
#include <stdbool.h>
#include "elt.h"
#define TAILLEMAX 20

typedef T_element T_tab;

typedef struct {
      int tete;
      int queue;
      T_tab tab[TAILLEMAX];;
}T_file;

void initialiser_file (T_file *f);

bool file_vide (T_file f);

bool file_pleine (T_file f);

void Enfiler (T_file *f, T_tab elem) ;

T_tab defiler (T_file *f);

T_tab tete_file (T_file f);

void afficher_file (T_file f);