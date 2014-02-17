/**
 * 
 */
package code;

/**
 * @author aob
 *
 */
public class Produit {
	private String nom;
	private double prix;
	/**
	 * @param nom
	 * @param prix
	 */
	public Produit(String nom, double prix) {
		this.nom = nom;
		this.prix = prix;
	}
	/**
	 * 
	 */
	public Produit(){
		nom = "";
		prix = 0.0;
	}
	/**
	 * 
	 */
	public boolean equals(Object object){
		if(object == null)
			return false;
		if(object.getClass() != getClass())
			return false;
		
		final Produit produit = (Produit) object;
		if(!produit.nom.equals(nom))
			return false;
		
		return produit.prix == prix;
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
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
}
