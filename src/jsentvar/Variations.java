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

/**
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class Variations {
    protected String phrase;
    protected HashMap<String,Object> positions;

    public Variations(String phrase, HashMap<String, Object> positions) {
        this.phrase = phrase;
        this.positions = positions;
    }
    
    public ArrayList<String> generate(){
        ArrayList<String> variations=null;
        return variations;
    }
    
}