/**
 * 
 */
package humain;

/**
 * @author diallo
 *
 */
public class Samourai extends Ronin{
	private String seigneur;
	
	/**
	 * Samourai constructor
	 * @param argent
	 * @param boisson
	 * @param nom
	 * @param seigneur
	 */
	public Samourai(int argent, String boisson, String nom, String seigneur) {
		super(argent, boisson, nom);
		this.seigneur = seigneur;
	}

	/**
	 * @return the seigneur
	 */
	public String getSeigneur() {
		return seigneur;
	}
	
	/**
	 * greeting Samourai
	 */
        @Override
	public void direBonjour(){
		super.direBonjour();
		this.parler("Je sers le seigneur " + seigneur);
	}
	
	/**
	 * to drink
	 */
	public void boire(String boisson){
		super.boire();
		this.parler("Ahhh,Quand même je bois aussi du " + boisson + " ! GLOUPS GLOUPS !");
	}
	
}
