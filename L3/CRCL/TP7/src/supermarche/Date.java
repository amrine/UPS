/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

/**
 *
 * @author 21007631
 */
public class Date implements Comparable<Date>{
    private int day, month, year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Date other = (Date) obj;
        if (this.day != other.day) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Date date) {
        int resultat = Integer.compare(year, date.year);
        if(resultat == 0){
            resultat = Integer.compare(month, date.month);
            if(resultat == 0){
                resultat = Integer.compare(day, date.day);
            }
        }
        
        return resultat;
    }
}
