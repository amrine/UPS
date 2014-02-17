/**
 * 
 */
package oumar.Structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import oumar.Enumeration.Sexe;

/**
 * <b>Description:</b><i>cette classe represente la structure d'une personne inscrite a la bibliotheque</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class Inscrit implements Serializable {

	private static final long serialVersionUID = -3415209097881553727L;
	private String image;
    private String nom;
    private String prenom;
    private String[] adresse;
    private String tel;
    private Sexe sex;
    private String age;
    private List<Emprunt> emprunt=new ArrayList<Emprunt>();
    

	/**
	 * 
	 */
	public Inscrit() {
		this.image = "";
		this.nom = "";
		this.prenom = "";
		this.adresse = null;
		this.tel = "";
		this.sex = Sexe.MASCULIN;
		this.age="";
	}
	/**
	 * @param image
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param tel
	 * @param sex
	 */
	public Inscrit(String image, String nom, String prenom, String[] adresse,
			String tel, Sexe sex,String age) {
		this.image = image;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.sex = sex;
		this.age=age;
	}
	
	
	/**
	 * @return the emprunt
	 */
	public List<Emprunt> getEmprunt() {
		return emprunt;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "image=" + image + ", nom=" + nom + ", prenom="
				+ prenom + 
				"\nadresse=" + adresseToString() + "\ntel="
				+ tel + ", sex=" + sex + ", age=" + age;
	}
	
	public String adresseToString(){
		String adr="";
		for(String str : adresse){
			adr+=" "+str;
		}
		return adr;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}



	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}



	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}



	/**
	 * @return the adresse
	 */
	public String[] getAdresse() {
		return adresse;
	}
	


	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	

	/**
	 * @return the sex
	 */
	public Sexe getSex() {
		return sex;
	}


	/**
	 * 
	 * @param i
	 */
	public void modifierInscrit(Inscrit i) {
		image = i.getImage();
		nom = i.getNom();
		prenom = i.getPrenom();
		adresse = i.getAdresse();
		tel = i.getTel();
		sex = i.getSex();
		age=i.getAge();
	}
	/**
	 * 
	 * @param key
	 * @param titre
	 */
	public void emprunter(int key, String titre) {
		Emprunt emp=new Emprunt(key,titre);
		emprunt.add(emp);
	}
}
