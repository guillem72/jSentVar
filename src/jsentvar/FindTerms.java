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
import java.util.Set;

/**
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class FindTerms {

    public static ArrayList<String> candidates;

    public ArrayList<String> found(String doc0) {
        String doc = doc0.toLowerCase();
        HashMap<String, Integer> hits = new HashMap<>();
        for (String candi0 : candidates) {
            String candi = candi0.toLowerCase();
            if (doc.contains(candi)) {
                System.out.println(candi + " found");
                if (hits.containsKey(candi)) {
                    hits.replace(candi, hits.get(candi) + 1);
                } else {
                    hits.put(candi, 1);
                }
            }
        }
        System.out.println("hits=" + hits.toString());
        return removeInsideTerms(hits);
    }

    protected ArrayList<String> removeInsideTerms(HashMap<String, Integer> f) {
        ArrayList<String> terms = new ArrayList<>();

        Set keys = f.keySet();
        ArrayList<String> rest = new ArrayList<>();
        
        rest.addAll(keys);
        for (Object key : keys) {
            String term = (String) key;
            rest.remove(term);
            if (!rest.isEmpty()) {
                for (String other : rest) {
                    if (!other.contains(term) && !term.contains(other)) {
                        terms.add(term);
                    } else {
                        if (f.get(other) < f.get(term) && other.contains(term)) {
                            terms.add(term);
                        }
                        if (term.contains(other) && f.get(other) <= f.get(term)) {
                            rest.remove(other);
                        }
                    }//else
                }//for (String other : rest)
            }
        }//for (Object key : keys)
        return terms;
    }
}
