

package corrector.treatement;

import corrector.util.UTIL;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/** 
 * <p>Title : ImageCorrector </p>
 * <p>the main class of the image </p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class ImageCorrector extends Observable{
    private BufferedImage image = null, source=null; // the image
   /**
    * method to restore the image
    */
    public void restore(){
        image = source;
    }
    /**
     * default constructor
     */
    public ImageCorrector() {
        super();
    }
    /**
     * this method return the buffered image
     * @return image the buffered image
     */
    public BufferedImage getImage(){
        return image;
    }    
    /**
     * this method load the image
     * @param imagePath the image path
     */
    public void loadImage(String imagePath){
        /*
         * if the the image is loaded we have a the buffered image else null
         */
        try {
            image = ImageIO.read(new File(imagePath));
            source = image;
            setChanged();
            notifyObservers(this);
        } catch (IOException ex) {
            Logger.getLogger(ImageCorrector.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }
    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
	setChanged();
        notifyObservers(this);
    }
    /**
    * rotate the image
    * @param angle the rotation agnle
    * @throws IOException 
    */
   public void rotation(int angle){
	// cosinus and sinus of the rotation angle
	double cos = Math.cos(Math.PI*angle/180.);
	double sin = Math.sin(Math.PI*angle/180.);
	int L = image.getWidth(), H = image.getHeight();
 
	// Center of the original image
	int Xc = L/2, Yc = H/2;
 
	// dimension of the new image
	int L2 = (int) (L*Math.abs(cos)+H*Math.abs(sin));
	int H2 = (int) (L*Math.abs(sin)+H*Math.abs(cos));
 
	// center of the new image
	int Xc2 = L2/2, Yc2 = H2/2;
 
	BufferedImage  imro = new BufferedImage(L2,H2,image.getType());
	for (int y2=0;y2<H2;y2++) {
		for (int x2=0;x2<L2;x2++) {
                    int x = (int) Math.round( Xc + (x2-Xc2)*cos - (y2-Yc2)*sin);
                    int y = (int) Math.round( Yc + (x2-Xc2)*sin + (y2-Yc2)*cos);
                    if (x>=0 && x<L && y>=0 && y<H)
                        imro.setRGB(x2, y2, image.getRGB(x,y));
                }
	}
        setImage(imro);
   }
   /**
     * method to save the image in jpeg format
     * @param filename String the file (output name)
     */
   public void saveAs(String filename, String format){
        try {
            ImageIO.write(image,format,new File(filename));
            UTIL.informationMessage("Image sauvegardé : "+filename, 
                    "Enregistrer");
        } catch (IOException ex) {
            UTIL.informationMessage("Image non sauvegardé : "+filename, 
                    "Enregistrer");
        }
   }
}

