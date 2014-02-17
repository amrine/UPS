.include "carte.inc"
.include "font.s"
.include "lcd_init.s"
.include "usart.s"

@Diallo Alpha Oumar Binta
@Licence 3 Informatique
@université Paul Sabatier
@Groupe 3.1

.global _start
_start:

main:  
    bl init_usart
    bl lcd_init @initialisation de l'ecran LCD
    bl lcd_setup @ Configuration de l'écran LCD
    bl lcd_clear @on efface l'ecran si quelque chose est déjà ecrit

    mov r1,#0 @x1 coin superieur gauche de l'ecran
    mov r2,#7 @x2 un caractère est affiché dans une matrice 8*8
    mov r3,#0 @compteur de nombre de caractères affichées
    mov r4,#16 @nombre de caractères sur une ligne
    mov r5,#2 @y1 on ajoute 2 car on a un bandeau invisible de 2 pixels de hauteur
    mov r6,#9 @y2 on ajoute 2 car on a un bandeau invisible de 2 pixels de hauteur (7+2)
    mov r7,#256 @nombre de caractères maximum qu'on peut ecrire sur l'ecran LCD
whileTrue:
    @s'assurer qu'on ecrit pas par dessus un caractère
    cmp r3,#0
    addhi r1,r1,#8
    @on empile x1
    mov r0, r1 @x1
    stmfd sp!, {r0}
    @on empile y1
    mov r0, r5 @y1 
    stmfd sp!, {r0}

    cmp r3,#0
    addhi r2,r2,#8
    @on empile x2
    mov r0, r2 @x2
    stmfd sp!, {r0}
    @on empile y2
    mov r0, r6 @y2 
    stmfd sp!, {r0}

    bl getchar @on recupère le code du caractère
    cmp r0,#27
    bleq effacer_ecran
    stmfd sp!, {r0}
    bl affiche_caratere
    add sp, sp, #5<<2
    

    add r3,r3,#1
    sub r7,r7,#1
    cmp r7,#0 @r7==0 on a rempli l'ecran LCD, on quitte le programme
    bleq exit
    @on teste voir si on atteint 16 caractères sur la ligne si r3==16
    @on a rempli la ligne courrante, on passe à la ligne suivante
    cmp r3,r4
    blo whileTrue
    addeq r5,r5,#8 @on incrémente y1
    addeq r6,r6,#8 @on incrémente y2
    moveq r1,#0 @x1
    moveq r2,#7 @x2
    moveq r3,#0

b whileTrue
exit:   b        exit



affiche_caratere : stmfd sp!, {r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, lr}
@definition du rectangle d'écriture
ldr r1, [sp, #15<<2] @ x1
ldr r2, [sp, #14<<2] @ y1
ldr r3, [sp, #13<<2] @ x2
ldr r4, [sp, #12<<2] @ y2

@on dessine le rectangle d'ecriture
mov r0, #PASET @row set
bl lcd_write_command
mov r0, r2
bl lcd_write_data
mov r0, r4
bl lcd_write_data
mov r0, #CASET @column set
bl lcd_write_command
mov r0, r1
bl lcd_write_data
mov r0, r3
bl lcd_write_data
@ On se préprare pour écrire les données
mov r0, #RAMWR
bl lcd_write_command

ldr r1,=font_8x8 @adresse du tableau contenant les caractères
ldr r0,[sp, #11<<2] @code ASCII du caractère renvoyé par usart (getchar)
add r1, r0, LSL#3 @on multiplie par 8 pour se positionner sur l'adresse du tableau du caractère (8=taille du tableau)

@traitement de la donnée
mov r2,#0 @nombre de lignes de l'écran
mov r3,#7 @nombre de colonne de l'écran
forRow:
  ldrb r4, [r1], #1 @r4 contient le premier octets du caractère
  cmp r2, #7
  beq endForRow @on sort si on atteint le nombre de ligne maximum
  mov r3,#7
  add r2,r2,#1 @on passe à la ligne suivante
  forColumn:
    cmp r3, #-1
    beq endForColumn
    mov r5,r4,LSR r3 @decalage a droite de r4
    and r5,r5,#1 @r5 contient le bit decalé
    cmp r5,#0 @on compare r5 a 0
    beq reset
    @ajout de masque
    mov r6, #0xFF 
    mov r7, #0xF0

    b suivant
    reset:
      mov r6, #0
      mov r7, #0
    suivant:
      sub r3, r3, #1 @on décremente r3 (nombre de colonne)
      mov r5,r4,LSR r3 @decalage a droite de r4
      and r5,r5,#1 @r5 contient le bit decalé
      cmp r5,#0 @on compare r5 a 0
      beq traiter
      
      orr r8, #0x0F
      mov r9, #0xFF
      
      b print
      traiter:
	orr r7, r7, #0
	mov r9, #0
      print:
	sub r3, r3, #1 @on décremente r3 (nombre de colonne)
	mov r0, r6
	bl lcd_write_data
	mov r0, r7
	bl lcd_write_data
	mov r0, r9
	bl lcd_write_data
  b forColumn
  mov r0, #NOP
  bl lcd_write_command
  endForColumn:
  b forRow
endForRow:
ldmfd sp!, {r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, pc}

effacer_ecran:
    @on reinitialise le tout
    mov r1,#0 @x1 coin superieur gauche de l'ecran
    mov r2,#7 @x2 un caractère est affiché dans une matrice 8*8
    mov r3,#0 @compteur de nombre de caractères affichées
    mov r4,#16 @nombre de caractères sur une ligne
    mov r5,#2 @y1 on ajoute 2 car on a un bandeau invisible de 2 pixels de hauteur
    mov r6,#9 @y2 on ajoute 2 car on a un bandeau invisible de 2 pixels de hauteur (7+2)
    mov r7,#256 @nombre de caractères maximum qu'on peut ecrire sur l'ecran LCD
    bl lcd_clear
    b whileTrue


