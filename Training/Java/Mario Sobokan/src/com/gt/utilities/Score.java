package com.gt.utilities;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Score implements Serializable{

  private String nom;
  private int point;

  public Score(){
	    nom = new String("Inconnue");
	    point = 0;
  }

  public Score(String nom, int point){
	  this.nom = nom;
	  this.point = point;
  }

  public int getPoint(){
	  return point;
  }
  
  /**
 * @param point the point to set
 */
public void setPoint(int point) {
	this.point = point;
}

public String getNom(){
	  return nom;
  }
  
  public void setNom(String nom){
	  this.nom = (nom != null) ? nom : "Inconnue";
  }


}
