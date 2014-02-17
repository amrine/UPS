/**
 * 
 */
package humain;

/**
 * @author diallo
 *
 */
public class MonHistoire {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Humain h=new Humain (10,"Porto","Prof");
		h.direBonjour();
		h.boire();
		
		Commercant c=new Commercant (35,"Marchant");
		c.direBonjour();
		
		Yakuza y=new Yakuza(42,"biere","Yaku le noir","WarSong");
		y.direBonjour();
		y.extorquer(c);
		
		Ronin r=new Ronin (61,"sake","Roro");
		r.donner(10,c);
		r.provoquer(y);
		r.direBonjour();
		
		Samourai musaichi = new Samourai (20,"the","Samourai","Miyamoto");
		musaichi.direBonjour();
		musaichi.boire("heineken");
		
		Traitre t= new Traitre(100,"jack daniels","nakamura",musaichi.getSeigneur());
		t.direBonjour();
		t.extorquer(c);
		t.extorquer(c);
		t.extorquer(c);
		t.extorquer(c);
		t.faireLeGentil(c,40);
		t.direBonjour();
		
		GrandMere m = new GrandMere(100000,"TATA");
		m.faireConnaissanceAvec(c);
		m.faireConnaissanceAvec(y);
		m.faireConnaissanceAvec(r);
		m.faireConnaissanceAvec(musaichi);
		m.faireConnaissanceAvec(t);
		m.ragoter();
	}

}
