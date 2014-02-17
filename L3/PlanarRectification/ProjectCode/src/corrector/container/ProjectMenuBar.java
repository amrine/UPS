

package corrector.container;

import corrector.component.ProjectMenuItem;
import corrector.gui.MainFrame;
import corrector.project.Project;
import corrector.treatement.ImageCorrector;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

/** 
 * <p>Title : ProjectMenuBar </p>
 * <p>this class display the menu bar</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class ProjectMenuBar extends JMenuBar{
    Project project;
    
    List<Observer> listObserver  = new ArrayList<Observer>();
    /**
     * 
     * @param project 
     */
    public ProjectMenuBar(Project project){
        super();
        this.project = project;
        initComponents();
        addObservers();
    }
    /**
     * this method add the list of obervers to the object observable
    */
    private void addObservers(){
    	for(Observer observer : listObserver)
             project.getMain().getImageCorrector().addObserver(observer);     
    }
    /**
     * method to init the component
     */
    private void initComponents() {
        JMenu file = new JMenu("Fichier");
        JMenu wiew = new JMenu("Affichage");
        JMenu image = new JMenu("Image");
        
        ProjectMenuItem open = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL_PATH+"openFile.png"),
                "ouvir une image", project, "openFile", true) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	project.getMain().getImageCorrector().addObserver(
                                project.getMain().getDisplayer());
                    	setEnabled(true);
                    }else {
                    	setEnabled(false);
                    }
                }
            }
        };
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
        listObserver.add(open);
        file.add(open);
        
        ProjectMenuItem close = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL_PATH+"closeFile.png"),
                "fermer l'image", project, "closeFile", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
        listObserver.add(close);
        file.add(close);
        ProjectMenuItem save = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL_PATH+"saveFile.png"),
                "sauvegarder l'image", project, "saveFile", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        listObserver.add(save);
        file.add(save);
        
        file.addSeparator();
        ProjectMenuItem quit = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL_PATH+"quit.jpg"),
                "Quitter", project, "closeApp", true) {

            @Override
            public void update(Observable o, Object arg) {}
        };
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        file.add(quit);
        
        ProjectMenuItem zoomIn = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL_PATH+"ZoomIn.png"),
                "Zoom avant", project, "zoomIn", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(zoomIn);
        wiew.add(zoomIn);
        
        ProjectMenuItem zoomOut = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL_PATH+"ZoomOut.png"),
                "Zoom arriere", project, "zoomOut", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(zoomOut);
        wiew.add(zoomOut);
        
        ProjectMenuItem zoomOrg = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL_PATH+"ZoomOrg.png"),
                "Image réelle", project, "zoomOrg", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(zoomOrg);
        wiew.add(zoomOrg);
        
        ProjectMenuItem rightRotation = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_GIF+"rightRotation.Gif"),
                "Rotation droite", project, "rightRotation", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(rightRotation);
        image.add(rightRotation);
        ProjectMenuItem leftRotation = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_GIF+"leftRotation.Gif"),
                "Rotation gauche", project, "leftRotation", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(leftRotation);
        image.add(leftRotation);
        ProjectMenuItem rectifierFig = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL+"Yes.png"),
                "rectifier l'image", project, "planarRectification", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(rectifierFig);
        image.add(rectifierFig);
        
        ProjectMenuItem gray = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_GIF+"gray.gif"),
                "transormer en niveau de gris", project, "imageToGray", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(gray);
        image.add(gray);
        ProjectMenuItem cancel = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_GIF+"restore.gif"),
                "retablir l'image d'origine", project, "restoreImage", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(cancel);
        image.add(cancel);
        image.addSeparator();
        ProjectMenuItem createFig = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL+"Formes.gif"),
                "creer une figure", project, "createFig", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(createFig);
        image.add(createFig);
        ProjectMenuItem moveFig = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL+"Deplacer.gif"),
                "deplacer une figure", project, "moveFig", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(moveFig);
        image.add(moveFig);
        ProjectMenuItem resizeFig = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL+"Resize.jpg"),
                "redimensionner une figure", project, "resizeFig", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(resizeFig);
        image.add(resizeFig);
        ProjectMenuItem deleteFig = new ProjectMenuItem(new 
                ImageIcon(MainFrame.IMAGE_TOOL+"Gomme.gif"),
                "supprimer une figure", project, "deleteFig", false) {

            @Override
            public void update(Observable o, Object arg) {
                if(o instanceof ImageCorrector){
                	ImageCorrector img = (ImageCorrector) arg;
                    if(img.getImage() == null){
                    	setEnabled(false);
                    }else {
                    	setEnabled(true);
                    }
                }
            }
        };
        listObserver.add(deleteFig);
        image.add(deleteFig);
        
        add(file);
        add(wiew);
        add(image);
    }
}
