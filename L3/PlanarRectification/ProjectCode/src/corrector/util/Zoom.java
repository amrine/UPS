/*
 * Copyright 2012 
 * http://www.oumar.geek-trick.com
 */

package corrector.util;

import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

/** 
 * Projet: IMAGEFILTER
 * <p>Title : Zoom </p>
 * <p>description</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class Zoom{
    private static final double ZOOMPERCENTAGE = 20.0; //the zoom percentage
    private static final double ORIGINAL = 1.0;
    private static final double LIMITE = 1.0;
    
    private double zoomFactor = ORIGINAL; //the zoom
    private double zoomPercentage; //the zoom percentage
    
    protected JComponent pan; //the image panel
    /**
     * This method returns the currently  zoomed percentage
     * @return zoom the zoomed percentage
     */
    public double getZoomedTo(){
        return zoomFactor*100;
    }
    /**
     * 
     * @return zoomFactor the zoom factor
     */
    public double getZoomFactor(){
        return zoomFactor;
    }
    /**
     * Sets the new zoomed percentage  
     * @param zoomPercentage 
     */
    private void setZoomPercentage(double zoomPercentage){
        this.zoomPercentage = zoomPercentage/100;
    }
    /**
     * This method set the image to the original size 
     * by setting the zoom factor to 1. i.e. 100%
     */
    public void originalSize(){
        zoomFactor = ORIGINAL;
        pan.repaint();
    }
    /**
     * This method increments the zoom factor with the zoom percentage, 
     * to create the zoom in effect
     */
    public void zoomIn(){
        zoomFactor += zoomPercentage;
        pan.repaint();
    }
    /**
     * This method decrements the zoom factor with the zoom percentage, 
     * to create the zoom out effect
     */
    public void zoomOut(){
        zoomFactor -= zoomPercentage;
        if(zoomFactor < zoomPercentage){
            if(zoomPercentage > LIMITE)
                zoomFactor = ORIGINAL;
            else
                zoomIn();
        }else{
            pan.repaint();
        }
    }
    /**
     * Constructor of zoom
     * @param pan the image panel
     */
    public Zoom(JComponent pan){
        this.pan = pan;
        setZoomPercentage(ZOOMPERCENTAGE);
    }
    /**
     * This method applies the zoom effect on the panel
     * @param g the graphics 2D
     */
    public void zoomEffect(Graphics2D g){
        //scale the graphics to get the zoom effect 
        g.scale(zoomFactor, zoomFactor);
    }
    /**
     * This method adjusts the layout after zooming
     */
    public void adjustLayout(JScrollPane scroll){
        scroll.doLayout();
        pan.doLayout();
    }
}
