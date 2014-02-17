amazone:main.o coups.o pile.o stack.o utilitaire.o verification.o jeu.o affichage_init.o             
	gcc -W -Wall -ansi -pedantic -lncurses -o amazone main.o coups.o pile.o stack.o utilitaire.o verification.o  jeu.o affichage_init.o         

main.o:main.c
	gcc -W -Wall -ansi -pedantic -lncurses -c main.c

utilitaire.o:utilitaire.c
	gcc -W -Wall -ansi -pedantic -lncurses -c utilitaire.c

coups.o:coups.c
	gcc -W -Wall -ansi -pedantic -lncurses -c coups.c

pile.o:pile.c
	gcc -W -Wall -ansi -pedantic -lncurses -c pile.c

stack.o:stack.c
	gcc -W -Wall -ansi -pedantic -lncurses -c stack.c

verification.o:verification.c
	gcc -W -Wall -ansi -pedantic -lncurses -c verification.c

jeu.o:jeu.c
	gcc -W -Wall -ansi -pedantic -lncurses -c jeu.c

affichage_init.o:affichage_init.c
	gcc -W -Wall -ansi -pedantic -lncurses -c affichage_init.c

clean:
	rm -f *.o *~