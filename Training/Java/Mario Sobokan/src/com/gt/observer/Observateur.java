/**
 * 
 */
package com.gt.observer;

/**
 * @author thierno
 *Interface Observateur implementé par tous les observateurs 
 */

public interface Observateur {
	
	/**
	 * 
	 * @param o
	 * Méthode appelée automatiquement lorsque l'état (carte du jeu, niveau,score,nom du joueur) du Jeu change.
	 */
    public void actualiser(Observable o);

	public void update(String hour);

}
