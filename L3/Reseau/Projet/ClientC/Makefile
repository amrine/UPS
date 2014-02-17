CC=gcc
SYS= -Wall -pedantic -Werror -DUNIX -I.

OBJ_COMMON=functions.o 

OBJ_GET=get.o
OBJ_PUT=put.o

OBJ_CLIENT=client.o
OBJ_SERVER=server.o

PRG=client server

all: get_put

# get put http
get_put: $(OBJ_COMMON) $(OBJ_GET) $(OBJ_PUT) $(OBJ_CLIENT) $(OBJ_SERVER)
	$(CC) -o client $(OBJ_COMMON) $(OBJ_GET) $(OBJ_PUT) $(OBJ_CLIENT)
	$(CC) -o server $(OBJ_COMMON) $(OBJ_GET) $(OBJ_PUT) $(OBJ_SERVER)


%.o: %.c
	$(CC) -o $@ -c $< $(SYS)

clean:
	rm -f *.o

realclean: clean
	rm -f $(PRG) *~ out.*

