/*Pierre Piccinini  7/02/09
Description: remplacement de sous_chaines dans une cha?ne
Version: 2
Etat: ok                                                                         */
/******************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define CODE_SORTIE 0
/******************************************************************************/
//remplacement de la premi?re occurrence d'une sous-chaine sch par la cha?ne sch2
//la chaine de d?part est conserv?e sans modification
char* remplacer_sc(const char ch[] , const char sch[] , const char sch2[])
{
 bool trouve=false;
 unsigned int i=0;
 while (!trouve && i<strlen(ch)-strlen(sch)+1)
    {
    trouve=!strncmp(&ch[i],sch,strlen(sch));
    if (!trouve) i++;
    }
 //Si trouve, i est l'indice de debut de sch dans ch
 char* res=(char *)ch;
 if (trouve)
    {
    res=(char *)calloc(strlen(ch)-strlen(sch)+strlen(sch2)+1,sizeof(char));
    strncpy(res,ch,i);
    strcat(res,sch2);
    strcat(res,ch+i+strlen(sch));
    }
return res;  //si sch non trouve, on renvoit ch
}
/******************************************************************************/
//remplacement de toutes les occurrences d'une sous-chaine sch par la cha?ne ch2
//la chaine de d?part est conserv?e sans modification
char* remplacer_toutes_sc(const char ch[] ,const char sch[] ,const char sch2[])
{
 //on fait une copie de ch qui pourra ?tre modifiee
 char* aux=(char *)malloc(strlen(ch)*sizeof(char));
 strcpy(aux,ch);
 char * aux1;
 bool continu=true; //vrai s'il y a encore au moins un remplacement a effectuer
 while (continu)
    {aux1=remplacer_sc(aux,sch,sch2);
     if (strcmp(aux,aux1)!=0) //un remplacement effectu?
         {char *temp=aux;
          free(temp);
          aux=aux1;
         }
     else continu=false; //pas de remplacement effectue: on sort
    }
return aux1;
}
/******************************************************************************/
//remplacement de toutes les occurrences d'une sous-chaine sch par la cha?ne ch2
//on modifie la cha?ne de d?part
void remplacer_toutes_sc_version2(char** ch , const char sch[] ,const char sch2[])
{
 bool continu=true; //booleen vrai tant qu'il y a un remplacement a effectuer
 while (continu)
    {char* aux1=remplacer_sc(*ch,sch,sch2);
     if (strcmp(aux1,*ch)!=0) //un remplacement effectu?
         {char *temp=*ch;
          free(temp);
          *ch=aux1;
         }
     else continu=false; //pas de remplacement effectue: on sort
    }
}
/******************************************************************************/
/******************************************************************************/
int main (void)
{
char* ch="bbbaababbaabb";
char* ch1=(char*) malloc(40*sizeof(char));
strcpy(ch1,"bbbaababbaabb");

printf("remplacement des occurrences de b par z z l'une apres l'autre:\n");
printf("%s\n",ch1);
int i;
for(i=0;i<9;i++)
    {
    ch1=remplacer_sc(ch1 , "b" , "z z" );
    printf("%s\n",ch1);
    }
free(ch1);


printf("remplacement simultane de toutes les occurrences de b par z z:\n");
char* ch2=(char*) malloc(40*sizeof(char));
strcpy(ch2,ch);
printf("%s\n",ch2);
printf("%s\n",remplacer_toutes_sc(ch2 , "b" , "z z"));


printf("remplacement simultane de toutes les occurrences de z z par b:\n");
remplacer_toutes_sc_version2(&ch2 , "z z" , "b");
printf("%s\n",ch2);

printf(":)\n");
return (CODE_SORTIE);
}
/******************************************************************************/
