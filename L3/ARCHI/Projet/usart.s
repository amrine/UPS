 @================================================================
 @ INITIALISATION
 @===============================================================
 @ On connecte les ports à l'USART0 et non au PIO
 @+++++++++++++++++++++++++++++++++++++++++++++++
init_usart:
         ldr        r0,=PIOA_BASE
         mov        r1,#0b11011
         str        r1,[r0,#PIO_PDR]
         str        r1,[r0,#PIO_IDR]
         str        r1,[r0,#PIO_ASR]
         
 @ On initialise l'USART pour recevoir et transmettre des caractères
 @ +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
         ldr        r0,=USART0_BASE @ 0xfffc0000
         
         @ On fait un reset de l'émetteur et du récepteur
         @-----------------------------------------------
         ldr        r1,=(US_RSTRX|US_RSTTX|US_RSTSTA)
         str        r1,[r0,#US_CR]
 
         @ On active l'émetteur et le récepteur
         @-------------------------------------
         ldr        r1,=(US_RXEN|US_TXEN)
         str        r1,[r0,#US_CR]
         
         @ Configuration des modes
         @------------------------
         @        - Clock Selection = MCK (US_CLKS_MCK)
         @        - Taille de caractère = 8 (US_CHRL_8)
         @        - Mode Asynchrone (SYNC=0)
         @        - Pas de parité (US_PAR_NONE)
         @        - 1 stop bit (US_NBSTOP_1)
         @        - Normal Channel Mode (US_CHMODE_NORMAL)
         ldr        r1,=(US_CLKS_MCK|US_CHRL_8|US_PAR_NONE|US_NBSTOP_1|US_CHMODE_NORMAL)
         str        r1,[r0,#US_MR]
         
         @Initialisation du baud rate generator register (CD)
         @---------------------------------------------------
         @ Cette valeur est calculée à partir de
         @        - MCK = 48 MHz
         @        - débit du terminal = 57600
         @        - mode asynchrone (débit = MCK/(CD*16))
         mov        r1,#52
         str        r1,[r0,#US_BRGR]
 
         @Initialisation du temps de garde
         @--------------------------------
         mov        r1,#0xa
         str        r1,[r0,#US_TTGR]
         
         @ Désactivation des interruptions de tout genre
         @----------------------------------------------
         mov        r1,#0x3F
         str        r1,[r0,#US_IDR]
	 mov pc, lr


 @================================================
 @ Ecrire un caractère à l'écran
 @        - passage du caractère par le registre r0
 @ - passage de l'adresse de l'USART dans r1
 @================================================
 putchar:
         stmfd        sp!,{r1, r2}
         ldr        r1,=USART0_BASE
 put_wait:
         ldr        r2,[r1,#US_CSR]
         ands        r2,r2,#US_TXRDY
         beq        put_wait
         @ On place le caractère dans le registre
         @ Transmitter Holding Register (US_THR)
         strb        r0,[r1,#US_THR]
         ldmfd        sp!,{r1, r2}
         mov        pc,lr
 
 @================================================
 @ Saisir un caractère au clavier
 @        - renvoi du caractère par le registre r0
 @ - passage de l'adresse de l'USART dans r1
 @================================================
 getchar:
         stmfd        sp!,{r1, r2}
         ldr        r1,=USART0_BASE
         @ Attente active de l'arrivée d'un caractère
         @ tapé au clavier
 get_wait:
         ldr        r2,[r1,#US_CSR]
         ands        r2,r2,#US_RXRDY
         beq        get_wait
         
         @ Une fois le caractère saisi, on le place dans r0
         ldrb        r0,[r1,#US_RHR]
         ldmfd        sp!,{r1, r2}
         mov        pc,lr
