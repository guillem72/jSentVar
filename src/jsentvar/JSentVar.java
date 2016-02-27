package jsentvar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FileUtils;

import org.apache.jena.rdf.model.Model;

/**
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class JSentVar {

    /**
     * @param args the command line arguments
     * args0 the taxonomy
     * args1 the positions
     * args2 the doc
     * @throws java.io.FileNotFoundException When reads a file
     *
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
//Get terms for substitution
        RDFReader lector = new RDFReader();
        String filename;
        //filename = "resources/test/miniReasoner.owl";
        filename="resources/IEEE_reasoner20022016.owl";
        if (args.length>0) filename=args[0];
        Model model;
        model = lector.reader(filename);
        //test getallterms
        
        DataMapper dm=new DataMapper();
        dm.setModel(model);
        ArrayList<String> labels=dm.getAllLabels();
        //System.out.println(labels.size());
        
        FindTerms finder=new FindTerms();
        FindTerms.candidates=labels;
        String docFile="resources/tex_doc0.txt";
         String doc=FileUtils.readFileToString(new File(docFile),"utf8");
        ArrayList<String> f=finder.found(doc);
        System.out.println(f);
        
//Read positions, a json file
        String possFile = "resources/text_doc0.json";
        if (args.length>1) possFile=args[1];
        JsonReader jreader = new JsonReader();
        HashMap<String, HashMap<Integer, Integer>> poss = jreader.reader(possFile);
        ArrayList<String>terms1=new ArrayList<>();
      
        Set originS=poss.keySet();
          terms1.addAll(originS);
        System.out.println(terms1);

        
//Get all the terms for substitution

        Surrogate sur = new Surrogate(model);
        HashMap<String, ArrayList<String>> alternatives;
        Utils utils = new Utils();
        ArrayList<String> terms = utils.firstUpperForeach(terms1);
        alternatives= sur.surrogatesForeach(terms);
       
        
        //terms is the terms from taxonomy appering in doc
        //alternatives is a term->related[] hashmap<string,arraylist>
       
        
         if (args.length>2) docFile=args[2];
        String dirTarget="out/files/";
        String fileTarget="doc0_alt";
       
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
