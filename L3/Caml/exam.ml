(*examen mai 2011*)
(*
val valeur : 'a -> ('a * 'b) list -> 'b = <fun>
*)
let rec valeur = fun cle list_assoc ->
    match list_assoc with
    |[]-> failwith"cle inexistante"
    |(key, value)::queue-> if cle=key then value else valeur cle queue
;;
(*
val inserer : 'a -> 'b -> ('a * 'b) list -> ('a * 'b) list = <fun>
*)
let rec inserer = fun cle valeur list_assoc ->
    match list_assoc with 
    |[]-> (cle, valeur)::[]
    |(key, value)::queue -> if cle=key then failwith"cle deja presente"
                            else if cle < key then
                                (cle,valeur)::list_assoc
                            else
                                (key, value)::inserer cle valeur queue
;;
(*
val supprime : 'a ->('a * 'b) list -> ('a * 'b) list = <fun>
*)
let rec supprime = fun cle list_assoc ->
    match list_assoc with
    |[]-> failwith"cle absente"
    |(key,value)::queue -> if cle=key then queue else supprime cle queue
;;
(*
val remplace : 'a -> 'b -> ('a * 'b) list -> ('a * 'b) list = <fun>
*)
let rec remplace = fun cle valeur list_assoc ->
    match list_assoc with
    |[]->failwith"cle absente"
    |(key,value)::queue -> if cle=key then (key,valeur)::queue 
                           else remplace cle valeur queue
;;
type (`a,`b) env = (`a*`b) list;;
let get liste a =
match liste with
|[]->failwith"absent"
|(`a,`b)env::q-> `b
;;