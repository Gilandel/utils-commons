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
package fr.landel.utils.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Check {@link ArrayUtils}
 *
 * @since Aug 3, 2016
 * @author Gilles
 *
 */
public class ArrayUtilsTest extends AbstractTest {

    /**
     * Test constructor for {@link ArrayUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(ArrayUtils.class));
    }

    /**
     * Test method for {@link ArrayUtils#containsAll(T[], U[])}.
     */
    @Test
    public void testContainsAll() {
        Byte[] bytes1 = new Byte[] {'2', '6', null, 'd'};
        Byte[] bytes2 = new Byte[] {'6', null};
        Byte[] bytes3 = new Byte[] {'1', '6', null};
        Byte[] bytes4 = new Byte[] {'1', '3'};

        assertTrue(ArrayUtils.containsAll(bytes1, bytes2));
        assertFalse(ArrayUtils.containsAll(bytes1, bytes3));
        assertFalse(ArrayUtils.containsAll(bytes1, bytes4));
        assertFalse(ArrayUtils.containsAll(new Byte[0], bytes2));
        assertFalse(ArrayUtils.containsAll(bytes1, new Character[] {'1'}));
        assertFalse(ArrayUtils.containsAll(bytes1, new Character[] {'1'}, false));

        assertException(() -> ArrayUtils.containsAll(null, bytes2), NullPointerException.class);
        assertException(() -> ArrayUtils.containsAll(bytes1, null), NullPointerException.class);
    }

    /**
     * Test method for {@link ArrayUtils#containsAny(T[], U[])}.
     */
    @Test
    public void testContainsAny() {
        Byte[] bytes1 = new Byte[] {'2', '6', null, 'd'};
        Byte[] bytes2 = new Byte[] {'6', null};
        Byte[] bytes3 = new Byte[] {'1', '6', null};
        Byte[] bytes4 = new Byte[] {'1', '3'};

        assertTrue(ArrayUtils.containsAny(bytes1, bytes2));
        assertTrue(ArrayUtils.containsAny(bytes1, bytes3));
        assertFalse(ArrayUtils.containsAny(bytes1, bytes4));
        assertFalse(ArrayUtils.containsAny(new Byte[0], bytes2));
        assertFalse(ArrayUtils.containsAny(bytes1, new Character[] {'1'}));
        assertFalse(ArrayUtils.containsAny(bytes1, new Character[] {'1'}, false));

        assertException(() -> ArrayUtils.containsAny(null, bytes2), NullPointerException.class);
        assertException(() -> ArrayUtils.containsAny(bytes1, null), NullPointerException.class);
    }

    /**
     * Test method for {@link ArrayUtils#replace(T[], T, T)}.
     */
    @Test
    public void testReplace() {
        Character[] chars1 = new Character[] {'2', '6', null, 'd'};

        ArrayUtils.replace(chars1, '6', '7');

        assertTrue(Arrays.equals(new Character[] {'2', '7', null, 'd'}, chars1));

        assertException(() -> ArrayUtils.replace(null, '6', '7'), NullPointerException.class);
    }

    /**
     * Test method for {@link ArrayUtils#replace(T[], T[], T[])}.
     */
    @Test
    public void testReplaceArray() {
        Character[] chars1 = new Character[] {'2', '6', null, 'd'};
        Character[] charsSearched = new Character[] {'6', null};
        Character[] charsReplacement = new Character[] {'8', '6'};

        ArrayUtils.replace(chars1, charsSearched, charsReplacement);

        assertTrue(Arrays.equals(new Character[] {'2', '8', '6', 'd'}, chars1));

        assertException(() -> ArrayUtils.replace(null, charsSearched, charsReplacement), NullPointerException.class);
        assertException(() -> ArrayUtils.replace(chars1, null, charsReplacement), NullPointerException.class);
        assertException(() -> ArrayUtils.replace(chars1, charsSearched, null), NullPointerException.class);

        assertException(() -> ArrayUtils.replace(chars1, charsSearched, chars1), IllegalArgumentException.class,
                "Searched values and replacement values arrays must have the same length");
    }

