/**
 * 
 */
package humain;

/**
 * @author diallo
 *
 */
public class GrandMere extends Humain {
	private Humain[] memoire = new Humain[30];
	private int nbreConnaissance =0;

	/**
	 * @param argent
	 * @param nom
	 */
	public GrandMere(int argent, String nom) {
		super(argent, "tisane", nom);
	}
	/**
	 * 
	 * @return type of object
	 */
	@SuppressWarnings("unused")
	private String humainHasard(){
		/*
		 * random entre deux valeurs : Min + (int)(Math.random() * ((Max - Min) + 1))
		 */
		int x = 1+(int)(Math.random()*((5-1)+1));
		String type = null;
		switch(x){
		case 1 : type= "Commercant"; break;
		case 2 : type= "Ronin"; break;
		case 3 : type= "Samourai"; break;
		case 4 : type= "Traitre"; break;
		case 5 : type= "Yakuza"; break;
		default : this.parler("cela doit etre un extra-terrestre jamais vu un Homme de ce type !!! "); break;
		}
		return type;
	}
	
	/**
	 * 
	 * @param humain
	 */
	public void faireConnaissanceAvec(Humain humain){
		this.direBonjour();
		humain.direBonjour();
		if(nbreConnaissance < 30){
			memoire[nbreConnaissance] = humain;
			nbreConnaissance++;
			this.parler("je suis ravi de vous connaitre "+humain.getNom());
			humain.parler("le plaisir est pour moi Mme "+this.getNom());	
		}else{
			this.parler("Oh my God je suis trop vieille, j'ai pas assez de memoire, desole "+humain.getNom());
			humain.parler("ne vous en faites pas Mme "+this.getNom());
		}		
	}
	
	/**
	 * 
	 */
	public void ragoter(){
		for(int i=0;i<nbreConnaissance;i++){
			if(memoire[i] instanceof Traitre) this.parler("Je sais que "+memoire[i].getNom()+" est un Traitre !");
			if(memoire[i] instanceof Commercant) this.parler("Je crois que "+memoire[i].getNom()+" est un Commercant !");
			if(memoire[i] instanceof Ronin) this.parler("Je crois que "+memoire[i].getNom()+" est un Ronin !");
			if(memoire[i] instanceof Yakuza) this.parler("Je crois que "+memoire[i].getNom()+" est un Yakuza !");
			if(memoire[i] instanceof Samourai) this.parler("Je crois que "+memoire[i].getNom()+" est un Samourai !");
		}
		this.parler("je suis une vraie ragoteuse moi !!!!");
	}

}
