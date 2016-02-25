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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.toIntExact;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Read a Json file (in specific format) and return it as a hashMap.
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class JsonReader implements iReader {

    /**
     * Read the Json file inputFileName with the positions in a text of some
     * terms and return a hashMap to be use in Java
     *
     * @param inputFileName The name of the file with the positions as      * <code>     
    {
     * "hardware": {
     * "573": 581,
     * "1227": 1235
     * },
     * "software": {
     * "53": 61,
     * "560": 568,
     * "1248": 1256
     * },
     * "management": {
     * "195": 205
     * },
     * "information security": {
     * "352": 372
     * },
     * "documentation": {
     * "429": 442
     * },
     * "system performance": {
     * "1613": 1631
     * }
     * }
     * </code>
     *
     * @return HashMap with a similar structure term -&gt; (inicial position
     * -&gt; end position)
     */
    @Override
    public HashMap<String, HashMap<Integer, Integer>> reader(String inputFileName) {
        // throw new UnsupportedOperationException("Not supported yet."); 
        HashMap<String, HashMap<Integer, Integer>> positions = new HashMap<String, HashMap<Integer, Integer>>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(inputFileName));
            JSONObject jsonObject = (JSONObject) obj;
            //System.out.println(jsonObject);
            //System.out.println(jsonObject.keySet());
            Set keys = jsonObject.keySet();
            Iterator iterkeys = keys.iterator();
            while (iterkeys.hasNext()) {
                Object key = iterkeys.next();
                Object obj0 = parser.parse(jsonObject.get(key).toString());
                JSONObject poss = (JSONObject) obj0;
                Set posKeys = poss.keySet();
                HashMap<Integer, Integer> found = new HashMap<Integer, Integer>();

                Iterator iterposs = posKeys.iterator();
                //System.out.println("keys " + posKeys.toString());
                while (iterposs.hasNext()) {

                    Object ini0 = iterposs.next();
                    Object end0 = poss.get(ini0);
                    String ini1 = (String) ini0;
                    int ini = Integer.parseInt(ini1);
                    int end = Integer.parseInt(end0.toString());

                    // System.out.println(ini1);       
                    //System.out.println("ini=" + ini + ", end=" + end);
                    try {

                        found.put(ini, end);//return a value equal to end
                    } catch (NullPointerException e) {
                        System.out.println("ERROR putin in found " + e);
                    }
                }//iterposs.hasNext()
                try {
                    positions.put(key.toString(), found);
                } catch (NullPointerException e) {
                    System.out.println("ERROR puting in positions " + e);
                }
            }//iterkeys.hasNext()         
        } catch (FileNotFoundException e) {
        } catch (IOException | ParseException e) {
        }

        return positions;
    }

}
