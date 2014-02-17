/**
 * 
 */
package classe;

import java.util.ArrayList;





/**
 * @author 21007631
 *
 */
public abstract class Type {
	private Couleur couleur;
	private String nom;
	

	/**
	 * @param couleur
	 * @param nom
	 */
	public Type(Couleur couleur, String nom) {
		this.couleur = couleur;
		this.nom = nom;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * @param couleur the couleur to set
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	/**
	 * 
	 * @param position
	 * @param plateau
	 * @return a list of right position where the destination of the piece is possible
	 */
	public abstract ArrayList<Position> destinations(Position position, Plateau plateau);
	
}
