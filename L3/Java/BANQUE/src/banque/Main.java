/**
 * 
 */
package banque;

/**
 * @author oumar
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Carte maCarte;
		Banque banque = new Banque();
		Banque banque2 = new Banque();
		int []code1 = {1,2,3,4};
		int []codeCarte = {1,2,3,4};
		Terminal t= new Terminal(code1,new Date(24,05,2007));
		Client c1 = new Client("Diallo",new Date(20,02,2003));
		Client c2 = new Client("Barry",new Date(23,05,2007));
		
		banque.ajouterClient(c1);
		banque2.ajouterClient(c2);
		
		System.out.println("\nClient 1 : " + banque.getClients(0).getNom() +" nee le : " + banque.getClients(0).getDate());
        System.out.println("Client 2 : " + banque2.getClients(0).getNom() +" nee le : " + banque.getClients(0).getDate());
        
        System.out.println("Creation d'un compte 1 pour chaque client en les creditant de c1=340 c2=100 : " );
        banque.getClients(0).ajouterCompte();
        banque2.getClients(0).ajouterCompte();
        
        banque.getClients(0).getComptes(0).depot(340);
        banque2.getClients(0).getComptes(0).depot(100);
        
        System.out.print("Client 1 ");
        banque.getClients(0).afficherBilan();
		System.out.print("Client 2 ");
        banque2.getClients(0).afficherBilan();
        
        maCarte = new Carte(codeCarte,banque,0,0,new Date(24,05,2007));

		
		System.out.println("\nEffectuons un paiement par carte de 100 euro du compte client 1 vers le compte du client 2 :");
		String autorisation = t.genererAutorisation(maCarte,100);
		if (autorisation.equals("OK")) 
			maCarte.payer(banque2,0,0,100);
		else System.out.println("Central de paiement : " +autorisation);
		
        
        
		System.out.print("Client 1 ");
        banque.getClients(0).afficherBilan();
		System.out.print("Client 2 ");
        banque2.getClients(0).afficherBilan();

	}

}

