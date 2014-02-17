/**
 * 
 */
package oumar.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import oumar.Utilitaires.Ajout;
import oumar.Enumeration.Sexe;
import oumar.Structure.Inscrit;
import oumar.Utilitaires.ClavierListener;
import oumar.Utilitaires.FiltreFichier;

/** <b>Description</b><i>Cette classe heritiere de la classe abstraite Ajout est utilise pour ajouter(une nouvelle personne)
 *  ou modifier les informations d'une personne de la bibliotheque a partir d'une JDialog contenant 
 *  les champs a remplir</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class AjoutHumain extends Ajout{
	private Inscrit inscrit;
    private JComboBox<String> sexe;
    private JFormattedTextField tel,code,age;
    private JLabel labelPrenom,labelSexe,labelAdr,labelCode,labelCommune,labelTel,labelImage;
    private JButton imageB;
    private JTextField adr,commune,prenom,nom;
    private JLabel labelNom,labelAge;
    private Dimension dimLabel,dimField;
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
	public AjoutHumain(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(350,530);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		inscrit=new Inscrit();
		this.initComponent();
		
	}
	/**
	 * 
	 * @param parent
	 * @param title
	 * @param inscrit
	 * @param modal
	 */
	public AjoutHumain(JFrame parent, String title,Inscrit inscrit, boolean modal){
		super(parent, title, modal);
		this.setSize(350,530);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.inscrit=inscrit;
		this.initComponent();
		
	}
	
	/**
	 * Methode appelee pour utiliser la boite 
	 * @return inscrit
	 */
	public Inscrit showJDialog(){
		setVisible(true);		
		return inscrit;		
	}
	
	public void initComponent(){
		
        labelNom = new JLabel("Nom");
        nom = new JTextField(inscrit.getNom().trim().equals("") ? "" : inscrit.getNom());
        labelAge = new JLabel("Age");
        labelPrenom = new JLabel("Prenom");
        prenom = new JTextField(inscrit.getPrenom().trim().equals("") ? "" : inscrit.getPrenom());
        image=  inscrit.getImage().trim().equals("") ? "images/humain.png" : inscrit.getImage();
        labelSexe = new JLabel("Sexe");
        sexe = new JComboBox<String>(new String[] { "MASCULIN", "FEMININ" });
        labelTel = new JLabel("Tel");
        Image icon=new ImageIcon(image).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        labelImage = new JLabel(new ImageIcon(icon));
        imageB = new JButton("Choisir...",new ImageIcon("images/openFile.png"));
        choix.addChoosableFileFilter(mfi);
        labelAdr = new JLabel("Adresse");
        adr = new JTextField(inscrit.getAdresse()==null ? "" : inscrit.getAdresse()[0]);
        labelCode = new JLabel("Code");
        labelCommune = new JLabel("Ville");
        commune = new JTextField(inscrit.getAdresse()==null ? "" : inscrit.getAdresse()[2]);
        dimLabel=new Dimension(50,20);
        dimField=new Dimension(150,20);
        labelImage.setPreferredSize(new Dimension(150,150));
        labelImage.setBorder(BorderFactory.createLineBorder(Color.red));
        
        
        try{
      	   MaskFormatter telFrance = new MaskFormatter("##-##-##-##-##");
      	   MaskFormatter codeP = new MaskFormatter("#####");
      	   MaskFormatter codeAge = new MaskFormatter("##");
      	   tel = new JFormattedTextField(telFrance);
    	   code = new JFormattedTextField(codeP);
    	   age = new JFormattedTextField(codeAge);
    	   if(!inscrit.getAge().trim().equals("")) age.setText(inscrit.getAge());
    	   if(!inscrit.getTel().trim().equals("")) tel.setText(inscrit.getTel());
    	   if(inscrit.getAdresse()!=null) code.setText(""+Integer.parseInt(inscrit.getAdresse()[1]));
         }catch(ParseException e){
      	e.printStackTrace();
         }
		
        //toolTip
        nom.setToolTipText("votre nom"); 
        prenom.setToolTipText("votre prenom"); 
        tel.setToolTipText("votre numero de telephone"); 
        adr.setToolTipText("votre adresse"); 
        code.setToolTipText("votre code postale"); 
        commune.setToolTipText("votre ville"); 
        age.setToolTipText("votre age");
        Font police = new Font("Arial", Font.BOLD, 14);
        tel.setFont(police);
        code.setFont(police);
        
        
        
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
		panImage.setLayout(new FlowLayout(FlowLayout.LEADING));
		panImage.add(labelImage);
		panImage.add(imageB);
		
	
		//panneau contenant les infos sur la personne
		JPanel id=new JPanel(new GridLayout(5,1));
		id.setBorder(BorderFactory.createTitledBorder("Identification de la personne"));
		id.setBackground(Color.white);
		id.add(panNom);
		id.add(panPrenom);
		id.add(panAge);
		id.add(panSexe);
		id.add(panTel);
		
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
		
		
		JPanel control = new JPanel();
		control.setBackground(Color.white);
		JButton okBouton = new JButton("OK",new ImageIcon("images/Yes.png"));
		JButton cancelBouton = new JButton("Annuler",new ImageIcon("images/Cancel.png"));
		control.add(okBouton);
		control.add(cancelBouton);
		
		//ajout du listener sur les entrees pour leS champs attendant un entier
		tel.addKeyListener(new ClavierListener(tel,"un entier est attendu de votre part","zone numero de telephone"));
		code.addKeyListener(new ClavierListener(code,"un entier est attendu de votre part","zone code postale"));
		age.addKeyListener(new ClavierListener(age,"un entier est attendu de votre part\n format deux chiffres","zone age"));
		
		okBouton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(testInformation()){
					String[] vtreAdr={adr.getText(),code.getText(),commune.getText()};
					inscrit= new Inscrit(image,nom.getText(),prenom.getText(),vtreAdr,
							tel.getText(), (sexe.getSelectedIndex()==Sexe.MASCULIN.ordinal()) ? Sexe.MASCULIN : Sexe.FEMININ,age.getText());
					
					setVisible(false);
				}
				
			}
			
		});
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				inscrit=null;
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
			        }else image="images/humain.png";// pas de fichier choisit
			        }			
		});
		JPanel haut= new JPanel(new BorderLayout());
		haut.add(id,BorderLayout.NORTH);
		haut.add(domicile,BorderLayout.SOUTH);
		haut.setBackground(Color.white);
		
		JPanel bas= new JPanel(new BorderLayout());
		bas.add(panImage,BorderLayout.NORTH);
		bas.add(control,BorderLayout.SOUTH);
		bas.setBackground(Color.white);
		
		getContentPane().setBackground(Color.white);
		getContentPane().add(haut, BorderLayout.NORTH);
		getContentPane().add(bas, BorderLayout.SOUTH);
		
		

	}
	public boolean testInformation(){
		if(nom.getText().trim().equals("")){
			 warningMessage("vous devez entrer votre nom","Zone Nom");
			return false;
		}
		if(prenom.getText().trim().equals("")){
			warningMessage("vous devez entrer votre prenom","Zone Prenom");
			return false;
		}
		if(!age.getText().trim().matches("\\d+") || age.getText().trim().equals("")) {
			age.setText("");
			warningMessage("vous devez entrer votre age","Zone Age");
			return false;
		}
		//"\d" correspond a un chiffre
		if(!tel.getText().trim().matches("^0[0-689](-[\\d]{2}){4}$") || tel.getText().trim().equals("")) {
			tel.setText("");
			warningMessage("seul les numeros en france sont autorises","Zone Tel");
			return false;
		}
		if(adr.getText().trim().equals("")){
			 warningMessage("vous devez entrer votre adresse","Zone Adresse");
			return false;
		}
		if(!code.getText().trim().matches("\\d+") || code.getText().trim().equals("")) {
			warningMessage("vous devez entrer votre code postale","Zone Code Postale");
			return false;
		}
		if(commune.getText().trim().equals("")){
			 warningMessage("vous devez entrer votre ville","Zone Commune");
			return false;
		}
		return true;
	}
}
