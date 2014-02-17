/**
 * 
 */
package banque;

/**
 * @author oumar
 *
 */
public class Compte {
	private float solde;
	


	/**
	 * creation du compte
	 */
	public Compte() {
		solde = (float) 0.;
	}

	/**
	 * @return the solde
	 */
	public float getSolde() {
		return solde;
	}
	
	/**
	 * Pour faire un depot sur le compte
	 * @param valeur
	 */
	public void depot(float valeur){
		this.solde += valeur; 
	}
	
	/**
	 * Pour faire un retrait sur le compte
	 * @param  valeur
	 */
	public void retrait(float valeur){
		this.solde -= valeur;
	}
	
	/**
	 * Afficher le solde du compte
	 */
	public void afficherSolde(){
		System.out.println("le solde de votre compte est : " + this.solde);
	}
	
	/**
	 * Pour effectuer un virement
	 * @param valeur
	 * @param destinataire
	 */
	public void virer(float valeur, Compte destinataire){
		destinataire.solde += valeur;
		this.solde -= valeur;
	}
	

}
