
package corrector.treatement;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;


/** 
 * <p>Title : DialogInfo </p>
 * <p>this class display a dialog box for choosing method and parameters for the plane 
 * rectification</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public class DialogInfo extends JDialog {
        private int method = 0;
        private double ratio;
        private int typeOfRectification;
        
	private JLabel ratioLabel, modelLabel, heightLabel,widthLabel,sizeLabel;
	private JRadioButton neighbor, billinear, bicubic;
	private JComboBox model, sizeCombo;
	private JFormattedTextField heightField, widthField;
	private JFormattedTextField ratioField;
        
        final JCheckBox boxRapport = new JCheckBox();
        final JCheckBox boxDimension = new JCheckBox();
        final JCheckBox boxModele = new JCheckBox();
    
    
	/**
	 * Constructeur
	 * @param parent a JFrame
	 * @param title  a title for the dialog box
	 * @param modal  a boolean
	 */
	public DialogInfo(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		setSize(550, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		initComponent();
                setVisible(true);
	}	
	/**
         * method to init the component
         */
	private void initComponent(){		
            ratioField = new JFormattedTextField();
            heightField = new JFormattedTextField();
            widthField = new JFormattedTextField();
            ratioField.addKeyListener(new KeyBoardListener(ratioField));
            heightField.addKeyListener(new KeyBoardListener(heightField));
            widthField.addKeyListener(new KeyBoardListener(widthField));
            
            JPanel panRapport = new JPanel();
            panRapport.setBackground(Color.white);
            ratioField.setPreferredSize(new Dimension(100, 25));
            ratioField.setToolTipText("entrer le rapport entre les "
                    + "dimensions de l'objet");
            panRapport.setBorder(BorderFactory.createTitledBorder("Rapport "
                    + "entre les dimensions"));
            ratioLabel = new JLabel("Saisir le rapport:");
            panRapport.add(boxRapport);
            panRapport.add(ratioLabel);
            panRapport.add(ratioField);
            ratioField.setEnabled(false);
            
            JPanel panModele = new JPanel();
            panModele.setBackground(Color.white);
            panModele.setBorder(BorderFactory.createTitledBorder("Modèle"));
            model = new JComboBox();
            model.addItem("A4");
            model.addItem("A3");
            model.setEnabled(false);
            modelLabel = new JLabel("Choisir un modèle : ");
            panModele.add(boxModele);
            panModele.add(modelLabel);
            panModele.add(model);
            
            JPanel panSize = new JPanel();
            panSize.setBackground(Color.white);
            panSize.setBorder(BorderFactory.createTitledBorder(
                    "Taille de l'image"));
            sizeCombo = new JComboBox();
            sizeCombo.addItem("même taille que l'image");
            sizeCombo.addItem("même taille que l'objet");
            sizeLabel = new JLabel("Choisir un type : ");
            panSize.add(sizeLabel);
            panSize.add(sizeCombo);
           
            JPanel panMethod = new JPanel();
            panMethod.setBackground(Color.white);
            panMethod.setBorder(BorderFactory.createTitledBorder(
                    "Choix de la méthode"));
            neighbor = new JRadioButton("Plus proche voisin");
            neighbor.setSelected(true);
            billinear = new JRadioButton("Interpolation billinéaire");
            bicubic = new JRadioButton("Interpolation bicubique");
            ButtonGroup bg = new ButtonGroup();
            bg.add(neighbor);
            bg.add(billinear);
            bg.add(bicubic);
            panMethod.add(neighbor);
            panMethod.add(billinear);
            panMethod.add(bicubic);
            
            
            //panneau pour les dimensions
            JPanel panDimension = new JPanel();
            panDimension.setBackground(Color.white);
            panDimension.setBorder(BorderFactory.createTitledBorder(
                    "Dimension de l'objet"));
            heightLabel = new JLabel("Hauteur : ");
            heightField.setPreferredSize(new Dimension(90, 25));
            heightField.setToolTipText("entrer la hauteur de l'objet");
            widthLabel = new JLabel("Largeur : ");
            widthField.setPreferredSize(new Dimension(90, 25));
            widthField.setToolTipText("entrer la largeur de l'objet");
            heightField.setEnabled(false);
            widthField.setEnabled(false);
            panDimension.add(boxDimension);
            panDimension.add(heightLabel);
            panDimension.add(heightField);
            panDimension.add(widthLabel);
            panDimension.add(widthField);
            
            boxRapport.addChangeListener(new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e) {
                    ratioField.setEnabled(boxRapport.isSelected());
                    widthField.setEnabled(false);
                    heightField.setEnabled(false);
                    model.setEnabled(false);
                    boxDimension.setSelected(false);
                    boxModele.setSelected(false);
                }
                
            });
            boxDimension.addChangeListener(new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e) {
                    heightField.setEnabled(boxDimension.isSelected());
                    widthField.setEnabled(boxDimension.isSelected());
                    model.setEnabled(false);
                    ratioField.setEnabled(false);
                    boxModele.setSelected(false);
                    boxRapport.setSelected(false);
                }
                
            });
            boxModele.addChangeListener(new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e) {
                    model.setEnabled(boxModele.isSelected());
                    widthField.setEnabled(false);
                    heightField.setEnabled(false);
                    ratioField.setEnabled(false);
                    boxDimension.setSelected(false);
                    boxRapport.setSelected(false);
                }
                
            });
                    
            
            JPanel content = new JPanel();
            content.setBackground(Color.white);
            content.add(panRapport);
            content.add(panModele);
            content.add(panDimension);
            content.add(panMethod);
            content.add(panSize);
            
            
            JPanel control = new JPanel();
            JButton okBouton = new JButton("OK");
            
            okBouton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        setRatio();
                        setMethod();
                        typeOfRectification = sizeCombo.getSelectedIndex();
                        if(ratio != 0)
                            setVisible(false);
                    }		
            });
            
            JButton cancelBouton = new JButton("Annuler");
            cancelBouton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        ratio = 0;
                        setVisible(false);
                    }			
            });
            
            control.add(okBouton);
            control.add(cancelBouton);
            getContentPane().add(content, BorderLayout.CENTER);
            getContentPane().add(control, BorderLayout.SOUTH);
        
	}
        /**
         * 
         * @return <code>double</code> the ratio
         */
        public double getRatio(){
            return ratio;
        }
        /**
         * set the ratio to use
         */
        public void setRatio(){
            if(boxDimension.isSelected()){
                try{
                    double height = Double.parseDouble(heightField.getText());
                    double width = Double.parseDouble(widthField.getText());
                    ratio = height/width;
                }
                catch(NumberFormatException e){
                    ratio = 0;
                }
                
            }else if(boxRapport.isSelected()){
                ratio = Double.parseDouble(ratioField.getText());
            }else if(boxModele.isSelected()){
                int selected = model.getSelectedIndex();
                if(selected == 0){
                    //A4
                    double height = 210;
                    double width = 297;
                    ratio = height/width;                   
                }else if(selected == 1){
                    //A3
                    double height = 297;
                    double width = 420;
                    ratio = height/width;
                }
            }
        }
        /**
         * 
         * @return <code>Integer</code> the method to use
         */
        public int getMethod(){
            return method;
        }
        /**
         * set the method to use
         */
        public void setMethod(){
           if (neighbor.isSelected())
               method = 0;
           else if(billinear.isSelected()) 
               method =1;
           else if(bicubic.isSelected())
               method=2; 
        }
        /**
         * 
         * @return an integer the type of rectification to apply
         */
        public int getTypeOfRectification(){
            return typeOfRectification;
        }
}
/** 
 * <p>Title : KeyBoardListener </p>
 * <p>this class is used to control the post user input attribute it to the 
 * component on which the arrest is made​​, a message is displayed if an error 
 * occurs and the title of the window</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
class KeyBoardListener implements KeyListener{
	JTextComponent jtc;
	String message;
	String title;
	
	/**
         * 
         * @param jtc a JTextComponent
         * @param message the message to show
         * @param title the title of the box
         */
	public KeyBoardListener(JTextComponent jtc,String message,
                String title) {
		this.jtc = jtc;
		this.message=message;
		this.title=title;
	}
        /**
         * 
         * @param jtc a JTextComponent
         */
        public KeyBoardListener(JTextComponent jtc) {
            this.jtc = jtc;
	}

        @Override
	public void keyReleased(KeyEvent event) {
            if(event.getKeyCode() != 127 && event.getKeyCode() != 8)
		if(!isNumeric(event.getKeyChar())){
			jtc.setText(jtc.getText().replace(
                                String.valueOf(event.getKeyChar()), ""));
		}			
	}
	
	
        @Override
	public void keyPressed(KeyEvent event) {}
        @Override
	public void keyTyped(KeyEvent event) {}
	
	/**
	 * @param carac a char
	 * @return Boolean true if the char is numeric
	 */
	private boolean isNumeric(char carac){
            if(carac != '.'){
                try {
                    Integer.parseInt(String.valueOf(carac));
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            }
            return true;
    }
}