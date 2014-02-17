
package corrector.container;

import corrector.figure.DrawFigure;
import corrector.figure.Figure;
import corrector.util.Zoom;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/** 
 * <p>Title : ImagePanel </p>
 * <p>this class display the image in the panel with the zoom effect</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class ImagePanel extends JPanel{
    private BufferedImage image; //the image to draw
    private Zoom zoom; //the zoomed
    private DrawFigure drawFig; //the figure drawer
    protected JScrollPane scroll; //the scroll bar
    /**
     * 
     * @param f the figure to set
     */
    public void setDrawFigure(Figure f){
         drawFig.setFigure(f);
    }
    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }
    /**
     * 
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
        scroll.doLayout();
        doLayout();
    }
    /**
     * 
     * @return zoom the zoom used
     */
    public Zoom getZoom(){
        return zoom;
    }
    /**
     * 
     * @return JScrollPane
     */
    public JScrollPane getScroll(){
    	return scroll;
    }
    /**
     * 
     * @return drawFig the figure drawer 
     */
    public DrawFigure getDrawFig(){
        return drawFig;
    }
    /**
     * Constructor of the image Container
     * @param image the buffered image
     */
    public ImagePanel(BufferedImage image){
        super();
        this.image = image;
        zoom = new Zoom(this);
        drawFig = new DrawFigure(this);
        setBackground(Color.white);
        scroll = new JScrollPane(this);
        scroll.setAutoscrolls(true);
        addMouseWheelListener(new WheelZoom());
    }
    /**
     * default constructor
     */
    public ImagePanel(){
        super();
    }
    /**
     * 
     * @return boolean true if the image exist else false
     */
    public boolean isImageSet(){
        return image != null;
    }
    /**
    * This method applies the zoom in
    */
    public void zoomIn(){
        if(isImageSet()){
            getZoom().zoomIn();
            getZoom().adjustLayout(scroll);
        }
    }
    /**
    * This method applies the zoom out
    */
    public void zoomOut(){
        if(isImageSet()){
            getZoom().zoomOut();
            getZoom().adjustLayout(scroll);
        }  
    }
    /**
    * This method restores the zoom
    */
    public void zoomOrig(){
        if(isImageSet()){
            getZoom().originalSize();
            getZoom().adjustLayout(scroll);
        }
    }
    /**
     * This method is overridden to return the preferred size 
     * which will be the width and height of the image plus 
     * the zoomed width width and height. 
     * while zooming out the zoomed width and height is negative
     * @return the dimension of the panel
     */
    @Override
    public Dimension getPreferredSize(){
        return new Dimension((int)
                (image.getWidth(this) + 
                (image.getWidth(this) *(zoom.getZoomFactor() - 1))),
                (int)(image.getHeight(this) + 
                (image.getHeight(this) *(zoom.getZoomFactor() -1))));
        
    }
    /**
     * this method paint the graphics (the image, the selection area) on the
     * panel
     * @param g a graphics
     */
    @Override
    public void paintComponent(Graphics g){
        Graphics2D ourGraphics = (Graphics2D) g;
	// save the original transform so that we can restore
	// it later
	AffineTransform saveTransform = ourGraphics.getTransform();

	// blank the screen. If we do not call super.paintComponent, then
	// we need to blank it ourselves
	ourGraphics.setColor(Color.WHITE);
	ourGraphics.fillRect(0, 0, getWidth(), getHeight());
			
	// We need to add new transforms to the existing
	// transform, rather than creating a new transform from scratch.
	// If we create a transform from scratch, we will
	// will start from the upper left of a JFrame, 
	// rather than from the upper left of our component
	AffineTransform at = new AffineTransform(saveTransform);

	// The zooming transformation
	at.scale(zoom.getZoomFactor(), zoom.getZoomFactor());
	ourGraphics.setTransform(at);

	// draw the objects (our image)
	ourGraphics.setColor(Color.BLACK);
	ourGraphics.drawImage(image, 0, 0, this);
	    
	// make sure you restore the original transform or else the drawing
	// of borders and other components might be messed up
	ourGraphics.setTransform(saveTransform);
                   
        // draw the selection area
        drawFig.drawFigure(ourGraphics);
    }
    /**
     * this class allow users to use the mouse wheel for zoom effect
     */
    private class WheelZoom implements MouseWheelListener{

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int direction = e.getWheelRotation();
            if(direction < 0){
                //top
                zoomIn();
            }else{
                //bottom
                zoomOut();
            }
        } 
    }
}
