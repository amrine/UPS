

package corrector.figure;

import corrector.util.Point2D;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/** 
 * <p>Title : Figure </p>
 * <p>Abstract class representing an object of type Figure  and  implements 
 * interface serializable  for serializing the figure constructed 
 * for recording in a file.</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public abstract class Figure implements Serializable {
  /**
   * Attribute that contains all the memory points of the figure
  */
  protected Point2D[] array;
  /**
   * Attribute that indicates if the figure is selected by the user
   */
  protected boolean selected;
  /**
   * the color of the figure
   */
  protected Color color;
  /**
   * Abstract method that return the number of points required for the 
   * construction of the figure
   * @return Integer the number of points needed
   */
  public abstract int nbPoints();
  /**
   *Abstract method which can change the memory points of figure
   * @param point array of points of entry
   */
  public abstract void changePoints(Point2D[] point);
  /**
   *This method returns "true" if x and y coordinates passed are in the polygon.
   * @param x the x coordinate
   * @param y the x coordinate
   * @return boolean
   */
  public abstract boolean contains(int x, int y) ;
  /**
   * Constructor of figure
   */
  public Figure(){
    array = new Point2D[nbPoints()];
    selected = false;
    color = Color.BLACK;
  }
  /**
   * Method used to visualize and draw on the screen a  figure.
   * @param g a graphics
   */
  public void display(Graphics g) {
      if(selected){
          Color c = g.getColor();
          g.setColor(Color.red);
          int x, y;
          for (int i = 0; i < array.length; i++) {
              x = (int) array[i].getX();
              y = (int) array[i].getY();
              g.drawRect( x-5, y-5, 7, 7);
          }
          g.setColor(c);
      }
  }
  /**
   *Method to select a figure
   */
  public void select(){
    selected = true;
  }
  /**
   * Method to deselect a figure
   */
  public void deselect(){
    selected = false;
  }
  /**
   * 
   * @return boolean true if the figure is selected else false
   */
  public boolean isSelected(){
      return selected != false;
  }
  /**
   * Method that can translate a geometric figure
   * @param dx the x coordinate for translation
   * @param dy the y coordinate for translation
   */
  public void translation(int dx , int dy){
    for (int i = 0; i < array.length; i++)
      array[i].translation(dx, dy);
  }
  /**
   * 
   * @param c the color to set
   */
  public void setColor(Color c){
      this.color=c;
  }
  /**
   * 
   * @return array of <code>Point2D</code> of the figure
   */
  public Point2D[] getArray(){
      return array;
  }
}