package oumar.Gui;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

		
		
/**
* <b>Description:</b><i>cette classe est utile pour definir une nouvelle facon de dessiner les composants dans les cellules.
* Nous allons dire a notre tableau que la valeur qu'il a dans telle ou telle cellule est un composant 
* (bouton,checkbox ou autre).</i>
* <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
* <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
* @author D.Alpha Oumar Binta
*/
public class tableCellRender extends DefaultTableCellRenderer {
		
	public Component getTableCellRendererComponent(JTable table,
		Object value, boolean isSelected, boolean hasFocus, int row,int column) {
			//Si la valeur de la cellule est un JButton, on transtype notre valeur
			if (value instanceof JButton){
				return (JButton) value;
				}
			else if (value instanceof JCheckBox){
				return (JCheckBox) value;
				}
			else
			return this;
	}
}
