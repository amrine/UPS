/*Auteur : Diallo Alpha Oumar Binta
Annee scolaire : 2010-2011
Projet Bataille navale
*/

#ifndef DEF_UTILITAIRE
#define DEF_UTILITAIRE

char orientation  (int o) ;
void sauvegarde(t_matrice m_joueur,t_matrice m_videJ,t_matrice m_ordi,t_matrice m_videIA);void charger(t_matrice m_joueur,t_matrice m_videJ,t_matrice m_ordi,t_matrice m_videIA);
char abcisse(int x);
void cheat (t_matrice m);
void sauvegarde_nbcase(int *nbcase_ordi,int *nbcase_joueur);
void charger_nbcase(int *nbcase_ordi,int *nbcase_joueur);


#endif
