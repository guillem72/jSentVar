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


