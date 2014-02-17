import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * <p>Professeur chargé du cours : <b>Rahim KACIMI</b><br/>
 * Projet de Réseau<br/>
 * Groupe 3.1<br/>
 * Annee scolaire : 2012-2013</p>
 * <p>Cette classe héritante de Thread est lancé à chaque fois q'un client se 
 * connecte, elle execute la requête envoyé par le client.</p>
 * @author Diallo Alpha oumar Binta (21007631)
 * @version 1.0
 */
public class ServerThread extends Thread{
    /**
     * le charset utilisé pour le transfert de fichier
     */
    private static final String CHARSET = "ISO-8859-1";
    /**
     * la socket du client
     */
    private Socket clientSocket = null;
    /**
     * flux d'entrée sur la socket du client
     */
    private BufferedReader is = null;
    /**
     * flux de sortie sur la socket du client
     */
    private PrintWriter os = null;
    /**
     * le fichier à renvoyer ou à sauvegarder
     */
    private File file = null;
    /**
     * le nom du fichier à renvoyer ou à sauvegarder
     */
    private String fileName;
    /**
     * protocole utilisé
     */
    private String protocol;
    /**
     * taille maximale des buffers
     */
    private final int BLOCK_SIZE = 1024;
    /**
     * Cette méthode affiche la chaine <code>str</code> sur l'interface
     * graphique
     * @param str <code>String</code>
     */
    public void show(String str){
        MultiThreadServer.gui.write(str);
    }
    /**
     * Constructeur du Thread chargé de traiter la requête du client
     * @param clientSocket <code>Socket</code> la socket du client
     * @param id <code>long</code> l'identifiant du client
     */
    public ServerThread(Socket clientSocket, long id) {
        super("Client " + id + " on " + clientSocket.getInetAddress()
                .getHostAddress() + " : " + clientSocket.getPort());
        this.clientSocket = clientSocket;
    }
    /**
     * Cette méthode execute la requete envoyé en paramètre
     * @param request <code>String</code> la requête du client
     * @return <code>boolean</code> vrai si la requête est executé faux sinon
     */
    private boolean execute(String[] request){
        /* detection du type de service */
        String serviceType = request[0].toUpperCase();
        switch (serviceType) {
            case "GET":
                getService();
                break;
            case "PUT":
                putService();
                break;
            default:
                return false;
        }
        return true;
    }
    /**
     * Cette fonction ferme la connexion du client
     */
    private void closeSocket(){
        try { 
            file = null;
            os.close();
            is.close();
            clientSocket.close();
        } catch (IOException ex) {
            show("ERROR on " + getName() + " closing closeSocket():\n\t"
                    + ex.getMessage());
        }
    }
    /**
     * Cette méthode écrit sur os le contentu lut sur is.
     * @param is un flux de type <code>InputStream</code>
     * @param os un flux de type <code>OutputStream</code>
     * @param close <code>boolean</code> pour fermer les flux
     */
    private void sendFile(InputStream is, OutputStream os, boolean closeIs, 
            boolean closeOs){
        try {
            byte buffer[] = new byte[BLOCK_SIZE];
            int n;
            while((n = is.read(buffer)) != -1) {
                os.write(buffer, 0, n);
            }
        } catch (IOException ex) {
            show("ERROR on sending file:\n\t" + ex.getMessage());
        }
        if(closeIs){
            try {
                is.close();
            } catch (IOException ex) {
                show("ERROR on closing is stream:\n\t"
                        + ex.getMessage());
            }
        }
        if(closeOs){
            try {
                os.close();
            } catch (IOException ex) {
                show("ERROR on closing os stream:\n\t"
                        + ex.getMessage());
            }
        }
    }
    /**
     * Cette méthode envoie le header (entête de la réponse du serveur) sur la
     * socket du client
     * @param status un entier <code>int</code> indiquant le status (code de
     * retour) 
     */
    private void httpHeader(int status){
        String header = protocol + " " + status;
        /*
         * le message associé au code de retour
         */
        switch(status){
            case 200 :  header += " OK";
                        break;
            case 201 :  header += " Created";
                        break;
            case 400 :  header += " Bad Request";
                        break;
            case 404 :  header += " Not Found";
                        break;
            case 401 :  header += " Unauthorized";
                        break;
            case 411 :  header += " Length Required";
                        break;
            case 500 :  header += " Internal Server Error";
                        break;
            case 501 :  header += " Not Implemented";
                        break;      
        }
        os.println(header);
        os.println("Date: " + new Date());
        //Nous ne pouvons pas gérer les connexions persistantes.(HTTP/1.0)
        os.println("Connection: close");
        os.println("Server: " + MultiThreadServer.SERVER_NAME);
        /*
         * Construire le bon Content-Type pour l'en-tête.
         * Ceci afin que le navigateur sache quoi faire avec le fichier, 
         * Le navigateur ne s'occupe pas de l'extension du fichier, c'est le 
         * travail du serveur de lui tenir informer afin qu'il sache comment
         * l'interpréter.
         * Ex: si le content type n'est pas spécifié quand on envoie une image,
         * il sera affiché sur le naviguateur sous forme de texte.
         */
        String ext = file.getName().substring(file.getName().lastIndexOf("."));
        if(MultiThreadServer.extension.containsKey(ext)){
            os.println("Content-Type: " + MultiThreadServer.extension.get(ext));
        }else{
            os.println("Content-Type: text/html");
        }
        //taille du fichier
        os.println("Content-length: " + file.length());
        //marque de la fin de l'entête (une ligne vide)
        os.println();
    }
    
