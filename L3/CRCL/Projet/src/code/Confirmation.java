/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

/**
 *
 * @author 21007631
 * 
 * Cette enumeration contient des messages de confirmation
 * SUCESS si une operation effectue est une réussite
 * WAITING_LIST si un utilisateur a ete ajoute dans la liste d'attente
 * ERROR si une operation effectue est un echec
 * FULL si la file d'attente est pleine
 */
public enum Confirmation {
    SUCCESS, WAITING_LIST, ERROR, FULL, BLACK_LIST
}
