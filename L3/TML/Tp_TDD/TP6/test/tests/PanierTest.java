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

	private Panier panier;
	
	public PanierTest(String name){
		super(name);
	}
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		panier = new Panier();
	}@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		panier = null;
	}
	public void testNullNotNull(){
		assertTrue(panier != null);
	}
	public void testGetMontantTotal(){
        double expResult = 0;
        double result = panier.getMontantTotal();
        assertEquals(expResult, result, 0);
	}
	public void testAjouterProduit(){
		boolean expectedResult = true;
		Produit produit = new Produit("yaourt", 3);
		boolean result = panier.ajouterProduit(produit, 6);
		
		assertEquals(expectedResult, result);
		assertTrue(panier.getMontantTotal() == 18);
		assertEquals(false, panier.ajouterProduit(null, 10));
		assertTrue(panier.getMontantTotal() == 18);
	}
	public void testEquals(){
		Panier panier1 = new Panier();
		Produit produit1 = new Produit("yaourt", 3);
		
		assertTrue(panier1.equals(panier));
		panier1.ajouterProduit(produit1, 6);
		assertFalse(panier.equals(panier1));
		
		Object obj = null;
        Panier instance = new Panier();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
	}
	public void testSame(){
		Panier panier1 = new Panier();
		assertSame(panier, panier);
		assertNotSame(panier1, panier);
	}
	public void testPanierVide(){
		boolean expResult = true;
		boolean result = panier.panierVide();
		assertEquals(expResult, result);
	}
}
