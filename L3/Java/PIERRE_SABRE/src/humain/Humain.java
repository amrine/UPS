/**
 * 
 */
package humain;

/**
 * @author diallo
 *
 */
public class Humain {
	private int argent;
	private String boisson;
	private String nom;
	
	/**
	 * Humain Constructor Object
	 * @param argent
	 * @param boisson
	 * @param nom
	 */
	public Humain(int argent, String boisson, String nom) {
		this.argent = argent;
		this.boisson = boisson;
		this.nom = nom;
	}

	/**
	 * @return the argent
	 */
	public int getArgent() {
		return argent;
	}

	/**
	 * @return the boisson
	 */
	public String getBoisson() {
		return boisson;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * to Add money
	 * @param argent
	 */
	public void ajouterArgent(int argent){
		this.argent += argent;
	}
	
	/**
	 * to lose money
	 * @param argent
	 */
	public void perdreArgent(int argent){
		this.argent -= argent;
	}
	
	/**
	 * to talk
	 * @param texte
	 */
	public void parler(String texte){
		System.out.println("(" + nom + ") - " + texte);
		
	}
	
	/**
	 * to drink
	 */
	public void boire(){
		this.parler("Ahhh, un bon verre de " + boisson + " ! GLOUPS !");
	}
	
	/**
	 * to greet
	 */
	public void direBonjour(){
		this.parler("Bonjour ! Je m’appelle " + nom + ", j’aime boire du " + boisson + " et j'ai " + argent +" sous en poche");
	}
	
	

}
