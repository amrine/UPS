/**
 * 
 */
package oumar.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import oumar.Structure.Inscrit;
import oumar.Structure.Livre;

/**
 * <b>Description:</b><i>cette classe heritiere de la classe Conteneur sert a afficher la description d'une personne inscrite 
 * a la bibliotheque</i>
 *  <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 *
 */
public class DescriptionInscrit extends Conteneur{
	private Inscrit inscrit;
	private List<Livre> lesLivres;
    private JCheckBox sexe;
    JLabel tel;
	JLabel code;
	private JLabel age;
    private JLabel labelPrenom,labelSexe,labelAdr,labelCode,labelCommune,labelTel;
    private JLabel image,adr,commune,prenom,nom;
    private JLabel labelNom,labelAge;
    private Dimension dimLabel,dimField;
    private JList liste;
    private JPopupMenu menuContextuel=new JPopupMenu();
    private JMenuItem rendreL=new JMenuItem("rendre ce livre",new ImageIcon("images/Erase.png"));
    
	public DescriptionInscrit(Dimension dim,Inscrit inscrit,List<Livre> lesLivres) {
		super(dim);
		this.inscrit=inscrit;
		this.lesLivres=lesLivres;
		initPanel();
	}
	

	
	
	public void initPanel(){
		
        labelNom = new JLabel("Nom :");
        nom = new JLabel(inscrit.getNom());
        labelAge = new JLabel("Age :");
        labelPrenom = new JLabel("Prenom :");
        prenom = new JLabel(inscrit.getPrenom());
        labelSexe = new JLabel("Sexe :");
        sexe = new JCheckBox(""+inscrit.getSex(),true);
        sexe.setEnabled(false);
        sexe.setBackground(Color.white);
        labelTel = new JLabel("Tel :");
        Image icon=new ImageIcon(inscrit.getImage()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        image = new JLabel(new ImageIcon(icon)) ;
        image.setPreferredSize(new Dimension(150,150));
        image.setBorder(BorderFactory.createLineBorder(Color.red));
        
        labelAdr = new JLabel("Adresse :");
        adr = new JLabel(inscrit.getAdresse()[0]);
        labelCode = new JLabel("Code Postale :");
        labelCommune = new JLabel("Ville :");
        commune = new JLabel(inscrit.getAdresse()[2]);
        dimLabel=new Dimension(90,20);
        dimField=new Dimension(150,20);
        
        
      	tel = new JLabel(inscrit.getTel());
    	code = new JLabel(inscrit.getAdresse()[1]);
    	age = new JLabel(inscrit.getAge());
    	
        tel.setFont(comics20);
        code.setFont(comics20);
        age.setFont(comics20);
        
        liste=new JList(inscrit.getEmprunt().toArray());
        
        JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		nom.setPreferredSize(dimField);
		labelNom.setPreferredSize(dimLabel);
		panNom.setLayout(new FlowLayout(FlowLayout.LEADING));
		panNom.add(labelNom);
		panNom.add(nom);
		
		JPanel panPrenom = new JPanel();
		panPrenom.setBackground(Color.white);
		prenom.setPreferredSize(dimField);
		labelPrenom.setPreferredSize(dimLabel);
		panPrenom.setLayout(new FlowLayout(FlowLayout.LEADING));
		panPrenom.add(labelPrenom);
		panPrenom.add(prenom);
		
		JPanel panSexe = new JPanel();
		panSexe.setBackground(Color.white);
		labelSexe.setPreferredSize(dimLabel);
		panSexe.setLayout(new FlowLayout(FlowLayout.LEADING));
		panSexe.add(labelSexe);
		panSexe.add(sexe);
		
		JPanel panTel = new JPanel();
		panTel.setBackground(Color.white);
		tel.setPreferredSize(dimField);
		labelTel.setPreferredSize(dimLabel);
		panTel.setLayout(new FlowLayout(FlowLayout.LEADING));
		panTel.add(labelTel);
		panTel.add(tel);
		
		JPanel panAge = new JPanel();
		panAge.setBackground(Color.white);
		age.setPreferredSize(dimField);
		labelAge.setPreferredSize(dimLabel);
		panAge.setLayout(new FlowLayout(FlowLayout.LEADING));
		panAge.add(labelAge);
		panAge.add(age);
    
		JPanel panImage = new JPanel();
		panImage.setBackground(Color.white);
		panImage.add(image);
		
	
		//panneau contenant les infos sur la personne
		JPanel id=new JPanel(new GridLayout(6,1));
		id.setBorder(BorderFactory.createTitledBorder("Identification de la personne"));
		id.setBackground(Color.white);
		id.add(panNom);
		id.add(panPrenom);
		id.add(panAge);
		id.add(panSexe);
		id.add(panTel);
		id.add(panImage);
		
		//domicile
		JPanel panAdr = new JPanel();
		panAdr.setBackground(Color.white);
		adr.setPreferredSize(dimField);
		labelAdr.setPreferredSize(dimLabel);
		panAdr.setLayout(new FlowLayout(FlowLayout.LEADING));
		panAdr.add(labelAdr);
		panAdr.add(adr);
		
		JPanel panCode = new JPanel();
		panCode.setBackground(Color.white);
		code.setPreferredSize(dimField);
		labelCode.setPreferredSize(dimLabel);
		panCode.setLayout(new FlowLayout(FlowLayout.LEADING));
		panCode.add(labelCode);
		panCode.add(code);
		
		JPanel panCommune = new JPanel();
		panCommune.setBackground(Color.white);
		commune.setPreferredSize(dimField);
		labelCommune.setPreferredSize(dimLabel);
		panCommune.setLayout(new FlowLayout(FlowLayout.LEADING));
		panCommune.add(labelCommune);
		panCommune.add(commune);
		
		//panneau contenant les infos sur la personne
		JPanel domicile=new JPanel(new GridLayout(3,1));
		domicile.setBorder(BorderFactory.createTitledBorder("Domicile de la personne"));
		domicile.setBackground(Color.white);
		domicile.add(panAdr);
		domicile.add(panCode);
		domicile.add(panCommune);
		

		JPanel haut=new JPanel();
		haut.setBackground(Color.white);
		haut.add(id,BorderLayout.WEST);
		haut.add(panImage,BorderLayout.EAST);
		
		JPanel bas=new JPanel(new GridLayout(1,0));
		bas.setBackground(Color.white);
		
		JPanel emprun=new JPanel();
		emprun.setBorder(BorderFactory.createTitledBorder("Liste des livres empruntes"));
		emprun.setBackground(Color.white);
		emprun.add(new JScrollPane(liste));
		bas.add(domicile);
		bas.add(emprun);
		
		panel.setLayout(new GridLayout(2,0));
		panel.add(haut);
		panel.add(bas);

		/*
		 * ajout d'un listener sur la liste des livres empruntes
		 * un double clique de la souris ou en cliquant sur rendre ce livre 
		 * du menu contextuel sur un item permet de rendre le livre emprunter
		 */
		menuContextuel.add(rendreL);
		rendreL.addActionListener(new ActionListener(){
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 				int selected = liste.getSelectedIndex();
                int idLivre= inscrit.getEmprunt().get(selected).getKey();
                rendreLivre(selected, idLivre);
 			}
 	  	});
	 	liste.addMouseListener(new MouseListener(){
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {}
	 			@Override
	 			public void mouseEntered(MouseEvent arg0) {}
	 			@Override
	 			public void mouseExited(MouseEvent arg0) {}
	 			@Override
	 			public void mousePressed(MouseEvent arg0) {}
	 			@Override
	 			public void mouseReleased(MouseEvent arg0) {
	 				// TODO Auto-generated method stub
	 				  if ( arg0.getClickCount() == 2 ) {
		                     int selected = liste.getSelectedIndex();
		                     if(selected >-1)
		                    	 if(inscrit.getEmprunt().size() >= selected){
		                    		 int idLivre= inscrit.getEmprunt().get(selected).getKey();
				                     rendreLivre(selected, idLivre);
		                    	 } 
		               }
	 				if(arg0.getButton()==MouseEvent.BUTTON3){
	 	                  if(liste.getSelectedIndex()>=0){
	 		                    menuContextuel.show(liste,arg0.getX() ,arg0.getY());
	 	                  }
	 	            }
	 			} 
	     	 });

	}
	/**
	 * @param selected
	 * @param idLivre
	 */
	public void rendreLivre(int selected, int idLivre) {
		//on demande une confirmation avant de rendre le livre
		int i=JOptionPane.showConfirmDialog(null, "Voulez vous rendre ce livre" +
				"\nLa Bibiliotheque", "Information Emprunt Livre", JOptionPane.YES_NO_OPTION);
		if(i==JOptionPane.YES_OPTION){
			lesLivres.get(idLivre).setDisponible(true);
			 inscrit.getEmprunt().remove(selected);
			 liste.setListData(inscrit.getEmprunt().toArray());
			 JOptionPane.showMessageDialog(null, "Ce livre a ete rendu" +
					"\nLa Bibiliotheque", "Information Emprunt Livre", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "Le retour du livre est annule" +
					"\nLa Bibiliotheque", "Information Emprunt Livre", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
