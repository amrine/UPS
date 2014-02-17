/**
 * 
 */
package humain;

/**
 * @author diallo
 *
 */
public class Yakuza extends Humain{
	private String clan;
	private int reputation;
	
	/**
	 * Yakuza constructor
	 * @param argent
	 * @param boisson
	 * @param nom
	 * @param clan
	 */
	public Yakuza(int argent, String boisson, String nom, String clan) {
		super(argent, boisson, nom);
		this.clan = clan;
		reputation = 0;
	}

	/**
	 * @return the clan
	 */
	public String getClan() {
		return clan;
	}

	/**
	 * @return the reputation
	 */
	public int getReputation() {
		return reputation;
	}
	
	/**
	 * 
	 * @param commercant
	 */
	public void extorquer(Commercant commercant){
		this.reputation++;
		this.ajouterArgent(commercant.getArgent());
		commercant.seFaireExtorquer();
		this.parler("J’ai piqué le fric de Marchant");
	}
	
	/**
	 * Yakuza lose duel
	 */
	public void perdre(){
		this.parler("J’ai perdu mon duel et mes " + this.getArgent() +" sous, snif...");
		this.perdreArgent(this.getArgent());
		this.reputation--;
	}
	
	/**
	 * yakuza win duel
	 */
	public void gagner(){
		this.parler("J’ai gagné mon duel hahaha...");
		this.reputation++;
	}
	
	/**
	 * greeting yakuza
	 */
        @Override
	public void direBonjour(){
		super.direBonjour();
		this.parler("Je suis du clan " + clan);
	}

}
