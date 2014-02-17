/**
 * 
 */
package oumar.Utilitaires;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <b>Description:</b><i>Cette classe sert de base a d'autres classes derivees pour afficher des composants dans une JDialog
 * pour demander ou visualiser des informations</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta 
 */
public abstract class Ajout extends JDialog {
	/**
	 * 
	 * @param parent
	 * @param title
	 * @param modal
	 * Constructeur parent
	 */
	public Ajout(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
	}
	/**
	 * cette methode est utiliser pour la creation des composants qui seront contenu dans la JDialog
	 */
	public abstract void initComponent();
	/**
	 * 
	 * @return Boolean 
	 * fonction utilise pour les tests des post saisies sur le composant creee
	 */
	public abstract boolean testInformation();
	/**
	 * 
	 * @return Object 
	 * cette methode retourne l'objet creee a partir de la JDialog
	 */
	public abstract Object showJDialog();
	
	/**
	 * 
	 * @param message
	 * @param titre
	 * affiche le message sur une JOptionPane
	 */
	public void warningMessage(String message,String titre){
		JOptionPane.showMessageDialog(null, message +
				"\n	La Bibliotheque", titre, JOptionPane.WARNING_MESSAGE);
	}
}
