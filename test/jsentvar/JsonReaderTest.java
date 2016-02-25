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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jsentvar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guillem LLuch Moll guillem72@gmail.com
 */
public class JsonReaderTest {
    
    public JsonReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of reader method, of class JsonReader.
     * @throws java.io.IOException
     */
    @Test
    public void testReader() throws IOException {
        System.out.println("JsonReader.reader");
        String inputFileName = "resources/test/text_doc0.json";
        JsonReader instance = new JsonReader();
        String expResult = FileUtils.readFileToString(new File("resources/test/doc0Result.txt"),"utf8").trim();
        HashMap<String, HashMap<Integer, Integer>> result0 = instance.reader(inputFileName);
        String result=result0.toString();
        System.out.println("Esperat:\n"+expResult);
        System.out.println("Obtingut:\n"+result);
        assertEquals(expResult, result);
        
    }
    
}
