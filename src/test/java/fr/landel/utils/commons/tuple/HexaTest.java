/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilles Landel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package fr.landel.utils.commons.tuple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Check {@link Hexa}
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 */
public class HexaTest {

    /**
     * Test method for {@link Hexa#getFirst()}, {@link Hexa#getSecond()},
     * {@link Hexa#getThird()}, {@link Hexa#getFourth()},
     * {@link Hexa#getFifth()} and {@link Hexa#getSixth()}.
     */
    @Test
    public void testGet() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 7;

        Hexa<String, String, String, Integer, Integer, Integer> obj = Hexa.of(first, second, third, fourth, fifth, sixth);

        assertNotNull(obj);
        assertEquals(first, obj.getFirst());
        assertEquals(second, obj.getSecond());
        assertEquals(third, obj.getThird());
        assertEquals(fourth, obj.getFourth());
        assertEquals(fifth, obj.getFifth());
        assertEquals(sixth, obj.getSixth());
    }

    /**
     * Test method for {@link Hexa#equals(Object)}.
     */
    @Test
    public void testEquals() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;

        Hexa<String, String, String, Integer, Integer, Integer> obj = Hexa.of(first, second, third, fourth, fifth, sixth);

        assertTrue(obj.equals(obj));
        assertTrue(obj.equals(Hexa.of(first, second, third, 2, fifth, sixth)));
        assertFalse(obj.equals(Hexa.of(first, second, third, 3, fifth, sixth)));
        assertFalse(obj.equals(Hexa.of(first, null, third, 2, fifth, sixth)));
        assertFalse(obj.equals(Hexa.of(null, second, third, 2, fifth, sixth)));
        assertFalse(obj.equals(Hexa.of(first, second, null, 2, fifth, sixth)));
        assertFalse(obj.equals(Hexa.of(first, null, null, 2, fifth, sixth)));
        assertFalse(obj.equals(Hexa.of(null, second, null, 2, fifth, sixth)));
        assertFalse(obj.equals(Hexa.of(null, null, third, 2, fifth, sixth)));
        assertFalse(obj.equals(Hexa.of(null, null, null, null, fifth, sixth)));

        Hexa<String, String, String, Integer, Integer, Integer> objNull = null;
        assertFalse(obj.equals(objNull));
    }

    /**
     * Test method for {@link Hexa#hashCode()}.
     */
    @Test
    public void testHashCode() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;

        Hexa<String, String, String, Integer, Integer, Integer> obj = Hexa.of(first, second, third, fourth, fifth, sixth);

        assertFalse(obj.hashCode() == 0);
        assertNotEquals(first.hashCode(), obj.hashCode());
        assertNotEquals(second.hashCode(), obj.hashCode());
        assertNotEquals(third.hashCode(), obj.hashCode());
        assertNotEquals(fourth.hashCode(), obj.hashCode());
        assertNotEquals(fifth.hashCode(), obj.hashCode());
        assertNotEquals(sixth.hashCode(), obj.hashCode());

        // {@link Arrays#hashCode}
        assertEquals(Arrays.hashCode(new Object[6]), Hexa.of(null, null, null, null, null, null).hashCode());
    }

    /**
     * Test method for {@link Hexa#compareTo(Hexa)}.
     */
    @Test
    public void testCompareTo() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;

        Hexa<String, String, String, Integer, Integer, Integer> obj = Hexa.of(first, second, third, fourth, fifth, sixth);

        assertEquals(0, obj.compareTo(obj));
        assertEquals(0, obj.compareTo(Hexa.of(first, second, third, 2, fifth, sixth)));
        assertEquals(-1, obj.compareTo(Hexa.of(first, second, third, 3, fifth, sixth)));
        assertEquals(1, obj.compareTo(Hexa.of(first, second, third, 1, fifth, sixth)));
        assertEquals(1, obj.compareTo(Hexa.of(first, null, third, 1, fifth, sixth)));
        assertEquals(1, obj.compareTo(Hexa.of(first, second, null, 1, fifth, sixth)));
        assertEquals(1, obj.compareTo(Hexa.of(null, second, third, 1, fifth, sixth)));
        assertEquals(1, obj.compareTo(Hexa.of(first, null, null, 1, fifth, sixth)));
        assertEquals(1, obj.compareTo(Hexa.of(null, second, null, 1, fifth, sixth)));
        assertEquals(1, obj.compareTo(Hexa.of(null, null, third, 1, fifth, sixth)));
        assertEquals(1, obj.compareTo(Hexa.of(null, null, null, null, fifth, sixth)));
        assertEquals(Integer.MAX_VALUE, obj.compareTo(null));
    }

    /**
     * Test method for {@link Hexa#toString()}.
     */
    @Test
    public void testToString() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;

        Hexa<String, String, String, Integer, Integer, Integer> obj = Hexa.of(first, second, third, fourth, fifth, sixth);

        assertEquals("(first,second,third,2,3,3)", obj.toString());
    }

    /**
     * Test method for {@link Hexa#toString(java.lang.String)}.
     */
    @Test
    public void testToStringString() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;

        Hexa<String, String, String, Integer, Integer, Integer> obj = Hexa.of(first, second, third, fourth, fifth, sixth);

        assertEquals("first, second, third, 2, 3, 3", obj.toString("%s, %s, %s, %s, %s, %s"));
    }

    /**
     * Test method for
     * {@link Hexa#ofMutable(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testOfMutable() {
        String first = "first";
        String second = "second";
        String third = "third";
        String value = "value";
        Integer fifth = 3;
        Integer sixth = 3;

        MutableHexa<String, String, String, Integer, Integer, Integer> obj = Hexa.ofMutable(first, second, third, 2, fifth, sixth);

        assertEquals(first, obj.getFirst());
        obj.setFirst(value);
        assertEquals(value, obj.getFirst());

        assertEquals(second, obj.getSecond());
        obj.setSecond(value);
        assertEquals(value, obj.getSecond());

        assertEquals(third, obj.getThird());
        obj.setThird(value);
        assertEquals(value, obj.getThird());

        assertEquals(2, obj.getFourth().intValue());
        obj.setFourth(1);
        assertEquals(1, obj.getFourth().intValue());

        assertEquals(3, obj.getFifth().intValue());
        obj.setFifth(5);
        assertEquals(5, obj.getFifth().intValue());

        assertEquals(3, obj.getSixth().intValue());
        obj.setSixth(5);
        assertEquals(5, obj.getSixth().intValue());
    }
}
