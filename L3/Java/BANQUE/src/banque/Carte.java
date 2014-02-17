/**
 * 
 */
package banque;

import java.util.Arrays;

/**
 * @author oumar
 *
 */
public class Carte {
	private int[] code = new int[4];
	private Banque banque;
	private int numClient;
	private int numCompte;
	private Date dateValiditer;
	private int compteurErr;
	
	
	/**
	 * @param code
	 * @param banque
	 * @param numClient
	 * @param numCompte
	 * @param dateValiditer
	 */
	public Carte(int[] code, Banque banque, int numClient, int numCompte,
			Date dateValiditer) {
		this.code = code;
		this.banque = banque;
		this.numClient = numClient;
		this.numCompte = numCompte;
		this.dateValiditer = dateValiditer;
		compteurErr = 3;
	}
	
	/**
	 * @return the code
	 */
	public int[] getCode() {
		return code;
	}

	/**
	 * @return the banque
	 */
	public Banque getBanque() {
		return banque;
	}

	/**
	 * @return the numClient
	 */
	public int getNumClient() {
		return numClient;
	}

	/**
	 * @return the numCompte
	 */
	public int getNumCompte() {
		return numCompte;
	}

	/**
	 * @return the dateValiditer
	 */
	public Date getDateValid() {
		return dateValiditer;
	}

	/**
	 * @return the compteurErr
	 */
	public int getCompteurErr() {
		return compteurErr;
	}
	
	/**
	 * codeValide
	 * @param code
	 * @return boolean true if right code else false
	 */
	public boolean codeValide(int[] code){
		if (this.code.length != code.length) return false;
		if (compteurErr==0) return false;
		for(int i=0; i < this.code.length ; i++){
			if (this.code[i] != code[i]){
				compteurErr--;
				return false;
			}
		}
		return true;
	}
	
	/**
	 * payer (virement sur un autre compte)
	 * @param banque
	 * @param numClient
	 * @param numCompte
	 * @param montant
	 */
	public void payer(Banque banque, int numClient, int numCompte, float montant){
		
		this.banque.getClients(this.numClient).getComptes(this.numCompte).virer(montant,banque.getClients(numClient).getComptes(numCompte));
	}
	
	
	

	/* 
	 * * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Carte [banque=" + banque + ", code=" + Arrays.toString(code)
				+ ", compteurErr=" + compteurErr + ", dateValiditer="
				+ dateValiditer + ", numClient=" + numClient + ", numCompte="
				+ numCompte + "]";
	}
}
