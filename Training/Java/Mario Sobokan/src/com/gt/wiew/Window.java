/**
 * 
 */
package com.gt.wiew;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileSystemView;

import com.gt.game.GameLauncher;
import com.gt.utilities.ScoreSerializer;



/**
 * @author thierno
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener{
	private JMenuBar menu = new JMenuBar();//creating menu barr
	
	private JMenu file = new JMenu("Fichier"); //tab file
	//items of tab file
	private JMenuItem new_game = new JMenuItem("Nouveau jeu");
	private JMenuItem score= new JMenuItem("Score");
	private JMenuItem quit= new JMenuItem("Quitter");
	
	private JMenu about= new JMenu("A Propos");//tab about
	//items of tab about
	private JMenuItem rules= new JMenuItem("Règles du Jeu");
	private JMenuItem who= new JMenuItem(" ? ");
	
	private Dimension size;
	private JPanel container = new JPanel();
	
	ScoreSerializer liste_score = new ScoreSerializer();
	
	//folder for saving game , score
	File folder=new File (FileSystemView.getFileSystemView().getDefaultDirectory()+"/Sobokan/"); 
	
	public Window(){
		this.setTitle("Mario Sobokan"); //title of the window
		this.setSize(900, 600); //size of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //quit on pressing exit
		this.setLocationRelativeTo(null); //place window at the center
		this.setResizable(false); //unresizable window
		this.size = new Dimension(this.getWidth(), this.getHeight()); //set the dimension
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/Paper Mario.png"));
		
		//creating folder if necessary
		if(folder.exists()==false) folder.mkdirs();
		
		//creating tab file and customizing items
		file.setMnemonic('F'); //underline F
		//adding items to the tab file
		file.add(new_game);
		file.add(score);
		file.addSeparator();
		file.add(quit);
		
		//creating tab about and customizing items
  		about.setMnemonic('A'); //underline A
  		//adding items to the tab about
  		about.add(rules);
  	    about.add(who);	
  	    
		//shortcut
		new_game.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK)); //shortcut for a new game CTRL N
		score.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK)); //shortcut for show score CTRL R
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK)); //shortcut for exit CTRL W
		
	
	    //adding tabs file and about to the menu  
	  	this.menu.add(file); 
	  	this.menu.add(about);
	  	//setting the menu bar
	  	this.setJMenuBar(menu);
	  
	  	//ajout des ecouteurs 
	  	new_game.addActionListener(this); 
	  	score.addActionListener(this);
	  	quit.addActionListener(this);
	  	rules.addActionListener(this);
	  	who.addActionListener(this);
	  	
	  	container.setPreferredSize(this.size );
	  	container.setBackground(Color.white);
	  	container.add(new HomePanel(size).getPanel());
	  	this.setContentPane(container);	
	  	setVisible(true);
	}

	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == new_game) {
			new GameLauncher();
			dispose();
			
		}
		else if(arg0.getSource() == score){
			container.removeAll();
			container.add(new ScorePanel(size, liste_score.getListeScore()).getPanel());
			container.revalidate();
		}
		else if(arg0.getSource() ==quit){
			System.exit(0);
		}
		else if(arg0.getSource() == rules){
			container.removeAll(); //deleting all component
			container.add(new RulesPanel(size).getPanel());
	    	container.revalidate(); //update of the window			
		}
		else if(arg0.getSource() == who){
			JOptionPane.showMessageDialog(null,
    		          "Développeur : Alpha Oumar Binta Diallo\nLicense : Freeware\nCopyright : 2011 (c) Alpha Oumar Binta Diallo\n" +
    		          "Version : 1.0.2\n" +	  
    		          "Contact : aob.diallo@gmail.com",
    		          "Information", JOptionPane.NO_OPTION);
			container.removeAll(); //deleting all component
			container.add(new HomePanel(size).getPanel());
	    	container.revalidate(); //update of the window
		}
		
	}


}
