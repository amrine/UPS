package com.gt.vue;

import com.gt.model.Model;
import com.gt.observer.Observable;

public class Main {
	public static void main(String[] args){
		Observable model = new Model();
		Fenetre fen = new Fenetre(model);
		fen.setVisible(true);
	}
}
