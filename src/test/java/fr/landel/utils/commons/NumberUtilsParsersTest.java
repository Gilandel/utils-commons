/*
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Check utility class (number parsers).
 *
 * @since Nov 27, 2015
 * @author Gilles Landel
 *
 */
public class NumberUtilsParsersTest extends AbstractTest {

    /**
     * Test method for {@link NumberUtils#parseInt(java.lang.String)}.
     */
    @Test
    public void testParseIntString() {
        final int expected = 10;

        assertNull(NumberUtils.parseInt(null));
        assertNull(NumberUtils.parseInt(""));
        assertNull(NumberUtils.parseInt("   SS "));
        assertNull(NumberUtils.parseInt("  12 "));

        assertEquals(expected, (int) NumberUtils.parseInt("10"));
        assertEquals(-expected, (int) NumberUtils.parseInt("-10"));
        assertEquals(expected, (int) NumberUtils.parseInt("+10"));
        assertNull(NumberUtils.parseInt("10L"));
        assertNull(NumberUtils.parseInt("10.0"));
        assertNull(NumberUtils.parseInt("10.0D"));
        assertNull(NumberUtils.parseInt("10.0F"));

        assertNull(NumberUtils.parseInt(null, false));
        assertNull(NumberUtils.parseInt("", false));
        assertException(() -> NumberUtils.parseInt("   SS ", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseInt("  12 ", false), NumberFormatException.class);

        assertEquals(expected, (int) NumberUtils.parseInt("10", false));
        assertEquals(-expected, (int) NumberUtils.parseInt("-10", false));
        assertEquals(expected, (int) NumberUtils.parseInt("+10", false));
        assertException(() -> NumberUtils.parseInt("10L", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseInt("10.0", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseInt("10.0D", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseInt("10.0F", false), NumberFormatException.class);
    }

    /**
     * Test method for
     * {@link NumberUtils#parseInt(java.lang.String, java.lang.Integer)} .
     */
    @Test
    public void testParseIntStringInteger() {
        final int value = 19;
        final int expectedIfNullOrFalse = 19;
        final int expected = 10;

        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt(null, value));
        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt("", value));
        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt("   SS ", value));
        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt("  12 ", value));

        assertEquals(expected, (int) NumberUtils.parseInt("10", value));
        assertEquals(-expected, (int) NumberUtils.parseInt("-10", value));
        assertEquals(expected, (int) NumberUtils.parseInt("+10", value));
        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt("10L", value));
        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt("10.0", value));
        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt("10.0D", value));
        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt("10.0F", value));

        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt(null, value, false));
        assertEquals(expectedIfNullOrFalse, (int) NumberUtils.parseInt("", value, false));
        assertException(() -> NumberUtils.parseInt("   SS ", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseInt("  12 ", value, false), NumberFormatException.class);

        assertEquals(expected, (int) NumberUtils.parseInt("10", value, false));
        assertEquals(-expected, (int) NumberUtils.parseInt("-10", value, false));
        assertEquals(expected, (int) NumberUtils.parseInt("+10", value, false));
        assertException(() -> NumberUtils.parseInt("10L", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseInt("10.0", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseInt("10.0D", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseInt("10.0F", value, false), NumberFormatException.class);
    }

    /**
     * Test method for {@link NumberUtils#parseFloat(java.lang.String)}.
     */
    @Test
    public void testParseFloatString() {
        final float delta = 0.001f;
        final float expected = 10.0f;

        assertNull(NumberUtils.parseFloat(null));
        assertNull(NumberUtils.parseFloat(""));
        assertNull(NumberUtils.parseFloat("   SS "));

        assertEquals(expected, NumberUtils.parseFloat("  10 "), delta);
        assertEquals(expected, NumberUtils.parseFloat("10"), delta);
        assertEquals(-expected, NumberUtils.parseFloat("-10"), delta);
        assertNull(NumberUtils.parseFloat("10L"));
        assertEquals(expected, NumberUtils.parseFloat("10.0"), delta);
        assertEquals(expected, NumberUtils.parseFloat("10.0D"), delta);
        assertEquals(expected, NumberUtils.parseFloat("10.0F"), delta);

        assertNull(NumberUtils.parseFloat(null, false));
        assertNull(NumberUtils.parseFloat("", false));
        assertException(() -> NumberUtils.parseFloat("   SS ", false), NumberFormatException.class);

        assertEquals(expected, NumberUtils.parseFloat("  10 ", false), delta);
        assertEquals(expected, NumberUtils.parseFloat("10", false), delta);
        assertEquals(-expected, NumberUtils.parseFloat("-10", false), delta);
        assertException(() -> NumberUtils.parseFloat("10L", false), NumberFormatException.class);
        assertEquals(expected, NumberUtils.parseFloat("10.0", false), delta);
        assertEquals(expected, NumberUtils.parseFloat("10.0D", false), delta);
        assertEquals(expected, NumberUtils.parseFloat("10.0F", false), delta);
    }

    /**
     * Test method for
     * {@link NumberUtils#parseFloat(java.lang.String, java.lang.Float)} .
     */
    @Test
    public void testParseFloatStringFloat() {
        final float delta = 0.001f;
        final float value = 19.0f;
        final float expectedIfNullOrFalse = 19.0f;
        final float expected = 10.0f;

        assertEquals(expectedIfNullOrFalse, (float) NumberUtils.parseFloat(null, value), delta);
        assertEquals(expectedIfNullOrFalse, (float) NumberUtils.parseFloat("", value), delta);
        assertEquals(expectedIfNullOrFalse, (float) NumberUtils.parseFloat("   ND ", value), delta);

        assertEquals(expected, (float) NumberUtils.parseFloat("  10 ", value), delta);
        assertEquals(expected, (float) NumberUtils.parseFloat("10", value), delta);
        assertEquals(-expected, (float) NumberUtils.parseFloat("-10", value), delta);
        assertEquals(expectedIfNullOrFalse, (float) NumberUtils.parseFloat("10L", value), delta);
        assertEquals(expected, (float) NumberUtils.parseFloat("10.0", value), delta);
        assertEquals(expected, (float) NumberUtils.parseFloat("+10.0", value), delta);
        assertEquals(expected, (float) NumberUtils.parseFloat("10.0D", value), delta);
        assertEquals(expected, (float) NumberUtils.parseFloat("10.0F", value), delta);

        assertEquals(Float.MAX_VALUE, (float) NumberUtils.parseFloat(String.valueOf(Float.MAX_VALUE), value), delta);
        assertEquals(Float.MIN_VALUE, (float) NumberUtils.parseFloat(String.valueOf(Float.MIN_VALUE), value), delta);
        assertEquals(Float.POSITIVE_INFINITY, (float) NumberUtils.parseFloat(String.valueOf(Double.MAX_VALUE), value), delta);
        assertEquals(Float.NEGATIVE_INFINITY, (float) NumberUtils.parseFloat(String.valueOf(-Double.MAX_VALUE), value), delta);
        assertEquals(0.0f, (float) NumberUtils.parseFloat(String.valueOf(Double.MIN_VALUE), value), delta);
        assertEquals(Float.POSITIVE_INFINITY, (float) NumberUtils.parseFloat(String.valueOf(Float.POSITIVE_INFINITY), value), delta);
        assertEquals(Float.NEGATIVE_INFINITY, (float) NumberUtils.parseFloat(String.valueOf(Float.NEGATIVE_INFINITY), value), delta);

        assertEquals(expectedIfNullOrFalse, (float) NumberUtils.parseFloat(null, value, false), delta);
        assertEquals(expectedIfNullOrFalse, (float) NumberUtils.parseFloat("", value, false), delta);
        assertException(() -> NumberUtils.parseFloat("   ND ", value, false), NumberFormatException.class);

        assertEquals(expected, (float) NumberUtils.parseFloat("  10 ", value, false), delta);
        assertEquals(expected, (float) NumberUtils.parseFloat("10", value, false), delta);
        assertEquals(-expected, (float) NumberUtils.parseFloat("-10", value, false), delta);
        assertException(() -> NumberUtils.parseFloat("10L", value, false), NumberFormatException.class);
        assertEquals(expected, (float) NumberUtils.parseFloat("10.0", value, false), delta);
        assertEquals(expected, (float) NumberUtils.parseFloat("+10.0", value, false), delta);
        assertEquals(expected, (float) NumberUtils.parseFloat("10.0D", value, false), delta);
        assertEquals(expected, (float) NumberUtils.parseFloat("10.0F", value, false), delta);

        assertEquals(Float.MAX_VALUE, (float) NumberUtils.parseFloat(String.valueOf(Float.MAX_VALUE), value, false), delta);
        assertEquals(Float.MIN_VALUE, (float) NumberUtils.parseFloat(String.valueOf(Float.MIN_VALUE), value, false), delta);
        assertEquals(Float.POSITIVE_INFINITY, (float) NumberUtils.parseFloat(String.valueOf(Double.MAX_VALUE), value, false), delta);
        assertEquals(Float.NEGATIVE_INFINITY, (float) NumberUtils.parseFloat(String.valueOf(-Double.MAX_VALUE), value, false), delta);
        assertEquals(0.0f, (float) NumberUtils.parseFloat(String.valueOf(Double.MIN_VALUE), value, false), delta);
        assertEquals(Float.POSITIVE_INFINITY, (float) NumberUtils.parseFloat(String.valueOf(Float.POSITIVE_INFINITY), value, false), delta);
        assertEquals(Float.NEGATIVE_INFINITY, (float) NumberUtils.parseFloat(String.valueOf(Float.NEGATIVE_INFINITY), value, false), delta);
    }

    /**
     * Test method for {@link NumberUtils#parseLong(java.lang.String)}.
     */
    @Test
    public void testParseLongString() {
        final long expected = 10L;

        assertNull(NumberUtils.parseLong(null));
        assertNull(NumberUtils.parseLong(""));
        assertNull(NumberUtils.parseLong("   SS "));
        assertNull(NumberUtils.parseLong("  12 "));

        assertEquals(expected, (long) NumberUtils.parseLong("10"));
        assertEquals(-expected, (long) NumberUtils.parseLong("-10"));
        assertNull(NumberUtils.parseLong("10L"));
        assertNull(NumberUtils.parseLong("10.0"));
        assertNull(NumberUtils.parseLong("10.0D"));
        assertNull(NumberUtils.parseLong("10.0F"));

        assertNull(NumberUtils.parseLong(null, false));
        assertNull(NumberUtils.parseLong("", false));
        assertException(() -> NumberUtils.parseLong("   SS ", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseLong("  12 ", false), NumberFormatException.class);

        assertEquals(expected, (long) NumberUtils.parseLong("10", false));
        assertEquals(-expected, (long) NumberUtils.parseLong("-10", false));
        assertException(() -> NumberUtils.parseLong("10L", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseLong("10.0", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseLong("10.0D", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseLong("10.0F", false), NumberFormatException.class);
    }

    /**
     * Test method for
     * {@link NumberUtils#parseLong(java.lang.String, java.lang.Long)} .
     */
    @Test
    public void testParseLongStringLong() {
        final long value = 190L;
        final long expectedIfNullOrFalse = 190L;
        final long expected = 190L;

        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong(null, value));
        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong("", value));
        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong("   SS ", value));
        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong("  12 ", value));

        assertEquals(expected, (long) NumberUtils.parseLong("190", value));
        assertEquals(-expected, (long) NumberUtils.parseLong("-190", value));
        assertEquals(expected, (long) NumberUtils.parseLong("+190", value));
        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong("190L", value));
        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong("190.0", value));
        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong("190.0D", value));
        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong("190.0F", value));

        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong(null, value, false));
        assertEquals(expectedIfNullOrFalse, (long) NumberUtils.parseLong("", value, false));
        assertException(() -> NumberUtils.parseLong("   SS ", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseLong("  12 ", value, false), NumberFormatException.class);

        assertEquals(expected, (long) NumberUtils.parseLong("190", value, false));
        assertEquals(-expected, (long) NumberUtils.parseLong("-190", value, false));
        assertEquals(expected, (long) NumberUtils.parseLong("+190", value, false));
        assertException(() -> NumberUtils.parseLong("190L", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseLong("190.0", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseLong("190.0D", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseLong("190.0F", value, false), NumberFormatException.class);
    }

    /**
     * Test method for {@link NumberUtils#parseDouble(java.lang.String)}.
     */
    @Test
    public void testParseDoubleString() {
        final double delta = 0.001d;
        final double expected = 10.0d;

        assertNull(NumberUtils.parseDouble(null));
        assertNull(NumberUtils.parseDouble(""));
        assertNull(NumberUtils.parseDouble("   SS "));

        assertEquals(expected, NumberUtils.parseDouble("  10 "), delta);
        assertEquals(expected, NumberUtils.parseDouble("10"), delta);
        assertEquals(-expected, NumberUtils.parseDouble("-10"), delta);
        assertNull(NumberUtils.parseDouble("10L"));
        assertEquals(expected, (double) NumberUtils.parseDouble("10.0"), delta);
        assertEquals(expected, NumberUtils.parseDouble("10.0D"), delta);
        assertEquals(expected, NumberUtils.parseDouble("10.0F"), delta);

        assertNull(NumberUtils.parseDouble(null, false));
        assertNull(NumberUtils.parseDouble("", false));
        assertException(() -> NumberUtils.parseDouble("   SS ", false), NumberFormatException.class);

        assertEquals(expected, NumberUtils.parseDouble("  10 ", false), delta);
        assertEquals(expected, NumberUtils.parseDouble("10", false), delta);
        assertEquals(-expected, NumberUtils.parseDouble("-10", false), delta);
        assertException(() -> NumberUtils.parseDouble("10L", false), NumberFormatException.class);
        assertEquals(expected, (double) NumberUtils.parseDouble("10.0", false), delta);
        assertEquals(expected, NumberUtils.parseDouble("10.0D", false), delta);
        assertEquals(expected, NumberUtils.parseDouble("10.0F", false), delta);
    }

    /**
     * Test method for
     * {@link NumberUtils#parseDouble(java.lang.String, java.lang.Double)} .
     */
    @Test
    public void testParseDoubleStringDouble() {
        final double delta = 0.001d;
        final double value = 19.0d;
        final double expectedIfNullOrFalse = 19.0d;
        final double expected = 10.0d;

        assertEquals(expectedIfNullOrFalse, (double) NumberUtils.parseDouble(null, value), delta);
        assertEquals(expectedIfNullOrFalse, (double) NumberUtils.parseDouble("", value), delta);
        assertEquals(expectedIfNullOrFalse, (double) NumberUtils.parseDouble("   SS ", value), delta);

        assertEquals(expected, (double) NumberUtils.parseDouble("  10 ", value), delta);
        assertEquals(expected, (double) NumberUtils.parseDouble("10", value), delta);
        assertEquals(-expected, (double) NumberUtils.parseDouble("-10", value), delta);
        assertEquals(expectedIfNullOrFalse, (double) NumberUtils.parseDouble("10L", value), delta);
        assertEquals(expected, (double) NumberUtils.parseDouble("10.0", value), delta);
        assertEquals(expected, (double) NumberUtils.parseDouble("10.0D", value), delta);
        assertEquals(expected, (double) NumberUtils.parseDouble("10.0F", value), delta);

        assertEquals(expectedIfNullOrFalse, (double) NumberUtils.parseDouble(null, value, false), delta);
        assertEquals(expectedIfNullOrFalse, (double) NumberUtils.parseDouble("", value, false), delta);
        assertException(() -> NumberUtils.parseDouble("   SS ", value, false), NumberFormatException.class);

        assertEquals(expected, (double) NumberUtils.parseDouble("  10 ", value, false), delta);
        assertEquals(expected, (double) NumberUtils.parseDouble("10", value, false), delta);
        assertEquals(-expected, (double) NumberUtils.parseDouble("-10", value, false), delta);
        assertException(() -> NumberUtils.parseDouble("10L", value, false), NumberFormatException.class);
        assertEquals(expected, (double) NumberUtils.parseDouble("10.0", value, false), delta);
        assertEquals(expected, (double) NumberUtils.parseDouble("10.0D", value, false), delta);
        assertEquals(expected, (double) NumberUtils.parseDouble("10.0F", value, false), delta);
    }

    /**
     * Test method for {@link NumberUtils#parseShort(java.lang.String)}.
     */
    @Test
    public void testParseShortString() {
        final short expected = 10;

        assertNull(NumberUtils.parseShort(null));
        assertNull(NumberUtils.parseShort(""));
        assertNull(NumberUtils.parseShort("   SS "));
        assertNull(NumberUtils.parseShort("  12 "));

        assertEquals(expected, (short) NumberUtils.parseShort("10"));
        assertEquals(-expected, (short) NumberUtils.parseShort("-10"));
        assertNull(NumberUtils.parseShort("10L"));
        assertNull(NumberUtils.parseShort("10.0"));
        assertNull(NumberUtils.parseShort("10.0D"));
        assertNull(NumberUtils.parseShort("10.0F"));

        assertNull(NumberUtils.parseShort(null));
        assertNull(NumberUtils.parseShort(""));
        assertException(() -> NumberUtils.parseShort("   SS ", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("  12 ", false), NumberFormatException.class);

        assertEquals(expected, (short) NumberUtils.parseShort("10", false));
        assertEquals(-expected, (short) NumberUtils.parseShort("-10", false));
        assertException(() -> NumberUtils.parseShort("10L", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("10.0", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("10.0D", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("10.0F", false), NumberFormatException.class);
    }

    /**
     * Test method for
     * {@link NumberUtils#parseShort(java.lang.String, java.lang.Short)} .
     */
    @Test
    public void testParseShortStringShort() {
        final short value = 19;
        final short expectedIfNullOrFalse = 19;
        final short expected = 10;

        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort(null, value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort("", value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort("   SS ", value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort("  12 ", value));

        assertEquals(expected, (short) NumberUtils.parseShort("10", value));
        assertEquals(-expected, (short) NumberUtils.parseShort("-10", value));
        assertEquals(expected, (short) NumberUtils.parseShort("+10", value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort(String.valueOf(Short.MAX_VALUE + 1), value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort(String.valueOf(Short.MIN_VALUE - 1), value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort("10L", value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort("10L", value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort("10.0", value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort("10.0D", value));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort("10.0F", value));

        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort(null, value, false));
        assertEquals(expectedIfNullOrFalse, (short) NumberUtils.parseShort("", value, false));
        assertException(() -> NumberUtils.parseShort("   SS ", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("  12 ", value, false), NumberFormatException.class);

        assertEquals(expected, (short) NumberUtils.parseShort("10", value, false));
        assertEquals(-expected, (short) NumberUtils.parseShort("-10", value, false));
        assertEquals(expected, (short) NumberUtils.parseShort("+10", value, false));
        assertException(() -> NumberUtils.parseShort(String.valueOf(Short.MAX_VALUE + 1), value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort(String.valueOf(Short.MIN_VALUE - 1), value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("10L", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("10L", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("10.0", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("10.0D", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseShort("10.0F", value, false), NumberFormatException.class);
    }

    /**
     * Test method for {@link NumberUtils#parseByte(java.lang.String)}.
     */
    @Test
    public void testParseByteString() {
        final byte expected = 10;

        assertNull(NumberUtils.parseByte(null));
        assertNull(NumberUtils.parseByte(""));
        assertNull(NumberUtils.parseByte("   SS "));
        assertNull(NumberUtils.parseByte("  12 "));

        assertEquals(expected, (byte) NumberUtils.parseByte("10"));
        assertEquals(-expected, (byte) NumberUtils.parseByte("-10"));
        assertNull(NumberUtils.parseByte("10L"));
        assertNull(NumberUtils.parseByte("10.0"));
        assertNull(NumberUtils.parseByte("10.0D"));
        assertNull(NumberUtils.parseByte("10.0F"));

        assertNull(NumberUtils.parseByte(null, false));
        assertNull(NumberUtils.parseByte("", false));
        assertException(() -> NumberUtils.parseByte("   SS ", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("  12 ", false), NumberFormatException.class);

        assertEquals(expected, (byte) NumberUtils.parseByte("10", false));
        assertEquals(-expected, (byte) NumberUtils.parseByte("-10", false));
        assertException(() -> NumberUtils.parseByte("10L", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("10.0", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("10.0D", false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("10.0F", false), NumberFormatException.class);
    }

    /**
     * Test method for
     * {@link NumberUtils#parseByte(java.lang.String, java.lang.Byte)} .
     */
    @Test
    public void testParseByteStringByte() {
        final byte value = 19;
        final byte expectedIfNullOrFalse = 19;
        final byte expected = 10;

        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte(null, value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte("", value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte("   SS ", value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte("  12 ", value));

        assertEquals(expected, (byte) NumberUtils.parseByte("10", value));
        assertEquals(-expected, (byte) NumberUtils.parseByte("-10", value));
        assertEquals(expected, (byte) NumberUtils.parseByte("+10", value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte(String.valueOf(Byte.MAX_VALUE + 1), value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte(String.valueOf(Byte.MIN_VALUE - 1), value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte(String.valueOf(Long.MAX_VALUE), value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte(String.valueOf(Long.MIN_VALUE), value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte("10L", value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte("10L", value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte("10.0", value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte("10.0D", value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte("10.0F", value));

        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte(null, value));
        assertEquals(expectedIfNullOrFalse, (byte) NumberUtils.parseByte("", value));
        assertException(() -> NumberUtils.parseByte("   SS ", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("  12 ", value, false), NumberFormatException.class);

        assertEquals(expected, (byte) NumberUtils.parseByte("10", value, false));
        assertEquals(-expected, (byte) NumberUtils.parseByte("-10", value, false));
        assertEquals(expected, (byte) NumberUtils.parseByte("+10", value, false));
        assertException(() -> NumberUtils.parseByte(String.valueOf(Byte.MAX_VALUE + 1), value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte(String.valueOf(Byte.MIN_VALUE - 1), value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte(String.valueOf(Long.MAX_VALUE), value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte(String.valueOf(Long.MIN_VALUE), value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("10L", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("10L", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("10.0", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("10.0D", value, false), NumberFormatException.class);
        assertException(() -> NumberUtils.parseByte("10.0F", value, false), NumberFormatException.class);
    }

}
