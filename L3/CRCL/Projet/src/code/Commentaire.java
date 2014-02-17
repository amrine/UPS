/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

/**
 *
 * @author 21007631
 */
//TODO Exercice II (question 2.1)
public class Commentaire {
    private String commentaire;
    private double note;

    /**
     * Constructeur par défaut d'un commentaire
     * @param commentaire
     * @param note 
     */
    public Commentaire(String commentaire, double note) {
        this.commentaire = commentaire;
        this.note = note;
    }

    /**
     * 
     * @return commentaire le commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }
    //TODO Q 2.1
    /**
     * 
     * @return note la note
     */
    public double getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "commentaire=" + commentaire + ", note=" + note + '}';
    }
    //fin 2.1
    
    
}
