/**
 * 
 */
package classe;

import java.util.ArrayList;

/**
 * @author 21007631
 *
 */
public class Pion extends Type{
	

	public Pion(Couleur couleur) {
		super(couleur,"Pion");
	}
	
	public boolean destinationValide(Position position, Plateau plateau){
		if (!position.gridContaint(position.getI(),position.getJ())) return false;
		else
		switch(plateau.getType(position).getCouleur()){
		case BLANC :
		case NOIR :
		}
		return false;
	}

	public ArrayList<Position> destinations(Position position, Plateau plateau) {
		
		return null;
	}

}
