import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Professeur chargé du cours : <b>Rahim KACIMI</b><br/>
 * Projet de Réseau<br/>
 * Groupe 3.1<br/>
 * Annee scolaire : 2012-2013</p>
 * <p>Cette classe lance le serveur sur un port donnée en arguument
 * A chaque connection d'un client, un nouveau Thread (ServerThread) est lancé
 * pour traiter ces requêtes, celà nous permet de recevoir plusieurs clients en 
 * parallèle</p>
 * @author Diallo Alpha oumar Binta (21007631)
 * @version 1.0
 */
public class MultiThreadServer {
    /**
     * identifiant du client
     */
    private static long clientId = 0;
    /**
     * om du serveur
     */
    public static final String SERVER_NAME = "OUMAR-HTTPSERVER";
    /**
     * constante HTTP_1_1
     */
    public static final String HTTP_1_1 = "HTTP/1.1";
    /**
     * constante HTTP_1_0
     */
    public static final String HTTP_1_0 = "HTTP/1.0";
    /**
     * chemin vers le repertoire contenant les fichiers du serveur
     */
    public static final String ROOT = "../www.data/";
    /**
     * chemin vers le fichier bad_request.html
     */
    public static final String BAD_REQUEST_FILE = ROOT + "file_system/bad_request.html";
    /**
     * chemin vers le fichier extension.txt
     */
    private static final String EXTENSION_FILE = ROOT + "file_system/extension.txt";
    /**
     * chemin vers le fichier not_implemented.html
     */
    public static final String NOT_IMPLEMENTED_FILE = ROOT + "file_system/not_implemented.html";
    /**
     * chemin vers le fichier not_found.html
     */
    public static final String NOT_FOUND_FILE = ROOT + "file_system/not_found.html";
    /**
     * chemin vers le fichier unauthorized.html
     */
    public static final String UNAUTHORZED_FILE = ROOT + "file_system/unauthorized.html";
    /**
     * chemin vers le fichier server_error.html
     */
    public static final String SERVER_ERROR = ROOT + "file_system/server_error.html";
    /**
     * chemin vers le fichier length_required.html
     */
    public static final String LENGTH_REQUIRED_FILE = ROOT + "file_system/length_required.html";
    /**
     * chemin vers le fichier created.html
     */
    public static final String CREATED_FILE = ROOT + "file_system/created.html";
    /**
     * chemin vers le fichier sucess.html
     */
    public static final String SUCESS_FILE = ROOT + "file_system/sucess.html";
    /**
     * chemin vers le fichier index.html
     */
    public static final String INDEX = ROOT + "index.html";
    /**
     * une Map contenant des extensions de fichier pour définir le Content-Type
     */
    public static Map<String, String> extension = new HashMap<>();
    /**
     * le numéro de port
     */
    private int port;
    /**
     * l'interface graphique chargé d'afficher les opérations sur le serveur
     */
    public  static Gui gui;
    /**
     * Constructeur du serveur
     * @param gui <code>Gui</code> l'interface graphique
     * @param port <code>int</code> le numéro de port
     */
    public MultiThreadServer(Gui gui, int port){
        MultiThreadServer.gui = gui;
        this.port = port;
        launch();
    }
    /**
     * Cette methode lance le serveur
     */
    private void launch(){
        gui.write(SERVER_NAME + " v.1\nCoded by Diallo Alpha Oumar Binta"
                + " (21007631)" +"\n<aob.diallo@gmail.com>\n\n" + new Date());
        /**
         * la socket d'écoute
         */
        ServerSocket serverSocket = null;
        /**
         * la socket client
         */
        Socket clientSocket = null;
        try {
            //Chargement des extensions pour définir le Content-Type
            gui.write("Uploading extensions...");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                                                         new FileInputStream(
                                                         new File(EXTENSION_FILE))))) {
                String str[];
                String line;
                while ((line = br.readLine())!=null){
                    str = line.split("=");
                    extension.put(str[0], str[1]);
                }
                gui.write("Uploading extensions terminated");
            }
        } catch (IOException ex) {
            gui.write("Uploading extensions failed\n\t" + ex.getMessage());
        }
        /*
         * création de la socket d'écoute du serveur
         */
        try{
            gui.write("Trying to bind to localhost on port " + port);
            serverSocket = new ServerSocket(port);
            gui.write("The server is openned on port " + port);
        }catch(IOException | IllegalArgumentException e){
            gui.write("Fatal Error: could not listen on port " + port + "\n\t"
                    + e.getMessage());
            System.err.println("Fatal Error: could not listen on port " + port + "\n\t"
                    + e.getMessage());
            System.exit(0);
        }
        gui.write("\nReady, Waiting for requests...\n");
        //boucle d'attente des clients
        while(true){
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException ex) {
                gui.write("ERROR on accept:" + ex.getMessage());
            }
            //lancement du thread charger d'exécuter la requete du client
            new ServerThread(clientSocket, ++clientId).start();
        }
    }

}
