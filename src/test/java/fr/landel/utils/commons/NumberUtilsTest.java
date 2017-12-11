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

import static fr.landel.utils.commons.NumberUtilsTest.EnumNumberDecimal.TYPE_NOT_SUPPORTED_LENIENT;
import static fr.landel.utils.commons.NumberUtilsTest.EnumNumberDecimal.TYPE_NOT_SUPPORTED_NOT_LENIENT;
import static fr.landel.utils.commons.NumberUtilsTest.EnumNumberDecimal.TYPE_SUPPORTED_LENIENT;
import static fr.landel.utils.commons.NumberUtilsTest.EnumNumberDecimal.TYPE_SUPPORTED_NOT_LENIENT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Check utility class (numbers).
 *
 * @since Nov 27, 2015
 * @author Gilles Landel
 *
 */
public class NumberUtilsTest extends AbstractTest {

    /**
     * Test constructor for {@link NumberUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(NumberUtils.class));
    }

    /**
     * Test method for {@link NumberUtils#isEqual(Double, Double)} .
     */
    @Test
    public void testIsEqualDoubleDouble() {
        assertTrue(NumberUtils.isEqual(5565680.25d, 5565680.25d));
        assertTrue(NumberUtils.isEqual(0.00000000000000000000000001d, 0.00000000000000000000000001d));
        assertFalse(NumberUtils.isEqual(0.00000000000000000000000001d, 0.00000000000000000000000002d));
        assertFalse(NumberUtils.isEqual(0.0000000000000000000000001d, 0.0000000000000000000000002d));
    }

    /**
     * Test method for {@link NumberUtils#isEqual(Double, Double, Integer)} .
     */
    @Test
    public void testIsEqualDoubleDoubleInt() {
        assertTrue(NumberUtils.isEqual(0.25d, 0.25d, 5));
        assertTrue(NumberUtils.isEqual(0.00012d, 0.00013d, 5));
        assertFalse(NumberUtils.isEqual(0.00012d, 0.00013d, 6));
        assertTrue(NumberUtils.isEqual(0.00012d, 0.00013d, null));
        assertTrue(NumberUtils.isEqual((Double) null, (Double) null, 5));
        assertFalse(NumberUtils.isEqual(0.00013d, (Double) null, 5));
        assertFalse(NumberUtils.isEqual((Double) null, 0.00013d, 5));
    }

    /**
     * Test method for {@link NumberUtils#isEqual(Float, Float)} .
     */
    @Test
    public void testIsEqualFloatFloat() {
        assertTrue(NumberUtils.isEqual(5565680.25f, 5565680.25f));
        assertTrue(NumberUtils.isEqual(0.0000000000000000000000001f, 0.0000000000000000000000001f));
        assertFalse(NumberUtils.isEqual(0.00000000000000000000000001f, 0.00000000000000000000000002f));
        assertFalse(NumberUtils.isEqual(0.0000000000000000000000001f, 0.0000000000000000000000002f));
    }

    /**
     * Test method for {@link NumberUtils#isEqual(Float, Float, Integer)} .
     */
    @Test
    public void testIsEqualFloatFloatInt() {
        assertTrue(NumberUtils.isEqual(0.25f, 0.25f, 5));
        assertTrue(NumberUtils.isEqual(0.000012f, 0.000013f, 5));
        assertFalse(NumberUtils.isEqual(0.00012f, 0.00013f, 6));
        assertFalse(NumberUtils.isEqual(0.00012f, 0.00013f, null));
        assertTrue(NumberUtils.isEqual((Float) null, (Float) null, 5));
        assertFalse(NumberUtils.isEqual(0.00013f, (Float) null, 5));
        assertFalse(NumberUtils.isEqual((Float) null, 0.00013f, 5));
    }

