#define _POSIX_C_SOURCE 1
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <string.h>
#include <stdbool.h>
#include <string.h>
#include <signal.h>
#define A_TOI SIGUSR1
pid_t Pid_Fils;
char carac = '&';
/* ----------------- */
/* Fonction d'erreur */
/* ----------------- */
void Erreur(const char *Mess, int Val_Exit)
{
perror(Mess);
exit(Val_Exit);
}
/* ---------------- */
/* Fonction handler */
/* ---------------- */
void Handler_A_TOI(int S)
{
}
void Code_flip()
{
printf("Flip: Debut\n");
signal(A_TOI, Handler_A_TOI);
while(1)
{
printf("FLIP\n");
kill(Pid_Fils, A_TOI);
pause();
}
}
void Code_flop()
{
printf("Flop: Debut\n");
signal(A_TOI, Handler_A_TOI);
while(1)
{
pause();
printf("FLOP\n");
kill(getppid(), A_TOI);
}
}
int main(int ARGC, char *ARGV[])
{
/* creation du processus fils flop */
switch(Pid_Fils = fork())
{
case -1:
/* ---------------------- */
/* Cas d'erreur */
/* Execute par le pere */
/* ---------------------- */
Erreur(" *** ERRREUR creation processus fils", 97);
case 0:
/* ---------------------------------------------------- */
/* Code du processus fils charge de traiter les lettres */
/* ---------------------------------------------------- */
Code_flop();
default:
break;
}
Code_flip();
return 0;
}