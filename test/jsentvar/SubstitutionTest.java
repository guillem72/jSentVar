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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.jena.rdf.model.Model;
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
public class SubstitutionTest {
    
    public SubstitutionTest() {
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
     * Test of oneTerm method, of class Substitution.
     */
    @Test
    public void testOneTerm() throws IOException {
        System.out.println("Substitution.oneTerm() Test");
        RDFReader lector = new RDFReader();
        String filename;
        //filename = "resources/test/miniReasoner.owl";
        filename="resources/IEEE_reasoner20022016.owl";
        Model model;
        //String term_value = term_value0.substring(0, 1).toUpperCase() +term_value0.substring(1);
        
        model = lector.reader(filename);
        
//Read positions, a json file
        String possFile = "resources/test/text_doc0.json";
        JsonReader jreader = new JsonReader();
        HashMap<String, HashMap<Integer, Integer>> poss = jreader.reader(possFile);
        /*
        try {
            System.out.println(poss.toString());
        } catch (NullPointerException e) {
            System.out.println("ERROR showing results " + e);
        }
        */
        ArrayList<String>terms1=new ArrayList<>();
      
        Set originS=poss.keySet();
          terms1.addAll(originS);
        

        
//Get all the terms for substitution
/*
ArrayList<String> terms0 = new ArrayList<>();
        terms0.add("military satellites");
        terms0.add("intelligent_systems");
*/
        Surrogate sur = new Surrogate(model);
        HashMap<String, ArrayList<String>> alternatives;
        Utils utils = new Utils();
        ArrayList<String> terms = utils.firstUpperForeach(terms1);
        alternatives= sur.surrogatesForeach(terms);
        //System.out.println(alternatives.toString());
        
       
        String docFile="resources/test/tex_doc0.txt";
       String doc=FileUtils.readFileToString(new File(docFile),"utf8");
        Substitution gen=new Substitution();
       HashSet newDocs =new HashSet();
         for (String term:terms){
         System.out.println(term);
           HashSet newDocs0=gen.oneTerm(doc, term, alternatives.get(term));
           newDocs.addAll(newDocs0);
        }
               
        String expResult = FileUtils.readFileToString
        (new File("resources/test/substitutionOneTermResult.txt"),
                "utf8");
        assertEquals(expResult, newDocs.toString());
        
    }
    
}
