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
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package jsentvar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * A class for substitutes in a doc some terms by others.
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class Substitution {

    /**
     * Substitutes the term "original" in "doc" for each candidate in
     * "candidates".
     *
     * @param doc The document where substitutions will make.
     * @param original The original term to be substituted.
     * @param candidates Every term that can substitute the original term
     * @return A set made by docs in one term had been sustituted
     */
    public HashSet
            oneTerm(String doc, String original, ArrayList<String> candidates) {
        HashSet newDocs = new HashSet();
        Utils utils = new Utils();
        for (String candidate : candidates) {
            String doc0 = utils.replaceCaseInsensitive(doc, original, candidate);
            if (!doc0.isEmpty()) {
                //doc0 = original + " -> " + candidate + "\n\n" + doc0;
                newDocs.add(doc0);
            }
        }

        return newDocs;
    }

    /**
     * Substitutes each term in "doc" for each related term. 
     * @param doc The base text  where to do substituions
     * @param alternatives A HashMap. The keys are the original terms in doc and 
     * alternatives are, for each term, an arrayList with all the substitutive terms.
     * @return terms -&gt; doc_with_term_substituted[]
     */
    public HashSet 
        allTerms(String doc, HashMap<String, 
                ArrayList<String>> alternatives) {
        Set terms=alternatives.keySet();
        HashSet newDocs = new HashSet();
        for (Object term0 : terms) {
            System.out.println(term0);
            String term=(String) term0;
            HashSet newDocs0 = this.oneTerm(doc, term, alternatives.get(term));
            newDocs.addAll(newDocs0);
        }
        return newDocs;
    }
}
