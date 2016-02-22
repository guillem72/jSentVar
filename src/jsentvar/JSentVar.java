package jsentvar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;


/**
 *
 * @author Guillem LLuch Moll <guillem72@gmail.com>
 */
public class JSentVar {

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException  {
        // org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
        //Log.setLog4j("jena-log4j.properties");
        
        RDFReader lector = new RDFReader();
        String filename;
        filename = "resources/test/miniReasoner.owl";
        Model model;
        
        String term_value0 = "intelligent_systems";
        String term_value = term_value0.substring(0, 1).toUpperCase() +term_value0.substring(1);
        model = lector.reader(filename);
        
             
        
        Resource term = model.getResource(term_value);
       
        System.out.print(term.toString() + "\n");
        
        Surrogate sur=new Surrogate(term_value,model);
      
        ArrayList <String> new_terms=sur.getSurrogates("wide");
        System.out.println(new_terms.toString());

        
        //Test Results generation
   GenerateTestsResults gtr=new GenerateTestsResults(model);
       // gtr.getUrisResult();
       //gtr.readerResult();
        
    }

}
