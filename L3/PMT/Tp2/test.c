#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
 
int  main ( ){
  char *pointeur;
  char *separateur = { " " }; /*Le séparateur*/
  char *buffer;
  char *Chaine_Entrante="bonjour je m'appelle Trinita";
  int nb_mot=1
 
  buffer = strdup ( Chaine_Entrante );
 
  /* premier appel,*/
  pointeur = strtok( buffer, separateur );
  printf ("Mot de la phrase numero : %d %s\n",nb_mot, pointeur);
 
  while( pointeur != NULL )
  {
  /*Cherche les autres separateur*/
  pointeur = strtok( NULL, separateur );
  if ( pointeur != NULL )
  {
  nb_mot++; /*increment du nombre de mot*/
  printf ("Mot de la phrase numero : %d %s\n",nb_mot, pointeur);
  }
  }
 
  getchar (); /* pause en mode console*/
 
  return 0 ;
  } 
