/**
 * 
 */
package banque;

/**
 * @author oumar
 *
 */
public class Banque {
	private int nbreClient;
	private Client[] clients;
	private final int MAX = 100;

	/**
	 * creation de la banque
	 */
	
	public Banque() {
		nbreClient = 0;
		clients = new Client[MAX];
	}

	/**
	 * @return the nbreClient
	 */
	public int getNbreClient() {
		return nbreClient;
	}

	/**
	 * @return the clients
	 */
	public Client getClients(int numero) {
		return clients[numero];
	}
	
	/**
	 * Ajouter un client � la banque
	 * @return the value
	 */
	public int ajouterClient(Client leClient){
		if (nbreClient < MAX){
			clients[nbreClient] = leClient;
			nbreClient ++;
			return nbreClient;
		} else return MAX;
	}
	
	/**
	 * Afficher le bilan complet de la banque
	 */
	public void afficherBilanBanque(){
		float sommeBanque = (float) 0.;
		int cpt;
		for(int i=0; i< nbreClient; i++){
			cpt =i; cpt++;
			sommeBanque += getClients(i).soldeTotal();
			System.out.println("Le Bilan Total du client " + cpt + " est de : " + getClients(i).soldeTotal());
		}
		System.out.println("Le Bilan Total de la banque est de : " + sommeBanque);
	}
}
