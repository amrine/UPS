/**
 * 
 */
package oumar.Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import oumar.Enumeration.Categorie;
import oumar.Enumeration.Theme;
import oumar.Structure.Livre;
import oumar.Utilitaires.Ajout;
import oumar.Utilitaires.ClavierListener;
import oumar.Utilitaires.FiltreFichier;

/**
 *<b>Description:</b><i>Cette classe heritiere de la classe abstraite Ajout est utilise pour ajouter ou modifier un
 * livre a la bibliotheque a partir d'une JDialog contenant les champs a remplir</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */

public class AjoutLivre extends Ajout{
	private Livre livre;
    private JComboBox<Categorie> categorie;
    private JLabel labelTitre,labelCategorie,labelPage,labelImage,labelTheme;
    private JButton imageB;
    private JTextField titre,auteur,page;
    private JTextArea resume;
    private JLabel labelAuteur;
    private Dimension dimLabel,dimField;
    private JCheckBox tSport,tHumour,tPolitique,tFiction,tArt;
	private Categorie categ = null;
	private List<Theme> listTheme=new ArrayList<Theme>();
	private FiltreFichier mfi = new FiltreFichier( 
 		   new String[]{"gif","tif","jpeg","jpg","tiff"},"les fichiers image (*.gif, *.tif,*.jpeg)");
	private JFileChooser choix = new JFileChooser();
	private String image;
	/**
	 * 
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public AjoutLivre(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(450,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		livre=new Livre();
		this.initComponent();
		
	}
	/**
	 * 
	 * @param parent
	 * @param title
	 * @param livre
	 * @param modal
	 */
	public AjoutLivre(JFrame parent, String title,Livre livre, boolean modal){
		super(parent, title, modal);
		this.setSize(450,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.livre=livre;
		this.initComponent();
		
	}
	
	/**
	 * Methode appelee pour utiliser la boite 
	 * @return livre
	 */
	public Livre showJDialog(){
		setVisible(true);		
		return livre;		
	}
	
	public void initComponent(){
		
        labelAuteur = new JLabel("Auteur");
        auteur = new JTextField(livre.getAuteur().trim().equals("") ? "" : livre.getAuteur());
        labelTitre = new JLabel("Titre");
        titre = new JTextField(livre.getTitre().trim().equals("") ? "" : livre.getTitre());
        labelCategorie = new JLabel("Categorie");
        categorie = new JComboBox<Categorie>(new Categorie[] { Categorie.MAGAZINE, Categorie.BD,Categorie.ROMAN });
        /*si une categorie est affecte  elle est selectionnee par defaut*/
        if(livre.getCategorie()!=null) categorie.setSelectedItem(livre.getCategorie());
        tSport=new JCheckBox("SPORT",(livre.getTheme()!=null)?livre.getTheme().contains(Theme.SPORT):false);
        tHumour=new JCheckBox("HUMOUR",(livre.getTheme()!=null)?livre.getTheme().contains(Theme.HUMOUR):false);
        tPolitique=new JCheckBox("POLITIQUE",(livre.getTheme()!=null)?livre.getTheme().contains(Theme.POLITIQUE):false);
        tFiction=new JCheckBox("FICTION",(livre.getTheme()!=null)?livre.getTheme().contains(Theme.FICTION):false);
        tArt=new JCheckBox("ART",(livre.getTheme()!=null)?livre.getTheme().contains(Theme.ART):false);

        image=  livre.getImage().trim().equals("") ? "images/livre.png" : livre.getImage();
        labelPage = new JLabel("Page");
        Image icon=new ImageIcon(image).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        labelImage = new JLabel(new ImageIcon(icon));
        labelTheme=new JLabel("Theme");
        imageB = new JButton("Choisir...",new ImageIcon("images/openFile.png"));
        choix.addChoosableFileFilter(mfi);
        resume = new JTextArea(livre.getResume().trim().equals("") ? "Entrer ici le resume du texte" : livre.getResume(),10,0);
        dimLabel=new Dimension(50,20);
        dimField=new Dimension(150,20);
        labelImage.setPreferredSize(new Dimension(150,150));
        labelImage.setBorder(BorderFactory.createLineBorder(Color.red));
        
        tSport.setBackground(Color.white);
        tHumour.setBackground(Color.white);
        tPolitique.setBackground(Color.white);
        tFiction.setBackground(Color.white);
        tArt.setBackground(Color.white);
        
        page = new JTextField();
      	if(!livre.getNbrePage().trim().equals("")) page.setText(""+livre.getNbrePage());
         
		
        //toolTip
        auteur.setToolTipText("nom de l'auteur"); 
        titre.setToolTipText("titre du livre"); 
        page.setToolTipText("nombre de page"); 
		
        Font police = new Font("Arial", Font.BOLD, 14);
        page.setFont(police);
        
        
        
        JPanel panAuteur = new JPanel();
		panAuteur.setBackground(Color.white);
		auteur.setPreferredSize(dimField);
		labelAuteur.setPreferredSize(dimLabel);
		panAuteur.setLayout(new FlowLayout(FlowLayout.LEADING));
		panAuteur.add(labelAuteur);
		panAuteur.add(auteur);
		
		JPanel panTitre = new JPanel();
		panTitre.setBackground(Color.white);
		titre.setPreferredSize(dimField);
		labelTitre.setPreferredSize(dimLabel);
		panTitre.setLayout(new FlowLayout(FlowLayout.LEADING));
		panTitre.add(labelTitre);
		panTitre.add(titre);
		
		JPanel panCategorie = new JPanel();
		panCategorie.setBackground(Color.white);
		labelCategorie.setPreferredSize(dimLabel);
		panCategorie.setLayout(new FlowLayout(FlowLayout.LEADING));
		panCategorie.add(labelCategorie);
		panCategorie.add(categorie);
		
		
		JPanel panTheme = new JPanel();
		panTheme.setBackground(Color.white);
		labelTheme.setPreferredSize(dimLabel);
		panTheme.setLayout(new FlowLayout(FlowLayout.LEADING));
		panTheme.add(labelTheme);
		panTheme.add(tSport);
		panTheme.add(tHumour);
		panTheme.add(tPolitique);
		panTheme.add(tFiction);
		panTheme.add(tArt);
		
		JPanel panPage = new JPanel();
		panPage.setBackground(Color.white);
		page.setPreferredSize(dimField);
		labelPage.setPreferredSize(dimLabel);
		panPage.setLayout(new FlowLayout(FlowLayout.LEADING));
		panPage.add(labelPage);
		panPage.add(page);
		
    
		JPanel panImage = new JPanel();
		panImage.setBackground(Color.white);
		imageB.setPreferredSize(dimField);
		panImage.setLayout(new FlowLayout(FlowLayout.LEADING));
		panImage.add(labelImage);
		panImage.add(imageB);
		
	
		//panneau contenant les infos sur la personne
		JPanel descriptif=new JPanel(new GridLayout(5,1));
		descriptif.setBorder(BorderFactory.createTitledBorder("Identification du Livre"));
		descriptif.setBackground(Color.white);
		descriptif.add(panAuteur);
		descriptif.add(panTitre);
		descriptif.add(panCategorie);
		descriptif.add(panPage);
		descriptif.add(panTheme);
		
				
		
		JPanel control = new JPanel();
		control.setBackground(Color.white);
		JButton okBouton = new JButton("OK",new ImageIcon("images/Yes.png"));
		JButton cancelBouton = new JButton("Annuler",new ImageIcon("images/Cancel.png"));
		control.add(okBouton);
		control.add(cancelBouton);
		//ajout du listener sur les entrees pour le champs nombre de page
		page.addKeyListener(new ClavierListener(page,"un entier est attendu de votre part","zone nombre de page"));
		
		okBouton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(tSport.isSelected()) listTheme.add(Theme.SPORT);
				if(tHumour.isSelected()) listTheme.add(Theme.HUMOUR);
				if(tPolitique.isSelected()) listTheme.add(Theme.POLITIQUE);
				if(tFiction.isSelected()) listTheme.add(Theme.FICTION);
				if(tArt.isSelected()) listTheme.add(Theme.ART);
				
				
				if(categorie.getSelectedIndex()==Categorie.MAGAZINE.ordinal()) categ=Categorie.MAGAZINE;
				if(categorie.getSelectedIndex()==Categorie.BD.ordinal()) categ=Categorie.BD;
				if(categorie.getSelectedIndex()==Categorie.ROMAN.ordinal()) categ=Categorie.ROMAN;
				
				if(testInformation()){
					livre= new Livre(image,titre.getText(),auteur.getText(),resume.getText(),
							categ,listTheme,page.getText());
					
					setVisible(false);					
				}				
			}
			
		});
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				livre=null;
				setVisible(false);
			}			
		});
		
		imageB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				 int retour=choix.showOpenDialog(null);
			        if(retour==JFileChooser.APPROVE_OPTION){
			           // un fichier a ete choisi (sortie par OK)
			           // nom du fichier  choisi 
			           // chemin absolu du fichier choisi
			        	if(mfi.accept(choix.getSelectedFile())){
				        	  if(mfi.uploadFile(choix)){
				        		  image="images/Medias/"+choix.getSelectedFile().getName();
				        		  Image icon=new ImageIcon(image).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
				        		  labelImage.setIcon(new ImageIcon(icon));
				        	  }
				          }
			        }else image="images/livre.png";// pas de fichier choisit
			 }			
		});
		JPanel bas= new JPanel(new BorderLayout());
		bas.setBackground(Color.white);
		bas.add(panImage,BorderLayout.NORTH);
		bas.add(new JScrollPane(resume),BorderLayout.SOUTH);
		
		JPanel haut= new JPanel(new BorderLayout());
		haut.add(descriptif,BorderLayout.NORTH);
		haut.add(bas,BorderLayout.SOUTH);
		haut.setBackground(Color.white);
		
		
		getContentPane().setBackground(Color.white);
		getContentPane().add(haut, BorderLayout.NORTH);
		getContentPane().add(control, BorderLayout.SOUTH);
	}
	
	public boolean testInformation(){
		if(auteur.getText().trim().equals("")){
			warningMessage("vous devez entrer un auteur","Zone Auteur");
			return false;
		}
		if(titre.getText().trim().equals("")){
			warningMessage("vous devez entrer un titre","Zone Titre");
			return false;
		}
		if(categ==null){
			warningMessage("vous devez choisir une categorie","Zone Categorie");
			return false;
		}
		if(page.getText().trim().equals("") ){
			warningMessage("vous devez entrer le nombre de page du livre (un entier est attendu)","Zone Page");
			return false;
		}
		if(listTheme.isEmpty()){
			warningMessage("vous devez choisir un theme","Zone Theme");
			return false;
		}
		if(resume.getText().trim().equals("")){
			warningMessage("vous devez entrer un resume","Zone Resume");
			return false;
		}
		return true;
	}


	
}