    /**
     * Cette méthode appélé lorsqu'un client se connecte et envoie une requête 
     * sur le serveur.
     */
    private void processRequest(){
        try {
            is = new BufferedReader(new InputStreamReader(
                                            clientSocket.getInputStream(), CHARSET));
            os = new PrintWriter(new BufferedWriter(
                                    new OutputStreamWriter(
                                        clientSocket.getOutputStream())),true);
            
            /* *
             * on lit la première ligne contenant la requête envoyer par
             * le client.
             * on découpe la requête afin de récupérer la méthode, le nom du 
             * fichier et le protocole.
             * Si aucune ligne n'est lu où la ligne ne contient pas les trois
             * informations nécessaires, on envoie au client un message d'erreur
             * (bad request)
             * Sinon on vérifie le protocole, s'il est différent de
             * (HTTP/1.0 ou HTTP/1.1) => envoie de BAD REQUEST
             * Sinon si le service démandé est implémenté, le service est 
             * executé
             * Sinon on envoie un message d'erreur NOT IMPLEMENTED
             * En cas d'erreur interne on envoie un erreur de type 500 
             */
            String request[] = is.readLine().split(" ");
            if(request != null && request.length == 3){
                show(getName() + " request:\n\t" + request[0] + " "+ request[1]
                        +  " " + request[2]);
                if(checkProtocol(request)){
                    /*
                     * suppression de '/' s'il est placé au début du nom 
                     * du fichier
                     */
                    if(request[1].length() == 1){
                        fileName = MultiThreadServer.INDEX;
                    }else if(request[1].charAt(0) == '/'){
                        fileName = MultiThreadServer.ROOT + request[1].substring(1);
                    }else{
                        fileName = MultiThreadServer.ROOT + request[1];
                    }
                    /*
                     * execution du service
                     */
                    if(!execute(request)){
                        //NOT IMPLEMENTED
                        notImplemented();
                    }
                }else{
                    //bad request (protocol maybe)
                    badRequest();
                }
            }else{
                //bad request
                badRequest();
            }
        } catch (IOException | NullPointerException ex) {
            show("ERROR on " + getName() + " request:\n\t"
                    + ex.getMessage());
            //ERREUR INTERNE DU SERVEUR
            internalError();
        }
        finally{
            show(getName() + " disconnected");
            closeSocket();
        }
    }
    /**
     * Cette méthode lance le thread chargé du traitement
     */
    @Override
    public void run(){
        show("Handle new connection => " + getName());
        processRequest();        
    }
    /**
     * 
     * @param request <code>String</code> la requête du client
     * @return <code>boolean</code> vrai si le protocole est bon, faux sinon
     */
    private boolean checkProtocol(String[] request){
        
        switch (request[2].toUpperCase()) {
            case MultiThreadServer.HTTP_1_0:
                protocol = MultiThreadServer.HTTP_1_0;
                break;
            case MultiThreadServer.HTTP_1_1:
                protocol = MultiThreadServer.HTTP_1_0;
                break;
            default:
                return false;
        }
        return true;
    }   
    /**
     * Cette méthode renvoie au client un message d'erreur de type 400
     */
    private void badRequest(){
        file = new File(MultiThreadServer.BAD_REQUEST_FILE);
        httpHeader(400);
        try {
            sendFile(new BufferedInputStream(
                        new FileInputStream(file)), clientSocket.getOutputStream(),
                                            true, false);
        } catch (IOException ex) {
            show("ERROR on " + getName() + " badRequest():\n\t"
                    + ex.getMessage());
        }
    }
    /**
     *  Cette méthode renvoie au client un message d'erreur de type 500
     */
    private void internalError(){
        file = new File(MultiThreadServer.SERVER_ERROR);
        httpHeader(500);
        try {
            sendFile(new BufferedInputStream(
                        new FileInputStream(file)), clientSocket.getOutputStream(),
                                            true, false);
        } catch (IOException ex) {
            show("ERROR on " + getName() + " internalError():\n\t"
                    + ex.getMessage());
        }
    }
    /**
     *  Cette méthode renvoie au client un message d'erreur de type 501
     */
    private void notImplemented(){
        file = new File(MultiThreadServer.NOT_IMPLEMENTED_FILE);
        httpHeader(501);
        try {
            sendFile(new BufferedInputStream(
                        new FileInputStream(file)), clientSocket.getOutputStream(),
                                true, false);
        } catch (IOException ex) {
            show("ERROR on " + getName() + " notImplemented():\n\t"
                    + ex.getMessage());
        }
    }
    /**
     *  Cette méthode renvoie au client un message d'erreur de type 404
     */
    private void notFound(){
        file = new File(MultiThreadServer.NOT_FOUND_FILE);
        httpHeader(404);
        try {
            sendFile(new BufferedInputStream(
                        new FileInputStream(file)), clientSocket.getOutputStream(),
                                                true, false);
        } catch (IOException ex) {
            show("ERROR on " + getName() + " notFound():\n\t"
                    + ex.getMessage());
        }
    }
    /**
     *  Cette méthode renvoie au client un message d'erreur de type 401
     */
    private void unauthorized(){
        file = new File(MultiThreadServer.UNAUTHORZED_FILE);
        httpHeader(401);
        try {
            sendFile(new BufferedInputStream(
                        new FileInputStream(file)),clientSocket.getOutputStream(),
                                            true, false);
        } catch (IOException ex) {
            show("ERROR on " + getName() + " unauthorized():\n\t"
                    + ex.getMessage());
        }
    }
    /**
     *  Cette méthode renvoie au client un message d'erreur de type 411
     */
    private void length_Required(){
        file = new File(MultiThreadServer.LENGTH_REQUIRED_FILE);
        httpHeader(411);
        try {
            sendFile(new BufferedInputStream(
                        new FileInputStream(file)),clientSocket.getOutputStream(),
                                            true, false);
        } catch (IOException ex) {
            show("ERROR on " + getName() + " length_Required():\n\t"
                    + ex.getMessage());
        }  
    }
    private void sucess(){
        file = new File(MultiThreadServer.SUCESS_FILE);
        httpHeader(200);
        try {
            sendFile(new BufferedInputStream(
                        new FileInputStream(file)),clientSocket.getOutputStream(),
                                            true, false);
        } catch (IOException ex) {
            show("ERROR on " + getName() + " sucess():\n\t"
                    + ex.getMessage());
        }  
    }
    /**
     *  Cette méthode renvoie au client un message d'erreur de type 201
     */
    private void created(){
        file = new File(MultiThreadServer.CREATED_FILE);
        httpHeader(201);
        try {
            sendFile(new BufferedInputStream(
                        new FileInputStream(file)),clientSocket.getOutputStream(),
                                            true, false);
        } catch (IOException ex) {
            show("ERROR on " + getName() + " created():\n\t"
                    + ex.getMessage());
        }  
    }
    /**
     *  Cette méthode exécute le service GET
     */
    private void getService() {
        try {
            file = new File(fileName);
            /*
             * test de l'existence et des droits du fichiers
             */
            if(!file.exists()){
                notFound();
            }else if (!file.isFile() || !file.canRead()){
                unauthorized();
            } else{
                httpHeader(200);
                sendFile(new BufferedInputStream(
                    new FileInputStream(file)), clientSocket.getOutputStream(),
                                        true, false);
            }
        } catch (IOException ex) {
            show("ERROR on" + getName() + " getService():\n\t"
                    + ex.getMessage());
        }
    }
    /**
     *  Cette méthode exécute le service PUT
     */
    private void putService() {
        try {
            boolean sizeSet = false, startSave = false;
            int fileSize = 0;
            String str;
            while(true){
                str = is.readLine();
                //detection de la taille du fichier
                if(str.toUpperCase().startsWith("CONTENT-LENGTH")){
                    String fSize[] = str.split(":");
                    if((fileSize = Integer.parseInt(fSize[1].trim())) >= 0){
                        sizeSet = true;
                    } 
                }
                /*
                 * detection de la fin de l'entête, on compare à la chaine
                 * vide car readLine() supprime le retour à la ligne à chaque
                 * coup
                 */
                if(str.equals("")){
                    startSave = true;
                    break;
                }
            }
            if(sizeSet && startSave){
                file = new File(fileName);
                if(!file.exists()){
                    receiveFile(fileSize);
                    created();
                }else if(!file.canWrite()){
                    unauthorized();
                }else{
                    receiveFile(fileSize);
                    sucess();
                }
            }else{
                length_Required();
            }
        } catch (IOException ex) {
            show("ERROR on" + getName() + " putService():\n\t"
                    + ex.getMessage());
        }
    }
    /**
     * Cette méthode sauvegarde le fichier envoyé sur la socket
     * @param fileSize <coode>int</code> la taille du fichier à recevoir
     */
    public void receiveFile(int fileSize){
        BufferedWriter out = null;
        try {
            int bytesRead, totalBytesRead = 0;
            char[] buffer = new char[BLOCK_SIZE];
            out = new BufferedWriter(
                        new OutputStreamWriter(
                            new FileOutputStream(file), CHARSET));
            while(true){
                bytesRead = is.read(buffer);
                totalBytesRead += bytesRead;
                if(bytesRead > 0){
                    out.write(buffer, 0, bytesRead);
                    out.flush();
                }
                if(totalBytesRead == fileSize) {
                    break;
                }
            }
        } catch (IOException ex) {
            show("ERROR on" + getName() + " receiveFile():\n\t"
                    + ex.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                show("ERROR on closing " + getName() + " receiveFile():\n\t"
                    + ex.getMessage());
            }
        }
    }

}
