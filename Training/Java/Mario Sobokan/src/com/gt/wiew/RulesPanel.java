/**
 * 
 */
package com.gt.wiew;

/**
 * @author thierno
 *
 */
/**
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @author thierno
 *
 */
public class RulesPanel extends Container {

	public RulesPanel(Dimension dim) {
		super(dim);
		initPanel();
	}

	
	protected void initPanel() {
		 JLabel titre = new JLabel("R�gles du jeu Mario Sobokan\n"); //title of the label
		 titre.setFont(comics40); //setting the font of the title
		 titre.setHorizontalAlignment(JLabel.CENTER); //alignement the title to the center
		 panel.add(titre,BorderLayout.NORTH); //placing title to the north of the panel
		 panel.add(new JLabel(new ImageIcon("images/mario.jpg")), BorderLayout.CENTER); //placing the picture to the center of the panel
		 //the greeting
		 JTextArea texte = new JTextArea("Mario Sobokan\n" +
					"\nGardien d'entrep�t, vous devez ranger des caisses sur des cases cibles." +
					"\nVous pouvez vous d�placer dans les quatre directions, et pousser (mais pas tirer) une seule caisse � la fois."+
					"\nUne fois toutes les caisses rang�es, le niveau est r�ussi et vous passez au niveau suivant." +
					"\nL'id�al est de r�ussir avec le moins de coups possibles (d�placements et pouss�es)."
					 );
		 texte.setFont(arial);
		 texte.setEditable(false);//we can't edite the text
		 texte.setBackground(Color.white); //color of the background
		 panel.add(texte, BorderLayout.SOUTH);//place text to the south
	}

}

