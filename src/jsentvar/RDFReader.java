/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsentvar;

import java.io.InputStream;
//import static org.apache.jena.assembler.JA.Model;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

/**
 *
 * @author Guillem LLuch Moll <guillem72@gmail.com>
 */
public class RDFReader {

    /**
     *
     * @param inputFileName
     * @return
     */
    public Model reader(String inputFileName) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName + " not found");
        }

// read the RDF/XML file
        model.read(in, null);

// write it to standard out
        //model.write(System.out);

        return model;
    }
}


