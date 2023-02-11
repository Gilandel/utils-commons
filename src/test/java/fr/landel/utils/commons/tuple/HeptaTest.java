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
 * Check {@link Hepta}
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 */
public class HeptaTest {

    /**
     * Test method for {@link Hepta#getFirst()}, {@link Hepta#getSecond()},
     * {@link Hepta#getThird()}, {@link Hepta#getFourth()},
     * {@link Hepta#getFifth()}, {@link Hepta#getSixth()} and
     * {@link Hepta#getSeventh()}.
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

        Hepta<String, String, String, Integer, Integer, Integer, Integer> obj = Hepta.of(first, second, third, fourth, fifth, sixth,
                seventh);

        assertNotNull(obj);
        assertEquals(first, obj.getFirst());
        assertEquals(second, obj.getSecond());
        assertEquals(third, obj.getThird());
        assertEquals(fourth, obj.getFourth());
        assertEquals(fifth, obj.getFifth());
        assertEquals(sixth, obj.getSixth());
        assertEquals(seventh, obj.getSeventh());
    }

    /**
     * Test method for {@link Hepta#equals(Object)}.
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

        Hepta<String, String, String, Integer, Integer, Integer, Integer> obj = Hepta.of(first, second, third, fourth, fifth, sixth,
                seventh);

        assertTrue(obj.equals(obj));
        assertTrue(obj.equals(Hepta.of(first, second, third, 2, fifth, sixth, seventh)));
        assertFalse(obj.equals(Hepta.of(first, second, third, 3, fifth, sixth, seventh)));
        assertFalse(obj.equals(Hepta.of(first, null, third, 2, fifth, sixth, seventh)));
        assertFalse(obj.equals(Hepta.of(null, second, third, 2, fifth, sixth, seventh)));
        assertFalse(obj.equals(Hepta.of(first, second, null, 2, fifth, sixth, seventh)));
        assertFalse(obj.equals(Hepta.of(first, null, null, 2, fifth, sixth, seventh)));
        assertFalse(obj.equals(Hepta.of(null, second, null, 2, fifth, sixth, seventh)));
        assertFalse(obj.equals(Hepta.of(null, null, third, 2, fifth, sixth, seventh)));
        assertFalse(obj.equals(Hepta.of(null, null, null, null, fifth, sixth, seventh)));

        Hepta<String, String, String, Integer, Integer, Integer, Integer> objNull = null;
        assertFalse(obj.equals(objNull));
    }

    /**
     * Test method for {@link Hepta#hashCode()}.
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

        Hepta<String, String, String, Integer, Integer, Integer, Integer> obj = Hepta.of(first, second, third, fourth, fifth, sixth,
                seventh);

        assertFalse(obj.hashCode() == 0);
        assertNotEquals(first.hashCode(), obj.hashCode());
        assertNotEquals(second.hashCode(), obj.hashCode());
        assertNotEquals(third.hashCode(), obj.hashCode());
        assertNotEquals(fourth.hashCode(), obj.hashCode());
        assertNotEquals(fifth.hashCode(), obj.hashCode());
        assertNotEquals(sixth.hashCode(), obj.hashCode());
        assertNotEquals(seventh.hashCode(), obj.hashCode());

        // {@link Arrays#hashCode}
        assertEquals(Arrays.hashCode(new Object[7]), Hepta.of(null, null, null, null, null, null, null).hashCode());
    }

    /**
     * Test method for {@link Hepta#compareTo(Hepta)}.
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

        Hepta<String, String, String, Integer, Integer, Integer, Integer> obj = Hepta.of(first, second, third, fourth, fifth, sixth,
                seventh);

        assertEquals(0, obj.compareTo(obj));
        assertEquals(0, obj.compareTo(Hepta.of(first, second, third, 2, fifth, sixth, seventh)));
        assertEquals(-1, obj.compareTo(Hepta.of(first, second, third, 3, fifth, sixth, seventh)));
        assertEquals(1, obj.compareTo(Hepta.of(first, second, third, 1, fifth, sixth, seventh)));
        assertEquals(1, obj.compareTo(Hepta.of(first, null, third, 1, fifth, sixth, seventh)));
        assertEquals(1, obj.compareTo(Hepta.of(first, second, null, 1, fifth, sixth, seventh)));
        assertEquals(1, obj.compareTo(Hepta.of(null, second, third, 1, fifth, sixth, seventh)));
        assertEquals(1, obj.compareTo(Hepta.of(first, null, null, 1, fifth, sixth, seventh)));
        assertEquals(1, obj.compareTo(Hepta.of(null, second, null, 1, fifth, sixth, seventh)));
        assertEquals(1, obj.compareTo(Hepta.of(null, null, third, 1, fifth, sixth, seventh)));
        assertEquals(1, obj.compareTo(Hepta.of(null, null, null, null, fifth, sixth, seventh)));
        assertEquals(Integer.MAX_VALUE, obj.compareTo(null));
    }

    /**
     * Test method for {@link Hepta#toString()}.
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

        Hepta<String, String, String, Integer, Integer, Integer, Integer> obj = Hepta.of(first, second, third, fourth, fifth, sixth,
                seventh);

        assertEquals("(first,second,third,2,3,3,4)", obj.toString());
    }

    /**
     * Test method for {@link Hepta#toString(java.lang.String)}.
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

        Hepta<String, String, String, Integer, Integer, Integer, Integer> obj = Hepta.of(first, second, third, fourth, fifth, sixth,
                seventh);

        assertEquals("first, second, third, 2, 3, 3, 4", obj.toString("%s, %s, %s, %s, %s, %s, %s"));
    }

    /**
     * Test method for
     * {@link Hepta#ofMutable(java.lang.Object, java.lang.Object)}.
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

        MutableHepta<String, String, String, Integer, Integer, Integer, Integer> obj = Hepta.ofMutable(first, second, third, 2, fifth,
                sixth, seventh);

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
    }
}
