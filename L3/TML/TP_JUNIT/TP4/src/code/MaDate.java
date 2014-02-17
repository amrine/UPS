/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

/**
 *
 * @author aob
 */
public class MaDate {
    private int jour=0, mois=0, an=0;

    public MaDate(int jour, int mois, int an) {
        this.jour = jour;
        this.mois = mois;
        this.an = an;
    }

    public MaDate() {
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAns() {
        return an;
    }

    public void setAns(int ans) {
        this.an = ans;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaDate other = (MaDate) obj;
        
        if (this.an != other.an) {
            return false;
        }
        if (this.mois != other.mois) {
            return false;
        }
        if (this.jour != other.jour) {
            return false;
        }
        
        return true;
    }
    
    public boolean anneeBissextile(){
        return (an % 4 == 0 && an % 100 != 0) || (an % 400 == 0);
    }
    
    public int nbJourDuMois(){
        if(mois == 0)
            return 0;
        
        int resultatProvisoire = 31;
        
        if ((mois == 4) || (mois == 6) || (mois == 9) || (mois == 11)){
            resultatProvisoire = 30;
        }
        if (mois == 2){
            resultatProvisoire = 28;
            if (anneeBissextile())
                resultatProvisoire = 29;
        }
        
        return resultatProvisoire;
    }
    public boolean estValide(){
        if ( an < 1600 || an > 2400 || mois < 1 || mois > 12
                || jour < 1 || jour > nbJourDuMois()){
            return false;
        }
        return true;
    }
    public MaDate lendemain(){
        if(an == 0 || mois == 0)
            return new MaDate();
        
        int j, m = mois, a = an;
        j = jour + 1;
        if (j > nbJourDuMois()){
            j = 1;
            m = m + 1;
            if (m > 12){
                m = 1;
                a = a +1;
            }
        }
        return new MaDate(j,m,a);
  }
    
}
