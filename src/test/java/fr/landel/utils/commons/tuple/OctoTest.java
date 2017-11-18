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
 * Check {@link Octo}
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 */
public class OctoTest {

    /**
     * Test method for {@link Octo#getFirst()}, {@link Octo#getSecond()},
     * {@link Octo#getThird()}, {@link Octo#getFourth()},
     * {@link Octo#getFifth()}, {@link Octo#getSixth()},
     * {@link Octo#getSeventh()} and {@link Octo#getEighth()}.
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

        Octo<String, String, String, Integer, Integer, Integer, Integer, Integer> obj = Octo.of(first, second, third, fourth, fifth, sixth,
                seventh, eighth);

        assertNotNull(obj);
        assertEquals(first, obj.getFirst());
        assertEquals(second, obj.getSecond());
        assertEquals(third, obj.getThird());
        assertEquals(fourth, obj.getFourth());
        assertEquals(fifth, obj.getFifth());
        assertEquals(sixth, obj.getSixth());
        assertEquals(seventh, obj.getSeventh());
        assertEquals(eighth, obj.getEighth());
    }

    /**
     * Test method for {@link Octo#equals(Object)}.
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

        Octo<String, String, String, Integer, Integer, Integer, Integer, Integer> obj = Octo.of(first, second, third, fourth, fifth, sixth,
                seventh, eighth);

        assertTrue(obj.equals(obj));
        assertTrue(obj.equals(Octo.of(first, second, third, 2, fifth, sixth, seventh, eighth)));
        assertFalse(obj.equals(Octo.of(first, second, third, 3, fifth, sixth, seventh, eighth)));
        assertFalse(obj.equals(Octo.of(first, null, third, 2, fifth, sixth, seventh, eighth)));
        assertFalse(obj.equals(Octo.of(null, second, third, 2, fifth, sixth, seventh, eighth)));
        assertFalse(obj.equals(Octo.of(first, second, null, 2, fifth, sixth, seventh, eighth)));
        assertFalse(obj.equals(Octo.of(first, null, null, 2, fifth, sixth, seventh, eighth)));
        assertFalse(obj.equals(Octo.of(null, second, null, 2, fifth, sixth, seventh, eighth)));
        assertFalse(obj.equals(Octo.of(null, null, third, 2, fifth, sixth, seventh, eighth)));
        assertFalse(obj.equals(Octo.of(null, null, null, null, fifth, sixth, seventh, eighth)));

        Octo<String, String, String, Integer, Integer, Integer, Integer, Integer> objNull = null;
        assertFalse(obj.equals(objNull));
    }

    /**
     * Test method for {@link Octo#hashCode()}.
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

        Octo<String, String, String, Integer, Integer, Integer, Integer, Integer> obj = Octo.of(first, second, third, fourth, fifth, sixth,
                seventh, eighth);

        assertFalse(obj.hashCode() == 0);
        assertNotEquals(first.hashCode(), obj.hashCode());
        assertNotEquals(second.hashCode(), obj.hashCode());
        assertNotEquals(third.hashCode(), obj.hashCode());
        assertNotEquals(fourth.hashCode(), obj.hashCode());
        assertNotEquals(fifth.hashCode(), obj.hashCode());
        assertNotEquals(sixth.hashCode(), obj.hashCode());
        assertNotEquals(seventh.hashCode(), obj.hashCode());
        assertNotEquals(eighth.hashCode(), obj.hashCode());

        // {@link Arrays#hashCode}
        assertEquals(Arrays.hashCode(new Object[8]), Octo.of(null, null, null, null, null, null, null, null).hashCode());
    }

    /**
     * Test method for {@link Octo#compareTo(Octo)}.
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

        Octo<String, String, String, Integer, Integer, Integer, Integer, Integer> obj = Octo.of(first, second, third, fourth, fifth, sixth,
                seventh, eighth);

        assertEquals(0, obj.compareTo(obj));
        assertEquals(0, obj.compareTo(Octo.of(first, second, third, 2, fifth, sixth, seventh, eighth)));
        assertEquals(-1, obj.compareTo(Octo.of(first, second, third, 3, fifth, sixth, seventh, eighth)));
        assertEquals(1, obj.compareTo(Octo.of(first, second, third, 1, fifth, sixth, seventh, eighth)));
        assertEquals(1, obj.compareTo(Octo.of(first, null, third, 1, fifth, sixth, seventh, eighth)));
        assertEquals(1, obj.compareTo(Octo.of(first, second, null, 1, fifth, sixth, seventh, eighth)));
        assertEquals(1, obj.compareTo(Octo.of(null, second, third, 1, fifth, sixth, seventh, eighth)));
        assertEquals(1, obj.compareTo(Octo.of(first, null, null, 1, fifth, sixth, seventh, eighth)));
        assertEquals(1, obj.compareTo(Octo.of(null, second, null, 1, fifth, sixth, seventh, eighth)));
        assertEquals(1, obj.compareTo(Octo.of(null, null, third, 1, fifth, sixth, seventh, eighth)));
        assertEquals(1, obj.compareTo(Octo.of(null, null, null, null, fifth, sixth, seventh, eighth)));
        assertEquals(Integer.MAX_VALUE, obj.compareTo(null));
    }

    /**
     * Test method for {@link Octo#toString()}.
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

        Octo<String, String, String, Integer, Integer, Integer, Integer, Integer> obj = Octo.of(first, second, third, fourth, fifth, sixth,
                seventh, eighth);

        assertEquals("(first,second,third,2,3,3,4,5)", obj.toString());
    }

    /**
     * Test method for {@link Octo#toString(java.lang.String)}.
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

        Octo<String, String, String, Integer, Integer, Integer, Integer, Integer> obj = Octo.of(first, second, third, fourth, fifth, sixth,
                seventh, eighth);

        assertEquals("first, second, third, 2, 3, 3, 4, 5", obj.toString("%s, %s, %s, %s, %s, %s, %s, %s"));
    }

    /**
     * Test method for
     * {@link Octo#ofMutable(java.lang.Object, java.lang.Object)}.
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

        MutableOcto<String, String, String, Integer, Integer, Integer, Integer, Integer> obj = Octo.ofMutable(first, second, third, 2,
                fifth, sixth, seventh, eighth);

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
    }
}
