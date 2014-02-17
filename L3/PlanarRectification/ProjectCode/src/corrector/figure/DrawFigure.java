

package corrector.figure;


import corrector.figure.shape.Rectangle;
import corrector.util.Point2D;
import corrector.util.Point2D.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/** 
 * <p>Title : DrawFigure </p>
 * <p>this class is used to paint a figure on a panel</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class DrawFigure{
    /**
     * points of the figure to draw
     */
    public Point2D[] points = null;
    /**
     * the panel
     */
    private JPanel panel;
    /**
     * the figure
     */
    private Figure figure;
    /**
     * the manipulator
     */
    protected FigureManipulator manip;
    /**
     * 
     * @return panel the panel 
     */
    public JPanel getPanel(){
        return panel;
    }
    /**
     * 
     * @return figure the Figure
     */
    public Figure getFigure(){
        return figure;
    }
    /**
     * 
     * @param figure the figure to set
     */
    public void setFigure(Figure figure){
        this.figure = figure;
    }
    /**
     * 
     * @return manip the figure manipulator
     */
    public FigureManipulator getManip(){
        return manip;
    }
    /**
     * Constructor
     */
    public DrawFigure(JPanel panel){
        this.panel = panel;
        manip = new FigureManipulator();
    }
    /**
     * method to add the figuure on the panel
     * @param f the figure
     */
    public void add(Figure f){
        if (f != null ){
            figure = f;
            panel.addMouseMotionListener(manip);
            panel.addMouseListener(manip);
            figure.select();
        }
        panel.repaint();
    } 
    /**
     * 
     * @return boolean true if the figure exist else false
     */
    public boolean isFigureSet(){
        return figure != null;
    }
    /**
     * this method draw the figure on the panel, it's called in the 
     * paintComponent method of the component that displays the panel.
     * @param g a graphics
     */
    public void drawFigure(Graphics g){
        if(figure != null)
        figure.display(g);
    }    
    /**
     * method used to delete the figure
     */
    public void delete(){
        figure = null;
        points = null;
        panel.removeMouseListener(manip);
        panel.removeMouseMotionListener(manip);
        panel.repaint();
    }
