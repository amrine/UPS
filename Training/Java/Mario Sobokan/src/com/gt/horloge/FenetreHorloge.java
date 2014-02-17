package com.gt.horloge;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.gt.observer.Observable;
import com.gt.observer.Observateur;

@SuppressWarnings("serial")
public class FenetreHorloge extends JFrame {

	private JLabel label = new JLabel();
	private Horloge horloge;
	
	public FenetreHorloge(){
		/* On initialise notre JFrame  */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setSize(200, 80);
		
		/* On initialise l'horloge  */
		this.horloge = new Horloge();
		//On place un écouteur sur notre horloge
		this.horloge.ajouterObservateur(new Observateur(){
			public void update(String hour) {
				label.setText(hour);
			}

			@Override
			public void actualiser(Observable o) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/* On initialise notre JLabel  */
		Font police = new Font("DS-digital", Font.TYPE1_FONT, 30);
		this.label.setFont(police);
		this.label.setHorizontalAlignment(JLabel.CENTER);
		/* On ajoute le JLabel à notre JFrame */
		this.getContentPane().add(this.label, BorderLayout.CENTER);		
	}


	/* Méthode main pour lancer le programme 
	public static void main(String[] args){
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
	}
	*/
}
