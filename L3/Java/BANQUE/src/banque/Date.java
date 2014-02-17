/**
 * 
 */
package banque;

/**
 * @author oumar
 *
 */
public class Date {
	private int jour, mois, an, heure, minute, seconde;

	/**
	 * @param jour
	 * @param mois
	 * @param an
	 */
	public Date(int jour, int mois, int an) {
		this.jour = jour;
		this.mois = mois;
		this.an = an;
		this.heure = 0;
		this.minute = 0;
		this.seconde = 0;
	}

	/**
	 * @return the jour
	 */
	public int getJour() {
		return jour;
	}

	/**
	 * @return the mois
	 */
	public int getMois() {
		return mois;
	}

	/**
	 * @return the an
	 */
	public int getAn() {
		return an;
	}

	/**
	 * @return the heure
	 */
	public int getHeure() {
		return heure;
	}

	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * @return the seconde
	 */
	public int getSeconde() {
		return seconde;
	}
	
	/**
	 * @return the date
	 */
        @Override
	public String toString(){
		return "[" + heure + ":" + minute + ":" + seconde +":" + " " + jour + "/" + mois +"/" + an +"]";
	}
	
}
