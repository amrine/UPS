/**
 * 
 */
package utiles;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diallo, Nzeng
 *
 */
/**
 * Parseur de texte
 */
public class ParseText {

	private List<String> liste=new ArrayList<String>();
	
	public ParseText(String txt){
		liste=transforme(txt);
	}
	/**
	 * 
	 * @return the liste
	 */
	public List <String> getListe(){
		return liste;
	}
	
	
	/**
	 * 
	 * @param text
	 * @return une liste comportant des lettres alphabetiques
	 */
	public List<String> transforme(String text){
		
		for(int i=0;i<text.length();i++){
		
			if (('a'<=text.charAt(i) && text.charAt(i)<='z') || ('A'<=text.charAt(i) && text.charAt(i)<='Z'))
			{
				liste.add(String.valueOf(text.charAt(i)));
			}
		    
		}
		
		return liste;
	}
}
