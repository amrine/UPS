CC=gcc
SYS= -Wall -DUNIX -I.

OBJ_COMMON=physique.o couche_liaison.o

OBJ_LSAP0=service_liaison0.o
OBJ_LSAP1=service_liaison1.o
OBJ_LSAP2=service_liaison2.o
OBJ_LSAP3=service_liaison3.o

OBJ_ENVOIE=emetteur.o
OBJ_RECOIT=recepteur.o

PRG=emetteur recepteur

all: sl0 sl1 sl2 sl3

# Pas de controle de flux, ni reprise sur erreurs
sl0: $(OBJ_COMMON) $(OBJ_LSAP0) $(OBJ_ENVOIE) $(OBJ_RECOIT)
	$(CC) -o emetteur $(OBJ_COMMON) $(OBJ_LSAP0) $(OBJ_ENVOIE)
	$(CC) -o recepteur $(OBJ_COMMON) $(OBJ_LSAP0) $(OBJ_RECOIT)

# Controle de flux S&W
sl1: $(OBJ_COMMON) $(OBJ_LSAP1) $(OBJ_ENVOIE) $(OBJ_RECOIT)
	$(CC) -o emetteur $(OBJ_COMMON) $(OBJ_LSAP1) $(OBJ_ENVOIE)
	$(CC) -o recepteur $(OBJ_COMMON) $(OBJ_LSAP1) $(OBJ_RECOIT)	

# Controle de flux et reprise sur erreur S&W PAR (non connecté)
sl2: $(OBJ_COMMON) $(OBJ_LSAP2) $(OBJ_ENVOIE) $(OBJ_RECOIT)
	$(CC) -o emetteur $(OBJ_COMMON) $(OBJ_LSAP2) $(OBJ_ENVOIE)
	$(CC) -o recepteur $(OBJ_COMMON) $(OBJ_LSAP2) $(OBJ_RECOIT)	

# Controle de flux et reprise sur erreur S&W PAR (connecté)
sl3: $(OBJ_COMMON) $(OBJ_LSAP3) $(OBJ_ENVOIE) $(OBJ_RECOIT)
	$(CC) -o emetteur $(OBJ_COMMON) $(OBJ_LSAP3) $(OBJ_ENVOIE)
	$(CC) -o recepteur $(OBJ_COMMON) $(OBJ_LSAP3) $(OBJ_RECOIT)	

# Fenetre d'anticipation
# ...
	
%.o: %.c
	$(CC) -o $@ -c $< $(SYS)

clean:
	rm -f *.o

realclean: clean
	rm -f $(PRG) *~ out.*

