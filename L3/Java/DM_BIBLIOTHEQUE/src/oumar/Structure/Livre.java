/**
 * 
 */
package oumar.Structure;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import oumar.Enumeration.Categorie;
import oumar.Enumeration.Theme;

/**
 * <b>Description:</b><i>cette classe represente la structure d'un livre de la bibliotheque</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class Livre implements Serializable{

	private static final long serialVersionUID = -4113434291601966473L;
	private String image;
    private String titre;
    private String auteur;
    private String resume;
    private Categorie categorie;
    private List<Theme> theme;
    private String nbrePage;
    private Boolean disponible=true;
    
    
    public Livre() {
		this.image = "";
		this.titre = "";
		this.auteur = "";
		this.resume = "";
		this.categorie = null;
		this.theme=null;
		this.nbrePage ="";
	}
    
    
	/**
	 * @param image
	 * @param titre
	 * @param auteur
	 * @param resume
	 * @param i
	 * @param listTheme
	 * @param nbrePage
	 */
	public Livre(String image, String titre, String auteur, String resume,
			Categorie i, List<Theme> listTheme, String nbrePage) {
		this.image = image;
		this.titre = titre;
		this.auteur = auteur;
		this.resume = resume;
		this.categorie = i;
		this.theme = listTheme;
		this.nbrePage = nbrePage;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "image=" + image + ", titre=" + titre + "," +
				"\nauteur="+auteur + ", " +", categorie=" + categorie
				+ ",\ntheme=" + themeToString() + ", nbrePage="+ nbrePage + ""+
				"\nresume=" + resume ;
	}
	
	/**
	 * 
	 * @return the String of theme
	 */
	public String themeToString(){
		String str="";
		ListIterator<Theme> i= theme.listIterator();
		while(i.hasNext())
			str+=i.next().toString()+", ";
		return str;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @return the theme
	 */
	public List<Theme> getTheme() {
		return theme;
	}

	/**
	 * @return the nbrePage
	 */
	public String getNbrePage() {
		return nbrePage;
	}
	
	
	/**
	 * 
	 * @param l
	 * modification des attributs d'un livre
	 */
	public void modifierLivre(Livre l) {
		image = l.getImage();
		titre = l.getTitre();
		auteur = l.getAuteur();
		resume = l.getResume();
		categorie = l.getCategorie();
		theme = l.getTheme();
		nbrePage = l.getNbrePage();
	}


	/**
	 * @return the disponible
	 */
	public Boolean getDisponible() {
		return disponible;
	}


	/**
	 * @param disponible the disponible to set
	 */
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	

}
