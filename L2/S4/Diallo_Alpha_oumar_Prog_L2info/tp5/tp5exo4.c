/* 24/03/2008  Pierre Piccinini                                               */
/* Comptage de mots d'un fichier texte contenant sur chaque ligne un mot      */
/******************************************************************************/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h>
#include<ctype.h>

#define LONG_MOT_MAX 30
/******************************************************************************/
char ** occurrence_mots(int* nb_mots, char* nom_fichier_mots)
{
 //Test d'existence et Ouverture du fichier de mots
 FILE* flux=fopen(nom_fichier_mots,"r");
 if (flux==NULL)
        {
        fprintf(stderr,"Pb de fichier !!");
        exit(1);
        }
 //Cr?ation du tableau dynamique de cha?nes
 int i=0;
 char ** tab=NULL;
 while (!feof(flux))
   {
   tab=(char **)realloc(tab,sizeof(char *)*(i+1));
   if (tab==NULL)
        {
        fprintf(stderr,"Tas sature !!");
        exit(1);
        }
   char ch_aux[LONG_MOT_MAX];
   fgets(ch_aux,LONG_MOT_MAX,flux);
   //suppression de l'?ventuel retour chariot (code 10) en fin de cha?ne
   if (ch_aux[strlen(ch_aux)-1]==10) ch_aux[strlen(ch_aux)-1]='\0';
   //allocation de tab[i]
   tab[i]=(char *)malloc(sizeof(char)*(strlen(ch_aux)+1));
   if (tab[i]==NULL)
             {
             fprintf(stderr,"Tas sature !!");
             exit(1);
             }
   strcpy(tab[i],ch_aux);
   i=i+1;
   }
//if (feof(flux)!=0) printf("fin de fichier=%d\n",feof(flux));
*nb_mots=i;
fclose(flux);
return tab;
}
/******************************************************************************/
/*affichage de la liste de mots tab  */
void afficher(char ** tab, int nb)
{
int i;
for(i=0;i<nb;i++) printf("%s\n",tab[i]);
}
/******************************************************************************/
/* determine l'indice de la case de tab o? placer la cha?ne ch  */
int place(char ** tab ,char * ch , int nb)
{
 int i =0;
 while (strcmp(ch,tab[i])>0 && i<nb) i++;
 return i;
}
/******************************************************************************/
/* decale les cases allant de d ? f-1 en d+1 ? f, puis place en d le contenu de
 la case f initiale
*/
void decale(char ** tab , int d , int f)
{
 int k;
 char* aux=tab[f];
 for(k=f;k>d;k--)
    {
     tab[k]=tab[k-1];
    }
 tab[d]=aux;
}
/******************************************************************************/
void tri_insertion(char** tab , int nb_mots)
{
int i;
for (i=0;i<nb_mots;i++)
     {
     int p=place(tab,tab[i],nb_mots);
     decale(tab,p,i);
     }
}
/******************************************************************************/
void ecriture_dans_fichier(char** tab , int nb_mots, char* nom_fichier_resultats)
{
 //Cr?ation du fichier resultat
 FILE * fichier_resultat;
 fichier_resultat=fopen(nom_fichier_resultats,"w");
 if (fichier_resultat==NULL)
    {
    fprintf(stderr,"Pb lors de la creation du fichier !\n");
    exit(1);
    }
 //Ecriture des resultats dans le fichier resultats.txt
 int i=0;
 while (i<nb_mots)
        {
        int cpt=0;
        int j=i;
        bool fini;
        do {cpt++;
            i++;
            //printf("cpt=%d\n",cpt);
            if (i==nb_mots) fini=true;
            else fini=strcmp(tab[j],tab[i])!=0;
           }
        while(!fini);
        fprintf(fichier_resultat,"%s:  %d\n",tab[j],cpt);
        }
 fclose(fichier_resultat);
}
/******************************************************************************/
int main(void)
{
//cr?ation du tableau dynamique
int nb_de_mots;
char** mots=occurrence_mots(&nb_de_mots,"liste_mots.txt");

printf("\nTableau non encore trie:\n");afficher(mots,nb_de_mots);
//tri du tableau
tri_insertion(mots,nb_de_mots);
printf("\nTableau trie:\n");afficher(mots,nb_de_mots);

//comptage et ?criture dans fichier
ecriture_dans_fichier(mots,nb_de_mots,"resultats.txt");
fflush(stdout);
return 0;
}
/******************************************************************************/




