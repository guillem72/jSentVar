package jsentvar;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

/**
 *
 * @author Guillem LLuch Moll <guillem72@gmail.com>
 */
public class JSentVar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
        //Log.setLog4j("jena-log4j.properties");
        RDFReader lector = new RDFReader();
        String filename;
        filename = "/root/sincron/programacio/java"+
                "/netbeans/jSentVar/resources/IEEE_reasoner20022016.owl";
        Model model = ModelFactory.createDefaultModel();
        
        String term_value = "Approximation methods";
        model = lector.reader(filename);
        Resource term = model.getResource(term_value);
        //StmtIterator properties = term.listProperties();
        System.out.print(term.toString() + "\n");
        
        Surrogate sur=new Surrogate(term_value,model);
        ArrayList <String> new_terms=sur.getSurrogates("wide");
        System.out.println(new_terms.toString());

        
    }

}
