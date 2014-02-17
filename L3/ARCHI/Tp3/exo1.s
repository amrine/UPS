.global _start
_start:


@r3 registre contenant le tableau
@r0 registre contenant la valeur d'un indice du tableau
main: 
	adr r3,tab 
	mov r4, #0 @somme
	mov r2,#0 @compteur
tq:
  cmp r2,#4
	bgt exit 
	ldr r0,[r3],#4
	add r2, r2, #1
	bl ABS
	add r4,r4,r0
	  b tq
	
	
	
exit:mov r0,#0x18 	@ les 3 instructions suivantes servent

	ldr  r1,=0x20026 @ a rendre la main à Angel (moniteur)

	swi  0x123456



ABS : STMFD r13!,{r1,r14}
	  mov r1,#0
	  cmp r0,r1
	  bge fin
	  sub r0,r1,r0
fin : LDMFD r13!,{r1,r15}

tab : .int 0,-2,-3,1
