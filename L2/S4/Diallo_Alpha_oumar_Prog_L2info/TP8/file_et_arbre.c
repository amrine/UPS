#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#include "arbre.h"
#include "elt.h"   //le type element doit �tre ordonn� (int par exemple)
#include "pile.h"

void donnee_entre_utilisateur ()
{
  FILE *fichier;
  int elem;
  
  