/**
 * 
 */
package tests;

import code.Monnaie;
import junit.framework.TestCase;

/**
 * @author aob
 *
 */
public class MonnaieTest extends TestCase{
	private Monnaie monnaie1, monnaie2;
	
	public void setUp() throws Exception {
		super.setUp();
		monnaie1 = new Monnaie("EUR", 5);
		monnaie2 = new Monnaie("USD", 10);
	}
	public void tearDown() throws Exception {
		super.tearDown();
		monnaie1 = null;
		monnaie2 = null;
	}
	public MonnaieTest(String name){
		super(name);
	}
	
	public void testNonNull(){
		assertNotNull(monnaie1);
		monnaie2 = null;
		assertNull(monnaie2);
	}
	
	public void testGetMontant(){
		assertEquals(5.0, monnaie1.getMontant());
	}
	
	public void testGetDevise(){
		monnaie2 = new Monnaie();   
        
		assertTrue("EUR".equals(monnaie1.getDevise()));
		assertTrue(monnaie1.getDevise().length() == 3);
		assertEquals("", monnaie2.getDevise());
	}
	
	public void testEquals(){
		Monnaie monnaie3 = new Monnaie("USD", 10);
		
		assertFalse(monnaie1.equals(monnaie2));
		assertTrue(monnaie2.equals(monnaie2));
		assertEquals(monnaie2, monnaie3);
		
		monnaie3 = new Monnaie();
		assertEquals(false, monnaie3.equals(null));
	}
	
	public void testAjouter() throws Exception{
		Monnaie monnaie3 = new Monnaie("USD", 25);
		monnaie2.ajouter(monnaie3);
		try{
			monnaie2.ajouter(monnaie1);
			monnaie2.ajouter(null);
			fail("Une exception de type IllegalStateException aurait du etre levee");
		}catch(IllegalStateException ex){
			
		}
	}
	
	public void testMultiplier(){
		monnaie1.multiplier(2);
		assertTrue(monnaie1.getMontant() == 10);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
