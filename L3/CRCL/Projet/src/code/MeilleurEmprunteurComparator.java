/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.Comparator;

/**
 *
 * @author 21007631
 */
//TODO Q 3.3
public class MeilleurEmprunteurComparator<T extends Truc> implements Comparator<Membre<T>>{

    @Override
    public int compare(Membre<T> o1, Membre<T> o2) {
        /**
         * on recupere chaque note à part pour qu'à chaque fois qu'on a un membre
         * qui, à un ordre naturel du nom et prenom mieux placée ne soit pas en
         * tête de liste quand il n'a pas de commentaire ou effectuer des emprunts
         */
        //trie sur l'ordre decroissant de la moyenne
        int res;
        double noteO1, noteO2;
        try {
            noteO1 = o1.noteEmprunts();
        } catch (PasDeCommentaire ex) {
            noteO1 = 0;
        }
        try {
            noteO2 = o2.noteEmprunts();
        } catch (PasDeCommentaire ex) {
            noteO2 = 0;
        }
        res = Double.compare(noteO2, noteO1);
        //trie sur ordre decroissant du nombre d'emprunt
        if(res == 0){
            res = Integer.compare(o2.getNbEmpruntEffectuer(), o1.getNbEmpruntEffectuer());
        }
        //trie sur l'ordre naturel du nom et prenom
        if(res == 0){
            res = o1.compareTo(o2);
        }
        return res;
    }
    
}
//fin comparateur Q 3.3