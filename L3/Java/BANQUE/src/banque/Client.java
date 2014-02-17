/**
 * 
 */
package banque;

/**
 * @author oumar
 *
 */
public class Client {
	private String nom;
	Date date;
	private int nbreCompte;
	private Compte[] comptes;
	private final int MAX = 100;
	
	/**
	 * @param nom
	 * @param date
	 */
	public Client(String nom, Date date) {
		this.nom = nom;
		this.date = date;
		nbreCompte = 0;
		comptes= new Compte[MAX];
	}



	/**
	 * renvoie le nombre de compte
	 * @return the nbreCompte
	 */
	public int getNbreCompte() {
		return nbreCompte;
	}


	/**
	 * renvoie le nom du client
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * renvoie la date de naissance du client
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * renvoi le compte correspondant au numero
	 * @return the comptes
	 */
	public Compte getComptes(int numero) {
		return comptes[numero];
	}

	
	/**
	 * Afficher le solde de chaque compte
	 */
	public void afficherBilan(){
		int numeroCompte =0;
		for(int i=0; i < nbreCompte ; i++){
			numeroCompte ++;
			System.out.println("Le solde du compte " + numeroCompte + " est : " + getComptes(i).getSolde());
		}
	}
	
	/**
	 * Calculer le solde total
	 * @return the total
	 */
	public float soldeTotal(){
		float total= (float) 0.0;
		for(int i=0; i < nbreCompte ; i++){
			total += getComptes(i).getSolde() ;
		}
		return total;
	}
	
	/**
	 * Afficher le solde total des comptes
	 */
	public void afficherSolde(){
		System.out.println("Le solde total des comptes est : " + soldeTotal());
	}
	
	/**
	 * Ajouter un compte
	 * @return the value
	 */
	public int ajouterCompte(){
		if (nbreCompte < MAX){
			comptes[nbreCompte] = new Compte();
			nbreCompte ++;
			return nbreCompte;
		} else return MAX;
	}
}

