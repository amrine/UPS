

package corrector.container;


import java.util.Observer;
import javax.swing.JTabbedPane;

/** 
 * <p>Title : Displayer </p>
 * <p>abstract class that display an image panel</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public abstract class Displayer extends JTabbedPane implements Observer{
    /**
     * default constructor
     */
    public Displayer(){
        super();
    }
    /**
     * 
     * @return content the content
     */
    public JTabbedPane getContent(){
    	return this;
    }
    /**
     * this method add an image on the container
     * @param panel the image panel 
     */
    public void setPanel(ImagePanel panel){
    	add(panel.getScroll());
    }
}
