/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Objects;

/**
 *
 * @author alphaoumar
 */
public class Paire<T, U> {
    private T first;
    private U second;

    public Paire(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.first);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paire<T, U> other = (Paire<T, U>) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        return true;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Paire{" + "first=" + first + ", second=" + second + '}';
    }
}
