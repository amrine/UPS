import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
public class ServeurEcho extends Object {
		public static void main (String args[]) {
		ServerSocket socketEcoute;
		Socket socketService;
		InputStream entreeSocket;
		OutputStream sortieSocket;
		BufferedReader fluxEntreeSocket;
		try {

		// création du socket d’écoute sur le port 0 (le serveur cherche un port libre)
		socketEcoute = new ServerSocket(0);

		System.out.println("le numero de port est : " + socketEcoute);
		while (true) {

		// attente d’une demande de connexion
		socketService = socketEcoute.accept();
		System.out.println("Nouvelle connexion : " + socketService);

		// récupération des flux d’entrée/sortie de la socket de service
		entreeSocket = socketService.getInputStream();
		sortieSocket = socketService.getOutputStream();

		// creation d’un flux de type BufferedReader lié au flux d’entrée de la socket
		fluxEntreeSocket= new BufferedReader(new InputStreamReader(entreeSocket));
		try {

		//message envoyer par le client
		String message = fluxEntreeSocket.readLine();
		System.out.println("Message du client: " +message);

		StringTokenizer st = new StringTokenizer(message);
     while (st.hasMoreTokens()) {
         System.out.println(st.nextToken());
     }



		System.out.println("Fin de connexion");
		} // try
		catch (IOException ex)
		{
		// fin de connexion
		System.out.println("Fin de connexion : "+ex);
		ex.printStackTrace();
		}
		socketService.close();
		} // while (true)
		} // try
		catch (Exception ex)
		{
		// erreur de connexion
		System.err.println("Une erreur est survenue : "+ex);
		ex.printStackTrace();
		}
		} // main
} // class		
