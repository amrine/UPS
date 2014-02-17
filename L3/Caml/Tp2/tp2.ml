(*
Diallo Alpha Oumar Binta
21007631
Licence 3 Informatique Universite Paul Sabatier
groupe 3.1
tp2
*)

(*Question 1*)
let sommeCarres = 
    let rec calcul = function
        0 ->0;
        |n->(n*n)+calcul(n-1)
    in fun i -> if i < 0 then failwith "argument negatif"
                else calcul i
;;

(*Question 2*)
let sommeFonction = 
    let rec calcul = fun f n ->
        match n with
        0-> f 0
        |i-> f i + calcul f (i-1)
    in fun fx x -> if x < 0 then failwith "n négatif"
                         else calcul fx x
;;

let carres  = fun x -> x*x;;

let secSommeCarres = function
    0->0
    |n-> sommeFonction(carres) n
;;

(*Question 3*)
let rec sommeChiffres = fun x ->
    if x > 9 then (x mod 10) + sommeChiffres(x/10)
    else x
;;

(*Question 3*)
let rec sommeIteree = fun n ->
    let somme = sommeChiffres n in
        if somme > 9 then sommeIteree somme
        else somme
;;

(*Question 5*)
let rec iterer = fun  f p x ->
	if p x then x
	else iterer f p (f x)
;;
	
(*Question 6*)
let rec sommeIteree2 = fun n->
	iterer sommeChiffre (fun n-> n <10) n
;;
	
(*Question 7*)
let rec ack = function
    (0, n) -> n+1
    |(m, 0)-> if m >0 then ack(m-1, 1) else failwith "m negatif"
    |(m, n)-> if m>0 && n>0 then ack(m-1, ack(m, n-1)) else failwith "negatif"
;;
