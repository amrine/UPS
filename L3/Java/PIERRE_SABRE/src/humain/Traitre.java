/**
 * 
 */
package humain;

/**
 * @author diallo
 *
 */
public class Traitre extends Samourai{
	private int levelOfBetrayal;
	
	/**
	 * Traitor Constructor
	 * @param argent
	 * @param boisson
	 * @param nom
	 * @param seigneur
	 */
	public Traitre(int argent, String boisson, String nom, String seigneur) {
		super(argent, boisson, nom, seigneur);
		levelOfBetrayal = 0;
	}

	/**
	 * @return the levelOfBetrayal
	 */
	public int getLevelOfBetrayal() {
		return levelOfBetrayal;
	}
	
	/**
	 * 
	 * @param commercant
	 */
	public void extorquer(Commercant commercant){
		if (levelOfBetrayal < 3){
			levelOfBetrayal++;
			this.ajouterArgent(commercant.getArgent());
			commercant.seFaireExtorquer();
			this.parler("J’ai extorque ce Commercant hum ");
		} else
			this.parler("Suis pas digne d'etre samourai, mon niveau de traitrise est egal a "+levelOfBetrayal);
			
		
	}
	
	/**
	 * to greet
	 */
        @Override
	public void direBonjour(){
		super.direBonjour();
		this.parler("mon niveau de traitrise est egal a "+levelOfBetrayal);
	}
	
	/**
	 * to do the good guy
	 * @param humain
	 * @param argent
	 */
	public void faireLeGentil(Humain humain, int argent){
		humain.ajouterArgent(argent);
		this.perdreArgent(argent);
		if ((levelOfBetrayal -= argent/10) < 0)
			levelOfBetrayal =0;
		this.parler("Tiens "+ humain.getNom() + ", je t'offre " +argent+" sous, je le fais ami-ami !!!");
		humain.parler("Merci " + this.getNom() + " vous etes genereux");
			
	}

	

}
