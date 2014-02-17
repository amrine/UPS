/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

/**
 *
 * @author alphaoumar
 */
public class MauvaisTypeEntreeException extends Exception{

    public MauvaisTypeEntreeException() {
    }

    public MauvaisTypeEntreeException(String message) {
        super(message);
    }

    public MauvaisTypeEntreeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MauvaisTypeEntreeException(Throwable cause) {
        super(cause);
    }

    public MauvaisTypeEntreeException(String message, Throwable cause, 
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
