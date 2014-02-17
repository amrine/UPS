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
public class AllTests {
	
	public static Test suite(){
		TestSuite suite = new TestSuite("Test for All");
		
		suite.addTestSuite(ProduitTest.class);
		suite.addTestSuite(PanierTest.class);
		suite.addTestSuite(MonnaieTest.class);
		suite.addTestSuite(MaDateTest.class);
		
		return suite;
		
	}
}
