

package corrector.gui;


import corrector.container.*;
import corrector.project.Project;
import corrector.treatement.ImageCorrector;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import javax.swing.JFrame;

/** 
 * <p>Title : MainFrame </p>
 * <p>this class display the main frame of the application</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class MainFrame extends JFrame{
    public static final String IMAGE_TOOL_PATH = "Images/ToolBar/";
    public static final String IMAGE_TOOL = "Images/Tools/";
    public static final String IMAGE_GIF = "Images/Gif/";
    public static final String SOFTWARE_NAME = "IMAGE CORRECTOR";
    /**
     * the image
     */
    private ImageCorrector image = new ImageCorrector();
    /**
     * the image panel
     */
    private ImagePanel panel = new ImagePanel();
    /**
     * 
     * @return the image panel
     */
    public ImagePanel getPanel(){
        return panel;
    }
    /**
     * the tools 
     */
    private ProjectTools tools;
    /**
     * @return the image
     */
    public ImageCorrector getImageCorrector(){
        return image;
    }
    /**
     * the menu bar
     */
    private ProjectMenuBar menu;
    /**
     * the content pane
     */
    private Displayer content;
    /**
     * the tool bar
     */
    private ProjectToolBar toolBar;
    /**
     * 
     * @return the displayer
     */
    public Displayer getDisplayer(){
        return content;
    }
    /**
     * Constructor of the main frame
     * @param title the title of the frame
     */
    public MainFrame(String title){
        super(title);
        initComponents();        
    }
    /**
     * method to init the component of the application
     */
    private void initComponents(){
        Project p = new Project(this);       
        
        content = new Displayer() {
            	@Override
                public void update(Observable arg0, Object arg1) {
                    if(arg0 instanceof ImageCorrector){
			ImageCorrector img = (ImageCorrector) arg1;
			if(img != null){
                           panel = new ImagePanel(img.getImage());
                           setPanel(panel);
                           arg0.deleteObserver(this);
                        }
		}
	}
        };
        image.addObserver(content);
        toolBar = new ProjectToolBar(p);
        menu = new ProjectMenuBar(p);
        tools = new ProjectTools(p);
        
        //adding the observers to the observable
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(menu);
        
        //the screen size
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        //the height for content and tools
        int heightComponent = screenDim.height-161;
        
        toolBar.setPreferredSize(new Dimension(screenDim.width-21,25));
        content.getContent().setPreferredSize(new Dimension(screenDim.width-162,heightComponent));
        tools.setPreferredSize(new Dimension(123,heightComponent));
        
        setPreferredSize(new Dimension(screenDim.width-5,screenDim.height-37));
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(content.getContent(), BorderLayout.WEST);
        add(tools, BorderLayout.EAST);

        pack();   
    }
}
