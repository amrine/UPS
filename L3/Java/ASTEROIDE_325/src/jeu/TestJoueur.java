/**
 * 
 */
package jeu;

/**
 * @author 21007631
 *
 */
public class TestJoueur implements Joueur{

	
	public void gagner(int argent) {
		System.out.println("j'ai gagné "+argent);
		
	}


	public void perdre(int argent) {
		System.out.println("j'ai perdu "+argent);
	}

}
