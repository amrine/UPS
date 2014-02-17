
package corrector.project;

import corrector.figure.DrawFigure;
import corrector.figure.Figure;
import corrector.figure.shape.Quadrilateral;
import corrector.gui.MainFrame;
import corrector.treatement.DialogInfo;
import corrector.treatement.Homography;
import corrector.util.FileType;
import corrector.util.Point2D;
import corrector.util.UTIL;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;

/** 
 * <p>Title : Project </p>
 * <p>class to invoke the methods defined (the actions between an user and the 
 * application)</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class Project {
    /**
     * the main frame
     */
    private MainFrame main;
    /**
     * points selected by the user
     */
    private Point2D[] points = null;
    /**
     * Project Constructor
     * @param mainFrame 
     */
    public Project(MainFrame mainFrame){
        this.main = mainFrame;
    }
    /**
     * 
     * @return the mainFrame
     */
    public MainFrame getMain(){
        return main;
    }
    /**
     * method called to open an image
     */
    public void openFile(){
        JFileChooser chooser = new JFileChooser();
        FileType fType = new FileType(new String[]{"gif","tif","jpeg",
            "jpg","tiff", "png"},"les fichiers image (*.gif, *.tiff,*.jpeg,"
                + " *.png)");  
        chooser.addChoosableFileFilter(fType);
        int res = chooser.showOpenDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            if(fType.accept(file)){
            	main.getImageCorrector().loadImage(file.getPath());
            }else{
                UTIL.warningMessage("Ce type de fichier n'est pas "
                        + "pris en charge", file.getParent());
            }
        }else{
            UTIL.warningMessage("Vous avez annuler l'operation", 
                    MainFrame.SOFTWARE_NAME);
        }
    }
    /**
     * method called to close the image
     */
    public void closeFile() {
        main.getImageCorrector().setImage(null);
        main.getDisplayer().getContent().remove(
        main.getDisplayer().getContent().getSelectedIndex());
    }
    /**
     * method called to create a figure
     */
    public void createFig() {
        DrawFigure.FigureMaker ff; 
        Quadrilateral f = new Quadrilateral();
        main.getPanel().setDrawFigure(f);
        DrawFigure drawer = main.getPanel().getDrawFig();
        Figure fig = main.getPanel().getDrawFig().getFigure();
        if (fig != null ) {
            ff = drawer.new FigureMaker(fig);
            drawer.getPanel().addMouseListener( ff );
            drawer.getPanel().addMouseMotionListener( ff );
        }
        
    }
    /**
     * method called for deleting a figure
     */
    public void deleteFig() {
        Figure fig = main.getPanel().getDrawFig().getFigure();
        if(fig == null){
            UTIL.errorMessage("the figure does not exist","Deleting figure");
        }else if(fig.isSelected()){
            DrawFigure drawer = main.getPanel().getDrawFig();
            drawer.getManip().setManip(DrawFigure.FigureManipulator.SELECTION);
            drawer.delete();
        }else{
            UTIL.warningMessage("Please select the figure first", 
                    "Deleting figure");
        }
    }
    /**
     * method called for moving a figure
     */
    public void moveFig() {
       Figure fig = main.getPanel().getDrawFig().getFigure();
       if(fig == null){
           UTIL.errorMessage("the figure does not exist","Moving figure");
       }else if(fig.isSelected()){
           DrawFigure drawer = main.getPanel().getDrawFig();
           drawer.getManip().setManip(DrawFigure.FigureManipulator.TRANSLATION);    
       }else{
           UTIL.warningMessage("Please select the figure first", 
                   "Moving figure");
       }  
    }
    /**
     * method called to resize a figure
     */
    public void resizeFig() {
        Figure fig = main.getPanel().getDrawFig().getFigure();
       if(fig == null){
           UTIL.errorMessage("the figure does not exist","Resizing figure");
       }else if(main.getPanel().getDrawFig().getFigure().isSelected()){
           DrawFigure drawer = main.getPanel().getDrawFig();
            drawer.getManip().setManip(DrawFigure.FigureManipulator.RESIZING);
       }else{
           UTIL.warningMessage("Please select the figure first", 
                   "Resizing figure");
       }
         
    }
    /**
     * method called to zoom in
     */
    public void zoomIn() {
        main.getPanel().zoomIn();
    }
    /**
     * method called to restore the zoom
     */
    public void zoomOrg() {
        main.getPanel().zoomOrig();
    }
    /**
     * method called to zoom out
     */
    public void zoomOut() {
        main.getPanel().zoomOut();
    }
    /**
     * method called to do a left rotation
     */
    public void leftRotation() {
        main.getImageCorrector().rotation(90);
        main.getPanel().setImage(main.getImageCorrector().getImage());
    }
    /**
     * method called to do a right rotation
     */
    public void rightRotation() {
        main.getImageCorrector().rotation(-90); 
        main.getPanel().setImage(main.getImageCorrector().getImage());
    }
    /**
     * method called to do the plane rectification
     */
    public void planarRectification() {
        if(applyZoom()){
            /* the the source image */
            BufferedImage image = main.getImageCorrector().getImage();
            /* Corrected image */
            BufferedImage corrected =new BufferedImage(image.getWidth(), 
                    image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

            Homography H = new Homography(image);
            /**
            * if the four points of the object are selected, then we open
            * the dialog box to allow the user to enter information needed to 
            * correct and for choosing which method to use.
            */
            /* dialog box*/
            DialogInfo data = new DialogInfo(main, "Rectification de l'image", true);
            if(data.getRatio() != 0){
                /* if the user choose the first choice we correct the image with
                 the same size of source image else with size of the object */
                if(data.getTypeOfRectification() == 0)
                    H.rectifyWithTheSameSizeOfImage(points, data.getRatio());
                else H.rectifyWithTheSameSizeOfObject(points);
                
                /* the interpolation to apply*/
                if(data.getMethod() == 0){
                    H.nearestNeighbor(corrected);
                    main.getImageCorrector().setImage(corrected);
                }else if(data.getMethod() == 1){
                    H.billinearInterpolation(corrected);
                    main.getImageCorrector().setImage(corrected);
                }else if(data.getMethod() == 2){
                    H.bicubicInterpolation(corrected);
                    main.getImageCorrector().setImage(corrected);
                }
                deleteFig();
                main.getPanel().setImage(corrected);
            }
        }
    }
    /**
     * method to retrieve the real points location
     * @return p with zoom effect
     */
    private boolean applyZoom(){
        double zoom = main.getPanel().getZoom().getZoomFactor();
        points = main.getPanel().getDrawFig().points;
        if(points == null){
            UTIL.errorMessage("selectionner les points","rectifier figure");
        }else{
            for(int i=0; i < points.length; i++){
                points[i].setLocation(points[i].x/zoom, points[i].y/zoom);
            }
            return true;
        }
        return false;
    }
    /**
     * method called to close the application
     */
    public void closeApp(){
        System.exit(0);
    }
     /**
     * method called to save an image
     */
    public void saveFile(){
        JFileChooser chooser = new JFileChooser();
        
        int res = chooser.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            String format;
            String ext;
            String fileName = file.getName() ;
            if(fileName.contains(".")){
                ext = fileName.substring(fileName.lastIndexOf(".")+1);
                format = ext;
            }else {
                format = "jpeg";
                fileName += "."+format;
            }
            main.getImageCorrector().saveAs(fileName, format);
           
        }else{
            UTIL.warningMessage("Vous avez annuler l'operation", 
                    MainFrame.SOFTWARE_NAME);
        }
    }
    /*
     * method to tranform the image in gray
     */
    public void imageToGray(){
        try {
            main.getImageCorrector().setImage((BufferedImage) UTIL.transform(
                    main.getImageCorrector().getImage()));
            main.getPanel().setImage(main.getImageCorrector().getImage());
        } catch (Exception ex) {}
        
    }
    /**
     * method to restore the image
     */
    public void restoreImage(){
        main.getImageCorrector().restore();
        main.getPanel().setImage(main.getImageCorrector().getImage());
    }
}

