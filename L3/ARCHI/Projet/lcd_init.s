			
@ Constantes supplémentaires
			.equ	PWMC_BASE,	0xFFFCC000
			.equ	PWMC_CH0,	0xFFFCC200
			.equ	SPI0_BASE,	0xFFFE0000
			.equ	PID_SPI0,	(1<<4)
			
@ Commandes
			.equ	DISON	,0xAF
			.equ	DISOFF	,0xAE
			.equ	DISNOR	,0xA6
			.equ	DISINV	,0xA7
			.equ	COMSCN	,0xBB
			.equ	DISCTL	,0xCA
			.equ	SLPIN	,0x95
			.equ	SLPOUT	,0x94
			.equ	PASET	,0x75
			.equ	CASET	,0x15
			.equ	DATCTL	,0xBC
			.equ	RGBSET8	,0xCE
			.equ	RAMWR	,0x5C
			.equ	RAMRD	,0x5D
			.equ	PTLIN	,0xA8
			.equ	PTLOUT	,0xA9
			.equ	RMWIN	,0xE0
			.equ	RMWOUT	,0xEE
			.equ	ASCSET	,0xAA
			.equ	SCSTART	,0xAB
			.equ	OSCON	,0xD1
			.equ	OSCOFF	,0xD2
			.equ	PWRCTR	,0x20
			.equ	VOLCTR	,0x81
			.equ	VOLUP	,0xD6
			.equ	VOLDOWN	,0xD7
			.equ	TMPGRD	,0x82
			.equ	EPCTIN	,0xCD
			.equ	EPCOUT	,0xCC
			.equ	EPMWR	,0xFC
			.equ	EPMRD	,0xFD
			.equ	EPSRRD1	,0x7C
			.equ	EPSRRD2	,0x7D
			.equ	NOP		,0x25
			
			@ Bits pour SPI_CR : registre de contrôle
			.equ	SPI_SPIEN	,1
			.equ	SPI_SPIDIS	,1<<1
			.equ	SPI_SWRST	,1<<7
			.equ	SPI_LASTXFER,1<<24
			
			@ Bits pour SPI_MR : registre de mode
			.equ	SPI_MSTR	,1
			.equ	SPI_PS		,1<<1
			.equ	SPI_PCSDEC	,1<<2
			.equ	SPI_MODFDIS	,1<<4
			.equ	SPI_LLB		,1<<7
			
			@ Bits pour SPI_CSR : registre de configuration d'horloge
			.equ	SPI_CPOL	,1
			.equ	SPI_NCPHA	,1<<1
			.equ	SPI_CSAAT	,1<<3
			
