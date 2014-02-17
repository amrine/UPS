/**
 * 
 */
package jeu;


/**
 * @author 21007631
 *
 */
public class Test   {
	static void Arbitrer(Joueur j1, Joueur j2, JeuSimple jeu){
		
		String joueur1,joueur2;
		joueur1 = jeu.jouerUnTour();
		joueur2 = jeu.jouerUnTour();
		
		switch(jeu.arbitrer(joueur1, joueur2)){
		
		case -1 :j1.gagner(10);j2.perdre(0); break;
		
		case  1 :j2.gagner(10);j1.perdre(0); break;
		
		case  0 : System.out.println("Personne n'a gagné au jeu");
		default: break;
		}
	}
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			Joueur j1 = new TestJoueur();
			Joueur j2 = new TestJoueur();
			JeuSimple jeu = new jeuShifumi();
			Arbitrer(j1,j2,jeu);
			
		}
}
	


