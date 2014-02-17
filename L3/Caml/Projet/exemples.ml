	        (*----------------------------------------------------------------*)
			(* 1.2  Exemples de formules (traduction logique en commentaires  *)
			(*----------------------------------------------------------------*)


Et ( Atome('p'), Ou(Faux, Atome('q')));;  (*==>  p /\ (⊥ \/ q)  *)
Et ( Atome('p'), Ou(Faux, Et(Vrai, Atome('q'))));; (* ==>  p /\ ( ⊥ \/ (⊤ /\ q)) *)
Et (Atome('p'), Non(Ou(Atome('q'), Faux)));;  (*==>  p /\ ¬(q \/ ⊥) *) 
Non(Atome('x'));; (* ==> ¬x *)
Ou(Non(Et(Vrai,Atome('a'))), Faux);; (* ¬(⊤^a) \/ ⊥ *)
Ou(Vrai, Faux);; (* ==> ⊤ \/ ⊥ *)
Et(Ou(Non(Implique(Vrai,Vrai)), Equivaut(Atome('b'), Non(Atome('b')))), Faux);; (*==> (¬( ⊤ -> ⊤)\/ ( b <-> ¬b ))/\ ⊥  *)
Vrai;; (* ==> ⊤ *)
Implique(Faux, Vrai);; (*==> ⊥ -> ⊤ *)
Ou(Non(Atome('e')), Vrai);; (** => ¬e \/ ⊤)


			(*----------------------*)
			(* 2.1 fonction valeur  *)
			(*----------------------*)
			
(*Exemples: valeur 'b' ['a'; 'b'; 'c'] [true];;  Résultat => Exception: Failure "longueur des listes (atomes & interpretation) incompatible". *)

let maVal = valeur 'c' ['a'; 'b'; 'c'] [true; true; false];; (*=> - maVal: bool = false;;*)

        (*------------------------*)
		(* 2.3 fonction Applique  *)
		(*------------------------*)
		
		(*Exemples:*)

(* (avb)^(avc) a->true b->false c->true *)
applique (['a';'b';'c']) ([true;false;true]) ( Et(  Ou( Atome('a'),Atome('b')  ), Ou( Atome('a'), Atome('c')) )  );;
(*- : bool = true *)

(* ⁊a a->true *)
applique (['a';'b']) ([true;true]) ( Non(Atome('a')) );;
(*- : bool = false *)

(* ⁊b v ⊥  b->false *)
applique (['a';'b']) ([true;false]) (    Ou( Non(Atome('b')), Faux  )  );;
(*- : bool = true *)

(* ⊥ *)
applique (['a';'b']) ([true;false]) (     Faux  );;
(*- : bool = false *)

	    (*--------------------------------------------------*)
		(* 3.1 Definition des fonctions appartient et union *)
		(*--------------------------------------------------*)
		
		(*Exemple:*)
appartient 'f' ['a';'c';'b';'f';'x';'v'];;  (* ==> - : bool = true *)
appartient 'f' ['a';'c';'b';'h';'x';'v'];;  (* ==> - : bool = false *)


let un = ['a';'b';'c'] and deux = ['b';'d';'f';'c'];; (* ==> val un : char list = ['a'; 'b'; 'c']         *)
													  (*     val deux : char list = ['b'; 'd'; 'f'; 'c']  *)

let assoc1 = union un deux;;  (*  - : char list = ['a'; 'b'; 'd'; 'f'; 'c']  *)
let assoc2 = union deux un;;  (*  - : char list = ['f'; 'd'; 'a'; 'b'; 'c']  *)
				  (* on s'assure que notre fonction est associative *)

	    (*---------------------------------------------------------------------*)
		(* 3.2 Fonctions atomes: construit l'ensemble des atomes d'une formule *)
		(*---------------------------------------------------------------------*)

(*Exemples*)
(* p^(⊥ v q) *)
atomes(Et ( Atome('p'), Ou(Faux, Atome('q'))));;  (* ==> - : char list = ['p'; 'F'; 'q'] *)

(* p^(⊥ v(⊤^q)) *)
atomes (Et ( Atome('p'), Ou(Faux, Et(Vrai, Atome('q')))));; (* ==> - : char list = ['p'; 'F'; 'V'; 'q'] *)

	        (*----------------------------------*)
			(* 3.3 Fonction interpretation_zero *)
			(*----------------------------------*)
			
			(* Exemple *)
interpretation_zero ['a'; 'b'; 'd'; 'f'; 'c'];; (* ==> - : char list = [false;false;false;false;false] *)


