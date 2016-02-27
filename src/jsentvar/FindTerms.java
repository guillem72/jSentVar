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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class FindTerms {

    public static ArrayList<String> vocabulary;

    /**
     *  Return all the terms from the vocabulary found in doc0. Note that return the 
     * larger term found, so for "Management System" it returns one term not "Management"
     * and "System".
     * @param doc0 The doc where the terms will be searched
     * @return A list made by the found terms. 
     */
    public ArrayList<String> found(String doc0) {
        String doc = doc0.toLowerCase();
        HashMap<String, Integer> hits = new HashMap<>();
        for (String candi0 : vocabulary) {
            String candi = candi0.toLowerCase();
            if (doc.contains(candi)) {
                //System.out.println(candi + " found");
                
                    String[] parts=doc.split(candi);
                    //System.out.println(" Parts="+parts);
                    hits.put(candi,parts.length-1);
                
            }
        }
        //System.out.println("hits=" + hits.toString());
        return removeInsideTerms(hits);
    }

    /**
     * Internal function which removes a term that is part of another term.
     * @param f An array term-&gt; hits of the term
     * @return A list without the non proper terms deleted.
     */
    protected ArrayList<String> removeInsideTerms(HashMap<String, Integer> f) {
        ArrayList<String> terms = new ArrayList<>();//the good
        Stack pila=new Stack();
        
        Set keys = f.keySet();//all term found, term form part of other term, too
        pila.addAll(keys);
        
       while (!pila.empty()){ 
       String t1=(String) pila.pop();
                Stack rest=(Stack)pila.clone();
                while (!rest.empty()){
                    String t2=(String)rest.pop();
                    //if one term t2 is in the other t1 and apper the same number of times,  
                    //it means than t2 is not a proper term, only is part of a larger term so
                    //it has to be removed (from f).
                    if (t1.contains(t2) && f.get(t1)>=f.get(t2)){//TODO throw error if f.get(t1)>f.get(t2)
                        //.remove(t2);
                        f.remove(t2);
                    }
                    if (t2.contains(t1) && f.get(t2)>=f.get(t1)){//TODO throw error if f.get(t2)>f.get(t1)
                        f.remove(t1);
                    }
                }
       }
       terms.addAll(f.keySet());
       
        return terms;
    }
}
