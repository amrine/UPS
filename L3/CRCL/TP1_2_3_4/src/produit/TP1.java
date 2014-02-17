/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package produit;

/**
 *
 * @author Diallo Alpha oumar Binta (21007631)
 */
public class TP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Categorie cremerie = new Categorie("Cremerie");
        Categorie boucherie = new Categorie("Boucherie");
        Categorie televiseur = new Categorie("Multimedia");
        Nature fromage = new Nature(cremerie, "les fromages");
        Nature yaourt = new Nature(cremerie, "les yaourts");
        Nature boeuf = new Nature(boucherie, "la viande de boeuf");
        Nature technologieLed = new Nature(televiseur, "la technologie LED");
        
        
        GestionProduit gestion = new GestionProduit();
        gestion.ajouterNatureProduit(fromage, boeuf, yaourt, technologieLed);
        
      
        gestion.ajouterProduit(fromage, new Aliment("fromage de chevre"), new Aliment("fromage bleu"));
        gestion.ajouterProduit(yaourt, new Aliment("yaourt nature"), new Aliment("yaourt au fruits"));
        gestion.ajouterProduit(boeuf, new Aliment("steack hache"));
        gestion.ajouterProduit(technologieLed, new MultiMedia("ecran 3/4"), new MultiMedia("ecran 16/9"));
     
        System.out.println(gestion.affichageBaseProduit());
        gestion.suppressionNature(fromage);
        System.out.println(gestion.affichageBaseProduit());
    }

}
