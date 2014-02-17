/**
 * 
 */
package com.gt.game;

import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import com.gt.observer.Observable;
import com.gt.observer.Observateur;
import com.gt.utilities.Map;
import com.gt.utilities.Position;
import com.gt.utilities.Score;
import com.gt.utilities.ScoreSerializer;

/**
 * @author thierno
 *
 */
public class Game implements Observable {
	private Map map;
	private int level;
	private int score;
	private int time;
	private String name;
	private Position positionGamer;
	int err; //nombre de reprise de la partie
	Score save_score = new Score();
	ScoreSerializer scoreSerializer = new ScoreSerializer();
	private int direction; //mario direction
	private boolean marioOnGoal;
	
	/**
	 * @return the positionGamer
	 */
	public Position getPositionGamer() {
		return positionGamer;
	}
	

	/**
	 * 
	 * @return the marioOnGoal
	 */
	public boolean getMarioOnGoal(){
		return marioOnGoal;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	private ArrayList<Observateur> tabObservateur;// Tableau d'observateurs.
	public enum Direction{TOP, LOW, LEFT, RIGHT};
    public enum Element{EMPTY, WALL, BOX, GOAL, MARIO, BOX_OK};
	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	
	public int[][] getTabMap() {
		return map.getMap();
	}
	/**
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	
	public int updateScore(int reset){
		
		if(level == 0) return 100;
		else{
			switch(reset){
			case 0 : score+=100; break;
			case 1 : score+=50; break;
			case 2 : score+=35; break;
			case 3 : score+=25; break;
			case 4 : score+=15; break;
			case 5 : score+=10; break;
			case 6 : score+=5; break;
			default : score+=0; break;
			}
		}
		return score;
	}
	/**
	 * @param map
	 * @param level
	 * @param score
	 * @param time
	 * @param tabObservateur
	 */
	public Game() {
		map = new Map();
		map.loadLevel();
		level = 0;
		score = 0;
		time = 0;
		tabObservateur = new ArrayList<Observateur>();
		name = JOptionPane.showInputDialog(null, "Veuillez entrer votre nom pour jouer ! ",
				"Mario Sobokan V.1.0.2 : new game", JOptionPane.QUESTION_MESSAGE);
		positionGamer = new Position();
		direction = Direction.LOW.ordinal();
		marioOnGoal=false;
		err=0;
	}
	
	public Position searchPosition(Position positionGamer){
		// Recherche de la position de Mario au départ
	    for (int i = 0 ; i < map.getWidth() ; i++)
	    {
	        for (int j = 0 ; j < map.getHeight() ; j++)
	        {
	        	
	            if (map.getMapElement(i,j) == Element.MARIO.ordinal()) // Si Mario se trouve à cette position sur la carte
	            {
	                positionGamer.setX(i);
	                positionGamer.setY(j);
	                map.setMapElement(i, j, Element.EMPTY.ordinal());
	            }
	        }
	    }
	    
		return positionGamer;	
	}
	
	public void moveMario(Map map, Position positionGamer, Direction dir){
		
		switch(dir){
		case LEFT : 
			if (positionGamer.getY() - 1 < 0) // Si le joueur dépasse l'écran, on arrête
            break;
			
			// S'il y a un mur, on arrête
			if (map.getMapElement(positionGamer.getX(), positionGamer.getY()-1) == Element.WALL.ordinal()) 
            break;
			
			// Si on veut pousser une caisse, il faut vérifier qu'il n'y a pas de mur derrière (ou une autre caisse, ou la limite du monde)
            if ((map.getMapElement(positionGamer.getX(), positionGamer.getY()-1) == Element.BOX.ordinal() || 
            		map.getMapElement(positionGamer.getX(), positionGamer.getY()-1) == Element.BOX_OK.ordinal()) &&
                (positionGamer.getY() - 2 < 0 || map.getMapElement(positionGamer.getX(), positionGamer.getY()-2) == Element.WALL.ordinal() ||
                		map.getMapElement(positionGamer.getX(), positionGamer.getY()-2) == Element.BOX.ordinal() ||
                				map.getMapElement(positionGamer.getX(), positionGamer.getY()-2) == Element.BOX_OK.ordinal()))
            break;
            
            //Si on arrive là, c'est qu'on peut déplacer le joueur !
            // On vérifie d'abord s'il y a une caisse à déplacer
            moveBox(positionGamer.getX(),positionGamer.getY()-1,positionGamer.getX(),positionGamer.getY()-2);
            positionGamer.setY(positionGamer.getY()-1); // On peut enfin faire monter le joueur (oufff !)
            break;
			
		case RIGHT : 
			
			if (positionGamer.getY() + 1 >= map.getBlock_height())
                break;
			
            if (map.getMapElement(positionGamer.getX(), positionGamer.getY()+1)== Element.WALL.ordinal())
                break;
            
            if ((map.getMapElement(positionGamer.getX(), positionGamer.getY()+1) == Element.BOX.ordinal() || 
            		map.getMapElement(positionGamer.getX(), positionGamer.getY()+1) == Element.BOX_OK.ordinal()) &&
                    (positionGamer.getY() + 2 >= map.getBlock_height() || map.getMapElement(positionGamer.getX(), positionGamer.getY()+2) == Element.WALL.ordinal() ||
                    		map.getMapElement(positionGamer.getX(), positionGamer.getY()+2) == Element.BOX.ordinal() ||
                    				map.getMapElement(positionGamer.getX(), positionGamer.getY()+2) == Element.BOX_OK.ordinal()))
                    break;
            
            moveBox(positionGamer.getX(),positionGamer.getY()+1,positionGamer.getX(),positionGamer.getY()+2);
            positionGamer.setY(positionGamer.getY()+1);
            break;
			
		case TOP :
			if (positionGamer.getX() - 1 < 0)
                break;
            if (map.getMapElement(positionGamer.getX()-1, positionGamer.getY()) == Element.WALL.ordinal())
                break;
            
            if ((map.getMapElement(positionGamer.getX()-1, positionGamer.getY()) == Element.BOX.ordinal() ||
            		map.getMapElement(positionGamer.getX()-1, positionGamer.getY()) == Element.BOX_OK.ordinal()) &&
                    (positionGamer.getX() - 2 < 0 || map.getMapElement(positionGamer.getX()-2, positionGamer.getY()) == Element.WALL.ordinal() ||
                    		map.getMapElement(positionGamer.getX()-2, positionGamer.getY()) == Element.BOX.ordinal() ||
                    				map.getMapElement(positionGamer.getX()-2, positionGamer.getY()) == Element.BOX_OK.ordinal()))
                    break;
            
            moveBox(positionGamer.getX() - 1,positionGamer.getY(),positionGamer.getX() - 2,positionGamer.getY());
            positionGamer.setX(positionGamer.getX()-1);
            break;
            
		case LOW :
			if (positionGamer.getX() + 1 >= map.getWidth())
                break;
            if (map.getMapElement(positionGamer.getX()+1, positionGamer.getY()) == Element.WALL.ordinal())
                break;
            if ((map.getMapElement(positionGamer.getX()+1, positionGamer.getY()) == Element.BOX.ordinal() ||
            		map.getMapElement(positionGamer.getX()+1, positionGamer.getY()) == Element.BOX_OK.ordinal()) &&
                    (positionGamer.getX() + 2 >= map.getWidth() || map.getMapElement(positionGamer.getX()+2, positionGamer.getY()) == Element.WALL.ordinal() ||
                    		map.getMapElement(positionGamer.getX()+2, positionGamer.getY()) == Element.BOX.ordinal() ||
                    				map.getMapElement(positionGamer.getX()+2, positionGamer.getY()) == Element.BOX_OK.ordinal()))
                    break;
            
            moveBox(positionGamer.getX() + 1,positionGamer.getY(),positionGamer.getX() + 2,positionGamer.getY());
            positionGamer.setX(positionGamer.getX()+1);
            break;
		}
	}
	
	void moveBox(int idep, int ydep, int iarr,int yarr)
	{
	    if (map.getMapElement(idep,ydep) == Element.BOX.ordinal() || map.getMapElement(idep,ydep) == Element.BOX_OK.ordinal())
	    {
	        if (map.getMapElement(iarr,yarr)== Element.GOAL.ordinal())
	        	map.setMapElement(iarr,yarr,Element.BOX_OK.ordinal());
	        else
	        	map.setMapElement(iarr,yarr,Element.BOX.ordinal());

	        if (map.getMapElement(idep,ydep) == Element.BOX_OK.ordinal())
	        	map.setMapElement(idep,ydep,Element.GOAL.ordinal());
	        else
	        	map.setMapElement(idep,ydep,Element.EMPTY.ordinal());
	    }
	}

	   // Permet d'ajouter (abonner) un observateur à l'écoute du Jeu.
 public void ajouterObservateur(Observateur o)
 {
         tabObservateur.add(o); 
 }
 
 // Permet de supprimer (résilier) un observateur écoutant le Jeu
 public void supprimerObservateur(Observateur o)
 {
         tabObservateur.remove(o);              
 }

 // Méthode permettant de notifier tous les observateurs lors d'un changement d'état du Jeu.
 public void notifierObservateurs()
 {
         for(int i=0;i<tabObservateur.size();i++)
         {
                 Observateur o = tabObservateur.get(i);
                 o.actualiser(this);
         }
 }

// Méthode qui permet de mettre à jour de façon artificielle le JEU.
 // Dans un cas réel, on utiliserait les valeurs retournées par les capteurs.
 public void setGame(Map map, int level, int score, int time, String name)
 {
         this.map=map;
         this.level=level;
         this.score=score;
         this.time=time;
         this.name=name;
         notifierObservateurs();
 }
 
   public void keyPressed(KeyEvent e) {
	   /*
	    * quand la touche est presse on cherche la position actuel de mario dans la grille de jeu
	    * si le position a laquelle on veut deplacer mario est un objectif on met marioOnGoal a true
	    * on place mario a cette position
	    * si marioOnGoal est a true on notifie pour afficher la grille de jeu,on repositionne un objectif a cette place,puis on met a false
	    * sinon on teste voir si il ya un objectif restant
	    * si oui on met a jour les changements (notification) puis on continue le jeu
	    * sinon on gagne la partie, on continue au niveau suivant
	    */
       int key = e.getKeyCode();
       
       if (key == KeyEvent.VK_LEFT) {
           this.searchPosition(positionGamer);
           moveMario(map,positionGamer, Direction.LEFT);
           if(map.getMapElement(positionGamer.getX(),positionGamer.getY()) == Element.GOAL.ordinal())
        	   marioOnGoal=true;
           map.setMapElement(positionGamer.getX(), positionGamer.getY(), Element.MARIO.ordinal());
           this.time++;
           this.direction = Direction.LEFT.ordinal();        
       }
       
       if (key == KeyEvent.VK_RIGHT) {
    	   this.searchPosition(positionGamer);
           moveMario(map,positionGamer, Direction.RIGHT);
           if(map.getMapElement(positionGamer.getX(),positionGamer.getY()) == Element.GOAL.ordinal())
        	   marioOnGoal=true;
           map.setMapElement(positionGamer.getX(), positionGamer.getY(), Element.MARIO.ordinal());
           this.time++;
           this.direction = Direction.RIGHT.ordinal();
       }
       if (key == KeyEvent.VK_UP) {
    	   this.searchPosition(positionGamer);
           moveMario(map,positionGamer, Direction.TOP);
           if(map.getMapElement(positionGamer.getX(),positionGamer.getY()) == Element.GOAL.ordinal())
        	   marioOnGoal=true;
           map.setMapElement(positionGamer.getX(), positionGamer.getY(), Element.MARIO.ordinal());
           this.time++;
           this.direction = Direction.TOP.ordinal();           
       }
       if (key == KeyEvent.VK_DOWN) {
    	   this.searchPosition(positionGamer);
           moveMario(map,positionGamer, Direction.LOW);
           if(map.getMapElement(positionGamer.getX(),positionGamer.getY()) == Element.GOAL.ordinal())
        	   marioOnGoal=true;
           map.setMapElement(positionGamer.getX(), positionGamer.getY(), Element.MARIO.ordinal());
           this.time++;
           this.direction = Direction.LOW.ordinal();           
       }
       
       //si on recommence la partie
       if (key == KeyEvent.VK_R) {
    	   map.loadLevel(this.level);
    	   err++;
    	   this.time=0;
    	   positionGamer = new Position();
    	   this.notifierObservateurs();
       }
       
     //sauvegarder la partie
       if (key == KeyEvent.VK_S) {
    	   saveLevel();
       }
       
     //si on charge une sauvegarde
       if (key == KeyEvent.VK_C) {
    	   loadSaveLevel();
    	   map.loadLevel(level);
    	   err=0;
    	   time=0;
    	   score=0;
    	   positionGamer = new Position();
    	   this.notifierObservateurs();
       }
       
       if(marioOnGoal==true){
    	   this.notifierObservateurs();
    	   map.setMapElement(positionGamer.getX(), positionGamer.getY(), Element.GOAL.ordinal());
    	   marioOnGoal=false;
       }
       else
       if (map.searchGoalInMap()==true){
			this.notifierObservateurs(); 
			this.level++;
			updateScore(err);
			JOptionPane.showMessageDialog(null,
			          "Vous avez gagné avec : " +this.time+" deplacements"+
			          "\nVous passez au niveau ",
			          "Information", JOptionPane.NO_OPTION);
			map.loadLevel(this.level);
			this.time=0;
			err=0;
			
			if(level == map.getCount()){
				JOptionPane.showMessageDialog(null,
				          "Vous avez fini le jeu"+
				          "\nNombre de niveau atteint  "+map.getCount(),
				          "Félicitations", JOptionPane.NO_OPTION);
				save_score.setNom(name);
				save_score.setPoint(score);
				if(scoreSerializer.isAccpeted(this.save_score))
					scoreSerializer.serialize();
				save_score= new Score();
				scoreSerializer = new ScoreSerializer();
				map.loadLevel();
				level = 0;
				time=0;
				err=0;
			}
			
			this.notifierObservateurs();
		}
       else
       this.notifierObservateurs();
   }

   /**
 * @param map the map to set
 */
public void setMap(Map map) {
	this.map = map;
}

public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        
        if (key == KeyEvent.VK_LEFT) {
               
        }
        if (key == KeyEvent.VK_RIGHT) {
		               
        }
        if (key == KeyEvent.VK_UP) {
		            
        }
        if (key == KeyEvent.VK_DOWN) {
	              
        }
   }

public void saveLevel(){
	try {
		ObjectOutputStream oos = new ObjectOutputStream(
									new BufferedOutputStream(
											new FileOutputStream(
													new File(FileSystemView.getFileSystemView().getDefaultDirectory()+"/Sobokan/save.scr"))));
		oos.writeObject(level);
		oos.close();
		
	} catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier sauvegarde !", "ERREUR", JOptionPane.ERROR_MESSAGE);
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier sauvegarde !", "ERREUR", JOptionPane.ERROR_MESSAGE);
	}
	
}

public void loadSaveLevel(){
	try {
		File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory()+"/Sobokan/save.scr");
		if(file.length() > 0)
		{
			ObjectInputStream ois = new ObjectInputStream(
										new BufferedInputStream(
											new FileInputStream(
													file)));
			
			level = (int)ois.readObject();
			ois.close();
		}
		else{
			level=0;
		}
		
	} catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier de sauvegarde!\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier de sauvegarde !\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
	} catch (ClassNotFoundException e) {
		JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier de sauvegarde !\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
	}	
}
}
