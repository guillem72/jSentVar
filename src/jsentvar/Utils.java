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

/**
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class Utils {
    
    public String firstUpper(String text){
        String ntext = text.substring(0, 1).toUpperCase() +text.substring(1);
        return ntext;
    }
    
    public ArrayList <String> firstUpperForeach(ArrayList <String> texts){
        ArrayList <String> ntexts=new ArrayList <>();
        texts.stream().forEach((text) -> {
            ntexts.add(firstUpper(text));
        });
        return ntexts;
    }
    
    public String firstLower(String text){
        return text.substring(0, 1).toLowerCase() +text.substring(1);
    }
    
    public String replaceCaseInsensitive(String doc0, String old, String newterm){
        String doc="";
        if (doc0.contains(old))
            doc=doc0.replace(old, newterm);
        else
        {   
            if (old.equals(firstLower(old))){ //starts lowerCase
                old=firstUpper(old);
            }
            else old=firstLower(old);
            if (doc0.contains(old))
            doc=doc0.replace(old, newterm);
        } 
        
        return doc;
    }
}
