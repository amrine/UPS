.global _start
_start:
main:
	
	
	
tq1:
	
	
	

	cmp
	r6, #0 	@ tant que B != 0

	beq
	exit 	

	mov
	r2, r5 	@ R <- A
tq2:
	
	
	

	cmp
	r2, r6 	@ tant que R ≥ B

	blo
	ftq2
	

	sub
	r2, r2, r6 	@ R <- R - B

	b
	tq2
	
ftq2:
	
	
	

	mov
	r5, r6 	@ A <- B

	mov 	r6, r2
	@ B <- R

	b
	tq1
	
exit:
	
	
	

	mov
	r0,#0x18
	@ retour à Angel

	ldr
	r1,=0x20026
	

	swi
	0x123456
	
