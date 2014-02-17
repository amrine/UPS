/**
 * 
 */
package asteroide;

import tp3.Sujet;
import tp3.console.*;


/**
 * @author 21007631
 *
 */
public class Prince implements Sujet{

	private String nom;
	private int argent;
	
	/**
	 * @param nom
	 * @param argent
	 */
	public Prince(String nom, int argent) {
		super();
		this.nom = nom;
		this.argent = argent;
	}

	/**
	 * @return the name
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the money
	 */
	public int getArgent() {
		return argent;
	}

	/**
	 * method run
	 */
	public void run() {
		//creation de la console de test de la classe prince represente par this
		ConsoleJavaBoy console = new ConsoleJavaBoy(this);
		console.parler("My name is " + nom + "and i am the prince");
		console.seDirigerVers(0);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Prince prince = new Prince("Alpha",20000);
		prince.run();
		
	}

	

}