/** 
 * <p>Title : FigureManipulator </p>
 * <p>Inner class that translates the user's actions on a figure</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
    public class FigureManipulator extends MouseInputAdapter {
        public static final int TRANSLATION = 1;
        public static final int RESIZING = 2;
        public static final int SELECTION = 3;
        /**
         * Abscissa represents the last point stored by a mouse click.
         */
        private int lastX;
        /**
         * Ordinate represents the last point stored by a mouse click.
         */
        private int lastY;
        /**
         * Represents a choice of manipulation
         * 1-translation 
         * 2-resizing
         * 3-selection
         */
        private int manip;
        /**
         * default constructor
         */
        public FigureManipulator(){
        }
        /**
         * 
         * @param manip the manipulation to set
         */
        public void setManip(int manip){
            this.manip = manip;
        }
        /**
         * Method that reflects the action of the mouse when a button is pressed
         * and saves the coordinates of the mouse click if it is a left click
         * @param e an event
         */
        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e) && isFigureSet()) {
                lastX = e.getX();
                lastY = e.getY();
                if (figure.contains(e.getX(), e.getY())) {
                	figure.select();
                }else{
                	figure.deselect();
                }
                panel.repaint();
            }
        }
        /**
         * Method that reflects the action of the mouse when it moves with a 
         * button pressed
         * @param e an event
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e) && isFigureSet()) {
                if (figure.isSelected() && (manip > 0 || manip < SELECTION) ){
                    int indexPoint = 0;
                    Rectangle r = null;
                    for (int i = 0; i < figure.array.length; i++) {
                        indexPoint = i;
                        r = new Rectangle(figure.array[i]);
                        if (!r.contains(e.getX(), e.getY())) r = null;
                        else break;
                    }
                    if (manip == TRANSLATION){//translation
                        figure.translation(e.getX() - lastX, e.getY() - lastY);
                        figure.select();
                        panel.repaint();
                        lastX = e.getX();
                        lastY = e.getY();
                    }
                    if (manip == RESIZING){//resizing
                        if ((r != null && r.contains(e.getX(), e.getY() ))){
                            if (figure instanceof Rectangle){
                                // for the rectangle: two displacements to 
                                //manage storage according to the point selected
                                if ((indexPoint == 0 )){
                                    figure.array[indexPoint].translation(
                                            e.getX() - lastX, e.getY() - lastY);
                                    figure.array[3].translation(
                                            e.getX() - lastX , 0);
                                    figure.array[1].translation(
                                            0 , e.getY() - lastY);
                                }else if ((indexPoint == 2 )){
                                    figure.array[indexPoint].translation(
                                            e.getX() - lastX, e.getY() - lastY);
                                    figure.array[3].translation(
                                            0 , e.getY() - lastY);
                                    figure.array[1].translation(
                                            e.getX() - lastX , 0);
                                }
                                lastX = e.getX();
                                lastY = e.getY();
                                panel.repaint();
                            }else{
                                figure.array[indexPoint].translation(
                                        e.getX() - lastX, e.getY() - lastY);
                                lastX = e.getX();
                                lastY = e.getY();
                                panel.repaint();
                            }  
                        }
                    }
                }
                points = figure.array;
            }  
        }
    }
 /**  
 * <p>Title : FigureMaker </p>
 * <p>Inner class that create a figure</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
 public class FigureMaker extends MouseInputAdapter {
    /**
     * a figure
     */
    private Figure fc;
    /**
     * Attribute that stores the number of points of mouse click during 
     * manufacture of the figure
     */
    private int nbPointsClick;
    /**
     * Array that contains the recorded points for the construction
     */
    public Point[] pointEntry;

     /**
     * Shows if a point of a figure has been set.
     */
    private boolean sourisPressed;
    /**
     * constructor that initializes internal instance attributes.
     * @param f the figure to create
     */
    public FigureMaker(Figure f){   	
        if(f != null){
            fc = f;
            nbPointsClick = 0;
            pointEntry = new Point2D.Point[f.nbPoints()];
            points = new Point2D[f.nbPoints()];
            sourisPressed = false;
        }
    }    
    /**
     * method called when the mouse is entered
     * @param e an event
     */
        @Override
    public void mouseEntered  (MouseEvent e) {
        if (!sourisPressed){
            Graphics g = ((JPanel)e.getSource()).getGraphics();
            int x=(((JPanel)(e.getSource())).getSize().width)/2-60;
            g.setColor(Color.red);
            g.drawString("Definissez "+fc.nbPoints()+" points de saisis",x,12);
        }
    }
    /**
     * method called when the mouse is existed
     * @param e an event
     */
        @Override
    public void mouseExited (MouseEvent e) { 
        if (!sourisPressed){
            panel.repaint();
        }
    }
    /**
     * method called when the mouse button is released
     * @param e an event
     */
        @Override
    public void mouseReleased(MouseEvent e){
        if( fc != null){
            Graphics g = ((JPanel)e.getSource()).getGraphics();
            if(nbPointsClick == 2)
                g.drawLine(pointEntry[0].x, pointEntry[0].y, 
                        pointEntry[1].x, pointEntry[1].y);
            else if(nbPointsClick == 3){
                g.drawLine(pointEntry[0].x, pointEntry[0].y, 
                        pointEntry[1].x, pointEntry[1].y);
                g.drawLine(pointEntry[1].x, pointEntry[1].y,
                        pointEntry[2].x, pointEntry[2].y);
            }
        }
    }
        @Override
    public void mouseMoved(MouseEvent e) {
        if (fc != null) {
            Graphics g = ((JPanel)e.getSource()).getGraphics();
            if(nbPointsClick == 1){
            	g.drawLine(pointEntry[0].x, pointEntry[0].y, 
                        e.getX(), e.getY());
            	panel.repaint();
            }
            if(nbPointsClick == 2){
            	g.drawLine(pointEntry[0].x, pointEntry[0].y, 
                        pointEntry[1].x, pointEntry[1].y);
                g.drawLine(pointEntry[1].x, pointEntry[1].y,
                        e.getX(), e.getY());
                panel.repaint();
            }
            else if(nbPointsClick == 3){
                g.drawLine(pointEntry[0].x, pointEntry[0].y, 
                        pointEntry[1].x, pointEntry[1].y);
                g.drawLine(pointEntry[1].x, pointEntry[1].y,
                        pointEntry[2].x, pointEntry[2].y);
                g.drawLine(pointEntry[2].x, pointEntry[2].y,
                        e.getX(), e.getY());
                panel.repaint();
            }
        }
    }
    /**
     * Method that reflects the action of the mouse during the construction of 
     * the figure by storing the points clicked on certain conditions.
     * @param e an event
     */
        @Override
    public void mousePressed(MouseEvent e) {
            Point2D p = new Point2D();
        if (SwingUtilities.isLeftMouseButton(e)){
            if (fc != null){
                if(nbPointsClick < pointEntry.length ) {
                    pointEntry[nbPointsClick] =  p.new Point(e.getX() , e.getY() );
                    nbPointsClick++;                    
                    if (nbPointsClick == pointEntry.length){
                        points = pointEntry;
                        fc.changePoints( pointEntry );
                        add(fc);
                        panel.repaint();
                        ((JComponent)e.getSource()).removeMouseListener(this);     
                    }
                }
            }
            sourisPressed = true;
        }
    }
}
}

