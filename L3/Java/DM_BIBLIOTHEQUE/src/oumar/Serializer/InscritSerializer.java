/**
 * 
 */
package oumar.Serializer;

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

import oumar.Structure.Inscrit;

/**
 *<b>Description:</b><i>Cette classe permet de sauvegarder la liste contenant des inscrits dans un ficher</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class InscritSerializer {
	private List<Inscrit> lesInscrits= new ArrayList<Inscrit>();
	
	/**
	 * @return the lesInscrits
	 */
	public List<Inscrit> getLesInscrits() {
		return lesInscrits;
	}
	
	/**
	 * Constructeur (charge la liste des inscrits dans une ArrayList a partir d'un fichier
	 * 
	 */
	public InscritSerializer(){
		try{
			File file = new File("donnees/inscrit.scr");
			//on verifie s'il existe des donnees
			if(file.length()>0){
				ObjectInputStream ois = new ObjectInputStream(
						new BufferedInputStream(
							new FileInputStream(
									file)));
				lesInscrits=(List<Inscrit>) ois.readObject();
				ois.close();
			}
			else{
				lesInscrits= new ArrayList<Inscrit>();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier inscrit !\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier inscrit !\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier inscrit !\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
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
														new File("donnees/inscrit.scr"))));
			oos.writeObject(lesInscrits);
			oos.close();
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier inscrit !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier inscrit !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}

}
