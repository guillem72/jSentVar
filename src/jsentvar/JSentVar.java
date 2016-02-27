package jsentvar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FileUtils;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class JSentVar {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException When reads a file
     *
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
        //Log.setLog4j("jena-log4j.properties");

//Get terms for substitution
        RDFReader lector = new RDFReader();
        String filename;
        //filename = "resources/test/miniReasoner.owl";
        filename="resources/IEEE_reasoner20022016.owl";
        Model model;
        //String term_value = term_value0.substring(0, 1).toUpperCase() +term_value0.substring(1);
        
        model = lector.reader(filename);
        
//Read positions, a json file
        String possFile = "resources/text_doc0.json";
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
        //terms is the terms from taxonomy appering in doc
        //alternatives is a term->related[] hashmap<string,arraylist>
       
        String docFile="resources/tex_doc0.txt";
        String dirTarget="out/files/";
        String fileTarget="doc0_alt";
        String doc=FileUtils.readFileToString(new File(docFile),"utf8");
        Substitution gen=new Substitution();
       HashSet newDocs =gen.allTerms(doc, alternatives);
         int i=0;
         for (Object newDoc:newDocs){
             i++;
             String name;
             name=dirTarget+fileTarget+Integer.toString(i)+".txt";
             FileUtils.writeStringToFile(new File(name),(String) newDoc, "utf8");
         }
        
//Test Results generation

        //GenerateTestsResults gtr = new GenerateTestsResults(model);
        // gtr.getUrisResult();
        //gtr.readerResult();
        //gtr.surrogateForeachResult();
         // gtr.substitutionOneTermResult();
    }

}
