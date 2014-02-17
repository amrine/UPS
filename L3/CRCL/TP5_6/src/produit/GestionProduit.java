/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package produit;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Diallo Alpha oumar Binta (21007631)
 */
public class GestionProduit<P extends Produit> {
    List<Nature<P>> ensembleNatureProduits = new LinkedList<>();
    
    public void ajouterNatureProduit(Nature<P> nature){
        for(ListIterator<Nature<P>> iter = ensembleNatureProduits.listIterator();
                iter.hasNext(); ){
            Nature n_actuel = iter.next();
            //meme categorie
            if(n_actuel.getCategorie().equals(nature.getCategorie())){
                //est ce qu'il ya un element suivant
                if(iter.hasNext()){
                    Nature<P> n_suivant = iter.next();
                    //le suivant est different, je l'ajoute ici
                    if(!n_suivant.getCategorie().equals(n_actuel.getCategorie())){
                        //on revient sur la bonne position, car on a depasse l'element avec n_suivant
                        iter.previous();
                        iter.add(nature);
                        return;
                    }
                    else {
                        //non c'etait pas le bon, retour en arriere
                        iter.previous();
                    }
                }
            }
        }
        //rien n'a ete trouve, rajout a la fin
        ensembleNatureProduits.add(nature);
    }
    
    public void ajouterNatureProduit(Nature<P>... tabNature){
        for(Nature<P> nature : tabNature){
            this.ajouterNatureProduit(nature);
        }
    }
    
    public String ajouterProduit(Nature<P> nature, P produit){
        String msg = "";
        //test de l'existence de la nature
        int existe_index = this.ensembleNatureProduits.lastIndexOf(nature);
        if(existe_index != -1){
            this.ensembleNatureProduits.get(existe_index).getProduits().add(produit);
            msg += "Ajout avec succes : " + produit.toString();
            return msg;
        }
        throw new RuntimeException("Impossible d'ajouter le produit : Nature inconnue");
    }
    public String ajouterProduit(Nature<P> nature, P... tabProduit){
        for(P produit : tabProduit){
            this.ajouterProduit(nature, produit);
        }       
        return "Tous les produits ont ete ajoutee";
    }
    
    public String affichageBaseProduit(){
        Nature<P> nature;
        String str = "";
        Categorie cat = ensembleNatureProduits.get(0).getCategorie();
        str += "Categorie " + cat.getNom() + " :\n";
        for(ListIterator<Nature<P>> iter = ensembleNatureProduits.listIterator(); iter.hasNext();){
            nature = iter.next();
            Categorie catSuiv = nature.getCategorie();
                        
            if(cat != catSuiv){
                str += "Categorie " + nature.getCategorie().getNom() + " :\n";
                cat=catSuiv;
            }           
            str += "Les produits concernants " + nature.getNom() + " sont : [";
            for(Iterator<P> iter2 = nature.getProduits().iterator(); iter2.hasNext();){
                P produit = iter2.next();
                str += produit.toString();
                if(iter2.hasNext()) {
                    str += ",";
                }
            }
            str += "]\n";   
        }        
        return str;
    }
    
    public void suppressionNature(Nature<P> natureToSuppress){
        for(Iterator<Nature<P>> iter = ensembleNatureProduits.iterator(); iter.hasNext(); ){
            if(iter.next() == natureToSuppress){
                iter.remove();
            }
        }
    }
    public void suppressionNature2(Nature natureToSuppress){
        for(ListIterator<Nature<P>> iter = ensembleNatureProduits.listIterator(); iter.hasNext(); ){
            if(iter.next() == natureToSuppress){
                iter.remove();
            }
        }
    }
    
    
    public List<Nature<P>> listNature(Categorie categorie){
        Nature<P> nature;
        int firstIndex = -1, lastIndex = -1;
        Categorie cat = ensembleNatureProduits.get(0).getCategorie();
        if(cat == categorie){
           firstIndex = 0;
           lastIndex = 0;
        }
        for(ListIterator<Nature<P>> iter = ensembleNatureProduits.listIterator(); iter.hasNext();){
            nature = iter.next();
            Categorie catSuiv = nature.getCategorie();
                        
            if(catSuiv == categorie && firstIndex != -1){
                firstIndex = iter.previousIndex();
                System.out.println(iter.previousIndex());
            }else if(catSuiv == categorie){
                lastIndex = iter.previousIndex();
                System.out.println(iter.previousIndex());
            }            
        }
        
        if(firstIndex != -1 && lastIndex != -1) {
            return this.ensembleNatureProduits.subList(firstIndex, lastIndex);
        }
        else {
            return null;
        }
    }
    
}
