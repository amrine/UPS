

package corrector.util;

import java.io.Serializable;



/** 
 * <p>Title : Point2D </p>
 * <p>A point representing a location in (x, y) coordinate space, 
 * specified in double precision.</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class Point2D implements Serializable {
    /**
     * The X coordinate of this <code>Point2D</code>.
     */
    public double x;
    /**
     * The Y coordinate of this <code>Point2D</code>.
     */
    public double y;
    /**
     * Constructs and initializes a <code>Point2D</code> with
     * coordinates (0,&nbsp;0)
     */
    public Point2D() {
        this(0, 0);
    }
    /**
     * Constructs and initializes a <code>Point2D</code> with the
     * specified coordinates.
     *
     * @param x the X coordinate of the newly
     *          constructed <code>Point2D</code>
     * @param y the Y coordinate of the newly
     *          constructed <code>Point2D</code>
     */
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Returns the X coordinate of this <code>Point2D</code> in
     * <code>double</code> precision.
     * @return the X coordinate of this <code>Point2D</code>.
     */
    public double getX() {
        return x;
    }
    /**
     * Returns the Y coordinate of this <code>Point2D</code> in
     * <code>double</code> precision.
     * @return the Y coordinate of this <code>Point2D</code>.
     */
    public double getY() {
        return y;
    }
    /**
     * Sets the location of this <code>Point2D</code> to the
     * specified <code>double</code> coordinates.
     *
     * @param x the new X coordinate of this <code>Point2D</code>
     * @param y the new Y coordinate of this <code>Point2D</code>
     */
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Sets the location of this <code>Point2D</code> to the same
     * coordinates as the specified <code>Point2D</code> object.
     * @param p the specified <code>Point2D</code> to which to set
     * this <code>Point2D</code>
     */
    public void setLocation(Point2D p) {
        setLocation(p.getX(), p.getY());
    }
    /**
     * Returns the square of the distance between two points.
     *
     * @param x1 the X coordinate of the first specified point
     * @param y1 the Y coordinate of the first specified point
     * @param x2 the X coordinate of the second specified point
     * @param y2 the Y coordinate of the second specified point
     * @return the square of the distance between the two
     * sets of specified coordinates.
     */
    public static double distanceSq(double x1, double y1,
                                    double x2, double y2)
    {
        x1 -= x2;
        y1 -= y2;
        return (x1 * x1 + y1 * y1);
    }
    /**
     * Returns the distance between two points.
     *
     * @param x1 the X coordinate of the first specified point
     * @param y1 the Y coordinate of the first specified point
     * @param x2 the X coordinate of the second specified point
     * @param y2 the Y coordinate of the second specified point
     * @return the distance between the two sets of specified
     * coordinates.
     */
    public static double distance(double x1, double y1,
                                  double x2, double y2)
    {
        x1 -= x2;
        y1 -= y2;
        return Math.sqrt(x1 * x1 + y1 * y1);
    }
    /**
     * Returns the square of the distance from this
     * <code>Point2D</code> to a specified point.
     *
     * @param px the X coordinate of the specified point to be measured
     *           against this <code>Point2D</code>
     * @param py the Y coordinate of the specified point to be measured
     *           against this <code>Point2D</code>
     * @return the square of the distance between this
     * <code>Point2D</code> and the specified point.
     */
    public double distanceSqTo(double px, double py) {
        px -= getX();
        py -= getY();
        return (px * px + py * py);
    }
    /**
     * Returns the square of the distance from this
     * <code>Point2D</code> to a specified <code>Point2D</code>.
     *
     * @param pt the specified point to be measured
     *           against this <code>Point2D</code>
     * @return the square of the distance between this
     * <code>Point2D</code> to a specified <code>Point2D</code>.
     */
    public double distanceSqTo(Point2D pt) {
        double px = pt.getX() - getX();
        double py = pt.getY() - getY();
        return (px * px + py * py);
    }
    /**
     * Returns the distance from this <code>Point2D</code> to
     * a specified point.
     *
     * @param px the X coordinate of the specified point to be measured
     *           against this <code>Point2D</code>
     * @param py the Y coordinate of the specified point to be measured
     *           against this <code>Point2D</code>
     * @return the distance between this <code>Point2D</code>
     * and a specified point.
     */
    public double distanceTo(double px, double py) {
        px -= getX();
        py -= getY();
        return Math.sqrt(px * px + py * py);
    }
    /**
     * Returns the distance from this <code>Point2D</code> to a
     * specified <code>Point2D</code>.
     *
     * @param pt the specified point to be measured
     *           against this <code>Point2D</code>
     * @return the distance between this <code>Point2D</code> and
     * the specified <code>Point2D</code>.
     * @since 1.2
     */
    public double distanceTo(Point2D pt) {
        double px = pt.getX() - getX();
        double py = pt.getY() - getY();
        return Math.sqrt(px * px + py * py);
    }
    	/**
	 * Method to translate a point along the coordinate parameter
	 * @param dx the x coordinate
	 * @param dy the y coordinate
	 */
	public void translation(int dx, int dy){
		x += dx;
		y += dy;		
	}
    /**
     * Creates a new object of the same class and with the
     * same contents as this object.
     * @return     a clone of this instance.
     * @exception  OutOfMemoryError            if there is not enough memory.
     * @see        java.lang.Cloneable
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }
    /**
     * Returns the hashcode for this <code>Point2D</code>.
     * @return      a hash code for this <code>Point2D</code>.
     */
    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(getX());
        bits ^= java.lang.Double.doubleToLongBits(getY()) * 31;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }
    /**
     * Determines whether or not two points are equal. Two instances of
     * <code>Point2D</code> are equal if the values of their
     * <code>x</code> and <code>y</code> member fields, representing
     * their position in the coordinate space, are the same.
     * @param obj an object to be compared with this <code>Point2D</code>
     * @return <code>true</code> if the object to be compared is
     *         an instance of <code>Point2D</code> and has
     *         the same values; <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point2D) {
            Point2D p2d = (Point2D) obj;
            return (getX() == p2d.getX()) && (getY() == p2d.getY());
        }
        return super.equals(obj);
    }
    /**
     * Returns a <code>String</code> that represents the value 
     * of this <code>Point2D</code>.
     * @return a string representation of this <code>Point2D</code>. 
     */
    @Override
    public String toString(){
        return "Point2D["+x+", "+y+"]";
    }
    public class Point extends Point2D{
        public int x;
        public int y;
        
        public Point(int x, int y){
            super(x,y);
            this.x = x;
            this.y = y;
        }
        
        
        
    }
}
