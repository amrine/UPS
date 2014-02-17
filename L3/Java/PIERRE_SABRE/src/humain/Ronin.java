/**
 * 
 */
package humain;

/**
 * @author diallo
 *
 */
public class Ronin extends Humain {
	private int honneur;
	
	/**
	 * Ronin Constructor
	 * @param argent
	 * @param boisson
	 * @param nom
	 */
	public Ronin(int argent, String boisson, String nom) {
		super(argent, boisson, nom);
		honneur = 1;
	}
	
	/**
	 * donate money to the commercant
	 * @param argent
	 * @param commercant
	 */
	public void donner(int argent, Commercant commercant){
		this.parler("Tiens Marchant voilà " + argent +" sous.");
		commercant.recevoir(argent);
		this.perdreArgent(argent);
	}
	
	/**
	 * provoke yakuza
	 * @param yakuza
	 */
	public void provoquer(Yakuza yakuza){
		if (honneur*2 > yakuza.getReputation()){
			this.ajouterArgent(yakuza.getArgent());
			yakuza.perdre();
			honneur++;
			this.parler("Je t’ai eu petit yakuza !");
		}
		else{
			this.parler("Aye j'ai perdu contre ce petit yakuza enfin un grand maintentant !");
			honneur--;
			yakuza.gagner();
		}
		
	}

	

}
