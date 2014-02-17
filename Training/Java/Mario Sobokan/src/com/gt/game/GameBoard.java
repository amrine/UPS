/**
 * 
 */
package com.gt.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.gt.horloge.Horloge;
import com.gt.observer.Observable;
import com.gt.observer.Observateur;
import com.gt.utilities.Map;
import com.gt.wiew.GameGrid;

/**
 * @author thierno
 *
 */

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements ActionListener,Observateur {


    private Game game;
    private Horloge horloge;
    Map map;
    
	private JLabel name= new JLabel(), score= new JLabel(),
			level= new JLabel(), time= new JLabel(),
			title= new JLabel();
	
	String the_name = "Nom du Joueur : ",
			the_score= "Votre score : ",
			the_level= "Niveau : ",
			the_time= "Nombre de Déplacements : ";

	
    public GameBoard() {
		
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.white);
        setDoubleBuffered(true);
        
        //initialisation
        game = new Game();
		horloge = new Horloge();
		
		//On place un écouteur sur notre horloge
		this.horloge.ajouterObservateur(new Observateur(){
			public void update(String hour) {
				title.setText("Heure Actuel : " +hour);
			}

		
			public void actualiser(Observable o) {}
		});
        
      //labels
		if(game.getName() == null)
			name.setText(the_name +" Inconnue");
		else
    	name.setText(the_name +" "+ (game.getName().equals("")?"Inconnue":game.getName()));
    	score.setText(the_score + " 0 pt");
    	level.setText(the_level + " 1");
    	time.setText(the_time + " 0 coup");
        map=game.getMap();
        PrintGamePanel(map);
        game.ajouterObservateur(this);
    }





    public void actionPerformed(ActionEvent e) { 
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            game.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            game.keyPressed(e);
            
        }
    }

	protected void PrintGamePanel( Map map) {
		GameGrid game_grid = new GameGrid(map);
		//containers
		JPanel	leftContent =new JPanel(),
				rightContent=new JPanel(),
				headContent=new JPanel();
		
		//dimensions of container
		Dimension left	 = new Dimension(map.getBlock_widht(),map.getBlock_height());
		Dimension right	 = new Dimension(450,50);
		Dimension head	 = new Dimension(450,50);
	
		//size of containers
		leftContent.setPreferredSize(left);
		rightContent.setPreferredSize(right);
		headContent.setPreferredSize(head);
		
		//background of containers
		
		leftContent.setBackground(Color.white);
		rightContent.setBackground(Color.white);
		headContent.setBackground(Color.white);
		
		//alignment of components
		name.setVerticalAlignment(JLabel.CENTER);
		
		score.setVerticalAlignment(JLabel.CENTER);
		score.setForeground(Color.blue);
		
		level.setVerticalAlignment(JLabel.CENTER);
		time.setVerticalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		
		//place of components
		rightContent.setLayout(new BorderLayout());
		rightContent.add(name,BorderLayout.NORTH);
		rightContent.add(score,BorderLayout.CENTER);
		rightContent.add(level,BorderLayout.SOUTH);
		
		headContent.setLayout(new BorderLayout());
		headContent.add(title,BorderLayout.NORTH);
		headContent.add(time,BorderLayout.SOUTH);
		
		
		game_grid.printGameGrid(map,game.getDirection());
		JTextArea commande = new JTextArea(
				"\nFermer : revenir au menu principal"+
				"\nR : recommencer le niveau" +
				"\nS : sauvegarder la partie" +
				"\nC : charger la derniere sauvegarde" +
				"\n↑ : deplacer mario vers le haut" +
				"\n↓ : deplacer mario vers le bas" +
				"\n← : deplacer mario vers la gauche" +
				"\n→ : deplacer mario vers la droite" +
				"\n");
		commande.setEditable(false);
		
		JPanel control = new JPanel();
		control.setBackground(Color.white);
		JLabel title_control = new JLabel("COMMANDE DE JEU");
		title_control.setFont( new Font("Comics Sans MS", Font.BOLD, 30));
		control.setLayout(new BorderLayout());
		control.add(title_control,BorderLayout.NORTH);
		control.add(commande,BorderLayout.SOUTH);
		leftContent.add(control);
		leftContent.add(game_grid);
		
		
		
		JPanel haut = new JPanel();
		haut.setPreferredSize(head);
		haut.setLayout(new BorderLayout());
		haut.add(headContent,BorderLayout.EAST);
		haut.add(rightContent,BorderLayout.WEST);
		JPanel bas = new JPanel();
		JLabel title_down =new JLabel("Mario Sobokan V.1.0.2");
		title_down.setFont( new Font("Comics Sans MS", Font.BOLD, 40));
		bas.add(title_down);
		bas.setBackground(Color.white);
		
		
		
		this.setLayout(new BorderLayout());
		this.add(haut,BorderLayout.NORTH);
		this.add(leftContent,BorderLayout.CENTER);
		this.add(bas,BorderLayout.SOUTH);
		
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}





	public void actualiser(Observable o) {
		if(o instanceof Game)
        {       
			Game g = (Game) o;
			int i=g.getLevel()+1;
			this.score.setText(the_score+g.getScore() +(g.getScore()<1? " point" : " points"));
			this.level.setText(the_level+i);
			this.time.setText(the_time+g.getTime());
			this.removeAll();
			PrintGamePanel(map);
        }
		
	}





	
	public void update(String hour) {}


}
