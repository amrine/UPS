/**
 * 
 */
package jeu;

/**
 * @author 21007631
 *
 */
abstract class  JeuSimple {
	public String nom;

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * 
	 * @return a String
	 */
	abstract String  jouerUnTour();
	
	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return an int
	 */
	abstract int arbitrer(String c1, String c2);
}
