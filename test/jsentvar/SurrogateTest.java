/*
 * Copyright (C) 2016 Guillem LLuch Moll <guillem72@gmail.com>
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guillem LLuch Moll <guillem72@gmail.com>
 */
public class SurrogateTest {
    
    public SurrogateTest() {
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
     * Test of getSurrogates method, of class Surrogate.
     */
    @Test
    public void testGetSurrogates_String() {
        System.out.println("getSurrogates(wide) Test");
        
        RDFReader lector = new RDFReader();
        String filename;
        filename = "resources/test/miniReasoner.owl";
        Model model;
        
        String term_value0 = "intelligent_systems";
        String term_value = term_value0.substring(0, 1).toUpperCase() +term_value0.substring(1);
        model = lector.reader(filename);
        
        Resource term = model.getResource(term_value);
       
        //System.out.print(term.toString() + "\n");
        
        Surrogate sur=new Surrogate(term_value,model);
      
        ArrayList <String> new_terms=sur.getSurrogates("wide");
        //System.out.println(new_terms.toString());
        String result=new_terms.toString().trim();
        String expResult="[Computational and artificial intelligence, Artificial intelligence]";
        
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getSurrogates method, of class Surrogate.
     */
    @Test
    public void testGetSurrogates_0args() {
        System.out.println("getSurrogates Test");
       RDFReader lector = new RDFReader();
        String filename;
        filename = "resources/test/miniReasoner.owl";
        Model model;
        
        String term_value0 = "intelligent_systems";
        String term_value = term_value0.substring(0, 1).toUpperCase() +term_value0.substring(1);
        model = lector.reader(filename);
        
        Resource term = model.getResource(term_value);
       
        //System.out.print(term.toString() + "\n");
        
        Surrogate sur=new Surrogate(term_value,model);
      
        ArrayList <String> new_terms=sur.getSurrogates();
        //System.out.println(new_terms.toString());
        String result=new_terms.toString().trim();
        String expResult="[Computational and artificial intelligence, Artificial intelligence]";
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of preQuery method, of class Surrogate.
     */
    @Test
    public void testPreQuery() {
        System.out.println("preQuery Test");
        String mode = "wide";
        String term_id = "Intelligent_systems";
        Surrogate instance = new Surrogate(null,null);
        String expResult = "SELECT ?parent WHERE {<http://glluch.com/ieee_taxonomy#Intelligent_systems>  <http://glluch.com/ieee_taxonomy#wide>  ?parent}";
        String result = instance.preQuery(mode, term_id).trim();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of surrogatesForeach method, of class Surrogate.
     */
    @Test
    public void testSurrogatesForeach() throws IOException {
        System.out.println("surrogatesForeach");
    
        RDFReader lector = new RDFReader();
        String filename;
        filename = "resources/test/miniReasoner.owl";
        Model model;
       //String term_value = term_value0.substring(0, 1).toUpperCase() +term_value0.substring(1);
       
              model = lector.reader(filename);
        ArrayList<String> terms0=new ArrayList<>();
        terms0.add("military satellites");
        terms0.add("intelligent_systems");
        
        Surrogate sur=new Surrogate(model);
        HashMap<String, ArrayList<String>> result ;
        Utils utils=new Utils();
        ArrayList<String> terms = utils.firstUpperForeach(terms0);
        result=sur.surrogatesForeach(terms);
        System.out.println(result.toString());
        String expResult=FileUtils.readFileToString(new File("resources/test/surrogateForeachResult.txt"), "utf8");
        assertEquals(expResult, result.toString());
        
    }
    
}
