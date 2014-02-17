

package corrector.container;



import corrector.component.ProjectButton;
import corrector.gui.MainFrame;
import corrector.project.Project;
import corrector.treatement.ImageCorrector;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

/** 
 * <p>Title : ProjectToolBar </p>
 * <p>this class display the tool bar</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class ProjectToolBar extends JToolBar{
    Project project;
    
    List<Observer> listObserver  = new ArrayList<Observer>();
    /**
     * 
     * @param project the main frame
     */
    public ProjectToolBar(Project project){
        super();
        this.project = project;
        setRollover(true);
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
        ProjectButton open = new ProjectButton(new 
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
        listObserver.add(open);
        add(open);
        ProjectButton save = new ProjectButton(new 
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
        listObserver.add(save);
        add(save);
        
        ProjectButton close = new ProjectButton(new 
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
        listObserver.add(close);
        add(close);
        
        ProjectButton zoomIn = new ProjectButton(new 
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
        add(zoomIn);
        
        ProjectButton zoomOut = new ProjectButton(new 
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
        add(zoomOut);
        
        ProjectButton zoomOrg = new ProjectButton(new 
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
        add(zoomOrg);
        ProjectButton rightRotation = new ProjectButton(new 
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
        add(rightRotation);
        ProjectButton leftRotation = new ProjectButton(new 
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
        add(leftRotation);
        ProjectButton gray = new ProjectButton(new 
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
        add(gray);
        ProjectButton cancel = new ProjectButton(new 
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
        add(cancel);
       
        ProjectButton correct = new ProjectButton(new 
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
        listObserver.add(correct);
        add(correct);
    }
}
