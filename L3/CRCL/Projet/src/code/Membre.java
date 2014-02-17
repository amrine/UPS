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
public class Membre<T extends Truc> extends Personne{
    private final int TAILLE_LISTE_ATTENTE = 10;
    //TODO Q 4.1
    private Map<T, Paire<Integer, Queue<Membre<T>>>> objets;
    //fin Q 4.1
    //TODO Q 2.5
    private int nbPretEffectuer, nbEmpruntEffectuer;
    //fin declaration Q 2.5
    //TODO Q 4.3
    private List<Membre<T>> blackList;
    //fin declaration Q 4.3
    //TODO Q 5.2
    private List<Paire<Membre<T>, T>> trucEmprunte;
    //fin declaration Q 5.2
    //TODO Q 2.4
    private Commentaires commentPret, commentEmprunt;

    public Commentaires getCommentPret() {
        return commentPret;
    }

    public Commentaires getCommentEmprunt() {
        return commentEmprunt;
    }
    //fin Q 2.4
    //TODO Q 2.5

    public int getNbPretEffectuer() {
        return nbPretEffectuer;
    }

    public int getNbEmpruntEffectuer() {
        return nbEmpruntEffectuer;
    }
    //fin Q 2.5
    //TODO Q 2.6
    public double notePrets() throws PasDeCommentaire{
        return commentPret.calculerNote();
    }
    public double noteEmprunts() throws PasDeCommentaire{
        return commentEmprunt.calculerNote();
    }
    //fin Q 2.6
    public Membre(String nom, String prenom){
        super(nom, prenom);
        commentPret = new Commentaires();
        commentEmprunt = new Commentaires();
        objets = new HashMap<>();
        trucEmprunte = new ArrayList<>();
        blackList = new LinkedList<>();
    }

