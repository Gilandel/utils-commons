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
 * Check {@link QuadIso}
 *
 * @since Aug 2, 2016
 * @author Gilles
 *
 */
public class QuadIsoTest {

    /**
     * Test method for {@link QuadIso#getLeft()},{@link QuadIso#getMiddle()},
     * {@link QuadIso#getRight()}.
     */
    @Test
    public void testGet() {
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "fourth";

        QuadIso<String> quad = QuadIso.of(first, second, third, fourth);

        assertNotNull(quad);
        assertEquals(first, quad.getFirst());
        assertEquals(second, quad.getSecond());
        assertEquals(third, quad.getThird());
        assertEquals(fourth, quad.getFourth());
    }

    /**
     * Test method for {@link QuadIso#equals(Object)}.
     */
    @Test
    public void testEquals() {
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "fourth";

        QuadIso<String> quad = QuadIso.of(first, second, third, fourth);

        assertTrue(quad.equals(quad));
        assertTrue(quad.equals(QuadIso.of(first, second, third, fourth)));
        assertFalse(quad.equals(QuadIso.of(first, second, third, 2)));
        assertFalse(quad.equals(QuadIso.of(first, null, third, fourth)));
        assertFalse(quad.equals(QuadIso.of(null, second, third, fourth)));
        assertFalse(quad.equals(QuadIso.of(first, second, null, fourth)));
        assertFalse(quad.equals(QuadIso.of(first, null, null, fourth)));
        assertFalse(quad.equals(QuadIso.of(null, second, null, fourth)));
        assertFalse(quad.equals(QuadIso.of(null, null, third, fourth)));
        assertFalse(quad.equals(QuadIso.of(null, null, null, null)));
        QuadIso<String> quadNull = null;
        assertFalse(quad.equals(quadNull));
    }

    /**
     * Test method for {@link QuadIso#hashCode()}.
     */
    @Test
    public void testHashCode() {
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "fourth";

        QuadIso<String> quad = QuadIso.of(first, second, third, fourth);

        assertFalse(quad.hashCode() == 0);
        assertNotEquals(first.hashCode(), quad.hashCode());
        assertNotEquals(second.hashCode(), quad.hashCode());
        assertNotEquals(third.hashCode(), quad.hashCode());

        // {@link Arrays#hashCode}
        assertEquals(923_521, QuadIso.of(null, null, null, null).hashCode());
    }

    /**
     * Test method for {@link QuadIso#compareTo(QuadIso)}.
     */
    @Test
    public void testCompareTo() {
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "fourth";

        QuadIso<String> quad = QuadIso.of(first, second, third, fourth);

        assertEquals(0, quad.compareTo(quad));
        assertEquals(0, quad.compareTo(QuadIso.of(first, second, third, fourth)));
        assertEquals(-1, quad.compareTo(QuadIso.of(first, second, third, "fourti")));
        assertEquals(1, quad.compareTo(QuadIso.of(first, second, third, "fourtg")));
        assertEquals(1, quad.compareTo(QuadIso.of(first, null, third, fourth)));
        assertEquals(1, quad.compareTo(QuadIso.of(first, second, null, fourth)));
        assertEquals(1, quad.compareTo(QuadIso.of(null, second, third, fourth)));
        assertEquals(1, quad.compareTo(QuadIso.of(first, null, null, fourth)));
        assertEquals(1, quad.compareTo(QuadIso.of(null, second, null, fourth)));
        assertEquals(1, quad.compareTo(QuadIso.of(null, null, third, fourth)));
        assertEquals(1, quad.compareTo(QuadIso.of(null, null, null, null)));
        assertEquals(Integer.MAX_VALUE, quad.compareTo(null));
    }

    /**
     * Test method for {@link QuadIso#toString()}.
     */
    @Test
    public void testToString() {
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "fourth";

        QuadIso<String> quad = QuadIso.of(first, second, third, fourth);

        assertEquals("(first,second,third,fourth)", quad.toString());
    }

    /**
     * Test method for {@link QuadIso#toString(java.lang.String)}.
     */
    @Test
    public void testToStringString() {
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "fourth";

        QuadIso<String> quad = QuadIso.of(first, second, third, fourth);

        assertEquals("first, second, third, fourth", quad.toString("%s, %s, %s, %s"));
    }

    /**
     * Test method for
     * {@link QuadIso#ofMutable(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testOfMutable() {
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "fourth";
        String value = "value";

        MutableQuadIso<String> quad = QuadIso.ofMutable(first, second, third, fourth);

        assertEquals(first, quad.getFirst());
        quad.setFirst(value);
        assertEquals(value, quad.getFirst());

        assertEquals(second, quad.getSecond());
        quad.setSecond(value);
        assertEquals(value, quad.getSecond());

        assertEquals(third, quad.getThird());
        quad.setThird(value);
        assertEquals(value, quad.getThird());

        assertEquals(fourth, quad.getFourth());
        quad.setFourth(value);
        assertEquals(value, quad.getThird());
    }
}
