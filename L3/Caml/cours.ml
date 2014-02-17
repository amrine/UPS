let f = function 
  |0. -> 3.
  | x -> if x>0. then x+. 6. else x-.2.;;
  
let f = fun x ->
  match x with
  |0. -> 3.
  |_-> if x>0. then x+. 6. else x-.2.;;
  
let dernier = fun x-> x mod 10;;
let entierPriveDernierChiffre = fun x-> x/10;;

let rec nbOcc = fun x occ ->
    if x>=10 then
        if dernier x=occ then
            1+nbOcc(entierPriveDernierChiffre x, occ)
        else nbOcc(entierPriveDernierChiffre x, occ)
    else
        if x=occ then 
            1
        else 0
;;

let rec premierChiffre = fun n->
    if n <10 then n 
    else premierChiffre(entierPriveDernierChiffre n)
;;