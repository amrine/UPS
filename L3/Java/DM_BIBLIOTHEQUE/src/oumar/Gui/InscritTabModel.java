/**
 * 
 */
package oumar.Gui;

import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import oumar.Structure.Inscrit;

/**
 * <b>Description:</b><i>Cette classe herite de classe AbstractTableModel
 * elle sert a visualiser la liste des inscrits de la bibliotheque</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class InscritTabModel extends AbstractTableModel {
	List<Inscrit> data;
	String[] title;
	
	/**
	 * @param data
	 * @param title
	 */
	public InscritTabModel(List<Inscrit> data, String[] title) {
		this.data = data;
		this.title = title;
		
	}
	
	/**
	 * @return le nombre de ligne
	 */
	@Override
	public int getRowCount() {
		return data.size();
	}
	/**
	 * @return le nombre de colonne
	 */
	@Override
	public int getColumnCount() {
		return title.length;
	}
	
	/**
	 * @return l'objet situe a cet index
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0 : return rowIndex+1;
		case 1 : return data.get(rowIndex).getNom();
		case 2 : return data.get(rowIndex).getPrenom();
		case 3 : return new JCheckBox(""+data.get(rowIndex).getSex(),true);
		case 4 : return data.get(rowIndex).adresseToString();
		case 5 : return data.get(rowIndex).getTel();
		case 6 : return data.get(rowIndex).getAge();
		case 7 : return data.get(rowIndex).getEmprunt().size();
		default: return data.get(rowIndex);
		}
	}

	/**
	 * 
	 * @param i
	 * @param showFen (afficher la boite de dialogue)
	 * methode d'ajout d'un inscrit a la liste des inscits
	 */
	public void ajouterInscritAuTableau(Inscrit i,boolean showFen){
		if(i!=null){
			data.add(i);
			if(showFen)
				JOptionPane.showMessageDialog(null, "Votre inscription a la bibliotheque a ete effectuee avec succes\n" +
						"Vos informations sont les suivantes :\n" +
						i.toString()+"\n	A bientot", "Informations Ajout Personnage", JOptionPane.INFORMATION_MESSAGE);
		    fireTableDataChanged();
		}else{
			JOptionPane.showMessageDialog(null, "Votre inscription a la bibliotheque est annule\n	Merci", "Information Ajout Personnage", JOptionPane.WARNING_MESSAGE);
		} 
	}
	
	
	/**
	 * 
	 * @param key
	 */
	public void supprimerHumain(int key){
		/*on verifier si la cle existe
		 * si oui on supprimer l'inscrit*/
		if(data.size()>key){
			if(!data.get(key).getEmprunt().isEmpty())
				JOptionPane.showMessageDialog(null,"Vous devez rendre les livres empruntes\navant de " +
						"pouvoir supprimer votre profil","Suppression Inscrit",JOptionPane.NO_OPTION);
			else{
				data.remove(key);
				JOptionPane.showMessageDialog(null, "Cette personne a ete supprime de la liste des inscrits\n" +
						"\n	A bientot si vous revenez", "Information Suppression Personnage", JOptionPane.INFORMATION_MESSAGE);
				fireTableDataChanged();
			}
		}
		else
		JOptionPane.showMessageDialog(null, "Erreur de suppression de cette personne\n", "Information Suppression Personnage", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * 
	 * @param key
	 * @param inscrit
	 */
	public void modifierHumain(int key,Inscrit inscrit){
		if(data.size()>key && inscrit!=null){
			data.get(key).modifierInscrit(inscrit);
			JOptionPane.showMessageDialog(null, "Les modifications ont ete effectue avec succes\n" +
					"\n	La Bibliotheque", "Information Modification Personnage", JOptionPane.INFORMATION_MESSAGE);
			fireTableDataChanged();
		}	
		else
			JOptionPane.showMessageDialog(null, "La modification est annulee\n", "Information Modification Personnage", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * @return the string titre de la colonne
	 */
	public String getColumnName(int col){
		return title[col];
	}
	
	/**
	 * Retourne la classe de la donnee de la colonne
	 * @param c numero de colonne
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
		}
	
	/**
	 * @return boolean
	 * true les cellules sont editables
	 * false non
	 */
	public boolean isCellEditable( int row, int col ){
		return false ;
	}
	

}
