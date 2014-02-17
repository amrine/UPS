/**
 * 
 */
package com.gt.wiew;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gt.game.Game.Direction;
import com.gt.game.Game.Element;
import com.gt.utilities.Map;

/**
 * @author thierno
 *
 */
@SuppressWarnings("serial")
public class GameGrid extends JPanel {
	
	JPanel game_grid;
	
	public GameGrid(Map map) {
		game_grid = new JPanel(new GridLayout(map.getWidth(),map.getHeight()));
		this.add(game_grid);
		this.setBackground(Color.darkGray);
	}
	public GameGrid(Map map,int dir) {
		game_grid = new JPanel(new GridLayout(map.getWidth(),map.getHeight()));
		this.add(game_grid);
		this.setBackground(Color.darkGray);
	}
	public void printGameGrid(Map map){
		game_grid.setBackground(Color.white);
		 for (int i = 0 ; i < map.getWidth() ; i++)
		    {
		        for (int j = 0 ; j < map.getHeight() ; j++)
		        {					
					if (map.getMapElement(i,j) == Element.WALL.ordinal()) { 
						game_grid.add(new JLabel(new ImageIcon("images/mur.jpg")));
					}
					if (map.getMapElement(i,j) == Element.BOX.ordinal() ){
						game_grid.add(new JLabel(new ImageIcon("images/caisse.jpg")));
					}
					if (map.getMapElement(i,j) == Element.BOX_OK.ordinal()){
						game_grid.add(new JLabel(new ImageIcon("images/caisse_ok.jpg")));
					}
					if (map.getMapElement(i,j) == Element.GOAL.ordinal()){
						game_grid.add(new JLabel(new ImageIcon("images/objectif.png")));
					}
					if (map.getMapElement(i,j) == Element.EMPTY.ordinal()){
						game_grid.add(new JLabel(new ImageIcon("images/vide.jpg")));
					}
					if (map.getMapElement(i,j) == Element.MARIO.ordinal()){
						game_grid.add(new JLabel(new ImageIcon("images/mario_bas.gif")));
					}
		        }
		    }
	}
	
	public void printGameGrid(Map map, int direction){
		game_grid.setBackground(Color.white);
		 for (int i = 0 ; i < map.getWidth() ; i++)
		    {
		        for (int j = 0 ; j < map.getHeight() ; j++)
		        {					
					if (map.getMapElement(i,j) == Element.WALL.ordinal()) { 
						game_grid.add(new JLabel(new ImageIcon("images/mur.jpg")));
					}
					if (map.getMapElement(i,j) == Element.BOX.ordinal() ){
						game_grid.add(new JLabel(new ImageIcon("images/caisse.jpg")));
					}
					if (map.getMapElement(i,j) == Element.BOX_OK.ordinal()){
						game_grid.add(new JLabel(new ImageIcon("images/caisse_ok.jpg")));
					}
					if (map.getMapElement(i,j) == Element.GOAL.ordinal()){
						game_grid.add(new JLabel(new ImageIcon("images/objectif.png")));
					}
					if (map.getMapElement(i,j) == Element.EMPTY.ordinal()){
						game_grid.add(new JLabel(new ImageIcon("images/vide.jpg")));
					}
					
					if (map.getMapElement(i,j) == Element.MARIO.ordinal()){
					if (direction == Direction.TOP.ordinal())
						game_grid.add(new JLabel(new ImageIcon("images/mario_haut.gif")));
					else if (direction == Direction.LEFT.ordinal())
						game_grid.add(new JLabel(new ImageIcon("images/mario_gauche.gif")));
						else if (direction == Direction.RIGHT.ordinal())
							game_grid.add(new JLabel(new ImageIcon("images/mario_droite.gif")));
							else game_grid.add(new JLabel(new ImageIcon("images/mario_bas.gif")));
					}
		        }
		    }
	}

}
