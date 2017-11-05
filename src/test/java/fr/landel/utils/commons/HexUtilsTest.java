/*
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
package fr.landel.utils.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Check hexadecimal utils
 *
 * @since Dec 11, 2015
 * @author Gilles Landel
 *
 */
public class HexUtilsTest extends AbstractTest {

    /**
     * Test constructor for {@link HexUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(HexUtils.class));
    }

    /**
     * Test method for {@link HexUtils#intToByte(int)} .
     */
    @Test
    public void testIntToByte() {
        final int expected = 40;
        final int input = 32;

        assertEquals((byte) expected, HexUtils.intToByte(input));
    }

    /**
     * Test method for {@link HexUtils#intToBytes(int)} .
     */
    @Test
    public void testIntToBytes() {
        final byte[] expected = {-128, 18, -20, 74};
        final int input = 1256985216;

        assertTrue(Arrays.equals(expected, HexUtils.intToBytes(input)));
    }

    /**
     * Test method for {@link HexUtils#byteArrayToHexString(byte[])} .
     */
    @Test
    public void testByteArrayToHexString() {
        byte[] bytes = {'A', 'B', 'C'};

        assertEquals("414243", HexUtils.byteArrayToHexString(bytes));
    }
}
