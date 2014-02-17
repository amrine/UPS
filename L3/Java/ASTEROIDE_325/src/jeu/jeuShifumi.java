/**
 * 
 */
package jeu;

import java.util.Random;

/**
 * @author 21007631
 *
 */
public class jeuShifumi extends JeuSimple {
	
	public jeuShifumi(){
		nom="Shifumi";
	}
	
	public jeuShifumi(String nom){
		this.nom = nom;
	}
	
	enum Shifumi{FEUILLE, CISEAUX, PIERRE};

	
	String jouerUnTour() {
		return ""+Shifumi.values()[new Random().nextInt(3)];
	}

	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return the winner (-1 if c1 > c2) (1 if c2 < c1) else 0
	 */
	int arbitrer(String c1, String c2){
		if((Shifumi.valueOf(c1) == Shifumi.PIERRE) && (Shifumi.valueOf(c2) == Shifumi.FEUILLE))
			return 1;
		else if((Shifumi.valueOf(c1) == Shifumi.PIERRE) && (Shifumi.valueOf(c2) == Shifumi.CISEAUX))
			return -1;
		else if((Shifumi.valueOf(c1) == Shifumi.FEUILLE) && (Shifumi.valueOf(c2) == Shifumi.PIERRE))
			return -1;
		else if((Shifumi.valueOf(c1) == Shifumi.FEUILLE) && (Shifumi.valueOf(c2) == Shifumi.CISEAUX))
			return 1;
		else if((Shifumi.valueOf(c1) == Shifumi.CISEAUX) && (Shifumi.valueOf(c2) == Shifumi.PIERRE))
			return -1;
		else if((Shifumi.valueOf(c1) == Shifumi.CISEAUX) && (Shifumi.valueOf(c2) == Shifumi.FEUILLE))
			return 1;
		else
		 return 0;
	}
}

