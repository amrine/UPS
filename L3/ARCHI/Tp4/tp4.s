.global _start
_start:

@programme reçoit en entrée une date (jour-mois-annee), calcule le quantieme
@(numero du jour dans l'annee compris entre 1 et 366)
@ ex : 3 mars 2011 -> 62
@ ex : 8 janvier 2004 -> 8

@test sous programme DIVISIBLE
main: 
mov r3, #6
mov r4, #2
stmfd r13!, {r3}
stmfd r13!, {r4}
bl DIVISIBLE
add r13, r13, #8 @on restaure la valeur de r3 et r4
exit:mov r0,#0x18 @ les 3 instructions suivantes servent
ldr  r1,=0x20026 @ a rendre la main à Angel (moniteur)
swi  0x123456

@test sous programme DEBUT
@main: 
@ldr r2, num_mois
@stmfd r13!, {r2} @on charge r2 par la pile, passage de parametre
@bl DEBUT
@add r13, r13, #4 @on restaure la valeur de r2
@exit:mov r0,#0x18 @ les 3 instructions suivantes servent
@ldr  r1,=0x20026 @ a rendre la main à Angel (moniteur)
@swi  0x123456


@Passage de parametre par la pile
@sous programme debut qui recoit par la pile un numero de mois et renvoie dans r0
@le quantieme du jour precedant ce mois
@On suppose que le numero de mois passé en parametre est toujours correct,
@c'est-a-dire compris entre 1 et 12
@ r1 contient le tableau
@ r2 est le numero du mois
@ r0 le quantieme du jour precedent ce mois
@on sauvegarde les registres r1 et r2 car ils ne doivent pas etre modifies
@r2 est sauvegarde car on veut l'utiliser dans le SP et qu'il ne faut pas le modifier
DEBUT : STMFD r13!,{r1, r2, r14} @sauvegarde
	  ldr r2, [r13, #12] @on recupere la valeur de r2 par la pile
	  adr r1,tab
	  sub r2,r2,#1
	  ldr r0,[r1,r2,LSL #2]
fin : LDMFD r13!,{r1, r2, r15} @restauration


@sous-programme DIVISIBLE qui reçoit deux valeurs entières a et b passees par la pile
@et renvoie dans r0 le resultat 1 si a est multiple de b et 0 sinon
@ r5 contient la valeur a
@ r6 contient la valeur b
@ r0 contient le resultat 1 si a est multiple de b sinon 0
DIVISIBLE : STMFD r13!,{r5, r6, r14}
ldr r5,[r13, #16] 		@on recupere  la valeur de r3 dans r5
ldr r6,[r13, #12]		@on recupere la valeur de r4 dans r6
mov r0, #0			@r0 = 0 si r5 not divisible par r6 (a multiple de b)
tantque:
	  cmp r5,r6 		@comparaison r5 et r6 (a et b)
	  blt suite		@si r5 < r6 on passe a la suite (a < b)
	  sub r5,r5,r6
	  b tantque
suite : 
	cmp r5, #0		@comparaison r5 et 0 (a et 0)
	moveq r0,#1		@r0 = 1 si r5 divisible par r6 (a multiple de b)
	LDMFD r13!,{r5, r6, r15}


@tableau contenant les quantieme des mois par ordre
tab : .int 0,31,59,90,120,151,181,212,243,273,304,334

@ mois avril
num_mois : .int 4
