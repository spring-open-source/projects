package com.stackhunter.util.tostring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.stackhunter.example.people.Person;

public class StringGeneratorTest {

    @Test
    public void testBasics() {
        assertEquals("null", generateString(null, 1));
        assertEquals("", generateString("", 1));
        assertEquals("Abcd", generateString("Abcd", 1));
        assertEquals("A", generateString('A', 1));
        assertEquals("800", generateString(800, 1));
        assertEquals("true", generateString(true, 1));
        assertEquals("1.1", generateString(1.1, 1));
        generateString(new int[]{111, 222, 333}, 4, " = 111", " = 222", " = 333", "[I@");
        generateString(new Person().setName("Jim"), 3, "friends = null", "name = Jim", Person.class.getName() + "@");
    }

    
    @Test
    public void testCircularGraph() {
        Person fred = new Person().setName("Fred");
        Person john = new Person().setName("John");
        Person bob = new Person().setName("Bob");
        
        john.addFriends(fred, bob);
        bob.addFriends(john);

        generateString(john, 10, 
                "friends = null", 
                "name = Fred",
                "name = Bob",
                "friends[0] = ",
                "...",
                Person.class.getName() + "@", 
                ArrayList.class.getName() + "@");
    }

    @Test
    public void testLongStringProperty() {
        String longString = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
        String expectedString = "0123456789012345678901234567890123456789012345678901234567890123...";
        
        
        assertEquals(expectedString, generateString(longString, 1));
        generateString(new Person().setName(longString), 3, 
                "friends = null", 
                "name = " + expectedString, 
                Person.class.getName() + "@");
    }

    
    @Test
    public void testManyChildren() {
        List<Person> friends = new ArrayList<Person>();
        
        for (int i = 0; i < 100; i++) {
            friends.add(new Person().setName("Person-" + i));
        }
        
        generateString(friends, 64*3+1);
        generateString(new Person().setName("Kim").setFriends(friends), 64*3+3, 
                Person.class.getName() + "@");
    }

    
    @Test
    public void testLongGeneratedString() {
        String longString = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";

        final int COUNT = 128;
        
        List<Person> people = new ArrayList<Person>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            Person person = new Person().setName("person-"+i+"-"+longString);
            people.add(person);
            List<Person> children = new ArrayList<Person>(COUNT);
            person.setFriends(children);
            for (int j = 0; j < COUNT; j++) {
                children.add(new Person().setName("person-"+i+"-"+j+"-"+longString));
            }
        }
        
        String s = generateString(people, 281);
        
        assertTrue("expected string length max ~" + StringGenerator.MAX_STRING_LENGTH + ", but found " + s.length(),
                s.length() < (2 * StringGenerator.MAX_STRING_LENGTH));
    }

    
    //================================================================================

    private static int countLines(String string) {
        try {
            BufferedReader in = new BufferedReader(new StringReader(string));
            int count = 0;
            while (in.readLine() != null) {
                count ++;
            }
            if (count == 0) {
                count = 1;
            }
            return count;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    
    private static String generateString(Object object, int expectedLines, String ... expectedSubstrings) {
        String string = StringGenerator.generate(object);
        
        System.out.println(string);
        System.out.println(string.length());
        
        if (string == null) {
            assertEquals("lines", expectedLines, 1);
            if (expectedSubstrings != null && expectedSubstrings.length > 0) {
                fail("expected substrings, but result is null");
            }
            return string;
        }
        
        int actualLines = countLines(string);
        assertEquals("lines", expectedLines, actualLines);
        
        if (expectedSubstrings != null) {
            for (String substring : expectedSubstrings) {
                assertTrue("contains \"" + substring + "\"", string.contains(substring));
            }
        }
        
        return string;
    }


}
