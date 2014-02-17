package vue;

import java.util.Scanner;

public class Clavier {
	public static String lireString() {
		String chaine;
		Scanner scanner = new Scanner(System.in);
		chaine = scanner.next();
		return chaine;
	}
	
	public static int lireEntier() throws MauvaisTypeEntreeException{
		int entier = 0;
                try{
                    Scanner scanner = new Scanner(System.in);
                    entier = scanner.nextInt();
                }
                catch(java.util.InputMismatchException ex){
                    throw new MauvaisTypeEntreeException("La valeur lue au "
                            + "clavier n'est pas un entier");
                }
                finally{
                    System.out.println("Vous avez entre " + entier);
                }
		
		return entier;		
	}
	
	public static boolean lireBoolean() throws MauvaisTypeEntreeException {
		boolean bool = false;
	
                try{
                    Scanner scanner = new Scanner(System.in);
                    bool = scanner.nextBoolean();
                }
                catch(java.util.InputMismatchException ex){
                    throw new MauvaisTypeEntreeException("La valeur lue au "
                            + "clavier n'est pas un boolean (true or false)");
                }
                finally{
                    System.out.println("Vous avez entre " + bool);
                }
		return bool;		
	}
        
        
        public static boolean getBool(String message){
            boolean reponseCorrect;
            boolean reponse = false;
            do{
                System.out.println(message);
                try {
                    reponse = Clavier.lireBoolean();
                    reponseCorrect = true;
                } catch (MauvaisTypeEntreeException ex) {
                    System.out.println(ex.getMessage());
                    reponseCorrect = false;
                }
            }while(!reponseCorrect);
            
            return reponse;
        }
        
        public static Integer  getInteger(String message){
            boolean reponseCorrect;
            Integer reponse = null ;
            do{
                System.out.println(message);
                try {
                    reponse = (Integer) Clavier.lireEntier();
                    reponseCorrect = true;
                } catch (MauvaisTypeEntreeException ex) {
                    System.out.println(ex.getMessage());
                    reponseCorrect = false;
                }
            }while(!reponseCorrect);
            
            return reponse;
        }
}
