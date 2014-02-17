package com.gt.controler;

import com.gt.model.Model;
import com.gt.observer.Observable;

public class Controler {
	
	private Model model;
	
	public Controler(Observable mod){
		this.model = (Model)mod;
	}
	
	public void control(char c){
            
            if (c == 'E'){
            	char tab[] = {'É', 'È' , 'Ê' , 'Ë', c};
            	this.model.assign(tab);
            }                     

            else if (c == 'A'){
            	char tab[] = {'À', 'Â' , 'Ä', c};
            	this.model.assign(tab);
            }                  

            else if(c == 'I') {
            	char tab[] = {'Î', 'Ï', c };
            	this.model.assign(tab);
            }

            else if (c == 'O') {
            	char tab[] = {'Ô', 'Ö', c };
            	this.model.assign(tab);
            }
            else if (c == 'U'){
            	char tab[] = {'Û', 'Ü', c };
            	this.model.assign(tab);
            }

            else if (c == 'C'){
            	char tab[] = {'Ç', c };
            	this.model.assign(tab);
            }
            
            else
            	this.model.assign(c);
   }

}
