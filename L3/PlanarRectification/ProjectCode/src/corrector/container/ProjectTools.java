

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
import javax.swing.JPanel;

/** 
 * <p>Title : ProjectTools </p>
 * <p>description</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class ProjectTools extends JPanel{
    Project project;
    
    List<Observer> listObserver  = new ArrayList<Observer>();
    /**
     * 
     * @param project the project
     */
    public ProjectTools(Project project){
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
        ProjectButton createFig = new ProjectButton(new 
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
        add(createFig);
        ProjectButton moveFig = new ProjectButton(new 
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
        add(moveFig);
        ProjectButton resizeFig = new ProjectButton(new 
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
        add(resizeFig);
        ProjectButton deleteFig = new ProjectButton(new 
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
        add(deleteFig); 
    }
}
