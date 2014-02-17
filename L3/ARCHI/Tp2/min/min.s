.global _start
_start:
main: 	  	
	adr r0,chaine	  	@r0  registre contenant le tableau(la chaine a modifier) 
	ldrb r1,[r0]		@r1 acces
tantque: cmp r1,#0
	bhi exit
	  cmp r1,#'a'
	  blt suite
	  cmp r1,#'z'
	  bhi suite
	  sub r1,r1,#'A'
	  strb r0,[r1]
	  suite :	
		ldrb r1,[r0],#1
		
	b tantque
exit:mov r0,#0x18 	@ les 3 instructions suivantes servent

	ldr  r1,=0x20026 @ a rendre la main à Angel (moniteur)

	swi  0x123456

chaine : .asciz "DoDo"
