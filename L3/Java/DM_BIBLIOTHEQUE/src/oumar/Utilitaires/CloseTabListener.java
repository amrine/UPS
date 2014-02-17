
package oumar.Utilitaires;

import java.util.EventListener;

/**
 * <b>Description:</b><i>Listener pour catcher les evenements de fermeture d'onglet</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public interface CloseTabListener extends EventListener {
    /**
     * Methode appeler lorsque que la croix est clique dans un MyTabbedPanePlus
     * @param event
     */
    public void closeAction (CloseTabEvent event);
}
