/**
 * 
 */
package com.gt.observer;

/**
 * @author thierno
 *Interface implémentée par toutes les classes souhaitant avoir des observateurs à leur écoute.
 */
public interface Observable
{
     /**
      * 
      * @param o
      * Méthode permettant d'ajouter (abonner) un observateur.
      */
	 public void ajouterObservateur(Observateur o);
     /**
      * 
      * @param o
      * Méthode permettant de supprimer (résilier) un observateur.
      */
     public void supprimerObservateur(Observateur o);
     /**
      * Méthode qui permet d'avertir tous les observateurs lors d'un changement d'état.
      */
     public void notifierObservateurs();
}