    public Map<T, Paire<Integer, Queue<Membre<T>>>> getObjets() {
        return objets;
    }
    //TODO Q 4.2
    public void ajouterTruc (T obj){
        //si l'objet est present,on recupere la valeur et augmente le nombre
        //d'exemplaire
        if (objets.containsKey(obj)) {
            
            Paire<Integer, Queue<Membre<T>>> valeur = objets.get(obj);
            //on augmente le nombre d'exemplaire
            valeur.setFirst(valeur.getFirst()+1);
            objets.put(obj, valeur); 
            //TODO Q 7.1
            //est ce que la file d'attente est vide
            if(!valeur.getSecond().isEmpty()){
                //on prete au premier membre et on le retire de la file d'attente
                preterTruc(obj, valeur.getSecond().poll());
            }
            //fin Q 7.1
        }else{
            objets.put(obj, new Paire<Integer, Queue<Membre<T>>>(1,
            		new PriorityQueue<Membre<T>>(TAILLE_LISTE_ATTENTE,
                    new MeilleurEmprunteurComparator())));
        }
    }
    public void supprimerTruc(T obj){
        if (objets.containsKey(obj)){
            Paire<Integer, Queue<Membre<T>>> valeur = objets.remove(obj);
            valeur.setFirst(valeur.getFirst()-1);
            if (valeur.getFirst() !=0 ){
                objets.put(obj, valeur);
            }
        }
    }
    //fin Q 4.2
    public boolean possedeTruc(T obj){
        return objets.containsKey(obj);
    }
    public T chercherTruc(T obj){
        if(possedeTruc(obj)){
            Set ensJeux = objets.keySet();
            NavigableSet<T> jeuxTries = new TreeSet<>(ensJeux);
            NavigableSet<T> newList = new TreeSet<>(jeuxTries.tailSet(obj, true));
            //on prend le premier element de notre sous-ensemble triee 
            //a  partir de notre jeu
            return newList.pollFirst();   
        }else{
            return null;
        }
    }
    //TODO Q 4.3
    public List<Membre<T>> getBlackList(){
        return blackList;
    }
    public boolean ajouterMembreBlackList(Membre<T> membre){
        if(!estDansBlackList(membre)){
            return blackList.add(membre);
        }
        return false;
    }
    public boolean retirerMembreBlackList(Membre<T> membre){
        if(estDansBlackList(membre)){
            return blackList.remove(membre);
        }
        return false;
    }
    public boolean estDansBlackList(Membre<T> membre){
        return blackList.contains(membre);
    }
    //fin Q 4.3
    //TODO Q 5.1
    public int tailleListeAttente(T obj){
        return objets.get(obj).getSecond().size();
    }
    //fin Q 5.1
    //TODO Q 5.2
    public void setTrucEmprunte(T obj, Membre<T> preteur){
        trucEmprunte.add(new Paire<>(preteur, obj));
    }
    //fin Q 5.2
    //TODO Q 5.3
    public List<T> getTrucEmprunte(){
        List<T> emprunts = new ArrayList<>();
        for(Iterator<Paire<Membre<T>, T>> iter = trucEmprunte.iterator(); iter.hasNext();){
            emprunts.add(iter.next().getSecond());
        }        
        return emprunts;
    }
    //fin Q 5.3
    //TODO Q 5.4
    public Queue<Membre<T>> getListeAttente(T obj){
        Paire<Integer, Queue<Membre<T>>> valeur = this.objets.get(obj);
        if(valeur != null){
            return valeur.getSecond();
        }
        return null;
    }
    public boolean ajoutListeAttente(T obj, Membre<T> membre){
        Paire<Integer, Queue<Membre<T>>> valeur = this.objets.get(obj);
        if(valeur != null){
            return valeur.getSecond().offer(membre);
        }
        return false;
    }
    //fin Q 5.4
    //TODO Q 6.1
    public MessagePret isPretPossible(T obj, Membre<T> membre){
        //on verifie que le membre n'est le preteur
        if(membre == this){
            return MessagePret.NON_DISPONIBLE;
        }
        //si le membre est dans la black list, pret indisponible
        if(estDansBlackList(membre)){
            return MessagePret.BLACK_LIST;
        }
        
        //on verifie que le truc existe, donc non NULL
        T trucAPreter = chercherTruc(obj);
        if(trucAPreter == null){
            return MessagePret.NON_DISPONIBLE;
        }
        //si la liste d'attente est pleine, INDISPONIBLE
        if(tailleListeAttente(obj) == TAILLE_LISTE_ATTENTE){
            return MessagePret.NON_DISPONIBLE;
        }
        
        //on verifie la quantite
        Paire<Integer, Queue<Membre<T>>> truc = getTruc(obj);
        if(truc.getFirst().intValue() == 0){
            return MessagePret.LISTE_ATTENTE;
        }else{
            return MessagePret.DISPONIBLE;
        }
    }
    /**
     * 
     * @param obj un truc
     * @return <code>Paire<Integer, Queue<Membre<T>>></code> la paire contenant 
     * la quantite et le file d'attente d'un truc
     */
    public Paire<Integer, Queue<Membre<T>>> getTruc(T obj){
        return objets.get(obj);
    }
    //fin Q 6.1
    //TODO Q 6.2
    /**
     * 
     * @param obj
     * @param emprunteur
     * @return Confirmation
     * SUCCESS si le pret a ete effectuer
     * ERROR si indisponible
     * WAITING_LIST si ajout en liste d'attente
     */
    public Confirmation preterTruc(T obj, Membre<T> emprunteur){
        MessagePret message = isPretPossible(obj, emprunteur);
        
        if(message == MessagePret.NON_DISPONIBLE){
            return Confirmation.ERROR;
        }else if(message ==  MessagePret.BLACK_LIST){
            return Confirmation.BLACK_LIST;
        }
        else if(message ==  MessagePret.LISTE_ATTENTE){
            return Confirmation.WAITING_LIST;
        }else{
            //on recupere le truc
            Paire<Integer, Queue<Membre<T>>> truc = getTruc(obj);
            //on ajouter le truc dans la liste d'emprunt de l'emprunteur
            //et on augmente son nombre d'emprunt
            emprunteur.setTrucEmprunte(obj, this);
            emprunteur.nbEmpruntEffectuer++;
            //on dimunie le nombre d'exemplaire disponible
            truc.setFirst(truc.getFirst()-1);
            //on augmente le nombre de pret du preteur
            nbPretEffectuer++;
            //on sauvegarde
            sauvegarderTruc(obj, truc);
            
            return Confirmation.SUCCESS;
        }
    }
    /**
     * sauvegarde d'une paire dans la map
     * @param obj un truc
     * @param content une paire
     */
    public void sauvegarderTruc(T obj, Paire<Integer, Queue<Membre<T>>> content){
        objets.put(obj, content);
    }
    //fin Q 6.2
    //TODO Q 7.2
    public void reprendreTruc (T obj){
        //si l'objet est present,on recupere la valeur et augmente le nombre
        //d'exemplaire
        if (objets.containsKey(obj)) {
            
            Paire<Integer, Queue<Membre<T>>> valeur = objets.get(obj);
            //on augmente le nombre d'exemplaire
            valeur.setFirst(valeur.getFirst()+1);
            objets.put(obj, valeur); 
            //le nombre de pret est reduit
            nbPretEffectuer--;
            //est ce que la file d'attente est vide
            if(!valeur.getSecond().isEmpty()){
                //on prete au premier membre et on le retire de la file d'attente
                preterTruc(obj, valeur.getSecond().poll());
            }            
        }
    }
    //fin Q 7.2
    //TODO Q 7.3
    public boolean rendreTruc (T obj){
        for(ListIterator<Paire<Membre<T>, T>> iter = trucEmprunte.listIterator(); iter.hasNext();){
            Paire<Membre<T>, T> current = iter.next();
            if(current.getSecond().equals(obj)){
                //le membre preteur reprend son truc
                current.getFirst().reprendreTruc(obj);
                //diminution du nombre d'emprunt
                nbEmpruntEffectuer--;
                //supprimer de la liste d'emprunt
                iter.remove();
                return true;
            }
        }
        return false;
    }
    //fin Q 7.3
    /**
     * 
     * @return str les statistiques relatifs à un membre
     */
    public String printMembreStats(){
        String str = "Membre " + toString(); 
        try {
            str += "\tNbrePrets : " + nbPretEffectuer;
            str += "\tNbreEmprunts : " + nbEmpruntEffectuer;
            str += "\tNotesPrets : " + notePrets();
            str += "\tNotesEmprunts : " + noteEmprunts();

        } catch (PasDeCommentaire ex) {
        }
        return str;
    }
}
