(**{C 
Projet de Programmation : Partie OCaml 
Univeriste Paul Sabatier, Toulouse
Module : Projet de Programmation L3}
{C Projet Java/OCaml 2011/2012}
{C Sujet v1.1-14/11/2011}

 @author  Kevin Nzeng Essimengane 
 @author  Diallo Alpha Oumar Binta
*)

		(**{C ------------------
		 1.1 Type formule
		------------------}*)
		(** @see 'exemples.ml' Contenant les exemples
		*)
		type formule = 	Vrai | Faux
		| Atome of char
		| Et of formule * formule 
		| Ou of formule * formule
		| Implique of formule * formule
		| Equivaut of formule * formule
		| Non of formule 
;;


			(**{C ----------------------
			 2.1 fonction valeur
			----------------------}*)
			
(** 
	@param x l'atome recherche
	@param y l'ensemble des atomes de la formule
	@param z la liste des interpretations de la formule
	@raise Longueur_differente si la taille des listes y et z different
	@return  la valeur de verite associe a l'atome x dans l'interpretation z
	*)
let rec valeur (x: char) (y:char list) (z: bool list) = 
	if (List.length y) <> (List.length z) then  (* Comparaison de la taille des deux listes passees en argument*)
												(* pour verifier la bijection entre les atomes et leurs interpretations respectives*)
		failwith "longueur des listes (atomes & interpretation) incompatible" 
	else 
	if x = (List.hd y) then (* si l'atome recherche est egal a la tete on retrourne l'interpretation en tete *)
		(List.hd z) else  (*sinon on reitere *)
		valeur x (List.tl y) (List.tl z);;
		
		
		(**{C -------------------------------------------------------------------
		 2.2 Fonction des tables de verite des operateurs \/ /\ -, -> <->
		-------------------------------------------------------------------}*)
(**
	@param x un booleen
	@param y un booleen
	@return effectue une operation AND logique entre x et y 
*)
let et x y = (x && y);;

(**
	@param x un booleen
	@param y un booleen 
	@return Effectue une operation OU logique entre x et y 
*)
let ou x y = x || y;;

(**
	@param x un booleen
	@return la negation logique de x
*)
let non x = not x;;

(**
	@param x un booleen
	@param y un booleen
	@return Determine si x implique logiquement y
*)
let implique x y = if (x = false) then true else y;;

(**
	@param y un booleen
	@param x un booleen
	@return Teste l'equivalence logique entre x et y
 
*)
let equivaut x y = if(x = false) then not y else y;;
                         



		(**{C ------------------------
		 2.3 fonction Applique  
		  ------------------------}*)
		  
