import java.io.*;
import java.net.*;

public class ClientEcho extends Object {
		public static void main (String args[]) {
		String reponse;
		Socket leSocket;
		PrintStream fluxSortieSocket;
		BufferedReader fluxEntreeSocket;
		try {
		// creation d’une socket et connexion à la machine localhost sur le port donnee en argument

		/*question 1
		Coté serveur : Le numéro de port doit être récupéré et affiché à l’écran.
		Coté client : il faut récupérer ce numéro de port et l’utiliser pour se connecter au serveur

		int port = Integer.parseInt(args[0]);
		leSocket = new Socket("localhost",port);
		*/

		/*question 2
		Le serveur accepte la connexion, renvoie une chaîne de caractères contenant la date et l’heure et ferme la
		connexion.
		Le client doit donc :
		- Se connecter sur le port 13
		- Attendre simplement une chaîne de caractères et l’afficher.

		leSocket = new Socket("aurore.cict.fr",13);
		System.out.println("Connecté sur : "+leSocket);
		*/
		
		int port = Integer.parseInt(args[0]);
		leSocket = new Socket("localhost",port);

		// création d'un flux de type PrintStream lié au flux de sortie de la socket
		fluxSortieSocket= new PrintStream(leSocket.getOutputStream());

		// creation d’un flux de type BufferedReader lié au flux d’entrée de la socket
		fluxEntreeSocket= new BufferedReader(new InputStreamReader(leSocket.getInputStream()));
		
		// envoi de données vers le serveur
		fluxSortieSocket.println("ushteyhh");

		// attente puis réception de données envoyées par le serveur
		reponse = fluxEntreeSocket.readLine();
		System.out.println("Reponse du serveur : " + reponse);
		leSocket.close();
		} // try
		catch (UnknownHostException ex)
		{
		System.err.println("Machine inconnue : "+ex);
		ex.printStackTrace();
		}
		catch (IOException ex)
		{
		System.err.println("Erreur : "+ex);
		ex.printStackTrace();
		}
		} // main
} // class
