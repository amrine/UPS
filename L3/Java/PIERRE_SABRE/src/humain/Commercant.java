/**
 * 
 */
package humain;

/**
 * @author diallo
 *
 */
public class Commercant extends Humain {
	
	/**
	 * Commercant constructor
	 * @param argent
	 * @param nom
	 */
	public Commercant(int argent,String nom) {
		super(argent,"thé", nom);
	}

	/**
	 * to lose money
	 */
	public int seFaireExtorquer(){
		this.perdreArgent(this.getArgent());
		this.parler("J’ai tout perdu ! Le monde est trop injuste...");
		return (this.getArgent());
	}
	
	/**
	 * to get money
	 */
	public void recevoir(int argent){
		this.ajouterArgent(argent);
		this.parler("Je te remercie généreux donateur !");
	}

}
