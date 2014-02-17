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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import oumar.Enumeration.Theme;
import oumar.Structure.Livre;

/**
 * <b>Description</b><i>cette classe heritiere de la classe Conteneur sert a afficher la description d'un livre 
 * de la bibliotheque </i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class DescriptionLivre extends Conteneur{
	private Livre livre;
    private JLabel labelTitre,labelCategorie,labelPage,labelTheme;
    private JLabel image;
    private JLabel titre,auteur,page;
    private JTextArea resume;
    private JLabel labelAuteur;
    private Dimension dimLabel,dimField;
    private JCheckBox categorie;
	private List<Theme> listTheme=new ArrayList<Theme>();

	public DescriptionLivre(Dimension dim,Livre l) {
		super(dim);
		this.livre=l;
		initPanel();
	}

public void initPanel(){
        labelAuteur = new JLabel("Auteur :");
        auteur = new JLabel(livre.getAuteur());
        labelTitre = new JLabel("Titre :");
        titre = new JLabel(livre.getTitre());
        labelCategorie = new JLabel("Categorie :");
        categorie = new JCheckBox(""+livre.getCategorie(),true);
        categorie.setEnabled(false);
        categorie.setBackground(Color.white);        
        labelPage = new JLabel("Page :");
        new JLabel("Image");
        labelTheme=new JLabel("Theme :");
        //reduction de la dimension de l'image
        Image icon=new ImageIcon(livre.getImage()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        image = new JLabel(new ImageIcon(icon)) ;
        image.setPreferredSize(new Dimension(150,150));
        image.setBorder(BorderFactory.createLineBorder(Color.red));
        
        resume = new JTextArea(livre.getResume());
        resume.setEditable(false);
        dimLabel=new Dimension(80,20);
        dimField=new Dimension(150,20);
        page = new JLabel(livre.getNbrePage());		
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
		/*
		 * vu l'inexistance des variables dynamiques en java, on passe par une map dont la cle represente le nom de notre variable
		 * on recupere la cle contenant une JCheckBox auquel on fait un setEnable false pour qu'elle ne soit pas modifiable
		 */
		Map <String, JCheckBox> map = new HashMap <String, JCheckBox>();
		for(int i=0;i<livre.getTheme().size();i++){
			map.put(""+livre.getTheme(), new JCheckBox(""+livre.getTheme().get(i),true));
			map.get(""+livre.getTheme()).setEnabled(false);
			map.get(""+livre.getTheme()).setBackground(Color.white);
			panTheme.add(map.get(""+livre.getTheme()));
		}
		
		JPanel panPage = new JPanel();
		panPage.setBackground(Color.white);
		page.setPreferredSize(dimField);
		labelPage.setPreferredSize(dimLabel);
		panPage.setLayout(new FlowLayout(FlowLayout.LEADING));
		panPage.add(labelPage);
		panPage.add(page);    
		JPanel panImage = new JPanel();
		panImage.setBackground(Color.white);
		panImage.add(image);
		
		//panneau contenant les infos sur la personne
		JPanel descriptif=new JPanel(new GridLayout(6,1));
		descriptif.setBorder(BorderFactory.createTitledBorder("Description du Livre"));
		descriptif.setBackground(Color.white);
		descriptif.add(panAuteur);
		descriptif.add(panTitre);
		descriptif.add(panCategorie);
		descriptif.add(panPage);
		descriptif.add(panTheme);
		
		JPanel haut=new JPanel();
		haut.setBackground(Color.white);
		haut.add(descriptif,BorderLayout.WEST);
		haut.add(panImage,BorderLayout.EAST);
		
		panel.setLayout(new GridLayout(2,0));
		panel.add(haut);
		panel.add(new JScrollPane(resume));

}
}
