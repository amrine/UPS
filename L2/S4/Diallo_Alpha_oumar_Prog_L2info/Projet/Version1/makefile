amazone:main.o coups.o pile.o utilitaire.o verification.o jeu.o affichage_init.o             
	gcc -o amazone main.o coups.o pile.o utilitaire.o verification.o  jeu.o affichage_init.o         

main.o:main.c
	gcc -Wall -ansi -c main.c

utilitaire.o:utilitaire.c
	gcc -Wall -ansi -c utilitaire.c

coups.o:coups.c
	gcc -Wall -ansi -c coups.c

pile.o:pile.c
	gcc -Wall -ansi -c pile.c

verification.o:verification.c
	gcc -Wall -ansi -c verification.c

jeu.o:jeu.c
	gcc -Wall -ansi -c jeu.c

affichage_init.o:affichage_init.c
	gcc -Wall -ansi -c affichage_init.c

clean:
	rm -f *.o *~