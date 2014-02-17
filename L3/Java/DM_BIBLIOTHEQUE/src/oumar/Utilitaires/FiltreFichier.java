/**
 * 
 */
package oumar.Utilitaires;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *<b>Description:</b><i>Classe utilisee pour faire un tri pour le choix des fichiers a charger
 *Les filtres permettent de ne visualiser que 
 *certains fichiers en fonction de leurs suffixes. 
 *Il faut d abord creer ces filtres, puis les ajouter au JFileChooser.</i>
 * <br/><b>mail:</b><a href="mailto:aob.diallo@gmail.com">aob.diallo@gmail.com</a><br/>
 * <b>site:</b><a href="http://www.oumar.geek-trick.com">www.oumar.geek-trick.com</a><br/>
 * @author D.Alpha Oumar Binta
 */
public class FiltreFichier extends FileFilter {
	   String [] lesSuffixes;
	   String  laDescription;
	   File fichier_courant = null;
	   /**
	    * 
	    * @param lesSuffixes
	    * @param laDescription
	    */
	   public FiltreFichier(String []lesSuffixes, 
	                         String laDescription){
	      this.lesSuffixes = lesSuffixes;
	      this.laDescription = laDescription;
	   }
	   /**
	    * 
	    * @param suffixe
	    * @return boolean
	    * vrai s'il fait partie des suffixes 
	    * faux sinon
	    */
	   boolean appartient( String suffixe ){
	      for( int i = 0; i<lesSuffixes.length; ++i)
	         if(suffixe.equals(lesSuffixes[i]))
	            return true;
	         return false;
	   }
	   /**
	    * @param f (a file)
	    * @return boolean
	    * vrai si le fichier est accepte
	    * faux sinon
	    */
	   public boolean accept(File f) {
	      if (f.isDirectory())  return true;
	      String suffixe = null;
	      String s = f.getName();
	      int i = s.lastIndexOf('.');
	      if(i > 0 &&  i < s.length() - 1)
	         suffixe=s.substring(i+1).toLowerCase();
	      return suffixe!=null&&appartient(suffixe);
	   }
	   
	   // la description du filtre
	   public String getDescription() {
	      return laDescription;
	   }
	   /**
	    * cette methode copier le fichier image choisit dans un autre repertoire 
	    * @param file
	    * @return boolean
	    */
	   public boolean uploadFile(JFileChooser file){
		   try {
		      // File_Image est mon FileChooser
			   InputStream in = new FileInputStream(file.getSelectedFile());
		      // Destination
		      File dst = new File("images/Medias/"+file.getSelectedFile().getName());
		      // Creation d'un nouveau fichier
		      dst.createNewFile();
		      OutputStream out = new FileOutputStream(dst);
		      // Transfert                                         
		      byte[] buf = new byte[1024];
		      int len;
		      while ((len = in.read(buf)) > 0) {
		          out.write(buf, 0, len);
		       }
		    
		      // Fermeture des flux
		      in.close();
		      out.close(); 
		      return true;
		   } 
		   catch (FileNotFoundException e) {
			   JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier d'origine!\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
		   } 
		   catch (IOException e){
			   JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier d'origine!\n" + e.getCause()+"\n", "ERREUR", JOptionPane.ERROR_MESSAGE);
		   }
		   return false;
	   }
		
	   
	   
	   
	}