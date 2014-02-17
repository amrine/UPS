/**
 * 
 */
package oumar.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

/**
 *<b>Description:</b><i>cette classe abstraite sert de base a d'autres classes derivees pour afficher des composants dans un Jpanel</i>
 *<br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public abstract class Conteneur {
	 //conteneur
    protected JPanel panel;
    //font utiliser par l'application
    protected Font comics30 = new Font("Comics Sans MS", Font.BOLD, 30);
    protected Font comics40 = new Font("Comics Sans MS", Font.BOLD, 40);
    protected Font comics20 = new Font("Comics Sans MS", Font.BOLD, 20);
    protected Font arial = new Font("Arial", Font.BOLD, 15);
    protected Font dialog = new Font("Dialog", Font.BOLD + Font.ITALIC, 15);
    
    /**
     * 
     * @param dim
     */
    public Conteneur(Dimension dim){
        panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setPreferredSize(dim);
    }
    /**
     * 
     * @return the panel
     */
    public JPanel getPanel(){
        return panel;
    }
    
    /**
     * initialisateur des composants du conteneur
     */
    protected abstract void initPanel();

}
