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

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Check {@link Nona}
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 */
public class NonaTest {

    /**
     * Test method for {@link Nona#getFirst()}, {@link Nona#getSecond()},
     * {@link Nona#getThird()}, {@link Nona#getFourth()},
     * {@link Nona#getFifth()}, {@link Nona#getSixth()},
     * {@link Nona#getSeventh()}, {@link Nona#getEighth()} and
     * {@link Nona#getNinth()}.
     */
    @Test
    public void testGet() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 7;
        Integer seventh = 4;
        Integer eighth = 5;
        Integer ninth = 6;

        Nona<String, String, String, Integer, Integer, Integer, Integer, Integer, Integer> obj = Nona.of(first, second, third, fourth,
                fifth, sixth, seventh, eighth, ninth);

        assertNotNull(obj);
        assertEquals(first, obj.getFirst());
        assertEquals(second, obj.getSecond());
        assertEquals(third, obj.getThird());
        assertEquals(fourth, obj.getFourth());
        assertEquals(fifth, obj.getFifth());
        assertEquals(sixth, obj.getSixth());
        assertEquals(seventh, obj.getSeventh());
        assertEquals(eighth, obj.getEighth());
        assertEquals(ninth, obj.getNinth());
    }

    /**
     * Test method for {@link Nona#equals(Object)}.
     */
    @Test
    public void testEquals() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;
        Integer seventh = 4;
        Integer eighth = 5;
        Integer ninth = 6;

        Nona<String, String, String, Integer, Integer, Integer, Integer, Integer, Integer> obj = Nona.of(first, second, third, fourth,
                fifth, sixth, seventh, eighth, ninth);

        assertTrue(obj.equals(obj));
        assertTrue(obj.equals(Nona.of(first, second, third, 2, fifth, sixth, seventh, eighth, ninth)));
        assertFalse(obj.equals(Nona.of(first, second, third, 3, fifth, sixth, seventh, eighth, ninth)));
        assertFalse(obj.equals(Nona.of(first, null, third, 2, fifth, sixth, seventh, eighth, ninth)));
        assertFalse(obj.equals(Nona.of(null, second, third, 2, fifth, sixth, seventh, eighth, ninth)));
        assertFalse(obj.equals(Nona.of(first, second, null, 2, fifth, sixth, seventh, eighth, ninth)));
        assertFalse(obj.equals(Nona.of(first, null, null, 2, fifth, sixth, seventh, eighth, ninth)));
        assertFalse(obj.equals(Nona.of(null, second, null, 2, fifth, sixth, seventh, eighth, ninth)));
        assertFalse(obj.equals(Nona.of(null, null, third, 2, fifth, sixth, seventh, eighth, ninth)));
        assertFalse(obj.equals(Nona.of(null, null, null, null, fifth, sixth, seventh, eighth, ninth)));

        Nona<String, String, String, Integer, Integer, Integer, Integer, Integer, Integer> objNull = null;
        assertFalse(obj.equals(objNull));
    }

    /**
     * Test method for {@link Nona#hashCode()}.
     */
    @Test
    public void testHashCode() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;
        Integer seventh = 4;
        Integer eighth = 5;
        Integer ninth = 6;

        Nona<String, String, String, Integer, Integer, Integer, Integer, Integer, Integer> obj = Nona.of(first, second, third, fourth,
                fifth, sixth, seventh, eighth, ninth);

        assertFalse(obj.hashCode() == 0);
        assertNotEquals(first.hashCode(), obj.hashCode());
        assertNotEquals(second.hashCode(), obj.hashCode());
        assertNotEquals(third.hashCode(), obj.hashCode());
        assertNotEquals(fourth.hashCode(), obj.hashCode());
        assertNotEquals(fifth.hashCode(), obj.hashCode());
        assertNotEquals(sixth.hashCode(), obj.hashCode());
        assertNotEquals(seventh.hashCode(), obj.hashCode());
        assertNotEquals(eighth.hashCode(), obj.hashCode());
        assertNotEquals(ninth.hashCode(), obj.hashCode());

        // {@link Arrays#hashCode}
        assertEquals(Arrays.hashCode(new Object[9]), Nona.of(null, null, null, null, null, null, null, null, null).hashCode());
    }

    /**
     * Test method for {@link Nona#compareTo(Nona)}.
     */
    @Test
    public void testCompareTo() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;
        Integer seventh = 4;
        Integer eighth = 5;
        Integer ninth = 6;

        Nona<String, String, String, Integer, Integer, Integer, Integer, Integer, Integer> obj = Nona.of(first, second, third, fourth,
                fifth, sixth, seventh, eighth, ninth);

        assertEquals(0, obj.compareTo(obj));
        assertEquals(0, obj.compareTo(Nona.of(first, second, third, 2, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(-1, obj.compareTo(Nona.of(first, second, third, 3, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(1, obj.compareTo(Nona.of(first, second, third, 1, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(1, obj.compareTo(Nona.of(first, null, third, 1, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(1, obj.compareTo(Nona.of(first, second, null, 1, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(1, obj.compareTo(Nona.of(null, second, third, 1, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(1, obj.compareTo(Nona.of(first, null, null, 1, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(1, obj.compareTo(Nona.of(null, second, null, 1, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(1, obj.compareTo(Nona.of(null, null, third, 1, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(1, obj.compareTo(Nona.of(null, null, null, null, fifth, sixth, seventh, eighth, ninth)));
        assertEquals(Integer.MAX_VALUE, obj.compareTo(null));
    }

    /**
     * Test method for {@link Nona#toString()}.
     */
    @Test
    public void testToString() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;
        Integer seventh = 4;
        Integer eighth = 5;
        Integer ninth = 6;

        Nona<String, String, String, Integer, Integer, Integer, Integer, Integer, Integer> obj = Nona.of(first, second, third, fourth,
                fifth, sixth, seventh, eighth, ninth);

        assertEquals("(first,second,third,2,3,3,4,5,6)", obj.toString());
    }

    /**
     * Test method for {@link Nona#toString(java.lang.String)}.
     */
    @Test
    public void testToStringString() {
        String first = "first";
        String second = "second";
        String third = "third";
        Integer fourth = 2;
        Integer fifth = 3;
        Integer sixth = 3;
        Integer seventh = 4;
        Integer eighth = 5;
        Integer ninth = 6;

        Nona<String, String, String, Integer, Integer, Integer, Integer, Integer, Integer> obj = Nona.of(first, second, third, fourth,
                fifth, sixth, seventh, eighth, ninth);

        assertEquals("first, second, third, 2, 3, 3, 4, 5, 6", obj.toString("%s, %s, %s, %s, %s, %s, %s, %s, %s"));
    }

    /**
     * Test method for
     * {@link Nona#ofMutable(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testOfMutable() {
        String first = "first";
        String second = "second";
        String third = "third";
        String value = "value";
        Integer fifth = 3;
        Integer sixth = 3;
        Integer seventh = 4;
        Integer eighth = 5;
        Integer ninth = 6;

        MutableNona<String, String, String, Integer, Integer, Integer, Integer, Integer, Integer> obj = Nona.ofMutable(first, second, third,
                2, fifth, sixth, seventh, eighth, ninth);

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

        assertEquals(4, obj.getSeventh().intValue());
        obj.setSeventh(5);
        assertEquals(5, obj.getSeventh().intValue());

        assertEquals(5, obj.getEighth().intValue());
        obj.setEighth(6);
        assertEquals(6, obj.getEighth().intValue());

        assertEquals(6, obj.getNinth().intValue());
        obj.setNinth(4);
        assertEquals(4, obj.getNinth().intValue());
    }
}
