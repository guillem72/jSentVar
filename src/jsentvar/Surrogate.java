package jsentvar;

import java.util.ArrayList;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;

/**
 *
 * @author Guillem LLuch Moll <guillem72@gmail.com>
 */
public class Surrogate implements iSurrogate {

    protected String term;
    protected Model model;
    protected String base_uri = "http://glluch.com/ieee_taxonomy#";

    public Surrogate(String term, Model model) {
        this.term = term;
        this.model = model;
    }

    @Override
    public ArrayList<String> getSurrogates(String mode) {
        String term_id = this.term.replace(" ", "_");
        ArrayList<String> surros = new ArrayList<>();
        String queryString = preQuery(mode, term_id);
        if (queryString.isEmpty()) {
            return surros;
        }//TODO error

        Query query = QueryFactory.create(queryString);

        try (// Execute the query and obtain results
                QueryExecution qe = QueryExecutionFactory.create(query, this.model)) {
            ResultSet results = qe.execSelect();
// Output query results       ResultSetFormatter.out(System.out, results, query);
// Important - free up resources used running the query
            for (; results.hasNext();) {
                // Access variables: soln.get("x") ;
                RDFNode n;
                QuerySolution soln = results.next();
                if (mode.equals("wide"))
                n = soln.get("parent"); // "x" is a variable in the query
                else n = soln.get("child");
                System.out.println(n);
               
            }
        }
        return surros;
    }

    @Override
    public ArrayList<String> getSurrogates() {

        ArrayList<String> surros = new ArrayList<>();

        return surros;

    }
    
    protected ResultSet doQuery(){
        ResultSet results=null;
        
        return results;
    }
    protected String preQuery(String mode, String term_id) {
        String query = "";
        if (mode.equals("wide")) {
            query = " SELECT ?parent " + "WHERE {<" + base_uri + term_id + ">"
                    + "  <" + base_uri + mode + ">  ?parent} ";
        }
        if (mode.equals("narrowed")) {
            query = " SELECT ?child " + "WHERE { ?child   <" + base_uri + mode + "> "
                    + "<" + base_uri + term_id
                    + ">} ";
        }
        //System.out.println(query);
        return query;
    }
    
    protected String queryLabel(){
    String label="";
    return label;
    }

}
