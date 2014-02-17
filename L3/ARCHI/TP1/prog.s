.global _start
_start:
main: 	  	
	mov r0,#0 	  	@ r0 = somme
	mov r1,#1 		@ r1 = i
boucle: cmp r1,#10 	
	bhi exit 	

	add r0,r0,r1 	@ somme <- somme + i

	add r1,r1,#1 	@ i <- i + 1

	b 	boucle 	
	
exit:mov r0,#0x18 	@ les 3 instructions suivantes servent

	ldr  r1,=0x20026 @ a rendre la main à Angel (moniteur)

	swi  0x123456



@add r0,r0,r1 
@1110 000 0100 0 0000 0000 0001   resultat =e080000(1)

@bhi exit
@1000 101 1 (offset (d=cible - branchement -8)/4) resultat=8a00000(2)
