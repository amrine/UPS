/**
 * 
 */
package classe;

/**
 * @author 21007631
 *
 */
public class Position {
	private int i;
	private int j;

	/**
	 * @param i
	 * @param j
	 */
	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}

	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}

	/**
	 * @param i the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * @return the j
	 */
	public int getJ() {
		return j;
	}

	/**
	 * @param j the j to set
	 */
	public void setJ(int j) {
		this.j = j;
	}

	
	/**
	 * print the position in the screen
	 */
        @Override
	public String toString() {
		return "Position [i=" + i + ", j=" + j + "]";
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return true if i and j contained in the grid
	 */
	public boolean gridContaint(int i, int j){
		return((i>=1 && i<=8) && (j>=1 && j<=8));
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return the new Position
	 */
	public Position ajouter(int i, int j){
		if(gridContaint(this.i+i, this.j+j)){
			this.i +=i;
			this.j +=j;
			return this;
		}
		else return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Position test= new Position(4,5);
		System.out.println("correcte "+test.toString());
		test.ajouter(4,5);
		System.out.println("incorrecte "+test.toString());

	}

}
