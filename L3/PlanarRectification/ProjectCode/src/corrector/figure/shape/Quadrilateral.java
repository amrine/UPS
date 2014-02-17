

package corrector.figure.shape;

import corrector.figure.IPolygon;

/** 
 * <p>Title : Quadrilateral </p>
 * <p>Class that instantiates an object of type quadrilateral</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class Quadrilateral extends IPolygon{
    /**
     * Constructor that initializes the points representing the vertices 
     * of the figure
     */
    public Quadrilateral(){
        super();
    }
    /**
     * Method that return the number of points needed to construct a basic 
     * quadrilateral, ie the number of points of entry.
     * @return Integer the number of point needed (four points)
     */
    @Override
    public int nbPoints() {
        return 4;
    }
}