    /**
     * Test method for {@link NumberUtils#getDefaultIfNull(Number, Number)} .
     */
    @Test
    public void testGetDefaultIfNull() {
        final Float num = 10.0f;
        final Float defaultNum = 15.0f;

        assertEquals(num, NumberUtils.getDefaultIfNull(num, defaultNum));
        assertEquals(defaultNum, NumberUtils.getDefaultIfNull(null, defaultNum));
        assertEquals(num, NumberUtils.getDefaultIfNull(num, null));
        assertNull(NumberUtils.getDefaultIfNull(null, null));
    }

    /**
     * Test method for {@link NumberUtils#round(java.lang.Double)}.
     */
    @Test
    public void testRound() {
        final double val = 10.358941684565d;
        final float expected = 10.4f;

        assertNull(NumberUtils.round(null));
        assertTrue(NumberUtils.isEqual(expected, NumberUtils.round(val), 1));
    }

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

    /**
     * Test method for {@link NumberUtils#isByte(java.lang.Number)} .
     */
    @Test
    public void testNumber1() {
        Number numByte = (byte) 12;
        Number numShort = (short) 12;
        Number numInteger = (int) 12;
        Number numLong = 12L;
        Number numFloat = 12.0f;
        Number numDouble = 12.0d;

        assertTrue(NumberUtils.isByte(numByte));
        assertFalse(NumberUtils.isShort(numByte));
        assertFalse(NumberUtils.isInteger(numByte));
        assertFalse(NumberUtils.isLong(numByte));
        assertFalse(NumberUtils.isFloat(numByte));
        assertFalse(NumberUtils.isDouble(numByte));
        assertFalse(NumberUtils.isBigInteger(numByte));
        assertFalse(NumberUtils.isBigDecimal(numByte));
        assertFalse(NumberUtils.isAtomicInteger(numByte));
        assertFalse(NumberUtils.isAtomicLong(numByte));

        assertFalse(NumberUtils.isByte(numShort));
        assertTrue(NumberUtils.isShort(numShort));
        assertFalse(NumberUtils.isInteger(numShort));
        assertFalse(NumberUtils.isLong(numShort));
        assertFalse(NumberUtils.isFloat(numShort));
        assertFalse(NumberUtils.isDouble(numShort));
        assertFalse(NumberUtils.isBigInteger(numShort));
        assertFalse(NumberUtils.isBigDecimal(numShort));
        assertFalse(NumberUtils.isAtomicInteger(numShort));
        assertFalse(NumberUtils.isAtomicLong(numShort));

        assertFalse(NumberUtils.isByte(numInteger));
        assertFalse(NumberUtils.isShort(numInteger));
        assertTrue(NumberUtils.isInteger(numInteger));
        assertFalse(NumberUtils.isLong(numInteger));
        assertFalse(NumberUtils.isFloat(numInteger));
        assertFalse(NumberUtils.isDouble(numInteger));
        assertFalse(NumberUtils.isBigInteger(numInteger));
        assertFalse(NumberUtils.isBigDecimal(numInteger));
        assertFalse(NumberUtils.isAtomicInteger(numInteger));
        assertFalse(NumberUtils.isAtomicLong(numInteger));

        assertFalse(NumberUtils.isByte(numLong));
        assertFalse(NumberUtils.isShort(numLong));
        assertFalse(NumberUtils.isInteger(numLong));
        assertTrue(NumberUtils.isLong(numLong));
        assertFalse(NumberUtils.isFloat(numLong));
        assertFalse(NumberUtils.isDouble(numLong));
        assertFalse(NumberUtils.isBigInteger(numLong));
        assertFalse(NumberUtils.isBigDecimal(numLong));
        assertFalse(NumberUtils.isAtomicInteger(numLong));
        assertFalse(NumberUtils.isAtomicLong(numLong));

        assertFalse(NumberUtils.isByte(numFloat));
        assertFalse(NumberUtils.isShort(numFloat));
        assertFalse(NumberUtils.isInteger(numFloat));
        assertFalse(NumberUtils.isLong(numFloat));
        assertTrue(NumberUtils.isFloat(numFloat));
        assertFalse(NumberUtils.isDouble(numFloat));
        assertFalse(NumberUtils.isBigInteger(numFloat));
        assertFalse(NumberUtils.isBigDecimal(numFloat));
        assertFalse(NumberUtils.isAtomicInteger(numFloat));
        assertFalse(NumberUtils.isAtomicLong(numFloat));

        assertFalse(NumberUtils.isByte(numDouble));
        assertFalse(NumberUtils.isShort(numDouble));
        assertFalse(NumberUtils.isInteger(numDouble));
        assertFalse(NumberUtils.isLong(numDouble));
        assertFalse(NumberUtils.isFloat(numDouble));
        assertTrue(NumberUtils.isDouble(numDouble));
        assertFalse(NumberUtils.isBigInteger(numDouble));
        assertFalse(NumberUtils.isBigDecimal(numDouble));
        assertFalse(NumberUtils.isAtomicInteger(numDouble));
        assertFalse(NumberUtils.isAtomicLong(numDouble));
    }

