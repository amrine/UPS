
package corrector.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.swing.JOptionPane;

/** 
 * <p>Title : UTIL </p>
 * <p>class containing methods called regularly.</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class UTIL {
     /**
     * @param p an array containing four points.
     * @return array the maximum and minimum values ​​stored per couple 
     * array[0] = (Xmin, Ymin)
     * array[1] = (Xmax, Ymax)
     */
    public static Point2D[] minAndMaxOfPoints(Point2D... p){
        double uMin = UTIL.min(p[0].getX(), p[1].getX(), p[2].getX(), 
                p[3].getX());
        double uMax = UTIL.max(p[0].getX(), p[1].getX(), p[2].getX(), 
                p[3].getX());
        double vMin = UTIL.min(p[0].getY(), p[1].getY(), p[2].getY(), 
                p[3].getY());
        double vMax = UTIL.max(p[0].getY(), p[1].getY(), p[2].getY(), 
                p[3].getY());
        Point2D valMin = new Point2D(uMin, vMin);
        Point2D valMax = new Point2D(uMax, vMax);
        Point2D[] tab = {valMin, valMax};
        return tab;
    }
     /**
     * @param p an array of <code>Point2D</code> containing four points
     * @return  array of <code>Point2D</code> 
     * the values ​​of four points forming a rectangle
     */
    public static Point2D[] boundingRectangle(Point2D... p){
        Point2D[] point = minAndMaxOfPoints(p);
        Point2D p1 = new Point2D(point[0].getX(),point[0].getY());
        Point2D p2 = new Point2D(point[0].getX(),point[1].getY());
        Point2D p3 = new Point2D(point[1].getX(),point[1].getY());
        Point2D p4 = new Point2D(point[1].getX(),point[0].getY());
        Point2D[] tab ={p1,p2,p3,p4};
        return tab;
    }
     /**
     * @param x <code>double</code>
     * @param y <code>double</code>
     * @param z <code>double</code>
     * @param t <code>double</code>
     * @return <code>double</code> the minimum of the four numbers
     */
    public static double min(double x, double y, double z, double t){
        return Math.min(Math.min(x,y), Math.min(z,t));
    }
    /**
     * @param x <code>double</code>
     * @param y <code>double</code>
     * @param z <code>double</code>
     * @param t <code>double</code>
     * @return <code>double</code> the maximum of the four numbers
     */
    public static double max(double x, double y, double z, double t){
        return Math.max(Math.max(x,y), Math.max(z,t));
    }
    /**
     * 
     * @param x a <code>double</code> number
     * @return <code>double</code> the decimal part of x
     */
    public static double decimalPart(double x){
        return x - (int)x;
    }
    /**
     * 
     * @param p a point
     * @return <code>boolean</code> 
     * true if the point values have not decimal
     * part greater than 0 else false
     */
    public static boolean isInteger(Point2D p){
        return decimalPart(p.getX()) ==0 && decimalPart(p.getY()) ==0;
    }
    /**
     * transform an image to gray color type
     * @param obj
     * @return Object
     * @throws Exception 
     */
    public static Object transform(Object obj) throws Exception
    {
       if(obj instanceof Image)
       {
          Image image = (Image) obj;
          BufferedImage buffImage = new BufferedImage(image.getWidth(null), 
                  image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);  
           Graphics2D g = buffImage.createGraphics();  
           g.drawImage(image,0,0,null);
           g.dispose();
           return buffImage;
       }
       if(obj instanceof RenderedImage)
       {
          RenderedImage image = (RenderedImage) obj;
          BufferedImage buffImage = new BufferedImage(image.getWidth(), 
                  image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);  
           Graphics2D g = buffImage.createGraphics();  
           g.drawRenderedImage(image,AffineTransform.getTranslateInstance(0,0));
           g.dispose();
           return buffImage;
       }
       throw new Exception("Invalid data type: "+obj.getClass().getName());
    }
    /**
    * Create a compatible image.
    *
    * @param width  The width.
    * @param height The height.
    * @param type   The type of the image.
    * @return A compatible image.
    */
    public static BufferedImage createCompatibleImage(int width, int height,
            int type) {
        GraphicsConfiguration gc = GraphicsEnvironment.
        getLocalGraphicsEnvironment().
                getDefaultScreenDevice().getDefaultConfiguration();

        return gc.createCompatibleImage(width, height, type);
    }
    /**
    * Create a compatible image from an existing image.
    *
    * @param image The image to make compatible.
    * @return A compatible image filled with the base image.
    */
    public static BufferedImage createCompatibleImage(BufferedImage image) {
        GraphicsConfiguration gc = GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getDefaultScreenDevice().getDefaultConfiguration();

        if (image.getColorModel().equals(gc.getColorModel())) {
            return image;
        } else {
            BufferedImage compatibleImage = gc.createCompatibleImage(
                    image.getWidth(),image.getHeight(),image.getTransparency());

            Graphics g = compatibleImage.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            return compatibleImage;
        }
    }
        /**
     * 
     * @param message
     * @param titre 
     */
    public static void warningMessage(String message,String titre){
        JOptionPane.showMessageDialog(null, message + 
                "\n", titre, JOptionPane.WARNING_MESSAGE);
    }
    /**
     * 
     * @param message
     * @param titre 
     */
    public static void errorMessage(String message,String titre){
        JOptionPane.showMessageDialog(null, message + 
                "\n", titre, JOptionPane.ERROR_MESSAGE);
    }
        /**
     * 
     * @param message
     * @param titre 
     */
    public static void informationMessage(String message,String titre){
        JOptionPane.showMessageDialog(null, message + 
                "\n", titre, JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * 
     * @param nanoSecs long
     * @return String a time value
     */
    public static String toString(long nanoSecs) {
      int minutes    = (int) (nanoSecs / 60000000000.0);
      int seconds    = (int) (nanoSecs / 1000000000.0)  - (minutes * 60);
      int millisecs  = (int) ( ((nanoSecs / 1000000000.0) - (seconds + minutes 
              * 60)) * 1000);
      if (minutes == 0 && seconds == 0)
         return millisecs + "ms";
      else if (minutes == 0 && millisecs == 0)
         return seconds + "s";
      else if (seconds == 0 && millisecs == 0)
         return minutes + "min";
      else if (minutes == 0)
         return seconds + "s " + millisecs + "ms";
      else if (seconds == 0)
         return minutes + "min " + millisecs + "ms";
      else if (millisecs == 0)
         return minutes + "min " + seconds + "s";

      return minutes + "min " + seconds + "s " + millisecs + "ms";
   }
   /**
    * method to transform a gray value to rgb value
    * @param gray an integer
    * @return Integer the rgb value
    */
    public static int gray2rgb(int gray) {
	gray&=0xFF;
	return (255<<24)+(gray<<16)+(gray<<8)+gray;
    }
     /**
    * method to transform a rgb value to gray value
    * @param srgb an integer
    * @return Integer the gray value
    */
    public static int rgb2gray(int srgb) {
	int r = (srgb >>16 ) & 0xFF;
	int g = (srgb >> 8 ) & 0xFF;
	int b = srgb & 0xFF;
	return (int)(0.299*r + 0.587*g + 0.114*b);
    }
}
