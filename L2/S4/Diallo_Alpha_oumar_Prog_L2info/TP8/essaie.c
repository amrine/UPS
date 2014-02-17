#include <stdio.h>
#include <stdlib.h>

#include "elt.h"
#include "file.h"
#include "arbre.h"

int main () 
{
    int i,cpt=0;
    int elem;
    T_file yop;
    FILE *fp;
    FILE *rc;
    int nblue;
    T_arbre a;
    T_tab t;
    T_element donnee;
    				/* Initialisation de la pile */
     
    fp=fopen("yechan.txt", "r");			/* On ouvre le fichier yechan.txt */
     if (fp == NULL) {					/* Si erreur */
        printf("Fichier yechan.txt non present\n");
        return 1;
    }
    
    initialiser_file (&yop);		/* initialisation de la file */
    initialiser_arbre(&a);		/* initialisation de l'arbre */
    
  
    while (!feof(fp))
    {		
	  fscanf(fp,"%d",&nblue);
	  Enfiler(&yop,nblue); 
	  cpt++;
	  if (cpt==TAILLEMAX-1)
	  { 
	    printf("Voici le file\n");
	    afficher_file(yop);
	    
	    while (!file_vide(yop))
	    {	
	      t= defiler(&yop);
	      if (!appartient_rec(a , t))
	      {
		ajouter_rec(&a , t);
	      }
	    }
	    printf("\n\n\nVoici l'arbre\n");
	    afficher_arbre(a);
	    
	    cpt=0;
	  }
    }
    
      
    rc=fopen("out.txt","w+");
     if (rc == NULL) {					/* Si erreur */
        printf("Fichier yechan.txt non present\n");
        return 1;
    }
   
      while (!est_vide(a)) 
      {
	donnee = supprimer_min_ite(&a);
	fprintf(rc , "%3d", donnee);
      }
    
   
	
	printf("\n");
    return(0);
}
    
    
       
       
       

     
    /* printf("Entrez 10 valeurs \n");

    
      for (i=0; i<10; i++) {
	  scanf("%d",&elem);
          Enfiler(&yop,elem);
      }
      afficher_file(yop);
      return(0);
}*/