    /**
     * Test method for {@link NumberUtils#isByte(java.lang.Number)} .
     */
    @Test
    public void testNumber2() {
        Number numBigInteger = BigInteger.valueOf(12L);
        Number numBigDecimal = BigDecimal.valueOf(12.0d);
        Number numNull = null;
        Number numAInteger = new AtomicInteger(12);
        Number numALong = new AtomicLong(12L);

        assertFalse(NumberUtils.isByte(numBigInteger));
        assertFalse(NumberUtils.isShort(numBigInteger));
        assertFalse(NumberUtils.isInteger(numBigInteger));
        assertFalse(NumberUtils.isLong(numBigInteger));
        assertFalse(NumberUtils.isFloat(numBigInteger));
        assertFalse(NumberUtils.isDouble(numBigInteger));
        assertTrue(NumberUtils.isBigInteger(numBigInteger));
        assertFalse(NumberUtils.isBigDecimal(numBigInteger));
        assertFalse(NumberUtils.isAtomicInteger(numBigInteger));
        assertFalse(NumberUtils.isAtomicLong(numBigInteger));

        assertFalse(NumberUtils.isByte(numBigDecimal));
        assertFalse(NumberUtils.isShort(numBigDecimal));
        assertFalse(NumberUtils.isInteger(numBigDecimal));
        assertFalse(NumberUtils.isLong(numBigDecimal));
        assertFalse(NumberUtils.isFloat(numBigDecimal));
        assertFalse(NumberUtils.isDouble(numBigDecimal));
        assertFalse(NumberUtils.isBigInteger(numBigDecimal));
        assertTrue(NumberUtils.isBigDecimal(numBigDecimal));
        assertFalse(NumberUtils.isAtomicInteger(numBigDecimal));
        assertFalse(NumberUtils.isAtomicLong(numBigDecimal));

        assertFalse(NumberUtils.isByte(numNull));
        assertFalse(NumberUtils.isShort(numNull));
        assertFalse(NumberUtils.isInteger(numNull));
        assertFalse(NumberUtils.isLong(numNull));
        assertFalse(NumberUtils.isFloat(numNull));
        assertFalse(NumberUtils.isDouble(numNull));
        assertFalse(NumberUtils.isBigInteger(numNull));
        assertFalse(NumberUtils.isBigDecimal(numNull));
        assertFalse(NumberUtils.isAtomicInteger(numNull));
        assertFalse(NumberUtils.isAtomicLong(numNull));

        assertFalse(NumberUtils.isByte(numAInteger));
        assertFalse(NumberUtils.isShort(numAInteger));
        assertFalse(NumberUtils.isInteger(numAInteger));
        assertFalse(NumberUtils.isLong(numAInteger));
        assertFalse(NumberUtils.isFloat(numAInteger));
        assertFalse(NumberUtils.isDouble(numAInteger));
        assertFalse(NumberUtils.isBigInteger(numAInteger));
        assertFalse(NumberUtils.isBigDecimal(numAInteger));
        assertTrue(NumberUtils.isAtomicInteger(numAInteger));
        assertFalse(NumberUtils.isAtomicLong(numAInteger));

        assertFalse(NumberUtils.isByte(numALong));
        assertFalse(NumberUtils.isShort(numALong));
        assertFalse(NumberUtils.isInteger(numALong));
        assertFalse(NumberUtils.isLong(numALong));
        assertFalse(NumberUtils.isFloat(numALong));
        assertFalse(NumberUtils.isDouble(numALong));
        assertFalse(NumberUtils.isBigInteger(numALong));
        assertFalse(NumberUtils.isBigDecimal(numALong));
        assertFalse(NumberUtils.isAtomicInteger(numALong));
        assertTrue(NumberUtils.isAtomicLong(numALong));
    }

