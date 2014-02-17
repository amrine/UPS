.global _start
_start:

@ r2 contient le numero de mois
num_mois : .int 4 @ mois avril
num_jour : .int 1 @ jour 1
annee    : .int 2000 @ annee
quantieme : .int 0

main: 


@	ldr r2,num_mois	 	@ mars
@	ldr r3,num_jour
@	bl DEBUT
@	add r0,r0,r3
	mov r1,#16
	mov r2,#2
  bl DIVISIBLE

exit:mov r0,#0x18 	@ les 3 instructions suivantes servent

	ldr  r1,=0x20026 @ a rendre la main à Angel (moniteur)

	swi  0x123456


@ r1 contient le tableau
@ r2 est le numero du mois
@ r0 le quantieme du jour precedent ce mois
DEBUT : STMFD r13!,{r14}
	  adr r1,tab
	  sub r2,r2,#1
	  ldr r0,[r1,r2,LSL #2]
fin : LDMFD r13!,{r15}


@ r1 contient la valeur a
@ r2 contient la valeur b
@ r0 contient le resultat 1 si a est multiple de b sinon 0
DIVISIBLE : STMFD r13!,{r1,r2,r14}
tantque:
	  cmp r1,r2
	  blt fintantque
	  sub r1,r1,r2
	  b tantque
	  
fintantque : 
cmp r1,r2
   moveq r0,#1
  movlt r0,#0
  LDMFD r13!,{r1,r2,r15}


tab : .int 0,31,59,90,120,151,181,212,243,273,304,334
