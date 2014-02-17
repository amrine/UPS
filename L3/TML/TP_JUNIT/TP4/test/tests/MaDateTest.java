/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import code.MaDate;
import junit.framework.TestCase;

/**
 *
 * @author alphaoumar
 */
public class MaDateTest extends TestCase {
	private MaDate instance;
	
    public MaDateTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance = new MaDate();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        instance = null;
    }

    /**
     * Test of getJour method, of class MaDate.
     */
    public void testGetJour() {
    	int expResult = 0;
        int result = instance.getJour();
        assertEquals(expResult, result);
    }

    /**
     * Test of setJour method, of class MaDate.
     */
    public void testSetJour() {
    	int jour = 12;
    	instance = new MaDate(jour, 9, 2014);
        instance.setJour(jour);
        assertEquals("test set jour", jour, instance.getJour());
    }

    /**
     * Test of getMois method, of class MaDate.
     */
    public void testGetMois() {
    	instance = new MaDate();
        int expResult = 0;
        int result = instance.getMois();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMois method, of class MaDate.
     */
    public void testSetMois() {
        int mois = 10;
        instance = new MaDate();
        instance.setMois(mois);
        assertEquals("test set mois", mois, instance.getMois());
    }

    /**
     * Test of getAns method, of class MaDate.
     */
    public void testGetAns() {
    	instance = new MaDate();
        int expResult = 0;
        int result = instance.getAns();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAns method, of class MaDate.
     */
    public void testSetAns() {
        int ans = 1998;
        instance = new MaDate(01, 02, 1998);
        instance.setAns(ans);
        assertEquals(ans, instance.getAns());
    }

    /**
     * Test of equals method, of class MaDate.
     */
    public void testEquals() {
        Object obj = null;
        instance = new MaDate();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of anneeBissextile method, of class MaDate.
     */
    public void testAnneeBissextile() {
        instance = new MaDate(12, 02, 2011);
        boolean expResult = false;
        boolean result = instance.anneeBissextile();
        assertEquals(expResult, result);
    }

    /**
     * Test of nbJourDuMois method, of class MaDate.
     */
    public void testNbJourDuMois() {
        instance = new MaDate();
        int expResult = 0;
        int result = instance.nbJourDuMois();
        assertEquals(expResult, result);
    }

    /**
     * Test of estValide method, of class MaDate.
     */
    public void testEstValide() {
        instance = new MaDate();
        boolean expResult = false;
        boolean result = instance.estValide();
        assertEquals(expResult, result);
    }

    /**
     * Test of lendemain method, of class MaDate.
     */
    public void testLendemain() {
        instance = new MaDate();
        MaDate expResult = new MaDate();
        MaDate result = instance.lendemain();
        assertEquals(expResult, result);
    }
}
