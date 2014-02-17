import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * <p>Professeur chargé du cours : <b>Rahim KACIMI</b><br/>
 * Projet de Réseau<br/>
 * Groupe 3.1<br/>
 * Annee scolaire : 2012-2013</p>
 * <p>Cette classe construit l'interface graphique du serveur et le lance sur un
 * numéro de port donnée en paramètre</p>
 * @author Diallo Alpha oumar Binta (21007631)
 * @version 1.0
 */
public class Gui extends javax.swing.JFrame {

    /**
     * scrool bar pour le defilement si la zone d'affichage est rempli
     */
    private javax.swing.JScrollPane scrollPane;
    /**
     * la zone d'affichage
     */
    private javax.swing.JTextArea textArea;
    /**
     * Constructeur de l'interface graphique
     * @param args <code>String</code> un numéro de port
     * lance le serveur le port donnée en paramètre
     */
    public Gui(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();
        int port = -1;
        /*
         * si le numéro de port est passé en ligne de commande, il correspond au
         * premier argument, sinon on demande à l'utilisateur d'entrer un numéro
         * de port. Si ce dernier n'est pas valide, on quitte le programme
         */
        if(args.length == 1){
            try{
                port = Integer.parseInt(args[0]);
            }catch(HeadlessException | NumberFormatException e){
                this.write(MultiThreadServer.SERVER_NAME + 
                        ": port\n\tport: the port number. ex: 7000\n");
                }
        }else{
            while(port <= 0){
                try{
                    String str = JOptionPane.showInputDialog("HTTP "
                            + "SERVER -> enter a port number");
                    if(str == null){
                        System.exit(0);
                    }else {
                        port = Integer.parseInt(str);
                    }
                }catch(HeadlessException | NumberFormatException e){
                    JOptionPane.showMessageDialog(null, MultiThreadServer.SERVER_NAME + 
                        ": port\n\tport: the port number. ex: 7000\n",
                            MultiThreadServer.SERVER_NAME, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
        new MultiThreadServer(this, port);
    }

    /**
     * cette méthode crée les composants de l'interface graphique
     */
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(MultiThreadServer.SERVER_NAME);
        setPreferredSize(new Dimension(520, 450));
        textArea.setEditable(false);
        textArea.setBackground(new java.awt.Color(0, 0, 0));
        textArea.setColumns(20);
        textArea.setForeground(new java.awt.Color(0, 153, 0));
        textArea.setRows(5);
        scrollPane.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              this_windowClosed(e);
            }
          });

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new Gui(args);
    }
    /**
     * Cette méthode affiche str sur l'interface graphique
     * @param str 
     */
    public void write(String str){
        textArea.append(str + "\n");
    }
    /**
     * Cette méthode est appélée lorqu'on ferme l'interface graphique 
     * (fermeture du serveur), les traces des évènements sont enrégistrés dans 
     * le fichier log.txt
     * @param e <code>WindowEvent</code>
     */
    public void this_windowClosed(WindowEvent e) {
        write("\nCreating log file ...");
        try (PrintWriter out = new PrintWriter (
                         new BufferedWriter (new FileWriter (
                             new File(MultiThreadServer.ROOT + "log.txt"))))) {
            
            String line;
            if((line = textArea.getText()) != null){
                out.println(line);
            }
        } catch (IOException ex) {
            System.err.println("Error on reating log file: " + ex.getMessage());
            System.exit(1);
        }
        System.out.println("Creating log file sucesss");
        System.exit(1);   
    }
}
