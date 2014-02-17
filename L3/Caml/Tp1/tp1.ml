(*
Diallo Alpha Oumar Binta
21007631
Licence 3 Informatique Universite Paul Sabatier
groupe 3.1
tp1
*)
let rang = fun jour->
  match jour with
  |"lundi" -> 1
  |"mardi" -> 2
  |"mercredi" -> 3
  |"jeudi" -> 4
  |"vendredi" -> 5
  |"samedi" -> 6
  |"dimanche" -> 7
  |_ -> 0
;;

let inf = fun jour1 jour2 ->
  (rang jour2) - (rang jour1) mod 7 = 1;;

let jsem = fun numero ->
  match numero with
  |1 -> "lundi"
  |2 -> "mardi" 
  |3-> "mercredi" 
  |4-> "jeudi" 
  |5-> "vendredi" 
  |6-> "samedi" 
  |7-> "dimanche" 
  |_ -> "ce jour n'existe pas"
;;

(*version 1 cssuc avec filtrage *)
let csucc = fun  jour ->
	match jour with
  	|"lundi" -> "mardi"
  	|"mardi" -> "mercredi"
  	|"mercredi" -> "jeudi"
  	|"jeudi" -> "vendredi"
  	|"vendredi" -> "samedi"
  	|"samedi" -> "dimanche"
  	|"dimanche" -> "lundi"
  	|_ -> "ce jour n'existe pas"
;;

let csucc2 = fun jour->
	let numJourCourant = rang(jour) in
	if (numJourCourant = 7)	then jsem(1)
	else jsem(numJourCourant+1);;

let csucc3 = fun jour->
	let numJourCourant = rang(jour) in
	jsem((numJourCourant + 1) mod 7);;

let cpred = fun  jour ->
        match jour with
        |"lundi" -> "dimanche"
        |"mardi" -> "lundi"
        |"mercredi" -> "mardi"
        |"jeudi" -> "mercredi"
        |"vendredi" -> "jeudi"
        |"samedi" -> "vendredi"
        |"dimanche" -> "samedi"
        |_ -> "ce jour n'existe pas"
;;

let cpred2 = fun jour->
	let numJourCourant = rang(jour) in
        if (numJourCourant = 1) then jsem(7)
        else jsem(numJourCourant-1);;
(* cela ne marche avec la version 3 car 0 mod 7 =0
let cpred4 = fun jour->
        let numJourCourant = rang(jour) in
        jsem((numJourCourant - 1) mod 7);;*)

(*Definition du type date*)
type date = int*int*int;;

let bissextile = fun year ->
	((year mod 4 =0) && (year mod 100!= 0)) || (year mod 400 =0);;

let nbjour = fun numMonth year ->
	if (numMonth =2) 
	then(
		if (bissextile(year))
		 then 29
		else 28)
	else	
	if ((numMonth = 4) || (numMonth=6) || (numMonth=9) || (numMonth=11))
	 then 30
	else 31)
;; 
