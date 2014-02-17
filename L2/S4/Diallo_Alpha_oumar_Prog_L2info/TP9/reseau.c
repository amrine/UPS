#include <stdio.h>
#include <stdlib.h>

#define N 10

typedef int carte[N][N] ;

void afficher_carte (carte m) {
	int i, j ;
	char a = 'A' ;

	printf ("\nVoici la carte:\n\n   ") ;
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

void binarise_carte( carte m){
  int moyenne;
  int i,j;
  