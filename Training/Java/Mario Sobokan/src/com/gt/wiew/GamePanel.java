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

import java.awt.Dimension;

import com.gt.game.GameBoard;

/**
 * @author thierno
 *
 */
public class GamePanel extends Container {

	public GamePanel(Dimension dim) {
		super(dim);
		initPanel();
	}

	
        @Override
	protected void initPanel() {
		 panel.add(new GameBoard()); //placing the game to the center of the panel
	}

}

