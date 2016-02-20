package jsentvar;

import java.util.Iterator;
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
        filename = "/root/sincron/programacio/java/netbeans/jSentVar/resources/TaxoCorregidaXMLRDF.owl";
        Model model = ModelFactory.createDefaultModel();
        String term_value = "http://glluch.com/ieee_taxonomy#ARPANET";
        model = lector.reader(filename);
        Resource term = model.getResource(term_value);
        StmtIterator properties = term.listProperties();
        System.out.print(term.toString() + "\n");
        while (properties.hasNext()) {
            Statement prop = properties.nextStatement();
            Resource entity = prop.getResource();
            Resource accessor = prop.getSubject();
            String m = "Object=" + entity.toString() + ", accessor=" + accessor.toString();
            m = m + " rel=" + prop.getPredicate().toString();
            System.out.println(m);
            //getResource()
        }//end while

    }

}
