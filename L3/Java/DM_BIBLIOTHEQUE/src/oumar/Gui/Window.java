/**
 * 
 */
package oumar.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import oumar.Bibliotheque.Bibliotheque;
import oumar.Structure.Inscrit;
import oumar.Structure.Livre;
import oumar.Utilitaires.MyTabbedPane;

/**
 * <b>Description:</b><i>Cette classe heritiere de JFrame est utlise pour contenir tous les composants de notre application</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class Window extends JFrame implements WindowListener{
	protected static final int INDEX_INSCRIT = 0;
	protected static final int INDEX_LIVRE = 1;

	private JMenuBar menu = new JMenuBar();//creating menu barr
	
	private JMenu file = new JMenu("Fichier"); //tab file
	//items of tab file
	private JMenu livreF=new JMenu("Livre"),inscritF=new JMenu("Personne");
	private JMenuItem ajoutLivre = new JMenuItem("Ajouter un livre",new ImageIcon("images/newFile.png"));
	private JMenuItem consulterListeLivre = new JMenuItem("consulter la liste",new ImageIcon("images/List.png"));
	private JMenuItem emprunterLivre = new JMenuItem("Emprunter ce livre",new ImageIcon("images/Hand.png"));
	
	private JMenuItem ajoutInscrit= new JMenuItem("Ajouter une personne",new ImageIcon("images/newFile.png"));
	private JMenuItem consulterListeInscrit= new JMenuItem("consulter la liste",new ImageIcon("images/List.png"));
	
	private JMenuItem quit= new JMenuItem("Quitter",new ImageIcon("images/exit.png"));
	private JMenuItem save= new JMenuItem("Sauvegarder",new ImageIcon("images/save.png"));
	
	private JMenu livre=new JMenu("Livre"),inscrit=new JMenu("Personne");
	private JMenu help=new JMenu("Aide");
	private JMenu edit= new JMenu("Edition");//tab edit
	//items of tab edit
	private JMenuItem modifierL= new JMenuItem("modifier",new ImageIcon("images/Edit.png"));
	private JMenuItem supprimerL= new JMenuItem("supprimer",new ImageIcon("images/Delete.png"));
	private JMenuItem modifierP= new JMenuItem("modifier",new ImageIcon("images/Edit.png"));
	private JMenuItem supprimerP= new JMenuItem("supprimer",new ImageIcon("images/Delete.png"));
	private JMenuItem descriptionL= new JMenuItem("description",new ImageIcon("images/Eye.png"));
	private JMenuItem descriptionP= new JMenuItem("description",new ImageIcon("images/Eye.png"));
	private JMenuItem who= new JMenuItem(" ? ",new ImageIcon("images/Info.png"));
	
	private Dimension size;
	
	//menu contextuel
	private JPopupMenu menuContextuelL=new JPopupMenu();
        private JMenuItem mModifierL=new JMenuItem("modifier",new ImageIcon("images/Edit.png"));
        private JMenuItem mSupprimerL=new JMenuItem("supprimer",new ImageIcon("images/Delete.png"));
        private JMenuItem mDescriptionL=new JMenuItem("description",new ImageIcon("images/Eye.png"));
        private JMenuItem mEmprunterL=new JMenuItem("Emprunter",new ImageIcon("images/Hand.png"));

        private JPopupMenu menuContextuelP=new JPopupMenu();
        private JMenuItem mModifierP=new JMenuItem("modifier",new ImageIcon("images/Edit.png"));
        private JMenuItem mSupprimerP=new JMenuItem("supprimer",new ImageIcon("images/Delete.png"));
        private JMenuItem mDescriptionP=new JMenuItem("description",new ImageIcon("images/Eye.png"));
        private MyTabbedPane  content;


        private String  title[] = {"ISBN", "Titre", "Auteur", "Categorie","Theme","Nombre de page","Disponible"};
        private String  title2[] = {"Numero", "Nom", "Prenom", "Sexe","Adresse","Tel","Age","Livre Emprunter"};

        private Bibliotheque bibliotheque = new Bibliotheque();
        private InscritTabModel mod;
        private JTable tableInscrit;
        private LivreTabModel mod2;
        private JTable tableLivre;

        File folder=new File ("images/Medias/"); 
        File donnees=new File ("donnees");
    
	/**
	 * Constructeur de la frame de l'application
	 */
	public Window(){
		super("Gestion Bibliotheque");
		mod= new InscritTabModel(bibliotheque.getHumain(),title2);
		tableInscrit=new JTable(mod);
		//on dit a notre tableau qu'il peut contenir une CheckBox
		tableInscrit.setDefaultRenderer(JCheckBox.class, new tableCellRender());
		//une seule selection possible au niveau des lignes
		tableInscrit.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mod2= new LivreTabModel(bibliotheque.getLesLivres(),title);
		tableLivre=new JTable(mod2);
		//on dit a notre tableau qu'il peut contenir une CheckBox
		tableLivre.setDefaultRenderer(JCheckBox.class, new tableCellRender());
		//une seule selection possible au niveau des lignes
		tableLivre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//creation du tabbedPane
		content=new MyTabbedPane(Color.LIGHT_GRAY, Color.WHITE);
		//title of the window
		setSize(900, 600); //size of the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //quit on pressing exit
		setLocationRelativeTo(null); //place window at the center
		setResizable(false); //unresizable window
		getContentPane().setBackground(Color.white);
		size = new Dimension(this.getWidth(), this.getHeight()); //set the dimension
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/bibliotheque.gif"));
		
		//on cree le dossier pour les images si il n'existe pas
		if(!folder.exists()) folder.mkdirs();
                if(!donnees.exists()) donnees.mkdirs();
		//creating tab file and customizing items
		file.setMnemonic('F'); //underline F
		//adding items to the tab file
		livreF.add(ajoutLivre);
		livreF.add(consulterListeLivre);
		inscritF.add(ajoutInscrit);
		inscritF.add(consulterListeInscrit);
		file.add(livreF);
		file.add(inscritF);
		file.addSeparator();
                file.add(save);
		file.add(quit);
		//creating tab edit and customizing items
  		edit.setMnemonic('A'); //underline A
  		//adding items to the tab edit
  		livre.add(modifierL);
  		livre.add(supprimerL);
  		livre.add(descriptionL);
  		livre.add(emprunterLivre);
  		inscrit.add(modifierP);
  		inscrit.add(supprimerP);
  		inscrit.add(descriptionP);
  		edit.add(livre);
  		edit.add(inscrit);
  		//creating tab help and customizing items
  		help.setMnemonic('H');
                help.add(who);	
                //adding tabs file and edit to the menu  
	  	menu.add(file); 
	  	menu.add(edit);
	  	menu.add(help);
	  	//setting the menu bar
	  	setJMenuBar(menu);
	  	content.setBackground(Color.white);
	  	content.insertTab("Liste des Inscrits",null,new JScrollPane(tableInscrit),"Panneau contenant la liste des inscrits",INDEX_INSCRIT);
	  	content.insertTab("Liste des Livres",null,new JScrollPane(tableLivre),"Panneau contenant la liste des Livres",INDEX_LIVRE);
	  	livre.setEnabled(false);
	  	setContentPane(content);
	  	setVisible(true);
	  	//menu contextuel et leurs actions sur les tables
	  	menuContextuel();
	  	//listener sur les menus de la'application
	  	actionlistener();
	  	//ecouteur de la fenetre
	  	addWindowListener(this);
	}	
	/**
	 * 
	 * @param key
	 * cette methode est appele quand on veut modifier les informations d'un inscrit
	 */
	public void modifierInfoInscrit(int key){
		if(key >=0){
			AjoutHumain jd = new AjoutHumain(null, "Modification des informations",bibliotheque.getHumain().get(key), true);
			Inscrit jInfo = jd.showJDialog(); 
			mod.modifierHumain(key, jInfo);
			}
		else JOptionPane.showMessageDialog(null,"Vous devez selectionner une personne dans la liste","Modification Info Inscrit",JOptionPane.NO_OPTION);
	}
	/**
	 * 
	 * @param key
	 * cette methode est appele quand on veut modifier les informations d'un livre
	 */
	public void modifierInfoLivre(int key){
		if(key >=0){
			if(bibliotheque.getLesLivres().get(key).getDisponible()){
				AjoutLivre zd = new AjoutLivre(null, "Modification des informations",bibliotheque.getLesLivres().get(key), true);
				Livre jInfo = zd.showJDialog(); 
				mod2.modifierLivre(key, jInfo);
				}
			else JOptionPane.showMessageDialog(null, "Ce livre est actuellement indisponible." +
					"\nAucune modification possible" +
					"\n	La Bibliotheque", "Modification du Livre", JOptionPane.WARNING_MESSAGE);
			}
		else JOptionPane.showMessageDialog(null,"Vous devez selectionner un livre dans la liste","Modification Info Livre",JOptionPane.NO_OPTION);
	}
	
	/**
	 * @param key
	 * cette methode sert a supprimer un livre
	 */
	public void suppressionLivre(int key) {
		if(key >=0){
			if(bibliotheque.getLesLivres().get(key).getDisponible())
				mod2.supprimerLivre(key);
			else JOptionPane.showMessageDialog(null, "Ce livre est actuellement indisponible. " +
					"\nAucune suppression possible" +
					"\n	La Bibliotheque", "Suppression du Livre", JOptionPane.WARNING_MESSAGE);
		}
		else JOptionPane.showMessageDialog(null,"Vous devez selectionner un livre dans la liste","Suppression Livre",JOptionPane.NO_OPTION);
	}
	
	/**
	 * @param key
	 * cette methode sert a afficher la description d'un livre
	 */
	public void descriptionLivre(int key) {
		if(key >=0){
			content.addTab("Description du livre : "+bibliotheque.getLesLivres().get(key).getTitre(),
					new DescriptionLivre(size,bibliotheque.getLesLivres().get(key)).getPanel(),MyTabbedPane.WITH_CLOSE_CROSS);
			content.setSelectedIndex(content.getTabCount()-1);
		}
		else JOptionPane.showMessageDialog(null,"Vous devez selectionner un livre dans la liste","Description Livre",JOptionPane.NO_OPTION);
	}
	
	/**
	 * @param key
	 * cette methode est appeler quand on veut emprunter un livre
	 */
	public void emprunterLivre(int key) {
		if(key >=0){
			if(bibliotheque.getLesLivres().get(key).getDisponible())
				mod2.emprunterLivre(key,bibliotheque.getHumain());
			else JOptionPane.showMessageDialog(null, "Ce livre est actuellement indisponible. " +
					"\nAucune emprunt possible" +
					"\n	La Bibliotheque", "Emprunt du Livre", JOptionPane.WARNING_MESSAGE);
		}
		else JOptionPane.showMessageDialog(null,"Vous devez selectionner un livre dans la liste","Emprunt Livre",JOptionPane.NO_OPTION);
	}
	
	/**
	 * @param key
	 * cette methode sert a afficher la description d'un inscrit
	 */
	public void descriptionInscrit(int key) {
		if(key >=0){
			content.addTab("Fichier de l'inscrit : "+bibliotheque.getHumain().get(key).getNom(),
					new DescriptionInscrit(size,bibliotheque.getHumain().get(key),bibliotheque.getLesLivres()).getPanel(),MyTabbedPane.WITH_CLOSE_CROSS);
			content.setSelectedIndex(content.getTabCount()-1);
		}
		else JOptionPane.showMessageDialog(null,"Vous devez selectionner une personne dans la liste","Description Inscrit",JOptionPane.NO_OPTION);
	}
	/**
	 * cette methode cree les actions listeners du menu
	 */
	public void actionlistener(){
	  	who.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
	    		          "Developpeur : Alpha Oumar Binta Diallo\nLicense : Freeware\nCopyright : 2012 (c) Paul Sabatier L3\n" +
	    		          "Version : 1.0.O\n" +	 
	    		          "Sujet : gestion bibliotheque en java \n" +	
	    		          "Contact : aob.diallo@gmail.com",
	    		          "Information", JOptionPane.NO_OPTION);
			}
	  	});
	  	quit.addActionListener(new ActionListener(){
	  		@Override
	  		public void actionPerformed(ActionEvent e) {
	  			System.exit(0);
	  		}
	  	});
	  	
	  	
	  	ajoutInscrit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AjoutHumain jd = new AjoutHumain(null, "Ajout d'une Personne", true);
				Inscrit jInfo = jd.showJDialog(); 
				mod.ajouterInscritAuTableau(jInfo,true);
			}
	  		
	  	});
	  	ajoutLivre.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AjoutLivre jd = new AjoutLivre(null, "Ajout d'un Livre", true);
				Livre jInfo = jd.showJDialog(); 
				mod2.ajouterLivreAuTableau(jInfo,true);
			}
	  		
	  	});
	  	modifierP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int key=tableInscrit.getSelectedRow();
				modifierInfoInscrit(key);
			}
	  		
	  	});
		modifierL.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int key=tableLivre.getSelectedRow();
				modifierInfoLivre(key);
			}
	  		
	  	});
	  	supprimerP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int key=tableInscrit.getSelectedRow();
				if(key >=0)
				mod.supprimerHumain(key);
				else JOptionPane.showMessageDialog(null,"Vous devez selectionner une personne dans la liste","Suppression Inscrit",JOptionPane.NO_OPTION);
			}
	  	});
		supprimerL.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int key=tableLivre.getSelectedRow();
				suppressionLivre(key);
				
			}
	  	});
		emprunterLivre.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int key=tableLivre.getSelectedRow();
				emprunterLivre(key);
			}
	  	});
		consulterListeLivre.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				content.setSelectedIndex(INDEX_LIVRE);
		    	inscrit.setEnabled(false);
		    	livre.setEnabled(true);
		    	edit.setEnabled(true);
			}
	  	});
		consulterListeInscrit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				content.setSelectedIndex(INDEX_INSCRIT);
		    	livre.setEnabled(false);
		    	inscrit.setEnabled(true);
		    	edit.setEnabled(true);
			}
	  	});
		descriptionL.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int key=tableLivre.getSelectedRow();
				descriptionLivre(key);
			}	
	  	});
		descriptionP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int key=tableInscrit.getSelectedRow();
				descriptionInscrit(key);
			}
	  	});
                save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bibliotheque.getFichierInscrit().serialize();
                                bibliotheque.getFichierLivre().serialize();
			}
	  	});
	}
	/**
	 * cette methode cree les menus contextuels utilises par l'application
	 */
	public void menuContextuel(){
		//creation des menu contextuels et de leur listener
				//pour les livres
				 menuContextuelL.add(mModifierL);
		    	 menuContextuelL.add(mSupprimerL);
		    	 menuContextuelL.add(mDescriptionL);
		    	 menuContextuelL.add(mEmprunterL);
		    	//pour les inscrits
		    	 menuContextuelP.add(mModifierP);
		    	 menuContextuelP.add(mSupprimerP);
		    	 menuContextuelP.add(mDescriptionP);
		    	 
		    	 mModifierP.addActionListener(new ActionListener(){
		 			@Override
		 			public void actionPerformed(ActionEvent arg0) {
		 				int key=tableInscrit.getSelectedRow();
		 				modifierInfoInscrit(key);
		 			}
		 	  	});
		    	 mSupprimerP.addActionListener(new ActionListener(){
		 			@Override
		 			public void actionPerformed(ActionEvent arg0) {
		 				int key=tableInscrit.getSelectedRow();
		 				if(key >=0)
		 				mod.supprimerHumain(key);
		 			}
		 	  	});
		    	 mDescriptionP.addActionListener(new ActionListener(){
		 			@Override
		 			public void actionPerformed(ActionEvent arg0) {
		 				int key=tableInscrit.getSelectedRow();
		 				descriptionInscrit(key);
		 			}
		 	  	});
		    	 mModifierL.addActionListener(new ActionListener(){
		 			@Override
		 			public void actionPerformed(ActionEvent arg0) {
		 				int key=tableLivre.getSelectedRow();
		 				modifierInfoLivre(key);
		 			}
		 	  		
		 	  	});
		    	 mSupprimerL.addActionListener(new ActionListener(){
		 			@Override
		 			public void actionPerformed(ActionEvent arg0) {
		 				int key=tableLivre.getSelectedRow();
		 				suppressionLivre(key);
		 				
		 			}
		 	  	});
		    	 mDescriptionL.addActionListener(new ActionListener(){
		 			@Override
		 			public void actionPerformed(ActionEvent arg0) {
		 				int key=tableLivre.getSelectedRow();
		 				descriptionLivre(key);
		 			}	
		 	  	});
		    	 mEmprunterL.addActionListener(new ActionListener(){
		 			@Override
		 			public void actionPerformed(ActionEvent arg0) {
		 				int key=tableLivre.getSelectedRow();
		 				emprunterLivre(key);
		 			}
		 	  	});
		    	 tableLivre.addMouseListener(new MouseListener(){
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
						  if ( arg0.getClickCount() == 2 ) {
							  if(tableLivre.getSelectedRow()>=0){
								  int key=tableLivre.getSelectedRow();
								  descriptionLivre(key);
							  }
			               }
						if(arg0.getButton()==MouseEvent.BUTTON3){
			                  if(tableLivre.getSelectedRow()>=0){
				                    menuContextuelL.show(tableLivre,arg0.getX() ,arg0.getY());
			                  }
			                }
					}
		    	 });
		    	 tableInscrit.addMouseListener(new MouseListener(){
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
		 				  if ( arg0.getClickCount() == 2 ) {
							  if(tableInscrit.getSelectedRow()>=0){
								  int key=tableInscrit.getSelectedRow();
								  descriptionInscrit(key);
							  }
			               }
		 				if(arg0.getButton()==MouseEvent.BUTTON3){
		 	                  if(tableInscrit.getSelectedRow()>=0){
		 		                    menuContextuelP.show(tableInscrit,arg0.getX() ,arg0.getY());
		 	                  }
		 	                }
		 			} 
		     	 });
		    	 content.addChangeListener(new ChangeListener() {
		   	      public void stateChanged(ChangeEvent e) {
		   	        if(content.getSelectedIndex()==INDEX_INSCRIT){
		   	        	inscrit.setEnabled(true);
		   	        	livre.setEnabled(false);
		   	        }
		   	        else if(content.getSelectedIndex()==INDEX_LIVRE){
		   	        	livre.setEnabled(true);
		   	        	inscrit.setEnabled(false);
		   	        }
		   	        else {
		   	        	livre.setEnabled(true);
		   	        	inscrit.setEnabled(true);
		   	        }
		   	      }
		   	    });
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) {
		bibliotheque.getFichierInscrit().serialize();
		bibliotheque.getFichierLivre().serialize();
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {}
}