@ Initialise l'écran LCD
lcd_init:	stmfd	sp!, {r1, r9, r10, r11, r12, lr}
			
			ldr		r12, =PIOA_BASE
			ldr		r11, =PIOB_BASE
			ldr		r10, =PMC_BASE
			ldr		r9,  =SPI0_BASE
			
			@ On allume l'éclairage de l'écran (PB20)
			mov		r1, #1<<20
			str		r1, [r11, #PIO_PER]
			str		r1, [r11, #PIO_OER]
			str		r1, [r11, #PIO_SODR]
			
			@ On réinitialise l'écran (PA02)
			mov		r1, #1<<2
			str		r1, [r12, #PIO_PER]
			str		r1, [r12, #PIO_OER]
			str		r1, [r12, #PIO_SODR]
			
			@ On active le périphérique A des ports 12, 16, 17 et 18 du PIOA
			@ (SPI0_NPCS0, SPI0_MISO, SPI0_MOSI, SPI0_SPCK)
			mov		r1, #(1<<12)|(1<<16)|(1<<17)|(1<<18)
			str		r1, [r12, #PIO_PDR]
			str		r1, [r12, #PIO_ASR]
			mov		r1, #0
			str		r1, [r12, #PIO_BSR]
			
			@ On active l'horloge du SPI0
			mov		r1, #PID_SPI0
			str		r1, [r10, #PMC_PCER]
			
			@ On réinitialise le SPI0
			mov		r1, #SPI_SPIEN|SPI_SWRST
			str		r1, [r9, #SPI_CR]
			
			@ maître, pas de détection d'erreur, périphérique 0 activé
			ldr		r1, =(SPI_MSTR|SPI_MODFDIS|(0xE<<16))
			str		r1, [r9, #SPI_MR]
			
			@ horloge en logique inverse, 8+1 bits par transmission, baud rate = MCK/12, délai avant SPCK = 1/MCK, délai entre transmissions = 32*1/MCK
			@ldr		r1, =(SPI_CPOL|0x1<<4|0xC<<8|0x1<<16|0x1<<24)
			ldr		r1, =(SPI_CPOL|(0x1<<4)|(0x8<<8)|(0x1<<16)|(0x1<<24))
			str		r1, [r9, #SPI_CSR]
			
			@ On active le SPI0
			mov		r1, #SPI_SPIEN
			str		r1, [r9, #SPI_CR]
			
			bl		lcd_setup
			
			ldmfd	sp!, {r1, r9, r10, r11, r12, pc}

@ Fonction de délai simple
delay_lcd:	subs	r0, r0, #1
			bne		delay_lcd
			mov		pc, lr

@ Envoie une donnée (lcd_write_data) ou une commande (lcd_write_command) à l'écran LCD
@	r0 (entrée) : commande ou donnée à envoyer (sur 1 octet)
lcd_write_data:
			@ Le bit 8 est mis à 1 si la donnée envoyée n'est pas une commande
			orr		r0, r0, #0x100
lcd_write_command:
			stmfd	sp!, {r1, r9}
			ldr		r9,  =SPI0_BASE
write_lcd_wait:
			@ On attend la fin de tous les envois en attente
			ldr		r1, [r9, #SPI_SR]
			tst		r1, #1<<9
			beq		write_lcd_wait
			
			@ On envoie les données au SPI
			str		r0, [r9, #SPI_TDR]
			
			ldmfd	sp!, {r1, r9}
			mov		pc, lr

@ Configure l'écran LCD
lcd_setup:	stmfd	sp!, {r0, r1, lr}
			
			mov		r1, #1<<2
			
			@ On réinitialise l'écran, il faut envoyer 0 à PA02, attendre un court instant, et envoyer 1
			str		r1, [r12, #PIO_CODR]
			mov		r0, #1024
			bl		delay_lcd
			
			str		r1, [r12, #PIO_SODR]
			mov		r0, #1024
			bl		delay_lcd
			
			@ On configure les paramètres de base de l'écran
			mov		r0, #DISCTL
			bl		lcd_write_command
			mov		r0, #0x00		@ 2 divisions (par défaut)
			bl		lcd_write_data
			mov		r0, #32			@ (32 + 1) * 4 = 132 lignes
			bl		lcd_write_data
			mov		r0, #0x00
			bl		lcd_write_data
			
			@ On paramètre la direction de balayage
			mov		r0, #COMSCN
			bl		lcd_write_command
			mov		r0, #0x01
			bl		lcd_write_data
			
			@ On active l'oscillateur interne
			mov		r0, #OSCON
			bl		lcd_write_command
			
			@ On désactive le mode veille de l'écran (?)
			mov		r0, #SLPOUT
			bl		lcd_write_command
			
			@ Contrôle du voltage
			mov		r0, #VOLCTR
			bl		lcd_write_command
			mov		r0, #36
			bl		lcd_write_data
			mov		r0, #3
			bl		lcd_write_data
			
			@ Contrôle de la température
			mov		r0, #TMPGRD
			bl		lcd_write_command
			mov		r0, #0x00
			bl		lcd_write_data
			
			@ Contrôle de la puissance
			mov		r0, #PWRCTR
			bl		lcd_write_command
			mov		r0, #0x0F
			bl		lcd_write_data
			mov		r0, #0x03
			bl		lcd_write_data
			
			@ On inverse l'affichage à cause de l'orientation de l'écran par rapport à la carte
			mov		r0, #DISINV
			bl		lcd_write_command
			
			@ Pas d'affichage partiel
			mov		r0, #PTLOUT
			bl		lcd_write_command
			
			@ Configuration de l'écriture de données
			mov		r0, #DATCTL
			bl		lcd_write_command
			mov		r0, #0x00		@ Pas d'inversion, pas de rotation
			bl		lcd_write_data
			mov		r0, #0x00		@ RGB
			bl		lcd_write_data
			mov		r0, #0x02		@ 16 niveaux de gris
			bl		lcd_write_data
			
			@ On donne le temps à l'alimentation de l'écran de se stabiliser
			mov		r0, #10240
			bl		delay_lcd
			
			@ On réinitialise le rectangle d'écriture
			mov		r0, #PASET
			bl		lcd_write_command
			mov		r0, #0
			bl		lcd_write_data
			mov		r0, #131
			bl		lcd_write_data
			
			mov		r0, #CASET
			bl		lcd_write_command
			mov		r0, #0
			bl		lcd_write_data
			mov		r0, #131
			bl		lcd_write_data
			
			@ On efface l'écran
			bl		lcd_clear
			
			@ On active l'affichage
			mov		r0, #DISON
			bl		lcd_write_command
			
			mov		r0, #NOP
			bl		lcd_write_command
			
			ldmfd	sp!, {r0, r1, pc}

@ Efface l'écran
lcd_clear:	stmfd	sp!, {r0, r1, lr}
			
			mov		r0, #PASET
			bl		lcd_write_command
			mov		r0, #0
			bl		lcd_write_data
			mov		r0, #131
			bl		lcd_write_data
			
			mov		r0, #CASET
			bl		lcd_write_command
			mov		r0, #0
			bl		lcd_write_data
			mov		r0, #131
			bl		lcd_write_data
			
			ldr		r1, =25743
			
			mov		r0, #RAMWR
			bl		lcd_write_command
			
clr_loop:	mov		r0, #0
			bl		lcd_write_data
			subs	r1, r1, #1
			bne		clr_loop
			
			mov		r0, #NOP
			bl		lcd_write_command
			
			ldmfd	sp!, {r0, r1, pc}

