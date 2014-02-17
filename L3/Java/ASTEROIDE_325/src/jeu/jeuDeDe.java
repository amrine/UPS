/**
 * 
 */
package jeu;

/**
 * @author 21007631
 *
 */
public class jeuDeDe extends JeuSimple{
	
	public jeuDeDe() {
		this.nom = "DeDe";
	}
	
	public jeuDeDe(String nom) {
		this.nom = nom;
	}

	/**
	 * return a number
	 */
        @Override
	String jouerUnTour(){
		int x = 1+(int)(Math.random()*((6-1)+1));
		return ""+x;
	}
	
	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return the winner (-1 if c1 > c2) (1 if c2 < c1) else 0
	 */
        @Override
	int arbitrer(String c1, String c2){
		int valc1 = Integer.valueOf(c1).intValue();
		int valc2 = Integer.valueOf(c2).intValue();
		
		if (valc1 > valc2) return -1;
		else if (valc1 < valc2) return 1;
		else return 0;
	}

	
	

}
