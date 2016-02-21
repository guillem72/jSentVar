package jsentvar;

import java.util.ArrayList;
import java.util.Iterator;
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
    protected DataMapper dataMapper;

    public Surrogate(String term, Model model) {
        this.term = term;
        this.model = model;
        dataMapper = new DataMapper();
        dataMapper.setModel(this.model);
    }

    @Override
    public ArrayList<String> getSurrogates(String mode) {
        String term_id = this.term.replace(" ", "_");
        ArrayList<String> surros = new ArrayList<>();
        ArrayList<String> uris = new ArrayList<>();
        String queryString = preQuery(mode, term_id);
        if (queryString.isEmpty()) {
            return surros;
        }//TODO error
        uris = dataMapper.getUris(queryString, mode);

        for (String uri : uris) {
            //System.out.println(uri);
            String label=dataMapper.getLabel(uri);
            if (!label.isEmpty())
            surros.add(dataMapper.getLabel(uri));
        }
        return surros;
    }

    @Override
    public ArrayList<String> getSurrogates() {

        ArrayList<String> surros = new ArrayList<>();

        return surros;

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
        System.out.println(query);
        return query;
    }

}
