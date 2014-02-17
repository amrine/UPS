/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

/**
 *
 * @author 21007631
 */
public class PasDeCommentaire extends Exception{

    public PasDeCommentaire() {}

    public PasDeCommentaire(String message) {
        super(message);
    }

    public PasDeCommentaire(String message, Throwable cause) {
        super(message, cause);
    }

    public PasDeCommentaire(Throwable cause) {
        super(cause);
    }

    public PasDeCommentaire(String message, Throwable cause, 
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
