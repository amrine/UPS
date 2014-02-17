/**
 * 
 */
package tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author aob
 *
 */
public class AllTest {
	
	public static Test suite(){
		TestSuite suite = new TestSuite("Test des deux classes");
		
		suite.addTestSuite(ProduitTest.class);
		suite.addTestSuite(PanierTest.class);
		
		return suite;
		
	}
}
