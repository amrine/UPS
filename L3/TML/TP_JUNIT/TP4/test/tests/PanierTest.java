/**
 * 
 */
package tests;

import code.Panier;
import code.Produit;
import junit.framework.TestCase;
/**
 * @author aob
 *
 */
public class PanierTest extends TestCase{
	private Panier panier1, panier2;
	
	
	public PanierTest(String name){
		super(name);
	}
	
	protected void setUp() throws Exception{
		super.setUp();
		panier1 = new Panier();
		panier2 = null;
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		panier1 = null;
		panier2 = null;
	}

	public void testNull(){
		assertNotNull(panier1);
		assertNull(panier2);
	}
	
	public void testPanierVide(){
		boolean expResult = true;
		boolean result = panier1.panierVide();
		assertEquals(expResult, result);
	}
	
	public void testSame(){
		assertSame(panier1, panier1);
		assertNotSame(panier1, panier2);
	}
	
	public void testGetMontantTotal(){
        double expResult = 0.0;
        double result = panier1.getMontantTotal();
        assertEquals(expResult, result, 0.0);
	}
	
	public void testAjouterProduit(){
		Produit produit1 = new Produit("yaourt", 3);
		Produit produit2 = null;
		panier2 = new Panier();
        int quantite = 0;
        panier2.ajouterProduit(produit2, quantite);
		panier1.ajouterProduit(produit1, 6);
		
		assertTrue(panier1.getMontantTotal() == 18);
        assertTrue(panier2.panierVide());
	}
	
	public void testEquals(){
		panier2 = new Panier();
		Produit produit1 = new Produit("yaourt", 3);
		
		assertTrue(panier1.equals(panier2));
		panier1.ajouterProduit(produit1, 6);
		assertFalse(panier2.equals(panier1));
		
		Object obj = null;
        Panier instance = new Panier();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
	}
}
