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

import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;


/**
 * Given a term return all related terms in the taxonomy. The taxonomy must
 * have  relations "wide" and "narrowed". For a term, this class can retrieve one or both of them.
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class Surrogate implements iSurrogate {

    protected String term;
    protected Model model;
    protected String base_uri = "http://glluch.com/ieee_taxonomy#";
    protected DataMapper dataMapper;

    /**
     * Create an instance base of a term and a org.apache.jena.rdf.model.
     * @param term The term for which related terms are needed.
     * @param model The org.apache.jena.rdf.model.
     */
    public Surrogate(String term, Model model) {
        this.term = term;
        this.model = model;
        dataMapper = new DataMapper();
        dataMapper.setModel(this.model);
    }

    /**
     * Retrieve the terms related to the surrogate.term in the model
     * @param mode In this implementation could be "wide" or "narrowed".
     * @return An array of Terms with the relation "mode", from the label of the RDF/XML. 
     */
    @Override
    public ArrayList<String> getSurrogates(String mode) {
        String term_id = this.term.replace(" ", "_");
        ArrayList<String> surros = new ArrayList<>();
        ArrayList<String> uris;
        String queryString = preQuery(mode, term_id);
        if (queryString.isEmpty()) {
            return surros;
        }//TODO error
        uris = dataMapper.getUris(queryString, mode);

        uris.stream().forEach((uri) -> {
            //System.out.println(uri);
            String label=dataMapper.getLabel(uri);
            if (!label.isEmpty()) {
                surros.add(dataMapper.getLabel(uri));
            }
        });
        return surros;
    }

    /**
     * Retrieve the terms related to the surrogate.term in the model. 
     * @return An array of terms related to this.term in all modes
     */
    @Override
    public ArrayList<String> getSurrogates() {

        ArrayList<String> surros;
        surros=getSurrogates("wide");
        surros.addAll(this.getSurrogates("narrowed"));
        return surros;

    }

    /**
     * Prepare the statement for a sparql command.
     * @param mode Could be "wide" or "narrowed"
     * @param term_id It is the term in uppercase and underscore instead of spaces
     * @return The sentence to be done in a string
     */
    protected String preQuery(String mode, String term_id) {
        String query = "";
        if (mode.equals("wide")) {
            query = " SELECT ?parent " + "WHERE {<" + base_uri + term_id + ">"
                    + "  <" + base_uri + mode + ">  ?parent} ";
        }
        if (mode.equals("narrowed")) {
            query = " SELECT ?child " + "WHERE {<" + base_uri + term_id + ">"
                    + "  <" + base_uri + mode + ">  ?child} ";
        }
        //System.out.println(query);
        return query;
    }

}
