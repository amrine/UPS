/**
 * 
 */
package com.gt.wiew;

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
public class HomePanel extends Container {

	public HomePanel(Dimension dim) {
		super(dim);
		initPanel();
	}

	
	protected void initPanel() {
		 JLabel titre = new JLabel("Mario Sobokan\n"); //title of the label
		 titre.setFont(comics40); //setting the font of the title
		 titre.setHorizontalAlignment(JLabel.CENTER); //alignement the title to the center
		 panel.add(titre,BorderLayout.NORTH); //placing title to the north of the panel
		 panel.add(new JLabel(new ImageIcon("images/mario.jpg")), BorderLayout.CENTER); //placing the picture to the center of the panel
		 //the greeting
		 JTextArea texte = new JTextArea("Bienvenue dans le jeu Mario Sobokan\n" +
					"\nSokoban est un jeu vidéo de puzzle inventé au Japon. Ce nom" +
					"\nprononcé sôkoban transcrit avec la méthode Kunrei, désigne un garde d'entrepôt.\n" +
					"\n\nDéveloppeur : Alpha Oumar Binta Diallo\nLicense : Freeware\nCopyright : 2011 (c) Alpha Oumar Binta Diallo\n" +
  		          "Version : 1.0.2\n" +	  
  		          "Contact : aob.diallo@gmail.com" );
		 texte.setFont(arial);
		 texte.setEditable(false);//we can't edite the text
		 texte.setBackground(Color.white); //color of the background
		 panel.add(texte, BorderLayout.SOUTH);//place text to the south
	}

}
