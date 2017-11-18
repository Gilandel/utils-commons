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
 * Check {@link Penta}
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 */
public class PentaTest {

    /**
     * Test method for {@link Penta#getFirst()}, {@link Penta#getSecond()},
     * {@link Penta#getThird()}, {@link Penta#getFourth()} and
     * {@link Penta#getFifth()}.
     */
    @Test
    public void testGet() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;

        Penta<String, String, String, Integer, Integer> obj = Penta.of(first, second, third, fourth, fifth);

        assertNotNull(obj);
        assertEquals(first, obj.getFirst());
        assertEquals(second, obj.getSecond());
        assertEquals(third, obj.getThird());
        assertEquals(fourth, obj.getFourth());
        assertEquals(fifth, obj.getFifth());
    }

    /**
     * Test method for {@link Penta#equals(Object)}.
     */
    @Test
    public void testEquals() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;

        Penta<String, String, String, Integer, Integer> obj = Penta.of(first, second, third, fourth, fifth);

        assertTrue(obj.equals(obj));
        assertTrue(obj.equals(Penta.of(first, second, third, 2, fifth)));
        assertFalse(obj.equals(Penta.of(first, second, third, 3, fifth)));
        assertFalse(obj.equals(Penta.of(first, null, third, 2, fifth)));
        assertFalse(obj.equals(Penta.of(null, second, third, 2, fifth)));
        assertFalse(obj.equals(Penta.of(first, second, null, 2, fifth)));
        assertFalse(obj.equals(Penta.of(first, null, null, 2, fifth)));
        assertFalse(obj.equals(Penta.of(null, second, null, 2, fifth)));
        assertFalse(obj.equals(Penta.of(null, null, third, 2, fifth)));
        assertFalse(obj.equals(Penta.of(null, null, null, null, fifth)));

        Penta<String, String, String, Integer, Integer> objNull = null;
        assertFalse(obj.equals(objNull));
    }

    /**
     * Test method for {@link Penta#hashCode()}.
     */
    @Test
    public void testHashCode() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;

        Penta<String, String, String, Integer, Integer> obj = Penta.of(first, second, third, fourth, fifth);

        assertFalse(obj.hashCode() == 0);
        assertNotEquals(first.hashCode(), obj.hashCode());
        assertNotEquals(second.hashCode(), obj.hashCode());
        assertNotEquals(third.hashCode(), obj.hashCode());
        assertNotEquals(fourth.hashCode(), obj.hashCode());
        assertNotEquals(fifth.hashCode(), obj.hashCode());

        // {@link Arrays#hashCode}
        assertEquals(Arrays.hashCode(new Object[5]), Penta.of(null, null, null, null, null).hashCode());
    }

    /**
     * Test method for {@link Penta#compareTo(Penta)}.
     */
    @Test
    public void testCompareTo() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;

        Penta<String, String, String, Integer, Integer> obj = Penta.of(first, second, third, fourth, fifth);

        assertEquals(0, obj.compareTo(obj));
        assertEquals(0, obj.compareTo(Penta.of(first, second, third, 2, fifth)));
        assertEquals(-1, obj.compareTo(Penta.of(first, second, third, 3, fifth)));
        assertEquals(1, obj.compareTo(Penta.of(first, second, third, 1, fifth)));
        assertEquals(1, obj.compareTo(Penta.of(first, null, third, 1, fifth)));
        assertEquals(1, obj.compareTo(Penta.of(first, second, null, 1, fifth)));
        assertEquals(1, obj.compareTo(Penta.of(null, second, third, 1, fifth)));
        assertEquals(1, obj.compareTo(Penta.of(first, null, null, 1, fifth)));
        assertEquals(1, obj.compareTo(Penta.of(null, second, null, 1, fifth)));
        assertEquals(1, obj.compareTo(Penta.of(null, null, third, 1, fifth)));
        assertEquals(1, obj.compareTo(Penta.of(null, null, null, null, fifth)));
        assertEquals(Integer.MAX_VALUE, obj.compareTo(null));
    }

    /**
     * Test method for {@link Penta#toString()}.
     */
    @Test
    public void testToString() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;

        Penta<String, String, String, Integer, Integer> obj = Penta.of(first, second, third, fourth, fifth);

        assertEquals("(first,second,third,2,3)", obj.toString());
    }

    /**
     * Test method for {@link Penta#toString(java.lang.String)}.
     */
    @Test
    public void testToStringString() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;

        Penta<String, String, String, Integer, Integer> obj = Penta.of(first, second, third, fourth, fifth);

        assertEquals("first, second, third, 2, 3", obj.toString("%s, %s, %s, %s, %s"));
    }

    /**
     * Test method for
     * {@link Penta#ofMutable(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testOfMutable() {
        String first = "first";
        String second = "second";
        String third = "third";
        String value = "value";
        Integer fifth = 3;

        MutablePenta<String, String, String, Integer, Integer> obj = Penta.ofMutable(first, second, third, 2, fifth);

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
    }
}
