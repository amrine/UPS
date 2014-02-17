
package corrector.figure.shape;

import corrector.figure.shape.Quadrilateral;
import corrector.util.Point2D;



/** 
 * <p>Title : Rectangle </p>
 * <p>Class that instantiates an object of type ractangle</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class Rectangle extends Quadrilateral{
    /**
     * default constructor
     */
    public Rectangle(){
        super();
    }
    /**
     * Constructor of a rectangle from a given point in parameter. a small 
     * rectangle of 40x40 pixels is created at the same time, exclusively for 
     * the handling of one of the fundamental points of a figure. This rectangle
     * will be deleted when finished an action  with the mouse on the figure.
     * @param p 
     */
    public Rectangle(Point2D p){
        array = new Point2D[super.nbPoints()];
        array[0] = new Point2D( p.getX() -20, p.getY() +20 );
        array[2] = new Point2D( p.getX() +20, p.getY() -20 );
        array[1] = new Point2D( array[2].getX() , array[0].getY() );
        array[3] = new Point2D( array[0].getX() , array[2].getY() );
    }
    /**
     * Method that return the number of points needed to construct a basic 
     * rectangle, ie the number of points of entry.
     * @return Intger the number of point needed (two points)
     */
    @Override
    public int nbPoints(){
        return 2;
    }
}
