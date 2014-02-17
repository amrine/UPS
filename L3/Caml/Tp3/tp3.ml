(*
Diallo Alpha Oumar Binta
21007631
Licence 3 Informatique Universite Paul Sabatier
groupe 3.1
tp3
*)

(* a) declaration de liste *)
let list1 = 10::20::[30];;
(* val list1 : int list = [10; 20; 30]*)

let list2= [10;20;30];;
(* val list2 : int list = [10; 20; 30]*)

(* b)fonction :
	head et tail qui retourne respectivement le premier element
	de la liste et la liste privee de son premier element *)

let head = function 
	[] -> failwith "La liste est vide"
	|x::l -> x
	;;
(* val head : 'a list -> 'a = <fun> *)

let tail = function 
	[] -> []
	|x::l -> l
	;;
(* val tail : 'a list -> 'a list = <fun> *)

(* c) fonction pour acceder au 3eme element des listes suivantes
[1;2;3;4];; 
[(1,2);(3,4);(5,6)];;
[];;
[1;2];;
[[1];[2;3;4];[]] ;;
[[1,2];[3,4] ;[5,6]];;
*)
let rec third = function
		e::l-> head (tail l)
		|_ -> failwith "Erreur";;

(*2 donnons les types 
[];;
- : 'a list = []

[1;2;true];; ce type n'existe pas nous attendons un type int
[1;(2,true)];; This expression has type int * bool but is here used with type int

[1,2,3];;
- : (int * int * int) list = [(1, 2, 3)]

 [[1,2];[3,4]];;
- : (int * int) list list = [[(1, 2)]; [(3, 4)]]

[[1,2];[3,4,5]];; n'existe pas on doit avoir un couple (int*int) list

[1;2;3];;
- : int list = [1; 2; 3]

[(1,true,5.0);(2,false,6.4);(3,true,7.9)];;
- : (int * bool * float) list = [(1, true, 5.); (2, false, 6.4); (3, true, 7.9)]

([1;2;3],[[];[true,false]]);;
- : int list * (bool * bool) list list = ([1; 2; 3], [[]; [(true, false)]])
*)

(* 3) Fonctions simples manipulant les listes *)

(*a*)
let rec consCpleDouble = function
		1-> [1,2]
		|n-> (n,2*n)::consCpleDouble(n-1)
		;;
		
(*b*)
let rec consCpleF = fun f n -> 
  match n with
	|1 -> [1,f 1]
	|n ->(n,f n)::consCpleF f (n-1)
	;;
	
(* 4*)
(* a) appartient
val appartient : 'a -> 'a list -> bool = <fun>
 *)
let rec appartient = fun el liste ->
	match liste with
	|[] -> false
	|t::q -> if t = el then true else appartient el q
;;

(* b) consEns 
val consEns : 'a list -> 'a list = <fun>
*)
let rec consEns = function
	[] -> []
	|t::[] -> [t]
	|t::q -> if (appartient t q) then consEns q else t::consEns q
;;

(* c) 
val cardinal : 'a list -> int = <fun>
*)
let rec cardinal = function
	[] -> 0;
	|t::q -> 1 + cardinal q
;;

(* d) *)
let  rec union = fun l1 -> fun l2 -> match (l1,l2) with
				        (l, []) -> l
				      |([],l) -> l
				      |(e1::l1, l2) ->  if (appartient e1 l2) then union l1 l2 else e1::union l1 l2;;


let  rec inter = fun l1 -> fun l2 -> match (l1,l2) with
				        (l, []) -> []
				      |([],l) -> []
				      |(e1::l1, l2) -> if (appartient e1 l2) then e1::inter l1 l2 else inter l1 l2;;

(* f) Ecrire la fonction inclus qui, étant donnés deux ensembles, indique si le premier
est inclus dans le second. *)
let  rec inclus = fun l1 -> fun l2 -> match (l1,l2) with
				        (l, []) -> false
				      |([],l) -> true
				      |(e1::l1, l2) -> if (appartient e1 l2) then inclus l1 l2 else false;;	
(* fonction egalite *)
let egalite = fun l1 -> fun l2 -> inclus l1 l2 && inclus l2 l1;;  

(* h) Ecrire la fonction prodCart qui étant donnés deux ensembles réalise leur produit
cartésien.
Rappel : Soient deux ensembles A = {a1,a2,…,an} et B = {b1,b2,…,bk}
Le produit cartésien de A par B est l’ensemble
{(a1,b1),…,(a1,bk),(a2,b1),…,(a2,bk),… ,(an,b1),…,(an,bk)} *)

let rec f = fun l1 -> fun l2 -> match (l1,l2) with
				        (l, []) -> []
				      |([],l) -> []
				      |(e1::l1, e2::l2) -> (e1,e2)::(f (e1::l1) l2);;

let  rec prodCart = fun l1 -> fun l2 -> match (l1,l2) with 
                     (l, []) -> []
				    |([],l) -> []
				    |(e1::l1, e2::l2) -> union (f (e1::l1) (e2::l2)) (prodCart l1 (e2::l2));;
								
				      
(* i) Ecrire la fonction ensSousEns qui, étant donné un ensemble, construit
l’ensemble de tous ses sous ensembles.*)
let rec ensSousEns = function
				 [] -> []
				|e::l -> [e]::(ensSousEns l);;
				      
				      
(* prodCart [0;1;4;5] [false;true];; *)