/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2018 Gilles Landel
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Check {@link TripleIso}
 *
 * @since Aug 2, 2016
 * @author Gilles
 *
 */
public class TripleIsoTest {

    /**
     * Test method for
     * {@link TripleIso#getLeft()},{@link TripleIso#getMiddle()},
     * {@link TripleIso#getRight()}.
     */
    @Test
    public void testGet() {
        String first = "first";
        String second = "second";
        String third = "third";

        TripleIso<String> tri = TripleIso.of(first, second, third);

        assertNotNull(tri);
        assertEquals(first, tri.getLeft());
        assertEquals(second, tri.getMiddle());
        assertEquals(third, tri.getRight());
    }

    /**
     * Test method for {@link TripleIso#equals(Object)}.
     */
    @Test
    public void testEquals() {
        String first = "first";
        String second = "second";
        String third = "third";

        TripleIso<String> tri = TripleIso.of(first, second, third);

        assertTrue(tri.equals(tri));
        assertTrue(tri.equals(TripleIso.of(first, second, third)));
        assertFalse(tri.equals(TripleIso.of(first, null, third)));
        assertFalse(tri.equals(TripleIso.of(null, second, third)));
        assertFalse(tri.equals(TripleIso.of(first, second, null)));
        assertFalse(tri.equals(TripleIso.of(first, null, null)));
        assertFalse(tri.equals(TripleIso.of(null, second, null)));
        assertFalse(tri.equals(TripleIso.of(null, null, third)));
        assertFalse(tri.equals(TripleIso.of(null, null, null)));
        TripleIso<String> triNull = null;
        assertFalse(tri.equals(triNull));
    }

    /**
     * Test method for {@link TripleIso#hashCode()}.
     */
    @Test
    public void testHashCode() {
        String first = "first";
        String second = "second";
        String third = "third";

        TripleIso<String> tri = TripleIso.of(first, second, third);

        assertFalse(tri.hashCode() == 0);
        assertNotEquals(first.hashCode(), tri.hashCode());
        assertNotEquals(second.hashCode(), tri.hashCode());
        assertNotEquals(third.hashCode(), tri.hashCode());

        // {@link Arrays#hashCode}
        assertEquals(29_791, TripleIso.of(null, null, null).hashCode());
    }

    /**
     * Test method for {@link TripleIso#compareTo(TripleIso)}.
     */
    @Test
    public void testCompareTo() {
        String first = "first";
        String second = "second";
        String third = "third";

        TripleIso<String> tri = TripleIso.of(first, second, third);

        assertEquals(0, tri.compareTo(tri));
        assertEquals(0, tri.compareTo(TripleIso.of(first, second, third)));
        assertEquals(1, tri.compareTo(TripleIso.of(first, null, third)));
        assertEquals(1, tri.compareTo(TripleIso.of(first, second, null)));
        assertEquals(1, tri.compareTo(TripleIso.of(null, second, third)));
        assertEquals(1, tri.compareTo(TripleIso.of(first, null, null)));
        assertEquals(1, tri.compareTo(TripleIso.of(null, second, null)));
        assertEquals(1, tri.compareTo(TripleIso.of(null, null, third)));
        assertEquals(1, tri.compareTo(TripleIso.of(null, null, null)));
        assertEquals(Integer.MAX_VALUE, tri.compareTo(null));
    }

    /**
     * Test method for {@link TripleIso#toString()}.
     */
    @Test
    public void testToString() {
        String first = "first";
        String second = "second";
        String third = "third";

        TripleIso<String> tri = TripleIso.of(first, second, third);

        assertEquals("(first, second, third)", tri.toString());
    }

    /**
     * Test method for {@link TripleIso#toString(java.lang.String)}.
     */
    @Test
    public void testToStringString() {
        String first = "first";
        String second = "second";
        String third = "third";

        TripleIso<String> tri = TripleIso.of(first, second, third);

        assertEquals("first, second, third", tri.toString("%s, %s, %s"));
    }

    /**
     * Test method for
     * {@link TripleIso#ofMutable(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testOfMutable() {
        String first = "first";
        String second = "second";
        String third = "third";
        String value = "value";

        MutableTripleIso<String> tri = TripleIso.ofMutable(first, second, third);

        assertEquals(first, tri.getLeft());
        tri.setLeft(value);
        assertEquals(value, tri.getLeft());

        tri.setMiddle(value);
        assertEquals(value, tri.getMiddle());
        tri.setMiddle(value);
        assertEquals(value, tri.getMiddle());

        tri.setRight(value);
        assertEquals(value, tri.getRight());
        tri.setRight(value);
        assertEquals(value, tri.getRight());
    }
}
