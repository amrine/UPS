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
	
	public Produit(){
		nom = "";
		prix = 0.0;
	}
	public Produit(String nom, double prix) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.prix = prix;
	}
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}
	public double getPrix() {
		// TODO Auto-generated method stub
		return prix;
	}
	public void setPrix(double prix) {
		// TODO Auto-generated method stub
		this.prix = prix;
	}
	public void setNom(String string) {
		// TODO Auto-generated method stub
		this.nom = string;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if(obj.getClass() != getClass())
			return false;
		
		final Produit produit = (Produit) obj;
		if(!produit.nom.equals(nom))
			return false;
		
		return produit.prix == prix;
	}

}
