/**
 * 
 */
package com.gt.observer;

/**
 * @author thierno
 *Interface Observateur implement� par tous les observateurs 
 */

public interface Observateur {
	
	/**
	 * 
	 * @param o
	 * M�thode appel�e automatiquement lorsque l'�tat (carte du jeu, niveau,score,nom du joueur) du Jeu change.
	 */
    public void actualiser(Observable o);

	public void update(String hour);

}
