/**
 * 
 */
package code;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author aob
 *
 */
public class Panier {

	private double montantTotal;
	private Map<Produit, Integer> listeCourse;
	
	public Panier(){
		montantTotal = 0.0;
		listeCourse = new HashMap<>();
	}

	public double getMontantTotal() {
		// TODO Auto-generated method stub
		return montantTotal;
	}

	public boolean ajouterProduit(Produit produit, int quantite) {
		// TODO Auto-generated method stub
		//si le produit est null ou la quantité inferieur à 0, le
		//produit n'est pas ajouté
		if(produit == null || quantite < 0){
			return false;
		}
		//si la quantité est égale à 0, on la met à 1 par défaut
		int qte = (quantite == 0) ? 1 : quantite;
		//si le panier est vide, la produit est ajouté
		if(listeCourse.containsKey(produit)){
			qte += listeCourse.get(produit);
			listeCourse.put(produit, qte);
		}else{
			listeCourse.put(produit, qte);
		}
		montantTotal = calculerMontant();
		return true;
	}
	/**
	 * @return
	 */
	public double calculerMontant() {
		//calcul du montant total du panier
		double prixArticleCourant, total = 0;
		for(Entry<Produit, Integer> courant : listeCourse.entrySet()){
			prixArticleCourant = courant.getKey().getPrix() * courant.getValue();
			total += prixArticleCourant;
		}
		return total;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Panier))
			return false;
		Panier other = (Panier) obj;
		if (montantTotal == 0.0) {
			if (other.montantTotal != 0.0)
				return false;
		} else if (montantTotal != other.montantTotal)
			return false;
		if (listeCourse == null) {
			if (other.listeCourse != null)
				return false;
		} else if (!listeCourse.equals(other.listeCourse))
			return false;
		return true;
	}

	public boolean panierVide() {
		// TODO Auto-generated method stub
		return listeCourse.isEmpty();
	}
}