    /**
     * Test method for {@link ArrayUtils#concat(T[], T...)}.
     */
    @Test
    public void testConcat() {
        Character[] chars1 = new Character[] {'2', '6', null, 'd'};
        Character[] chars2 = new Character[] {'6', null};
        Character[] charsExpected = new Character[] {'2', '6', null, 'd', '6', null};

        assertTrue(Arrays.equals(charsExpected, ArrayUtils.concat(chars1, chars2)));

        assertNotEquals(chars1, ArrayUtils.concat(chars1, (Character[]) null));
        assertTrue(Arrays.equals(chars1, ArrayUtils.concat(chars1, (Character[]) null)));

        assertNotEquals(chars2, ArrayUtils.concat((Character[]) null, chars2));
        assertTrue(Arrays.equals(chars2, ArrayUtils.concat((Character[]) null, chars2)));
    }

    /**
     * Test method for {@link ArrayUtils#concat(T[], T[], T...)}.
     */
    @Test
    public void testConcatOutput() {
        Character[] chars1 = new Character[] {'2', '6', null, 'd'};
        Character[] chars2 = new Character[] {'6', null};
        Character[] charsExpected = new Character[] {'2', '6', null, 'd', '6', null};

        assertTrue(Arrays.equals(charsExpected, ArrayUtils.concat(new Character[6], chars1, chars2)));

        assertTrue(Arrays.equals(chars1, ArrayUtils.concat(new Character[4], chars1, (Character[]) null)));
        assertTrue(Arrays.equals(chars2, ArrayUtils.concat(new Character[2], (Character[]) null, chars2)));

        assertTrue(Arrays.equals(new Character[] {'2', '6', null, 'd', null, null},
                ArrayUtils.concat(new Character[6], chars1, (Character[]) null)));

        assertException(() -> ArrayUtils.concat(null, chars1, chars2), NullPointerException.class, "The output array cannot be null");
        assertException(() -> ArrayUtils.concat(new Character[2], chars1, chars2), IllegalArgumentException.class,
                "The output array cannot be smaller than array1 plus array2 length");
    }

    /**
     * Test method for {@link ArrayUtils#count(T[], U[])}.
     */
    @Test
    public void testCountArray() {
        Byte[] bytes1 = new Byte[] {'2', '6', null, 'd'};
        Byte[] bytes2 = new Byte[] {'6', null};
        Byte[] bytes3 = new Byte[] {'1', '6', null};
        Byte[] bytes4 = new Byte[] {'1', '3'};

        assertEquals(2, ArrayUtils.count(bytes1, bytes2));
        assertEquals(2, ArrayUtils.count(bytes1, bytes3));
        assertEquals(0, ArrayUtils.count(bytes1, bytes4));

        assertEquals(0, ArrayUtils.count(new Byte[0], bytes2));

        assertEquals(0, ArrayUtils.count(bytes1, new Character[] {'1'}));

        assertException(() -> ArrayUtils.count(bytes1, null), NullPointerException.class);
        assertException(() -> ArrayUtils.count(null, bytes1), NullPointerException.class);
    }

    /**
     * Test method for {@link ArrayUtils#count(T[], U)}.
     */
    @Test
    public void testCountObject() {
        Character[] characters = new Character[] {'2', '6', null, '6'};

        assertEquals(2, ArrayUtils.count(characters, '6'));
        assertEquals(2, ArrayUtils.count(characters, '6', false));
        assertEquals(0, ArrayUtils.count(characters, (byte) '6'));
        assertEquals(1, ArrayUtils.count(characters, (Character) null));
        assertEquals(0, ArrayUtils.count(characters, '7'));

        assertEquals(0, ArrayUtils.count(new Character[0], '1'));

        assertException(() -> ArrayUtils.count(null, '1'), NullPointerException.class);
    }
}
