Date : le 8/4/11
TD5 - PHP


> Base de donnees :

LIVRE(NL, TITRE, EDITEUR, ANNEEPARUTION, FORMAT, PRIX)
AUTEUR(NA, NOM, PRENOM, DATEN)
ECRIRE(#NL, #NA)


________________________
recherche.html

<html>
	<head>
		<title> Recherches </title>
	</head>
	<body>
		<table>
			<tr>
				<td> Titre du livre </td>
				<td> <input type="text" name="titre"> </td>
			</tr><tr>
				<td> Auteur </td>
				<td> <input type="text" name="auteur"> </td>
			</tr><tr>
				<td> Format </td>
				<td>
					Poche <input type="checkbox" name="format" value="poche"> <br />
					Grand format <input type="checkbox" name="format" value="grandformat"> <br />
					Relie <input type="checkbox" name="format" value="relie">
				</td>
			</tr>
		</table>


	</body>
</html>



> requetes si :
	- on considere uniquement le titre
	- le titre et l'auteur
	- le titre et 2 formats
	- le titre et l'auteur et 2 formats


1) select titre, editeur, anneeparution, format, prix
   from Livre
	where titre=' '
	order by prix desc;

2) select titre, editeur, anneeparution, format, prix
	from Auteur, Livre, Ecrire
	where Livre.NL=Ecrire.NL
	  and Auteur.NA=Ecrire.NA
	  and nom=' '
	  and titre = ' '
	order by prix desc;

3) select titre, editeur, anneeparution, format, prix
	from Livre
	where titre=' '
	  and (format='relie' or format='poche')
	order by prix desc;

4) select titre, editeur, anneeparution, format, prix
	from Livre, Auteur, Ecrire
	where Livre.NL=Ecrire.NL
	  and Auteur.NA=Ecrire.NA
	  and nom=' '
	  and titre=' '
	  and (format='relie' or format='poche')
	order by prix desc;

where NL in (select NL
				 from Ecrire
				 where NA in (select NA
								  from Auteur
								  where nom=' '))















