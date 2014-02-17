/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21007631
 */
//TODO Exercice II (question 2.2)
public class Commentaires {
    //TODO Q 2.2
    private List<Commentaire> listeCommentaires;

    /**
     * constructeur par défaut des commenatires
     */
    public Commentaires() {
        listeCommentaires = new ArrayList<>();
    }
    
    //TODO Q 2.3
    public void ajouterCommentaire(Commentaire commentaire){
        listeCommentaires.add(commentaire);
    }
    /**
     * 
     * @return note la moyenne des commentaires
     * @throws PasDeCommentaire 
     */
    public double calculerNote() throws PasDeCommentaire{
        double note= 0;
        //TODO Q 3.2
        if(this.listeCommentaires.isEmpty())
            throw new PasDeCommentaire("Aucun Commentaire Trouve");
        for(Commentaire courant : listeCommentaires){
            note += courant.getNote();
        }
        return note/getNbCommentaire();
    }
    /**
     * 
     * @return <code>int</code> le nombre de commentaire
     */
    public int getNbCommentaire(){
        return listeCommentaires.size();
    }
    //fin Q 2.3
    
}
