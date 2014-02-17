/**
 * 
 */
package oumar.apply.calc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author diallo
 *
 */
public class Calculatrice extends JFrame{
	//le conteneur des interfaces
	private JPanel container = new JPanel();
	
	//tableau contenant les elements des bouttons
	String[] tab_string = {"1","2","3","4","5","6","7","8","9","0",".","=","C","+","-","*","/"};
	//tableau qui contiendra nos bouttons
	JButton[] tab_button = new JButton[tab_string.length];
	
	//l'ecran qui contiendra nos resultats de calcul (JLabel)
	private JLabel ecran = new JLabel();
	//defintion des dimmensions pour les ecrans (hauteur,largeur)
	private Dimension dim = new Dimension(50,40);
	private Dimension dim2 = new Dimension(50,31);
	
	//variable qui contiendra le reultat de l'operation
	private double chiffre1;
	//booleen permettant de savoir s'il s'agit d'un operateur ou d'une mise a jour
	private boolean clicOperateur = false, update = false;
	//l'operateur
	private String operateur = "";
	
	//fenetre de la calculatrice
	public Calculatrice(){
		//fixer la taille
		this.setSize(240, 260);
		//nom de la fenetre
		this.setTitle("Calculatrice");
		//fermeture de la fenetre au moment du clic sur ferme
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//centrer la fenetre
		this.setLocationRelativeTo(null);
		//empecher l'agrandissement
		this.setResizable(false);
		
		//initialisation des composants de la calculette
		initComposant();
		
		//annoncer a la fenetre que le container sera son contentPane
		this.setContentPane(container);
		//afficher la fenetre
		this.setVisible(true);
	}
	
	//initialisation des composants
	private void initComposant(){
		//creation de la police et de l'ecran qui contiendra le resultat ainsi que sa dimension
		Font police = new Font("Arial",Font.BOLD,20);
		ecran = new JLabel("0");
		ecran.setFont(police);
		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setPreferredSize(new Dimension(220,20));
		
		//creation  des conteneurs qui contiendront les chiffres et les operateurs et l'ecran
		JPanel operateur = new JPanel();        
        operateur.setPreferredSize(new Dimension(55, 225));
        JPanel chiffre = new JPanel();
        chiffre.setPreferredSize(new Dimension(165, 225));
        JPanel panEcran = new JPanel();
        panEcran.setPreferredSize(new Dimension(220, 30));
        
        //creation et ajout des bouttons a leur conteneur
        for(int i = 0; i < tab_string.length; i++)
        {
            
            tab_button[i] = new JButton(tab_string[i]);
            tab_button[i].setPreferredSize(dim);
            
            switch(i){
            
            	case 11 :
            		tab_button[i].addActionListener(new EgalListener());
            		chiffre.add(tab_button[i]);
            		break;
            	
            	case 12 :
            		tab_button[i].setForeground(Color.red);
                    tab_button[i].addActionListener(new ResetListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
            		break;
            	    
            	case 13 :
            		tab_button[i].addActionListener(new PlusListener());
            		tab_button[i].setPreferredSize(dim2);
            		operateur.add(tab_button[i]);
            		break;
            	
            	case 14 :
            		tab_button[i].addActionListener(new MoinsListener());
            		tab_button[i].setPreferredSize(dim2);
            		operateur.add(tab_button[i]);
            		break;	
            	
            	case 15 :	
            		tab_button[i].addActionListener(new MultiListener());
            		tab_button[i].setPreferredSize(dim2);
            		operateur.add(tab_button[i]);
            		break;
                
            	case 16 :
            		tab_button[i].addActionListener(new DivListener());
            		tab_button[i].setPreferredSize(dim2);
            		operateur.add(tab_button[i]);
            		break;
            	                    	
            	default :
            		chiffre.add(tab_button[i]);
            		tab_button[i].addActionListener(new ChiffreListener());
            		break;
            }
            
        }
        //ajout de l'ecran au conteneur panEcran
        panEcran.add(ecran);
        //bordure du panEcran
        panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
        //ajout des conteneurs au conteneur principal
        container.add(panEcran, BorderLayout.NORTH);
        container.add(chiffre, BorderLayout.CENTER);
        container.add(operateur, BorderLayout.EAST);
	}
	
	private void calcul(){
        if(operateur.equals("+"))
        {
                chiffre1 = chiffre1 + Double.valueOf(ecran.getText()).doubleValue();
                ecran.setText(String.valueOf(chiffre1));
        }
                
        if(operateur.equals("-"))
        {
                chiffre1 = chiffre1 - Double.valueOf(ecran.getText()).doubleValue();
                ecran.setText(String.valueOf(chiffre1));
        }               
        
        if(operateur.equals("*"))
        {
                chiffre1 = chiffre1 * Double.valueOf(ecran.getText()).doubleValue();
                ecran.setText(String.valueOf(chiffre1));
        }       
                
        if(operateur.equals("/"))
        {
                try{
                        chiffre1 = chiffre1 / Double.valueOf(ecran.getText()).doubleValue();
                        ecran.setText(String.valueOf(chiffre1));
                }catch(ArithmeticException e){
                        ecran.setText("0");
                }
        }
	}
	
	class ChiffreListener implements ActionListener{
		 
        public void actionPerformed(ActionEvent e) {
                //On affiche le chiffre en plus dans le label
                String str = ((JButton)e.getSource()).getText();
                
                if(update)
                {
                        update = false;
                }
                else
                {
                        if(!ecran.getText().equals("0"))
                                str = ecran.getText() + str;
                }
                
                ecran.setText(str);
        }
        
}


class EgalListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
                calcul();
                update = true;
                clicOperateur = false;
        }
        
}


class PlusListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
                
                if(clicOperateur)
                {
                        calcul();
                        ecran.setText(String.valueOf(chiffre1));
                }
                else
                {
                        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                        clicOperateur = true;
                }
                operateur = "+";
                update = true;
        }
        
}

class MoinsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
                if(clicOperateur)
                {
                        calcul();
                        ecran.setText(String.valueOf(chiffre1));
                }
                else
                {
                        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                        clicOperateur = true;
                }
                operateur = "-";
                update = true;
        }
        
}


class MultiListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
                if(clicOperateur)
                {
                        calcul();
                        ecran.setText(String.valueOf(chiffre1));
                }
                else
                {
                        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                        clicOperateur = true;
                }
                operateur = "*";
                update = true;
        }
        
}


class DivListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
                if(clicOperateur)
                {
                        calcul();
                        ecran.setText(String.valueOf(chiffre1));
                }
                else
                {
                        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                        clicOperateur = true;
                        
                }
                operateur = "/";
                update = true;
        }
        
}


class ResetListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
                clicOperateur = false;
                update = true;
                chiffre1 = 0;
                operateur = "";
                ecran.setText("");
        }
        
}


	
	

}
