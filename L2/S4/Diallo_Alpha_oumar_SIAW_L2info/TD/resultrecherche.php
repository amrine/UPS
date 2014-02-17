<html>
	<head>
		<title> Resultats de la recherche </title>
	</head>
	<body>
		<p>
			<?php
				require_once("Connect.php");
				$connexion = mysql_pconnect(SERVEUR,NOM,PASSE);
				if (!$connexion) {
					echo "D&eacute;sol&eacute;, connection &agrave; ".SERVEUR." impossible <br />";
				}
				if (!mysql_select_db(BASE,$connexion)) {
					echo "D&eacute;sol&eacute;, acc&egrave;s &agrave; la base ".BASE." impossible <br />";
					exit;
				}
			?>
		</p>

		<?php
			// on recupere les donnees du formulaire
			$condition = " ";
			$vtitre = $_GET['ftitre'];
			if (!empty($_GET['fauteur'])) {
				$vauteur = $_GET['fauteur'];
				$condition = "AND NL in (select NL from Ecrire where NA in (select NA from Auteur where nom='$vauteur'))";
			}
			$first = true;
			$type = "";
			if (!empty($_GET['fpoche'])) {
				$type = "format='poche'";
				$first = false;
			}
			if (!empty($_GET['fgrand'])) {
				if (!$first) {
					$type = $type." OR format='GrandFormat'";
				} else {
					$type = "format='GrandFormat'";
					$first = false;
				}
			}
			if (!empty ($_GET['relie'])) {
				if (!first){
					$type = $type."OR format='Relie'";}
				else {
					$type = "format = 'Relie'";
					$first = false;}
			}
			if (!first)
				$condition = $condition."AND ($type)";
			//on construit la requete
			$requete = "select titre,editeur,anneeparution,format,prix from livre where titre = '$vtitre' $condition order by prix desc ";
			//on affiche le resultat
			$resultat = mysql_query($requete,$connexion);
			if ($resultat){
				echo " <table>
							<tr><td> Titre </td>
								<td> Editeur </td>
								<td> Annee </td>
								<td> Format </td>
								<td> Prix </td></tr>";
				while ($livre = mysql_fetch_object($resulat)){
					echo "<tr><td> $livre->titre </td>
							  <td> $livre->editeur </td>
							  <td> $livre->annee </td>
							  <td> $livre->format </td>
							  <td> $livre->prix </td></tr>";
				}
				echo "</table>";
			}
		?>
	</body>
</html>			























