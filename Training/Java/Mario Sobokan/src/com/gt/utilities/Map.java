/**
 * 
 */
package com.gt.utilities;

import com.gt.game.Game.Element;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

/**
 * @author thierno
 *
 */
public class Map  {
	
	int block_size= 34; // block size (size of sprites)
	int width= 12;
	int height=12;
	//dimension of game panel
	int block_widht = width*block_size;
	int block_height = height*block_size;
	int map[][] = new int[width][height];
	int count; //sum of level
	
	public int getCount(){
		return count;
	}
	
	public Map() {
		initMap();		
	}
	
	private void initMap(){
		for(int i=0; i<map.length;i++) {
                for(int j=0; j<map.length;j++) {
                        map[i][j]=1;
                    }
            }
		countLevel();
	}
	/**
	 * @return the map
	 */
	public int[][] getMap() {
		return map;
	}
	/**
	 * @return the element
	 */
	public int getMapElement(int i,int j) {
		return map[i][j];
	}
	
	/**
	 * @return the element
	 */
	public void setMapElement(int i,int j, int element) {
		map[i][j]= element;
	} 
	
	/**
	 * @param map the map to set
	 */
	public void setMap(int[][] map) {
		this.map = map;
	}
	

	/**
	 * @return the block_size
	 */
	public int getBlock_size() {
		return block_size;
	}

	/**
	 * @return the block_widht
	 */
	public int getBlock_widht() {
		return block_widht;
	}

	/**
	 * @return the block_height
	 */
	public int getBlock_height() {
		return block_height;
	}
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	public int[][] loadLevel(){
		char[] tabWord =new char[width*height+1];
		try {
		File file = new File("file/niveaux.lvl");
                try (FileReader fnr = new FileReader(file)) {
                    int j = 0,cpt=0;
                    //Lecture des donn�es
                    while((j = fnr.read()) != -1){
                            if(cpt == width*height) {
                            break;
                        }
                            tabWord[cpt]= (char)j;
                            cpt++;
                    }
                    
                    for (int i = 0 ; i < width ; i++)
                {
                    for ( j = 0 ; j < height ; j++)
                    {
                        switch (tabWord[(i * width)+j])
                        {
                            case '0':
                                map[j][i] = 0;
                                break;
                            case '1':
                                map[j][i] = 1;
                                break;
                            case '2':
                                map[j][i] = 2;
                                break;
                            case '3':
                                map[j][i] = 3;
                                break;
                            case '4':
                                map[j][i] = 4;
                                break;
                        }
                    }
                }
                }
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement du niveau !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement du niveau !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return map;
	}
	
	public void printMap(){
		for(int k=0;k<map.length;k++){
			System.out.println("\n");
			for(int f=0;f<map.length;f++){
				System.out.print(" " +map[k][f]);
			}
		}
		System.out.println("\n");
	}
	
	public boolean searchGoalInMap(){
		for(int i=0;i<map.length;i++) {
                for(int j=0;j<map.length;j++) {
                        if(map[i][j] == Element.GOAL.ordinal()) {
                        return false;
                    }
                    }
            }
		return true;
	}


	public int[][] loadLevel(int numLine){
		char[] tabWord =new char[width*height+1];
		try {
                try (LineNumberReader fnr = new LineNumberReader(new FileReader(new File("file/niveaux.lvl")))) {
                    int j = 0,cpt=0;
                    //Lecture des donn�es
                    while((j = fnr.read()) != -1){
                            if(fnr.getLineNumber() == (numLine+1)) {
                            break;
                        }
                            else
                            if(fnr.getLineNumber() == numLine){
                                    if(cpt == width*height) {
                                    break;
                                }
                                    else{
                                    tabWord[cpt]= (char)j;
                                    cpt++;
                            }
                            }
                    }
                    
                    for (int i = 0 ; i < width ; i++)
                {
                    for ( j = 0 ; j < height ; j++)
                    {
                        switch (tabWord[(i * width)+j])
                        {
                            case '0':
                                map[j][i] = 0;
                                break;
                            case '1':
                                map[j][i] = 1;
                                break;
                            case '2':
                                map[j][i] = 2;
                                break;
                            case '3':
                                map[j][i] = 3;
                                break;
                            case '4':
                                map[j][i] = 4;
                                break;
                        }
                    }
                }
                }
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement du niveau !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement du niveau !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return map;
	}
	
	public void countLevel(){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("file/niveaux.lvl");
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Erreur du comptage des niveaux !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		LineNumberReader l = new LineNumberReader(       
		       new BufferedReader(new InputStreamReader(fis)));
			
		              try {
						while ((l.readLine())!=null)
						 {
						    count = l.getLineNumber();
						 }
					} catch (IOException e) {
					}
	}
	
    @Override
	public String toString(){
		String str="";
		for (int i = 0 ; i < width ; i++)
	    {
	        for ( int j = 0 ; j < height ; j++)
	        {
	        	str+=map[j][i];
	        	
	        }
	    }
		//System.out.println(str);
		return str;
	}
	
	public  void save() {
		File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory()+"/Sobokan/save.lvl");
		FileWriter fw;
		
		String lu= toString();
		try {
			//Cr�ation de l'objet
			fw = new FileWriter(file);
			//On �crit la cha�ne
			fw.write(lu);
			//On ferme le flux
			fw.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur  de la sauvegarde !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur  de la sauvegarde !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public int[][] loadSave(){
		char[] tabWord =new char[width*height+1];
		try {
		File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory()+"/Sobokan/save.lvl");
                try (FileReader fnr = new FileReader(file)) {
                    int j = 0,cpt=0;
                    //Lecture des donn�es
                    while((j = fnr.read()) != -1){
                            if(cpt == width*height) {
                            break;
                        }
                            tabWord[cpt]= (char)j;
                            cpt++;
                    }
                    
                    for (int i = 0 ; i < width ; i++)
                {
                    for ( j = 0 ; j < height ; j++)
                    {
                        switch (tabWord[(i * width)+j])
                        {
                            case '0':
                                map[j][i] = 0;
                                break;
                            case '1':
                                map[j][i] = 1;
                                break;
                            case '2':
                                map[j][i] = 2;
                                break;
                            case '3':
                                map[j][i] = 3;
                                break;
                            case '4':
                                map[j][i] = 4;
                                break;
                        }
                    }
                }
                }
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement de la sauvegarde !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement de la sauvegarde !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return map;
	}
}
