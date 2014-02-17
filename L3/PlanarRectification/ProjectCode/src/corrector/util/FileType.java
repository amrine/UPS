

package corrector.util;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/** 
 * <p>Title : FileType </p>
 * <p>Filters allow you to view only certain files based on their suffixes.
 * We must first of all create the filters, then add to a JFileChooser</p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */

public class FileType extends FileFilter {
    /**
     * the table of the suffixes
     */
    String [] suffixes;
    /**
     * the description of file type
     */
    String  description;
   /**
   * Constructor of FileType
   * @param suffixes
   * @param description
   */
   public FileType(String []suffixes, String description){
       this.suffixes = suffixes;
       this.description = description;
   }
   /**
   * @param suffixe
   * @return boolean
   * true if the suffixe is in the table else false
   */
   boolean contains( String suffixe ){
       for( int i = 0; i<suffixes.length; ++i)
           if(suffixe.equals(suffixes[i]))
               return true;
       return false;
    }
   /**
   * @param f a file
   * @return boolean
   * true if the file is accepted else false
   */
   @Override
   public boolean accept(File f) {
       if (f.isDirectory())  return true;
       String suffixe = null;
       String s = f.getName();
       int i = s.lastIndexOf('.');
       if(i > 0 &&  i < s.length() - 1)
           suffixe=s.substring(i+1).toLowerCase();
       return suffixe!=null&&contains(suffixe);
   }
	   
    /**
     * 
     * @return description the description of the file type
     */
    @Override
    public String getDescription() {
        return description;
    }
    /**
    * this method upload the file  
    * @param chooser a File Chooser
    * @param destination the folder path
    * @return boolean 
    * true if the file is uploaded else false
    */
   public boolean uploadFile(JFileChooser chooser, String destination){
       try {
           InputStream in = new FileInputStream(chooser.getSelectedFile());
            // Destination folder
	   File dst = new File(destination+chooser.getSelectedFile().getName());
           //Creating a new file
	   dst.createNewFile();
	   OutputStream out = new FileOutputStream(dst);
           // Transfert                                         
	   byte[] buf = new byte[1024];
	   int len;
	   while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
           }
	   // Streams are closed
	   in.close();
	   out.close(); 
	   return true;
        } 
       catch (FileNotFoundException e) {
           JOptionPane.showMessageDialog(null, 
                   "Error loading from the original file!\n" 
                   + e.getCause()+"\n", "ERROR", JOptionPane.ERROR_MESSAGE);
       } 
       catch (IOException e){
	   JOptionPane.showMessageDialog(null, 
                   "Error loading from the original file!\n" 
                   + e.getCause()+"\n", "ERROR", JOptionPane.ERROR_MESSAGE);
       } 
       return false;
   }
}