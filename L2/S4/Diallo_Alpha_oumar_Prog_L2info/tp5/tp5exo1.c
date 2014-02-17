/*Pierre Piccinini  07/02/09
Description: concatenation de deux chaines ch1 et ch2 dans
 une troisi?me cha?ne ch3.
Version:2
Etat: ok                                                           */
/****************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define CODE_SORTIE 0
/****************************************************************/
char* concat (char* ch1, char* ch2)
{
 char* ch3=(char *)calloc(strlen(ch1)+strlen(ch2)-1,sizeof(char));
 strcpy(ch3,ch1); //recopie de ch1 dans ch3
 strcat(ch3,ch2); //concat?nation de ch2
return ch3;
}
/****************************************************************/
int main (void)
{
char* ch1=(char *)malloc(20*sizeof(char));
strcpy(ch1,"Ainsi ");
char* ch2=(char *)calloc(20,sizeof(char));
strcpy(ch2,"toujours");

//Concat?nation directe//
char* ch3=(char *)calloc(strlen(ch1)+strlen(ch2)-1,sizeof(char));
//unsigned int i;
//for (i=0;i<strlen(ch1);i++) ch3[i]=ch1[i];
//ou bien
strcpy(ch3,ch1);
//for (i=0;i<strlen(ch2);i++) ch3[strlen(ch1)+i]=ch2[i];
//ou bien
strcat(ch3,ch2);
printf("\nDirectement dans le main: %s\n",ch3);
//lib?ration de la m?moire allou?e//
free(ch3);
free(ch2);


//En utilisant la fonction concat
printf("\nPar utilisation de la fonction: %s\n",concat(ch1,"toujours"));
//mais la m?moire n'a pu ?tre lib?r?e, car l'allocation de ch3 est faite dans la fonction



fflush(stdout);
return (CODE_SORTIE);
}
/****************************************************************/
