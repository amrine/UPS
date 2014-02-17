/**
 * 
 */
package oumar.Serializer;

/**
 * <b>Description:</b><i> Cette classe permet de sauvegarder la liste contenant les livres dans un ficher</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import oumar.Structure.Livre;

/**
 * @author D.Alpha Oumar Binta
 *
 */
public class LivreSerializer {
	private List<Livre> lesLivres= new ArrayList<Livre>();
	
	/**
	 * @return the lesLivres
	 */
	public List<Livre> getLesLivres() {
		return lesLivres;
	}
	
	/**
	 * Constructeur (charge la liste des inscrits dans une ArrayList a partir d'un fichier
	 * 
	 */
	public LivreSerializer(){
		try{
			File file = new File("donnees/livre.scr");
			//on verifie s'il existe des donnees
			if(file.length()>0){
				ObjectInputStream ois = new ObjectInputStream(
						new BufferedInputStream(
							new FileInputStream(
									file)));
				lesLivres=(List<Livre>) ois.readObject();
				ois.close();
			}
			else{
				lesLivres= new ArrayList<Livre>();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier livre !\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier livre !\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier livre !\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * cette methode enregistre les donnees des inscrits dans un fichier
	 */
	public void serialize(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
										new BufferedOutputStream(
												new FileOutputStream(
														new File("donnees/livre.scr"))));
			oos.writeObject(lesLivres);
			oos.close();
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier livre !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier livre !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}

}

