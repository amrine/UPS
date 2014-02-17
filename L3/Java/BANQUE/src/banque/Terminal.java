/**
 * 
 */
package banque;

/**
 * @author oumar
 *
 */
public class Terminal {
	private int[] codeEntree;
	private Date dateDuJour;
	
	
	/**
	 * @param codeEntree
	 * @param dateDuJour
	 */
	public Terminal(int[] codeEntree, Date dateDuJour) {
		this.codeEntree = codeEntree;
		this.dateDuJour = dateDuJour;
	}
	
	public boolean dateValide(Carte carte){
		return (carte.getDateValid().getJour() >= dateDuJour.getJour()
				&& carte.getDateValid().getMois() >= dateDuJour.getMois()
				&& carte.getDateValid().getAn() >= dateDuJour.getAn());
		
	}
	
	public  String genererAutorisation(Carte carte, float montant){
		if (carte.getCompteurErr() <= 0) return "Nombre maximum d'essai atteint";
		if (!carte.codeValide(codeEntree)) return "Code invalide";
		if (!dateValide(carte)) return "Carte perimee";
		if (montant > 1000) return "Montant > 1000 euro";
		if ((carte.getBanque().getClients(carte.getNumClient()).getComptes(carte.getNumCompte()).getSolde() - montant) < 0)
			return "Provision insuffisante";
		return "OK";
	}
}