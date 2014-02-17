
package corrector.figure;

import corrector.util.Point2D;
import java.awt.Graphics;
import java.awt.Polygon;

/** 
 * <p>Title : IPolygon </p>
 * <p>Abstract class representing a figure of type  polygon</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public abstract class IPolygon extends Figure{
    /**
    *Default constructor of a polygon
    */
    public IPolygon(){
        super();
        for (int i = 0 ; i < array.length ; i++)
            array[i] = new Point2D();
    }
    /**
    * Method used to visualize and draw on the screen a figure.
    * @param g a graphics
    */
    @Override
    public void display(Graphics g){
        super.display(g);
        g.drawPolygon(toPolygon());
    }
    /**
    *  method to change the memory points of figure
    * @param arrayEntry a point array
    */
    @Override
  public void changePoints(Point2D[] arrayEntry){
        array = new Point2D[arrayEntry.length];
        for (int i = 0;i < array.length; i++)
            array[i] = new Point2D(arrayEntry[i].getX(), arrayEntry[i].getY());
  }
    /**
    * Method to convert a figure (other than ConiqueCentree) to a polygon
    * @return polygon the polygon
    */
    public Polygon toPolygon() {
        int[] x = new int[array.length];
        int[] y = new int[array.length];
        for (int i = 0 ; i < array.length ; i++) {
            x[i] = (int) array[i].getX();
            y[i] = (int) array[i].getY();
        }
        Polygon p = new Polygon(x,y,x.length);
        return p;
    }
    /**
    * This method returns "true" if x and y coordinates passed as parameters a
    * re in the polygon.
    * @param x the x coordinate
    * @param y the y coordinate
    * @return boolean
    */
    @Override
    public boolean contains(int x, int y) {
        Polygon p = toPolygon();
        return p.contains(x,y);
    }
}
