
package oumar.Utilitaires;

/**
 * <b>Description:</b><i>Objet renvoye au listener lors de la fermeture d'un onglet</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class CloseTabEvent {
    private MyTabbedPane tabbedPane;
    private int tabIndex;
    
    public CloseTabEvent (MyTabbedPane tabbedPane, int tabIndex) {
        this.tabbedPane = tabbedPane;
        this.tabIndex = tabIndex;
    }

    
    /**
     * 
     * @return l'index de l'onglet clique
     */
    public int getTabIndex () {
        return this.tabIndex;
    }
    
    /**
     * 
     * @return le tabbedPane qui a subit le clique
     */
    public MyTabbedPane getTabbedPane () {
        return this.tabbedPane;
    }
    
}
