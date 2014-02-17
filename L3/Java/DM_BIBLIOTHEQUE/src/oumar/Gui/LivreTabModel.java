/**
 * 
 */
package oumar.Gui;

import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import oumar.Structure.Inscrit;
import oumar.Structure.Livre;

/**
 * <b>Description:</b><i>Cette classe herite de classe AbstractTableModel
 * elle sert a visualiser la liste des livres de la bibliotheque</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class LivreTabModel extends AbstractTableModel {
	List<Livre> data;
	String[] title;
	
	/**
	 * @param data
	 * @param title
	 */
	public LivreTabModel(List<Livre> data, String[] title) {
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
		case 1 : return data.get(rowIndex).getTitre();
		case 2 : return data.get(rowIndex).getAuteur();
		case 3 : return new JCheckBox(""+data.get(rowIndex).getCategorie(),true);
		case 4 : return new JCheckBox(""+data.get(rowIndex).themeToString(),true);
		case 5 : return data.get(rowIndex).getNbrePage();
		case 6 : return new Boolean(data.get(rowIndex).getDisponible());
		default: return data.get(rowIndex);
		}
	}

	/**
	 * 
	 * @param i
	 * @param showFen (afficher la boite de dialogue)
	 * methode d'ajout d'un Livre a la liste des livres
	 */
	public void ajouterLivreAuTableau(Livre i,boolean showFen){
		if(i!=null){
			data.add(i);
			if(showFen)
				JOptionPane.showMessageDialog(null, "L'ajout du livre a la bibliotheque a ete effectuee avec succes\n" +
						"Les informations sont les suivantes :\n" +
						i.toString()+"\n", "Informations Ajout Livre", JOptionPane.INFORMATION_MESSAGE);
		    fireTableDataChanged();
		}else{
			JOptionPane.showMessageDialog(null, "L'ajout du livre a la bibliotheque est annule\n	Merci", "Information Ajout Livre", JOptionPane.WARNING_MESSAGE);
		} 
	}
	
	
	/**
	 * 
	 * @param key
	 */
	public void supprimerLivre(int key){
		/*on verifier si la cle existe
		 * si oui on supprimer le livre*/
		if(data.size()>key){
			data.remove(key);
			JOptionPane.showMessageDialog(null, "Ce livre a ete supprime de la liste des Livres\n" +
					"\n", "Information Suppression Livre", JOptionPane.INFORMATION_MESSAGE);
			fireTableDataChanged();
		}
		else
		JOptionPane.showMessageDialog(null, "Erreur de suppression du livre\n", "Information Suppression Livre", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * 
	 * @param key
	 * @param inscrit
	 */
	public void emprunterLivre(int key,List<Inscrit> inscrit){
		if(data.size()>key){
			/*
			 * on cree un tableau contenant le nom, le prenom et le code de l'inscrit
			 * on l'affiche dans un JOptionPane
			 * Apres avoir choisi la personne qui emprunte le livre, on retrouve son identifiant
			 * on met la disponibilte du livre a false pour ne pas qu'une autre personne puisse l'emprunter
			 * puis on ajoute l'identifiant du livre et son titre dans la liste des emprunts de la personne
			 */
			String[] nomInscrit = new String[inscrit.size()];
			String nom=null;
			for(int i=0;i<inscrit.size();i++){
				nomInscrit[i]=inscrit.get(i).getNom()+" "+inscrit.get(i).getPrenom()+" id="+i;
			}
			if(nomInscrit.length>0){
				nom= (String) JOptionPane.showInputDialog(null,"veuiller selectionner votre nom","Selection de la personne",
						JOptionPane.QUESTION_MESSAGE,null,nomInscrit,nomInscrit[0]);
			}
			
			if(nom!=null){
				int id=-1;
				//recherche de l'identifiant de la personne
				for(int i=0;i<nomInscrit.length;i++){
					if(nomInscrit[i].equals(nom)){
						id=i;
						break;
					}
				}
				//on rend le livre indisponible et on a l'ajoute a liste des emprunts de la personne	
				if(id>=0){
					data.get(key).setDisponible(false);
					inscrit.get(id).emprunter(key,data.get(key).getTitre());	
					fireTableDataChanged();
				}
				
			}	
		}
		else
		JOptionPane.showMessageDialog(null, "Erreur d'emprunt du livre\n", "Information Emprunt Livre", JOptionPane.ERROR_MESSAGE);
		
	}
	
	
	/**
	 * 
	 * @param key
	 * @param Livre
	 */
	public void modifierLivre(int key,Livre Livre){
		if(data.size()>key && Livre!=null){
			data.get(key).modifierLivre(Livre);
			JOptionPane.showMessageDialog(null, "Les modifications ont ete effectue avec succes\n" +
					"\n	La Bibliotheque", "Information Modification Livre", JOptionPane.INFORMATION_MESSAGE);
			fireTableDataChanged();
		}	
		else
			JOptionPane.showMessageDialog(null, "La modification est annulee\n", "Information Modification Livre", JOptionPane.ERROR_MESSAGE);
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
