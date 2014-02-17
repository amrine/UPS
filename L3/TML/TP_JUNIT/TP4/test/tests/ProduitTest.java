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
public class ProduitTest extends TestCase{

	private Produit produit1, produit2, produit3, produit4;
	
	
	public ProduitTest(String name){
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		produit1 = new Produit("yaourt", 1.5);
		produit2 = new Produit("steak", 1.8);
		produit3 = new Produit("yaourt", 1.5);
		produit4 = new Produit();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		produit1 = null;
		produit2 = null;
		produit3 = null;
		produit4 = null;
	}
	
	public void testNullNotNull(){
		produit3 = null;
		assertNotNull(produit1);
		assertNotNull(produit4);
		assertNull(produit3);
	}
	
	public void testEquals(){
		Object object = null;
		boolean expResult = false;
        boolean result = produit4.equals(object);
        		
		assertTrue(produit1.equals(produit1));
		assertFalse(produit1.equals(produit2));
		assertTrue(produit1.equals(produit3));
		assertEquals(produit1, produit3);
		assertEquals(expResult, result);
	}
	
	public void testSame(){
		assertSame(produit1, produit1);
		assertNotSame(produit1, produit3);
		assertNotSame(produit1, produit2);
	}
	
	public void testGetPrix(){
		double expResult = 0.0;
        double result = produit4.getPrix();
        
        assertEquals(expResult, result, 0.0);
		assertEquals(1.5, produit1.getPrix());
	}
	
	public void testGetNom(){
        String expResult = "";
        String result = produit4.getNom();
        
		assertTrue("yaourt".equals(produit3.getNom()));
		assertEquals(expResult, result);
	}
	
	public void testSetPrix(){
		double prix = 20;
		produit4.setPrix(prix);
		
		assertTrue(produit4.getPrix() == prix);
	}
	
	public void testSetNom(){
		String nom = "MASSIE";
		produit4.setNom(nom);
		produit3.setNom("yaourt2");
		
		assertSame("yaourt2", produit3.getNom());
		assertTrue(produit4.getNom().equals(nom));
	}
	
}