    /**
     * Test method for {@link NumberUtils#isNumberInteger} .
     */
    @Test
    public void testIsNumberInteger() {

        // type supported
        Pattern p = Pattern.compile("[+-]?\\d+[lL]?");

        assertTrue(p.matcher("25").matches());
        assertTrue(p.matcher("+25").matches());
        assertFalse(p.matcher("-25.25").matches());
        assertFalse(p.matcher("-25.25d").matches());
        assertFalse(p.matcher(".25").matches());
        assertFalse(p.matcher(".25f").matches());
        assertFalse(p.matcher("2f").matches());
        assertTrue(p.matcher("2l").matches());
        assertTrue(p.matcher("2L").matches());

        assertTrue(NumberUtils.isNumberInteger("25", true));
        assertTrue(NumberUtils.isNumberInteger("+25", true));
        assertFalse(NumberUtils.isNumberInteger("-25.25", true));
        assertFalse(NumberUtils.isNumberInteger("-25.25d", true));
        assertFalse(NumberUtils.isNumberInteger(".25", true));
        assertFalse(NumberUtils.isNumberInteger(".25f", true));
        assertFalse(NumberUtils.isNumberInteger("2f", true));
        assertTrue(NumberUtils.isNumberInteger("2l", true));
        assertTrue(NumberUtils.isNumberInteger("2L", true));

        // type not supported
        p = Pattern.compile("[+-]?\\d+");

        assertTrue(p.matcher("25").matches());
        assertTrue(p.matcher("+25").matches());
        assertFalse(p.matcher("-25.25").matches());
        assertFalse(p.matcher("-25.25d").matches());
        assertFalse(p.matcher(".25").matches());
        assertFalse(p.matcher(".25f").matches());
        assertFalse(p.matcher("2f").matches());
        assertFalse(p.matcher("2l").matches());
        assertFalse(p.matcher("2L").matches());

        assertTrue(NumberUtils.isNumberInteger("25"));
        assertTrue(NumberUtils.isNumberInteger("+25"));
        assertFalse(NumberUtils.isNumberInteger("-25.25"));
        assertFalse(NumberUtils.isNumberInteger("-25.25d"));
        assertFalse(NumberUtils.isNumberInteger(".25"));
        assertFalse(NumberUtils.isNumberInteger(".25f"));
        assertFalse(NumberUtils.isNumberInteger("2f"));
        assertFalse(NumberUtils.isNumberInteger("2l"));
        assertFalse(NumberUtils.isNumberInteger("2L"));

        // errors

        assertFalse(NumberUtils.isNumberInteger("25."));
        assertFalse(NumberUtils.isNumberInteger("25.."));
        assertFalse(NumberUtils.isNumberInteger("."));
        assertFalse(NumberUtils.isNumberInteger("text"));
        assertFalse(NumberUtils.isNumberInteger((String) null));

        // Class

        assertTrue(NumberUtils.isNumberInteger(Byte.class));
        assertTrue(NumberUtils.isNumberInteger(Short.class));
        assertTrue(NumberUtils.isNumberInteger(Integer.class));
        assertTrue(NumberUtils.isNumberInteger(Long.class));
        assertFalse(NumberUtils.isNumberInteger(Float.class));
        assertFalse(NumberUtils.isNumberInteger(Double.class));
        assertTrue(NumberUtils.isNumberInteger(BigInteger.class));
        assertFalse(NumberUtils.isNumberInteger(BigDecimal.class));
        assertFalse(NumberUtils.isNumberInteger(String.class));
        assertFalse(NumberUtils.isNumberInteger((Class<Number>) null));
        assertTrue(NumberUtils.isNumberInteger(AtomicInteger.class));
        assertTrue(NumberUtils.isNumberInteger(AtomicLong.class));

        // Object

        assertTrue(NumberUtils.isNumberInteger((byte) 12));
        assertTrue(NumberUtils.isNumberInteger((short) 12));
        assertTrue(NumberUtils.isNumberInteger(12));
        assertTrue(NumberUtils.isNumberInteger(12L));
        assertFalse(NumberUtils.isNumberInteger(12F));
        assertFalse(NumberUtils.isNumberInteger(12D));
        assertTrue(NumberUtils.isNumberInteger(BigInteger.TEN));
        assertFalse(NumberUtils.isNumberInteger(BigDecimal.TEN));
        assertFalse(NumberUtils.isNumberInteger((Object) ""));
        assertFalse(NumberUtils.isNumberInteger((Object) null));
    }

