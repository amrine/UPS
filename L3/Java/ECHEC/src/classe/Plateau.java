/**
 * 
 */
package classe;

/**
 * @author 21007631
 *
 */
public class Plateau {
	private Type[][] plateau;

	/**
	 * constructor of plateau
	 */
	public Plateau() {
		plateau = new Type[8][8];
	}

	/**
	 * @return the type of the piece present in the case
	 */
	public Type getType(Position position) {
		return plateau[position.getI()][position.getJ()];
	}
	

}
