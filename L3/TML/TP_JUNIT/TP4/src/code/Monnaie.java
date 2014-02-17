/**
 * 
 */
package code;

/**
 * @author aob
 *
 */
public class Monnaie {
	private double montant;
	private String devise;
	
	public Monnaie(String devise, double montant){
		this.devise = devise;
		this.montant = montant;
	}
        public Monnaie(){
            montant = 0.0;
            devise = "";
        }
	
	public double getMontant(){
		return montant;
	}
	public String getDevise(){
		return devise;
	}
	
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(obj.getClass() != getClass())
			return false;
		
		final Monnaie monnaie = (Monnaie) obj;
		
		return devise.equals(monnaie.devise)
				&& montant == monnaie.montant;
		
	}
	
	public void ajouter(Monnaie monnaie) throws IllegalStateException{
            if(monnaie == null)
                throw new IllegalStateException("Monnaie Invalide");
		if(devise.equals(monnaie.devise) && devise.length() == 3){
			montant += monnaie.montant;
		}else
			throw new IllegalStateException("Les devises sont differentes");
	}
	
	public void multiplier(int nombre){
		montant *= nombre;
	}
}
