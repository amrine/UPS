#define _POSIX_C_SOURCE 1
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "erreur.h"

int main(int argc, char *argv[]){
  if (argc == 1) Erreur("my_sh",1);
  return 0;

}

