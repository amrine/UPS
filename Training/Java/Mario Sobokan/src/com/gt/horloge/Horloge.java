package com.gt.horloge;

import java.util.ArrayList;
import java.util.Calendar;

import com.gt.observer.Observable;
import com.gt.observer.Observateur;

public class Horloge extends Thread implements Observable{

	//On récupère l'instance d'un calendrier 
	//celui-ci va nous permettre de récupérer l'heure actuelle
	private Calendar cal;
	private String hour = "";
	//Notre collection d'observateurs !
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	
	public Horloge(){
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		while(true){
			this.cal = Calendar.getInstance();
			this.hour = 	/* Les heures */
					this.cal.get(Calendar.HOUR_OF_DAY) + " : " 
					+ 
					( 		/* Les minutes */
						this.cal.get(Calendar.MINUTE) < 10
						? "0" + this.cal.get(Calendar.MINUTE)
						: this.cal.get(Calendar.MINUTE)
					)
					+ " : " 
					+
					( 		/* Les secondes */
						(this.cal.get(Calendar.SECOND)< 10) 
						? "0"+this.cal.get(Calendar.SECOND) 
						: this.cal.get(Calendar.SECOND)
					);
			
			//On avertit les observateurs que l'heure a été mise à jour !
			this.notifierObservateurs();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	
	public void ajouterObservateur(Observateur o) {
		this.listObservateur.add(o);
		
	}

	
	public void supprimerObservateur(Observateur o) {
		this.listObservateur = new ArrayList<Observateur>();
		
	}

	
	public void notifierObservateurs() {
		for(Observateur obs : this.listObservateur )
			obs.update(this.hour);
		
	}
}
