/*
 * Copyright (C) 2016 Guillem LLuch Moll guillem72@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jsentvar;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class UtilsTest {
    
    public UtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of firstUpper method, of class Utils.
     */
    @Test
    public void testFirstUpper() {
        System.out.println("firstUpper");
        String text = "firstLower";
        Utils instance = new Utils();
        String expResult = "";
        String result = instance.firstUpper(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of firstUpperForeach method, of class Utils.
     */
    @Test
    public void testFirstUpperForeach() {
        System.out.println("firstUpperForeach");
        ArrayList<String> texts = null;
        Utils instance = new Utils();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.firstUpperForeach(texts);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of firstLower method, of class Utils.
     */
    @Test
    public void testFirstLower() {
        System.out.println("firstLower");
        String text = "firstLower";
        Utils instance = new Utils();
        String expResult = "";
        String result = instance.firstLower(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replaceCaseInsensitive method, of class Utils.
     */
    @Test
    public void testReplaceCaseInsensitive() {
        System.out.println("replaceCaseInsensitive");
        String doc0 = "";
        String old = "";
        String newterm = "";
        Utils instance = new Utils();
        String expResult = "";
        String result = instance.replaceCaseInsensitive(doc0, old, newterm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsCaseInsensitive method, of class Utils.
     */
    @Test
    public void testContainsCaseInsensitive() {
        System.out.println("containsCaseInsensitive");
        String doc = "";
        String term = "";
        boolean expResult = false;
        boolean result = Utils.containsCaseInsensitive(doc, term);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringContains method, of class Utils.
     */
    @Test
    public void testStringContains() {
        System.out.println("stringContains");
        String text = "I will come and meet you at the woods 123woods and all the woods";
        String term = "woods";
        boolean expResult = true;
        boolean result = Utils.stringContains(text, term);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
     /**
     * Test of stringContains method, of class Utils.
     */
    @Test
    public void testStringContains2() {
        System.out.println("stringContains2");
        String text = "I will come and meet you at the woods 123woods and all the woods";
        String term = "wood";
        boolean expResult = false;
        boolean result = Utils.stringContains(text, term);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
