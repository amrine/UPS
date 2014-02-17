/**
 * 
 */
package com.gt.observer;

/**
 * @author thierno
 *Interface impl�ment�e par toutes les classes souhaitant avoir des observateurs � leur �coute.
 */
public interface Observable
{
     /**
      * 
      * @param o
      * M�thode permettant d'ajouter (abonner) un observateur.
      */
	 public void ajouterObservateur(Observateur o);
     /**
      * 
      * @param o
      * M�thode permettant de supprimer (r�silier) un observateur.
      */
     public void supprimerObservateur(Observateur o);
     /**
      * M�thode qui permet d'avertir tous les observateurs lors d'un changement d'�tat.
      */
     public void notifierObservateurs();
}