(**	
	@param atm une liste d'atomes
	@param interp une interpretation sous forme de liste de booleens
	@param f une formule
	@return la valeur de la formule f dans l'interpretation interp		
*)
let rec applique (atm: char list) (interp: bool list) (f:formule) =  
	if (List.length atm) <> (List.length interp) then  (* Comparaison de la taille des deux listes atm & interp passees en argument*)
		failwith "longueur des listes (atomes & interpretation) incompatible" else
		match f with 
		| Atome(a) -> valeur a atm interp (* si la formule est un atome on retourne la valeur de l'atome dans l'interpretation interp *)
		
		| Et(x, y) -> et (applique atm interp x) (applique atm interp y) (* Si f est un Et, on applique la fonction 'et' *) 
		                                                                  (* sur les valeurs des formules x et y dans l'interpretation interp  *)
		                                                                  
		| Ou(x, y) -> ou (applique atm interp x) (applique atm interp y)  (* Si f est un Ou, on applique la fonction 'ou' *) 
		                                                                  (* sur les valeurs des formules x et y dans l'interpretation interp  *)
		                                                                  
		| Non(x) -> non (applique atm interp x) (* Si f est un Non, on applique la fonction 'non' *) 
		                                        (* sur la valeur de la formule x dans l'interpretation interp  *)
		                                        
		| Vrai -> true       (* Si f est la formule Vrai on retourne "true" *)
		| Faux -> false      (* Si f est la formule Faux on retourne "false" *)
		| Implique(x, y) -> implique (applique atm interp x) (applique atm interp y) (* Si f est un Implique, on applique la fonction 'implique' *) 
		                                                                             (* sur les valeurs des formules x et y dans l'interpretation interp  *)
		                                                                             
		| Equivaut(x, y) -> equivaut (applique atm interp x) (applique atm interp y) (* Si f est un Equivaut, on applique la fonction 'equivaut' *) 
		                                                                             (* sur les valeurs des formules x et y dans l'interpretation interp  *)
;;



		(**{C --------------------------------------------------
		  3.1 Definition des fonctions appartient et union 
		  --------------------------------------------------}*)
		
		
(** 
	@param x un atome
	@param liste une liste d'atomes
	@return true si x appartient a la liste sinon false
*)
let rec appartient (x:char) (liste:char list) = match liste with
	| [] -> false
	| tete::queue -> if tete = x then	
						true 
						else appartient x queue
;;


(**
@param l1 une liste d'atomes
@param l2 une liste d'atomes
@return l3 l'union des deux listes l1 & l2
*)
let rec union (l1:char list) (l2:char list) = match l1 with 
	[]-> l2
	|tete::reste -> if (appartient tete l2)  (*si l'atome de la liste 1 est inclus dans la liste 2*)
					then union reste l2  (* on passe a l'atome suivant *)
					else union reste (tete::l2) (* sinon on l'ajoute dans la liste 2 puis on passe a l'atome suivant *)
;;



		(**{C -------------------------------------------------------------------
		3.2 Fonctions atomes: construit l'ensemble des atomes d'une formule
	 ---------------------------------------------------------------------}*)
		
		
(**
	@param form une formule
	@return la liste contenant les atomes constituant la formule
*)
let rec atomes (form:formule) = match form with
	Atome(x) -> (x::[])  (* si form est un atome on l'insere dans la liste*)
	|Et(x, y) |Ou(x, y) |Implique(x, y) |Equivaut(x, y) -> (atomes x)@ (atomes y) (* si form est une formule a connecteur binaire  sur x et y *) 
	                                                                              (* on concatene la liste des atomes de x a celle de y *)
	                                                                              
	|  Non (x)  -> (atomes x) (* Si form est un "Non" on appelle atomes sur la/les formule(s) constituant x *)
	|  Vrai	 -> atomes(Atome('V')) (* Si form est un Vrai, on insere 'V' dans la liste *)
	|  Faux	 -> atomes(Atome('F')) (* Si form est un Faux, on insere 'F' dans la liste *)
;;



			(**{C ----------------------------------
			3.3 Fonction interpretation_zero
			----------------------------------}*)
			
(**
 @param f_liste liste d'atomes
 @return l'interpretation ne contenant que la valeur false
 *)
 let rec interpretation_zero = function (f_liste:char list) -> match f_liste with
	[] -> []
	|head::tail ->false::(interpretation_zero tail)
;;
 
 (**{b algorithme :}
 si interpretation = liste vide on retourne une liste vide
 *)
 (**sinon on met false en tete et on concatene avec l'interpretation suivante de la queue de la liste
*)

																

			(**{C --------------------------------------
			3.4 Fonction interpretation_suivante
			--------------------------------------}*)

(**
@param  interpretation une liste booleenne d'interpretation
@return l'interpretation suivante de l'interpretation donnee en parametre  dans le sens de la ligne suivante dans la table de verite

{b algorithme :}
 si interpretation = liste vide on retourne une liste vide
 si la tete de la liste est un false on retourne une liste avec true comme tete concatene a la queue de la liste
 sinon on met false en tete et on concatene avec l'interpretation suivante de la queue de la liste

*)								
let rec interpretation_suivante (interpretation:bool list) =  match interpretation with
	[] -> []
	|false::queue -> true::queue
	|true::queue -> false::interpretation_suivante queue
;;											
													
			(**-----------------------------*)
			(** 4.Prorietes d'une formmule  *)
			(**-----------------------------*)
			
			
			(**{C ------------------------
			 Propriete satisfaisable
			----------------------------}*)
			
(**
@param  f une formule
@return true si la formule prend au moins une fois la valeur true, false sinon 
*)
let satisfaisable (f:formule) = match f with
	Vrai->true
	|Faux->false
	|_->let atomes_f = atomes (f) in
		let interp = interpretation_suivante(interpretation_zero(atomes_f)) in
		let rec suivant interp =
			if(interp = interpretation_zero(atomes_f)) then if ((applique (atomes_f) (interp) (f) = true)) then true else false 
			else let res = applique (atomes_f) (interp) (f) in
					match res with
					true->true
					|false->suivant(interpretation_suivante interp)
			in suivant (interp)
;;
(**
{b algorithme :} 
*)
	(**
	        si f est un Vrai alors on renvoie true
	*)
	(**
	        si f est un Faux alors on renvoie false
	*)
	(**     sinon
	*)
	(**     atomes_f <- ensemble des atomes constituant f
	*)
	(**     interp 	 <- interpretation_suivante de interpretation_zero de f
	*)
	(**     iteration interp:
	*)
	(**            si interp = interpretation_zero de f (on traite la premiere interpretation)
	*)
	(**			     si la valeur de f dans interp = true
					alors on renvoie true 
				sinon on renvoie false (on aura alors evalue toutes les iterpretations)
	*)
	(**		sinon res <- valeur de f dans interp
	*)
	(**			si res = true alors  renvoie true
	*)
	(**			sinon iteration (interpretation_suivante interp)
*)

(** 
*)
            (**{C ----------------------------
			 Propriete d'antilogie:		la formule prend toujours
			la valeur false
			----------------------------}*)
(**
@param  f une formule
@return true si la formule prend toujours la valeur false, false sinon 
*)
let antilogie (f:formule) = match f with
	Vrai->false
	|Faux->true
	|_->let atomes_f = atomes (f) in
		let interp = interpretation_suivante(interpretation_zero(atomes_f)) in
		let rec suivant interp =
			if((interp = interpretation_zero(atomes_f)) && (applique (atomes_f) (interp) (f)) = false) then true
			else let res = applique (atomes_f) (interp) (f) in
					match res with
					true->false
					|false->suivant(interpretation_suivante interp)
			in suivant (interp)
;;

(**{b algorithme :} 
*)
(**
    si f est un Vrai alors false
*)
(** 
    si f est un Faux alors true
*)
(** sinon:
*)
(**		atomes_f <- ensemble des atomes constituant f
*)
(**		interp 	 <- interpretation_suivante de interpretation_zero de f
*)
(**		iteration interp
*)
(**			si interp = interpretation_zero de f et la valeur de f dans interp = false
*)
(**			alors true
*) 
(**			sinon res <- valeur de f dans interp
*)
(**				si res = true alors false
*)
(**				sinon iteration (interpretation_suivante interp)
*)


			(**{C -----------------------------------------------------
			 Propriete de tautologie:
			la formule prend toujours
				 la valeur true
			----------------------------------------}*)
(**
@param f une formule
@return true si la formule prend toujours la valeur true, false sinon 
*)
let tautologie (f:formule) = match f with
	Vrai->true
	|Faux->false
	|_->let atomes_f = atomes (f) in
		let interp = interpretation_suivante(interpretation_zero(atomes_f)) in
		let rec suivant interp =
			if((interp = interpretation_zero(atomes_f)) && (applique (atomes_f) (interp) (f))) then true
			else let res = applique (atomes_f) (interp) (f) in
					match res with
					false->false
					|true->suivant(interpretation_suivante interp)
			in suivant (interp)
;;

(**
 {b algorithme :} 
*)
(**	si f est un Vrai alors true
*)
(**	si f est un Faux alors false
*)
(**	sinon
*)
(**		atomes_f <- ensemble des atomes constituant f
*)
(**		interp 	 <- interpretation_suivante de interpretation_zero de f
*)
(**		iteration interp
*)
(**			si interp = interpretation_zero de f et si la valeur de f dans interp = true
*)
(**				alors true 
*)
(**			sinon res <- valeur de f dans interp
*)
(**				si res = false alors false
*)
(**				sinon iteration (interpretation_suivante interp)
*)

			(**{C --------------------------------------
			 Propriete contingente
			---------------------------------------}*)
(**
  @param f une formule
  @return true si la formule prend au moins une fois true et false, false sinon 
*)
let contingente (f:formule) = (not (tautologie f) && not (antilogie f));;

(**{b algorithme :} 
*)
(**		retourner le resultat du "et" logique entre (!tautologie de f) et (!antilogie de f)
*)
(**
{i explication} :	
	on parle de tautologie si f renvoie toujours true
*)
(**		et d'antilogie si f renvoie toujours false
	donc si chacune renvoie false
*)
(**		f est une formule contingente, car f aura pris au moins
	une fois true et false	
*)




            (**-------------------*)
            (**  Simplifications  *)
            (**-------------------*)
            
            
 (**
   @param f une formule
   @return la formule f a laquelle on a applique les regles de simplification selon les opereteurs logiques qui la constituent
 *)           
 let rec simplification (f:formule) = match f with
        | Vrai -> Vrai
        | Faux -> Faux
        | Atome(x) -> Atome(x)
        | Ou(x, y) -> if( (x = Vrai) || (y = Vrai) ) then Vrai else  (* P v T = T *)
 					  if (x = Non(y)) then Vrai else (* P v non-P = T *)
 					  if (x = y) then x else Ou(simplification(x), simplification(y)) (* P v P = P *)
 									                                                    
 		| Et(x, y) -> if( (x = Faux) || (y = Faux) ) then Faux else (* P ^ bottom = bottom *)
 					  if (x = y) then x else (* P ^ P = P *)
 					  if (x = Non(y)) then Faux else Et(simplification(x), simplification(y)) (* P ^ non-P = bottom *)
 									                                                    
 		| Non(x) -> if( x = Faux ) then Vrai else (* non-bottom = T *)
 					if( x = Vrai ) then Faux else Non(simplification(x)) (* non-T = bottom *)
 									                    
 		| Implique(x, y) -> if( (x = Faux) || (y = Vrai)) then Vrai else (* (bottom -> P = T) & (P -> T = T) *)
 							if(x = Vrai) then y else (* T -> P = P *)
 							if( (y = Faux) || ( x = Non(y)) ) then Non(x) (* (P -> bottom =  non-P) & (P -> non-P = non-P) *)
 									                          else Implique(simplification(x), simplification(y)) 
 									                                   
 		| Equivaut(x, y) -> Equivaut(simplification(x), simplification(y))
 		;;
 		
(**{b Alogorithme:}
*)
(** Si f est un "Vrai" ou un "Faux" ou un "Atome" alors on retoutne f tel quel
*)
(** {b Si f est un "Ou" alors }
*)
(** Si l'un au moins de ses argument est un "Vrai" alors on retourne Vrai
*)
(** Si ses arguments sont complementaires on retourne Vrai
*)
(** Si ses deux arguments sont le meme element, on retourne cet element
*)
(** Sinon on applique les regles de simplification a chacun de ses arguments
*)
(** {b Si f est un "Et" alors}
*)
(** Si l'un au moins de ses elements est un "Faux" alors on retourne Faux
*)
(** Si ses deux arguments sont le meme element, on retourne cet element
*)
(** Si ses arguments sont complementaires on retourne Faux
*)
(** Sinon on applique les regles de simplification a chacun de ses arguments*)
(** {b Si f est un "Non" alors} *)
(** on retourne le complementaire de son argument, si celui-ci est un "Vrai" ou un "Faux"*)
(** Sinon on applique les reles de simplification a son argument *)
(** {b Si f est un "Implique" alors } *)
(** Si son premier argument est un "Faux" ou si son second argument est un "Vrai" alors on retourne Vrai *)
(** Si son premier argument est un "Vrai" alors on retourne son secon argument *)
(** Si son second argument est un "Faux" ou si ses deux argument sont complementaires alors on retourne le complementaire de son premier argument *)
(* Sinon on applique les regles de simplification a chacun de ses arguments *)
(** {b Si f est une "Equivalence" alors} *)
(** On applique les regles de simplification a chacun de ses arguments *)

