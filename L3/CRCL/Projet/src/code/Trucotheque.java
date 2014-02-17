/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.*;

/**
 *
 * @author 21007631
 */
public class Trucotheque<T extends Truc> {
    private String nom;
    private List <Membre<T>> listeMembres;
    private int cotisation = 10;
    
    public Trucotheque(String nom){
        this.nom=nom;
        listeMembres = new ArrayList<>();
    }

    public int getCotisation() {
        return cotisation;
    }

    public List<Membre<T>> getListeMembres() {
        return listeMembres;
    }

    public String getNom() {
        return nom;
    }
    
    public Membre<T> ajouterMembre(String nom, String prenom){
        Membre<T> m = new Membre<>(nom, prenom);
        return ajouterMembre(m);
    }
    public Membre<T> ajouterMembre(Membre<T> m){
        boolean appartient=false;
        for (Membre<T> membreCourant: listeMembres) {
            if (m.equals(membreCourant)) {
                appartient=true;
            }
        }
        if (!appartient){
            listeMembres.add(m);	
        }
        return m;
    }
    public boolean retirerMembre(Membre<T> m){
        for (Iterator<Membre<T>> iter = listeMembres.iterator(); iter.hasNext();) {
            Membre<T> membreSuivt = (Membre<T>) iter.next();
            if (membreSuivt.equals(m)){
                iter.remove();
                return true;
            }	
        }
        return false;
    }
    public Membre<T> getMembre(Membre<T> m){
        for (Iterator<Membre<T>> iter = listeMembres.iterator(); iter.hasNext();) {
            Membre<T> membreSuivt = (Membre<T>) iter.next();
            if (membreSuivt.equals(m)){
                return membreSuivt;
            }	
        }
        return null;
    }
    public List<Membre<T>> membresPossedantTruc(T obj){
        List<Membre<T>> MembresPossedantTruc = new ArrayList<>();
        for (Membre membreCourant : listeMembres) {
            if (membreCourant.possedeTruc(obj)){
                MembresPossedantTruc.add(membreCourant);
            }
        }
        return MembresPossedantTruc;
    }
    public List<T> listeTrucs(){
        Set<T> maListe = new HashSet<>();
        for (Membre<T> membreCourant: listeMembres) {
            maListe.addAll(membreCourant.getObjets().keySet());	
        }
        List<T> res = new ArrayList<>(maListe);
        return res;
    }
    public int cotisationMembre(Membre<T> m){
        for (Membre<T> membreCourant: listeMembres){
            if (m.equals(membreCourant)){
                if (!membreCourant.getObjets().isEmpty()){
                    return cotisation/2;
                }
            }
        }
        return cotisation;
    }
    //TODO Q 3.1
    public List<Membre<T>> meilleursPreteurs(){
        /* 
         * on trie selon les meilleurs notes dans les commentaires relatifs 
         * aux prets, si on a une egalite de note, on trie selon le plus grand 
         * nombre de prets, si le nombre de pret est egale, on trie selon 
         * l'ordre alphabetique du nom et prenom des membres
         * 
         * on recupere chaque note à part pour qu'à chaque fois qu'on a un membre
         * qui, à un ordre naturel du nom et prenom mieux placée ne soit pas en
         * tête de liste quand il n'a pas de commentaire ou effectuer des prêts
        * */
        Set<Membre<T>> listeTriee = new TreeSet<>(new Comparator<Membre<T>>() {

            @Override
            public int compare(Membre<T> o1, Membre<T> o2) {
                //trie sur l'ordre decroissant de la moyenne
                int res;
                double noteO1, noteO2;
                //TODO Q 3.2
                try {
                    noteO1 = o1.notePrets();
                } catch (PasDeCommentaire ex) {
                    noteO1 = 0;
                }
                try {
                    noteO2 = o2.notePrets();
                } catch (PasDeCommentaire ex) {
                    noteO2 = 0;
                }
                res = Double.compare(noteO2, noteO1);
                //fin 3.2
                //trie sur l'ordre decroissant du nombre de pret
                if(res == 0){
                    res = Integer.compare(o2.getNbPretEffectuer(), o1.getNbPretEffectuer());
                }
                //trie sur l'ordre naturel du nom et prenom
                if(res == 0){
                    res = o1.compareTo(o2);
                }
                return res;
            }
        });
        listeTriee.addAll(listeMembres);
        return new ArrayList<>(listeTriee);
    }
    //fin 3.1
    //TODO Q 3.3
    public List<Membre<T>> meilleursEmprunteurs(){
        /* 
         * trie selon les meilleurs notes dans les commentaires relatifs aux 
         * emprunts, si egalite de note, on trie selon le plus grand nombre de 
         * prets, si le nombre de pret est egale, on trie selon l'ordre 
         * alphabetique du nom et prenom des membres
        * */
        Set<Membre<T>> listeTriee = new TreeSet<>(new MeilleurEmprunteurComparator());
        listeTriee.addAll(listeMembres);
        return new ArrayList<>(listeTriee);
    }
    //fin Q 3.3
    //TODO Q 6.3
    /**
     * 
     * @param obj un truc
     * @param membre un emprunteur
     * @return Confirmation un message de confirmation
     */
    public Confirmation emprunterTruc(T obj, Membre<T> membre){
        List<Membre<T>> liste = trieePreteur(obj, membre);
        //aucun preteur
        if(liste.isEmpty()){
            return Confirmation.ERROR;
        }
        //le premier preteur
        Membre<T> preteur = liste.get(0);
        Confirmation confirmation = preteur.preterTruc(obj, membre);        
        //cas de la liste d'attente et success
        if(confirmation != Confirmation.ERROR){
            //liste d'attente
            if(confirmation == Confirmation.WAITING_LIST){
                preteur.ajoutListeAttente(obj, membre);
                return Confirmation.WAITING_LIST;
            }else if(confirmation == Confirmation.BLACK_LIST){
                return Confirmation.BLACK_LIST;
            }else
                return Confirmation.SUCCESS;
        }
        //la liste d'attente est pleine
        return Confirmation.FULL;
    }
    /**
     * Cette méthode trie les preteurs dont le truc est disponible
     * @param obj un truc
     * @param membre un emprunteur
     * @return <code>List<Membre<T>></code> liste des preteurs
     */
    private List<Membre<T>> trieePreteur(final T obj, final Membre<T> membre){
        Set<Membre<T>> listeTriee = new TreeSet<>(new Comparator<Membre<T>>() {

            @Override
            public int compare(Membre<T> o1, Membre<T> o2) {
                MessagePret mso1 = o1.isPretPossible(obj, membre);
                MessagePret mso2 = o2.isPretPossible(obj, membre);

                if(mso1 == MessagePret.DISPONIBLE){
                    return 1;
                }else if(mso2 == MessagePret.DISPONIBLE){
                    return -1;
                }else if(mso1 == MessagePret.LISTE_ATTENTE
                        && mso2 == MessagePret.LISTE_ATTENTE){
                    return Integer.compare(o1.tailleListeAttente(obj),
                            o2.tailleListeAttente(obj));
                }
                return 0;
            }
        });
        //on retient les membres possedant le truc
        listeTriee.addAll(membresPossedantTruc(obj));
        return new ArrayList<>(listeTriee);
    }
    //fin Q 6.3
    public String printMembreTrucs(){
        String str = "\nListe des membres et de leurs trucs\n" ;
        for(Iterator<Membre<T>> iter = listeMembres.iterator(); iter.hasNext();){
            Membre<T> current = iter.next();
            str += current.toString() + " => " + current.getObjets() + "\n";
        }
        return str;
    }
    
}
