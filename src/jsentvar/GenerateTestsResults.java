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
 * along with this program.  If not, see http://www.gnu.org/licenses
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

/**
 * Aux class for generate several file to tests the classes
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class GenerateTestsResults {

    private Model model;

    //DataMapper
    public GenerateTestsResults(Model model) {
        this.model = model;
    }

    public void getUrisResult() throws IOException {
        System.out.println("getUris");
        String term_value0 = "intelligent_systems";
        String term_value = term_value0.substring(0, 1).toUpperCase() + term_value0.substring(1);
        Surrogate sur = new Surrogate(term_value, this.model);

        String mode = "wide";
        String term_id = term_value.replace(" ", "_");
        String queryString = sur.preQuery(mode, term_id);
        DataMapper instance = new DataMapper();
        instance.setModel(this.model);
        ArrayList<String> result0 = instance.getUris(queryString, mode);
        System.out.println("Generating getUrisResult:");
        System.out.println(result0.toString());
        //Utils.writeFile("resources/test/getUrisResult.txt", result0.toString());
        FileUtils.writeStringToFile(new File("resources/test/getUrisResult.txt"), result0.toString(), "utf8");
    }

    //RDFReader
    public void readerResult() throws IOException {
        RDFReader lector = new RDFReader();
        String filename;
        filename = "resources/test/miniReasoner.owl";
        Model model1;
        model1 = lector.reader(filename);
        System.out.println("readerResult():");
        System.out.println(model1.toString());
        FileUtils.writeStringToFile(new File("resources/test/mini_model.txt"), model1.toString(), "utf8");
    }

    public void jsonReaderResult() throws IOException {
        String possFile = "resources/text_doc0.json";
        JsonReader jreader = new JsonReader();
        HashMap<String, HashMap<Integer, Integer>> poss = jreader.reader(possFile);
        System.out.println(poss.toString());
        FileUtils.writeStringToFile(new File("resources/test/doc0Result.txt"), poss.toString(), "utf8");

    }

    public void surrogateForeachResult() throws IOException {
        ArrayList<String> terms0 = new ArrayList<>();
        terms0.add("military satellites");
        terms0.add("intelligent_systems");

        Surrogate sur = new Surrogate(model);
        HashMap<String, ArrayList<String>> Result;
        Utils utils = new Utils();
        ArrayList<String> terms = utils.firstUpperForeach(terms0);
        Result = sur.surrogatesForeach(terms);
        FileUtils.writeStringToFile(new File("resources/test/surrogateForeachResult.txt"), Result.toString(), "utf8");

    }

    public void substitutionOneTermResult() throws IOException {
 RDFReader lector = new RDFReader();
        String filename;
        //filename = "resources/test/miniReasoner.owl";
        filename="resources/IEEE_reasoner20022016.owl";
        //String term_value = term_value0.substring(0, 1).toUpperCase() +term_value0.substring(1);
        
        model = lector.reader(filename);
        
        
//Read positions, a json file
       String possFile = "resources/test/text_doc0.json";
        JsonReader jreader = new JsonReader();
        HashMap<String, HashMap<Integer, Integer>> poss = jreader.reader(possFile);
        ArrayList<String> terms1 = new ArrayList<>();

        Set originS = poss.keySet();
        terms1.addAll(originS);

//Get all the terms for substitution
        Surrogate sur = new Surrogate(model);
        HashMap<String, ArrayList<String>> alternatives;
        Utils utils = new Utils();
        ArrayList<String> terms = utils.firstUpperForeach(terms1);
        alternatives = sur.surrogatesForeach(terms);
        String docFile = "resources/tex_doc0.txt";
        String doc = FileUtils.readFileToString(new File(docFile), "utf8");
        Substitution gen = new Substitution();
        HashSet newDocs = new HashSet();
        for (String term : terms) {
            //System.out.println(term);
            HashSet newDocs0 = gen.oneTerm(doc, term, alternatives.get(term));
            newDocs.addAll(newDocs0);
        }
        FileUtils.writeStringToFile
        (new File("resources/test/substitutionOneTermResult.txt"), 
                newDocs.toString(), "utf8");

    }

}
