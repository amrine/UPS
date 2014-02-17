#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

#define N 10

typedef int map[N][N] ;

typedef struct coordonnees{
  int abs;
  int ord;
}coor_carte;

bool contenu_ds_carte (coor_carte coor){
  return ((coor.abs >=0 && coor.abs< N) && (coor.ord >=0 && coor.ord < N));
}

void saisir_coor(coor_carte *coor){
  int i;
  char a,abs;
  printf("Coordonnes ( LETTRE, chiffre) :");
  scanf(" %c",&abs);
  scanf("%d",&coor->ord);
  if ((abs >= 'a') && (abs < 'a' + N)){
	/* si abs est une minuscule et qu'il n'est pas superieur a la lettre j on le
		change en majuscule */
	abs = abs + 'A' - 'a';
  }
  i=0;
  for (a='A' ; a<abs ; a++){
	i++;
  }
  coor->abs = i;
  coor->ord --;
  while(!contenu_ds_carte (*coor)){
	/* tant que les coordonnes n'appartiennent pas a la grille, on recommence
		la saisie des coordonnees */
	printf("Coordonnes incorrect ! \nVeuillez recommencer\n");
	saisir_coor(coor);	
  }
}


void afficher_map (map m) {
	int i, j ;
	char a = 'A' ;

	printf ("\nVoici la map:\n\n   ") ;
	for (i=0; i<N; i++) {
		printf ("| %c ", a) ;
		a ++ ;
	}
	printf ("|\n") ;
	for (i=0; i<N; i++) {
		for (j=0; j<=N; j++) {
			printf ("---+") ;
		}
		if (i<9) {
			printf ("\n %d |", i+1) ;
		} else {
			printf ("\n%d |", i+1) ;
		}

		for (j=0; j<N; j++) {
			printf (" %d |", m[i][j]) ;
		}
		printf ("\n") ;
	}
	for (j=0; j<=N; j++) {
		printf ("---+") ;
	}
	printf ("\n\n") ;
}

void initialiser_map (map m) {
	int i, j ;
	srand(time(NULL)) ;

	for (i=0; i<N; i++) {
		for (j=0; j<N; j++) {
			m[i][j] = rand() % N ;
		}
	}
}

void binarise_map( map m){
  int somme=0,moyenne;
  int i,j;
  
  for (i=0; i<N; i++) {
    for (j=0; j<N; j++) {
      somme += m[i][j] ;
    }
  }
  moyenne = somme/(N*N);
  for (i=0; i<N; i++) {
   for (j=0; j<N; j++) {
      if (m[i][j] >= moyenne) m[i][j] = 1;
      else m[i][j] =0;
    }
  }
}


bool path_neighbour (map m, coor_carte *dep){ /*chemin voisin */
  int i,j;
  bool b;
  
  i= dep->ord;
  j= dep->abs;
  b=false;
  
  if (contenu_ds_carte(*dep)){
    if (m[i+1][j] == 1){
      b= true;
      dep->ord = i+1;
      dep->abs = j;
    }
    else if (m[i][j+1] == 1){
      b= true;
      dep->ord = i;
      dep->abs = j+1;
    }
    else if (m[i-1][j] == 1){
      b= true;
      dep->ord = i-1;
      dep->abs = j;
    }
    else if (m[i][j-1] == 1){
      b= true;
      dep->ord = i;
      dep->abs = j-1;
    }
    else b=false;
  }
  else b= false;
  return (b);
}
 
bool path_present (map m, coor_carte dep, coor_carte arriv){
  bool b;
  
  


  
 
  
  



int main(){
  map m;
  initialiser_map(m);
  afficher_map(m);
   binarise_map(m);
    afficher_map(m);
  return 0;
}
  