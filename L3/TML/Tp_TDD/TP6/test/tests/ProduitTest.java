/**
 * 
 */
package tests;

import code.Produit;
import junit.framework.TestCase;

/**
 * @author aob
 * 
 */
public class ProduitTest extends TestCase {
	private Produit produit;
	
	public ProduitTest(String name) {
		super(name);
	}
	

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		produit = new Produit();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		produit = null;
	}


	public void testNullNotNull() {
		boolean expResult = true;
		boolean result = (produit != null);

		assertEquals("un produit instancie n'est pas null", expResult, result);
	}
	public void testGetNom(){
		assertEquals("", produit.getNom());
	}
	public void testGetPrix(){
		assertEquals(0.0, produit.getPrix());
	}
	public void testSetPrix(){
		double prix = 20;
		produit.setPrix(prix);
		
		assertTrue(produit.getPrix() == prix);
	}
	public void testSetNom(){
		produit.setNom("yaourt2");
		
		assertSame("yaourt2", produit.getNom());
	}
	
	public void testEquals(){
		String nom = "yaourt";
		double prix = 1.05;
		produit = new Produit(nom, prix);
		assertTrue(nom.equals(produit.getNom()));
		assertEquals(prix, produit.getPrix());
		
		Object object = null;
		boolean expResult = false;
        boolean result = produit.equals(object);
        assertEquals(expResult, result);
        
        Produit instance = new Produit(nom, prix);
        assertEquals(produit, instance);
	}
	

}
