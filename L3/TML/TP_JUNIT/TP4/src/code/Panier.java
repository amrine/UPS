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
	private Map<Produit, Integer> panier;
	
	public Panier(){
		montantTotal = 0.0;
		panier = new HashMap<>();
	}
	
	public boolean panierVide(){
		return panier.isEmpty();
	}
	
	public double getMontantTotal(){
		return montantTotal;
	}
	
	public void ajouterProduit(Produit produit, int quantite){
		if(produit == null || quantite < 0)
			return;
                
		double prixArticleCourant;
		montantTotal = 0.0;
                
		if(panier.containsKey(produit)){
			int total = panier.get(produit) + quantite;
			panier.put(produit, total);
		}else{	
                    int qte = (quantite == 0) ? 1 : quantite;
                    panier.put(produit, qte);
		}
		
		//calcul du montant total du panier
		for(Entry<Produit, Integer> courant : panier.entrySet()){
			prixArticleCourant = courant.getKey().getPrix() * courant.getValue();
			montantTotal += prixArticleCourant;
		}
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
		if (panier == null) {
			if (other.panier != null)
				return false;
		} else if (!panier.equals(other.panier))
			return false;
		return true;
	}
	
}
