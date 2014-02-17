
package corrector.treatement;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import corrector.util.Point2D;
import corrector.util.UTIL;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;


/** 
 * <p>Title : Homography </p>
 * <p>The Homography class uses a 3x3 matrix to represent the relationship 
 * between the points in the camera and the points in the projected image.</p>
 * <br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class Homography {
    /**
     * the 3x3 matrix 
     */
    private Matrix H = Matrix.identity(3,3);
    /**
     * whether to display debug messages
     */
    public boolean debug = false;
    /**
     * the image source
     */
    private BufferedImage source;
    /**
     * writable data of the image to correct
     */
    private WritableRaster raster;
    /**
     * the number of row of the image
     */
    private int nbRow;
    /**
     * the number of columns of the image
     */
    private int nbCol;
    /**
     * array of pixel
     */
    int[] result;
    /**
     * default constructor
     */
    public Homography(){}
    /** 
     * @param source <code>BufferedImage</code> the image source
     */
    public Homography(BufferedImage source){
        this.source = source;
        nbRow = source.getWidth();
        nbCol = source.getHeight();
        result = new int[nbRow*nbCol];
    }
    /**
     * Estimate the homography between the camera and the projected image using 
     * an array of known correspondences. The four corners of the sketch are 
     * good for this.
     * @param uv an array of points in camera coordinates 
     * @param xy an array of points in screen coordinates
     */
     private void computeHomography(Point2D[] uv, Point2D[] xy){
         // Creates an array of two times the size of the cam[] array 
         double[][] a = new double[2*uv.length][];
                
         // Creates the estimation matrix
         for (int i = 0; i < uv.length; i++){
             double l1 [] = {uv[i].getX(), uv[i].getY(), 1, 0, 0, 0, 
                 -uv[i].getX()*xy[i].getX(), -uv[i].getY()*xy[i].getX(),
                 -xy[i].getX()};
             double l2 [] = {0, 0, 0, uv[i].getX(), uv[i].getY(), 1, 
                 -uv[i].getX()*xy[i].getY(), -uv[i].getY()*xy[i].getY(), 
                 -xy[i].getY()};
             a[2*i] = l1;
             a[2*i+1] = l2;
         }
         Matrix A = new Matrix(a);
         Matrix T = A.transpose();
         Matrix X = T.times(A);
         EigenvalueDecomposition E = X.eig();
         //Find the eigenvalues and put that in an array
         double[] eigenvalues = E.getRealEigenvalues();
         // grab the first eigenvalue from the eigenvalues []
         double w = eigenvalues[0];
         int r = 0; //the number of the vector
         // Find the minimun eigenvalue
         for (int i= 0; i< eigenvalues.length; i++){
             if (debug) System.out.println(eigenvalues[i]);
             if (eigenvalues[i] <= w){
                 w = eigenvalues[i];
                 r = i;
             }
         }
         // find the corresponding eigenvector
         Matrix v = E.getV();
         if (debug) v.print(9,9);          
         // create the homography matrix from the eigenvector v
         for (int i = 0; i < 3; i++){
             for (int j = 0; j < 3; j++){
                 H.set(i, j, v.get(i*3+j, r));
             }
         }
     }
     /**
      * Find the coordinates of the <code>Point2D</code> p in the intial image
      * @param p a <code>Point2D</code>
      */
     public void computePosition(Point2D p){
         Point2D r = findDistoredCorner(p);
         p.setLocation(r.getX(), r.getY());
     }
     /**
      * find the coordinates of the corners of images of the original image by 
      * the homography H (coordinates of the distorted image by H)
      * @param p a <code>Point2D</code> to be transformed
      * @return <code>Point2D</code>
      */
     private Point2D findDistoredCorner(Point2D p){
         double[][] a = new double[3][1];
         a[0][0] = p.getX();
         a[1][0] = p.getY();
         a[2][0] = 1;
         Matrix D = new Matrix(a);
         Matrix U = H.times(D);
         Matrix L = U.times(1/U.get(2,0));
         double x = L.get(0, 0);
         double y = L.get(1, 0);
         Point2D p2 = new Point2D(x,y);
         return p2;
     }
    /**
     * method to correct the distored image at the same size as the original 
     * image.
     * @param uv an array of <code>Point2D</code>
     * @param ratio <code>double</code>
     */
    public void rectifyWithTheSameSizeOfImage(Point2D[] uv, double ratio){
        /*
         * Parameter estimation of the homography H from the positions of 
         * the quadrilateral in the image.
         */
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(ratio, 0);
        Point2D p3 = new Point2D(ratio, 1);
        Point2D p4 = new Point2D(0, 1);
        Point2D[] xy = {p1, p2, p3, p4};
        /*
         * estimate the homography
         */
        computeHomography(uv,xy);
        /*
         * Calculating the coordinates of the corners of images of the original 
         * image by the homography H.
         */   
        Point2D xy1 = findDistoredCorner(new Point2D(0, 0));
        Point2D xy2 = findDistoredCorner(new Point2D(nbRow-1, 0));
        Point2D xy3 = findDistoredCorner(new Point2D(nbRow-1, nbCol-1));
        Point2D xy4 = findDistoredCorner(new Point2D(0, nbCol-1));
        /*
         * Calculation of the bounding rectangle of the transformed image
         */
        Point2D[] point = UTIL.minAndMaxOfPoints(xy1, xy2, xy3, xy4);
        /*
         * Calculation of the further processing so that the final image has 
         * the same size as the original image.
         */
        setFinalHomography(point);
        /* inverse of H which will produce the rectified image */
        H = H.inverse();
    }
    /**
     * method to set the final homography (final transformation)
     * @param point an array of lenght two <code>Point2D</code> odered by
     * Xmin,Ymin and Xmax,Ymax
     */
    private void setFinalHomography(Point2D[] point){
        /*calculating scale factors*/
        double aU = (nbRow - 1)/(point[1].getX() - point[0].getX());
        double aV = (nbCol - 1)/(point[1].getY() - point[0].getY());
        /*
         * selection of a single scaling factor for the length ratios are 
         * maintained and that we should lose nothing of the original image.
         */
        double a = Math.min(aU, aV);
        /*
         * calculation of the matrix of the additional transformation (
         * translation + scaling)
         */
        double[][] aArray = {
            {a, 0, -a*point[0].getX()}, 
            {0, a, -a*point[0].getY()},
            {0, 0, 1}
        };
        /*additional transformation matrix*/
        Matrix T = new Matrix(aArray);
        /*final processing*/
        Matrix Hf = T.times(H);
        H = Hf;
    }
    /**
     * method to correct the distored image
     * the new image to the same size of the object.
     * @param uv an array of <code>Point2D</code>
     */
    public void rectifyWithTheSameSizeOfObject(Point2D... uv){
        /*
         * Parameter estimation of the homography H from the positions of 
         * the quadrilateral in the image.
         */
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(nbRow, 0);
        Point2D p3 = new Point2D(nbRow, nbCol);
        Point2D p4 = new Point2D(0, nbCol);
        Point2D[] xy = {p1, p2, p3, p4};
        /*
         * estimate the homography
         */
        computeHomography(uv,xy);
        H = H.inverse();
    }
     /** 
     * @param p a point
     * @param L the number of lines
     * @param C the number of columns
     * @return boolean true if the point is contained in the plan else false
     */
    private boolean contains(Point2D p, int L, int C){
        return ((p.getX() >=0 && p.getX() < L) && 
                (p.getY() >= 0 && p.getY() < C));
    }
    /**
     * Method to get the gray value at the point coordinate in the array of 
     * pixels
     * @param point <code>Point2D</code>
     * @return <code>Integer</code> the value 
     */
    public int getGrayValueAt(Point2D point){
     try{
         return UTIL.rgb2gray(source.getRGB(((int)point.x),((int)point.y)));
     }catch(ArrayIndexOutOfBoundsException e){  
     }
        return 0;
    }
    /**
     * resampling : nearest neighbor method
     * @param dest <code>BufferedImage</code> the image to correct 
     */
    @SuppressWarnings("empty-statement")
     public void nearestNeighbor(BufferedImage dest){
        raster = (WritableRaster) dest.getData();
        for(int x=0; x< nbRow; x++){
            for(int y=0; y< nbCol ; y++){
                Point2D p = new Point2D(x,y);
                //retrieve the antecedant of the point
                computePosition(p);
                /*
                 * if the point is contained in the image grid we find his  
                 * closet neighbor and we set the pixel
                 */
                //the closest neighbor 
                p.setLocation(Math.round(p.x),Math.round(p.y));
                if(contains(p,nbRow, nbCol)){
                    int valueXY = getGrayValueAt(p);
                    result[y*nbRow+ x] = valueXY;
                }
            } 
        }
        raster.setPixels(0, 0, nbRow,nbCol , result);
        dest.setData(raster);
    }
    /**
     * resampling : billinear interpolation method
     * @param dest <code>BufferedImage</code> the image to correct
     */
     public void billinearInterpolation(BufferedImage dest){
        raster = (WritableRaster) dest.getData();
        for(int x=0; x< nbRow; x++){
            for(int y=0; y< nbCol ; y++){
                Point2D p = new Point2D(x,y);
                //retrieve the antecedant of the point
                computePosition(p);
                /*
                 * if the point is contained in the image grid we find his 4 
                 * closet neighbors and we set the pixel
                 */
                //the closest neighbor to the left
                int u = (int) p.x;
                int v = (int) p.y;
                p.setLocation(u, v);
                if(contains(p,nbRow, nbCol)){                  
                    double dx = p.x - u; //dx = x-i
                    double dy = p.y - v; //dy = y-j
                    int A = getGrayValueAt(new Point2D(u,v)); 
                    int B = getGrayValueAt(new Point2D(u+1,v)); 
                    int C = getGrayValueAt(new Point2D(u+1,v+1));
                    int D = getGrayValueAt(new Point2D(u,v+1)); 
                   
                    //B1 = (1-dx)I(i,j)+dxI(i+1,j)
                    double B1 = (1 - dx)*A + dx*B; 
                    //B2 = (1-dx)I(i,j+1)+dxI(i+1,j+1)
                    double B2 = (1 - dx)*D + dx*C; 
                    //I(x,y)=B=(1-dy)B1+dyB2
                    int valueXY = (int) ((1 - dy)*B1 + dy*B2); 
                    result[y*nbRow+ x] = valueXY;
                }
            } 
        }
        raster.setPixels(0, 0, nbRow,nbCol , result);
        dest.setData(raster);
     }
    /**
     * resampling : bicubic interpolation method
     * @param dest <code>BufferedImage</code> the image to correct
     */
     public void bicubicInterpolation(BufferedImage dest){
        raster = (WritableRaster) dest.getData(); 
        for(int x=0; x< nbRow; x++){
            for(int y=0; y< nbCol ; y++){
                Point2D p = new Point2D(x,y);
                //retrieve the antecedant of the point
                computePosition(p); 
                /*
                 * if the point is contained in the image grid we find his 16 
                 * closet neighbors and we set the pixel
                 */
               //the closest neighbor to the left
                int i = (int) p.x;
                int j = (int) p.y;
                if(contains(p,nbRow, nbCol)){
                    int valueXY = computeIxy(i, j, p.x, p.y);
                    result[y*nbRow+ x] = valueXY;
                }
            } 
        }
        
        raster.setPixels(0, 0, nbRow,nbCol , result);
        dest.setData(raster);
     }
     /**
      * 
      * @param i an integer
      * @param j an integer
      * @param y a double
      * @return  a double
      */
    private double computeIiy(int i, int j, double y){
        double jPrim = UTIL.decimalPart(y);
        double IJ3 = getGrayValueAt(new Point2D(i, j+3)); //I(i,j+3)
        double IJ2 = getGrayValueAt(new Point2D(i, j+2)); //I(i,j+2)
        double IJ1 = getGrayValueAt(new Point2D(i, j+1)); //I(i,j+1)
        double IJ = getGrayValueAt(new Point2D(i, j)); //I(i,j)
        double fact1 = IJ3 - IJ2 + IJ1 - IJ;
        double fact2 = IJ2 - IJ3 - 2*IJ1 + 2*IJ;
        double fact3 = IJ2 - IJ;
        double res = jPrim*(jPrim*(jPrim*(fact1) + fact2 ) + fact3) + IJ1;
        
        return res;
    }
    /**
     * 
     * @param i an integer
     * @param j an integer
     * @param x a double
     * @param y a double
     * @return an integr
     */
    private int computeIxy(int i, int j, double x, double y) {
        double iPrim = UTIL.decimalPart(x);
        double IY = computeIiy(i, j, y); //I(i,y)
        double I1Y = computeIiy(i+1, j, y); //I(i+1,y)
        double I2Y = computeIiy(i+2, j, y); //I(i+2,y)
        double I3Y = computeIiy(i+3, j, y); //I(i+3,y)
        double fact1 = I3Y - I2Y + I1Y - IY;
        double fact2 = I2Y - I3Y - 2*I1Y + 2*IY;
        double fact3 = I2Y - IY;
        double res = iPrim*(iPrim*(iPrim*(fact1) + fact2) + fact3) + I1Y;
        
        return (int) res;
    }
}