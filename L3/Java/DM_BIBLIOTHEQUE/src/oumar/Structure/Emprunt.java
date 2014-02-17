/**
 * 
 */
package oumar.Structure;

import java.io.Serializable;

/**
 * <b>Description:</b><i>cette classe est utilise pour ajouter un livre emprunter a la bibliotheque
 * elle contient un string le nom du livre et un entier le code du livre</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class Emprunt implements Serializable{
	private String name;
	private int key;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * @param name
	 * @param key
	 */
	public Emprunt(int key,String name) {
		this.name = name;
		this.key = key;
	}

	/**
	 * @return String
	 */
	public String toString() {
		return name;
	}

}
