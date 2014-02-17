#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>  /*contient la constante EXIT_FAILURE*/

#include "file.h"
#include "elt.h"



void initialiser_file (T_file *f)
{
    f->tete=f->queue=0;
}

bool file_vide (T_file f)
{
    return(f.tete==f.queue);
}

bool file_pleine (T_file f)
{     
    return((f.queue+1)%TAILLEMAX == f.tete);
}

void Enfiler (T_file *f, T_tab elem) 
{
    if (file_pleine(*f))
    {
	  fprintf(stderr,"\nempiler : file pleine !\n");
	  exit(EXIT_FAILURE);
    }
    else
    {
      f->tab[f->queue]=elem;
      f->queue=(f->queue+1)%TAILLEMAX; 
    }
}

T_tab defiler (T_file *f)
{
  T_tab elem;
  
    if (file_vide(*f))
    {
	  fprintf(stderr,"\nempiler : file vide !\n");
	  exit(EXIT_FAILURE);
    }
    else
    {
      elem=f->tab[f->tete];
      f->tete=(f->tete+1)%TAILLEMAX;
    }
    return(elem);
}

T_tab tete_file (T_file f)
{
  T_tab elem;
  
  if (file_vide(f))
  {
    fprintf(stderr,"\nempiler : file vide !\n");
	  exit(EXIT_FAILURE);
  }
  else
  {
    elem=f.tab[f.tete];
  }
  return(elem);
}

void afficher_file (T_file f)
{
  int i=0;
  
  while (i<f.queue)
  {
    afficher_element(f.tab[i]);
    i++;
  }
}
    
      