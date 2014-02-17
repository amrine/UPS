# ***************************************************************
# Makefile pour Atmel AT91SAM7X256 - Remaped RAM execution 
# ***************************************************************
NOM_PROG = main

CC	= arm-elf-gcc
AS	= arm-elf-as
LD	= arm-elf-ld -v
CP	= arm-elf-objcopy
OD	= arm-elf-objdump

CFLAGS  = -mcpu=arm7tdmi -I./ -c -fno-common -O0 -g -fomit-frame-pointer -Wcast-align
ASFLAGS  = -I$(ARM_HOME)/include -mapcs-32 -g
LFLAGS  =  -Ttext=0x2000

CPFLAGS = --output-target=binary
ODFLAGS	= -d

OBJECTS =	$(NOM_PROG).o

all: $(NOM_PROG).elf
	@ echo "...création fichier dump"
	$(OD) $(ODFLAGS) $(NOM_PROG).elf > $(NOM_PROG).dmp

$(NOM_PROG).elf: $(OBJECTS) 
	@ echo "...édition de liens"
	$(LD) $(LFLAGS) -o$(NOM_PROG).elf $(OBJECTS) 

clean:
	-rm -f $(OBJECTS) $(NOM_PROG).elf $(NOM_PROG).bin  $(NOM_PROG).dmp