    private static void checkDecimalNumber(final boolean expected, final Pattern p, final String string, final EnumNumberDecimal mode) {
        if (expected) {
            assertTrue(p.matcher(string).matches());

            switch (mode) {
            case TYPE_NOT_SUPPORTED_NOT_LENIENT:
                assertTrue(NumberUtils.isNumberDecimal(string));
                break;
            case TYPE_SUPPORTED_NOT_LENIENT:
                assertTrue(NumberUtils.isNumberDecimal(string, true));
                break;
            case TYPE_SUPPORTED_LENIENT:
                assertTrue(NumberUtils.isNumberDecimal(string, true, true));
                break;
            case TYPE_NOT_SUPPORTED_LENIENT:
                assertTrue(NumberUtils.isNumberDecimal(string, false, true));
                break;
            default:
                fail("no mode defined or unknown");
                break;
            }
        } else {
            assertFalse(p.matcher(string).matches());

            switch (mode) {
            case TYPE_NOT_SUPPORTED_NOT_LENIENT:
                assertFalse(NumberUtils.isNumberDecimal(string));
                break;
            case TYPE_SUPPORTED_NOT_LENIENT:
                assertFalse(NumberUtils.isNumberDecimal(string, true));
                break;
            case TYPE_SUPPORTED_LENIENT:
                assertFalse(NumberUtils.isNumberDecimal(string, true, true));
                break;
            case TYPE_NOT_SUPPORTED_LENIENT:
                assertFalse(NumberUtils.isNumberDecimal(string, false, true));
                break;
            default:
                fail("no mode defined or unknown");
                break;
            }
        }
    }

