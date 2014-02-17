/**
 * 
 */
package oumar.Bibliotheque;

import java.util.ArrayList;
import java.util.List;

import oumar.Serializer.InscritSerializer;
import oumar.Serializer.LivreSerializer;
import oumar.Structure.Inscrit;
import oumar.Structure.Livre;

/**
 * <b>Description</b><i>Cette Classe permet de creer la bibiliotheque</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class Bibliotheque {
	protected List<Livre> lesLivres;
	protected List<Inscrit> humain;
	
	protected InscritSerializer fichierInscrit;
	protected LivreSerializer fichierLivre;

	/**
	 * constructeur par defaut de la biblioteque
	 */
	public Bibliotheque() {
		fichierInscrit= new InscritSerializer();
		fichierLivre= new LivreSerializer();
		lesLivres=new ArrayList<Livre>();
		humain=new ArrayList<Inscrit>();
		 creerBibliotheque();
	}
	
	
	
	public void creerBibliotheque(){
		lesLivres=fichierLivre.getLesLivres();
		humain= fichierInscrit.getLesInscrits();
	}



	/**
	 * @return the lesLivres
	 */
	public List<Livre> getLesLivres() {
		return lesLivres;
	}



	/**
	 * @return the humain
	 */
	public List<Inscrit> getHumain() {
		return humain;
	}



	/**
	 * @return the fichierInscrit
	 */
	public InscritSerializer getFichierInscrit() {
		return fichierInscrit;
	}



	/**
	 * @return the fichierLivre
	 */
	public LivreSerializer getFichierLivre() {
		return fichierLivre;
	}
	
	
}
