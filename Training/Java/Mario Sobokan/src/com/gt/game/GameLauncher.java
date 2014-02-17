package com.gt.game;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.gt.utilities.Score;
import com.gt.utilities.ScoreSerializer;
import com.gt.wiew.Window;

@SuppressWarnings("serial")
public class GameLauncher extends JFrame implements WindowListener{
	Score save_score = new Score();
	ScoreSerializer scoreSerializer = new ScoreSerializer();
	GameBoard game = new GameBoard();
	
    public GameLauncher() {
        add(game);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setTitle("Fenetre de jeu Mario Sobokan V.1.0.2");
        setIconImage(Toolkit.getDefaultToolkit().getImage("images/Paper Mario.png"));
        setResizable(false);
        setVisible(true);
        addWindowListener(this);
    }

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		save_score.setNom(game.getGame().getName());
		save_score.setPoint(game.getGame().getScore());
		if(this.scoreSerializer.isAccpeted(this.save_score))
			scoreSerializer.serialize();
		new Window();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}

}