    /**
     * Test method for {@link NumberUtils#isNumberDecimal} .
     */
    @Test
    public void testIsNumberDecimalTypeSupportedNotLenient() {

        // type supported + not lenient
        Pattern p = Pattern.compile("[+-]?(\\d+[dfDF]|(\\d+)?\\.\\d+([eE][+-]?\\d+)?[dfDF]?)");

        assertNotNull(p);

        checkDecimalNumber(false, p, "25", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "+25", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25d", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, ".25", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, ".25f", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "2f", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "2l", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25E", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25E2d", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25e2D", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25E2f", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25e2F", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25E+2d", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25e-2F", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2de", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2d2", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25E2", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25e2", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25e-21", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25e+22", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25+22", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25+e22", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+2.2", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+2e2", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-2525e+2.2", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-2525e2+22", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-2525e2+2-2", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25252+2e-2", TYPE_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25252e-2+3", TYPE_SUPPORTED_NOT_LENIENT);
    }

    /**
     * Test method for {@link NumberUtils#isNumberDecimal} .
     */
    @Test
    public void testIsNumberDecimalTypeSupportedLenient() {

        // type supported + lenient
        Pattern p = Pattern.compile("[+-]?(\\d+|(\\d+)?\\.\\d+)([eE][+-]?\\d+)?[dfDF]?");

        assertNotNull(p);

        checkDecimalNumber(true, p, "25", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "+25", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25d", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, ".25", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, ".25f", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "2f", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "2l", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25E", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25E2d", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25E2D", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25E2f", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25e2F", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25E+2d", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25e-2F", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2de", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2d2", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25E2", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25e2", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25e-21", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25e+22", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25+22", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25+e22", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+2.2", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+2e2", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-2525e+2.2", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-2525e2+22", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-2525e2+2-2", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25252+2e-2", TYPE_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25252e-2+3", TYPE_SUPPORTED_LENIENT);
    }

    /**
     * Test method for {@link NumberUtils#isNumberDecimal} .
     */
    @Test
    public void testIsNumberDecimalTypeNotSupportedNotLenient() {

        // type not supported + not lenient
        Pattern p = Pattern.compile("[+-]?(\\d+)?\\.\\d+([eE][+-]?\\d+)?");

        assertNotNull(p);

        checkDecimalNumber(false, p, "25", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "+25", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25d", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, ".25", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, ".25f", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "2f", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "2l", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25E", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25E2d", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25e2D", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25E2f", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25e2F", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2d", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25e-2F", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2de", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2d2", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25E2", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25e2", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25e-21", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(true, p, "-25.25e+22", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25+22", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25+e22", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+2.2", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+2e2", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-2525e+2.2", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-2525e2+22", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-2525e2+2-2", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25252+2e-2", TYPE_NOT_SUPPORTED_NOT_LENIENT);
        checkDecimalNumber(false, p, "-25252e-2+3", TYPE_NOT_SUPPORTED_NOT_LENIENT);
    }

    /**
     * Test method for {@link NumberUtils#isNumberDecimal} .
     */
    @Test
    public void testIsNumberDecimalTypeNotSupportedLenient() {

        // type not supported + lenient
        Pattern p = Pattern.compile("[+-]?(\\d+|(\\d+)?\\.\\d+)?([eE][+-]?\\d+)?");

        assertNotNull(p);

        checkDecimalNumber(true, p, "25", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "+25", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25d", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, ".25", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, ".25f", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "2f", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "2l", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25E", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25E2d", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e2D", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e2f", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e2F", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2de", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2d2", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25E+2d", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e-2F", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25E2", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25e2", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25e-21", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(true, p, "-25.25e+22", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25+22", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25+e22", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+2.2", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25.25e+2e2", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-2525e+2.2", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-2525e2+22", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-2525e2+2-2", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25252+2e-2", TYPE_NOT_SUPPORTED_LENIENT);
        checkDecimalNumber(false, p, "-25252e-2+3", TYPE_NOT_SUPPORTED_LENIENT);
    }

    /**
     * Test method for {@link NumberUtils#isNumberDecimal} .
     */
    @Test
    public void testIsNumberDecimalInfinity() {

        assertTrue(NumberUtils.isNumberDecimal("Infinity"));
        assertTrue(NumberUtils.isNumberDecimal("-Infinity"));
        assertTrue(NumberUtils.isNumberDecimal("+Infinity"));
        assertFalse(NumberUtils.isNumberDecimal("Infinite"));
        assertFalse(NumberUtils.isNumberDecimal("-Infinite"));
        assertFalse(NumberUtils.isNumberDecimal("+Infinite"));
        assertFalse(NumberUtils.isNumberDecimal("I"));
        assertFalse(NumberUtils.isNumberDecimal("-I"));
        assertFalse(NumberUtils.isNumberDecimal("+I"));
        assertFalse(NumberUtils.isNumberDecimal("I1"));
        assertFalse(NumberUtils.isNumberDecimal("-I2"));
        assertFalse(NumberUtils.isNumberDecimal("+I3"));
        assertFalse(NumberUtils.isNumberDecimal("infinity"));
        assertFalse(NumberUtils.isNumberDecimal("-infinity"));
        assertFalse(NumberUtils.isNumberDecimal("+infinity"));
    }

    /**
     * Test method for {@link NumberUtils#isNumberDecimal} .
     */
    @Test
    public void testIsNumberDecimal() {

        // errors

        assertFalse(NumberUtils.isNumberDecimal("25."));
        assertFalse(NumberUtils.isNumberDecimal("25.."));
        assertFalse(NumberUtils.isNumberDecimal("."));
        assertFalse(NumberUtils.isNumberDecimal("text"));
        assertFalse(NumberUtils.isNumberDecimal((String) null));

        // Class

        assertFalse(NumberUtils.isNumberDecimal(Byte.class));
        assertFalse(NumberUtils.isNumberDecimal(Short.class));
        assertFalse(NumberUtils.isNumberDecimal(Integer.class));
        assertFalse(NumberUtils.isNumberDecimal(Long.class));
        assertTrue(NumberUtils.isNumberDecimal(Float.class));
        assertTrue(NumberUtils.isNumberDecimal(Double.class));
        assertFalse(NumberUtils.isNumberDecimal(BigInteger.class));
        assertTrue(NumberUtils.isNumberDecimal(BigDecimal.class));
        assertFalse(NumberUtils.isNumberDecimal(String.class));
        assertFalse(NumberUtils.isNumberDecimal((Class<Number>) null));
        assertFalse(NumberUtils.isNumberDecimal(AtomicInteger.class));
        assertFalse(NumberUtils.isNumberDecimal(AtomicLong.class));

        // Object

        assertTrue(NumberUtils.isNumberDecimal((Object) 12F));
        assertFalse(NumberUtils.isNumberDecimal((byte) 12));
        assertFalse(NumberUtils.isNumberDecimal((short) 12));
        assertFalse(NumberUtils.isNumberDecimal(12));
        assertFalse(NumberUtils.isNumberDecimal(12L));
        assertTrue(NumberUtils.isNumberDecimal(12F));
        assertTrue(NumberUtils.isNumberDecimal(12D));
        assertFalse(NumberUtils.isNumberDecimal(BigInteger.TEN));
        assertTrue(NumberUtils.isNumberDecimal(BigDecimal.TEN));
        assertFalse(NumberUtils.isNumberDecimal((Object) ""));
        assertFalse(NumberUtils.isNumberDecimal((Object) null));
    }

    /**
     * Test method for {@link NumberUtils#signum} .
     */
    @Test
    public void testSignum() {
        assertEquals(-1, NumberUtils.signum((byte) -10));
        assertEquals(0, NumberUtils.signum((byte) 0));
        assertEquals(1, NumberUtils.signum((byte) 10));

        assertEquals(-1, NumberUtils.signum((short) -10));
        assertEquals(0, NumberUtils.signum((short) 0));
        assertEquals(1, NumberUtils.signum((short) 10));

        assertEquals(-1, NumberUtils.signum(-10));
        assertEquals(0, NumberUtils.signum(0));
        assertEquals(1, NumberUtils.signum(10));

        assertEquals(-1, NumberUtils.signum(-10L));
        assertEquals(0, NumberUtils.signum(0L));
        assertEquals(1, NumberUtils.signum(10L));

        assertEquals(-1, NumberUtils.signum(-10F));
        assertEquals(0, NumberUtils.signum(0F));
        assertEquals(1, NumberUtils.signum(10F));

        assertEquals(-1, NumberUtils.signum(-10D));
        assertEquals(0, NumberUtils.signum(0D));
        assertEquals(1, NumberUtils.signum(10D));

        assertEquals(-1, NumberUtils.signum(BigInteger.valueOf(-12L)));
        assertEquals(0, NumberUtils.signum(BigInteger.valueOf(0L)));
        assertEquals(1, NumberUtils.signum(BigInteger.valueOf(12L)));

        assertEquals(-1, NumberUtils.signum(BigDecimal.valueOf(-12L)));
        assertEquals(0, NumberUtils.signum(BigDecimal.valueOf(0L)));
        assertEquals(1, NumberUtils.signum(BigDecimal.valueOf(12L)));

        assertEquals(-1, NumberUtils.signum(new AtomicInteger(-12)));
        assertEquals(0, NumberUtils.signum(new AtomicInteger(0)));
        assertEquals(1, NumberUtils.signum(new AtomicInteger(12)));

        assertEquals(-1, NumberUtils.signum(new AtomicLong(-12L)));
        assertEquals(0, NumberUtils.signum(new AtomicLong(0L)));
        assertEquals(1, NumberUtils.signum(new AtomicLong(12L)));

        assertEquals(0, NumberUtils.signum((Integer) null));

        assertEquals(0, NumberUtils.signum(new NewNumber()));
    }

    /**
     * Test method for {@link NumberUtils#isZero} .
     */
    @Test
    public void testIsZero() {
        assertTrue(NumberUtils.isZero((byte) 0));
        assertFalse(NumberUtils.isZero((byte) 1));

        assertTrue(NumberUtils.isZero((short) 0));
        assertFalse(NumberUtils.isZero((short) 1));

        assertTrue(NumberUtils.isZero(0));
        assertFalse(NumberUtils.isZero(1));

        assertTrue(NumberUtils.isZero(0L));
        assertFalse(NumberUtils.isZero(1L));

        assertTrue(NumberUtils.isZero(0F));
        assertFalse(NumberUtils.isZero(1F));

        assertTrue(NumberUtils.isZero(0D));
        assertFalse(NumberUtils.isZero(1D));

        assertTrue(NumberUtils.isZero(BigInteger.valueOf(0L)));
        assertFalse(NumberUtils.isZero(BigInteger.valueOf(1L)));

        assertTrue(NumberUtils.isZero(BigDecimal.valueOf(0L)));
        assertFalse(NumberUtils.isZero(BigDecimal.valueOf(1L)));

        assertTrue(NumberUtils.isZero(new AtomicInteger(0)));
        assertFalse(NumberUtils.isZero(new AtomicInteger(1)));

        assertTrue(NumberUtils.isZero(new AtomicLong(0)));
        assertFalse(NumberUtils.isZero(new AtomicLong(1)));

        assertFalse(NumberUtils.isZero((Integer) null));

        assertFalse(NumberUtils.isZero(new NewNumber()));
    }

    /**
     * Enumeration of check mode in {@link NumberUtils#isNumberDecimal}
     */
    protected static enum EnumNumberDecimal {
        TYPE_NOT_SUPPORTED_NOT_LENIENT,
        TYPE_SUPPORTED_NOT_LENIENT,
        TYPE_SUPPORTED_LENIENT,
        TYPE_NOT_SUPPORTED_LENIENT;
    }

    private static class NewNumber extends Number {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = -5179043884358962228L;

        @Override
        public int intValue() {
            return 0;
        }

        @Override
        public long longValue() {
            return 0;
        }

        @Override
        public float floatValue() {
            return 0;
        }

        @Override
        public double doubleValue() {
            return 0;
        }
    }
}